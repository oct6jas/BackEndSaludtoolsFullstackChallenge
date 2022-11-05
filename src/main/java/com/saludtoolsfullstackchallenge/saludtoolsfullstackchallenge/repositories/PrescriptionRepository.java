package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query("SELECT prescription FROM Prescription prescription WHERE prescription.id = :prescriptionId AND prescription.patientId = :patientId")
    Prescription getPrescriptionByIdAndPatientId(@Param("prescriptionId") Long prescriptionId, @Param("patientId") Long patientId);
//
//    @Modifying
//    @Query("UPDATE  Patient patient SET patient.deleteDate = NOW() WHERE patient.id = :patientId")
//    void deletePatientById(@Param("patientId") Long patientId);
//
//    @Query("SELECT new com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientDeleteDto(patient.id, patient.deleteDate) FROM Patient patient WHERE patient.id = :patientId")
//    PatientDeleteDto getPatientDelete(@Param("patientId") Long patientId);
//
//
//    @Query("SELECT new com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientDto(p.id, p.name, p.lastName, "
//            + "p.genderId) FROM Patient p"
//            + " WHERE (:searchText IS NULL OR p.name LIKE :textToSearch OR p.lastName LIKE :textToSearch) "
//            + " AND (:genderId IS NULL OR p.genderId = :genderId) AND p.deleteDate IS NULL")
//    public Page<PatientDto> getPatientBySearchTextAndGender(@Param(value = "textToSearch") String textToSearch, @Param(value = "searchText") Long searchText, @Param(value = "genderId") Long genderId, Pageable pag );

}
