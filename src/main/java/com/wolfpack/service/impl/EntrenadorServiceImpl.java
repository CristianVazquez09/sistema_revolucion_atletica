package com.wolfpack.service.impl;

import com.wolfpack.model.Entrenador;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IEntrenadorRepo;
import com.wolfpack.service.IEntrenadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntrenadorServiceImpl extends TenantCRUDImpl<Entrenador, Integer> implements IEntrenadorService {

    private final IEntrenadorRepo entrenadorRepo;
    @Override
    protected IGenericRepo<Entrenador, Integer> getRepo() {
        return entrenadorRepo;
    }
}
