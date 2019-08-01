package com.blu911.herorestapi.hero;

import com.blu911.herorestapi.power.PowerRepository;
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

    private final HeroRepository heroRepository;
    private final PowerRepository powerRepository;

    public Hero generateHero(){
        HeroGenerator heroGenerator = new HeroGenerator(powerRepository);
        return heroGenerator.generateHero();
    }

    @Override
    public Hero findHeroById(Long id) {
        return heroRepository.findHeroById(id);
    }

    @Override
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }

    @Override
    public Hero save(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public void deleteById(Long id) {
        heroRepository.deleteById(id);
    }

    @Override
    public List<Hero> sortHeroesByIdASC(List<Hero> heroes) {
        return heroes.stream()
                .sorted(Comparator.comparingLong(Hero::getId))
                .collect(Collectors.toList());
    }

    @Override
    public void saveAll(Collection<Hero> heroes) {
        heroRepository.saveAll(heroes);
    }
}
