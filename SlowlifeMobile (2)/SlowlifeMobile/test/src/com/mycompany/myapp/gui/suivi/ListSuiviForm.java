/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.suivi;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Suivi;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.gui.SideMenu;
import com.mycompany.myapp.services.GestionRegime.ServiceSuivi;
import java.util.ArrayList;

/**
 *
 * @author Yassine
 */
public class ListSuiviForm extends BaseForm{
     public ArrayList<Suivi> Suivis;
     private Resources res;
 
     private Container addItem(Suivi n) 
    {
      
        Form current = new Form("", BoxLayout.y());
        SpanLabel  sp = new SpanLabel();
        Container cnt1 = new Container(BoxLayout.y());
        cnt1.getStyle().setBorder(Border.createLineBorder(1));
        
        sp.setText("                      Date :   "+n.getDateSuivi()+"\nPoid :    "+n.getPoid()+" kg    Taille :     "+n.getTaille()+" m \nActivité :      "+n.getHeursActivite()+" h      Eau :     "+n.getConsommationEau()+" L \nIMC :     "+Math.round(n.getPoid()/(n.getTaille()*n.getTaille()))+"    Poids Ideal :     "+Math.round(20*(n.getTaille()*n.getTaille()))+" Kg");
                
        
      
        Label btndelete=stylebutton(FontImage.MATERIAL_DELETE,0xf21f1f,"DELETE");
        Label btnmodifier=stylebutton(FontImage.MATERIAL_EDIT,0x0000FF,"EDIT");
        
     
        
        Container btns = new Container(BoxLayout.x());
        btns.addAll(btndelete, btnmodifier);
        cnt1.addAll(sp,btns);
        Container cnt2 = new Container(BoxLayout.x());
        cnt2.add(cnt1);
        
       
         btndelete.addPointerPressedListener((e) -> {

            if (ServiceSuivi.getInstance().deleteSuivi(n.getIdSuivi())) 
            {
               new ListSuiviForm(current).show();
            } else {
                System.out.println("delete failed");
            }
        }
        );
           btnmodifier.addPointerPressedListener((e) -> {
                new EditSuiviForm(current,n).show();   
        }
        );
         
        return cnt2;
    }    
    
    public ListSuiviForm(Form previous){
        res = UIManager.initFirstTheme("/theme");
        super.addSideMenu(res);
        setTitle("List des suivis");
        Form list = new Form("Suivis:", BoxLayout.y());
        Label btnajout=stylebutton(FontImage.MATERIAL_ADD,0x008000,"NEW");
        list.add(btnajout);
        
        Suivis=ServiceSuivi.getInstance().getAllSuivi();
        
        for(Suivi Suivi:Suivis){
           list.add(addItem(Suivi));
        }
        btnajout.addPointerPressedListener(e->{
             new AddSuiviForm(previous).show();   
        });
       
        addAll(list);
        
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
