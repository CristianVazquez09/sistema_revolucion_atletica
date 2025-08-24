package com.wolfpack.multitenancy.resolver;


import jakarta.servlet.http.HttpServletRequest;

public interface TenantResolver {

    Long resolveTenantId(HttpServletRequest request);
}
