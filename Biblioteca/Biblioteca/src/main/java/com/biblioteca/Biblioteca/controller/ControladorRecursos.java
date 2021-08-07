package com.biblioteca.Biblioteca.controller;

import com.biblioteca.Biblioteca.dto.RecursosDTO;
import com.biblioteca.Biblioteca.service.ServicioRecursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos")
public class ControladorRecursos {

    @Autowired
    ServicioRecursos servicioRecursos;

    @PostMapping("/crearRecurso")
    public ResponseEntity<RecursosDTO> create(@RequestBody RecursosDTO recursosDTO) {
        return new ResponseEntity(servicioRecursos.crear(recursosDTO), HttpStatus.CREATED);
    }

    @GetMapping("/buscarRecurso/{id}")
    public ResponseEntity<RecursosDTO> findbyId(@PathVariable("id") String id) {
        return new ResponseEntity(servicioRecursos.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping("/todosRecursos")
    public ResponseEntity<List<RecursosDTO>> findAll() {
        return new ResponseEntity(servicioRecursos.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/todosRecursos/{tipo}")
    public ResponseEntity<List<RecursosDTO>> buscarTipoRecurso(@PathVariable("tipo") String tipo) {
        return new ResponseEntity(servicioRecursos.obtenerRecursosTipo(tipo), HttpStatus.OK);
    }

    @GetMapping("/tematicas/{tematica}")
    public ResponseEntity<List<RecursosDTO>> buscarTematica(@PathVariable("tematica") String tematica){
        return new ResponseEntity(servicioRecursos.obtenerTematica(tematica), HttpStatus.OK);
    }


}
