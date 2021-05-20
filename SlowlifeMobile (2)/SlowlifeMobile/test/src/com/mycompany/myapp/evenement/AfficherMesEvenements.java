package com.mycompany.myapp.evenement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.BaseForm;
import com.mycompany.myapp.entities.evenement.Activite;
import com.mycompany.myapp.entities.evenement.Evenement;
import com.mycompany.myapp.entities.evenement.ParticipationEvent;
import com.mycompany.services.evenement.ActiviteService;
import com.mycompany.services.evenement.EvenementService;
import com.mycompany.utils.SessionManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed Ezzine
 */
public class AfficherMesEvenements extends BaseForm{
    
     List l = new ArrayList<ParticipationEvent>();
     Resources theme;
     /*public AfficherEvenement() throws IOException {
         //form init
         
         this.setTitle("Liste des évènements");
         this.setLayout(BoxLayout.y());
         
         //widgets
         SpanLabel sp = new SpanLabel();
        sp.setText(EvenementService.getInstance().getAllEvents().toString());
          this.add(sp);   
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new SideMenu().showBack());
          
        
     
     */
     
     
     
     
     
     
     
     public AfficherMesEvenements(Resources res) throws IOException {  
         
          super.addSideMenu(res);
          this.setLayout(BoxLayout.y());
         
     
          theme = UIManager.initFirstTheme("/theme");
            this.getToolbar().setUIID("tb");
            
     
            
        Label logi = new Label("Mes Evenements");
        logi.setUIID("login");
        this.add(logi);
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new BaseForm(res).showBack());
       
    
     for(Evenement c:new EvenementService().getAllEventsByUser(SessionManager.getId())){
           
 
          this.add(addItem(c,res));   
          
          System.out.println("Evenement actuelle0"+c.getIdEvent());
           Label separator = new Label("-------------------------------------","BlackSeparator");
        separator.setShowEvenIfBlank(true);
        this.add(separator);  
    
 
        }
     }
     
     
  public Container addItem(Evenement e,Resources res) throws IOException{
        Container cn1=new Container(new BorderLayout());
        Container cn2=new Container(BoxLayout.y());
        Container cn3 = new Container(BoxLayout.y());
              Label titre=new Label(e.getNom());
          Label libelle_titre = new Label("LIBELLE");
          libelle_titre.setUIID("type1");
        Label nbrparticipant=new Label(String.valueOf(e.getParticipants()));
        Label libelle_nbrparticipant = new Label("Nombre Participant");
          libelle_nbrparticipant.setUIID("type1");
       
          
        Label lieu=new Label(e.getLieu());
        Label libelle_lieu = new Label("Lieu");
          libelle_lieu.setUIID("type1");
        Label description=new Label(e.getDescription());
          Label libelle_discription = new Label("Description");
          libelle_discription.setUIID("type1");
        Label dd=new Label(String.valueOf(e.getDateDeb()));
        Label libelle_dd = new Label("Date debut");
          libelle_dd.setUIID("type1");
        Label df=new Label(String.valueOf(e.getDateFin()));
        Label libelle_df = new Label("Date Fin");
          libelle_df.setUIID("type1");
       
       

       Button btn=new Button("Details");
        btn.setUIID("vtnvalid");
       

        cn2.add(libelle_titre).add(titre);
        
        cn2.add(libelle_lieu).add(lieu);
        cn2.add(libelle_discription).add(description);
        cn2.add(libelle_nbrparticipant).add(nbrparticipant);
        cn2.add(libelle_dd).add(dd);
        cn2.add(libelle_df).add(df);
        cn3.add(btn);
        cn2.add(cn3);
        cn1.add(BorderLayout.WEST, cn2);
      
        btn.addActionListener(e1->{
        
        Form  f2=new Form("Details",BoxLayout.y());
          Label logi = new Label("Dtails");
        logi.setUIID("login");
        f2.add(logi);
        Label titrem=new Label(e.getNom());
        System.out.println("Nom de l évènement"+e.getNom());
        Label nbrparticipantm=new Label(String.valueOf(e.getParticipants()));
        Label lieum=new Label(e.getLieu());
        Label descriptionm=new Label(e.getDescription());
        Label ddm=new Label(String.valueOf(e.getDateDeb()));
        Label dfm=new Label(String.valueOf(e.getDateFin()));
   
        ArrayList<Activite> activs = new ArrayList<>();
        activs = ActiviteService.getInstance().getEventActivities((int)e.getIdEvent());
        
        Label activ = new Label("Activités:");
        activ.setUIID("activ");
       
        
        
        
      Button quitter = new Button("Retour");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> {
            try {
                new AfficherMesEvenements(res);
            } catch (IOException ex) {
                System.out.println("Ahmed");
                          }
        });
         
      
         quitter.setUIID("vtnvalid");
            
       
        
       
     
         /*   try {
                 l = new Participe_Service().getParticipantByUser(e.getIdUser(),Session.getCurrentSession());
               System.out.println("forms.EventUser.addItem()"+l);

            } catch (Exception ex) {
           System.out.println("ereuuuur");

        }*/
           
     
        
       
                
           
         
           f2.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
             Label lib_titre = new Label("Titre");
                lib_titre.setUIID("pass");
      
              Label lib_Lieu = new Label("Lieu");
                lib_Lieu.setUIID("pass");
   
              Label lib_Description = new Label("Description");
                lib_Description.setUIID("pass");
 
     Label lib_nbr_places = new Label("Nombre de places : ");
                lib_nbr_places.setUIID("pass");
   Label lib_nbr_participant = new Label("Nombre de Participant : ");
                lib_nbr_participant.setUIID("pass");
          Label lib_dd = new Label("Date Debut : ");
                lib_dd.setUIID("pass");
       
          Label lib_df = new Label("Date Fin : ");
                lib_df.setUIID("pass");
                      f2.add(lib_titre).add(titrem).add(lib_nbr_participant).add(nbrparticipantm).add(lib_Lieu).add(lieum).add(lib_Description).add(descriptionm).add(lib_dd).add(ddm).add(lib_df).add(dfm);
                      System.out.println("helllo"+l);
            if(l.size()==0){
             f2.add(activ);
             for(Activite activite : activs)
             {
                  Label nom = new Label(activite.getNom());
                nom.setUIID("pass");
                f2.add(nom);
                
                Label type = new Label("Type : " + activite.getType());
                nom.setUIID("pass");
                f2.add(type);
                
                Label duree = new Label("duree : " + activite.getDuree());
                nom.setUIID("pass");
                f2.add(duree);
                
                Label desc = new Label("Déscription : " + activite.getDescription());
                nom.setUIID("pass");
                f2.add(desc);
             }
            
             f2.add(quitter);
            }
            quitter.addActionListener(e3 -> {
            try {
                new AfficherEvenement(res).show();
            } catch (IOException ex) {
                
            }
        } );
            
          
            f2.show();
         
        });
        cn1.setLeadComponent(btn);
        return cn1;
                
    }
  
  private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
     private Slider createStarRankSlider() {
        Slider starRank = new Slider();
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(10);
         Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
         Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
     
}
    
}
