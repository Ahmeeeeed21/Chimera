/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.Log;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Centre;
import com.mycompany.entities.Rendezvous;
import com.mycompany.entities.Service;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.gui.recette.TwilioSMS;
import com.mycompany.myapp.services.ServiceCentre;
import com.mycompany.myapp.services.ServiceRendezvous;
import com.mycompany.myapp.services.ServiceService;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author amira
 */
public class AddRdvForm extends BaseForm{

    public AddRdvForm(Resources res) {
        super.addSideMenu(res);
        setTitle("Ajouter Rendezvous");
        setLayout(BoxLayout.y());
        ArrayList<Service> ls= ServiceService.getInstance().getAllServices();
        ArrayList<Centre> lc= ServiceCentre.getInstance().getAllCentres();
        
        
        Calendar cld = new Calendar();
         ComboBox cbc=new ComboBox();
         ComboBox cbs=new ComboBox();
         Picker timePicker = new Picker();
         timePicker.setType(Display.PICKER_TYPE_TIME);
         Label l=new Label("Centre:");
         l.setUIID("labeltitre");
         Label l1=new Label("Service:");
         l1.setUIID("labeltitre");
         Label l2=new Label("Temps:");
         l2.setUIID("labeltitre");
         Label l3=new Label("Date:");
         l3.setUIID("labeltitre");
         Button b=new Button("Ajouter");
         for(Centre c:lc)
        {  
          cbc.addItem(c.getNom());
        }
        for(Service s:ls)
        {  
           cbs.addItem(s.getNom());
        }
        
        //cld.addActionListener((e) -> Log.p("You picked: " + new Date(cld.getSelectedDay())));
        
        
        
         b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println(new String(""+timePicker.getTime()));
                int h=timePicker.getTime()/60;
                int m=timePicker.getTime()%60;
                //System.out.println("h: "+h+"  m: "+m);
                String hours=""+h;
                if (hours.length()<2)
                    hours="0"+h;
                String min=""+m;
                if (min.length()<2)
                    min="0"+m;
                
                String t=hours+":"+min;
                
                System.out.println(t);
                Date d=new Date(cld.getSelectedDay());
                String dc = new SimpleDateFormat("yyyy-MM-dd").format(new Date(cld.getSelectedDay()));
                
                System.out.println(new Date(cld.getSelectedDay()));
                System.out.println(cbc.getSelectedItem());
                String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                
                if ((dc.compareTo(today)<0)||(timePicker.getTime()==0))
                    Dialog.show("Alert", "veuillez remplir tous les champs!", new Command("OK"));
                else
                {    Centre cc = null;
                    Service sc = null;
                    for(Centre c:lc)
                         {  
                       if(c.getNom().compareTo((String)cbc.getSelectedItem())==0)
                         cc= c;
                         }
                       for(Service s:ls)
                       {  
                      if(s.getNom().compareTo((String)cbs.getSelectedItem())==0)
                        sc= s;
                      }
                       

                         Rendezvous r = new Rendezvous(t,cc,sc,d);
                   
            
                        if( ServiceRendezvous.getInstance().addRendezvous(r))
                        {Dialog.show("Success","Rendezvous ajoute avec succes!",new Command("OK"));
                        new TwilioSMS("AC2e3f103543a521c05ddbd7f53f95f5bd", "19bef0458f6742f732df0332ec958002", "+16157518411").sendSmsAsync("+21622525030","Un rendez-vous a été pris dans le centre "+r.getCentre().getNom());
                        new ListRdvForm(res).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    
                }
                 
            }
        });
        
        
        
        
        addAll(l,cbc,l1,cbs,l2,timePicker,l3,cld,b);
        
    }
    
    
    
}
