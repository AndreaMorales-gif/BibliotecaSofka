package com.biblioteca.Biblioteca.service;

import com.biblioteca.Biblioteca.dto.RecursosDTO;
import com.biblioteca.Biblioteca.mapper.ResourceMapper;
import com.biblioteca.Biblioteca.model.Recursos;
import com.biblioteca.Biblioteca.repositories.RepositorioRecursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioRecursos {
    @Autowired
    RepositorioRecursos repositorioRecursos;
    ResourceMapper mapper = new ResourceMapper();

    public RecursosDTO crear(RecursosDTO recursosDTO) {
        Recursos recurso = mapper.fromDTO(recursosDTO);
        return mapper.fromCollection(repositorioRecursos.save(recurso));
    }

    public String obtenerPorId(String id) {
        Optional<Recursos> recurso = repositorioRecursos.findById(id);

        if (recurso.isEmpty()) {
            return "El recurso no existe.";
        }
        if (recurso.get().isEstadoRecurso(Boolean.FALSE)== false) {

            return "El recurso no está disponible.";
        } else {
            return "El recurso si está disponible.";
        }
    }

    public List<RecursosDTO> obtenerTodos() {
        List<Recursos> recurso = (List<Recursos>) repositorioRecursos.findAll();
        return mapper.fromCollectionList(recurso);
    }

    public List<RecursosDTO> obtenerRecursosTipo(String tipo) {
        List<Recursos> recurso = (List<Recursos>) repositorioRecursos.findByTipoRecurso(tipo);
        return mapper.fromCollectionList(recurso);
    }

    public List<RecursosDTO> obtenerTematica(String tematica){
        List<Recursos> recurso = (List<Recursos>) repositorioRecursos.findByTematica(tematica);
        return mapper.fromCollectionList(recurso);
    }
}


