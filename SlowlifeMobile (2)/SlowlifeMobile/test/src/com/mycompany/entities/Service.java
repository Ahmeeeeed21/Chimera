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
public class Service {
      int id;
    String nom, description,type;

    public Service() {
    }
    
    

    public Service(String nom, String description, String type) {
        this.nom = nom;
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", type=" + type + '}';
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
