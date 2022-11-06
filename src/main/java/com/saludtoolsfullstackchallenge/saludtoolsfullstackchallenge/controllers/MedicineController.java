package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.controllers;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.MedicineDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.interfaces.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    MedicineService medicineService;

    @CrossOrigin
    @GetMapping(value = "/filter/rule", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicineDto>> filterMedicineValidPerPatient(@RequestParam(value = "patientId", required = true) Long patientId) throws BasicException {
        List<MedicineDto> medicineDtoPage = medicineService.filterMedicineValidPerPatient(patientId);
        return new ResponseEntity<>(medicineDtoPage , HttpStatus.OK);
    }
}
