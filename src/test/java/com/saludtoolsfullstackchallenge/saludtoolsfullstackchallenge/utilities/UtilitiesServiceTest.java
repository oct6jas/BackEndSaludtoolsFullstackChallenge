package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.utilities;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UtilitiesServiceTest {

    @Autowired
    UtilitiesService utilitiesService;

    @Test
    void textDateToZonedDateTime_All_OK() throws BasicException {
        //arrange
        String birthDate = "05/01/1955 00:00";
        ZonedDateTime birthDateMock = ZonedDateTime.of(1955, 01,05,0,0,0,0, ZoneId.of("America/Bogota"));
        //act
        ZonedDateTime birthDateResponse = utilitiesService.textDateToZonedDateTime(birthDate);
        //assert
        Assertions.assertEquals(birthDateMock, birthDateResponse);
    }

    @Test
    void textDateToZonedDateTime_BirthDateBad_OK() throws BasicException {
        //arrange
        String birthDate = "05/99/1955 00:00";
        ZonedDateTime birthDateMock = ZonedDateTime.of(1955, 01,05,0,0,0,0, ZoneId.of("America/Bogota"));
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> utilitiesService.textDateToZonedDateTime(birthDate) );
        //assert
        Assertions.assertEquals("la fecha no cumple con el formato dd/MM/yyyy, por favor valide la informacion", basicException.getMessage());
    }
}