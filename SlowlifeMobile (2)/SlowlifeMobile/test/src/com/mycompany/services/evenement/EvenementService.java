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
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.evenement.Evenement;
import com.mycompany.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.sql.DataSource;

/**
 *
 * @author Ahmed Ezzine
 */
public class EvenementService {
  public ArrayList<Evenement> events;
  public static EvenementService instance=null;
  public boolean resultOK;
  private ConnectionRequest req;
  ArrayList<Evenement> evenements = new ArrayList<Evenement>();
  
   public EvenementService() {
         req = new ConnectionRequest();
    }

    public static EvenementService getInstance() {
        if (instance == null) {
            instance = new EvenementService();
        }
        return instance;
    }
    
    
    
    
   /* public ArrayList<Evenement> parseEvents(String jsonText){
        try {
            
            evenements =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> events = (List<Map<String,Object>>)tasksListJson.get("root");
            for (Map<String, Object> obj : events) {
                Evenement e = new Evenement();
                e.setId((int)Float.parseFloat(evenement.get("id").toString()));
                e.setUserId((int)Float.parseFloat(((Map<String, Object>)evenement.get("user")).get("id").toString()));
                e.setText(evenement.get("text").toString());
                
                evenements.add(p);
            }
            
            
            } catch (IOException ex) {

            }
            return publications;
        }
    
    public ArrayList<Publication> getAllPubs() {
        
        String url = Statics.BASE_URL+"/api/user/pubs";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
         
                publications = parsePubs(new String(req.getResponseData()));

                req.removeResponseListener(this);
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);    
        return publications;
    }
    */
    
    
    
   
     public ArrayList<Evenement> getAllEventsByUser(int userId) {
        String url = Statics.BASE_URL + "/evenement/myEvents/"+userId;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                    events = parseJSONAction(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                  //  Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return events;
    }
    
    
    public ArrayList<Evenement> getAllEvents() {
        String url = Statics.BASE_URL + "/evenement/afficherttEvent";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                try {
                    events = parseJSONAction(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (ParseException ex) {
                  //  Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return events;
    }
     
     
     
      public ArrayList<Evenement> parseJSONAction(String textJson) throws ParseException{
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> tasksList = (ArrayList<Map<String,Object>>) tasksListJson.get("root");
            
            
            for (Map<String, Object> obj : tasksList) {
                
                Evenement v = new Evenement();
                
                
                float idEvent = Float.parseFloat(obj.get("id").toString());
                v.setIdEvent((int) idEvent);  
            v.setNom(obj.get("nom").toString());
            v.setLieu(obj.get("lieu").toString());
            v.setType(obj.get("type").toString());
            v.setDescription(obj.get("description").toString());
         
           v.setDateDeb(obj.get("dateDebut").toString());
           v.setDateFin(obj.get("dateFin").toString());
           double parts = (double)obj.get("participants");
           v.setParticipants((int) parts);
            //v.setIdUser((int)Float.parseFloat(obj.get("iduser").toString()));
          // v.setUserId((int)Float.parseFloat(((Map<String, Object>)v.get("user")).get("id").toString()));
                
                evenements.add(v);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return evenements;  
    }
     
        
        /*  public ArrayList<Evenement> parseEvenement(String jsonText) throws ParseException {
        try {
            events = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

java.util.List<Map<String, Object>> list =(java.util.List<Map<String, Object>>) tasksListJson.get("root");

for (Map<String, Object> obj : list) {
            
            Evenement v = new Evenement();
            float idEvent =Float.parseFloat(obj.get("id").toString());
           v.setIdEvent((int) idEvent);  
            v.setNom(obj.get("nom").toString());
            v.setType(obj.get("type").toString());
            v.setDescription(obj.get("Description").toString());
            v.setLieu(obj.get("lieu").toString());
            Map map1 = ((Map) obj.get("date_debut"));
            Map map = ((Map) obj.get("date_fin")) ;
            Date date1 = new Date((((Double)map1.get("timestamp")).longValue()*1000)); 
            Date date = new Date((((Double)map.get("timestamp")).longValue()*1000)); 
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s = formatter.format(date);
            String s1 = formatter.format(date1);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date datedebut = new Date();
            Date datefin = new Date();
            datedebut = df.parse(s);
            datefin = df.parse(s1);
            v.setIdUser((int)Float.parseFloat(obj.get("idUser").toString()));
           
                  
                events.add(v);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return events;
    }*/

    
//    public ArrayList<Evenement> getAllEvent (){
//     //   String url = Statics.BASE_URL+"/tasks/";
//        String url = Statics.BASE_URL +"afficherttEvent" ;
//        
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                events = parseFeature(new String(req.getResponseData()));
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return events ;
//
//    
//    
//}
}
