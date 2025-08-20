package com.wolfpack.service.impl;

import com.wolfpack.model.Categoria;
import com.wolfpack.model.Producto;
import com.wolfpack.repo.ICategoriaRepo;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.IProductoRepo;
import com.wolfpack.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl extends TenantCRUDImpl<Producto, Long> implements IProductoService {

    private final IProductoRepo productoRepo;
    private final ICategoriaRepo categoriaRepo;
    @Override
    protected IGenericRepo<Producto, Long> getRepo() {
        return productoRepo;
    }

    @Override
    public List<Producto> buscarProductosPorCategoria(Integer idCategoria) {
        Categoria categoria = categoriaRepo.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        return productoRepo.findByCategoria(categoria);
    }

    @Override
    public List<Producto> buscarProductosNombre(String nombre) {

        return productoRepo.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Page<Producto> buscarProductos(Pageable pageable) {
        return productoRepo.findAll(pageable);
    }
}
