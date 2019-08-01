package com.blu911.herorestapi.hero;

import com.blu911.herorestapi.power.Power;
import com.blu911.herorestapi.power.PowerRepository;
import com.github.javafaker.*;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class HeroGenerator {
    private final Superhero superheroGenerator;
    private final Job jobGenerator;
    private final Lorem loremGenerator;
    private final PowerRepository powerRepository;

    public HeroGenerator(PowerRepository powerRepository) {
        Faker faker = new Faker();
        superheroGenerator = faker.superhero();
        jobGenerator = faker.job();
        loremGenerator = faker.lorem();
        this.powerRepository = powerRepository;
    }

    public Hero generateHero() {
        Random random = new Random();
        Hero hero = new Hero();
        hero.setName(superheroGenerator.name());
        hero.setHitPoints(random.nextInt(51)+50);
        hero.setManaPoints(random.nextInt(51)+50);
        hero.setPower(generatePowerForHero());
        return hero;
    }

    private Power generatePowerForHero() {
        Power power = new Power();
        power.setName(jobGenerator.keySkills());
        power.setDescription(loremGenerator.sentence());
        powerRepository.save(power);
        return power;
    }

}
