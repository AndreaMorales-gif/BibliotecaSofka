package com.biblioteca.Biblioteca.controller;

import com.biblioteca.Biblioteca.dto.RecursosDTO;
import com.biblioteca.Biblioteca.service.ServicioRecursos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
class ControladorRecursosTest {
    @MockBean
    private ServicioRecursos servicioRecursos;

    @Autowired
    private MockMvc mockMvc1;

    @Test
    @DisplayName("crear recurso")
    public void crearRecurso() throws Exception{

        RecursosDTO returnRecursos = new RecursosDTO();
        RecursosDTO recursosDTO = new RecursosDTO();

        returnRecursos.setIdRecurso("79");
        returnRecursos.setTitulo("Revista Vea");
        returnRecursos.setTematica("moda");
        returnRecursos.isEstadoRecurso(true);
        returnRecursos.setTipoRecurso("revista");

        recursosDTO.setIdRecurso("79");
        recursosDTO.setTitulo("El principito");
        recursosDTO.setTematica("infantil");
        recursosDTO.isEstadoRecurso(true);
        recursosDTO.setTipoRecurso("libro");

        doReturn(recursosDTO).when(servicioRecursos).crear(any());

        mockMvc1.perform(post("/recursos/crearRecurso")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(returnRecursos)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idRecurso", is("79")));

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    }

