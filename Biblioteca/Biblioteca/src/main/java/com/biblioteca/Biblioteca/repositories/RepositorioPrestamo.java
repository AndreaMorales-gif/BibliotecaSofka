package com.biblioteca.Biblioteca.repositories;

import com.biblioteca.Biblioteca.model.Prestamo;
import com.biblioteca.Biblioteca.model.Recursos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPrestamo extends MongoRepository<Prestamo, String> {
    Iterable<Prestamo> findByIdRecurso(String idRecurso);
}
