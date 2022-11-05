package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class PatientDto {

    private Long id;
    private String name;
    private String lastName;
    private String birthDay;
    private Long genderId;

    public PatientDto() {
    }

    public PatientDto(Long id, String name, String lastName, ZonedDateTime birthDay, Long genderId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.genderId = genderId;
    }
}
