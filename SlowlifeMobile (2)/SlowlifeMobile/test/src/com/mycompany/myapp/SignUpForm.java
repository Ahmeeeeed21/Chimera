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

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Calendar;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.PickerComponent;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.services.UtilisateurService;
import java.util.Date;
import java.util.Vector;

/**
 * Signup UI
 *
 * @author Abirn
 */
public class SignUpForm extends BaseForm {
    int t;

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom = new TextField("", "Prenom", 20, TextField.ANY);
        //TextField num = new TextField("", "NUM", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField mdp = new TextField("", "Mot de Passe", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirmer Mot de Passe", 20, TextField.PASSWORD);
        Vector<String> vectorGender;
        vectorGender = new Vector();
        vectorGender.add("Femme");
        vectorGender.add("Homme");
        ComboBox<String> genre = new ComboBox<>(vectorGender);
//        Vector<String> vectorType;
//        vectorType = new Vector();
//        vectorType.add("Coach");
//        vectorType.add("Client");
//        ComboBox<String> type = new ComboBox<>(vectorType);
        Picker dtp= new Picker();
        TextField domaine = new TextField("", "Domaine", 20, TextField.ANY);
        dtp.setType(Display.PICKER_TYPE_DATE);
        ButtonGroup typeGroup = new ButtonGroup();
        RadioButton client = RadioButton.createToggle("Client", typeGroup);
        client.setUIID("SelectBar");
        
        RadioButton coach = RadioButton.createToggle("Coach", typeGroup);
        coach.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        
        client.addActionListener((e) -> {
            domaine.setVisible(false);
            t=2;
        });
        coach.addActionListener((e) -> {
            domaine.setVisible(true);
            t=3;
        });

        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        mdp.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        domaine.setSingleLineTextArea(false);
        Button next = new Button("Sign Up");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Vous avez déjà un compte?");
        Container content = BoxLayout.encloseY(
                new Label("Créer un Compte", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                dtp,
                createLineSeparator(),
                new FloatingHint(email),

                new FloatingHint(mdp),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                createLineSeparator(),
                genre,
                createLineSeparator(),
                client,coach,
                domaine
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(e -> {
            UtilisateurService.getInstance().signup(nom, prenom, email, mdp, confirmPassword, genre, t,domaine,dtp, res);

        });
    }
}
