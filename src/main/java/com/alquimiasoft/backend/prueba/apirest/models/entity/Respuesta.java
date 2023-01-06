package com.alquimiasoft.backend.prueba.apirest.models.entity;

import java.io.Serializable;
import java.util.List;

public class Respuesta<T> implements Serializable {

	private String estado;
	private Object dato;
	private List<T> datoList;
	private String mensajeError;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public List<T> getDatoList() {
		return datoList;
	}

	public void setDatoList(List<T> datoList) {
		this.datoList = datoList;
	}

	public Object getDato() {
		return dato;
	}

	public void setDato(Object dato) {
		this.dato = dato;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
