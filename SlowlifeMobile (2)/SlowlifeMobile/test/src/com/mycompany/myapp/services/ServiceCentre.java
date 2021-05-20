/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Centre;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author amira
 */
public class ServiceCentre {
    public ArrayList<Centre> centres;
    
    public static ServiceCentre instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Map<String,Double> stat;

    public ServiceCentre() {
        req = new ConnectionRequest();
    }
    
    public static ServiceCentre getInstance() {
        if (instance == null) {
            instance = new ServiceCentre();
        }
        return instance;
    }
    
    
    
     public ArrayList<Centre> parseCentres(String jsonText){
        try {
            centres=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
  
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Centre t = new Centre();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setLieu(obj.get("lieu").toString());
                try{
                t.setImage(obj.get("image").toString());
                }
                catch(NullPointerException e){
                    t.setImage("");
                    
                }
                t.setType((obj.get("type").toString()));
                t.setDescription(obj.get("description").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                centres.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return centres;
    }
    
    
    
    
    public ArrayList<Centre> getAllCentres(){
        String url = Statics.BASE_URL+"/affcentrem";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                centres = parseCentres(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return centres;
    }
    
    
    
    
      public boolean addCentre(Centre t) {
        String url = Statics.BASE_URL + "/ajoutercentrem?nom=" + t.getNom() + "&type=" + t.getType()+ "&description=" + t.getDescription()+ "&lieu=" + t.getLieu(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener   
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
       public boolean SupprimerCentre(Centre t) {
        String url = Statics.BASE_URL + "/suppCentrem/" + t.getId();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
       
       
              public boolean ModifierCentre(Centre t) {
        String url = Statics.BASE_URL + "/modifierCentrem/" + t.getId()+"?nom=" + t.getNom() + "&type=" + t.getType()+ "&description=" + t.getDescription()+ "&lieu=" + t.getLieu();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
              
              
              //****************************
              
              
           
              
              
         public Map<String,Double> parseStat(String jsonText){
        try {
            stat=new HashMap<String,Double>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
  
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                
                
                //System.out.println(obj.get("val"));
                //System.out.println(obj.get("nom"));

                double val = Double.parseDouble(obj.get("val").toString());
                String nomcentre= obj.get("nom").toString();

                stat.put(nomcentre,val);
            }
            
            
        } catch (IOException ex) {
            
        }
        return stat;
    }
              
              
              
              
              
              
              
       public Map<String,Double> StatCentres(){
        String url = Statics.BASE_URL+"/statServiceCentrem";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                stat = parseStat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stat;
    }
      
      
    
    
    
}
