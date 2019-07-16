package com.blu911.herorestapi.repository;

import com.blu911.herorestapi.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findAllByNameLike(String name);

    Hero findById(long id);

    Hero findByName(String name);
}
