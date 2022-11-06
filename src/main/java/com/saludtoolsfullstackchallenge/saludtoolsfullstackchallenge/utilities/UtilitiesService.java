package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.utilities;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Patient;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static java.util.Objects.isNull;

@Service(value = "utilitiesService")
public class UtilitiesService {

    @Autowired
    PatientRepository patientRepository;

    public ZonedDateTime textDateToZonedDateTime(String date) throws BasicException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                    .withZone(ZoneId.of("America/Bogota"));
            return ZonedDateTime.parse(date, dtf);
        } catch (Exception e) {
            throw new BasicException( 400, "la fecha no cumple con el formato dd/MM/yyyy, por favor valide la informacion");
        }
    }

    public Patient validatePatient(Long patientId) throws BasicException {
        if(isNull(patientId)){
            throw new BasicException(400, "El id del paciente no puede ser null");
        }

        Patient patient = patientRepository.getPatientById(patientId);

        if(isNull(patient)){
            throw new BasicException(400, "No existe paciente con id: " + patientId);
        }

        return patient;
    }

    public Long agePatient(Patient patient) throws BasicException {
        ZonedDateTime birthDayPatient = patient.getBirthDay();
        Long age = ChronoUnit.YEARS.between(birthDayPatient, ZonedDateTime.now());
        return age;
    }
}
