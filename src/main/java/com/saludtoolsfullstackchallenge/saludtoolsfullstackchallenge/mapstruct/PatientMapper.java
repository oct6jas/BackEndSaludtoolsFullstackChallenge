package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientResponseDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Patient;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PatientMapper {

    @Mapping(source = "birthDay", target = "birthDay", dateFormat = "dd/MM/yyyy")
    PatientDto patientToPatientDto(Patient patient);

    @Mappings({
            @Mapping(ignore = true, target = "id"),
            @Mapping(ignore = true, target = "birthDay")
    })
    Patient patientDtoToPatient(PatientDto dto);

    @Mappings({
            @Mapping(ignore = true, target = "id"),
            @Mapping(ignore = true, target = "birthDay")
    })
    public void toUpdate(PatientDto dto, @MappingTarget Patient target);

    @Mapping(source = "birthDay", target = "birthDay", dateFormat = "dd/MM/yyyy")
    PatientResponseDto patientToPatientResponseDto(Patient patient);

}
