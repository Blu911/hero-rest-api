package com.blu911.herorestapi.hero;

import com.blu911.herorestapi.power.Power;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "heroes")
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Field cannot be empty")
    @Column(name = "name",unique = true)
    private String name;
    @ManyToOne
    private Power power;
    @Column(name = "hit_points")
    private int hitPoints;
    @Column(name = "mana_points")
    private int manaPoints;

    public Hero(@NotEmpty(message = "Field cannot be empty") String name) {
        this.name = name;
    }
    public Hero(Long id, @NotEmpty(message = "Field cannot be empty") String name) {
        this.name = name;
    }
}
