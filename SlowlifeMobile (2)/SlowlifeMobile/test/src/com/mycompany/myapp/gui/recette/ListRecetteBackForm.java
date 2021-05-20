/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.recette;

import com.codename1.messaging.Message;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Recette;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.services.GestionRegime.ServiceRecette;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Yassine
 */
public class ListRecetteBackForm extends BaseForm {
    Resources res;
    public ListRecetteBackForm(Form previous) throws IOException {
        res = UIManager.initFirstTheme("/theme");
        super.addSideMenu(res);
        setTitle("List Recettes");
       
        ArrayList<Recette> ALR = ServiceRecette.getInstance().getAllRecettesBack();
        Form hi = new Form(new GridLayout(2,1));
        for(Recette rct:ALR){
            Container cnt1 = new Container(BoxLayout.y());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(hi.getWidth(), hi.getWidth() / 5, 0x00000000), true);
            Image img = URLImage.createToStorage(placeholder, rct.getImage(), "http://localhost/img/recette/"+rct.getImage(), URLImage.RESIZE_SCALE);
            Label i = new Label(img);
            
            Container nomShow = new Container(BoxLayout.x());
            Label nom=new Label(rct.getNomRecette());
            Label btnShow = stylebutton(FontImage.MATERIAL_VISIBILITY,0xf21f1f);
            nomShow.addAll(nom,btnShow);
            btnShow.addPointerPressedListener(e->{
               
                new ShowMoreForm(previous,rct).show();
            });
          
            cnt1.addAll(i,nomShow);
            Container etat = new Container(BoxLayout.x());
                 
            Container actions = new Container(BoxLayout.x());
            Label btnAccepter = stylebutton(FontImage.MATERIAL_CHECK_CIRCLE_OUTLINE,0x00FF00);
            Label btnPending = stylebutton(FontImage.MATERIAL_PENDING,0x0000FF);
            Label btnRefuser = stylebutton(FontImage.MATERIAL_HIGHLIGHT_OFF,0xf21f1f);
        
        
        
            if("Accepter".equals(rct.getEtat())){
                
                Label text = new Label("Etat:");
                Label Etat= stylebutton(FontImage.MATERIAL_PUBLISH,0xf21f1f);
                Etat.setText("Publier");
                etat.addAll(text,Etat);
                cnt1.addAll(etat);
                actions.addAll(btnPending,btnRefuser);
            }
            if("Refuser".equals(rct.getEtat()))
            {
                 Label text = new Label("Etat:");
                 Label Etat= stylebutton(FontImage.MATERIAL_UNPUBLISHED,0xf21f1f);
                 Etat.setText("Refuser");
                 etat.addAll(text,Etat);
                 cnt1.addAll(etat);
                 actions.addAll(btnAccepter,btnPending);
            }
              if("En Attente".equals(rct.getEtat()))
            {
                 Label text = new Label("Etat:");
                 Label Etat= stylebutton(FontImage.MATERIAL_PENDING,0xf21f1f);
                 Etat.setText("En Attente");  
                 etat.addAll(text,Etat);
                 cnt1.addAll(etat);
                 actions.addAll(btnAccepter,btnRefuser);
            }
             
       
            btnPending.addPointerPressedListener(e->{  
                ServiceRecette.getInstance().AccepterRefuser(new Recette(rct.getIdRecette(),"En Attente"));
                //new TwilioSMS("AC2e3f103543a521c05ddbd7f53f95f5bd", "19bef0458f6742f732df0332ec958002", "+16157518411").sendSmsAsync("+21622525030","La recette "+rct.getNomRecette()+" est mise en attente");
                Message m = new Message("La recette "+rct.getNomRecette()+" est mise en attente") {}; Display.getInstance().sendMessage(new String[] {"Yassine.benamor@esprit.tn"},"Recette mise en attente", m);
                try{new ListRecetteBackForm(previous).show();}catch(IOException Ex){}
            });
            btnAccepter.addPointerPressedListener(e->{
                ServiceRecette.getInstance().AccepterRefuser(new Recette(rct.getIdRecette(),"Accepter"));
                //new TwilioSMS("AC2e3f103543a521c05ddbd7f53f95f5bd", "19bef0458f6742f732df0332ec958002", "+16157518411").sendSmsAsync("+21622525030","La recette "+rct.getNomRecette()+" a été accepté");
                Message m = new Message("La recette "+rct.getNomRecette()+" a été accepté") {}; Display.getInstance().sendMessage(new String[] {"Yassine.benamor@esprit.tn"},"Recette a été accepté", m);
                try{new ListRecetteBackForm(previous).show();}catch(IOException Ex){}
            });
            btnRefuser.addPointerPressedListener(e->{
                ServiceRecette.getInstance().AccepterRefuser(new Recette(rct.getIdRecette(),"Refuser"));       
                //new TwilioSMS("AC2e3f103543a521c05ddbd7f53f95f5bd", "19bef0458f6742f732df0332ec958002", "+16157518411").sendSmsAsync("+21622525030","La recette "+rct.getNomRecette()+" a été refusé");
                Message m = new Message("La recette "+rct.getNomRecette()+"a été refusé") {}; Display.getInstance().sendMessage(new String[] {"Yassine.benamor@esprit.tn"},"Recette a été refusé", m);
                try{new ListRecetteBackForm(previous).show();}catch(IOException Ex){}
            });
            cnt1.add(actions);
            
            hi.add(cnt1);
        }
        addAll(hi);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
        private Label stylebutton(char image,int color)
   {
        Label btn = new Label(" ");
        btn.setUIID("NewsTopLine");
        Style style = new Style(btn.getUnselectedStyle());
        style.setFgColor(color);
        
        FontImage ajoutImage = FontImage.createMaterial(image, style);
        btn.setIcon(ajoutImage); 
        return btn;
   }
        
}
