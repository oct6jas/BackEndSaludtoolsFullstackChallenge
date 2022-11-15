package com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.services.implementations;

import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.BasicDeleteDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.dto.PatientDto;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.entities.Patient;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.exceptions.BasicException;
import com.saludtoolsfullstackchallenge.saludtoolsfullstackchallenge.repositories.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.ZonedDateTime;

import static org.mockito.Mockito.*;


@SpringBootTest
class PatientServiceImplementationTest {

    @Autowired
    PatientServiceImplementation patientServiceImplementation;

    @MockBean
    PatientRepository patientRepository;

    PatientDto patientDto = new PatientDto();
    String oneHundred = "esto es un texto de mas de 100 caracteres de longitud para que falle la prueba de maximo caracteres por input";

    @BeforeEach
    public void newPatientDto(){
        patientDto.setName("Name");
        patientDto.setLastName("LastName");
        patientDto.setBirthDay("05/01/1955");
        patientDto.setGenderId(1L);
    }

    @Test
    void Create_All_OK() throws BasicException {
        //arrange
        Patient patientMock = new Patient();
        patientMock.setId(1L);
        patientMock.setName("Name");
        patientMock.setLastName("LastName");
        patientMock.setBirthDay(ZonedDateTime.now());
        patientMock.setGenderId(1L);
        doReturn(patientMock).when(patientRepository).save(Mockito.any(Patient.class));
        //act
        Patient patientResponse = patientServiceImplementation.create(patientDto);
        //assert
        Assertions.assertEquals(patientMock.getId(), patientResponse.getId());
        Assertions.assertEquals(patientMock.getName(), patientResponse.getName());
        Assertions.assertEquals(patientMock.getLastName(), patientResponse.getLastName());
        Assertions.assertEquals(patientMock.getBirthDay(), patientResponse.getBirthDay());
        Assertions.assertEquals(patientMock.getGenderId(), patientResponse.getGenderId());
    }

