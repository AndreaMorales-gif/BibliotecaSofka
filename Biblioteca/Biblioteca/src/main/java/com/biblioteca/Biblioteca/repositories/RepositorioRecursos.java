package com.biblioteca.Biblioteca.repositories;

import com.biblioteca.Biblioteca.model.Recursos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioRecursos extends MongoRepository<Recursos, String> {
    Iterable<Recursos> findByTipoRecurso(String tipoRecurso);
    Iterable<Recursos> findByTematica(String tematica);
}
