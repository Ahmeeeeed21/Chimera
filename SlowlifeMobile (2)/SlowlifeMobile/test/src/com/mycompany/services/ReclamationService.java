/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.entities.Feedback;
import com.mycompany.entities.Reclamation;
import com.mycompany.utils.Statics;

/**
 *
 * @author Wassim
 */
public class ReclamationService {
        public static ReclamationService instance=null;
     private ConnectionRequest req;
    public ReclamationService() {
         req = new ConnectionRequest();
    }

    public static ReclamationService getInstance() {
        if (instance == null) {
            instance = new ReclamationService();
        }
        return instance;
    }
    
    
    
            public void ajoutReclamation(Reclamation reclamation)
        {
            req = new ConnectionRequest();
                String url = Statics.BASE_URL+"/reclamation/newRec?description="+reclamation.getDescription()+"&sujet="+reclamation.getSujet()+"&etat="+reclamation.getEtat();
     req.setUrl(url);
            req.addResponseListener( (e) -> {
                String str = new String(req.getResponseData());
                System.out.println("data =="+str);
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
            
                    }
}
