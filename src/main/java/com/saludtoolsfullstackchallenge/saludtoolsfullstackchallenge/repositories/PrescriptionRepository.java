package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionResponseDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Prescription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query("SELECT prescription FROM Prescription prescription WHERE prescription.id = :prescriptionId AND prescription.patientId = :patientId AND prescription.deleteDate IS NULL")
    Prescription getPrescriptionByIdAndPatientId(@Param("prescriptionId") Long prescriptionId, @Param("patientId") Long patientId);

    @Modifying
    @Query("UPDATE  Prescription prescription SET prescription.deleteDate = NOW() WHERE prescription.id = :prescriptionId")
    void deletePrescriptionById(@Param("prescriptionId") Long prescriptionId);


    @Query("SELECT new com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto(prescription.id, prescription.deleteDate) FROM Prescription prescription WHERE prescription.id = :prescriptionId")
    BasicDeleteDto getPrescriptionDelete(@Param("prescriptionId") Long prescriptionId);

    @Query("SELECT new com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PrescriptionResponseDto(prescription.id, prescription.createDate, prescription.patientId, prescription.medicineId) FROM Prescription prescription WHERE prescription.patientId = :patientId AND prescription.deleteDate IS NULL")
    Page<PrescriptionResponseDto> findAllPrescriptionByPatientId(@Param("patientId") Long patientId, Pageable pageable);

    @Query("SELECT prescription FROM Prescription prescription WHERE prescription.patientId = :patientId AND MONTH(prescription.createDate) = :month AND prescription.deleteDate IS NULL")
    List<Prescription> numberOfPrescriptionInMonth(@Param("patientId") Long patientId, @Param("month") int month);

    @Query("SELECT prescription FROM Prescription prescription WHERE prescription.medicineId = :medicineId AND prescription.patientId = :patientId AND MONTH(prescription.createDate) = :month AND prescription.deleteDate IS NULL ")
    List<Prescription> numberOfPrescriptionInMonthForMedicine(@Param("medicineId") Long medicineId, @Param("patientId") Long patientId, @Param("month") int month);

}
