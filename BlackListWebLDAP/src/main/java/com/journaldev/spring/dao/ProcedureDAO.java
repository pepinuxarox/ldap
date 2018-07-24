package com.journaldev.spring.dao;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.spring.PersonController;
import com.journaldev.spring.model.ListDTO;
import com.journaldev.spring.model.Queue;
import com.journaldev.spring.model.QueueDTO;
import com.journaldev.spring.model.Resultado;
import com.journaldev.spring.model.ResultadoDTO;
import com.journaldev.spring.model.ScheduleDTO;
import com.journaldev.spring.model.ScoreDTO;
import com.journaldev.spring.model.SourceDTO;
import com.journaldev.spring.model.Status;

@Transactional
@Repository("procedureDAOOnline")
public class ProcedureDAO implements IProcedure {

	private static final String PARAM_PENDING = "Pending";
	private static final String PARAM_COMPLETE = "Complete";
	private static final String PARAM_REVIEW = "Review";
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	private final Logger logger = LoggerFactory
			.getLogger(PersonController.class);

	@Override
	public ResultadoDTO getBlacklist(String texto, String lista, String user,
			String score) {

		StringBuilder filter = new StringBuilder();
		Resultado resultado = null;
		String listDate = "";
		ResultadoDTO rDTO = new ResultadoDTO();
		List<Resultado> lresultado = new ArrayList<Resultado>();
		try {
			filter.setLength(0);
			filter.append(" @texto ='").append(texto + "'");
			filter.append(",@lista  ='").append(lista + "'");
			filter.append(",@user  ='").append(user + "'");
			filter.append(",@filter  ='").append(score + "'");

			Session session = this.sessionFactory.getCurrentSession();

			Query query = session.createSQLQuery("Search_Names_list"
					.concat(filter.toString()));
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {

				Object[] row = (Object[]) list.get(i);
				resultado = new Resultado();
				resultado.setId((BigInteger) row[0]);
				resultado.setName((String) row[1]);
				resultado.setLastUpdateDate((String) row[2]);
				resultado.setSource((String) row[3]);
				resultado.setOriginalSource((String) row[4]);
				resultado.setScore((String) row[5]);
				resultado.setDob((String) row[6]);
				resultado.setNationality((String) row[7]);
				resultado.setAddress((String) row[8]);
				resultado.setStatus((String) row[9]);
				listDate = (String) row[10];
				lresultado.add(resultado);
			}

			rDTO.setResultado(lresultado);
			rDTO.setList(lista);
			rDTO.setName(texto);
			rDTO.setUser(user);
			rDTO.setListDate(listDate);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return rDTO;
	}

	public void save(List<Resultado> resultSearch, String user,
			String nameSearch, String list, String typeSearch, String listDate,
			String score) throws ParseException {

		long initTime = System.currentTimeMillis();

		StringBuilder filter = new StringBuilder();
		String status = PARAM_PENDING;
		Date hoy = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("MM/dd/yyyy");
		Session session = this.sessionFactory.getCurrentSession();
		try {
			filter.setLength(0);
			filter.append(" @Dateprocessd ='").append(
					formateador.format(hoy) + "'");
			filter.append(",@user  ='").append(user + "'");
			filter.append(",@nameSearch  ='").append(nameSearch + "'");
			filter.append(",@list  ='").append(list + "'");
			filter.append(",@Records  ='").append(resultSearch.size() + "'");
			filter.append(", @Status ='").append(status + "'");
			filter.append(",@typeSeach  ='").append(typeSearch + "'");
			filter.append(",@listDate  ='").append(listDate + "'");
			filter.append(",@score  ='").append(score + "'");
			Query query1 = session.createSQLQuery("SP_insert_ResultSearch"
					.concat(filter.toString()));
			BigInteger idSearch = (BigInteger) query1.uniqueResult();

			for (int i = 0; i < resultSearch.size(); i++) {
				Resultado result = resultSearch.get(i);
				filter.setLength(0);
				filter.append(" @ID_Search ='").append(idSearch + "'");
				filter.append(",@name  ='").append(result.getName() + "'");
				filter.append(",@LastUpdate  ='").append(
						result.getLastUpdateDate() + "'");
				filter.append(",@source  ='").append(result.getSource() + "'");
				filter.append(",@OriginalSource  ='").append(
						result.getOriginalSource() + "'");
				filter.append(",@dob  ='").append(result.getDob() + "'");
				filter.append(",@nationality  ='").append(
						result.getNationality() + "'");
				filter.append(",@address1  ='").append(
						result.getAddress() + "'");
				filter.append(",@score  ='").append(result.getScore() + "'");
				filter.append(", @Status = '").append(result.getStatus() + "'");
				filter.append(",@Text  =''");
				filter.append(",@id_xml  ='").append(result.getId() + "'");
				filter.append(",@user  ='").append(user + "'");
				Query query2 = session
						.createSQLQuery("SP_insert_ResultSearchName"
								.concat(filter.toString()));
				query2.uniqueResult();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public QueueDTO getQueueList(String status, String type, int page) {

		StringBuilder filter = new StringBuilder();
		Queue queue = null;
		QueueDTO qDTO = new QueueDTO();
		int cRegistros = 0;
		int varQuantity = 10;
		List<Queue> lqueue = new ArrayList<Queue>();

		SimpleDateFormat formateador = new SimpleDateFormat("MM/dd/yyyy");
		try {
			filter.setLength(0);
			filter.append(" @Status ='").append(status + "'");
			filter.append(",@type ='").append(type + "'");
			filter.append(",@page=").append(page);
			// setear variable desde el properties
			filter.append(",@quantity=").append(varQuantity);

			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("SP_Select_ResultSearch"
					.concat(filter.toString()));
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				queue = new Queue();
				queue.setIdSearch((BigInteger) row[0]);
				queue.setDateProcessed((String) row[1]);
				queue.setUser((String) row[2]);
				queue.setNameSearch((String) row[3]);
				queue.setList((String) row[4]);
				queue.setRecords((Integer) row[5]);
				queue.setStatus((String) row[6]);
				queue.setTypeSearch((String) row[7]);
				queue.setListDate((String) row[8]);
				queue.setScore((String) row[9]);
				queue.setNroBatchSchedule((String) row[10]);
				queue.setQuantityMax((Integer) row[11]);
				cRegistros = (Integer) row[11];
				lqueue.add(queue);
			}
			qDTO.setListQueue(lqueue);
			qDTO.setStatus(status);
			qDTO.setCantRegistros(cRegistros);

			int cantPaginas = (cRegistros / varQuantity);

			if (cRegistros % varQuantity != 0)
				cantPaginas = cantPaginas + 1;

			qDTO.setCantPaginas(cantPaginas);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		return qDTO;

	}

	@Override
	public ResultadoDTO getSearchID(int idSearch, int page, String user) {
		StringBuilder filter = new StringBuilder();
		Resultado resultado = null;
		ResultadoDTO rDTO = new ResultadoDTO();
		List<Resultado> lresultado = new ArrayList<Resultado>();
		int cRegistros = 0;
		int varQuantity = 10;
		int news = 0;
		try {
			filter.setLength(0);
			filter.append(" @ID_Search =").append(idSearch);
			filter.append(",@page=").append(page);
			// setear variable desde el properties
			filter.append(",@quantity=").append(varQuantity);
			// filter.append(" ,@user ='").append(user + "'");
			Session session = this.sessionFactory.getCurrentSession();
			String lista = "";
			String nameSearch = "";
			String usuario = "";
			String rIdSearch = "";
			String statusNow = "";
			String listDate = "";

			Query query = session.createSQLQuery("SP_Select_ResultSearchName"
					.concat(filter.toString()));
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				resultado = new Resultado();
				resultado.setId((BigInteger) row[0]);
				resultado.setIdSearch((BigInteger) row[1]);
				resultado.setName((String) row[2]);
				resultado.setLastUpdateDate((String) row[3]);
				resultado.setSource((String) row[4]);
				resultado.setOriginalSource((String) row[5]);
				resultado.setDob((String) row[6]);
				resultado.setNationality((String) row[7]);
				resultado.setAddress((String) row[8]);
				resultado.setScore((String) row[9]);
				resultado.setStatus((String) row[10]);
				resultado.setText((String) row[11]);
				nameSearch = (String) row[12];
				lista = (String) row[13];
				usuario = (String) row[14];
				statusNow = (String) row[15];
				rIdSearch = new String(resultado.getIdSearch().toByteArray());
				listDate = (String) (String) row[16];
				cRegistros = (Integer) row[17];
				news = (Integer) row[18];

				lresultado.add(resultado);
			}

			rDTO.setResultado(lresultado);
			rDTO.setList(lista);
			rDTO.setName(nameSearch);
			rDTO.setUser(usuario);
			rDTO.setIdSearch(rIdSearch);
			rDTO.setStatusNow(statusNow);
			rDTO.setListDate(listDate);
			rDTO.setCantRegistros(cRegistros);
			rDTO.setNews(news);

			int cantPaginas = (cRegistros / varQuantity);

			if (cRegistros % varQuantity != 0)
				cantPaginas = cantPaginas + 1;

			rDTO.setCantPaginas(cantPaginas);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return rDTO;
	}

	@Override
	public List<ListDTO> getAllLists() {
		ListDTO rDTO = null;
		List<ListDTO> lresultado = new ArrayList<ListDTO>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("SP_Listas");
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				rDTO = new ListDTO();
				rDTO.setId((Integer) row[0]);
				rDTO.setNombre((String) row[1]);
				rDTO.setFecha((String) row[2]);
				lresultado.add(rDTO);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return lresultado;
	}

	@Override
	public List<SourceDTO> getAllSources() {
		SourceDTO sDTO = null;
		List<SourceDTO> lresultado = new ArrayList<SourceDTO>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("SP_source");
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				sDTO = new SourceDTO();
				sDTO.setId((Integer) row[0]);
				sDTO.setNombre((String) row[1]);
				lresultado.add(sDTO);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return lresultado;
	}

	@Override
	public List<Status> getAllStatus() {
		Status status = null;
		List<Status> lresultado = new ArrayList<Status>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("SP_Status");
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				status = new Status();
				status.setId((Integer) row[0]);
				status.setNombre((String) row[1]);
				lresultado.add(status);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return lresultado;
	}

	@Override
	public void updateStatusResultSearch(int idSearch, String status,
			String user) {
		StringBuilder filter = new StringBuilder();

		String upStatus = "";
		if (status.equals(PARAM_PENDING))
			upStatus = "Complete";
		else if (status.equals(PARAM_COMPLETE))
			upStatus = PARAM_REVIEW;
		else
			upStatus = PARAM_COMPLETE;
		try {
			filter.setLength(0);
			filter.append(" @ID_Search =").append(idSearch + "");
			filter.append(", @Status = '").append(upStatus + "'");
			filter.append(", @user =").append(user + "");
			Session session = this.sessionFactory.getCurrentSession();

			Query query = session.createSQLQuery("SP_ResultSearchUpdateStatus"
					.concat(filter.toString()));
			query.uniqueResult();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

	}

	@Override
	public void updateStatusnText(int id, String status, String text,
			String user) {
		StringBuilder filter = new StringBuilder();
		try {
			filter.setLength(0);
			filter.append(" @id ='").append(id + "'");
			filter.append(", @Status = '").append(status + "'");
			filter.append(",@text  ='").append(text + "'");
			filter.append(", @user =").append(user + "");
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session
					.createSQLQuery("SP_ResultSearchNameUpdateStatus"
							.concat(filter.toString()));
			query.list();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	@Override
	public List<ScoreDTO> getScores() {
		ScoreDTO score = null;
		List<ScoreDTO> lresultado = new ArrayList<ScoreDTO>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("SP_filterpercentage");
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				score = new ScoreDTO();
				score.setId((Integer) row[0]);
				score.setIdNombre((String) row[1]);
				score.setValor((String) row[2]);
				lresultado.add(score);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return lresultado;
	}

	@Override
	public List<ScheduleDTO> getSchedules() {
		ScheduleDTO schedule = null;
		List<ScheduleDTO> lresultado = new ArrayList<ScheduleDTO>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createSQLQuery("SP_BatchSchedule");
			List list = query.list();
			for (int i = 0; i < list.size(); i++) {
				Object[] row = (Object[]) list.get(i);
				schedule = new ScheduleDTO();
				schedule.setNro((Integer) row[0]);
				schedule.setCreated((String) row[1]);
				schedule.setUser((String) row[2]);
				schedule.setSource((String) row[3]);
				schedule.setList((String) row[4]);
				schedule.setListUpdated((String) row[5]);
				schedule.setFrom((String) row[6]);
				schedule.setTo((String) row[7]);
				schedule.setDate((String) row[8]);
				schedule.setTime((String) row[9]);
				schedule.setScore((String) row[10]);

				lresultado.add(schedule);
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return lresultado;
	}

	public void saveSchedule(String user, String source, String list,
			String listUpdate, String from, String to, String date,
			String time, String score) throws ParseException {

		Date hoy = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("MM/dd/yyyy");

		StringBuilder filter = new StringBuilder();
		Session session = this.sessionFactory.getCurrentSession();

		try {
			filter.setLength(0);
			filter.append(" @created  ='")
					.append(formateador.format(hoy) + "'");
			filter.append(",@user  ='").append(user + "'");
			filter.append(",@source  ='").append(source + "'");
			filter.append(",@list ='").append(list + "'");
			filter.append(",@list_update  ='").append(listUpdate + "'");
			filter.append(",@from  ='").append(from + "'");
			filter.append(",@to  ='").append(to + "'");
			filter.append(",@date  ='").append(date + "'");
			filter.append(",@time  ='").append(time + "'");
			filter.append(",@Score  ='").append(score + "'");

			Query query1 = session.createSQLQuery("SP_insert_BatchSchedule"
					.concat(filter.toString()));
			query1.uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public void completeAllNews(String idSearch, String user)
			throws ParseException {

		StringBuilder filter = new StringBuilder();
		Session session = this.sessionFactory.getCurrentSession();

		try {
			filter.setLength(0);
			filter.append(" @ID_Search  =").append(new BigInteger(idSearch));
			filter.append(" ,@user  ='").append(user + "'");
			Query query1 = session
					.createSQLQuery("SP_update_ResultSearchNameCleared"
							.concat(filter.toString()));
			query1.uniqueResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void deleteSchedules(int nro) {
		StringBuilder filter = new StringBuilder();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			filter.setLength(0);
			filter.append(" @nro  =" + nro);
			Query query = session.createSQLQuery("SP_delete_BatchSchedule"
					.concat(filter.toString()));
			query.executeUpdate();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

}
