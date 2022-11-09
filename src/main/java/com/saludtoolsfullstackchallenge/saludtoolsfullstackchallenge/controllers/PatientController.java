package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.controllers;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientResponseDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Patient;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.mapstruct.PatientMapper;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.interfaces.PatientService;
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
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientMapper patientMapper;

    @Autowired
    PatientService patientService;

    @CrossOrigin
    @PostMapping(value = "/create", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto request) throws BasicException {
        Patient patient = patientService.create(request);
        PatientDto patientDto =patientMapper.patientToPatientDto(patient);
        return new ResponseEntity<>(patientDto , HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/update", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto request) throws BasicException {
        Patient patient = patientService.update(request);
        PatientDto patientDto =patientMapper.patientToPatientDto(patient);
        return new ResponseEntity<>(patientDto , HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping(value = "/delete", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasicDeleteDto> deletePatient(@RequestBody PatientDto request) throws BasicException {
        BasicDeleteDto patientDelete = patientService.delete(request);
        return new ResponseEntity<>(patientDelete , HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/filter", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PatientResponseDto>> filterPatient(@RequestParam("page") int page,
                                                                      @RequestParam("size") int size,
                                                                      @RequestParam(value = "textToSearch", required = false) String textToSearch,
                                                                      @RequestParam(value = "genderId", required = false) Long genderId) throws BasicException {

        List<Sort.Order> listOrders = new ArrayList<Sort.Order>();
        listOrders.add(new Sort.Order(Sort.Direction.DESC, "id"));
        Pageable pageable = PageRequest.of(page, size);
        Page<PatientResponseDto> patientDtoPage = patientService.findAllBysearchTextAndGender(textToSearch, genderId, pageable);
        return new ResponseEntity<>(patientDtoPage , HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/byid", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientResponseDto> getPatientById(@RequestParam(value = "patientId", required = true) Long patientId) throws BasicException {
        PatientResponseDto patientResponseDto = patientService.getPatientById(patientId);
        return new ResponseEntity<>(patientResponseDto , HttpStatus.OK);
    }
}