    @Test
    void Create_NameEmpty_ReturnBasicException() {
        //arrange
        patientDto.setName("");
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.create(patientDto) );
        //assert
        Assertions.assertEquals("El nombre del paciente es obligatorio", basicException.getMessage());
    }

    @Test
    void Create_NameNull_ReturnBasicException() {
        //arrange
        patientDto.setName(null);
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.create(patientDto) );
        //assert
        Assertions.assertEquals("El nombre del paciente es obligatorio", basicException.getMessage());
    }

    @Test
    void Create_NameOneHundredLength_ReturnBasicException() {
        //arrange
        patientDto.setName(oneHundred);
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.create(patientDto) );
        //assert
        Assertions.assertEquals("El nombre debe ser menor a 100 caracteres", basicException.getMessage());
    }

    @Test
    void Create_LastNameEmpty_ReturnBasicException() {
        //arrange
        patientDto.setLastName("");
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.create(patientDto) );
        //assert
        Assertions.assertEquals("El Apellido del paciente es obligatorio", basicException.getMessage());
    }

    @Test
    void Create_LastNameNull_ReturnBasicException() {
        //arrange
        patientDto.setLastName(null);
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.create(patientDto) );
        //assert
        Assertions.assertEquals("El Apellido del paciente es obligatorio", basicException.getMessage());
    }

    @Test
    void Create_LastOneHundredLength_ReturnBasicException() {
        //arrange
        patientDto.setLastName(oneHundred);
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.create(patientDto) );
        //assert
        Assertions.assertEquals("El apellido debe ser menor a 100 caracteres", basicException.getMessage());
    }

    @Test
    void Create_BirthDateEmpty_ReturnBasicException() {
        //arrange
        patientDto.setBirthDay("");
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.create(patientDto) );
        //assert
        Assertions.assertEquals("La fecha de nacimiento del paciente es obligatorio", basicException.getMessage());
    }

    @Test
    void Create_BirthDateNull_ReturnBasicException() {
        //arrange
        patientDto.setBirthDay(null);
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.create(patientDto) );
        //assert
        Assertions.assertEquals("La fecha de nacimiento del paciente es obligatorio", basicException.getMessage());
    }

    @Test
    void Create_GenderIdNull_ReturnBasicException() {
        //arrange
        patientDto.setGenderId(null);
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.create(patientDto) );
        //assert
        Assertions.assertEquals("El genero del paciente es obligatorio", basicException.getMessage());
    }

    @Test
    void Create_GenderIdNotOneNotTwo_ReturnBasicException() {
        //arrange
        patientDto.setGenderId(3L);
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.create(patientDto) );
        //assert
        Assertions.assertEquals("El genero seleccionado no es valido, por favor escoja entre femenino o masculino", basicException.getMessage());
    }

    @Test
    void update() throws BasicException {
        //arrange
        patientDto.setId(1L);

        Patient oldPatientMock = new Patient();
        oldPatientMock.setId(1L);
        oldPatientMock.setName("Name");
        oldPatientMock.setLastName("LastName");
        oldPatientMock.setBirthDay(ZonedDateTime.now());
        oldPatientMock.setGenderId(1L);

        Patient updatePatientMock = new Patient();
        updatePatientMock.setId(1L);
        updatePatientMock.setName("NameUpdate");
        updatePatientMock.setLastName("LastNameUpdate");
        updatePatientMock.setBirthDay(ZonedDateTime.now());
        updatePatientMock.setGenderId(2L);

        when(patientRepository.getPatientById(patientDto.getId())).thenReturn(oldPatientMock);
        doReturn(updatePatientMock).when(patientRepository).save(Mockito.any(Patient.class));
        //act
        Patient patientResponse = patientServiceImplementation.update(patientDto);
        //assert
        Assertions.assertEquals(updatePatientMock.getId(), patientResponse.getId());
        Assertions.assertEquals(updatePatientMock.getName(), patientResponse.getName());
        Assertions.assertEquals(updatePatientMock.getLastName(), patientResponse.getLastName());
        Assertions.assertEquals(updatePatientMock.getBirthDay(), patientResponse.getBirthDay());
        Assertions.assertEquals(updatePatientMock.getGenderId(), patientResponse.getGenderId());
    }

    @Test
    void delete_All_OK() throws BasicException {
        //arrange
        patientDto.setId(1L);

        Patient patientToDelete = new Patient();

        BasicDeleteDto deletePatientMock = new BasicDeleteDto();
        deletePatientMock.setId(1L);
        deletePatientMock.setDeleteDate(ZonedDateTime.now());

        when(patientRepository.getPatientById(patientDto.getId())).thenReturn(patientToDelete);
        doNothing().when(patientRepository).deletePatientById(Mockito.any(Long.class));
        when(patientRepository.getPatientDelete(patientDto.getId())).thenReturn(deletePatientMock);
        //act
        BasicDeleteDto patientDeleteResponse = patientServiceImplementation.delete(patientDto);
        //assert
        Assertions.assertEquals(deletePatientMock.getId(), patientDeleteResponse.getId());
        Assertions.assertEquals(deletePatientMock.getDeleteDate(), patientDeleteResponse.getDeleteDate());
    }

    @Test
    void delete_PatientDtoIdNull_ReturnBasicException() throws BasicException {
        //arrange
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.delete(patientDto) );
        //assert
        Assertions.assertEquals("El id del paciente no puede ser null", basicException.getMessage());
    }

    @Test
    void delete_PatientDoesNotExist_ReturnBasicException() throws BasicException {
        //arrange
        patientDto.setId(1L);
        when(patientRepository.getPatientById(patientDto.getId())).thenReturn(null);
        //act
        BasicException basicException = Assertions.assertThrows(BasicException.class, () -> patientServiceImplementation.delete(patientDto) );
        //assert
        Assertions.assertEquals("No existe paciente con id: " + patientDto.getId(), basicException.getMessage());
    }

    @Test
    void findAllBysearchTextAndGender() {
    }
}