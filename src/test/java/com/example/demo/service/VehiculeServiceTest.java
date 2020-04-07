package com.example.demo.service;

import com.example.demo.exception.CustomMappingException;
import com.example.demo.exception.VehiculeNotFoundException;
import com.example.demo.model.Vehicule;
import com.example.demo.repository.VehiculeRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;


public class VehiculeServiceTest {

    private VehiculeRepository vehiculeRepository;
    private VehiculeService vehiculeService;

    @BeforeEach
    void beforeEach() {
        vehiculeRepository = mock(VehiculeRepository.class);
        vehiculeService = new VehiculeService(vehiculeRepository);
    }

    @Test
    void postQuizSkill_should_return_Quiz() throws CustomMappingException, CustomMappingException {

        Vehicule vehicule = new Vehicule();
        vehicule.setId(1);

        when(vehiculeRepository.saveVehicule(any())).thenReturn(vehicule);

        vehiculeService.postVehicule(vehicule);

        verify(vehiculeRepository, times(1)).saveVehicule(vehicule);

    }

    /*--------------------delete vehicule-------------------*/

    @Test
    void deleteQuiz_should_call_repository_one_time() {
        Integer id = 23;

        try {
            vehiculeService.deleteVehicule(id);
            verify(vehiculeRepository, times(1)).deleteVehicule(id);
        } catch (VehiculeNotFoundException e) {
            fail();
        }
    }

    @Test
    void putQuizSkill_should_return_Quiz() throws VehiculeNotFoundException {

        Vehicule vehicule = new Vehicule();
        vehicule.setId(1);

        when(vehiculeRepository.updateVehicule(any())).thenReturn(vehicule);

        vehiculeRepository.updateVehicule(vehicule);

        verify(vehiculeRepository, times(1)).updateVehicule(vehicule);

    }


    /*--------------------get by id--------------------*/

    @Test
    void getVehiculeById_should_call_repository() throws VehiculeNotFoundException {
        Integer id = 1;
        when(vehiculeRepository.getById(id)).thenReturn(new Vehicule());

        vehiculeService.getById(id);

        verify(vehiculeRepository, times(1)).getById(id);
        verifyNoMoreInteractions(vehiculeRepository);
    }

}
