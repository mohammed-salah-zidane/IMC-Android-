package com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models;

public class DoctorDates {

    private int doctorId;
    private String day;
    private String from_to;
    private int doctorTimesId;



    public DoctorDates() {
    }

    public DoctorDates(int doctorId, String day, String from_to, int doctorTimesId) {
        this.doctorId = doctorId;
        this.day = day;
        this.from_to = from_to;
        this.doctorTimesId = doctorTimesId;

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

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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



    /*public String getSubText() {
        return subText;
    }*/





}
