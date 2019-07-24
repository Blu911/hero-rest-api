package com.blu911.herorestapi;

import com.blu911.herorestapi.config.H2JpaTestConfig;
import com.blu911.herorestapi.hero.HeroController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {HeroRestApiApplication.class, H2JpaTestConfig.class})
@ActiveProfiles("test")
public class HeroRestApiApplicationTests {

    @Test
    public void contextLoads() {
        assertNotNull(HeroController.class);
    }

}
