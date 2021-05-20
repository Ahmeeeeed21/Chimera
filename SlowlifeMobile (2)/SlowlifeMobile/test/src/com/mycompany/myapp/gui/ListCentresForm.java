/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Centre;
import com.mycompany.myapp.services.ServiceCentre;
import java.io.IOException;
import java.util.ArrayList;
import com.mycompany.myapp.BaseForm;
/**
 *
 * @author amira
 */
public class ListCentresForm extends BaseForm  {
    //private Resources theme;

    public ListCentresForm(Resources res)  {
        super.addSideMenu(res);
        setTitle("Les Centres");
         Container B = new Container();
//       Button b =new Button("Ajouter",res.getImage("add.png"));
//       b.setUIID("btn");
//       b.addActionListener(l->{new AddCentreForm(res).show();});    
//       B.add(b);
       
       add(B);
        ArrayList<Centre> lc= ServiceCentre.getInstance().getAllCentres();
        for(Centre c:lc)
        {  
           add(cell(c,res));
        }
        
        
        
        
        getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
            System.out.println(text);
    if(text == null || text.length() == 0) {
        // clear search
        removeAll();
        for(Centre c:lc)
        {  
           add(cell(c,res));
        }  
        
        getContentPane().animateLayout(150);
    } else {
        removeAll();
        text = text.toLowerCase();
        
        for(Centre c:lc)
        {  
            if (c.getNom().toLowerCase().contains(text))
           add(cell(c,res));
        }
        
       getContentPane().animateLayout(150);
    }
}, 4);
        
        
        
        
        
        
    }
    
    
    
    
     public Container cell(Centre p,Resources res)
   {
       Container c=new Container(BoxLayout.x());
       
       String url="http://localhost/img/centre/"+p.getImage();
       EncodedImage enc= EncodedImage.createFromImage(res.getImage("ph.png"), false);
       URLImage img=URLImage.createToStorage(enc, "c"+p.getNom(), url);
      ImageViewer im= new ImageViewer(img);
      
       Container c11=new Container(BoxLayout.x());
       Container c22=new Container(BoxLayout.x());
       Label l11= new Label("Nom:  ");
       l11.setUIID("labeltitre");
       Label l1= new Label(p.getNom());
       c11.addAll(l11,l1);
       Label l22= new Label("Type: ");
       l22.setUIID("labeltitre");
       Label l2= new Label(p.getType());
       c22.addAll(l22,l2);
       Label l3= new Label("Description: ");
       Label l4= new Label(p.getDescription());
       Button b2=new Button("voir plus");
       Container cb=new Container(BoxLayout.x());
      // cb.addAll(b,b1,b2);
       
       Container c2=new Container(BoxLayout.y());
       c2.addAll(c11,c22,l3,l4,b2);
       c.addAll(im,c2);
       
       
       b2.addActionListener((e)->{
           new DetailCentre(res,p).show();
           /*
           Form f3= new Form(BoxLayout.y());
           ImageViewer imd= new ImageViewer(img);
           Container cdetn=new Container(BoxLayout.x());
           Container cdett=new Container(BoxLayout.x());
            Label l1d= new Label("Nom:  ");
            l1d.setUIID("labeltitre");
            Label l11d= new Label(p.getNom());
            cdetn.addAll(l1d,l11d);
            Label l2d= new Label("Type: ");
            l2d.setUIID("labeltitre");
            Label l22d= new Label(p.getType());
            cdett.addAll(l2d,l22d);
            Label l3d= new Label("Description: ");
            l3d.setUIID("labeltitre");
            Label l4d= new Label(p.getDescription());
            Label l5= new Label("Adresse: ");
            l5.setUIID("labeltitre");
            Label l6= new Label(p.getLieu());
           // Button b=new Button("supprimer",res.getImage("delete.png"));
           // Button b1=new Button("modifier",res.getImage("edit.png"));
           
            f3.addAll(imd,cdetn,cdett,l3d,l4d,l5,l6);
           /* 
            //supprimer
            b.addActionListener((ee)->
            {
               ServiceCentre sc= new ServiceCentre();
               sc.SupprimerCentre(p);
              new ListCentresForm(res).show();
            });
         */
            
            
            
            //*********************************modifier*********************************
            /*
             b1.addActionListener((ee)->
            {Form fm= new Form(BoxLayout.y());
                fm.setTitle("Modifier Centre");
                   setLayout(BoxLayout.y());
             Label lNom = new Label("Nom");
              lNom.getStyle().setFgColor(0x58cc61);
          
              TextField tfNom = new TextField(p.getNom(),"Nom du centre");
              Label lt = new Label("Type");
       lt.setUIID("labeltitre");
        ComboBox cbb=new ComboBox();
        cbb.addItem("spa");
        cbb.addItem("centre de remise en forme");
        cbb.addItem("salle de sport");
        cbb.addItem("autre");
        cbb.setSelectedItem(p.getType());
         Label ldes = new Label("Description");
        ldes.setUIID("labeltitre");
        TextField tfdesc = new TextField(p.getDescription(),"description du centre");
        Label ladd = new Label("Adresse");
        ladd.setUIID("labeltitre");
        TextField tfadd = new TextField(p.getLieu(),"Adresse du centre");
        Button btnValider = new Button("Modifier");
         Button ban=new Button("Annuler");

       ban.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListServicesForm(res).showBack();
                
            }
          });
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfdesc.getText().length()==0)||(tfadd.getText().length()==0))
                    Dialog.show("Alert", "veuillez remplir tous les champs!", new Command("OK"));
                else
                {
                        p.setDescription(tfdesc.getText());
                        p.setNom(tfNom.getText());
                        p.setLieu(tfadd.getText());
                        p.setType(cbb.getSelectedItem().toString());
                        
                        
                        if( ServiceCentre.getInstance().ModifierCentre(p))
                        {Dialog.show("Success","Centre modifie avec succes!",new Command("OK"));
                        new ListCentresForm(res).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    
                }
                 
            }
        });
         
        
            fm.addAll(lNom,tfNom,lt,cbb,ladd,tfadd,ldes,tfdesc,btnValider,ban);
          // fm.add(cbb)
              fm.show();
              
            });
             
            
            
            
           // f3.getToolbar().addCommandToLeftBar("Back", null, ev->{f.show();});
            f3.show();
*/
            
       });
       
      
       c.setUIID("cnt");
       return c;
   }
     
    
}
