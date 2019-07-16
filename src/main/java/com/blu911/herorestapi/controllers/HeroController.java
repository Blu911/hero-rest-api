package com.blu911.herorestapi.controllers;

import com.blu911.herorestapi.model.Hero;
import com.blu911.herorestapi.repository.HeroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HeroController {

    private final HeroRepository heroRepository;

    public HeroController(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @RequestMapping(path = "/heroes", method = RequestMethod.GET)
    public ResponseEntity<List<Hero>> showAllHeroes() {
        List<Hero> heroes = heroRepository.findAll();
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @RequestMapping(path = "/heroes/{id}", method = RequestMethod.GET)
    public ResponseEntity<Hero> showHero(@PathVariable long id) {
        Hero hero = heroRepository.findById(id);
        return new ResponseEntity<>(hero, HttpStatus.OK);
    }

    @RequestMapping(path = "/heroes", method = RequestMethod.PUT)
    public ResponseEntity updateHero(@RequestBody Hero hero) {
        Hero updatedHero = heroRepository.save(hero);
        return ResponseEntity.ok().body(updatedHero);
    }

    @RequestMapping(path = "/heroes", method = RequestMethod.POST)
    public ResponseEntity addHero(@RequestBody Hero hero) {
        Hero savedHero = heroRepository.save(hero);
        return ResponseEntity.ok().body(savedHero);
    }

    @RequestMapping(path = "/heroes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteHero(@PathVariable long id) {
        heroRepository.deleteById(id);
        return ResponseEntity.ok().body(id);
    }
}
