package com.journaldev.spring.model;

import java.math.BigInteger;

public class Queue {
	
	private BigInteger idSearch;
	private String dateProcessed;
	private String user;
	private String nameSearch;
	private String list;
	private int records;
	private String status;
	private String typeSearch;
	private String listDate;
	private String score;
	private String nroBatchSchedule;
	private int quantityMax;
	
	public Queue() {
		super();
	}
	
	public String getDateProcessed() {
		return dateProcessed;
	}
	public void setDateProcessed(String dateProcessed) {
		this.dateProcessed = dateProcessed;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getNameSearch() {
		return nameSearch;
	}
	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTypeSearch() {
		return typeSearch;
	}
	public void setTypeSearch(String typeSearch) {
		this.typeSearch = typeSearch;
	}

	public BigInteger getIdSearch() {
		return idSearch;
	}

	public void setIdSearch(BigInteger idSearch) {
		this.idSearch = idSearch;
	}

	public String getListDate() {
		return listDate;
	}

	public void setListDate(String listDate) {
		this.listDate = listDate;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getNroBatchSchedule() {
		return nroBatchSchedule;
	}

	public void setNroBatchSchedule(String nroBatchSchedule) {
		this.nroBatchSchedule = nroBatchSchedule;
	}

	public int getQuantityMax() {
		return quantityMax;
	}

	public void setQuantityMax(int quantityMax) {
		this.quantityMax = quantityMax;
	}
	
	

}
