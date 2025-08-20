package com.wolfpack.multitenancy.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "app.multitenancy")
public class MultitenancyProperties {
    private boolean enabled = true;
    private Long defaultTenantId = 1L;
    private boolean requireHeader = false;

}
