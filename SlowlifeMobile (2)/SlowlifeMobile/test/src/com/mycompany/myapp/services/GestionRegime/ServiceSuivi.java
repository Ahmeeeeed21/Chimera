/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services.GestionRegime;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Suivi;
import com.mycompany.entities.Utilisateur;
import com.mycompany.utils.SessionManager;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Yassine
 */
public class ServiceSuivi {
    
    public ArrayList<Suivi> Suivis;
    public static ServiceSuivi instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private int user;

    private ServiceSuivi() {
         req = new ConnectionRequest();
         user= SessionManager.getId();
    }

    public static ServiceSuivi getInstance() {
        if (instance == null) {
            instance = new ServiceSuivi();
        }
        return instance;
    }
      public boolean addSuivi(Suivi s) {
        Utilisateur userAdd=new Utilisateur();
        userAdd.setId(user);
        s.setUser(userAdd);
        String url = Statics.BASE_URL + "/suivi/AddSuivijson/?poids=" + s.getPoid()+ "&taille=" + s.getTaille() + "&eau="+ s.getConsommationEau() + "&heure="+s.getHeursActivite()+"&user="+s.getUser().getId();  //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/    
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
        public boolean updateSuivi(Suivi s) {
        String url = Statics.BASE_URL + "/suivi/UpdateSuivijson/"+s.getIdSuivi()+"?poids=" + s.getPoid()+ "&taille=" + s.getTaille() + "&eau="+ s.getConsommationEau() + "&heure="+s.getHeursActivite()+"&user="+s.getUser().getId();  //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
        public boolean deleteSuivi(int id) {
        String url = Statics.BASE_URL + "/suivi/DeleteSuivijson/"+id;//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      public ArrayList<Suivi> parseSuivi(String jsonText){
        try {
            Suivis=new ArrayList<>();
            JSONParser j = new JSONParser();        
            Map<String,Object> suiviListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)suiviListJson.get("root");
            for(Map<String,Object> obj : list){
                Suivi t = new Suivi();
                t.setIdSuivi((int)Float.parseFloat(obj.get("id").toString()));
                t.setPoid((int)(Float.parseFloat(obj.get("poids").toString())));
                t.setTaille(Float.parseFloat(obj.get("taille").toString()));
                t.setHeursActivite((int)Float.parseFloat(obj.get("heureActivite").toString()));
                t.setConsommationEau((int)Float.parseFloat(obj.get("consoEau").toString()));
                
                String date =obj.get("date").toString().substring(0,obj.get("date").toString().indexOf("T"));
                t.setDateSuivi(date);      
                Suivis.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return Suivis;
    }
    
    public ArrayList<Suivi> getAllSuivi(){
        String url = Statics.BASE_URL+"/suivi/ReadAllSuivi/"+user;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Suivis = parseSuivi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Suivis;
    }
    
    public ArrayList<Suivi> getOneSuivi(Float taille){
        String url = Statics.BASE_URL+"/suivi/ReadBySuivi/"+taille;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Suivis = parseSuivi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Suivis;
    }
      
      
}
