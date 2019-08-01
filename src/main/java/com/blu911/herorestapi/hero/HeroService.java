package com.blu911.herorestapi.hero;

import java.util.Collection;
import java.util.List;

public interface HeroService {
    Hero findHeroById(Long id);

    List<Hero> findAll();

    Hero save(Hero hero);

    void deleteById(Long id);

    List<Hero> sortHeroesByIdASC(List<Hero> heroes);

    void saveAll(Collection<Hero> heroes);

    Hero generateHero();
}
