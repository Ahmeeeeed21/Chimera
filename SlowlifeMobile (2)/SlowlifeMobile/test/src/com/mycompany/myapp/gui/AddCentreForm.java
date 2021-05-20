/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Centre;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.services.ServiceCentre;

/**
 *
 * @author amira
 */
public class AddCentreForm extends BaseForm{

    public AddCentreForm(Resources res) {
        super.addSideMenu(res);
        setTitle("Ajouter Centre");
        setLayout(BoxLayout.y());
        
        //
        Label lNom = new Label("Nom");
        lNom.setUIID("labeltitre");
        TextField tfNom = new TextField("","Nom du centre");
        
       // TextField tftype = new TextField("","Type du centre");
       Label lt = new Label("Type");
       lt.setUIID("labeltitre");
        ComboBox c=new ComboBox();
        c.addItem("spa");
        c.addItem("centre de remise en forme");
        c.addItem("salle de sport");
        c.addItem("autre");
        Label ldes = new Label("Description");
        ldes.setUIID("labeltitre");
        TextField tfdesc = new TextField("","description du centre");
        Label ladd = new Label("Adresse");
        ladd.setUIID("labeltitre");
        TextField tfadd = new TextField("","Adresse du centre");
        Button btnValider = new Button("Ajouter");
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfdesc.getText().length()==0)||(tfadd.getText().length()==0))
                    Dialog.show("Alert", "veuillez remplir tous les champs!", new Command("OK"));
                else
                {
                    
                        Centre t = new Centre(tfNom.getText(), tfdesc.getText(),tfadd.getText(),c.getSelectedItem().toString());
                        if( ServiceCentre.getInstance().addCentre(t))
                        {Dialog.show("Success","Centre ajoute avec succes!",new Command("OK"));
                        new ListCentresForm(res).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    
                }
                 
            }
        });
         
        
        addAll(lNom,tfNom,lt,c,ladd,tfadd,ldes,tfdesc,btnValider);
    }
    
    
    
}
