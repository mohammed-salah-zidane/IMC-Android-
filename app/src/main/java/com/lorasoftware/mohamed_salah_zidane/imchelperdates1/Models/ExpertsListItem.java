package com.lorasoftware.mohamed_salah_zidane.imchelperdates1.Models;

import java.io.Serializable;

public class ExpertsListItem implements Serializable {

    private String title ;

    private int expertId;
    private String nationality;

    private String from;
    private String to;
    private String generalSpec;
    private String specialSpec;
    private int image;

    public ExpertsListItem(int expertId,String title, String nationality,String generalSpec,String specialSpec, String from,  String to) {
        this.title = title;
       this.nationality = nationality;

        this.from=from;
        this.to=to;
        this.expertId= expertId;
        this.generalSpec = generalSpec;
        this.specialSpec = specialSpec;

    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }


    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getExpertId() {
        return expertId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setGeneralSpec(String generalSpec) {
        this.generalSpec = generalSpec;
    }

    public void setSpecialSpec(String specialSpec) {
        this.specialSpec = specialSpec;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public String getGeneralSpec() {
        return generalSpec;
    }

    public String getSpecialSpec() {
        return specialSpec;
    }

    public int getImage() {
        return image;
    }
}
