package com.example.demo.service;


import com.example.demo.exception.CustomMappingException;
import com.example.demo.exception.VehiculeNotFoundException;
import com.example.demo.model.Vehicule;
import com.example.demo.repository.VehiculeRepository;

import java.util.List;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VehiculeService {
        private final VehiculeRepository vehiculeRepository;

        /**
         * Constructeur de {@link VehiculeService}.
         *
         * @param vehiculeRepository une instance de {@link VehiculeRepository} injectée par Spring.
         */
        public VehiculeService(VehiculeRepository vehiculeRepository) {
                this.vehiculeRepository = vehiculeRepository;
        }

        /**
         * Méthode pour récupérer tous les projects.
         *
         * @return une {@link List} de {@link Vehicule}s
         */
        public List<Vehicule> getAll() {
                return vehiculeRepository.getAll();
        }

        /**
         * Méthode pour récupérer un {@link Vehicule} par son id.
         *
         * @param id l identifiant en question
         * @return le {@link Vehicule} trouvé
         * @throws VehiculeNotFoundException si le {@link Vehicule} n'est pas trouvé
         */
        public Vehicule getById(Integer id) throws VehiculeNotFoundException {
                return vehiculeRepository.getById(id);
        }

        /**
         * Ajoute un projet.
         *
         * @param vehicule le projet
         * @return un {@link Vehicule}
         * @throws CustomMappingException en cas d'erreur.
         */
        public Vehicule postVehicule(Vehicule vehicule) throws CustomMappingException {
                return vehiculeRepository.saveVehicule(vehicule);
        }

        /**
         * Update un {@link Vehicule}.
         *
         * @param vehicule   le {@link Vehicule} à modifier ou sauvegarder
         */
        public Vehicule updateVehicule(Vehicule vehicule) throws VehiculeNotFoundException {

                return vehiculeRepository.updateVehicule(vehicule);
        }


        /**
         * Supprime un vehicule.
         * @param vehiculeId l'id du quiz à supprimer.
         * @throws VehiculeNotFoundException en cas d'erreur.
         */
        @Transactional
        public void deleteVehicule(Integer vehiculeId) throws VehiculeNotFoundException {
                vehiculeRepository.deleteVehicule(vehiculeId);
        }


}
