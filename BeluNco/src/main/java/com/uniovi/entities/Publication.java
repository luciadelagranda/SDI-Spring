package com.uniovi.entities;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Publication {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	User autor;
	
	private String title;
	private String descripcion;
	private String date;
	
	public Publication() {}
	
	public Publication(User autor, String title, String descripcion) {
		super();
		this.autor = autor;
		this.title = title;
		this.descripcion = descripcion;
		this.date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAutor() {
		return autor;
	}

	public void setAutor(User autor) {
		this.autor = autor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	
}
