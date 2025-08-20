package com.wolfpack.service.impl;



import com.wolfpack.repo.IGenericRepo;
import com.wolfpack.service.ICRUD;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();
    // hooks opcionales para que subclases personalicen
    protected void beforeGuardar(T entity) {}
    protected T mergeForActualizar(T persistent, T incoming) { return incoming; }

    @Override
    public T guardar(T t) throws Exception {
        beforeGuardar(t);
        return getRepo().save(t);
    }

    @Override
    public T actualizar(ID id, T t) throws Exception {
        T persistent = getRepo().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID NOT FOUND: " + id));

        // Por defecto, devolvemos el incoming; subclases pueden copiar campos finamente.
        T merged = mergeForActualizar(persistent, t);
        return getRepo().save(merged);
    }

    @Override
    public List<T> buscarTodos() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T buscarPorId(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(()-> new EntityNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void eliminar(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new EntityNotFoundException("ID NOT FOUND: " + id));
        getRepo().deleteById(id);
    }
}
