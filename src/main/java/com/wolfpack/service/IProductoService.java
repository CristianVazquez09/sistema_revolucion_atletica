package com.wolfpack.service;

import com.wolfpack.model.Categoria;
import com.wolfpack.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IProductoService extends ICRUD<Producto, Long>{

    List<Producto> buscarProductosPorCategoria(Integer idCategoria);
    List<Producto> buscarProductosNombre(String nombre);

    Page<Producto> buscarProductos(Pageable pageable);

}
