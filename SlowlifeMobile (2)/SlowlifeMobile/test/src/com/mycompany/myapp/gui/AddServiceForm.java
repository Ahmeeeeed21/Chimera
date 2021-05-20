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
import com.mycompany.entities.Service;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.services.ServiceCentre;
import com.mycompany.myapp.services.ServiceService;

/**
 *
 * @author amira
 */
public class AddServiceForm  extends BaseForm {

    public AddServiceForm(Resources res) {
        super.addSideMenu(res);
         setTitle("Ajouter Service");
        setLayout(BoxLayout.y());
        
        //
        Label lNom = new Label("Nom");
           //lNom.getStyle().setFgColor(0x58cc61);
           lNom.setUIID("labeltitre");
           Label ldesc= new Label("Descripstion");
           ldesc.setUIID("labeltitre");
           
          // lNom.getStyle().set
        TextField tfNom = new TextField("","Nom du Service..");
        Label ltype=new Label("Type");
        ltype.setUIID("labeltitre");
        ComboBox cbb=new ComboBox();
        cbb.addItem("massage");
        cbb.addItem("sport");
        cbb.addItem("seance meditation");
        cbb.addItem("autre");
        
        TextField tfdesc = new TextField("","description du service..");
        
        Button btnValider = new Button("Ajouter");
        //btnValider.setMaxAutoSize(100);
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfdesc.getText().length()==0))
                    Dialog.show("Alert", "veuillez remplir tous les champs!", new Command("OK"));
                else
                {
                    
                        Service t = new Service(tfNom.getText(), tfdesc.getText(),cbb.getSelectedItem().toString());
                        if( ServiceService.getInstance().addService(t))
                        {Dialog.show("Success","Service ajoute avec succes!",new Command("OK"));
                        new ListServicesForm(res).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
                    
                }
                 
            }
        });
         
        
        addAll(lNom,tfNom,ltype,cbb,ldesc,tfdesc,btnValider);
        
    }
    
    
}
