package com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models;

public class ClinicListItem {

    private int clinic_id;
    private String name;
    private int floor_num;

    public ClinicListItem(int clinic_id,String name,int floor_num) {
        this.name = name;
        this.clinic_id = clinic_id;
        this.floor_num=floor_num;
    }

    public ClinicListItem() {
    }

    public int getFloor_num() {
        return floor_num;
    }

    public void setFloor_num(int floor_num) {
        this.floor_num = floor_num;
    }

    public int getClinic_id() {
        return clinic_id;
    }

    public void setClinic_id(int clinic_id) {
        this.clinic_id = clinic_id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
