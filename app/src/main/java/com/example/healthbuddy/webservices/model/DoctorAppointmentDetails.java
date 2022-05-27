package com.example.healthbuddy.webservices.model;

public class DoctorAppointmentDetails {
    private String doctor_id;
    private String user_email;
    private String appointment_status;
    private String user_id;
    private String user_mobile;
    private String user_name;
    private String user_address;
    private String appointment_id;
    private String appointment_date;
    private Integer id;
    private String appointment_mode;
    private String rejection_reason;

    public String getDoctorId() {
        return doctor_id;
    }
    public void setDoctorId(String doctor_id) {
        this.doctor_id = doctor_id;
    }
    public String getUserEmail() {
        return user_email;
    }
    public void setUserEmail(String userEmail) {
        this.user_email = userEmail;
    }
    public String getAppointmentStatus() {
        return appointment_status;
    }
    public void setAppointmentStatus(String appointmentStatus) {
        this.appointment_status = appointmentStatus;
    }
    public String getUserId() {
        return user_id;
    }
    public void setUserId(String userId) {
        this.user_id = userId;
    }
    public String getUserMobile() {
        return user_mobile;
    }
    public void setUserMobile(String userMobile) {
        this.user_mobile = userMobile;
    }
    public String getUserName() {
        return user_name;
    }
    public void setUserName(String userName) {
        this.user_name = userName;
    }
    public String getUserAddress() {
        return user_address;
    }
    public void setUserAddress(String userAddress) {
        this.user_address = userAddress;
    }
    public String getAppointmentId() {
        return appointment_id;
    }
    public void setAppointmentId(String appointmentId) {
        this.appointment_id = appointmentId;
    }
    public String getAppointmentDate() {
        return appointment_date;
    }
    public void setAppointmentDate(String appointmentDate) {
        this.appointment_date = appointmentDate;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAppointmentMode() {
        return appointment_mode;
    }
    public void setAppointmentMode(String appointmentMode) {
        this.appointment_mode = appointmentMode;
    }
    public String getRejectionReason() {
        return rejection_reason;
    }

    public void setRejectionReason(String rejection_reason) {
        this.rejection_reason = rejection_reason;
    }
}
