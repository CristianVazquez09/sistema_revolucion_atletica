package com.wolfpack.service.impl;

import com.wolfpack.model.Inscripcion;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IInscripcionRepo;
import com.wolfpack.service.IInscripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InscripcionServiceImpl extends CRUDImpl<Inscripcion, Integer> implements IInscripcionService {

    private final IInscripcionRepo inscripcionRepo;
    @Override
    protected IGenericRepo<Inscripcion, Integer> getRepo() {
        return inscripcionRepo;
    }
}
