package com.journaldev.spring.service;

import java.text.ParseException;
import java.util.List;

import com.journaldev.spring.model.ListDTO;
import com.journaldev.spring.model.QueueDTO;
import com.journaldev.spring.model.Resultado;
import com.journaldev.spring.model.ResultadoDTO;
import com.journaldev.spring.model.ScheduleDTO;
import com.journaldev.spring.model.ScoreDTO;
import com.journaldev.spring.model.SourceDTO;
import com.journaldev.spring.model.Status;

public interface IProcedureService {
	 ResultadoDTO getBlacklist(String texto, String lista, String user,String score);

	 void save(List<Resultado> resultSearch, String user, String nameSearch,
			String list, String typeSearch ,String listDate, String score) throws ParseException;

	 QueueDTO getQueueList(String status, String type,int page);
	 ResultadoDTO getSearchID(int idSearch,int page,String user);
	 List<ListDTO> getAllList();
	 List<Status> getAllStatus();
	 void updateStatusResultSearch(int idSearch, String status,String user);
	 void updateStatusnText(int id, String status,String text,String user);
	 List<ScoreDTO> getScores();
	 List<SourceDTO> getAllSources();
	 List<ScheduleDTO> getSchedules();
	 void saveSchedule(String user, String source, String list, String listUpdate,
				String from, String to,String date, String time, String score) throws ParseException;
	 void deleteSchedule(int nro) ;
	 void completeAllNews( String idSearch, String user) throws ParseException;
}
