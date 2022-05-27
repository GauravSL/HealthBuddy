package com.example.healthbuddy.webservices.model;


public class DoctorDetails {
    private String doctorId;
    private String doctorEmail;
    private String password;
    private String doctorSpeciality;
    private String doctorMobile;
    private Integer id;
    private String doctor_address;
    private String doctor_name;
    private Integer isVerified;
    private String hospital_name;

    public String getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    public String getDoctorEmail() {
        return doctorEmail;
    }
    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }
    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }
    public String getDoctorMobile() {
        return doctorMobile;
    }
    public void setDoctorMobile(String doctorMobile) {
        this.doctorMobile = doctorMobile;
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
