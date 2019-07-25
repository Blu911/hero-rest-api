package com.blu911.herorestapi.hero;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {

    private final HeroRepository repository;

    @Override
    public Hero findHeroById(Long id) {
        return repository.findHeroById(id);
    }

    @Override
    public List<Hero> findAll() {
        return repository.findAll();
    }

    @Override
    public Hero save(Hero hero) {
        return repository.save(hero);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Hero> sortHeroesByIdASC(List<Hero> heroes) {
        return heroes.stream()
                .sorted(Comparator.comparingLong(Hero::getId))
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(Collection<Hero> heroes) {
        repository.saveAll(heroes);
    }
}
