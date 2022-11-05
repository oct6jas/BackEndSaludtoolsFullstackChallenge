package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Prescription;
import org.mapstruct.*;

import java.time.ZonedDateTime;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PrescriptionMapper {

    PrescriptionDto prescriptionToPrescriptionDto(Prescription prescription);

    @Mapping(ignore = true, target = "id")
    Prescription prescriptionDtoToPrescription(PrescriptionDto dto);

    @Mapping(ignore = true, target = "id")
    public void toUpdate(PrescriptionDto dto, @MappingTarget Prescription target);

}
