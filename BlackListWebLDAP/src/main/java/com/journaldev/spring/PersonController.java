package com.journaldev.spring;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.journaldev.spring.model.ListDTO;
import com.journaldev.spring.model.QueueDTO;
import com.journaldev.spring.model.Resultado;
import com.journaldev.spring.model.ResultadoDTO;
import com.journaldev.spring.model.ScheduleDTO;
import com.journaldev.spring.model.ScoreDTO;
import com.journaldev.spring.model.SourceDTO;
import com.journaldev.spring.model.Status;
import com.journaldev.spring.service.IProcedureService;

@Controller
public class PersonController {

	private static final String PARAM_COMPLETE_REVIEW = "Complete Review";
	private static final String PARAM_IDSEARCH = "idSearch";
	private static final String REGISTROS = "registros";
	private static final String CANT_REGISTROS = "cantRegistros";
	@Resource
	private IProcedureService procedureService;
	String lqueue = "";

	private final Logger logger = LoggerFactory
			.getLogger(PersonController.class);

	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security Custom Login Form");
		model.addObject("message", "This is welcome page!");
		model.setViewName("login");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("search");
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,  HttpServletResponse response) throws IOException {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			model.setViewName("login");
			return model;
		}
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
			model.setViewName("login");
			return model;
		}
		model.setViewName("login");
		
		return model;
	}
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpServletRequest req,
			@ModelAttribute("name") String name,
			@ModelAttribute("list") String list,
			@ModelAttribute("score") String score) throws ServletException,
			IOException, ParseException {
		String user= req.getParameter("user");
		req.getSession().setAttribute("user",user);
		String save = req.getParameter("Save");
		String inputName = name;
	
		List<ListDTO> listas = procedureService.getAllList();
		List<ScoreDTO> scores = procedureService.getScores();
		req.setAttribute("listas", listas);
		req.setAttribute("scores", scores);
		req.setAttribute("cantRegistros", "0");
		if (inputName != null && !inputName.isEmpty() && (user!=null || !user.isEmpty())) {
			if (save == null) {

				ResultadoDTO rsn = procedureService.getBlacklist(name, list,
						user, score);
				List<Resultado> resultado = rsn.getResultado();
				int cregistros = rsn.getResultado().size();
				req.setAttribute(CANT_REGISTROS, cregistros);
				req.setAttribute("searchValueName", name);
				req.setAttribute("searchValueList", list);
				req.setAttribute("searchValueScore", score);
				req.setAttribute(REGISTROS, resultado);
				req.getSession().setAttribute("Resultado", rsn);
				req.getSession().setAttribute(CANT_REGISTROS, cregistros);
				req.getSession().setAttribute("listas", listas);
				req.getSession().setAttribute("scores", scores);
			} else {
				ResultadoDTO rsn = (ResultadoDTO) req.getSession()
						.getAttribute("Resultado");
				req.setAttribute(CANT_REGISTROS,
						req.getSession().getAttribute(CANT_REGISTROS));
				procedureService.save(rsn.getResultado(), rsn.getUser(),
						rsn.getName(), rsn.getList(), "Online",
						rsn.getListDate(), score);
				req.setAttribute(CANT_REGISTROS, "");
				req.setAttribute("searchValueName", "");
				req.setAttribute("searchValueList", "");
				req.setAttribute("searchValueScore", "");
				req.setAttribute("listas",
						req.getSession().getAttribute("listas"));
				req.setAttribute("scores",
						req.getSession().getAttribute("scores"));
				req.setAttribute(REGISTROS, "");
				req.setAttribute("cantRegistros", "0");
			}
		}

		return "search";
	}

	@RequestMapping(value = "/resultQ", method = RequestMethod.GET)
	public String resultQ(HttpServletRequest requ,
			@ModelAttribute("status") String status,
			@ModelAttribute("type") String type,
			@ModelAttribute("queue") String queue) throws ServletException,
			IOException, ParseException {
		String user= (String)requ.getSession().getAttribute("user");
		requ.getSession().setAttribute("user", user);
		if (status.equals("")) {
			status = "Pending";
		}
		if (type.equals("")) {
			type = "Online";
		}
		
		//setear variable desde los botones del paginador.
		int page=1;
		int cregistros =0;
		QueueDTO qDTO = procedureService.getQueueList(status, type,page);
		
		
		cregistros= qDTO.getCantRegistros();
		requ.setAttribute("search", "ok");
		requ.setAttribute(CANT_REGISTROS, cregistros);
		requ.setAttribute("lqueue", qDTO.getListQueue());
		requ.setAttribute("statusValue", status);
		requ.setAttribute("typeValue", type);
		requ.setAttribute("valorPage", page);
		
		int cPaginas=qDTO.getCantPaginas();
		requ.setAttribute("valorMaximo", cPaginas);
		
		
		if(requ.getParameter("pageMinus")!=null||requ.getParameter("pagePlus")!=null){
			if(requ.getParameter("pageMinus")!=null){
				int pageMinus=Integer.parseInt(requ.getParameter("pageMinus"));
				qDTO = procedureService.getQueueList(status, type,pageMinus);
				requ.setAttribute("lqueue", qDTO.getListQueue());
				requ.setAttribute("valorPage", pageMinus);
			}
			if(requ.getParameter("pagePlus")!=null){
				int pagePlus=Integer.parseInt(requ.getParameter("pagePlus"));
				qDTO = procedureService.getQueueList(status, type,pagePlus);
				requ.setAttribute("lqueue", qDTO.getListQueue());
				requ.setAttribute("valorPage", pagePlus);
			}
		}
		if (!queue.isEmpty()) {
			
			return "redirect:/details";

		}
		return "resultQ";
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String searchID(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String user= req.getParameter("user");
		if(user!=null){
			req.getSession().setAttribute("user", user);
			}
		String queue=req.getParameter("queue");
		String save = req.getParameter(PARAM_COMPLETE_REVIEW);
		String update = req.getParameter("update");
		String complete= req.getParameter("completeAll");
		int news = 0;
		user=(String) req.getSession().getAttribute("user");
		if (complete != null && !complete.isEmpty()) {
			procedureService.completeAllNews(queue,user);
		}


		if (update != null && !update.isEmpty()) {

			int id = Integer.parseInt(req.getParameter("update"));
			String status = req.getParameter("status_" + id);
			String text = req.getParameter("text_" + id);
			procedureService.updateStatusnText(id, status, text,user);
		}

		if (save != PARAM_COMPLETE_REVIEW) {
			int page=1;
			ResultadoDTO rsn = procedureService.getSearchID(Integer
					.parseInt(queue),page,user);

			List<Status> lstatus = procedureService.getAllStatus();
			List<Resultado> resultado = rsn.getResultado();
			int cregistros =0;
			cregistros = rsn.getCantRegistros();

			news=rsn.getNews();

			String idSearch = rsn.getIdSearch();
			req.setAttribute(CANT_REGISTROS, cregistros);
			req.setAttribute(REGISTROS, resultado);
			req.setAttribute("listDate", rsn.getListDate());
			req.setAttribute("lista", rsn.getList());
			req.setAttribute("lstatus", lstatus);
			req.setAttribute("name", rsn.getName());
			req.setAttribute("user", rsn.getUser());
			req.setAttribute("statusNow", rsn.getStatusNow());
			req.setAttribute("queue", queue);
			req.setAttribute("news", news);
			req.setAttribute(PARAM_COMPLETE_REVIEW, PARAM_COMPLETE_REVIEW);
			req.setAttribute("valorPage", page);
			int cPaginas=rsn.getCantPaginas();
			req.setAttribute("valorMaximo", cPaginas);
			
			if(req.getParameter("pageMinus")!=null||req.getParameter("pagePlus")!=null){
				if(req.getParameter("pageMinus")!=null){
					int pageMinus=Integer.parseInt(req.getParameter("pageMinus"));
					rsn = procedureService.getSearchID(Integer
							.parseInt(req.getParameter("queue")),pageMinus,user);
					resultado = rsn.getResultado();
					req.setAttribute(REGISTROS, resultado);
					req.setAttribute("valorPage", pageMinus);
				}
				if(req.getParameter("pagePlus")!=null){
					int pagePlus=Integer.parseInt(req.getParameter("pagePlus"));
					rsn = procedureService.getSearchID(Integer
							.parseInt(req.getParameter("queue")),pagePlus,user);
					resultado = rsn.getResultado();
					req.setAttribute(REGISTROS, resultado);
					req.setAttribute("valorPage", pagePlus);
				}
			}
		}

		if (req.getParameter(PARAM_IDSEARCH) != null
				&& !req.getParameter(PARAM_IDSEARCH).isEmpty()) {
			int idSearchUpdate = Integer.parseInt(req
					.getParameter(PARAM_IDSEARCH));
			procedureService.updateStatusResultSearch(idSearchUpdate,
					req.getParameter("statusNow"),user);
			return "redirect:/resultQ";
		}

		return "/details";
	}

	@RequestMapping(value = "/batch", method = RequestMethod.GET)
	public String batch(HttpServletRequest req) throws ParseException {
		String user= req.getParameter("user");		
		
		Date hoy = new Date();
		String mensaje = "";
		String[] vlista = null;
		String valueList = "";
		String valueDataList = "";	
		String search = req.getParameter("Search");	
		
		String id = req.getParameter("delete");
		String llist = req.getParameter("llist");
		List<ScheduleDTO> lSchedule = procedureService.getSchedules();
		//variables de listas para la pantalla
		List<ListDTO> listas = procedureService.getAllList();
		List<SourceDTO> sources = procedureService.getAllSources();
		List<ScoreDTO> scores = procedureService.getScores();							
		//variables seteadas para los combos
		req.setAttribute("scores", scores);
		req.setAttribute("listas", listas);
		req.setAttribute("sources", sources);
		req.setAttribute("lSchedules", lSchedule);
		
		//horas para el combo
		List<String> hora = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			if (i < 10)
				hora.add("0" + i+":00");
			else
				hora.add("" + i+":00");
		}
		req.setAttribute("hours", hora);
		
		if (search != null && search.equals("Search")) {
			//variables seleccionadas
			String lsource = req.getParameter("lsource");
			String date = req.getParameter("date");
			String hour = req.getParameter("hour");
			String from = req.getParameter("from");
			String to = req.getParameter("to");
			String score = req.getParameter("score");	
			SimpleDateFormat formateador = new SimpleDateFormat("MM/dd/yyyy");
			
			if(lsource.equals("CUSTOMERS")){
				from="-";
				to="-";			
			}			
			if (!llist.isEmpty()) {
				vlista = llist.split("/");
				if (vlista.length == 1) {
					valueList = vlista[0];
					valueDataList = "no data";
				} else {
					valueList = vlista[0];
					valueDataList = vlista[1];
				}
			}
			//validaciones
			//valida fecha de schedule no sea menos que hoy
			if (date != null && !date.isEmpty()) {			
				String hoyFormat = formateador.format(hoy);
				Date date1 = formateador.parse(date);
				Date date2 = formateador.parse(hoyFormat);
				int diferencia=date1.compareTo(date2);	
				int horaSys=hoy.getHours();
				int hourInt= Integer.parseInt(hour.replace(":00",""));
				if(diferencia<0 || (diferencia==0 && hourInt<=horaSys))				
					mensaje="The date must be later than today ";			
			}	
			//valida ingreso parametros
				if( !lsource.equals("CUSTOMERS") && (from.isEmpty() || to.isEmpty()) || date.isEmpty() )
					mensaje="To schedule you must add all the parameters";						

			//valida diferencia de 1 mes entre from y to
			if(from!=null && !from.isEmpty() && to!=null && !to.isEmpty()){
				if(!from.equals("-") && !to.equals("-")){
					String hoyFormat = formateador.format(hoy);
					Date date1 = formateador.parse(to);
					Date date2 = formateador.parse(hoyFormat);
					
					Calendar inicio = new GregorianCalendar();
			        Calendar fin = new GregorianCalendar();
			        inicio.setTime(formateador.parse(from));
			        fin.setTime(formateador.parse(to));			        
			        fin.add(Calendar.MONTH,1);
			        int diferencia=date1.compareTo(date2);
			        if(diferencia>0){				
						mensaje="The date range must be later than today ";
					}
			        else if(inicio.compareTo(fin)<0){
			        	   mensaje="The date range should not exceed 1 month ";
			        }
			        else
			        	mensaje="";
			        
				}
			}
			if (mensaje.isEmpty() && vlista != null && vlista.length > 0 && lsource != null
					&& !lsource.isEmpty() && date != null && !date.isEmpty()
					&& hour != null && !hour.isEmpty() && score != null && !score.isEmpty() && user!=null){
				procedureService.saveSchedule(user, lsource,valueList, valueDataList, from, to, date, hour, score);
				lSchedule = procedureService.getSchedules();				
		}			
		}
		//delete schedule
				if (id != null && !id.isEmpty()) {
					procedureService.deleteSchedule(Integer.parseInt(id));
					lSchedule = procedureService.getSchedules();
				}
		//limpia variables 
		req.setAttribute("lSchedules", lSchedule);
		req.setAttribute("message", mensaje);
		req.setAttribute("Search", "");
		req.setAttribute("llist", "");
		req.setAttribute("lsource", "");
		req.setAttribute("date", "");
		req.setAttribute("hour", "");
		req.setAttribute("minute", "");
		req.setAttribute("from", "");
		req.setAttribute("to", "");
		req.setAttribute("score", "");
		
		return "/batch";
	}

	public IProcedureService getProcedureService() {
		return procedureService;
	}

	public void setProcedureService(IProcedureService procedureService) {
		this.procedureService = procedureService;
	}

}
