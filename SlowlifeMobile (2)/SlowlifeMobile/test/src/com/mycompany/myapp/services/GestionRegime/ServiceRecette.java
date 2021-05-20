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
import com.mycompany.entities.Recette;
import com.mycompany.entities.Utilisateur;
import com.mycompany.utils.SessionManager;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;


/**

/**
 *
 * @author Yassine
 */
public class ServiceRecette {
    public ArrayList<Recette> Recettes;
    public Recette Rct;
    public static ServiceRecette instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    private int coach;

    public ServiceRecette() {
         req = new ConnectionRequest();
         coach  = SessionManager.getId();
    }

    public static ServiceRecette getInstance() {
        if (instance == null) {
            instance = new ServiceRecette();
        }
        return instance;
    }
    public ArrayList<Recette> parseRecettes(String jsonText){
        try {
            Recettes=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Recette rct = new Recette();
                float id = Float.parseFloat(obj.get("idrecette").toString());
                rct.setIdRecette((int)id);
                rct.setImage(obj.get("image").toString());
                rct.setNomRecette(obj.get("nomrecette").toString());
                rct.setDescription(obj.get("description").toString());
                rct.setTypeRecette(obj.get("typerecette").toString());
                rct.setEtat(obj.get("etat").toString());
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                Recettes.add(rct);
              
            }    
        } catch (IOException ex) {
            System.out.println("the error is here");
        }
        return Recettes;
    }
    public ArrayList<Recette> getAllRecettes(){
        String url = Statics.BASE_URL+"/recette/ReadAllRecetteFront/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Recettes = parseRecettes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Recettes;
    }

       public ArrayList<Recette> getAllRecettesBack(){
        String url = Statics.BASE_URL+"/recette/ReadAllRecetteBack/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Recettes = parseRecettes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Recettes;
    }
       
        public ArrayList<Recette> getAllRecettesCoach(){
        String url = Statics.BASE_URL+"/recette/ReadAllRecetteCoach/?idcoach="+coach;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Recettes = parseRecettes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Recettes;
    }
       
       public ArrayList<Recette> FiltreRecettes(String rct){
        String url = Statics.BASE_URL+"/recette/FiltreRecetteFront/"+rct;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Recettes = parseRecettes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Recettes;
    }
        public ArrayList<Recette> ReadRecettesByEtat(String etat){
        String url = Statics.BASE_URL+"/recette/ReadRecetteByEtat/"+etat;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Recettes = parseRecettes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Recettes;
    }
        public ArrayList<Recette> ReadRecette(String nom){
        String url = Statics.BASE_URL+"/recette/ReadOneRecette/"+nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Recettes = parseRecettes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Recettes;
    }
        
        public boolean AccepterRefuser(Recette r) {
        String url = Statics.BASE_URL + "/recette/"+r.getIdRecette()+"/ChangerEtat?etat=" + r.getEtat();  //création de l'URL
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
        public boolean deleteRecette(Recette rct) {
        String url = Statics.BASE_URL + "/recette/DeleteRecettejson/"+rct.getIdRecette();//création de l'URL
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
        public boolean updateRecette(Recette rct) {
        String url = Statics.BASE_URL + "/recette/UpdateRecettejson/"+rct.getIdRecette()+"?nom=" + rct.getNomRecette()+ "&description=" + rct.getDescription(); //création de l'URL
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
}
