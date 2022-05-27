package com.example.healthbuddy.webservices.model;

public class UserDetails {
    private String user_email;
    private String user_id;
    private String user_mobile;
    private String user_name;
    private String user_address;
    private Integer id;

    public String getUserEmail() {
        return user_email;
    }
    public void setUserEmail(String userEmail) {
        this.user_email = userEmail;
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
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
