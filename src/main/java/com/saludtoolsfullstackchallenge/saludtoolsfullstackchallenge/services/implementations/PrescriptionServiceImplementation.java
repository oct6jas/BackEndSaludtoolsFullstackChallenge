package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.implementations;


import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionResponseDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Medicine;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Prescription;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct.PrescriptionMapper;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories.MedicineRepository;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories.PrescriptionRepository;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.interfaces.PrescriptionService;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.utilities.UtilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
        validateDto(dto);
        Prescription prescription = prescriptionMapper.prescriptionDtoToPrescription(dto);
        prescription.setCreateDate(ZonedDateTime.now(ZoneId.of("America/Bogota")));
        prescription = prescriptionRepository.save(prescription);
        return prescription;
    }

    @Override
    public Prescription update(PrescriptionDto dto) throws BasicException {
        validateDto(dto);
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

        utilitiesService.validatePatient(dto.getPatientId());

        validateMedicine(dto.getMedicineId(), dto.getPatientId());
    }

    private Prescription validatePrescription(Long id, Long patientId) throws BasicException {

        if(isNull(id)){
            throw new BasicException(400, "el id de la prescripcion no puede ser null");
        }

        utilitiesService.validatePatient(patientId);

        Prescription prescription = prescriptionRepository.getPrescriptionByIdAndPatientId(id, patientId);
        if(isNull(prescription)){
            throw new BasicException(400, "No existe prescripcion con id: " + id + " Para ese paciente");
        }
        return prescription;
    }

    private void validateMedicine(Long medicineId, Long patientId) throws BasicException {
        if(isNull(medicineId)){
            throw new BasicException(400, "El id del medicamento no puede ser null");
        }

        Medicine medicine = medicineRepository.getMedicineById(medicineId);
        if(isNull(medicine)){
            throw new BasicException(400, "No existe medicamento con id: " + medicineId);
        }

        int month = ZonedDateTime.now().getMonthValue();
        List<Prescription> medicineForMonth = prescriptionRepository.numberOfPrescriptionInMonthForMedicine(medicineId, patientId, month);
        if(medicineForMonth.size() > 0){
            throw new BasicException(400, "Este medicamento ya fue prescito este mes. No se puede prescribir el mismo medicamento dentro del mismo mes al mismo paciente");
        }
    }

    @Override
    public Page<PrescriptionResponseDto> findAllPrescriptionByPatientId(Long patientId, Pageable pageable) {
        Page<PrescriptionResponseDto> prescriptionDtoPage = prescriptionRepository.findAllPrescriptionByPatientId(patientId, pageable);
        for(PrescriptionResponseDto prescriptionResponseDto: prescriptionDtoPage) {
            getMedicineName(prescriptionResponseDto);
        }
        return prescriptionDtoPage;
    }

    private void getMedicineName(PrescriptionResponseDto prescriptionResponseDto) {
            Medicine medicine = medicineRepository.getMedicineById(prescriptionResponseDto.getMedicineId());
            prescriptionResponseDto.setMedicineName(medicine.getName());
    }

    @Override
    public Boolean createValidateForMonth(Long patientId) {
        int month = ZonedDateTime.now().getMonthValue();
        List<Prescription> numberOfPrescriptionInMonth = prescriptionRepository.numberOfPrescriptionInMonth(patientId, month);
        if(numberOfPrescriptionInMonth.size() >= 3){
            return false;
        }
        return true;
    }

    @Override
    public PrescriptionResponseDto getPrescriptionById(Long patientId, Long prescriptionId) throws BasicException {
        Prescription prescription = validatePrescription(prescriptionId, patientId);
        PrescriptionResponseDto prescriptionResponseDto = prescriptionMapper.prescriptionToPrescriptionResponseDto(prescription);
        getMedicineName(prescriptionResponseDto);
        return prescriptionResponseDto;
    }
}
