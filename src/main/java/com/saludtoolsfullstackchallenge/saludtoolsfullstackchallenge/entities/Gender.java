package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities;


import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Entity
@Table(name = "genders")
@Getter
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "abbreviation")
    private String abbreviation;
}
