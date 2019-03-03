package com.example.sih2019;

public class Car {
    String year;
    String brand;
    String mileage;
    String model;
    String fuel,bider;
    Integer bid;
    String link,kms,serviceno,inLink,fcLink,id;
    String reg_date,end_date,number;
    Double pushmin_price,pushmax_price;

    public Car(String year, String brand, String mileage,String model,String fuel,String link,String kms,String serviceno,String inLink,String fcLink,String reg_date,String end_date,String number,String bider,Integer bid,Double pushmin_price,Double pushmax_price,String id) {
        this.year = year;
        this.brand = brand;
        this.mileage = mileage;
        this.model = model;
        this.fuel = fuel;
        this.link = link;
        this.kms =kms;
        this.serviceno = serviceno;
        this.inLink = inLink;
        this.fcLink = fcLink;
        this.reg_date = reg_date;
        this.end_date = end_date;
        this.number = number;
        this.bider = bider;
        this.bid = bid;
        this.pushmin_price = pushmin_price;
        this.pushmax_price=pushmax_price;
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public String getBrand() {
        return brand;
    }

    public String getMileage() {
        return mileage;
    }
    public String getModel() {
        return model;
    }

    public String getFuel() {
        return fuel;
    }

    public String getLink() {
        return link;
    }
    public String getKms(){
        return kms;
    }
    public String getBider(){
        return bider;
    }
    public Integer getBid(){
        return bid;
    }

    public Double getPushmin_price(){
        return pushmin_price;
    }
    public Double getPushmax_price(){
        return pushmax_price;
    }

    public String getId() {
        return id;
    }

    public String getServiceno() {
        return serviceno;
    }

    public String getInLink() {
        return inLink;
    }

    public String getFcLink() {
        return fcLink;
    }

    public String getReg_date() {
        return reg_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getNumber() {
        return number;
    }


    public Car(){

    }
}

