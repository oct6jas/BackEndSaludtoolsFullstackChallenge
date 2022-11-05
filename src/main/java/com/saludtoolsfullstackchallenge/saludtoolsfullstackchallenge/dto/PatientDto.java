package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto;

import lombok.Data;

@Data
public class PatientDto {

    private Long id;
    private String name;
    private String lastName;
    private String birthDay;
    private Long genderId;

    public PatientDto() {
    }

    public PatientDto(Long id, String name, String lastName, Long genderId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.genderId = genderId;
    }
}
