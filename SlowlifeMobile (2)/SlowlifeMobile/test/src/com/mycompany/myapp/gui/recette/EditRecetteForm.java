/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.recette;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Recette;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.services.GestionRegime.ServiceRecette;
import java.io.IOException;

/**
 *
 * @author Yassine
 */
public class EditRecetteForm extends BaseForm {
    Resources res;
     public EditRecetteForm(Form previous,Recette rct) {
        res = UIManager.initFirstTheme("/theme");
        super.addSideMenu(res);
   
        setTitle("Edit une Recette");
        setLayout(BoxLayout.y());
        TextField tfNom = new TextField(rct.getNomRecette(),"Nom");
        Label Desc = new Label("Description");
        TextArea tfDescription= new TextArea(rct.getDescription());
        Button btnValider = new Button("EDIT RECETTE");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().length()==0|| tfDescription.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Recette t = new Recette();
                        t.setIdRecette(rct.getIdRecette());
                        t.setNomRecette(tfNom.getText());
                        t.setDescription(tfDescription.getText());
                        if( ServiceRecette.getInstance().updateRecette(t)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                            new ListRecetteCoachForm(previous).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    } catch (IOException ex) {
                       
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,Desc,tfDescription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> {try {new ListRecetteCoachForm(previous).show();} catch (IOException ex) {}
        }); // Revenir vers l'interface précédente
                
    }
    
    
}
