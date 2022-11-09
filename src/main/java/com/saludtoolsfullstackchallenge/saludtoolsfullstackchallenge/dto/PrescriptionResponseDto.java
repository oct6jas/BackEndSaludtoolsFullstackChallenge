package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class PrescriptionResponseDto extends PrescriptionDto {

    private String medicineName;

    public PrescriptionResponseDto(Long id, ZonedDateTime createDate, Long patientId, Long medicineId) {
        super(id, createDate, patientId, medicineId);
    }
}
