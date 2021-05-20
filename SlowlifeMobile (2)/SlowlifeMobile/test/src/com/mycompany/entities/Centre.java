/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author amira
 */
public class Centre {
      private int id;
    private String nom , description , lieu, image,type;

    public Centre(String nom, String description, String lieu, String type) {
        this.nom = nom;
        this.description = description;
        this.lieu = lieu;
        this.type = type;
    }

    public Centre() {
        //sthrow new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Centre{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", lieu=" + lieu + ", image=" + image + ", type=" + type + '}';
    }
    
    
}
