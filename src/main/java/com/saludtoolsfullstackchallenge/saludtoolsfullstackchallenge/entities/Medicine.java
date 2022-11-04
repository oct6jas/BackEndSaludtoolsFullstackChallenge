package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities;


import lombok.Getter;

import javax.persistence.*;


@Entity
@Table(name = "medicines")
@Getter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "minimum_drinking_age")
    private Long minimumDrinkingAge;

    @Column(name = "maximum_drinking_age")
    private Long maximumDrinkingAge;

    @Column(name = "exclusive_gender_use")
    private Long exclusiveGenderUse;
}
