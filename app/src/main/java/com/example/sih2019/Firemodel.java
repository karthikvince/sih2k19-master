package com.example.sih2019;

public class Firemodel {

    String kms,model,year,fuel,link,number,serviceno,mileage,fcLink,inLink,brand,reg_date,end_date,id;
    Integer bid;
    Double pushmin_price,pushmax_price;


    public String getKms(){
        return kms;
    }
    public String getModel(){
        return model;
    }
    public String getYear(){
        return year;
    }
    public String getFuel(){
        return  fuel;
    }
    public String getLink(){
        return link;
    }
    public String getNumber(){
        return number;
    }
    public Integer getBid(){
        return bid;
    }
    public String getReg_date(){
        return reg_date;
    }

    public String getId() {
        return id;
    }

    public Double getPushmax_price() {
        return pushmax_price;
    }

    public Double getPushmin_price() {
        return pushmin_price;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getServiceno(){
        return serviceno;
    }
    public String getMileage(){
        return mileage;
    }

    public String getFcLink(){
        return fcLink;
    }
    public String getInLink(){
        return inLink;
    }
    public String getBrand(){
        return brand;
    }
    public void setKms(String kms){
        this.kms = kms;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setModel(String model){
        this.model=model;
    }
    public void setYear(String year){
        this.year = year;
    }
    public void setFuel(String fuel){
        this.fuel = fuel;
    }
    public void setLink(String link){
        this.link = link;
    }
    public void setNumber(String number){
        this.number = number;
    }
    public void setBid(Integer bid){
        this.bid = bid;
    }
    public void setServiceno(String serviceno){
        this.serviceno = serviceno;
    }
    public void setMileage(String mileage){
        this.mileage = mileage;
    }

    public void setPushmax_price(Double pushmax_price) {
        this.pushmax_price = pushmax_price;
    }

    public void setPushmin_price(Double pushmin_price) {
        this.pushmin_price = pushmin_price;
    }

    public void setFcLink(String fcLink) {
        this.fcLink = fcLink;
    }

    public void setInLink(String inLink) {
        this.inLink = inLink;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }
}
