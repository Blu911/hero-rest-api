package com.blu911.herorestapi.hero;


import com.blu911.herorestapi.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HeroController.class)
public class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    HeroService heroService;

    @Test
    void getHeroShouldReturnExpectedHero() throws Exception {
        //given
        Hero batman = new Hero(1L,"Batman");
        when(heroService.findHeroById(1L)).thenReturn(batman);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/heroes/1")).andReturn();
        //then
        Hero resultHero = TestUtils.jsonToObject(mvcResult.getResponse().getContentAsString(), Hero.class);
        assertNotNull(resultHero);
        assertEquals(1L, resultHero.getId().longValue());
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        verify(heroService).findHeroById(1L);
    }

    @Test
    void getHeroesShouldReturnExpectedHeroList() throws Exception {
        //given
        Hero batman = new Hero(1L, "Batman");
        Hero superman = new Hero(2L, "Superman");
        Hero spiderman = new Hero(3L, "Spiderman");
        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.add(batman);
        heroes.add(superman);
        heroes.add(spiderman);
        when(heroService.findAll()).thenReturn(heroes);
        when(heroService.sortHeroesByIdASC(heroes)).thenReturn(heroes);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/heroes")).andReturn();
        //then
        @SuppressWarnings("unchecked")
        List<Hero> resultHero = TestUtils.jsonToList(mvcResult.getResponse().getContentAsString(), List.class);
        assertNotNull(resultHero);
        assertTrue(resultHero.size() == 3);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        verify(heroService).findAll();
    }

    @Test
    void postHeroShouldSaveSentHero() throws Exception {
        //given
        Hero batman = new Hero(1L,"Batman");
        when(heroService.save(any(Hero.class))).thenReturn(batman);
        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/heroes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.objectToJason(batman))).andReturn();
        //then
        Hero resultHero = TestUtils.jsonToObject(mvcResult.getResponse().getContentAsString(), Hero.class);
        assertNotNull(resultHero);
        assertEquals(1L, resultHero.getId().longValue());
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
        verify(heroService).save(any(Hero.class));
    }

    @Test
    void putShouldUpdateGivenHero() throws Exception {
        //given
        Hero batman = new Hero(1L, "Batman");
        when(heroService.save(batman)).thenReturn(batman);
        //when
        heroService.save(batman);
        batman.setName("Superman");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put("/heroes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.objectToJason(batman))).andReturn();
        //then
        Hero resultHero = TestUtils.jsonToObject(mvcResult.getResponse().getContentAsString(), Hero.class);
        assertNotNull(resultHero);
        assertEquals(1L, resultHero.getId().longValue());
        assertEquals("Superman", resultHero.getName());
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
    @Test
    void deleteShouldRemoveGivenHero() throws Exception {
        //given
        Hero superman = new Hero(1L, "Superman");
        when(heroService.findHeroById(1L)).thenReturn(superman);
        //when
        mockMvc.perform(post("/heroes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.objectToJason(superman))).andReturn();

        MvcResult mvcResult = mockMvc.perform(delete("/heroes/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        //then
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        verify(heroService, times(1)).deleteById(1L);
    }
}
