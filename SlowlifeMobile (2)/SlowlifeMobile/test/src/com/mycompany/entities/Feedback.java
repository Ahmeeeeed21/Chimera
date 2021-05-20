/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Ahmed Ezzine
 */
public class Feedback {
    private int id;
    private String sujet;
    private int rate;
    private String Description;
    private String type;
    private int otherid;
    private int statRate;

    public Feedback() {
    }

    public Feedback(String sujet, int rate, String Description, String type) {
        this.sujet = sujet;
        this.rate = rate;
        this.Description = Description;
        this.type = type;
    }

    public Feedback(String sujet, int rate, String Description, String type, int otherid) {
        this.sujet = sujet;
        this.rate = rate;
        this.Description = Description;
        this.type = type;
        this.otherid = otherid;
    }

    public Feedback(int id, String sujet, int rate, String Description, String type, int otherid) {
        this.id = id;
        this.sujet = sujet;
        this.rate = rate;
        this.Description = Description;
        this.type = type;
        this.otherid = otherid;
    }

    public Feedback(int id) {
        this.id = id;
    }

    public Feedback(String toString, String toString0, String toString1, String valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOtherid() {
        return otherid;
    }

    public void setOtherid(int otherid) {
        this.otherid = otherid;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", sujet=" + sujet + ", rate=" + rate + ", Description=" + Description + ", type=" + type + ", otherid=" + otherid + '}';
    }
    
}
