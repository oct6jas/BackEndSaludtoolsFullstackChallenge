package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class PrescriptionDto {

    private Long id;
    private String createDate;
    private Long patientId;
    private Long medicineId;

    public PrescriptionDto() {
    }

    public PrescriptionDto(Long id, ZonedDateTime createDate, Long patientId, Long medicineId) {
        this.id = id;
        this.createDate = createDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.patientId = patientId;
        this.medicineId = medicineId;
    }
}
