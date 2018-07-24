package com.journaldev.spring.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.spring.dao.IProcedure;
import com.journaldev.spring.model.ListDTO;
import com.journaldev.spring.model.QueueDTO;
import com.journaldev.spring.model.Resultado;
import com.journaldev.spring.model.ResultadoDTO;
import com.journaldev.spring.model.ScheduleDTO;
import com.journaldev.spring.model.ScoreDTO;
import com.journaldev.spring.model.SourceDTO;
import com.journaldev.spring.model.Status;

@Service
public class ProcedureService implements IProcedureService {

	private IProcedure procedureDAO;
	

	@Override
	@Transactional
	public ResultadoDTO getBlacklist(String texto, String lista, String user,String score) {

		return procedureDAO.getBlacklist(texto, lista, user,score);
	}

	public IProcedure getProcedureDAO() {
		return procedureDAO;
	}

	public void setProcedureDAO(IProcedure procedureDAO) {
		this.procedureDAO = procedureDAO;
	}

	@Override
	public void save(List<Resultado> resultSearch, String user,
			String nameSearch, String list, String typeSearch,String listDate, String score)
			throws ParseException {
		procedureDAO.save(resultSearch, user, nameSearch, list, typeSearch, listDate, score);

	}

	@Override
	public QueueDTO getQueueList(String status,String type,int page) {
		return procedureDAO.getQueueList(status,type,page);
	}

	@Override
	public ResultadoDTO getSearchID(int idSearch,int page, String user) {
		return procedureDAO.getSearchID(idSearch,page,user);
	}

	@Override
	public List<ListDTO> getAllList() {		
		return procedureDAO.getAllLists();
	}

	@Override
	public List<SourceDTO> getAllSources() {		
		return procedureDAO.getAllSources();
	}
	@Override
	public List<Status> getAllStatus() {
		return procedureDAO.getAllStatus();
	}

	@Override
	public void updateStatusResultSearch(int idSearch, String status, String user) {
		procedureDAO.updateStatusResultSearch(idSearch, status,user);
		
	}

	@Override
	public void updateStatusnText(int id, String status, String text, String user) {
		procedureDAO.updateStatusnText(id, status, text,user);
		
	}

	@Override
	public List<ScoreDTO> getScores() {
		return procedureDAO.getScores();
	}
	@Override
	public List<ScheduleDTO> getSchedules() {
		return procedureDAO.getSchedules();
	}

	@Override
	public void saveSchedule(String user, String source, String list, String listUpdate,
	String from, String to,String date, String time, String score) throws ParseException {
		 procedureDAO.saveSchedule(user,source,list,listUpdate,from,to,date,time,score);

	}

	@Override
	public void deleteSchedule(int nro) {
		procedureDAO.deleteSchedules(nro);// TODO Auto-generated method stub
		
	}

	@Override
	public void completeAllNews(String idSearch, String user) throws ParseException {
		procedureDAO.completeAllNews(idSearch,user);
		
	}

}
