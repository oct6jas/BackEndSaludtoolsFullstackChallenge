package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories;


import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    @Query("SELECT medicine FROM Medicine medicine WHERE medicine.id = :medicineId")
    Medicine getMedicineById(@Param("medicineId") Long medicineId);

    @Query("SELECT medicine FROM Medicine medicine WHERE (medicine.minimumDrinkingAge <= :patientAge OR medicine.minimumDrinkingAge IS NULL) "
            + "AND (medicine.maximumDrinkingAge >= :patientAge OR medicine.maximumDrinkingAge IS NULL ) "
            + "AND (medicine.exclusiveGenderUse = :genderId OR medicine.exclusiveGenderUse IS NULL)")
    List<Medicine> findMedicineValidPerPatient(@Param("patientAge") Long patientAge, @Param("genderId") Long genderId );

}
