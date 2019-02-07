package com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models;

import java.io.Serializable;

public class DoctorListItem implements Serializable {

    private int doctorId;
    private String name;
    private String degree;
    private String clinicName;

    private String day;
    private String from_to;
    private int doctorTimesId;
    private boolean isExpanded;

    private   boolean isSelected;

    public DoctorListItem() {
    }

    public DoctorListItem(int doctorId, String name, String degree, String clinicName, boolean isExpanded, boolean isSelected) {
        this.doctorId = doctorId;
        this.name = name;
        this.degree = degree;
        this.clinicName = clinicName;

        this.isExpanded = isExpanded;
        this.isSelected = isSelected;
    }









    public int getDoctorId() {
        return doctorId;
    }

    public String getDay() {
        return day;
    }

    public String getFrom_to() {
        return from_to;
    }

    public int getDoctorTimesId() {
        return doctorTimesId;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setFrom_to(String from_to) {
        this.from_to = from_to;
    }

    public void setDoctorTimesId(int doctorTimesId) {
        this.doctorTimesId = doctorTimesId;
    }


    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /*public String getSubText() {
        return subText;
    }*/

    public void setName(String name) {
        this.name = name;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public String getName() {
        return name;
    }

    public String getDegree() {
        return degree;
    }

    public String getClinicName() {
        return clinicName;
    }
}
