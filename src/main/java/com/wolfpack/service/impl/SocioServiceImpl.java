package com.wolfpack.service.impl;

import com.wolfpack.dto.SocioDTO;
import com.wolfpack.model.Socio;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.ISocioRepo;
import com.wolfpack.service.ISocioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
    public class SocioServiceImpl extends CRUDImpl<Socio, Long> implements ISocioService {

    private final ISocioRepo socioRepo;
    @Override
    protected IGenericRepo<Socio, Long> getRepo() {
        return socioRepo;
    }

    @Override
    public Page<Socio> buscarSocios(Pageable pageable) {
        return socioRepo.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Socio> buscarSociosPorNombre(String nombre, Pageable pageable) {

        return socioRepo.buscarSociosPorNombre(nombre, pageable);

    }


}
