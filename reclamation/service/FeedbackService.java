/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Feedback;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmed Ezzine
 */
public class FeedbackService {
    public static FeedbackService instance=null;
     private ConnectionRequest req;
    public FeedbackService() {
         req = new ConnectionRequest();
    }

    public static FeedbackService getInstance() {
        if (instance == null) {
            instance = new FeedbackService();
        }
        return instance;
    }
    
    
    
            public void ajoutFeedback(Feedback feedback)
        {
            req = new ConnectionRequest();
                String url = Statics.BASE_URL+"/feedback/newFeed?type="+feedback.getType()+"&nbEtoiles="+feedback.getRate()+"&description="+feedback.getDescription()+"&sujet="+feedback.getSujet();
     req.setUrl(url);
            req.addResponseListener( (e) -> {
                String str = new String(req.getResponseData());
                System.out.println("data =="+str);
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
            
                    }
            
        public ArrayList<Feedback>affichageFeedBacks() {
                        req = new ConnectionRequest();
ArrayList<Feedback> result = new ArrayList<>();
String url = Statics. BASE_URL+"/publication/physique/affichagephy";
req.setUrl(url);
req.setHttpMethod("GET");
req.addResponseListener (new ActionListener<NetworkEvent>() {  
@Override

public void actionPerformed (NetworkEvent evt) {
JSONParser jsonp;
jsonp = new JSONParser();
try {
Map<String, Object>mapFeedBacks = jsonp.parseJSON (new CharArrayReader (new String (req.getResponseData() ).toCharArray()));
List<Map<String, Object>> listOfMaps =(List<Map<String, Object>>) mapFeedBacks.get("root");

for (Map<String, Object> obj : listOfMaps) {

Feedback re = new Feedback();
//dima id fi codename one float Southouha
float id = Float.parseFloat(obj.get("id").toString());
String type = obj.get("type").toString();
String description = obj.get ("description").toString();
String sujet = obj.get ("sujet").toString();
double rate = (Double.parseDouble(obj.get("nbEtoiles").toString()));

re.setId((int) id);
re.setType(type);
re.setDescription(description);
re.setSujet(sujet);
re.setRate((int)rate);


result.add(re);
  
}
    
    
}   catch (IOException ex) {
ex.printStackTrace();
}

}
});


NetworkManager.getInstance().addToQueueAndWait(req);
return result;
}

}
