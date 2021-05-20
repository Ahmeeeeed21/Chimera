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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Centre;
import com.mycompany.entities.Rendezvous;
import com.mycompany.entities.Service;
import com.mycompany.entities.Utilisateur;
import com.mycompany.utils.SessionManager;
import com.mycompany.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author amira
 */
public class ServiceRendezvous {
    public static int id= SessionManager.getId();
    
     public ArrayList<Rendezvous> rendezvous;
    
    public static ServiceRendezvous instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceRendezvous() {
        req = new ConnectionRequest();
    }
    
     public static ServiceRendezvous getInstance() {
        if (instance == null) {
            instance = new ServiceRendezvous();
        }
        return instance;
    }
     
     
      public ArrayList<Rendezvous> parseRendezvous(String jsonText){
        try {
            rendezvous =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
  
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Rendezvous t = new Rendezvous();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                LinkedHashMap mc= (LinkedHashMap) (obj.get("idCentre"));
                LinkedHashMap ms= (LinkedHashMap) (obj.get("idService"));
                LinkedHashMap mu= (LinkedHashMap) (obj.get("idUtilisateur"));
               // System.out.println(mc.get("nom"));
                ArrayList<Service> ls= ServiceService.getInstance().getAllServices();
                ArrayList<Centre> lc= ServiceCentre.getInstance().getAllCentres();
                for(Centre c:lc)
                         {  
                       if(c.getNom().compareTo((String)mc.get("nom"))==0)
                         t.setCentre(c);
                         }
                       for(Service s:ls)
                       {  
                      if(s.getNom().compareTo((String)ms.get("nom"))==0)
                        t.setService(s);
                      }
                       Utilisateur u=new Utilisateur();
                       u.setNom((String)mu.get("nom"));
                       u.setPrenom((String)mu.get("prenom"));
                       u.setEmail((String)mu.get("email"));

              t.setClient(u);
                
                DateFormat destDf = new SimpleDateFormat("yyyy-MM-dd");
                Date date;
                try {
                    date = destDf.parse(obj.get("date").toString());
                    t.setDate(date);
                } catch (ParseException ex) {
                    
                }
                
                t.setTemps(obj.get("temps").toString());
                rendezvous.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return rendezvous;
    }
      
      
      
      
      
         public ArrayList<Rendezvous> getAllRendezvous(){
        String url = Statics.BASE_URL+"/affRendezvousm/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                rendezvous = parseRendezvous(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rendezvous;
    }
         
         
            
      public boolean addRendezvous(Rendezvous t) {
          DateFormat destDf = new SimpleDateFormat("yyyy-MM-dd");
           String ds = destDf.format(t.getDate());
           String url = null;
           
                        //System.out.println("Converted date is : " + ds);
                       url = Statics.BASE_URL + "/ajouterRendezvousm?date=" + ds + "&temps=" + t.getTemps()+ "&centre=" + t.getCentre().getId()+ "&service=" + t.getService().getId()+ "&idu=" + id; //création de l'URL

          
          
          
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
      
      
      
      
       public boolean SupprimerRendezvous(Rendezvous t) {
        String url = Statics.BASE_URL + "/suppRendezvousm/" + t.getId();
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
       
       
       
       
         public boolean ModifierRendezvous(Rendezvous t) {
              DateFormat destDf = new SimpleDateFormat("yyyy-MM-dd");
           String ds = destDf.format(t.getDate());
        String url = Statics.BASE_URL + "/modifierRendezvousm/" + t.getId()+"?date=" + ds + "&temps=" + t.getTemps()+ "&centre=" + t.getCentre().getId()+ "&service=" + t.getService().getId();
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
     
    
}
