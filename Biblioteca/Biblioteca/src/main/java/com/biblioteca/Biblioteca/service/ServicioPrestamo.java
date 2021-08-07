package com.biblioteca.Biblioteca.service;

import com.biblioteca.Biblioteca.dto.PrestamoDTO;
import com.biblioteca.Biblioteca.dto.RecursosDTO;
import com.biblioteca.Biblioteca.mapper.LendingMapper;
import com.biblioteca.Biblioteca.model.Prestamo;
import com.biblioteca.Biblioteca.model.Recursos;
import com.biblioteca.Biblioteca.repositories.RepositorioPrestamo;
import com.biblioteca.Biblioteca.repositories.RepositorioRecursos;
import com.biblioteca.Biblioteca.repositories.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioPrestamo {
    @Autowired
    RepositorioPrestamo repositorioPrestamo;
    @Autowired
    RepositorioUsuario repositorioUsuario;
    @Autowired
    RepositorioRecursos repositorioRecursos;

    LendingMapper mapper = new LendingMapper();

    public String crear(PrestamoDTO prestamoDTO) {
        Prestamo prestamo = mapper.fromDTO(prestamoDTO);
        if(repositorioRecursos.existsById(prestamo.getIdRecurso())&&repositorioUsuario.existsById(prestamo.getIdUsuario())){
            Optional<Recursos> recurso = repositorioRecursos.findById(prestamoDTO.getIdRecurso());
            if (recurso.get().isEstadoRecurso(Boolean.TRUE)){
                recurso.get().setEstadoRecurso(Boolean.FALSE);
                mapper.fromCollection(repositorioPrestamo.save(prestamo));
                return "Puede realizar su prestámo.";
            }else{
                return "No puede hacer la creación del prestámo";
            }
        }
        return "No se puede hacer el prestámo, si no existe el usuario o el recurso.";
    }

    public String prestar(String id){
        Recursos recurso = repositorioRecursos.findById(id).orElseThrow(()
                -> new RuntimeException("Asegurese de verificar si puede realizar su prestámo"));
        if (recurso.isEstadoRecurso(Boolean.TRUE)){
            recurso.setEstadoRecurso(Boolean.FALSE);
            repositorioRecursos.save(recurso);
            return "El prestámo ha sido confirmado";
        }else {
            return "Asegurese de verificar si puede realizar su prestámo";
        }
    }

    public String devolverPrestamo(String id){
        Recursos recurso = repositorioRecursos.findById(id).orElseThrow(()
                -> new RuntimeException("No se encontró el recurso"));
        if (recurso.isEstadoRecurso(Boolean.TRUE)){
            return "El recurso no lo has prestado, por lo tanto no puedes devolverlo.";
        }else {
            recurso.setEstadoRecurso(Boolean.TRUE);
            repositorioRecursos.save(recurso);
            return "El recurso lo has devuelto exitosamente.";
        }
    }

    public List<PrestamoDTO> buscarPorIdRecurso(String idRecurso){
        List<Prestamo> prestamo = (List<Prestamo>) repositorioPrestamo.findByIdRecurso(idRecurso);
        return mapper.fromCollectionList(prestamo);
    }

    public PrestamoDTO modificar(PrestamoDTO prestamoDTO) {
        Prestamo prestamo = mapper.fromDTO(prestamoDTO);
        repositorioPrestamo.findById(prestamo.getIdPrestamo()).orElseThrow(() -> new RuntimeException("prestámo no encontrado"));
        return mapper.fromCollection(repositorioPrestamo.save(prestamo));
    }


    public List<PrestamoDTO> obtenerTodos() {
        List<Prestamo> prestamo = (List<Prestamo>) repositorioPrestamo.findAll();
        return mapper.fromCollectionList(prestamo);
    }

    public void borrar(String id) {
        repositorioPrestamo.deleteById(id);
    }
}
