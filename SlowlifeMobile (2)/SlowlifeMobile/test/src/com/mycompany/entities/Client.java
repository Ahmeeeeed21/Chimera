/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;


import java.util.Date;


/**
 *
 * @author zaefdfyjhlk
 */
public class Client extends Utilisateur{

    public Client() {
    }

    public Client(int id, String nom, String prenom, String email, String mdp, String photo, int type, String genre, Date bday) {
        super(id, nom, prenom, email, mdp, photo, type, genre, bday);
    }

    public Client(String nom, String prenom, String email, String mdp, String photo, int type, String genre, Date bday) {
        super(nom, prenom, email, mdp, photo, type, genre, bday);
    }

    public Client(String nom, String prenom, String email, String mdp, String photo, int type, String genre) {
        super(nom, prenom, email, mdp, photo, type, genre);
    }

    public Client(String nom, String prenom, String email, String mdp, String photo, String genre) {
        super(nom, prenom, email, mdp, photo, genre);
    }

    public Client(String nom, String prenom, String email, String photo, int type, String genre, Date bday) {
        super(nom, prenom, email, photo, type, genre, bday);
    }

    public Client(int id, String nom, String prenom, String email, String photo, int type, String genre, Date bday) {
        super(id, nom, prenom, email, photo, type, genre, bday);
    }

    

   
    
    
    
}
