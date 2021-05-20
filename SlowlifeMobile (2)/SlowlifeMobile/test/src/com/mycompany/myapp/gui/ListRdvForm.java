/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Centre;
import com.mycompany.entities.Rendezvous;
import com.mycompany.entities.Service;
import com.mycompany.myapp.services.ServiceCentre;
import com.mycompany.myapp.services.ServiceRendezvous;
import com.mycompany.myapp.services.ServiceService;
import java.util.ArrayList;
import java.util.Date;
import com.mycompany.myapp.BaseForm;
/**
 *
 * @author amira
 */
public class ListRdvForm extends BaseForm{

    public ListRdvForm(Resources res) {
        super.addSideMenu(res);

        setTitle("Les Rendez-vous");
         Container B = new Container();
       Button b =new Button("Ajouter",res.getImage("add.png"));
       b.setUIID("btn");
       b.addActionListener(l->{new AddRdvForm(res).show();});    
       B.add(b);
       
       add(B);
        ArrayList<Rendezvous> lc= ServiceRendezvous.getInstance().getAllRendezvous();
        for(Rendezvous c:lc)
        {  
           add(cell(c,res));
        }
    }
    
    
    
    
          public Container cell(Rendezvous p,Resources res)
   {
       Container c=new Container(BoxLayout.x());
       Container cim=new Container(BoxLayout.x());
       ImageViewer img=new ImageViewer(res.getImage("RdvIcon.png"));
       Label l1= new Label("Client:  "+p.getClient().getNom());
       Label l2= new Label("Centre: ");
       Label l22 =new Label(p.getCentre().getNom());
       Label l3= new Label("Date: ");
       String dater = new SimpleDateFormat("yyyy-MM-dd").format(p.getDate());
       Label l4= new Label(dater);
       Label l5=new Label("temps");
       String t=p.getTemps().substring(11, 16);
       Label l6=new Label(t);
       l5.setUIID("labeltitre");
       l3.setUIID("labeltitre");
       l2.setUIID("labeltitre");
       
        Button b=new Button("annuler",res.getImage("delete.png"));
            Button b1=new Button("modifier",res.getImage("edit.png"));
       Container cb=new Container(BoxLayout.x());

       Container c2=new Container(BoxLayout.y());
       c.addAll(b1,b);
       c2.addAll(l1,l2,l22,l3,l4,l5,l6,c);
       cim.addAll(img,c2);
       
       
       
       
            //supprimer
            b.addActionListener((ee)->
            {
               ServiceRendezvous sc= new ServiceRendezvous();
               sc.SupprimerRendezvous(p);
              new ListRdvForm(res).show();
            });
            
            
            
            //*********************************modifier*********************************
             b1.addActionListener((ee)->
            {Form fm= new Form(BoxLayout.y());
                fm.setTitle("Modifier Rendezvous");
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
         Label ll1=new Label("Service:");
         ll1.setUIID("labeltitre");
         Label ll2=new Label("Temps:");
         ll2.setUIID("labeltitre");
         Label ll3=new Label("Date:");
         ll3.setUIID("labeltitre");
         Button bb=new Button("Modifier");
         Button ban=new Button("Annuler");
         
         
         for(Centre cccc:lc)
        {  
          cbc.addItem(cccc.getNom());
        }
        for(Service s:ls)
        {  
           cbs.addItem(s.getNom());
        }
        cbc.setSelectedItem(p.getCentre().getNom());
                //System.out.println(p.getCentre());
        cbs.setSelectedItem(p.getService().getNom());
        cld.setSelectedDate(p.getDate());
        
        
        //cld.addActionListener((e) -> Log.p("You picked: " + new Date(cld.getSelectedDay())));
        
          ban.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListRdvForm(res).showBack();
                
            }
          });
        
         bb.addActionListener(new ActionListener() {
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
                       p.setCentre(cc);
                       p.setDate(d);
                       p.setService(sc);
                       p.setTemps(t);

                        if( ServiceRendezvous.getInstance().ModifierRendezvous(p))
                        {Dialog.show("Success","Rendezvous modifie avec succes!",new Command("OK"));
                        new ListRdvForm(res).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    
                }
                 
            }
        });
        
            fm.addAll(l,cbc,ll1,cbs,ll2,timePicker,ll3,cld,bb,ban);
              fm.show();
              
            });
             cim.setUIID("cnt");
            

       return cim;
   }
    

    
    
}
