package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="Vehicule")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vehicule{

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private int id;

        @Column(name="vehicleName")
        private String  vehicleName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVehicleName() {
            return vehicleName;
        }

        public void setVehicleName(String vehicleName) {
            this.vehicleName = vehicleName;
        }

        @Override
        public String toString() {
            return "Vehicule{" +
                    "id=" + id +
                    ", vehicleName='" + vehicleName + '\'' +
                    '}';
        }
}
