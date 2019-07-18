package com.blu911.herorestapi;

import com.blu911.herorestapi.hero.Hero;
import com.blu911.herorestapi.hero.HeroGenerator;
import com.blu911.herorestapi.hero.HeroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HeroRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeroRestApiApplication.class, args);
    }

    /*This Bean is used to generate example heroes and insert them into the database
    for testing phase
    TODO: Remove this Bean after development completed
     */
    @Bean
    CommandLineRunner init(HeroRepository heroRepository) {
        return args -> {
            HeroGenerator heroGenerator = new HeroGenerator();
            Set<Hero> heroSet = new HashSet<>();

            while (heroSet.size() < 100) {
                heroSet.add(heroGenerator.generateHero());
            }
            heroRepository.saveAll(heroSet);
        };
    }
}
