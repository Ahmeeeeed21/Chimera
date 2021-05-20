/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.recette;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Recette;
import com.mycompany.myapp.BaseForm;

/**
 *
 * @author Yassine
 */
public class ShowMoreForm extends BaseForm{
    Resources res;
    public ShowMoreForm(Form previous,Recette rct){
        res = UIManager.initFirstTheme("/theme");
        super.addSideMenu(res);
        Container cnt1 = new Container(BoxLayout.y());
       
        setTitle(rct.getNomRecette());
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(500, 500 , 0x00000000), true);
        Image img = URLImage.createToStorage(placeholder, rct.getImage(), "http://localhost/img/recette/"+rct.getImage(), URLImage.RESIZE_SCALE);
        Label i = new Label(img);
        SpanLabel Description = new SpanLabel("Comment la preparé \n"+rct.getDescription());
        cnt1.addAll(i,Description);
        
        addAll(cnt1);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
