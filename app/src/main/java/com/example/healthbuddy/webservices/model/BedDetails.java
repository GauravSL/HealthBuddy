package com.example.healthbuddy.webservices.model;

public class BedDetails {
   private String id;
    private String hospital_name;
    private String general_bed;
    private String special_bed;
    private String icu_bed;
    private String hospital_address;

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

    public String getGeneral_bed() {
        return general_bed;
    }

    public void setGeneral_bed(String general_bed) {
        this.general_bed = general_bed;
    }

    public String getSpecial_bed() {
        return special_bed;
    }

    public void setSpecial_bed(String special_bed) {
        this.special_bed = special_bed;
    }

    public String getIcu_bed() {
        return icu_bed;
    }

    public void setIcu_bed(String icu_bed) {
        this.icu_bed = icu_bed;
    }

    public String getHospital_address() {
        return hospital_address;
    }

    public void setHospital_address(String hospital_address) {
        this.hospital_address = hospital_address;
    }


}
