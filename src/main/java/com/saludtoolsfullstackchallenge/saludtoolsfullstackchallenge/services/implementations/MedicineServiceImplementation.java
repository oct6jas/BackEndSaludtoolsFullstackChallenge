package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.implementations;


import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.MedicineDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Medicine;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Patient;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct.MedicineMapper;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories.MedicineRepository;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.interfaces.MedicineService;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.utilities.UtilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service(value = "medicineService")
@Transactional
public class MedicineServiceImplementation implements MedicineService {

    @Autowired
    UtilitiesService utilitiesService;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    MedicineMapper medicineMapper;

    @Override
    public List<MedicineDto> filterMedicineValidPerPatient(Long patientId) throws BasicException {

        Patient patient = utilitiesService.validatePatient(patientId);
        Long patientAge = utilitiesService.agePatient(patient);
        List<Medicine> medicineList =medicineRepository.findMedicineValidPerPatient(patientAge, patient.getGenderId());
        List<MedicineDto> medicineDtoList = medicineMapper.medicineTomedicineDto(medicineList);
        return medicineDtoList;
    }
}
