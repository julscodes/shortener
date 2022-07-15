package com.julscodes.shortener;

import com.fasterxml.jackson.core.util.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ShortenerApplication extends ResourceConfig {
    public ShortenerApplication() {
        register(new ShortenerApplicationBinder());
        register(ObjectMapperProvider.class);
        register(JacksonFeature.class);
        register(ShortenerExceptionMapper.class);
        packages(true, "com.julscodes.shortener");
    }
}
