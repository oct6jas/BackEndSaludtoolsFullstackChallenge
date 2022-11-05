package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT patient FROM Patient patient WHERE patient.id = :patientId AND patient.deleteDate IS NULL")
    Patient getPatientById(@Param("patientId") Long patientId);

    @Modifying
    @Query("UPDATE  Patient patient SET patient.deleteDate = NOW() WHERE patient.id = :patientId")
    void deletePatientById(@Param("patientId") Long patientId);

    @Query("SELECT new com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto(patient.id, patient.deleteDate) FROM Patient patient WHERE patient.id = :patientId")
    BasicDeleteDto getPatientDelete(@Param("patientId") Long patientId);


    @Query("SELECT new com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientDto(p.id, p.name, p.lastName, "
            + " p.birthDay, p.genderId) FROM Patient p"
            + " WHERE (:searchText IS NULL OR p.name LIKE :textToSearch OR p.lastName LIKE :textToSearch) "
            + " AND (:genderId IS NULL OR p.genderId = :genderId) AND p.deleteDate IS NULL")
    public Page<PatientDto> getPatientBySearchTextAndGender(@Param(value = "textToSearch") String textToSearch, @Param(value = "searchText") Long searchText, @Param(value = "genderId") Long genderId, Pageable pag );

}
