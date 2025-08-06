package com.wolfpack.service.impl;

import com.wolfpack.model.Venta;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IVentaRepo;
import com.wolfpack.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl extends CRUDImpl<Venta, Long> implements IVentaService {

    private final IVentaRepo ventaRepo;
    @Override
    protected IGenericRepo<Venta, Long> getRepo() {
        return ventaRepo;
    }
}
