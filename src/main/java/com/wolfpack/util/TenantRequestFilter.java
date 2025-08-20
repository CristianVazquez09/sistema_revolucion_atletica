package com.wolfpack.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 11) // corre inmediatamente después del OEIV
public class TenantRequestFilter extends OncePerRequestFilter {

    @PersistenceContext
    private EntityManager em;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String header = req.getHeader("X-Tenant-Id");
        if (header == null || header.isBlank()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Falta header X-Tenant-Id");
            return;
        }

        Long tenantId = Long.valueOf(header);

        // habilita el filtro en el MISMO Session que abrió el OEIV
        var session = em.unwrap(org.hibernate.Session.class);
        session.enableFilter("tenant").setParameter("tenantId", tenantId);

        try {
            TenantContext.set(tenantId); // opcional: por si lo necesitas en Services
            chain.doFilter(req, res);
        } finally {
            try { session.disableFilter("tenant"); } catch (Exception ignored) {}
            TenantContext.clear();
        }
    }
}




