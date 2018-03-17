package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Peticion {

	@Id
    @GeneratedValue
    private Long id;
    
    @ManyToOne
    private User usuarioPeticionado;

    @ManyToOne
    private User usuarioPeticionador;

    public Peticion(User usuarioPeticionado, User usuarioPeticionador) {
        super();
        this.usuarioPeticionado = usuarioPeticionado;
        this.usuarioPeticionador = usuarioPeticionador;
    }

    Peticion() {
    }

    public User getUsuarioPeticionado() {
        return usuarioPeticionado;
    }

    public void setUsuarioPeticionado(User usuarioPeticionado) {
        this.usuarioPeticionado = usuarioPeticionado;
    }

    public User getUsuarioPeticionador() {
        return usuarioPeticionador;
    }

    public void setUsuarioPeticionador(User usuarioPeticionador) {
        this.usuarioPeticionador = usuarioPeticionador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





}
