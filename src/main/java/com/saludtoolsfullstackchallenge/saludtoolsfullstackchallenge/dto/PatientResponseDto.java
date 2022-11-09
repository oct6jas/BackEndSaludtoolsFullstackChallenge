package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class PatientResponseDto extends PatientDto {

    private Long age;
    private String gender;

    public PatientResponseDto(Long id, String name, String lastName, ZonedDateTime birthDay, Long genderId) {
        super(id, name, lastName, birthDay, genderId);
    }
}
