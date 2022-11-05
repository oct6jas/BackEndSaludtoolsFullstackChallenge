package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class PrescriptionDto {

    private Long id;
    private ZonedDateTime createDate;
    private Long patientId;
    private Long medicineId;

    public PrescriptionDto() {
    }
}
