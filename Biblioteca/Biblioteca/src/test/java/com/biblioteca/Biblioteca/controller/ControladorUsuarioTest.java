package com.biblioteca.Biblioteca.controller;

import com.biblioteca.Biblioteca.dto.UsuarioDTO;
import com.biblioteca.Biblioteca.service.ServicioUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@AutoConfigureMockMvc
public class ControladorUsuarioTest {

    @MockBean
    private ServicioUsuario servicioUsuario;

    @Autowired
    private MockMvc mockMvc1;


    @Test
    @DisplayName("crear usuario")
    public void crearusuario() throws Exception {


        UsuarioDTO returnUsuario = new UsuarioDTO();
        UsuarioDTO usuarioDTO = new UsuarioDTO();


        returnUsuario.setIdUsuario("35");
        returnUsuario.setNombre("Gaby Grajales");
        returnUsuario.setFecha("02/10/2002");

        usuarioDTO.setIdUsuario("35");
        usuarioDTO.setNombre("Kevin Galeano");
        usuarioDTO.setFecha("05/04/2021");

        doReturn(usuarioDTO).when(servicioUsuario).crear(any());

        mockMvc1.perform(post("/usuario/crearUsuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(returnUsuario)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idUsuario", is("20")));

    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Buscar Recursos")
    void buscarTodos() throws Exception {
        UsuarioDTO returnUsuario = new UsuarioDTO();
        UsuarioDTO usuarioDTO = new UsuarioDTO();


        returnUsuario.setIdUsuario("35");
        returnUsuario.setNombre("Gaby Grajales");
        returnUsuario.setFecha("02/10/2002");

        usuarioDTO.setIdUsuario("35");
        usuarioDTO.setNombre("Kevin Galeano");
        usuarioDTO.setFecha("05/04/2021");

        doReturn(usuarioDTO).when(servicioUsuario).crear(any());

        mockMvc1.perform(get("/usuario/todosRecursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(returnUsuario)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


    }

    @Test
    @DisplayName("actualizar")
    void actalizar() throws Exception {
        UsuarioDTO returnUsuario = new UsuarioDTO();
        UsuarioDTO usuarioDTO = new UsuarioDTO();


        returnUsuario.setIdUsuario("35");
        returnUsuario.setNombre("Gaby Grajales");
        returnUsuario.setFecha("02/10/2002");

        usuarioDTO.setIdUsuario("35");
        usuarioDTO.setNombre("Kevin Galeano");
        usuarioDTO.setFecha("05/04/2021");

        doReturn(usuarioDTO).when(servicioUsuario).crear(any());

        mockMvc1.perform(put("/usuario/modificarUsuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(returnUsuario)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


    }

    @Test
    @DisplayName("borrarUsuario")
    void borrarUsuario() throws Exception {
        UsuarioDTO returnUsuario = new UsuarioDTO();
        UsuarioDTO usuarioDTO = new UsuarioDTO();


        returnUsuario.setIdUsuario("35");
        returnUsuario.setNombre("Gaby Grajales");
        returnUsuario.setFecha("02/10/2002");

        usuarioDTO.setIdUsuario("35");
        usuarioDTO.setNombre("Kevin Galeano");
        usuarioDTO.setFecha("05/04/2021");

        doReturn(usuarioDTO).when(servicioUsuario).crear(any());

        mockMvc1.perform(MockMvcRequestBuilders.delete("/usuario/borrarUsuario/35")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(returnUsuario)))
                .andExpect(status().isOk());

    }
}
