package com.biblioteca.Biblioteca.controller;

import com.biblioteca.Biblioteca.dto.RecursosDTO;
import com.biblioteca.Biblioteca.dto.UsuarioDTO;
import com.biblioteca.Biblioteca.service.ServicioRecursos;
import com.biblioteca.Biblioteca.service.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class ControladorUsuario {
    @Autowired
    ServicioUsuario servicioUsuario;

    @PostMapping("/crearUsuario")
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO) {

        return new ResponseEntity(servicioUsuario.crear(usuarioDTO), HttpStatus.CREATED);
    }

    @GetMapping("/todosRecursos")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return new ResponseEntity(servicioUsuario.obtenerTodos(), HttpStatus.OK);
    }

    @PutMapping("/modificarUsuario")
    public ResponseEntity<UsuarioDTO> update(@RequestBody UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getIdUsuario() != null) {
            return new ResponseEntity(servicioUsuario.modificar(usuarioDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/borrarUsuario/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            servicioUsuario.borrar(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
