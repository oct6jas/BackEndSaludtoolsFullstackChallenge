package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto;

import lombok.Data;

@Data
public class MedicineDto {

    private Long id;
    private String name;
    private Long minimumDrinkingAge;
    private Long maximumDrinkingAge;
    private Long exclusiveGenderUse;
}
