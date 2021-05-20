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
public class Com {
           private int id ;
private String date;
private String datem;
private String contenu;
private int id_publication;
    public Com() {
    }

    public Com(int id, String contenu) {
        this.id = id;
        this.contenu = contenu;
    }

    public Com(int id, String datem, String contenu) {
        this.id = id;
        this.datem = datem;
        this.contenu = contenu;
    }


    public Com(int id, String date,  String contenu, int id_publication) {
        this.id = id;
        this.date = date;
        this.contenu = contenu;
        this.id_publication = id_publication;
    }

    public Com(String date, String contenu, int id_publication) {
        this.date = date;
        this.contenu = contenu;
        this.id_publication = id_publication;
    }

   


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Com(int id) {
        this.id = id;
    }

    public String getDatem() {
        return datem;
    }

    public void setDatem(String datem) {
        this.datem = datem;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

}
