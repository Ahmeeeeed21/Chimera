/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.recette;

import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
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
public class SearchForm extends BaseForm{
    Resources res;
     public SearchForm(Form previous,ArrayList<Recette> ALR) throws IOException {
        res = UIManager.initFirstTheme("/theme");
        super.addSideMenu(res);
        setTitle("List Recettes");
        Container page = new Container(BoxLayout.y());
        TextField tfRecherche = new TextField("","Recherche");
        Label btnRecherche=stylebutton(FontImage.MATERIAL_SEARCH,0xf21f1f,"Rechercher");
        Form hi = new Form(new GridLayout(2,1));
        for(Recette rct:ALR){
            Container cnt1 = new Container(BoxLayout.y());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(hi.getWidth(), hi.getWidth() / 5, 0x00000000), true);
            Image img = URLImage.createToStorage(placeholder, rct.getImage(), "http://localhost/img/recette/"+rct.getImage(), URLImage.RESIZE_SCALE);
            Label i = new Label(img);
            Container cnt2 = new Container(BoxLayout.x());
            Label nom=new Label(rct.getNomRecette());
            Label btnShow = stylebutton(FontImage.MATERIAL_VISIBILITY,0x008700);
            Label btnmodifier=stylebutton(FontImage.MATERIAL_EDIT,0x0000FF);
            Label btndelete=stylebutton(FontImage.MATERIAL_DELETE,0xf21f1f);
        
            
            cnt2.addAll(nom,btnShow,btndelete,btnmodifier);
            
            btnShow.addPointerPressedListener(e->{
                new ShowMoreForm(previous,rct).show();
            });
              btnmodifier.addPointerPressedListener(e->{
                new EditRecetteForm(previous,rct).show();
            });
            btndelete.addPointerPressedListener(e->{
                ServiceRecette.getInstance().deleteRecette(rct);try{new ListRecetteCoachForm(previous).show();}catch(IOException ex){}
            });       
            cnt1.addAll(i,cnt2);
            
            hi.addAll(cnt1);
        }
        page.addAll(tfRecherche,btnRecherche,hi);
           btnRecherche.addPointerPressedListener(e->{
                    if(tfRecherche.getText().length()==0){
                       try{new ListRecetteCoachForm(previous).showBack();}catch(Exception ex){}
                    }
                    else{
                    try {new SearchForm(previous,ServiceRecette.getInstance().ReadRecette(tfRecherche.getText())).show();} catch (IOException ex) {}
                    }
            });
        addAll(page);
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
           private Label stylebutton(char image,int color,String text)
   {
        Label btn = new Label(" ");
        btn.setUIID("NewsTopLine");
        Style ajoutstyle = new Style(btn.getUnselectedStyle());
        ajoutstyle.setFgColor(color);
        
        FontImage ajoutImage = FontImage.createMaterial(image, ajoutstyle);
        btn.setIcon(ajoutImage); 
      
        btn.setText(text);
        return btn;
   }

}
