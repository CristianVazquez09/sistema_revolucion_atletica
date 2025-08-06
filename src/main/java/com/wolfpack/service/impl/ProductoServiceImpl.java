package com.wolfpack.service.impl;

import com.wolfpack.model.Producto;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IProductoRepo;
import com.wolfpack.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl extends CRUDImpl<Producto, Long> implements IProductoService {

    private final IProductoRepo productoRepo;
    @Override
    protected IGenericRepo<Producto, Long> getRepo() {
        return productoRepo;
    }
}
