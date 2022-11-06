package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.MedicineDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Medicine;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicineMapper {

    List<MedicineDto> medicineTomedicineDto(List<Medicine> medicines);

}
