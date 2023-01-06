package com.alquimiasoft.backend.prueba.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "sucursal")
public class Sucursal implements Serializable {

	/**
	 * • La entidad dirección cliente o sucursal deberá tener los siguientes campos:
	 * (id, provincia, ciudad, dirección)
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long suc_id;
	private String suc_provincia;
	private String suc_ciudad;
	private String suc_direccion;
	private String suc_esprincipal;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cli_id")
	@JsonBackReference
	Cliente cliente;

	public Long getSuc_id() {
		return suc_id;
	}

	public void setSuc_id(Long suc_id) {
		this.suc_id = suc_id;
	}

	public String getSuc_provincia() {
		return suc_provincia;
	}

	public void setSuc_provincia(String suc_provincia) {
		this.suc_provincia = suc_provincia;
	}

	public String getSuc_ciudad() {
		return suc_ciudad;
	}

	public void setSuc_ciudad(String suc_ciudad) {
		this.suc_ciudad = suc_ciudad;
	}

	public String getSuc_direccion() {
		return suc_direccion;
	}

	public void setSuc_direccion(String suc_direccion) {
		this.suc_direccion = suc_direccion;
	}

	public String isSuc_esprincipal() {
		return suc_esprincipal;
	}

	public void setSuc_esprincipal(String suc_esprincipal) {
		this.suc_esprincipal = suc_esprincipal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	private static final long serialVersionUID = 1L;

}
