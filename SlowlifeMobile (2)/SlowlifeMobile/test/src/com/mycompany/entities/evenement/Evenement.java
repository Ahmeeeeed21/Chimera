/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities.evenement;

import java.util.Date;

/**
 *
 * @author Ahmed Ezzine
 */
public class Evenement {
     private long idEvent ;
    private String nom ;
    private String type ;
    private String dateDeb ;
    private String dateFin ;
    private String lieu ;
    private String description ;
    private int idUser;
    private String nomC;
    private String prenomC;
    private int participants;
    
    public Evenement() {
        super();
    }

    public Evenement(long idEvent, String nom, String type, String dateDeb, String dateFin, String lieu, String description, int idUser) {
        this.idEvent = idEvent;
        this.nom = nom;
        this.type = type;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.description = description;
        this.idUser = idUser;
    }

    public Evenement(String nom, int participants) {
        this.nom = nom;
        this.participants = participants;
    }

   /* public Evenement(String toString, String toString0, String toString1, String valueOf, Date valueOf0, Date valueOf1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    

    public long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(String dateDeb) {
        this.dateDeb = dateDeb;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public void setPrenomC(String prenomC) {
        this.prenomC = prenomC;
    }
    
    public String getNomCoach () {
        return nomC + " " + prenomC;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }
    
    
    
    @Override
    public String toString() {
        
       return "Event{" + "Nom=" + nom + ", Type=" + type + ", description=" + description +""
                + "Date Debut=" + dateDeb + "Date Fin="+ dateFin +"nbr_participants= " +participants 
                +"Region= " +lieu +'}';
    }
    
}
