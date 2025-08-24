package com.wolfpack.multitenancy.filter;

import com.wolfpack.multitenancy.core.MultitenancyProperties;
import com.wolfpack.multitenancy.resolver.TenantResolver;
import com.wolfpack.multitenancy.core.TenantContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 11)
public class TenantRequestFilter extends OncePerRequestFilter {

    @PersistenceContext
    private EntityManager entityManager;

    private final TenantResolver tenantResolver;
    private final MultitenancyProperties props;

    public TenantRequestFilter(@Qualifier("dev") TenantResolver tenantResolver, MultitenancyProperties props) {
        this.tenantResolver = tenantResolver;
        this.props = props;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        if (!props.isEnabled()) {
            // Multitenancy deshabilitado (no recomendado, pero posible):
            chain.doFilter(request, response);
            return;
        }

        Long tenantId = tenantResolver.resolveTenantId(request);

        if (tenantId == null) {
            if (props.isRequireHeader()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se pudo resolver el tenant");
                return;
            } else {
                // Fallback a default en dev
                tenantId = props.getDefaultTenantId();
            }
        }

        Session session = entityManager.unwrap(Session.class);
        session.enableFilter("tenant").setParameter("tenantId", tenantId);

        try {
            TenantContext.set(tenantId);
            chain.doFilter(request, response);
        } finally {
            try { session.disableFilter("tenant"); } catch (Exception ignored) {}
            TenantContext.clear();
        }
    }
}
