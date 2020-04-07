package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.exception.CustomGlobalExceptionHandler;
import com.example.demo.model.Vehicule;
import com.example.demo.service.VehiculeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.Test;
public class VehiculeControllerTest {

    private MockMvc mockMvc;
    private VehiculeService vehiculeService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void beforeEach() {
        vehiculeService = mock(VehiculeService.class);

        VehiculeController controller = new VehiculeController(vehiculeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(CustomGlobalExceptionHandler.class).build();
        objectMapper = new ObjectMapper();

    }

    /*--------------------post Vehicule--------------------*/
    @Test
    void createVehicule_should_update() throws Exception {


        Vehicule vehicule = new Vehicule();
        vehicule.setId(1);
        vehicule.setVehicleName("url");


        when(vehiculeService.postVehicule(vehicule)).thenReturn(vehicule);

        mockMvc.perform(post("/vehicule/v1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(vehicule)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        verify(vehiculeService, times(1)).postVehicule(vehicule);
    }

    @Test
    void editVehicule_should_update_vehicule() throws Exception {

        Vehicule vehicule = new Vehicule();
        vehicule.setId(1);
        vehicule.setVehicleName("url");


        when(vehiculeService.updateVehicule(vehicule)).thenReturn(vehicule);

        String result = mockMvc.perform(put("/vehicule/v1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(vehicule)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();


        verify(vehiculeService, times(1)).updateVehicule(vehicule);
        assertThat(vehicule).isEqualToComparingFieldByField(vehicule);
    }

    @Test
    void delete_vehicule_should_return_204() throws Exception {

        doNothing().when(vehiculeService).deleteVehicule(any());

        mockMvc.perform(delete("/vehicule/v1", 0))
                .andExpect(status().isNoContent());

        verify(vehiculeService, times(1)).deleteVehicule(any());
        verifyNoMoreInteractions(vehiculeService);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
