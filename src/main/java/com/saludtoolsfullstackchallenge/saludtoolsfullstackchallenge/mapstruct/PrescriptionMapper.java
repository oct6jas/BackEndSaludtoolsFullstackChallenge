package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionResponseDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Prescription;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PrescriptionMapper {

    PrescriptionDto prescriptionToPrescriptionDto(Prescription prescription);

    PrescriptionResponseDto prescriptionToPrescriptionResponseDto(Prescription prescription);

    @Mapping(ignore = true, target = "id")
    Prescription prescriptionDtoToPrescription(PrescriptionDto dto);

    @Mappings({
            @Mapping(ignore = true, target = "id"),
            @Mapping(ignore = true, target = "createDate"),
            @Mapping(ignore = true, target = "patientId")
    })
    public void toUpdate(PrescriptionDto dto, @MappingTarget Prescription target);

}
