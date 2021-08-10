package com.biblioteca.Biblioteca.service;

import com.biblioteca.Biblioteca.dto.PrestamoDTO;
import com.biblioteca.Biblioteca.dto.UsuarioDTO;
import com.biblioteca.Biblioteca.model.Prestamo;
import com.biblioteca.Biblioteca.model.Usuario;
import com.biblioteca.Biblioteca.repositories.RepositorioUsuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ServicioUsuarioTest {
    @Mock
    private RepositorioUsuario repositorioUsuario;

    @InjectMocks
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
        usuario3.setIdUsuario("6");
        usuario3.setNombre("Natalia Morales");
        usuario3.setFecha("25/09");

        Mockito.when(repositorioUsuario.save(any())).thenReturn((usuario1));

        var resultado = servicioUsuario.crear(usuario3);
        Assertions.assertEquals(usuario1.getIdUsuario(), resultado.getIdUsuario());
    }

    @Test
    void actualizar(){
        var usuario1 = new Usuario();
        usuario1.setIdUsuario("6");
        usuario1.setNombre("Natalia Morales");
        usuario1.setFecha("25/09");

        var usuario2 = new UsuarioDTO();
        usuario2.setIdUsuario("6");
        usuario2.setNombre("Natalia Morales");
        usuario2.setFecha("25/09");

        Mockito.when(repositorioUsuario.findById(anyString())).thenReturn(Optional.of(usuario1));

        Mockito.when(repositorioUsuario.save(any(Usuario.class))).thenReturn(usuario1);

        UsuarioDTO find = servicioUsuario.modificar(usuario2);

        Assertions.assertEquals(usuario2.getIdUsuario(),find.getIdUsuario());
    }

    @Test
    void borrar(){
        var usuario = new Usuario();
        usuario.setIdUsuario("18");

        Mockito.doNothing().when(repositorioUsuario).deleteById("18");

        servicioUsuario.borrar("18");

        Mockito.verify(repositorioUsuario).deleteById("18");
    }
}