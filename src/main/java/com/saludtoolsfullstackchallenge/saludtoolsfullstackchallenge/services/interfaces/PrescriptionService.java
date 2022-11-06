package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.interfaces;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Prescription;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PrescriptionService {

    Prescription create(PrescriptionDto dto) throws BasicException;

    Prescription update(PrescriptionDto dto) throws BasicException;

    BasicDeleteDto delete(PrescriptionDto dto) throws BasicException;

    Page<PrescriptionDto> findAllPrescriptionByPatientId(Long patientId, Pageable pageable);
}
