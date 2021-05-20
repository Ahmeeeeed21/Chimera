/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.codename1.io.Preferences;
import java.util.Date;

/**
 *
 * @author zaefdfyjhlk
 */
public class SessionManager {
    public static Preferences pref ; 
    
    
    
   
    private static int id ; 
    private static String nom ; 
    private static String prenom ; 
    private static String bday ; 
    private static String email; 
    private static String mdp ;
    private static int type ;
    private static String genre ;
    private static String domaine ;
    //private static String photo;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);
    }

    public static void setId(int id) {
        pref.set("id",id);
    }

    public static String getNom() {
        return pref.get("nom",nom);
    }

    public static void setNom(String nom) {
         pref.set("nom",nom);
    }
    public static String getPrenom() {
        return pref.get("prenom",prenom);
    }

    public static void setPrenom(String prenom) {
         pref.set("prenom",prenom);
    }


    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getMdp() {
        return pref.get("mdp",mdp);
    }

    public static void setMdp(String mdp) {
         pref.set("mdp",mdp);
    }

//    public static String getPhoto() {
//        return pref.get("photo",photo);
//    }
//
//    public static void setPhoto(String photo) {
//         pref.set("photo",photo);
//    }
     public static int getType() {
        return pref.get("type",type);
    }

    public static void setType(int type) {
        pref.set("type",type);
    }

    public static String getGenre() {
        return pref.get("genre",genre);
    }

    public static void setGenre(String genre) {
         pref.set("genre",genre);
    }
    public static String getDomaine() {
        return pref.get("domaine",domaine);
    }

    public static void setDomaine(String Domaine) {
         pref.set("domaine",domaine);
    }

    public static String getBday() {
        return bday;
    }

    public static void setBday(String bday) {
        SessionManager.bday = bday;
    }

   
    
    
    
    
}
