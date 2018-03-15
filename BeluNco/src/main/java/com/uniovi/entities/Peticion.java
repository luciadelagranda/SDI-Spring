package com.uniovi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Peticion {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private Long usuarioPeticionado;
	
	@Column(unique=true)
	private Long usuarioPeticionador;
	
	@Column(name="status")
	boolean aceptada;
	
	public Peticion(Long usuarioPeticionado, Long usuarioPeticionador) {
		super();
		this.aceptada = false;
		this.usuarioPeticionado = usuarioPeticionado;
		this.usuarioPeticionador = usuarioPeticionador;
	}
	
	Peticion() {
	}

	public Long getUsuarioPeticionado() {
		return usuarioPeticionado;
	}

	public void setUsuarioPeticionado(Long usuarioPeticionado) {
		this.usuarioPeticionado = usuarioPeticionado;
	}

	public Long getUsuarioPeticionador() {
		return usuarioPeticionador;
	}

	public void setUsuarioPeticionador(Long usuarioPeticionador) {
		this.usuarioPeticionador = usuarioPeticionador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isAceptada() {
		return aceptada;
	}

	public void setAceptada(boolean aceptada) {
		this.aceptada = aceptada;
	}

	
	
}