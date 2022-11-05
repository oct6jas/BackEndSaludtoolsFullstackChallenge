package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.implementations;


import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Medicine;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Prescription;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct.PrescriptionMapper;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories.MedicineRepository;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories.PrescriptionRepository;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.interfaces.PrescriptionService;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.utilities.UtilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.util.Objects.isNull;

@Service(value = "prescriptionService")
@Transactional
public class PrescriptionServiceImplementation implements PrescriptionService {

    @Autowired
    PrescriptionMapper prescriptionMapper;

    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Autowired
    UtilitiesService utilitiesService;

    @Autowired
    MedicineRepository medicineRepository;

    @Override
    public Prescription create(PrescriptionDto dto) throws BasicException {
        dto.setCreateDate(ZonedDateTime.now(ZoneId.of("America/Bogota")));
        validateDto(dto);
        validateMedicine(dto.getMedicineId());
        Prescription prescription = prescriptionMapper.prescriptionDtoToPrescription(dto);
        prescription = prescriptionRepository.save(prescription);
        return prescription;
    }

    @Override
    public Prescription update(PrescriptionDto dto) throws BasicException {
        dto.setCreateDate(ZonedDateTime.now(ZoneId.of("America/Bogota")));
        validateDto(dto);
        validateMedicine(dto.getMedicineId());
        Prescription prescription = validatePrescription(dto.getId(), dto.getPatientId());
        prescriptionMapper.toUpdate(dto, prescription);
        prescription = prescriptionRepository.save(prescription);
        return prescription;
    }

    @Override
    public BasicDeleteDto delete(PrescriptionDto dto) throws BasicException {
        validatePrescription(dto.getId(), dto.getPatientId());
        prescriptionRepository.deletePrescriptionById(dto.getId());
        BasicDeleteDto prescriptionDeleteDto = prescriptionRepository.getPrescriptionDelete(dto.getId());
        return prescriptionDeleteDto;
    }

    private void validateDto(PrescriptionDto dto) throws BasicException {
        if(isNull(dto.getPatientId())){
            throw new BasicException(400, "El id del paciente no puede ser null");
        }
        utilitiesService.validatePatient(dto.getPatientId());

        if(isNull(dto.getCreateDate())){
            throw new BasicException(400, "La fecha de creacion no puede ser null");
        }
    }

    private Prescription validatePrescription(Long id, Long patientId) throws BasicException {

        if(isNull(id)){
            throw new BasicException(400, "el id de la prescripcion no puede ser null");
        }

        utilitiesService.validatePatient(patientId);

        Prescription prescriptionToUpdate = prescriptionRepository.getPrescriptionByIdAndPatientId(id, patientId);
        if(isNull(prescriptionToUpdate)){
            throw new BasicException(400, "No existe prescripcion con id: " + id + " Para ese paciente");
        }
        return prescriptionToUpdate;
    }

    private void validateMedicine(Long medicineId) throws BasicException {
        if(isNull(medicineId)){
            throw new BasicException(400, "El id del medicamento no puede ser null");
        }
        Medicine medicine = medicineRepository.getMedicineById(medicineId);

        if(isNull(medicine)){
            throw new BasicException(400, "No existe medicamento con id: " + medicineId);
        }

    }
}
