package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.interfaces;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Prescription;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;


public interface PrescriptionService {

    Prescription create(PrescriptionDto dto) throws BasicException;

    Prescription update(PrescriptionDto dto) throws BasicException;

    BasicDeleteDto delete(PrescriptionDto dto) throws BasicException;

//    Page<PrescriptionDto> findAllBysearchTextAndGender(String textToSearch, Long genderId, Pageable pageable);
}
