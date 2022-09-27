package com.example.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "app")
public interface AppConfig {

    String url();
    String username();
    String password();
}
