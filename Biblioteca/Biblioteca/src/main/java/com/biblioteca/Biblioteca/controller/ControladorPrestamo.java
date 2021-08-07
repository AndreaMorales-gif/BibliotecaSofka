package com.biblioteca.Biblioteca.controller;

import com.biblioteca.Biblioteca.dto.PrestamoDTO;
import com.biblioteca.Biblioteca.dto.RecursosDTO;
import com.biblioteca.Biblioteca.dto.UsuarioDTO;
import com.biblioteca.Biblioteca.service.ServicioPrestamo;
import com.biblioteca.Biblioteca.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.UnsatisfiedDependencyException;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class ControladorPrestamo {

    @Autowired
    ServicioPrestamo servicioPrestamo;

    @PostMapping("/crearPrestamo")
    public String create(@RequestBody PrestamoDTO prestamoDTO) {
        return servicioPrestamo.crear(prestamoDTO);
    }

    @PutMapping("/modificarPrestamo")
    public ResponseEntity<PrestamoDTO> update(@RequestBody PrestamoDTO prestamoDTO) {
        if (prestamoDTO.getIdPrestamo() != null) {
            return new ResponseEntity(servicioPrestamo.modificar(prestamoDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrarPrestamo/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            servicioPrestamo.borrar(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/todosRecursos")
    public ResponseEntity<List<PrestamoDTO>> findAll() {
        return new ResponseEntity(servicioPrestamo.obtenerTodos(), HttpStatus.OK);
    }

    @PutMapping("/prestar/{id}")
    public ResponseEntity prestar(@PathVariable("id")String id) {
        return new ResponseEntity(servicioPrestamo.prestar(id), HttpStatus.OK);
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity devolver(@PathVariable("id")String id) {
        return new ResponseEntity(servicioPrestamo.devolverPrestamo(id), HttpStatus.OK);
    }

    @GetMapping("/buscarIdRecurso/{idRecurso}")
    public ResponseEntity<List<PrestamoDTO>> buscarTipoRecurso(@PathVariable("idRecurso") String idRecursoo) {
        return new ResponseEntity(servicioPrestamo.buscarPorIdRecurso(idRecursoo), HttpStatus.OK);
    }

}
