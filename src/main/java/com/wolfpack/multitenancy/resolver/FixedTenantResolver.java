package com.wolfpack.multitenancy.resolver;


import com.wolfpack.multitenancy.core.MultitenancyProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("dev")
public class FixedTenantResolver implements TenantResolver {

    private final MultitenancyProperties props;

    public FixedTenantResolver(MultitenancyProperties props) {
        this.props = props;
    }

    @Override
    public Long resolveTenantId(HttpServletRequest request) {
        return props.getDefaultTenantId(); // p. ej. 1L
    }
}
