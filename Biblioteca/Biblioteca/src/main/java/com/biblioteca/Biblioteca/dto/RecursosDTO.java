package com.biblioteca.Biblioteca.dto;

public class RecursosDTO {
    private String idRecurso;

    private String titulo;
    private String tematica;
    private boolean estadoRecurso;
    private String tipoRecurso;


    public RecursosDTO(){

    }

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

    public boolean isEstadoRecurso(Boolean aTrue) {
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
