package com.biblioteca.Biblioteca.service;

import com.biblioteca.Biblioteca.dto.PrestamoDTO;
import com.biblioteca.Biblioteca.dto.RecursosDTO;
import com.biblioteca.Biblioteca.dto.UsuarioDTO;
import com.biblioteca.Biblioteca.model.Prestamo;
import com.biblioteca.Biblioteca.model.Recursos;
import com.biblioteca.Biblioteca.model.Usuario;
import com.biblioteca.Biblioteca.repositories.RepositorioPrestamo;
import com.biblioteca.Biblioteca.repositories.RepositorioRecursos;
import com.biblioteca.Biblioteca.repositories.RepositorioUsuario;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@ExtendWith(MockitoExtension.class)
class ServicioPrestamoTest {
    @Mock
    private RepositorioPrestamo repositorioPrestamo;

    @Mock
    RepositorioRecursos repositorioRecursos;

    @Mock
    RepositorioUsuario repositorioUsuario;

    @InjectMocks
    private ServicioPrestamo servicioPrestamo;



    @Test
    @DisplayName("Prestamo test")
    public void findAll(){
    //Preparación para la prueba
        var prestamo1 = new Prestamo();
        prestamo1.setIdPrestamo("6");
        prestamo1.setIdUsuario("42");
        prestamo1.setIdRecurso("9");
        prestamo1.setFechaPrestamo("30/01");
        prestamo1.setFechaEntrega("30/02");

        var prestamo2 = new Prestamo();
        prestamo2.setIdPrestamo("11");
        prestamo2.setIdUsuario("65");
        prestamo2.setIdRecurso("76");
        prestamo2.setFechaPrestamo("04/08");
        prestamo2.setFechaEntrega("04/09");

        var prestamo3 = new Prestamo();
        prestamo3.setIdPrestamo("18");
        prestamo3.setIdUsuario("6");
        prestamo3.setIdRecurso("15");
        prestamo3.setFechaPrestamo("12/12");
        prestamo3.setFechaEntrega("12/01");

        var lista = new ArrayList<Prestamo>();
        lista.add(prestamo1);
        lista.add(prestamo2);
        lista.add(prestamo3);
        Mockito.when(repositorioPrestamo.findAll()).thenReturn(lista);

        //Actuar sobre el servicio
        var respuesta = servicioPrestamo.obtenerTodos();


        //Verificación
        Assertions.assertEquals(3,respuesta.size());
        Assertions.assertEquals(prestamo1.getIdPrestamo(), respuesta.get(0).getIdPrestamo());
        Assertions.assertEquals(prestamo2.getIdPrestamo(), respuesta.get(1).getIdPrestamo());
        Assertions.assertEquals(prestamo3.getIdPrestamo(), respuesta.get(2).getIdPrestamo());

    }

    @Test
    void crear(){

        var recurso = new Recursos();
        recurso.setIdRecurso("15");
        recurso.setTitulo("Orgullo y prejuicio");
        recurso.setTematica("Drama");
        recurso.setEstadoRecurso(true);
        recurso.setTipoRecurso("Libro");

        var usuario1 = new Usuario();
        usuario1.setIdUsuario("6");
        usuario1.setNombre("Natalia Morales");
        usuario1.setFecha("25/09");


        var prestamo3 = new PrestamoDTO();
        prestamo3.setIdPrestamo("18");
        prestamo3.setIdUsuario("6");
        prestamo3.setIdRecurso("15");
        prestamo3.setFechaPrestamo("12/12");
        prestamo3.setFechaEntrega("12/01");


        Mockito.when(repositorioRecursos.findById(anyString())).thenReturn(Optional.of(recurso));
        Mockito.when(repositorioUsuario.findById(anyString())).thenReturn(Optional.of(usuario1));
        Mockito.when(repositorioPrestamo.save(any())).thenReturn("Puede realizar su prestámo.");


        String find = servicioPrestamo.crear(prestamo3);


        Assertions.assertEquals("Puede realizar su prestámo.",find);

        //El crear tiene un error, este error no soy capaz de solucionarlo.
    }



