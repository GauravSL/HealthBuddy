package com.example.healthbuddy.webservices.model;

public class OrderDetails {
    private String id;
    private String order_id;
    private String  order_date;
    private String  is_delivered;
    private String  user_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getIs_delivered() {
        return is_delivered;
    }

    public void setIs_delivered(String is_delivered) {
        this.is_delivered = is_delivered;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


}
