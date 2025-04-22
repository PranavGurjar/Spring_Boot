package com.actuator.helper;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Controller;

@Controller
public class MyDBHealthService implements HealthIndicator {

    public static final String DB_Service="Database Service";

    public boolean checkHealth(){
        //custom logic
        return false;
    }

    @Override
    public Health health() {
        if (checkHealth()){
            return Health.up().withDetail(DB_Service, "Database service is running").build();
        }
        return Health.down().withDetail(DB_Service, "Database service is running down").build();
    }
}
