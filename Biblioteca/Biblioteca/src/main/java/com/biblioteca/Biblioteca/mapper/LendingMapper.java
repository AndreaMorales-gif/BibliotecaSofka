package com.biblioteca.Biblioteca.mapper;

import com.biblioteca.Biblioteca.dto.PrestamoDTO;
import com.biblioteca.Biblioteca.model.Prestamo;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LendingMapper {

    public Prestamo fromDTO(PrestamoDTO dto){
        Prestamo prestamo = new Prestamo();
        prestamo.setIdPrestamo(dto.getIdPrestamo());
        prestamo.setIdUsuario(dto.getIdUsuario());
        prestamo.setIdRecurso(dto.getIdRecurso());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setFechaEntrega(dto.getFechaEntrega());
        return prestamo;
    }
    public PrestamoDTO fromCollection(Prestamo collection){
        PrestamoDTO prestamoDTO = new PrestamoDTO();
        prestamoDTO.setIdPrestamo(collection.getIdPrestamo());
        prestamoDTO.setIdUsuario(collection.getIdUsuario());
        prestamoDTO.setIdRecurso(collection.getIdRecurso());
        prestamoDTO.setFechaPrestamo(collection.getFechaPrestamo());
        prestamoDTO.setFechaEntrega(collection.getFechaEntrega());
        return prestamoDTO;
    }

    public List<PrestamoDTO> fromCollectionList(List<Prestamo> collection) {
        if (collection == null) {
            return null;

        }
        List<PrestamoDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Prestamo prestamo = (Prestamo) listTracks.next();
            list.add(fromCollection(prestamo));
        }

        return list;
    }
}
