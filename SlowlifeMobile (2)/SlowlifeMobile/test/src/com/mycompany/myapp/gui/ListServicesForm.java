/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Service;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.services.ServiceService;
import java.util.ArrayList;

/**
 *
 * @author amira
 */
public class ListServicesForm extends BaseForm{
    //private Resources theme;

    public ListServicesForm(Resources res) {
        
        super.addSideMenu(res);
        
        
         Tabs t = new Tabs();

        setTitle("Les Services");
                 Container B = new Container();
                 Container B2 = new Container();
                  Container B3 = new Container();
                   Container B4 = new Container();
                   Container B5 = new Container();
                   
//         TextField tfr=new TextField();
//         Button bt= new Button("Recherche");
//         Container Br= new Container(BoxLayout.x());
//         add(tfr);
//         add(bt);
         
  
         
         
             
             
               ArrayList<Service> lc= ServiceService.getInstance().getAllServices();
               
        
        for(Service c:lc)
        {  
           B.add(cell(c,res));
        }
        for(Service c:lc)
        {  if (c.getType().toLowerCase().compareTo("yoga")==0)
           B2.add(cell(c,res));
        }
        for(Service c:lc)
        {  if (c.getType().toLowerCase().compareTo("autre")==0)
           B5.add(cell(c,res));
        }
        for(Service c:lc)
        {  if (c.getType().toLowerCase().compareTo("massage")==0)
           B3.add(cell(c,res));
        }
        for(Service c:lc)
        {  if (c.getType().toLowerCase().compareTo("seance meditation")==0)
           B4.add(cell(c,res));
        }

         t.addTab("Tous", B);
         t.addTab("Yoga", B2);
         t.addTab("Massage", B3);
         t.addTab("Meditation", B4);
        // t.addTab("Sport", B2);
         t.addTab("Autre", B5);
         add(t);
         
               getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
            System.out.println(text);
    if(text == null || text.length() == 0) {
        // clear search
        removeAll();
       // t.removeAll();
        B.removeAll();
        B2.removeAll();
        B3.removeAll();
        B4.removeAll();
        B5.removeAll();
        for(Service c:lc)
        {  
           B.add(cell(c,res));
        }
        for(Service c:lc)
        {  if (c.getType().toLowerCase().compareTo("yoga")==0)
           B2.add(cell(c,res));
        }
        for(Service c:lc)
        {  if (c.getType().toLowerCase().compareTo("autre")==0 || c.getType().toLowerCase().compareTo("sport")==0)
           B5.add(cell(c,res));
        }
        for(Service c:lc)
        {  if (c.getType().toLowerCase().compareTo("massage")==0)
           B3.add(cell(c,res));
        }
        for(Service c:lc)
        {  if (c.getType().toLowerCase().compareTo("seance meditation")==0)
           B4.add(cell(c,res));
        }
//         t.addTab("Tous", B);
//         t.addTab("Yoga", B2);
//         t.addTab("Massage", B3);
//         t.addTab("Meditation", B4);
//        // t.addTab("Sport", B2);
//         t.addTab("Autre", B5);
        add(t);
        
        getContentPane().animateLayout(150);
    } else {
        ArrayList<Service> k= ServiceService.getInstance().getRechercheServices(text);
        removeAll();
       // t.removeAll();
         B.removeAll();
        B2.removeAll();
        B3.removeAll();
        B4.removeAll();
        B5.removeAll();
        text = text.toLowerCase();
        
       for(Service c:k)
        {  
           B.add(cell(c,res));
        }
        for(Service c:k)
        {  if (c.getType().toLowerCase().compareTo("yoga")==0)
           B2.add(cell(c,res));
        }
        for(Service c:k)
        {  if (c.getType().toLowerCase().compareTo("autre")==0 || c.getType().toLowerCase().compareTo("sport")==0)
           B5.add(cell(c,res));
        }
        for(Service c:k)
        {  if (c.getType().toLowerCase().compareTo("massage")==0)
           B3.add(cell(c,res));
        }
        for(Service c:k)
        {  if (c.getType().toLowerCase().compareTo("seance meditation")==0)
           B4.add(cell(c,res));
        }
//         t.addTab("Tous", B);
//         t.addTab("Yoga", B2);
//         t.addTab("Massage", B3);
//         t.addTab("Meditation", B4);
//        // t.addTab("Sport", B2);
//         t.addTab("Autre", B5);
        add(t);
        
       getContentPane().animateLayout(150);
    }
}, 4);
          
          
       
        

    }
    
    
   
    
    
    
    
         public Container cell(Service p,Resources res)
   {
       Container c=new Container(BoxLayout.x());
        ImageViewer im= new ImageViewer(res.getImage("service.png"));
       Container cim=new Container(BoxLayout.x());
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
       l3.setUIID("labeltitre");
       Label l4= new Label(p.getDescription());
       
       
       
        //Button b=new Button("supprimer",res.getImage("delete.png"));
         //   Button b1=new Button("modifier",res.getImage("edit.png"));
       Container cb=new Container(BoxLayout.x());

       Container c2=new Container(BoxLayout.y());
       
      // c.addAll(b1,b);
       c2.addAll(c11,c22,l3,l4);
       cim.addAll(im,c2);
       
       
       
       
            //supprimer
//            b.addActionListener((ee)->
//            {
//               ServiceService sc= new ServiceService();
//               sc.SupprimerService(p);
//              new ListServicesForm(res).show();
//            });
//            
            
            
            //*********************************modifier*********************************
            /*
             b1.addActionListener((ee)->
            {Form fm= new Form(BoxLayout.y());
                fm.setTitle("Modifier service");
                   setLayout(BoxLayout.y());
             Label lNom = new Label("Nom");
              lNom.getStyle().setFgColor(0x58cc61);
          
              TextField tfNom = new TextField(p.getNom(),"Nom du service");
        ComboBox cbb=new ComboBox();
        cbb.addItem("massage");
        cbb.addItem("sport");
        cbb.addItem("seance meditation");
        cbb.addItem("autre");
        cbb.setSelectedItem(p.getType());
        TextField tfdesc = new TextField(p.getDescription(),"description du service");
        
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
                //System.out.println("ici");
                if ((tfNom.getText().length()==0)||(tfdesc.getText().length()==0))
                    Dialog.show("Alert", "veuillez remplir tous les champs!", new Command("OK"));
                else
                {
                        p.setDescription(tfdesc.getText());
                        p.setNom(tfNom.getText());
                        p.setType(cbb.getSelectedItem().toString());

                        if( ServiceService.getInstance().ModifierService(p))
                        { 
                            Dialog.show("Success","Service modifie avec succes!",new Command("OK"));
                        new ListServicesForm(res).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    
                }
                 
            }
        });
         
        
            fm.addAll(lNom,tfNom,cbb,tfdesc,btnValider,ban);
              fm.show();
              
            });
             */
            // System.out.println("ici");
            
      cim.setUIID("cnt");
       return cim;
   }
    
    
    
    
    
}
