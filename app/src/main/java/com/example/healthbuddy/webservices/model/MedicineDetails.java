package com.example.healthbuddy.webservices.model;

public class MedicineDetails {

    private String medicine_type;
    private String available_quantity;
    private String medicine_exp_date;
    private String medicine_id;
    private String pick_up_address;
    private Integer id;
    private String medicine_name;
    private int orderMedicineQuantity = 0;


    public int getOrderMedicineQuantity() {
        return orderMedicineQuantity;
    }

    public void setOrderMedicineQuantity(int orderMedicineQuantity) {
        this.orderMedicineQuantity = orderMedicineQuantity;
    }

    public String getMedicine_type() {
        return medicine_type;
    }

    public void setMedicine_type(String medicine_type) {
        this.medicine_type = medicine_type;
    }

    public String getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(String available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getMedicine_exp_date() {
        return medicine_exp_date;
    }

    public void setMedicine_exp_date(String medicine_exp_date) {
        this.medicine_exp_date = medicine_exp_date;
    }

    public String getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(String medicine_id) {
        this.medicine_id = medicine_id;
    }

    public String getPick_up_address() {
        return pick_up_address;
    }

    public void setPick_up_address(String pick_up_address) {
        this.pick_up_address = pick_up_address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

}
