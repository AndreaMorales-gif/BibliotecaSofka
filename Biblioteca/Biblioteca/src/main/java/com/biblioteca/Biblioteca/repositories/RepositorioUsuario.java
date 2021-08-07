package com.biblioteca.Biblioteca.repositories;


import com.biblioteca.Biblioteca.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends MongoRepository<Usuario, String> {
}
