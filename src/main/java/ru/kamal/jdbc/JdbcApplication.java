package ru.kamal.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import ru.kamal.jdbc.app.configuration.NumberServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
@PropertySource(value = "classpath:application.yml")
public class JdbcApplication {

    public JdbcApplication() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        SpringApplication.run(JdbcApplication.class, args);


    }



}
