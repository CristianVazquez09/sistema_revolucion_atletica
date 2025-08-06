package com.wolfpack.service.impl;

import com.wolfpack.model.Categoria;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.ICategoriaRepo;
import com.wolfpack.service.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl extends CRUDImpl<Categoria, Integer> implements ICategoriaService {

    private final ICategoriaRepo categoriaRepo;
    @Override
    protected IGenericRepo<Categoria, Integer> getRepo() {
        return categoriaRepo;
    }
}
