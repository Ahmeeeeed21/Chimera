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
import com.mycompany.entities.Ingredient;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Yassine
 */
public class ServiceIngredient {
    public ArrayList<Ingredient> Ingredients;
    public static ServiceIngredient instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceIngredient() {
         req = new ConnectionRequest();
    }

    public static ServiceIngredient getInstance() {
        if (instance == null) {
            instance = new ServiceIngredient();
        }
        return instance;
    }
    public ArrayList<Ingredient> parseIngredient(String jsonText){
        try {
            Ingredients=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Ingredient rct = new Ingredient();
                float id = Float.parseFloat(obj.get("id").toString());
                rct.setId((int)id);
                rct.setImage(obj.get("image").toString());
                rct.setNom(obj.get("nom").toString());
                rct.setCalories((int)Float.parseFloat(obj.get("calorie").toString()));
                rct.setUnite(obj.get("unite").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                Ingredients.add(rct);
              
            }    
        } catch (IOException ex) {
            
        }
        return Ingredients;
    }
    public ArrayList<Ingredient> getAllIngredients(){
        String url = Statics.BASE_URL+"/ingredient/ReadIngredient/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Ingredients = parseIngredient(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Ingredients;
    }
        public ArrayList<Ingredient> TriParNom(){
        String url = Statics.BASE_URL+"/ingredient/TriParNom/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Ingredients = parseIngredient(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Ingredients;
    }
        public ArrayList<Ingredient> TriParCalorie(){
        String url = Statics.BASE_URL+"/ingredient/TriParCalorie/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Ingredients = parseIngredient(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Ingredients;
    }
        public ArrayList<Ingredient> getOneSuivi(String nom){
        String url = Statics.BASE_URL+"/suivi/ReadBySuivi/"+nom;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Ingredients = parseIngredient(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Ingredients;
    }
        public boolean updateRecette(Ingredient ing) {
        String url = Statics.BASE_URL + "/ingredient/UpdateIngredientjson/"+ing.getId()+"?nom=" + ing.getNom()+"&calorie="+ing.getCalories(); //création de l'URL
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
