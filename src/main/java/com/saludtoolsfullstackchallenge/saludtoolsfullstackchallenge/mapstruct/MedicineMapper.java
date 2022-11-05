package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.MedicineDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Medicine;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicineMapper {

    MedicineDto medicineTomedicineDto(Medicine medicine);

}
