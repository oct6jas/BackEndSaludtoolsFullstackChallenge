package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.interfaces;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.MedicineDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;


import java.util.List;

public interface MedicineService {

    List<MedicineDto> filterMedicineValidPerPatient(Long patientId) throws BasicException;
}
