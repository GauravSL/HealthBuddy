package com.example.healthbuddy.webservices.model;

import java.io.Serializable;

public class UserAppointmentDetails implements Serializable {
    private String doctor_id;
    private String appointment_status;
    private String doctor_speciality;
    private String user_id;
    private String doctor_mobile;
    private String appointment_id;
    private String appointment_date;
    private Integer id;
    private String doctor_address;
    private String doctor_name;
    private String appointment_mode;
    private String rejection_reason;
    private String hospital_name;

    public String getDoctorId() {
        return doctor_id;
    }
    public void setDoctorId(String doctorId) {
        this.doctor_id = doctorId;
    }
    public String getAppointment_status() {
        return appointment_status;
    }
    public void setAppointment_status(String appointment_status) {
        this.appointment_status = appointment_status;
    }
    public String getDoctorSpeciality() {
        return doctor_speciality;
    }
    public void setDoctorSpeciality(String doctor_speciality) {
        this.doctor_speciality = doctor_speciality;
    }
    public String getUserId() {
        return user_id;
    }
    public void setUserId(String userId) {
        this.user_id = userId;
    }
    public String getDoctorMobile() {
        return doctor_mobile;
    }
    public void setDoctorMobile(String doctor_mobile) {
        this.doctor_mobile = doctor_mobile;
    }
    public String getAppointmentId() {
        return appointment_id;
    }
    public void setAppointmentId(String appointment_id) {
        this.appointment_id = appointment_id;
    }
    public String getAppointmentDate() {
        return appointment_date;
    }
    public void setAppointmentDate(String appointment_date) {
        this.appointment_date = appointment_date;
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
    public void setDoctorAddress(String doctor_address) {
        this.doctor_address = doctor_address;
    }
    public String getDoctorName() {
        return doctor_name;
    }
    public void setDoctorName(String doctor_name) {
        this.doctor_name = doctor_name;
    }
    public String getAppointmentMode() {
        return appointment_mode;
    }
    public void setAppointmentMode(String appointmentMode) {
        this.appointment_mode = appointmentMode;
    }

    public String getRejection_reason() {
        return rejection_reason;
    }

    public void setRejection_reason(String rejection_reason) {
        this.rejection_reason = rejection_reason;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }
}
