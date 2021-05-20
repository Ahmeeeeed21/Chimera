/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Ingredient;

import com.codename1.components.SpanLabel;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.entities.Ingredient;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.services.GestionRegime.ServiceIngredient;
import java.util.ArrayList;

/**
 *
 * @author Yassine
 */
public class TrierIngredientForm extends BaseForm{
    public TrierIngredientForm(Form previous,ArrayList<Ingredient> LI) {
        setTitle("List Ingredients");
        
        Label lbTri = new Label("Tri par");
        CheckBox cbID = new CheckBox("ID");
        CheckBox cbNom = new CheckBox("Nom");
        CheckBox cbCalorie = new CheckBox("Calorie");
        Form tri = new Form(BoxLayout.y());
        tri.addAll(lbTri,cbID,cbNom,cbCalorie);
        add(tri);
        
        cbID.addActionListener(e->{
             cbCalorie.setSelected(false);
            new TrierIngredientForm(previous, ServiceIngredient.getInstance().getAllIngredients()).show();
        });
        cbNom.addActionListener(e->{
            cbCalorie.setSelected(false);
            new TrierIngredientForm(previous, ServiceIngredient.getInstance().TriParNom()).show();
        });
        cbCalorie.addActionListener(e->{
            cbNom.setSelected(false);
             new TrierIngredientForm(previous, ServiceIngredient.getInstance().TriParCalorie()).show();
        });
        
        
        Form list = new Form(BoxLayout.y());
        Form hi = new Form(new GridLayout(2,1));
        for(Ingredient ing : LI)
        {
            Container ligne = new Container(BoxLayout.y());
            Container text = new Container(BoxLayout.x());
            ligne.getStyle().setBorder(Border.createLineBorder(1));
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(hi.getWidth(), hi.getWidth() / 5, 0x00000000), true);
            Image img = URLImage.createToStorage(placeholder, ing.getImage(), "http://localhost/img/ingredient/"+ing.getImage(), URLImage.RESIZE_SCALE);
            Label lbImage = new Label(img);
            
            Label lbNom = new Label(ing.getNom());
            SpanLabel lbCalorie = new SpanLabel(String.valueOf(ing.getCalories())+"KCalorie/"+ing.getUnite());
            text.addAll(lbNom,lbCalorie);
            
            ligne.addAll(lbImage,text);
            list.add(ligne);
        }
       
        add(list);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
