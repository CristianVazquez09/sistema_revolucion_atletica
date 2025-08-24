package com.wolfpack.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class TimeConfig {

    @Bean
    public ZoneId appZoneId(@Value("${app.timezone:America/Merida}") String zone) {
        return ZoneId.of(zone);
    }

    @Bean
    public Clock appClock(ZoneId appZoneId) {
        return Clock.system(appZoneId);
    }
}

