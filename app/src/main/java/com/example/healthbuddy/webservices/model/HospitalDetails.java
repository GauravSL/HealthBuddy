package com.example.healthbuddy.webservices.model;

public class HospitalDetails {
    private String id;
    private String hospital_name;
    private String  hospital_address;
    private String  hospital_timing;
    private String  hospital_contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospital_address() {
        return hospital_address;
    }

    public void setHospital_address(String hospital_address) {
        this.hospital_address = hospital_address;
    }

    public String getHospital_timing() {
        return hospital_timing;
    }

    public void setHospital_timing(String hospital_timing) {
        this.hospital_timing = hospital_timing;
    }

    public String getHospital_contact() {
        return hospital_contact;
    }

    public void setHospital_contact(String hospital_contact) {
        this.hospital_contact = hospital_contact;
    }

}
