package com.blu911.herorestapi.hero;

import com.blu911.herorestapi.HeroRestApiApplication;
import com.blu911.herorestapi.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = HeroRestApiApplication.class)
@ActiveProfiles("test")
class HeroControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void performGetHeroes() throws Exception {
        mockMvc.perform(get("/heroes")).andExpect(status().isOk());
    }

    @Test
    void performGetHero() throws Exception {
        mockMvc.perform(get("/heroes/1")).andExpect(status().isOk());
    }

    @Test
    void performPostHero() throws Exception {
        //given
        Hero batman = new Hero(1L, "Batman");
        //when
        MvcResult mvcResult = mockMvc.perform(post("/heroes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.objectToJason(batman))).andReturn();
        //then
        Hero resultHero = TestUtils.jsonToObject(mvcResult.getResponse().getContentAsString(), Hero.class);
        assertNotNull(resultHero);
        assertEquals(1L, resultHero.getId().longValue());
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void performPutHero() throws Exception {
        //given
        Hero hero = new Hero(1L, "Superman");
        //when
        mockMvc.perform(post("/heroes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.objectToJason(hero))).andReturn();

        hero.setName("Batman");
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put("/heroes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.objectToJason(hero))).andReturn();
        //then
        Hero resultHero = TestUtils.jsonToObject(mvcResult.getResponse().getContentAsString(), Hero.class);
        assertNotNull(resultHero);
        assertEquals(1L, resultHero.getId().longValue(), "Hero id is not matching expected value");
        assertEquals("Batman", resultHero.getName(), "Hero name is not matching expected value");
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    void performDeleteHero() throws Exception {
        //given
        Hero hero = new Hero(1L, "Superman");
        //when
        mockMvc.perform(post("/heroes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(TestUtils.objectToJason(hero))).andReturn();

        MvcResult mvcResult = mockMvc.perform(delete("/heroes/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn();
        //then
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
}