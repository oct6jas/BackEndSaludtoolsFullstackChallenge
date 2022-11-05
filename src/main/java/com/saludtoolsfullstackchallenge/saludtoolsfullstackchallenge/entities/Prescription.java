package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities;


import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Entity
@Table(name = "prescriptions")
@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "medicine_id")
    private Long medicineId;

    @Column(name = "delete_date")
    private ZonedDateTime deleteDate;
}
