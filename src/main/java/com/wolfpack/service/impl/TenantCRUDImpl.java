package com.wolfpack.service.impl;

import com.wolfpack.model.Gimnasio;
import com.wolfpack.multitenancy.core.TenantContext;
import com.wolfpack.multitenancy.jpa.TenantScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

public abstract class TenantCRUDImpl<T extends TenantScoped, ID> extends CRUDImpl<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected void beforeGuardar(T entity) {
        // Si no viene gimnasio (y no debe venir del cliente), lo asignamos por tenant
        if (entity.getGimnasio() == null) {
            Long tenantId = TenantContext.get();

            Gimnasio gimnasioRef = entityManager.getReference(Gimnasio.class, tenantId);
            entity.setGimnasio(gimnasioRef);
        } else {
            // Si por alguna razón viene, validamos que sea el mismo tenant
            Long tenantId = TenantContext.get();
            if (entity.getGimnasio().getId() == null
                    || !entity.getGimnasio().getId().equals(tenantId)) {
                throw new EntityNotFoundException("Gimnasio inválido para el tenant actual.");
            }
        }
    }

    @Override
    protected T mergeForActualizar(T persistent, T incoming) {
        // Regla de oro: NO cambiar el gimnasio en updates.
        // Conservamos el gimnasio del que ya existe en BD y pisamos el incoming.
        incoming.setGimnasio(persistent.getGimnasio());

        // Además validamos que el gimnasio del persistente coincide con el tenant actual
        Long tenantId = TenantContext.get();
        if (persistent.getGimnasio() == null
                || persistent.getGimnasio().getId() == null
                || !persistent.getGimnasio().getId().equals(tenantId)) {
            throw new EntityNotFoundException("No autorizado para actualizar recursos de otro gimnasio.");
        }
        return incoming;
    }
}
