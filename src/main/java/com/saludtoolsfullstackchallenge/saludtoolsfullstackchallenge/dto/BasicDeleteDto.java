package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class BasicDeleteDto {

    private Long id;
    private ZonedDateTime deleteDate;

    public BasicDeleteDto() {
    }

    public BasicDeleteDto(Long id, ZonedDateTime delete_date) {
        this.id = id;
        this.deleteDate = delete_date;
    }
}
