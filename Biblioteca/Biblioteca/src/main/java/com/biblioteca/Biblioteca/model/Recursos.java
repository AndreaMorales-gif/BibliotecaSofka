package com.biblioteca.Biblioteca.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Recursos {
    @Id
    private String idRecurso;

    private String titulo;
    private String tematica;
    private boolean estadoRecurso;
    private String tipoRecurso;


    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public boolean isEstadoRecurso(Boolean aFalse) {
        return estadoRecurso;
    }

    public void setEstadoRecurso(boolean estadoRecurso) {
        this.estadoRecurso = estadoRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

}
