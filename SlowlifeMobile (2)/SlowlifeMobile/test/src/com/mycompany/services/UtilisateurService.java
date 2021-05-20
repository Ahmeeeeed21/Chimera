/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Utilisateur;
import com.mycompany.myapp.AcceuilForm;
import com.mycompany.myapp.SignInForm;
import com.mycompany.myapp.gui.recette.TwilioSMS;
import com.mycompany.utils.SessionManager;
import com.mycompany.utils.Statics;
import com.sun.mail.smtp.SMTPTransport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author zaefdfyjhlk
 */
public class UtilisateurService {
    //Singleton

    public static UtilisateurService instance = null;
    public static boolean resultOk = true;
    String json;

    //Initialisation du connection request
    private ConnectionRequest req;

    public static UtilisateurService getInstance() {
        if (instance == null) {
            instance = new UtilisateurService();
        }
        return instance;
    }

    public UtilisateurService() {
        req = new ConnectionRequest();
    }

    //Register
    public void signup(TextField nom, TextField prenom, TextField email, TextField mdp, TextField confirmPassword, ComboBox genre, int type, TextField domaine, Picker dtp, Resources res) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String bday = format.format(dtp.getDate());
        String url;
        if ((domaine.getText().toString()).equals("")) {
            url = Statics.BASE_URL + "/AddJSON?nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString()
                    + "&genre=" + genre.getSelectedItem().toString() + "&type=" + type
                    + "&email=" + email.getText().toString() + "&mdp=" + mdp.getText().toString() + "&bday=" + bday;
        } else {
            url = Statics.BASE_URL + "/AddCoachJSON?nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString()
                    + "&genre=" + genre.getSelectedItem().toString() + "&type=" + type + "&domaine=" + domaine.getText().toString()
                    + "&email=" + email.getText().toString() + "&mdp=" + mdp.getText().toString() + "&bday=" + bday;
        }
        req.setUrl(url);
        // Controle de saisie
        if (nom.getText().equals("")) {
            Dialog.show("ERREUR", "Le champs nom est obligatoire", "OK", null);
        } else if (prenom.getText().equals("")) {
            Dialog.show("ERREUR", "Le champs prenom est obligatoire", "OK", null);
        } else if (genre.getSelectedItem().equals("")) {
            Dialog.show("ERREUR", "Indiquer Votre genre", "OK", null);
        } else if (email.getText().equals("")) {
            Dialog.show("ERREUR", "Le champs email est obligatoire", "OK", null);
        } else if (mdp.getText().equals("")) {
            Dialog.show("ERREUR", "Le champs mot de passe est obligatoire", "OK", null);
        } else if (!(mdp.getText().equals(confirmPassword.getText()))) {
            Dialog.show("ERREUR", "Confirmer votre mot de passe", "OK", null);
        }
        //Execution de l'URL 
        req.addResponseListener((e) -> {
            byte[] data = (byte[]) e.getMetaData();
            String responsableData = new String(data);
            System.out.println("data =>" + responsableData);
            Dialog.show("Succes", "Bienvenue Dans Slowlife", "OK", null);
            String body = "Bonjour Mme/Mr" + nom.getText().toString() + ""
                    + "Bienvenue Dans Slowlife";
            String sujet = "Inscription";
            String to = email.getText().toString();
            sendMail(to, body, sujet, res);
            new TwilioSMS("AC2e3f103543a521c05ddbd7f53f95f5bd", "19bef0458f6742f732df0332ec958002", "+16157518411").sendSmsAsync("+21622525030","Bonjour Mme/Mr" + nom.getText().toString() + ""+ "Bienvenue Dans Slowlife");
            new SignInForm(res).show();
        }
        );
        //Aprés l'exécution de l'URL on attend la réponse du serveur 
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    //Send MAIL
    public void sendMail(String email, String body, String sujet, Resources res) {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "stpm.gmail.com");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(props, null);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("<monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, email);
            msg.setSubject(sujet);
            msg.setSentDate(new Date(System.currentTimeMillis()));
            msg.setText(body);
            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
            st.connect("smtp.gmail.com", 465, "slowlife.testpi@gmail.com", "slowlife3a4");
            st.sendMessage(msg, msg.getAllRecipients());
            System.out.println("server response : " + st.getLastServerResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Login
    public void signin(TextField email, TextField mdp, Resources rs) {

        String url = Statics.BASE_URL + "/LoginJSON?email=" + email.getText().toString()
                + "&mdp=" + mdp.getText().toString();
        //req = new ConnectionRequest(url,false);//l'URL pas encore envoyer au serveur
        req.setUrl(url);
        req.addResponseListener((e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData()) + "";
            try {
                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Utilisateur n'existe pas!", "OK", null);
                } else {
                    if (json.equals("fauxmdp")) {
                        Dialog.show("Echec d'authentification", "Veuillez vérifier votre mot de passe", "OK", null);

                    } else {
                        System.out.println("data=>" + json);
                        Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                        //Session  Service
                        float id = Float.parseFloat(user.get("id").toString());
                        SessionManager.setId((int) id);
                        SessionManager.setMdp(user.get("mdp").toString());
                        SessionManager.setNom(user.get("nom").toString());
                        SessionManager.setPrenom(user.get("prenom").toString());
                        SessionManager.setEmail(user.get("email").toString());
                        SessionManager.setGenre(user.get("genre").toString());
                        float type = Float.parseFloat(user.get("type").toString());
                        SessionManager.setType((int) type);
                        SessionManager.setBday(user.get("bday").toString());
                        System.out.println("userLogged: " + SessionManager.getEmail() + " , " + SessionManager.getMdp());
                        if (user.size() > 0)//User found
                        {
                            Dialog.show("Succes d'authentification", "Connecté", "OK", null);
                            new AcceuilForm(rs).show();
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //Aprés l'exécution de l'URL on attend la réponse du serveur 
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    

//logout
    public void logout(Resources res) {
        SessionManager.pref.clearAll();
        Storage.getInstance().clearStorage();
        Storage.getInstance().clearCache();
        new SignInForm(res).show();
        
    }

}
