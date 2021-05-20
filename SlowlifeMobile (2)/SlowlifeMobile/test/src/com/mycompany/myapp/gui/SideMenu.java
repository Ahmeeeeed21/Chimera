/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author amira
 */
public class SideMenu extends Form {

    public SideMenu(Resources res) {
        
        BoxLayout.y();
        Image Pic = res.getImage("image23.png");
        ImageViewer im=new ImageViewer(Pic);
        
        //Label profilePicLabel = new Label("Slowlife", Pic, "SideMenuTitle");
        Container sidemenuTop = BorderLayout.center(im);
        sidemenuTop.setUIID("SideCommand");
        Label l =new Label("Slowlife");
        l.setUIID("SideCommand");
         getToolbar().addComponentToSideMenu(sidemenuTop);
         getToolbar().addComponentToSideMenu(l);
        getToolbar().addMaterialCommandToSideMenu("  Centre", FontImage.MATERIAL_DASHBOARD, e -> {new ListCentresForm(res).show();});
        getToolbar().addMaterialCommandToSideMenu("  Service", FontImage.MATERIAL_TRENDING_UP, e -> {new ListServicesForm(res).show();});
        getToolbar().addMaterialCommandToSideMenu("  Rendez-vous", FontImage.MATERIAL_CALENDAR_VIEW_DAY, e -> {new ListRdvForm(res).show();});
        getToolbar().addMaterialCommandToSideMenu("  Stat", FontImage.MATERIAL_ACCESS_TIME, e -> {new StatCentre(res).show();});
    
    }

}
