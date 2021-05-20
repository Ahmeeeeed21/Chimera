/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Centre;
import com.mycompany.myapp.BaseForm;

/**
 *
 * @author amira
 */
public class DetailCentre extends BaseForm{
    
    public DetailCentre(Resources res,Centre p) {
        super.addSideMenu(res);
        setTitle("Details");
        setLayout(BoxLayout.y());
        String url="http://localhost/img/centre/"+p.getImage();
       EncodedImage enc= EncodedImage.createFromImage(res.getImage("ph.png"), false);
       URLImage img=URLImage.createToStorage(enc, "c"+p.getNom(), url);
       ImageViewer im= new ImageViewer(img);
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
            Button b=new Button("Partager avec un ami");
           // Button b1=new Button("modifier",res.getImage("edit.png"));
           
           b.addActionListener(l->{
                Message m = new Message("check out this center!\nNom: "+p.getNom()+"\nadresse: "+p.getLieu()+"\nDescription:"+p.getDescription());
                
            Display.getInstance().sendMessage(new String[] {"yourfriend@gmail.com"}, "Subject of message", m); 
           });
           
            addAll(imd,cdetn,cdett,l3d,l4d,l5,l6,b);
    }
    
}
