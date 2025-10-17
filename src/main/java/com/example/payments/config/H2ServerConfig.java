package com.example.payments.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2ServerConfig {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TcpServer() throws java.sql.SQLException {
        return Server.createTcpServer("-tcp", "-tcpPort", "9092");
    }
}