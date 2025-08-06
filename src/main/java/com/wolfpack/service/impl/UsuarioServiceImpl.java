package com.wolfpack.service.impl;

import com.wolfpack.model.Rol;
import com.wolfpack.model.Usuario;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IRolRepo;
import com.wolfpack.repo.IUsuarioRepo;
import com.wolfpack.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends CRUDImpl<Usuario, Integer> implements IUsuarioService {

    private final IUsuarioRepo usuarioRepo;
    private final IRolRepo rolRepo;
    @Override
    protected IGenericRepo<Usuario, Integer> getRepo() {
        return usuarioRepo;
    }

    @Override
    public Usuario guardarNuevoUsuario(Usuario usuario, String nombreRol) {

        Rol rol = rolRepo.findByNombre(nombreRol);

        usuario.setRol(rol);
        return usuarioRepo.save(usuario);
    }
}
