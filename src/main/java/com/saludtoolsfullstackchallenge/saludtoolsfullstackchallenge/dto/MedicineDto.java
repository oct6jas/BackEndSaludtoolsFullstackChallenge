package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
public class MedicineDto {

    private Long id;
    private String name;
    private Long minimumDrinkingAge;
    private Long maximumDrinkingAge;
    private Long exclusiveGenderUse;
}
