package com.alquimiasoft.backend.prueba.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity

@Table(name = "cliente", uniqueConstraints = { @UniqueConstraint(columnNames = { "cli_numero_identificacion" }) })
public class Cliente implements Serializable {

	/*
	 * @nota La entidad clientes deberá tener los siguientes campos: (id, tipo
	 * identificación (RUC o Cédula), número de identificación, nombres, correo y
	 * número de celular). Si cree pertinente agregar o reemplazar campos puede
	 * hacerlo.
	 */

	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClienteSeq")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cli_id;
	private String cli_tipo_identificacion;

	@Column(unique = true)
	private String cli_numero_identificacion;
	private String cli_nombres;
	private String cli_email;
	private String cli_telefono;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	// @OneToMany(mappedBy = "cliente" )
	@OneToMany(targetEntity = Sucursal.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "cli_id", referencedColumnName = "cli_id")
	List<Sucursal> sucursales;

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public Long getCli_id() {
		return cli_id;
	}

	public void setCli_id(Long cli_id) {
		this.cli_id = cli_id;
	}

	public String getCli_tipo_identificacion() {
		return cli_tipo_identificacion;
	}

	public void setCli_tipo_identificacion(String cli_tipo_identificacion) {
		this.cli_tipo_identificacion = cli_tipo_identificacion;
	}

	public String getCli_numero_identificacion() {
		return cli_numero_identificacion;
	}

	public void setCli_numero_identificacion(String cli_numero_identificacion) {
		this.cli_numero_identificacion = cli_numero_identificacion;
	}

	public String getCli_nombres() {
		return cli_nombres;
	}

	public void setCli_nombres(String cli_nombres) {
		this.cli_nombres = cli_nombres;
	}

	public String getCli_email() {
		return cli_email;
	}

	public void setCli_email(String cli_email) {
		this.cli_email = cli_email;
	}

	public String getCli_telefono() {
		return cli_telefono;
	}

	public void setCli_telefono(String cli_telefono) {
		this.cli_telefono = cli_telefono;
	}

	private static final long serialVersionUID = 1L;

}
