package com.wolfpack.service.impl;

import com.wolfpack.model.Categoria;
import com.wolfpack.model.Gimnasio;
import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.repo.ICategoriaRepo;
import com.wolfpack.service.ICategoriaService;
import com.wolfpack.util.TenantContext;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl extends TenantCRUDImpl<Categoria, Integer> implements ICategoriaService {

    private final ICategoriaRepo categoriaRepo;
    private final EntityManager em;
    @Override
    protected IGenericRepo<Categoria, Integer> getRepo() {
        return categoriaRepo;
    }

}
