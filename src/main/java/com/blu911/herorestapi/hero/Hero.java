package com.blu911.herorestapi.hero;

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

    public Hero(@NotEmpty(message = "Field cannot be empty") String name) {
        this.name = name;
    }
}
