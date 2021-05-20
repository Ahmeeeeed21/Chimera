/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp.gui.recette;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
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
public class ListRecetteForm extends BaseForm{
    Resources res;
     public ListRecetteForm(Form previous) throws IOException {
        res = UIManager.initFirstTheme("/theme");
        super.addSideMenu(res);
        setTitle("List Recettes");
        
        
        Form nav = new Form(BoxLayout.x());
        Button btnDessert = new Button("Dessert");
        Button btnPlat = new Button("Plat");
        Button btnAccompagnement = new Button("Accompagnement");
        Button btnAmuse = new Button("Amuse bouche");
        Button btnBoisson = new Button("Boisson");
        Button btnSauce = new Button("Sauce");
        Button btnEntrée = new Button("Entrée");
        Button btnTout = new Button("Tout");
        ComboBox<String> cbtype = new ComboBox<>(
                "Tout",
                "Dessert",
                "Plat",
                "Accompagnement",
                "Amuse bouche",
                "Boisson",
                "Sauce",
                "Entrée"
                );
        ArrayList<Recette> ALR = ServiceRecette.getInstance().getAllRecettes();
        Form hi = new Form(new GridLayout(2,1));
        for(Recette rct:ALR){
            Container cnt1 = new Container(BoxLayout.y());
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(hi.getWidth(), hi.getWidth() / 5, 0x00000000), true);
            Image img = URLImage.createToStorage(placeholder, rct.getImage(), "http://localhost/img/recette/"+rct.getImage(), URLImage.RESIZE_SCALE);
            Label i = new Label(img);
            Container cnt2 = new Container(BoxLayout.x());
            Label nom=new Label(rct.getNomRecette());
            Label btnShow = stylebutton(FontImage.MATERIAL_VISIBILITY,0x008700);
        
            cnt2.addAll(nom,btnShow);
            btnShow.addPointerPressedListener(e->{
                new ShowMoreForm(previous,rct).show();
            });
            cnt1.addAll(i,cnt2);
            
            hi.add(cnt1);
        }
          
        cbtype.addActionListener(e->{
            if(cbtype.getSelectedItem()!="Tout"){
                 if(cbtype.getSelectedItem()=="Entrée"){
                    try {
                        new FiltreRecetteForm(previous,ServiceRecette.getInstance().FiltreRecettes("Entr%C3%A9e"),"Entr%C3%A9e").show();
                    } catch (IOException ex) {}
                }
                 else{
                try {
                    new FiltreRecetteForm(previous, ServiceRecette.getInstance().FiltreRecettes(cbtype.getSelectedItem()),cbtype.getSelectedItem()).show();
                } catch (IOException ex) {}
                System.out.println(ServiceRecette.getInstance().FiltreRecettes(cbtype.getSelectedItem()));
                 }
            } 
        });
        addAll(cbtype,hi);
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
