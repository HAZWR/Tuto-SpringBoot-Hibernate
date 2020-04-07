package com.example.demo.controller;

import com.example.demo.exception.CustomMappingException;
import com.example.demo.exception.VehiculeNotFoundException;
import com.example.demo.model.Vehicule;

import org.slf4j.Logger;

import com.example.demo.service.VehiculeService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicule/v1")
public class VehiculeController{

    private static final Logger logger = LoggerFactory.getLogger(VehiculeController.class);
    private VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    /**
     * Ajoute une url vers un quiz.
     *
     * @param vehicule le nouveau {@link Vehicule} à ajouter
     */
    @PostMapping
    public ResponseEntity<Vehicule> createVehicule(@RequestBody Vehicule vehicule)
            throws CustomMappingException {
        Vehicule vh=vehiculeService.postVehicule(vehicule);

        if (vehicule!=null) {
            logger.debug("An empty quiz cannot be mapped : ({}, {})", vh);
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(vh);
    }

    /**
     * Modifie un quiz.
     *
     * @param vehicule le {@link Vehicule} à modifier
     */
    @PutMapping
    public ResponseEntity<Vehicule> editVehicule(@RequestBody Vehicule vehicule)
            throws VehiculeNotFoundException {

        Vehicule vh = vehiculeService.updateVehicule(vehicule);

        if (vh!=null) {
            logger.debug("An empty quiz cannot be mapped : ({}, {})", vehicule);
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(vh);
    }


    /**
     * Supprime un vehicule.
     *
     * @param id l'id du vehicule
     * @return soit un code 204 (ok) si vehicule supprimé
     *         soit un code 404 (bad request) si vehicule non trouvé
     * @throws VehiculeNotFoundException en cas d'erreur.
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteVehicule(@PathVariable Integer id) throws VehiculeNotFoundException {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }


}


