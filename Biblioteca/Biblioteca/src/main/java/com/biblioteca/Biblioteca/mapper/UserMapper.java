package com.biblioteca.Biblioteca.mapper;


import com.biblioteca.Biblioteca.dto.UsuarioDTO;
import com.biblioteca.Biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserMapper {

    public Usuario fromDTO(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(dto.getIdUsuario());
        usuario.setNombre(dto.getNombre());
        usuario.setFecha(dto.getFecha());
        return usuario;
    }


    public UsuarioDTO fromCollection(Usuario collection){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(collection.getIdUsuario());
        usuarioDTO.setNombre(collection.getNombre());
        usuarioDTO.setFecha(collection.getFecha());
        return usuarioDTO;
    }

    public List<UsuarioDTO> fromCollectionList(List<Usuario> collection) {
        if (collection == null) {
            return null;

        }
        List<UsuarioDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Usuario usuario = (Usuario)listTracks.next();
            list.add(fromCollection(usuario));
        }

        return list;
    }
}
