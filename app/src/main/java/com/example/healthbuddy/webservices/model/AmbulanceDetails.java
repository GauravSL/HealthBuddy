package com.example.healthbuddy.webservices.model;

public class AmbulanceDetails {
    private String id;
    private String ambulance_address;
    private String abmulance_capacity;
    private String ambulance_driver;
    private String ambulance_contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmbulance_address() {
        return ambulance_address;
    }

    public void setAmbulance_address(String ambulance_address) {
        this.ambulance_address = ambulance_address;
    }

    public String getAbmulance_capacity() {
        return abmulance_capacity;
    }

    public void setAbmulance_capacity(String abmulance_capacity) {
        this.abmulance_capacity = abmulance_capacity;
    }

    public String getAmbulance_driver() {
        return ambulance_driver;
    }

    public void setAmbulance_driver(String ambulance_driver) {
        this.ambulance_driver = ambulance_driver;
    }

    public String getAmbulance_contact() {
        return ambulance_contact;
    }

    public void setAmbulance_contact(String ambulance_contact) {
        this.ambulance_contact = ambulance_contact;
    }

}
