package com.wolfpack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@SpringBootApplication
public class RevolucionAtleticaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RevolucionAtleticaBackendApplication.class, args);
    }

    // @Configuration


}
