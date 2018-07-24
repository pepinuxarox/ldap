package com.journaldev.spring.model;

import java.util.List;

public class ResultadoDTO {

	private List<Resultado> resultado;
	private String name;
	private String list;
	private String user;
	private String idSearch;
	private String statusNow;
	private String listDate;
	private int cantRegistros;
	private int cantPaginas;
	private int news;

	public ResultadoDTO() {
		super();
	}

	public List<Resultado> getResultado() {
		return resultado;
	}

	public void setResultado(List<Resultado> resultado) {
		this.resultado = resultado;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getIdSearch() {
		return idSearch;
	}

	public void setIdSearch(String idSearch) {
		this.idSearch = idSearch;
	}

	public String getStatusNow() {
		return statusNow;
	}

	public void setStatusNow(String statusNow) {
		this.statusNow = statusNow;
	}

	public String getListDate() {
		return listDate;
	}

	public void setListDate(String listDate) {
		this.listDate = listDate;
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

	public int getNews() {
		return news;
	}

	public void setNews(int news) {
		this.news = news;
	}
	
	

}
