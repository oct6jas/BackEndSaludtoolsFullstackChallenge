package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories;


import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    @Query("SELECT medicine FROM Medicine medicine WHERE medicine.id = :medicineId")
    Medicine getMedicineById(@Param("medicineId") Long medicineId);

}
