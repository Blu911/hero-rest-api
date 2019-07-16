package com.blu911.herorestapi;

import com.blu911.herorestapi.model.HeroGenerator;
import com.blu911.herorestapi.repository.HeroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HeroRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroRestApiApplication.class, args);
    }

    @Bean
    CommandLineRunner init(HeroRepository heroRepository) {
        return args -> {
            int i = 1;
            HeroGenerator heroGenerator = new HeroGenerator();

            while (i <= 100) {
                heroRepository.save(heroGenerator.generateHero());
                i++;
            }
        };
    }
}
