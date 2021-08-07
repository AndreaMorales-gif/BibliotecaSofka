package com.biblioteca.Biblioteca.service;

import com.biblioteca.Biblioteca.dto.RecursosDTO;
import com.biblioteca.Biblioteca.model.Recursos;
import com.biblioteca.Biblioteca.repositories.RepositorioRecursos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServicioRecursosTest {
    @MockBean
    private RepositorioRecursos repositorioRecursos;

    @Autowired
    private ServicioRecursos servicioRecursos;

    @Test
    @DisplayName("Recursos test")
    public void findAll() {

        var recurso1 = new Recursos();
        recurso1.setIdRecurso("8");
        recurso1.setTitulo("Nach: Hambriento");
        recurso1.setTematica("Poesía");
        recurso1.isEstadoRecurso(Boolean.TRUE);
        recurso1.setTipoRecurso("Libro");

        var recurso2 = new Recursos();
        recurso2.setIdRecurso("55");
        recurso2.setTitulo("Sí, si es contigo");
        recurso2.setTematica("Romance");
        recurso2.isEstadoRecurso(Boolean.TRUE);
        recurso2.setTipoRecurso("Libro");

        var recurso3 = new Recursos();
        recurso3.setIdRecurso("20");
        recurso3.setTitulo("Crónica de una muerte anunciada");
        recurso3.setTematica("Drama");
        recurso3.isEstadoRecurso(Boolean.TRUE);
        recurso3.setTipoRecurso("libro");

        var lista = new ArrayList<Recursos>();
        lista.add(recurso1);
        lista.add(recurso2);
        lista.add(recurso3);
        Mockito.when(repositorioRecursos.findAll()).thenReturn(lista);

        var respuesta = servicioRecursos.obtenerTodos();

        Assertions.assertEquals(3,respuesta.size());
        Assertions.assertEquals(recurso1.getIdRecurso(), respuesta.get(0).getIdRecurso());
        Assertions.assertEquals(recurso2.getIdRecurso(), respuesta.get(1).getIdRecurso());
        Assertions.assertEquals(recurso3.getIdRecurso(), respuesta.get(2).getIdRecurso());

    }


    @Test
    void crear(){

        var recurso1 = new Recursos();
        recurso1.setIdRecurso("8");
        recurso1.setTitulo("Nach: Hambriento");
        recurso1.setTematica("Poesía");
        recurso1.isEstadoRecurso(Boolean.TRUE);
        recurso1.setTipoRecurso("Libro");

        var recurso2 = new RecursosDTO();
        recurso2.setIdRecurso("8");
        recurso2.setTitulo("Nach: Hambriento");
        recurso2.setTematica("Poesía");
        recurso2.isEstadoRecurso(Boolean.TRUE);
        recurso2.setTipoRecurso("Libro");


        Mockito.when(repositorioRecursos.save(any())).thenReturn((recurso1));

        var resultado = servicioRecursos.crear(recurso2);

        Assertions.assertEquals(recurso1.getIdRecurso(), resultado.getIdRecurso());
    }
}