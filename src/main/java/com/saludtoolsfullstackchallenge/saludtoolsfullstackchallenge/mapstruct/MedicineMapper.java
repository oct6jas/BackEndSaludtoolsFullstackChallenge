package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.MedicineDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Medicine;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Patient;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MedicineMapper {

    MedicineDto medicineTomedicineDto(Medicine medicine);

}
