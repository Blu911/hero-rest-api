package com.blu911.herorestapi.hero;

import com.blu911.herorestapi.HeroRestApiApplication;
import com.blu911.herorestapi.config.H2JpaTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {HeroRestApiApplication.class, H2JpaTestConfig.class})
@ActiveProfiles("test")
class HeroServiceImplTest {

    private HeroRepository heroRepository;
    private HeroService heroService;

    @BeforeEach
    void setUp() {
        heroRepository = Mockito.mock(HeroRepository.class);
        heroService = new HeroServiceImpl(heroRepository);
    }

    @Test
    void savedHeroShouldReturnHeroObject() {
        //given
        Hero batman = new Hero(1L, "Batman");
        when(heroRepository.save(any(Hero.class))).then(returnsFirstArg());
        //when
        Hero savedHero = heroService.save(batman);
        //then
        assertThat(savedHero).hasNoNullFieldsOrProperties();
        assertTrue(savedHero.getName().equals(batman.getName()));
    }

    @Test
    void updateHeroShouldReturnChangedHero() {
        //given
        Hero hero = new Hero(1L, "Spider-man");
        when(heroRepository.save(any(Hero.class))).then(returnsFirstArg());
        //when
        hero.setName("Batman");
        Hero updatedHero = heroService.save(hero);
        //then
        assertThat(updatedHero).hasNoNullFieldsOrProperties();
        assertTrue(updatedHero.getName().equals("Batman"));
        assertTrue(updatedHero.getId() == 1L);
    }

    @Test
    void findHeroByIdShouldReturnExpectedHero() {
        //given
        Hero hero = new Hero(1L, "Spider-man");
        when(heroRepository.save(any(Hero.class))).then(returnsFirstArg());
        when(heroRepository.findHeroById(1L)).thenReturn(hero);
        //when
        heroService.save(hero);
        Hero heroById = heroService.findHeroById(1L);
        //then
        assertTrue(heroById.getName().equals("Spider-man"));
        assertTrue(heroById.getId() == 1L);
        assertNull(heroService.findHeroById(2L));
    }

    @Test
    void findAllHeroesShouldReturnExpectedHeroList() {
        //given
        Hero spiderman = new Hero(1L, "Spider-man");
        Hero batman = new Hero(2L, "Batman");
        Hero superman = new Hero(3L, "Superman");
        given(heroRepository.findAll()).willReturn(asList(spiderman, batman, superman));

        //when
        List<Hero> heroList = heroService.findAll();
        //then
        assertTrue(heroList.size() == 3);
        assertTrue(heroList.contains(superman));
    }
}