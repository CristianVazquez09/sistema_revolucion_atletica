package com.wolfpack.multitenancy.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component("prod")
public class HeaderTenantResolver implements TenantResolver {

    @Override
    public Long resolveTenantId(HttpServletRequest request) {
        String header = request.getHeader("X-Tenant-Id");
        if (header == null || header.isBlank()) {
            return null;
        }
        try {
            return Long.valueOf(header);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
