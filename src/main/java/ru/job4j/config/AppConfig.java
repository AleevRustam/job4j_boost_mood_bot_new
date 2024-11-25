package ru.job4j.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class AppConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.url}")
    private String appUrl;

    @Value("${app.timeout}")
    private int timeout;

    public void printConfig() {
        System.out.println("App Name: " + appName);
        System.out.println("App Version: " + appVersion);
        System.out.println("App URL: " + appUrl);
        System.out.println("Timeout: " + timeout);
    }
}


