package com.blu911.herorestapi.hero;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    Hero findHeroById(Long id);
    List<Hero> findAll();
}
