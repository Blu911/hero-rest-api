package com.blu911.herorestapi.hero;

import com.github.javafaker.Faker;
import com.github.javafaker.Superhero;
import org.springframework.stereotype.Component;

@Component
public class HeroGenerator {
    private final Superhero superheroGenerator;

    public HeroGenerator() {
        Faker faker = new Faker();
        superheroGenerator = faker.superhero();
    }

    public Hero generateHero() {
        Hero hero = new Hero();
        hero.setName(superheroGenerator.name());
        return hero;
    }

}
