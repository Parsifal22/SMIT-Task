package com.example.smit.contacts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class ContactConfig implements WebMvcConfigurer {

    @Bean
    CommandLineRunner commandLineRunner(ContactRepository repository) {
        return args -> {
            Contact client = new Contact(
                    1L,
                    "Martin",
                    "Nitram2",
                    "+372 555222"

            );
            Contact client2 = new Contact(
                    "Anton",
                    "Notna8",
                    "+372 531257"

            );

            repository.saveAll(
                    List.of(client, client2)
            );
        };
    }


}
