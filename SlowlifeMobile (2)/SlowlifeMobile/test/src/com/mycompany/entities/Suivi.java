/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Yassine
 */
public class Suivi {
    private int idSuivi;
    private String dateSuivi;
    private int poid;
    private Float taille;
    private int heursActivite;
    private int consommationEau;
    private float IMC;
    private Utilisateur user;

    public Suivi() {
    }

    public Suivi(int idSuivi, int poid, Float taille, int heursActivite, int consommationEau, Utilisateur user) {
        this.idSuivi = idSuivi;
        this.poid = poid;
        this.taille = taille;
        this.heursActivite = heursActivite;
        this.consommationEau = consommationEau;
        this.user = user;
    }

    public Suivi(String dateSuivi, int poid, Float taille, int heursActivite, int consommationEau) {
        this.dateSuivi = dateSuivi;
        this.poid = poid;
        this.taille = taille;
        this.heursActivite = heursActivite;
        this.consommationEau = consommationEau;
    }

    
    public Suivi(int poid, Float taille, int heursActivite, int consommationEau, Utilisateur user) {
        this.poid = poid;
        this.taille = taille;
        this.heursActivite = heursActivite;
        this.consommationEau = consommationEau;
        this.user = user;
    }

    public Suivi(int idSuivi, int poid, Float taille, int heursActivite, int consommationEau) {
        this.idSuivi = idSuivi;
        this.poid = poid;
        this.taille = taille;
        this.heursActivite = heursActivite;
        this.consommationEau = consommationEau;
    }

    


    public float getIMC() {
        return IMC;
    }

    public void setIMC(float IMC) {
        this.IMC = IMC;
    }

    public int getIdSuivi() {
        return idSuivi;
    }

    public String getDateSuivi() {
        return dateSuivi;
    }

    public int getPoid() {
        return poid;
    }

    public Float getTaille() {
        return taille;
    }
    public int getHeursActivite() {
        return heursActivite;
    }

    public int getConsommationEau() {
        return consommationEau;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setIdSuivi(int idSuivi) {
        this.idSuivi = idSuivi;
    }

    public void setDateSuivi(String dateSuivi) {
        this.dateSuivi = dateSuivi;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public void setTaille(Float taille) {
        this.taille = taille;
    }


    public void setHeursActivite(int heursActivite) {
        this.heursActivite = heursActivite;
    }

    public void setConsommationEau(int consommationEau) {
        this.consommationEau = consommationEau;
    }

    public void setUser(Utilisateur idUser) {
        this.user = idUser;
    }

    @Override
    public String toString() {
        return "SuiviRegime{" + "idSuivi=" + idSuivi + ", dateSuivi=" + dateSuivi + ", poid=" + poid + ", taille=" + taille + ", heursActivite=" + heursActivite + ", consommationEau=" + consommationEau + ", IMC=" + IMC + ", user=" + user + '}';
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Suivi other = (Suivi) obj;
        return Objects.equals(this.dateSuivi, other.dateSuivi);
    }
    
    
    
    
}
