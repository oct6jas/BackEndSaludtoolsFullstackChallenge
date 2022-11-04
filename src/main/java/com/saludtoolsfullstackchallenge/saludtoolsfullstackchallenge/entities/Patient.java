package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Entity
@Table(name = "patients")
@Data
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_day")
    private ZonedDateTime birthDay;

    @Column(name = "gender_id")
    private Long genderId;

    @Column(name = "delete_date")
    private ZonedDateTime deleteDate;
}
