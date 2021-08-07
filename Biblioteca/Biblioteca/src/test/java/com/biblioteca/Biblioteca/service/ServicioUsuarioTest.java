package com.biblioteca.Biblioteca.service;

import com.biblioteca.Biblioteca.dto.UsuarioDTO;
import com.biblioteca.Biblioteca.model.Usuario;
import com.biblioteca.Biblioteca.repositories.RepositorioUsuario;
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
class ServicioUsuarioTest {
    @MockBean
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private ServicioUsuario servicioUsuario;


    @Test
    @DisplayName("Usuario test")
    public void findAll(){
        var usuario1 = new Usuario();
        usuario1.setIdUsuario("6");
        usuario1.setNombre("Natalia Morales");
        usuario1.setFecha("25/09");

        var usuario2 = new Usuario();
        usuario2.setIdUsuario("12");
        usuario2.setNombre("Carmenza Marín");
        usuario2.setFecha("31/10");

        var usuario3 = new Usuario();
        usuario3.setIdUsuario("90");
        usuario3.setNombre("Alexander Cañaveral");
        usuario3.setFecha("11/04");

        var lista = new ArrayList<Usuario>();
        lista.add(usuario1);
        lista.add(usuario2);
        lista.add(usuario3);
        Mockito.when(repositorioUsuario.findAll()).thenReturn(lista);
        var respuesta = servicioUsuario.obtenerTodos();

        Assertions.assertEquals(3,respuesta.size());
        Assertions.assertEquals(usuario1.getIdUsuario(), respuesta.get(0).getIdUsuario());
        Assertions.assertEquals(usuario2.getIdUsuario(), respuesta.get(1).getIdUsuario());
        Assertions.assertEquals(usuario3.getIdUsuario(), respuesta.get(2).getIdUsuario());


    }

    @Test
    void crear(){
        var usuario1 = new Usuario();
        usuario1.setIdUsuario("6");
        usuario1.setNombre("Natalia Morales");
        usuario1.setFecha("25/09");

        var usuario3 = new UsuarioDTO();
        usuario3.setIdUsuario("90");
        usuario3.setNombre("Alexander Cañaveral");
        usuario3.setFecha("11/04");

        Mockito.when(repositorioUsuario.save(any())).thenReturn((usuario1));

        var resultado = servicioUsuario.crear(usuario3);
        Assertions.assertEquals(usuario1.getIdUsuario(), resultado.getIdUsuario());

    }
}