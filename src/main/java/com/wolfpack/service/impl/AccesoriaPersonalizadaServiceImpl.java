package com.wolfpack.service.impl;

import com.wolfpack.model.AccesoriaPersonalizada;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IAccesoriaPersonalizadaRepo;
import com.wolfpack.service.IAccesoriaPersonalizadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccesoriaPersonalizadaServiceImpl extends TenantCRUDImpl<AccesoriaPersonalizada, Long> implements IAccesoriaPersonalizadaService {

    private final IAccesoriaPersonalizadaRepo accesoriaRepo;
    @Override
    protected IGenericRepo<AccesoriaPersonalizada, Long> getRepo() {
        return accesoriaRepo;
    }
}
