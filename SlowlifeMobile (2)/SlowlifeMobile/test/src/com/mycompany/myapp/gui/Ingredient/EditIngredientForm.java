/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Ingredient;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Ingredient;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.services.GestionRegime.ServiceIngredient;

/**
 *
 * @author Yassine
 */
public class EditIngredientForm extends BaseForm {
    public EditIngredientForm(Form previous,Ingredient ing){
        setTitle("Edit une Recette");
        setLayout(BoxLayout.y());
        TextField tfNom = new TextField(ing.getNom(),"Nom");
        TextField tfCalorie= new TextField(String.valueOf(ing.getCalories()),ing.getCalories());
        Button btnValider = new Button("EDIT INGREDIENT");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfNom.getText().length()==0|| tfCalorie.getText().length()==0)
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setId(ing.getId());
                        ingredient.setCalories(Integer.parseInt(tfCalorie.getText()));
                        ingredient.setNom(tfNom.getText());
                        if( ServiceIngredient.getInstance().updateRecette(ingredient)){
                            Dialog.show("Success","Edit accepted",new Command("OK"));
                            new ListIngredientForm(previous).show();
                        }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,tfCalorie,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> {new ListIngredientForm(previous).show();
        }); // Revenir vers l'interface précédente
                
    }
}
