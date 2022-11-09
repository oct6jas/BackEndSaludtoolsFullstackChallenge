package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.interfaces;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientResponseDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Patient;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {

    Patient create(PatientDto dto) throws BasicException;

    Patient update(PatientDto dto) throws BasicException;

    BasicDeleteDto delete(PatientDto dto) throws BasicException;

    Page<PatientResponseDto> findAllBysearchTextAndGender(String textToSearch, Long genderId, Pageable pageable) throws BasicException;

    PatientResponseDto getPatientById(Long patientId) throws BasicException;
}
