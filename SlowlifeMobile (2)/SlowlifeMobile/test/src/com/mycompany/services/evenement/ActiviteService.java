package com.mycompany.services.evenement;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.evenement.Activite;
import com.mycompany.myapp.entities.evenement.Evenement;

import com.mycompany.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Ahmed Ezzine
 */
public class ActiviteService {
    private ConnectionRequest req;
     public static ActiviteService instance=null;
            ArrayList<Activite> activs = new ArrayList<Activite>();

    public ActiviteService() {
         req = new ConnectionRequest();
    }

    public static ActiviteService getInstance() {
        if (instance == null) {
            instance = new ActiviteService();
        }
        return instance;
    }
    
    public ArrayList<Activite> getEventActivities(int idEvent) {
        String url = Statics.BASE_URL + "/evenement/getEventActivities/"+idEvent;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                    activs = parseJSONAction(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                  //  Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return activs;
    }
    
    public ArrayList<Activite> parseJSONAction(String textJson) throws ParseException{
                ArrayList<Activite> activs = new ArrayList<Activite>();

        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> tasksList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
            
            
            for (Map<String, Object> obj : tasksList) {
                
                Activite v = new Activite();
                
            float id = Float.parseFloat(obj.get("id").toString());
            v.setIdEvent((int) id);
            v.setNom(obj.get("nom").toString());
            v.setType(obj.get("type").toString());
            v.setDescription(obj.get("description").toString());
            double duree = (double)obj.get("duree");
            v.setDuree((int) duree);
                activs.add(v);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return activs;  
    }
}
