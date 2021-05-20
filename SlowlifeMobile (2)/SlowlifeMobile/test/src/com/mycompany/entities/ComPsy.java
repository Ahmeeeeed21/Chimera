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
public class ComPsy {
            private int id ;
private String date;
private String datem;
private String contenu;
private int id_publication2;
    public ComPsy() {
    }

    public ComPsy(int id) {
        this.id = id;
    }

    public ComPsy(int id, String date, String datem, String contenu, int id_publication2) {
        this.id = id;
        this.date = date;
        this.datem = datem;
        this.contenu = contenu;
        this.id_publication2 = id_publication2;
    }

    public ComPsy(int id, String datem, String contenu) {
        this.id = id;
        this.datem = datem;
        this.contenu = contenu;
    }

    public ComPsy(String date, String contenu, int id_publication2) {
        this.date = date;
        this.contenu = contenu;
        this.id_publication2 = id_publication2;
    }

    public String getDatem() {
        return datem;
    }

    public void setDatem(String datem) {
        this.datem = datem;
    }

  
   
  


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId_publication2() {
        return id_publication2;
    }

    public void setId_publication2(int id_publication2) {
        this.id_publication2 = id_publication2;
    }

}
