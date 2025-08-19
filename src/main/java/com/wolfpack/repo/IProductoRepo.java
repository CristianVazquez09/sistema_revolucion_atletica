package com.wolfpack.repo;

import com.wolfpack.model.Categoria;
import com.wolfpack.model.Producto;

import java.util.List;

public interface IProductoRepo extends IGenericRepo<Producto, Long>{

    List<Producto> findByCategoria(Categoria categoria);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
