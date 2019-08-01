package com.blu911.herorestapi.hero;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class HeroController {

    private final HeroService heroService;

    /*This method is used to generate example heroes and insert them into the database
    for testing phase
    TODO: Remove this method after development completed
    */
    @PostConstruct
    public void generateFakeHeroes() {
        Set<Hero> heroSet = new HashSet<>();

        while (heroSet.size() < 10) {
            heroSet.add(heroService.generateHero());
        }
        heroService.saveAll(heroSet);
    }

    @RequestMapping(path = "/heroes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity showAllHeroes() {
        List<Hero> heroes = heroService.findAll();
        List<Hero> heroesById = heroService.sortHeroesByIdASC(heroes);
        return ResponseEntity.ok(heroesById);
    }

    @RequestMapping(path = "/heroes/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity showHero(@PathVariable Long id) {
        Hero hero = heroService.findHeroById(id);
        return ResponseEntity.ok(hero);
    }

    @RequestMapping(path = "/heroes", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public ResponseEntity updateHero(@Valid @RequestBody Hero hero) {
        Hero updatedHero = heroService.save(hero);
        return ResponseEntity.ok(updatedHero);
    }

    @RequestMapping(path = "/heroes", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity addHero(@Valid @RequestBody Hero hero) {
        Hero addedHero = heroService.save(hero);
        return ResponseEntity.status(201).body(addedHero);
    }

    @RequestMapping(path = "/heroes/{id}", method = RequestMethod.DELETE, produces = "application/json", consumes = "application/json")
    public ResponseEntity deleteHero(@PathVariable Long id) {
        heroService.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
