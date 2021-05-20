/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Wassim
 */
public class Pubpsy {
          private int id;
    private String type ;
    private String img ;
    private String description ;
    private String sujet;

    public Pubpsy(int id) {
        this.id = id;
    }

    public Pubpsy() {
    }

    public Pubpsy(int id, String type, String img, String description, String sujet) {
        this.id = id;
        this.type = type;
        this.img = img;
        this.description = description;
        this.sujet = sujet;
    }

    public Pubpsy(String type, String img, String description, String sujet) {
        this.type = type;
        this.img = img;
        this.description = description;
        this.sujet = sujet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

}
