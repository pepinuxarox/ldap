package com.journaldev.spring.model;

import java.util.List;

public class QueueDTO {
	
	private List<Queue> listQueue;
	private String status;
	private int cantRegistros;
	private int cantPaginas;
	public QueueDTO() {
		super();
	}
	public List<Queue> getListQueue() {
		return listQueue;
	}
	public void setListQueue(List<Queue> listQueue) {
		this.listQueue = listQueue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCantRegistros() {
		return cantRegistros;
	}
	public void setCantRegistros(int cantRegistros) {
		this.cantRegistros = cantRegistros;
	}
	public int getCantPaginas() {
		return cantPaginas;
	}
	public void setCantPaginas(int cantPaginas) {
		this.cantPaginas = cantPaginas;
	}
	
	

}
