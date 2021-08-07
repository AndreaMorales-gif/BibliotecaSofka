package com.biblioteca.Biblioteca.service;

import com.biblioteca.Biblioteca.dto.PrestamoDTO;
import com.biblioteca.Biblioteca.model.Prestamo;
import com.biblioteca.Biblioteca.repositories.RepositorioPrestamo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServicioPrestamoTest {
    @MockBean
    private RepositorioPrestamo repositorioPrestamo;

    @Autowired
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

        var prestamo2 = new Prestamo();
        prestamo2.setIdPrestamo("11");
        prestamo2.setIdUsuario("65");
        prestamo2.setIdRecurso("76");
        prestamo2.setFechaPrestamo("04/08");

        var prestamo3 = new Prestamo();
        prestamo3.setIdPrestamo("18");
        prestamo3.setIdUsuario("6");
        prestamo3.setIdRecurso("15");
        prestamo3.setFechaPrestamo("12/12");

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
        var prestamo1 = new Prestamo();
        prestamo1.setIdPrestamo("6");
        prestamo1.setIdUsuario("42");
        prestamo1.setIdRecurso("9");
        prestamo1.setFechaPrestamo("30/01");

        var prestamo3 = new PrestamoDTO();
        prestamo3.setIdPrestamo("18");
        prestamo3.setIdUsuario("6");
        prestamo3.setIdRecurso("15");
        prestamo3.setFechaPrestamo("12/12");

        Mockito.when(repositorioPrestamo.save(any())).thenReturn("puede realizar su prestamo");

        var respuesta = servicioPrestamo.crear(prestamo3);


    }
}