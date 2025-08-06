package com.wolfpack.service.impl;

import com.wolfpack.model.Socio;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.ISocioRepo;
import com.wolfpack.service.ISocioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocioServiceImpl extends CRUDImpl<Socio, Long> implements ISocioService {

    private final ISocioRepo socioRepo;
    @Override
    protected IGenericRepo<Socio, Long> getRepo() {
        return socioRepo;
    }
}
