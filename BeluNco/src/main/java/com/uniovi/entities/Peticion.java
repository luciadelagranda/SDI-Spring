package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Peticion {

	@Id
    @GeneratedValue
    private Long id;

    private Long usuarioPeticionado;

    private Long usuarioPeticionador;

    public Peticion(Long usuarioPeticionado, Long usuarioPeticionador) {
        super();
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





}
