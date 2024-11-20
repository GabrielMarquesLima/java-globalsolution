package br.com.ecosafe.application;

import org.glassfish.jersey.server.ResourceConfig;
import br.com.ecosafe.util.CorsFilter;

public class ApiConfig extends ResourceConfig {
    public ApiConfig() {
        packages("br.com.ecosafe.resource");
        register(CorsFilter.class);
    }
}
