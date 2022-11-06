package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.controllers;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionDto;
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
    public ResponseEntity<Page<PrescriptionDto>> filterPrescriptionByPatient(@RequestParam("page") int page,
                                                          @RequestParam(value = "patientId", required = true) Long patientId) throws BasicException {

        int size = 10;
        List<Sort.Order> listOrders = new ArrayList<Sort.Order>();
        listOrders.add(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(page, size);
        Page<PrescriptionDto> PrescriptionDtoPage = prescriptionService.findAllPrescriptionByPatientId(patientId, pageable);
        return new ResponseEntity<>(PrescriptionDtoPage , HttpStatus.OK);
    }
}
