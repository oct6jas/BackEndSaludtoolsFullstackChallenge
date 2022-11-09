package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.controllers;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientResponseDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionResponseDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Prescription;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct.PrescriptionMapper;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.interfaces.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    PrescriptionMapper prescriptionMapper;

    @Autowired
    PrescriptionService prescriptionService;


    @CrossOrigin
    @PostMapping(value = "/create", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrescriptionDto> createPrescription(@RequestBody PrescriptionDto request) throws BasicException {
        Prescription prescription = prescriptionService.create(request);
        PrescriptionDto prescriptionDto = prescriptionMapper.prescriptionToPrescriptionDto(prescription);
        return new ResponseEntity<>(prescriptionDto , HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/update", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrescriptionDto> updatePrescription(@RequestBody PrescriptionDto request) throws BasicException {
        Prescription Prescription = prescriptionService.update(request);
        PrescriptionDto PrescriptionDto = prescriptionMapper.prescriptionToPrescriptionDto(Prescription);
        return new ResponseEntity<>(PrescriptionDto , HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/delete", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasicDeleteDto> deletePrescription(@RequestBody PrescriptionDto request) throws BasicException {
        BasicDeleteDto PrescriptionDelete = prescriptionService.delete(request);
        return new ResponseEntity<>(PrescriptionDelete , HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/filter/patient", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PrescriptionResponseDto>> filterPrescriptionByPatient(@RequestParam("page") int page,
                                                                                     @RequestParam(value = "patientId", required = true) Long patientId) throws BasicException {

        int size = 10;
        List<Sort.Order> listOrders = new ArrayList<Sort.Order>();
        listOrders.add(new Sort.Order(Sort.Direction.DESC, "createDate"));
        Pageable pageable = PageRequest.of(page, size, Sort.by(listOrders));
        Page<PrescriptionResponseDto> PrescriptionDtoPage = prescriptionService.findAllPrescriptionByPatientId(patientId, pageable);
        return new ResponseEntity<>(PrescriptionDtoPage , HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/byid", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PrescriptionResponseDto> getPrescriptionById(@RequestParam(value = "patientId", required = true) Long patientId, @RequestParam(value = "prescriptionId", required = true) Long prescriptionId) throws BasicException {
        PrescriptionResponseDto prescriptionResponseDto = prescriptionService.getPrescriptionById(patientId, prescriptionId);
        return new ResponseEntity<>(prescriptionResponseDto , HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/create/validate", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createValidateForMonth(@RequestParam(value = "patientId", required = true) Long patientId) throws BasicException {

        Boolean canCreate = prescriptionService.createValidateForMonth(patientId);
        return new ResponseEntity<>(canCreate , HttpStatus.OK);
    }


}
