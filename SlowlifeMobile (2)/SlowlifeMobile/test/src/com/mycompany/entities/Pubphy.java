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
public class Pubphy {
     private int id;
     private String type ;
    private String img ;
    private String description ;
        private String nom ;
    private int duree ;
    private int repetition ;

    public Pubphy(String type, String img, String description, String nom, int duree, int repetition) {
        this.type = type;
        this.img = img;
        this.description = description;
        this.nom = nom;
        this.duree = duree;
        this.repetition = repetition;
    }

    public Pubphy() {
    }

    public Pubphy(int id, String type, String img, String description, String nom, int duree, int repetition) {
        this.id = id;
        this.type = type;
        this.img = img;
        this.description = description;
        this.nom = nom;
        this.duree = duree;
        this.repetition = repetition;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }
    
    
    
}
