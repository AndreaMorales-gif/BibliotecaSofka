package com.biblioteca.Biblioteca.service;
import com.biblioteca.Biblioteca.dto.PrestamoDTO;
import com.biblioteca.Biblioteca.dto.UsuarioDTO;
import com.biblioteca.Biblioteca.mapper.UserMapper;
import com.biblioteca.Biblioteca.model.Prestamo;
import com.biblioteca.Biblioteca.model.Usuario;
import com.biblioteca.Biblioteca.repositories.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioUsuario {
    @Autowired
    RepositorioUsuario repositorioUsuario;
    UserMapper mapper = new UserMapper();

    public UsuarioDTO crear( UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.fromDTO(usuarioDTO);
        return mapper.fromCollection(repositorioUsuario.save(usuario));
    }

    public  UsuarioDTO modificar( UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.fromDTO(usuarioDTO);
        repositorioUsuario.findById(usuario.getIdUsuario()).orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        return mapper.fromCollection(repositorioUsuario.save(usuario));
    }
    public List<UsuarioDTO> obtenerTodos() {
        List<Usuario> usuario = (List<Usuario>) repositorioUsuario.findAll();
        return mapper.fromCollectionList(usuario);
    }

    public void borrar(String id) {
        repositorioUsuario.deleteById(id);
    }
}
