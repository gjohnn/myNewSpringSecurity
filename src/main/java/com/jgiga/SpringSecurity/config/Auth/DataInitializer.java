package com.jgiga.SpringSecurity.config.Auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.jgiga.SpringSecurity.repositories.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    public DataInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        ClassPathResource resource = new ClassPathResource("setup_users.sql");
        try {

            if (userRepository.count() >= 1) {
                System.out.println("Users already inserted");
                return;
            }

            if (resource.exists()) {
                System.out.println("SQL File found!");
                String sql = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
                System.out.println("Executing SQL... ");
                jdbcTemplate.execute(sql);
                System.out.println("Users inserted correctly 🚀");
            } else {
                System.out.println("setup_users.sql not found.");
            }

        } catch (IOException e) {
            System.out.println("Error reading SQL query: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
