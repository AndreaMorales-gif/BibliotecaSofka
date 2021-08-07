package com.biblioteca.Biblioteca.mapper;

import com.biblioteca.Biblioteca.dto.RecursosDTO;
import com.biblioteca.Biblioteca.model.Recursos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResourceMapper {

    public Recursos fromDTO(RecursosDTO dto){
        Recursos recursos = new Recursos();
        recursos.setIdRecurso(dto.getIdRecurso());
        recursos.setTitulo(dto.getTitulo());
        recursos.setTematica(dto.getTematica());
        recursos.setEstadoRecurso(dto.isEstadoRecurso(Boolean.TRUE));
        recursos.setTipoRecurso(dto.getTipoRecurso());
        return recursos;
    }

    public RecursosDTO fromCollection(Recursos collection){
        RecursosDTO recursosDTO = new RecursosDTO();
        recursosDTO.setIdRecurso(collection.getIdRecurso());
        recursosDTO.setTitulo(collection.getTitulo());
        recursosDTO.setTematica(collection.getTematica());
        recursosDTO.setEstadoRecurso(collection.isEstadoRecurso(Boolean.FALSE));
        recursosDTO.setTipoRecurso(collection.getTipoRecurso());
        return recursosDTO;
    }

    public List<RecursosDTO> fromCollectionList(List<Recursos> collection) {
        if (collection == null) {
            return null;

        }
        List<RecursosDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Recursos recursos = (Recursos)listTracks.next();
            list.add(fromCollection(recursos));
        }

        return list;
    }
}
