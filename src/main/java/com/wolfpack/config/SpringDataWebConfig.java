package com.wolfpack.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@Configuration
@EnableSpringDataWebSupport(
        pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO
)
public class SpringDataWebConfig {

    @Bean
    public FilterRegistrationBean<OpenEntityManagerInViewFilter> oeivFilter() {
        var bean = new FilterRegistrationBean<>(new OpenEntityManagerInViewFilter());
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 10); // corre primero, abre el EM de la request
        return bean;
    }
}
