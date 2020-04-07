package com.example.demo.repository;

import com.example.demo.exception.CustomMappingException;
import com.example.demo.exception.VehiculeNotFoundException;
import com.example.demo.model.Vehicule;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
public class VehiculeRepositoryTest {

    VehiculeRepository vehiculeRepository;
    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void setUp() {
        vehiculeRepository = new VehiculeRepository(em);
    }
    /*-------------------- saveVehicule --------------------*/
    @Test
    void saveVehicule_should_return_Vehicule() {
        try {
            Vehicule vehicule = new Vehicule();
            vehicule.setId(2);
            vehicule.setVehicleName("save");

            Vehicule vehicule1 = vehiculeRepository.saveVehicule(vehicule);

            assertNotNull(vehicule1);
            assertEquals(vehicule, vehicule1);
        } catch (CustomMappingException ex) {
            fail();
        }

    }

    @Test
    void saveQuiz_should_throw_CustomMappingException() {
        Vehicule vehicule = null;

        assertThrows(CustomMappingException.class, () -> vehiculeRepository.saveVehicule(vehicule));
    }


    /*-------------------- updateVehicule --------------------*/
    @Test
    void updateQuiz_should_return_Quiz() {
        try {
            Vehicule vehicule = new Vehicule();
            vehicule.setId(1);
            vehicule.setVehicleName("save");

            Vehicule vehicule1 = vehiculeRepository.updateVehicule(vehicule);

            assertNotNull(vehicule1);
            assertEquals(vehicule, vehicule1);
        } catch ( VehiculeNotFoundException ex) {
            fail();
        }

    }

    /*-------------------- DeleteVehicule --------------------*/

    @Test
    void deleteQuiz_should_delete_quiz(){
        try {
            vehiculeRepository.deleteVehicule(23);
            assertNull(vehiculeRepository.getById(23));
        } catch (VehiculeNotFoundException ignored) {
        }
    }

    @Test
    void deleteQuiz_should_throw_QuizNotFoundException() {
        assertThrows(VehiculeNotFoundException.class, () -> vehiculeRepository.deleteVehicule(1000));
    }


}
