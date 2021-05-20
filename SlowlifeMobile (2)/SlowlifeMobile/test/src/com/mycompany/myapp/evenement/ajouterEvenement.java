package com.mycompany.myapp.evenement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.entities.evenement.Evenement;

//import java.util.Date;
//import java.sql.Date;

/**
 *
 * @author Ahmed Ezzine
 */
public class ajouterEvenement extends Form {
    public ajouterEvenement(){
         //form init
         this.setTitle("Nouvel évènement");
         this.setLayout(BoxLayout.y());
         
         //widget
         
         TextField nom = new TextField("","Nom");
         TextField type = new TextField("","Type");
         TextField lieu = new TextField("","Lieu");
         TextField description = new TextField("","Description");
         Picker dateDebut = new Picker();
         Picker dateFin = new Picker();
         
         Button btnAjouter = new Button("Ajouter");
     addStringValue("",btnAjouter);
         
    /* btnAjouter.addActionListener((e) -> {
         try {
             if(nom.getText()=="" || type.getText()=="" || lieu.getText()=="" || description.getText()=="" || dateDebut.getValue()=="" || dateFin.getValue()=="")
             {
                 Dialog.show("veuillez verifiez les champs","","Annuler","Ok");
                 
                 
             }
             else {
                 InfiniteProgress ip = new InfiniteProgress();
                 
                 final Dialog iDialog = ip.showInfiniteBlocking();
               
                 
                Evenement ev = new Evenement(
                         String.valueOf(nom.getText()).toString(),
                         String.valueOf(type.getText()).toString(),
                         String.valueOf(lieu.getText()).toString(),
                         String.valueOf(description.getText()).toString(),
                         Date.(dateDebut.getValue()),
                         Date.valueOf(dateDebut.getValue())
                         
                 );
                 System.out.println("data évènement=="+ev);
                 EvenementService.getInstance().ajouterEvenement(ev);
                 iDialog.dispose();
                 refreshTheme();
             }
             
         }catch(Exception ex)
         {
             ex.printStackTrace();
         }
     }  
         );*/
     
     //getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, ev -> new BaseForm(res).showBack());
     }
    
    
     private void addStringValue(String s,Component v) {
add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
add(createLineSeparator(0xeeeee));
    }

    private Component createLineSeparator(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
