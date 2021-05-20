/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.suivi;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Suivi;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.services.GestionRegime.ServiceSuivi;

/**
 *
 * @author Yassine
 */
public class EditSuiviForm extends BaseForm {
      public EditSuiviForm(Form previous,Suivi s) {

        setTitle("Edit a new Suivi");
        setLayout(BoxLayout.y());
        TextField tfPoids = new TextField(String.valueOf(s.getPoid()),"Poids");
        TextField tfTaille= new TextField(String.valueOf(s.getTaille()), "Taille");
        TextField tfEau = new TextField(String.valueOf(s.getConsommationEau()),"Consommation en eau");
        TextField tfHeure= new TextField(String.valueOf(s.getHeursActivite()), "Heure d'activité");
        Button btnValider = new Button("EDIT Suivi");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfPoids.getText().length()==0|| tfTaille.getText().length()==0 || tfEau.getText().length()==0 || tfHeure.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Suivi t = new Suivi(s.getIdSuivi(),Integer.parseInt(tfPoids.getText()),Float.parseFloat(tfTaille.getText()),Integer.parseInt(tfEau.getText()),Integer.parseInt(tfHeure.getText()));
                        if( ServiceSuivi.getInstance().updateSuivi(t)){
                            Dialog.show("Success","Edit accepted",new Command("OK"));
                            new ListSuiviForm(previous).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfPoids,tfTaille,tfEau,tfHeure,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new ListSuiviForm(previous).show()); // Revenir vers l'interface précédente
                
    }
    
    
}
