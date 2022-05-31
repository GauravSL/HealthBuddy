package com.example.healthbuddy.webservices.model;


public class DoctorDetails {
    private String doctor_id;
    private String doctor_email;
    private String password;
    private String doctor_speciality;
    private String doctor_mobile;
    private Integer id;
    private String doctor_address;
    private String doctor_name;
    private Integer isVerified;
    private String hospital_name;

    public String getDoctorId() {
        return doctor_id;
    }
    public void setDoctorId(String doctorId) {
        this.doctor_id = doctorId;
    }
    public String getDoctorEmail() {
        return doctor_email;
    }
    public void setDoctorEmail(String doctorEmail) {
        this.doctor_email = doctorEmail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDoctorSpeciality() {
        return doctor_speciality;
    }
    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctor_speciality = doctorSpeciality;
    }
    public String getDoctorMobile() {
        return doctor_mobile;
    }
    public void setDoctorMobile(String doctorMobile) {
        this.doctor_mobile = doctorMobile;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDoctorAddress() {
        return doctor_address;
    }
    public void setDoctorAddress(String doctorAddress) {
        this.doctor_address = doctorAddress;
    }
    public String getDoctorName() {
        return doctor_name;
    }
    public void setDoctorName(String doctorName) {
        this.doctor_name = doctorName;
    }
    public Integer getIsVerified() {
        return isVerified;
    }
    public void setIsVerified(Integer isVerified) {
        this.isVerified = isVerified;
    }
    public String getHospital_name() {
        return hospital_name;
    }
    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }
}
