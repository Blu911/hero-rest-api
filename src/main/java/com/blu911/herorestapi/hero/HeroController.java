package com.blu911.herorestapi.hero;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HeroController {

    private final HeroRepository heroRepository;

    public HeroController(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @RequestMapping(path = "/heroes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity showAllHeroes() {
        List<Hero> heroes = heroRepository.findAll();
        List<Hero> heroesById = heroes.stream().sorted(Comparator.comparingLong(Hero::getId)).collect(Collectors.toList());
        return ResponseEntity.ok(heroesById);
    }

    @RequestMapping(path = "/heroes/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity showHero(@PathVariable long id) {
        Hero hero = heroRepository.findById(id);
        return ResponseEntity.ok(hero);
    }

    @RequestMapping(path = "/heroes", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateHero(@Valid @RequestBody Hero hero) {
        Hero updatedHero = heroRepository.save(hero);
        return ResponseEntity.ok(updatedHero);
    }

    @RequestMapping(path = "/heroes", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addHero(@Valid @RequestBody Hero hero) {
        Hero addedHero = heroRepository.save(hero);
        return ResponseEntity.ok(addedHero);
    }

    @RequestMapping(path = "/heroes/{id}", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
    public ResponseEntity deleteHero(@PathVariable long id) {
        heroRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
