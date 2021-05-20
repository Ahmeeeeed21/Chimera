/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author amira
 */
public class Rendezvous {
     private int id;
    private String temps;
    private Centre centre;
    private Utilisateur client;
    private Service service;
     Date date;

    public Rendezvous() {
    }
     
     
     

    public Rendezvous(String temps, Centre centre, Service service, Date date) {
        this.temps = temps;
        this.centre = centre;
        //this.client = client;
        this.service = service;
        this.date = date;
    }
     
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public Utilisateur getClient() {
        return client;
    }

    public void setClient(Utilisateur client) {
        this.client = client;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
     
    
}
