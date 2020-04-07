package com.example.demo.repository;

import com.example.demo.exception.CustomMappingException;
import com.example.demo.exception.VehiculeNotFoundException;
import com.example.demo.model.Vehicule;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;


@Repository
public class VehiculeRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private static final String GET_ALL = "FROM Vehicule";
    private static final String QUERY_SELECT_VEHICULE_ID =
            "FROM Vehicule "
                    + "WHERE id= :idQuiz";

    private final Logger logger = LoggerFactory.getLogger(VehiculeRepository.class);

    public VehiculeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    /**
     * Méthode pour récupérer tous les vehicules.
     *
     * @return une {@link List} de {@link Vehicule}s
     */
    public List<Vehicule> getAll() {
        Session session = entityManager.unwrap(Session.class);
        Query<Vehicule> query = session.createQuery(GET_ALL, Vehicule.class);
        return query.getResultList();
    }

    /**
     * Permet de sauvegarder un vehicule.
     *
     * @param vehicule  le {@link Vehicule} a ajouter
     * @return une {@link Vehicule}
     * @throws CustomMappingException en cas d'erreur.
     */
    @Transactional
    public Vehicule saveVehicule(Vehicule vehicule) throws CustomMappingException {
        Session session = entityManager.unwrap(Session.class);

        if (vehicule!=null) {
            session.save(vehicule);

            return vehicule;
        } else {
            logger.debug("An optional client cannot be mapped : {}", vehicule);
            throw new CustomMappingException();
        }
    }
        /**
         * Méthode pour récupérer un {@link Vehicule} par son id.
         *
         * @param id l identifiant du vehicule
         * @return le {@link Vehicule} trouvé
         * @throws VehiculeNotFoundException si le {@link Vehicule} n'est pas trouvé
         */
        public Vehicule getById(Integer id) throws VehiculeNotFoundException {
            Session session = entityManager.unwrap(Session.class);
            Vehicule vehicule = session.get(Vehicule.class, id);
            if (vehicule != null) {
                session.evict(vehicule);
                return vehicule;
            } else {
                throw new VehiculeNotFoundException();
            }
        }

        /**
         * Update un {@link Vehicule}.
         *
         * @param vehicule le {@link Vehicule} à modifier ou sauvegarder
         * @return une {@link Vehicule}
         */
        @Transactional
        public Vehicule updateVehicule(Vehicule vehicule) throws VehiculeNotFoundException {
            try {
                Session session = entityManager.unwrap(Session.class);
                session.update(vehicule);
                return vehicule;
            } catch (NoSuchElementException e) {
                throw new VehiculeNotFoundException();
            }
        }


        /**
         * Supprime un vehicule.
         *
         * @param idVehicule l'id du quiz à supprimer.
         * @throws VehiculeNotFoundException en cas d'erreur.
         */

        public void deleteVehicule(Integer idVehicule) throws VehiculeNotFoundException {
            try {
                Session session = entityManager.unwrap(Session.class);
                Query<Vehicule> queryGet = session.createQuery(QUERY_SELECT_VEHICULE_ID, Vehicule.class);
                queryGet.setParameter("idVehicule", idVehicule);
                Vehicule vehicule = queryGet.getSingleResult();
                session.delete(vehicule);
            } catch (NoSuchElementException | NoResultException e) {
                throw new VehiculeNotFoundException();
            }
        }



    }


