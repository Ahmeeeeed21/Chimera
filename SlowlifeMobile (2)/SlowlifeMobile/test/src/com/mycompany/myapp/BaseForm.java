/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.evenement.AfficherEvenement;
import com.mycompany.myapp.gui.ListCentresForm;
import com.mycompany.myapp.gui.ListRdvForm;
import com.mycompany.myapp.gui.ListServicesForm;
import com.mycompany.myapp.gui.StatCentre;
import com.mycompany.myapp.gui.recette.HomeRecetteCoachForm;
import com.mycompany.myapp.gui.recette.ListRecetteBackForm;
import com.mycompany.myapp.gui.recette.ListRecetteForm;
import com.mycompany.myapp.gui.suivi.ListSuiviForm;
import com.mycompany.services.UtilisateurService;
import com.mycompany.utils.SessionManager;
import java.io.IOException;

/**
 * Base class for the forms with common functionality
 *
 * @author zaefdfyjhlk
 */
public class BaseForm extends Form {
    UtilisateurService us = new UtilisateurService();

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }
    
    
    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label nom = new Label(SessionManager.getNom()+" "+SessionManager.getPrenom());
        
        tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                sl,
                FlowLayout.encloseCenterBottom(
                        new Label(res.getImage("bbed5dde3094ba11206234a40f71b0a1.jpg"), "PictureWhiteBackgrond")),
                nom              
        ));
        
        
        tb.addMaterialCommandToSideMenu("Profile", FontImage.MATERIAL_SETTINGS, e -> new AcceuilForm(res).show());
        tb.addMaterialCommandToSideMenu("Mot de Passe", FontImage.MATERIAL_LOCK, e -> new AcceuilForm(res).show());
        tb.addMaterialCommandToSideMenu("Centre", FontImage.MATERIAL_DASHBOARD, e -> {new ListCentresForm(res).show();});
        tb.addMaterialCommandToSideMenu("Service", FontImage.MATERIAL_TRENDING_UP, e -> {new ListServicesForm(res).show();});
        tb.addMaterialCommandToSideMenu("Rendez-vous", FontImage.MATERIAL_CALENDAR_VIEW_DAY, e -> {new ListRdvForm(res).show();});
        tb.addMaterialCommandToSideMenu("Stat", FontImage.MATERIAL_ACCESS_TIME, e -> {new StatCentre(res).show();});
        if(SessionManager.getType()==2)
        {
                tb.addMaterialCommandToSideMenu("Recette", FontImage.MATERIAL_FASTFOOD, e -> {try {new ListRecetteForm(this).show();} catch (IOException ex) {}});
                tb.addMaterialCommandToSideMenu("Suivi", FontImage.MATERIAL_INSERT_CHART_OUTLINED, e -> new ListSuiviForm(new Form()).show());
        }
        if(SessionManager.getType()==1)
        {
            tb.addMaterialCommandToSideMenu("Recette", FontImage.MATERIAL_FASTFOOD, e ->{ try{ new ListRecetteBackForm(this).show();}catch(IOException ex){}});
        }
        if(SessionManager.getType()==3)
        {
            tb.addMaterialCommandToSideMenu("Recette", FontImage.MATERIAL_FASTFOOD, e -> new HomeRecetteCoachForm().show());
        }
        tb.addMaterialCommandToSideMenu("Evènements", FontImage.MATERIAL_EVENT_AVAILABLE, e -> {
            try {
                new AfficherEvenement(res).show();
            } catch (IOException ex) {
               // Logger.getLogger(BaseForm.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Ahmed");
            }
        });
         tb.addMaterialCommandToSideMenu("Publications Physiques", FontImage.MATERIAL_POST_ADD, e -> new ListPubPhyForm(res).show());
         tb.addMaterialCommandToSideMenu("Publications Psy", FontImage.MATERIAL_POST_ADD, e -> new ListPubPsyForm(res).show());
         tb.addMaterialCommandToSideMenu("Donner un avis", FontImage.MATERIAL_FEEDBACK, e -> new AjoutFeedForm(res).show());
         tb.addMaterialCommandToSideMenu("Réclamer", FontImage.MATERIAL_FEEDBACK, e -> new AjoutRecForm(res).show());
        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> us.logout(res));
        
 
        
    }
}
