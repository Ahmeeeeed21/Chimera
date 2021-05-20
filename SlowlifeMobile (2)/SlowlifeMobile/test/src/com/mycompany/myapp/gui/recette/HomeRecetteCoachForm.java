/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.recette;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.gui.Ingredient.ListIngredientForm;
import com.mycompany.myapp.gui.SideMenu;
import java.io.IOException;

/**
 *
 * @author Yassine
 */
public class HomeRecetteCoachForm extends BaseForm {
    private Resources res;
    Form current;
    public HomeRecetteCoachForm() {
        res = UIManager.initFirstTheme("/theme");
        super.addSideMenu(res);
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());
       

        add(new Label("Choose an option"));
        Button btnListRecetteCoach = new Button("List Recettes");
        Button btnListIngredient = new Button("List Ingredient");
        Button btnStat = new Button("Statistiques");
        //btnListSuivi.addActionListener(e -> new ListSuiviForm(current).show());
        btnListRecetteCoach.addActionListener(e -> {try {new ListRecetteCoachForm(current).show();} catch (IOException ex) {}});
        btnListIngredient.addActionListener(e->{new ListIngredientForm(current).show();});
        btnStat.addActionListener(e->{new Stat().createPieChartForm().show();});
        addAll(btnListRecetteCoach,btnListIngredient,btnStat);
      
}
}
