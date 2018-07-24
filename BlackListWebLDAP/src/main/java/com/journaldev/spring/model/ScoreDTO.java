package com.journaldev.spring.model;

public class ScoreDTO {
	
	private int id;
	private String idNombre;
	private String valor;
	
	public ScoreDTO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdNombre() {
		return idNombre;
	}
	public void setIdNombre(String idNombre) {
		this.idNombre = idNombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}
