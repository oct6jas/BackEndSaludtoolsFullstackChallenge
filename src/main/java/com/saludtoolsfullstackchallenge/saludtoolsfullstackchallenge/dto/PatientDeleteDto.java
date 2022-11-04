package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class PatientDeleteDto {

    private Long id;
    private ZonedDateTime deleteDate;

    public PatientDeleteDto() {
    }

    public PatientDeleteDto(Long id, ZonedDateTime delete_date) {
        this.id = id;
        this.deleteDate = delete_date;
    }
}