    @Test
    void actualizar(){
        var prestamo1 = new Prestamo();
        prestamo1.setIdPrestamo("6");
        prestamo1.setIdUsuario("16");
        prestamo1.setIdRecurso("20");
        prestamo1.setFechaPrestamo("11/10");
        prestamo1.setFechaEntrega("11/11");

        var prestamo2 = new PrestamoDTO();
        prestamo2.setIdPrestamo("6");
        prestamo2.setIdUsuario("16");
        prestamo2.setIdRecurso("20");
        prestamo2.setFechaPrestamo("11/10");
        prestamo2.setFechaEntrega("11/11");

        Mockito.when(repositorioPrestamo.findById(anyString())).thenReturn(Optional.of(prestamo1));

        Mockito.when(repositorioPrestamo.save(any(Prestamo.class))).thenReturn(prestamo1);

        PrestamoDTO find = servicioPrestamo.modificar(prestamo2);

        Assertions.assertEquals(prestamo2.getIdPrestamo(),find.getIdPrestamo());
    }

    @Test
    void borrar(){
        var prestamo = new Prestamo();
        prestamo.setIdPrestamo("18");

        Mockito.doNothing().when(repositorioPrestamo).deleteById("18");

        servicioPrestamo.borrar("18");

        Mockito.verify(repositorioPrestamo).deleteById("18");
    }

    @Test
    void devolver(){
        var recurso = new Recursos();
        recurso.setIdRecurso("20");
        recurso.setTitulo("Orgullo y prejuicio");
        recurso.setTematica("Drama");
        recurso.setEstadoRecurso(false);
        recurso.setTipoRecurso("Libro");

        var prestamo = new Prestamo();
        prestamo.setIdPrestamo("6");
        prestamo.setIdUsuario("16");
        prestamo.setIdRecurso("20");
        prestamo.setFechaPrestamo("11/10");
        prestamo.setFechaEntrega("11/11");

        Mockito.when(repositorioRecursos.findById("20")).thenReturn(Optional.of(recurso));

        String find = servicioPrestamo.devolverPrestamo("20");

        Assertions.assertEquals("El recurso lo has devuelto exitosamente.",find);
    }

    @Test
    void prestar(){
        var recurso = new Recursos();
        recurso.setIdRecurso("20");
        recurso.setTitulo("Orgullo y prejuicio");
        recurso.setTematica("Drama");
        recurso.setEstadoRecurso(true);
        recurso.setTipoRecurso("Libro");

        var prestamo = new Prestamo();
        prestamo.setIdPrestamo("6");
        prestamo.setIdUsuario("16");
        prestamo.setIdRecurso("20");
        prestamo.setFechaPrestamo("11/10");
        prestamo.setFechaEntrega("11/11");

        Mockito.when(repositorioRecursos.findById("20")).thenReturn(Optional.of(recurso));

        String find = servicioPrestamo.prestar("20");

        Assertions.assertEquals("El prestámo ha sido confirmado",find);
    }



    @Test
    void buscarIdRecurso(){
        var recurso = new Recursos();
        recurso.setIdRecurso("10");

        var prestamo = new Prestamo();
        prestamo.setIdRecurso("10");


        Mockito.when(repositorioRecursos.findById("10")).thenReturn(Optional.of(recurso));
        Mockito.when(repositorioPrestamo.findByIdRecurso("10")).thenReturn((Iterable<Prestamo>) prestamo);

        PrestamoDTO find = (PrestamoDTO) servicioPrestamo.buscarPorIdRecurso("10");


        Assertions.assertEquals("El recurso si está disponible.",find);
        //el servicio buscarIdRecurso, no soy capaz de hacerlo.
    }


}