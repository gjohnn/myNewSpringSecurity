package com.jgiga.SpringSecurity.config.Auth;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class DataInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DataInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        // Cargar el archivo SQL desde el classpath
        // Cargar el archivo SQL desde el classpath
        ClassPathResource resource = new ClassPathResource("setup_users.sql");

        try {
            // Verificar que el archivo está siendo encontrado
            if (resource.exists()) {
                System.out.println("Archivo SQL encontrado!");

                // Leer el contenido del archivo
                String sql = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));

                // Mostrar el SQL que se va a ejecutar (para depuración)
                System.out.println("SQL a ejecutar: ");
                System.out.println(sql);

                // Ejecutar el SQL
                jdbcTemplate.execute(sql);
                System.out.println("Usuarios insertados correctamente 🚀");
            } else {
                System.out.println("No se encontró el archivo setup_users.sql en el classpath.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo SQL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
