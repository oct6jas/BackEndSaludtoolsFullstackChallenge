package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.implementations;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Patient;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct.PatientMapper;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories.PatientRepository;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.interfaces.PatientService;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.utilities.UtilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@Service(value = "patientService")
@Transactional
public class PatientServiceImplementation implements PatientService {

    @Autowired
    PatientMapper patientMapper;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UtilitiesService utilitiesService;


    @Override
    public Patient create(PatientDto dto) throws BasicException {
        validateDto(dto);
        String birthDate = dto.getBirthDay().trim() + " " + "00:00";
        Patient patient = patientMapper.patientDtoToPatient(dto);
        patient.setBirthDay(utilitiesService.textDateToZonedDateTime(birthDate));
        patient = patientRepository.save(patient);
        return patient;
    }

    @Override
    public Patient update(PatientDto dto) throws BasicException {
        validateDto(dto);
        String birthDate = dto.getBirthDay().trim() + " " + "00:00";
        Patient patient = utilitiesService.validatePatient(dto.getId());
        patientMapper.toUpdate(dto, patient);
        patient.setBirthDay(utilitiesService.textDateToZonedDateTime(birthDate));
        patient = patientRepository.save(patient);
        return patient;
    }

    @Override
    public PatientDeleteDto delete(PatientDto dto) throws BasicException {
        utilitiesService.validatePatient(dto.getId());
        patientRepository.deletePatientById(dto.getId());
        PatientDeleteDto patientDeleteDto = patientRepository.getPatientDelete(dto.getId());
        return patientDeleteDto;
    }

    private void validateDto(PatientDto dto) throws BasicException {
        if (isNull(dto.getName()) || dto.getName().isEmpty()) {
            throw new BasicException(400, "El nombre del paciente es obligatorio");
        }
        if (dto.getName().length() > 100) {
            throw new BasicException(400, "El nombre debe ser menor a 100 caracteres");
        }
        if (isNull(dto.getLastName()) || dto.getLastName().isEmpty()) {
            throw new BasicException(400, "El Apellido del paciente es obligatorio");
        }
        if (dto.getLastName().length() > 100) {
            throw new BasicException(400, "El apellido debe ser menor a 100 caracteres");
        }
        if (isNull(dto.getBirthDay()) || dto.getBirthDay().isEmpty()) {
            throw new BasicException(400, "La fecha de nacimiento del paciente es obligatorio");
        }
        if (isNull(dto.getGenderId())) {
            throw new BasicException(400, "El genero del paciente es obligatorio");
        }
        if (!dto.getGenderId().equals(1L) && !dto.getGenderId().equals(2L) ) {
            throw new BasicException(400, "El genero seleccionado no es valido, por favor escoja entre femenino o masculino");
        }
    }

    @Override
    public Page<PatientDto> findAllBysearchTextAndGender(String textToSearch, Long genderId, Pageable pageable) {
        Long searchText = null;
        if (textToSearch != null && !textToSearch.isEmpty()) {
            textToSearch = "%"+textToSearch.trim().replaceAll("\\s", "%")+"%";
            searchText=0L;
        }
        Page<PatientDto> patientDtoPage = patientRepository.getPatientBySearchTextAndGender(textToSearch, searchText, genderId, pageable);
        return patientDtoPage;
    }
}
