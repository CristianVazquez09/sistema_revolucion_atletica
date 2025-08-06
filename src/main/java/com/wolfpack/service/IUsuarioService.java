package com.wolfpack.service;

import com.wolfpack.model.Usuario;


public interface IUsuarioService extends ICRUD<Usuario, Integer>{

    Usuario guardarNuevoUsuario(Usuario usuario, String nombreRol);

}
