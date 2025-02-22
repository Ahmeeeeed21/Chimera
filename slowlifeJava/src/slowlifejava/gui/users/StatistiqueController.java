/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slowlifejava.gui.users;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import slowlifejava.services.users.UserService;
import slowlifejava.utils.SlowlifeDB;


/**
 * FXML Controller class
 *
 * @author zaefdfyjhlk
 */
public class StatistiqueController implements Initializable {

    @FXML
    private JFXComboBox<String> stat_cmb;
    UserService us = new UserService();

    @FXML
    private PieChart pie_age;
    ObservableList<PieChart.Data> pieageData;
    ObservableList<PieChart.Data> pietypeData;
    ObservableList<PieChart.Data> piedomainData;
    ObservableList<PieChart.Data> piegenreData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("L'age des Utilisateurs");
        list.add("Le type des Utilisateurs");
        list.add("Le domaine des Coachs");
        list.add("Le genre des Utilisateurs");
        stat_cmb.setItems(list);
        stat_cmb.getSelectionModel().select("L'age des Utilisateurs");
        try {
            ageStat();
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    private void Selon(ActionEvent event) throws SQLException {

    }

    public void ageStat() throws SQLException {
        pieageData = FXCollections.observableArrayList();
        String req = "SELECT YEAR(bday) AS year, COUNT(*) AS nb FROM utilisateur GROUP BY YEAR(bday)";
        Statement st = SlowlifeDB.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            pieageData.add(new PieChart.Data((rs.getString("year")), rs.getInt("nb")));
        }
        pie_age.setData(pieageData);
        pie_age.setTitle("Les utilisateurs par age");
        pie_age.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });
    }

    public void typeStat() throws SQLException {
        pietypeData = FXCollections.observableArrayList();
        String req = "SELECT type, COUNT(*) AS nb FROM utilisateur WHERE type != 1 GROUP BY type";
        Statement st = SlowlifeDB.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            if (rs.getInt("type") == 2) {
                pietypeData.add(new PieChart.Data("Client", rs.getInt("nb")));
            } else if (rs.getInt("type") == 3) {
                pietypeData.add(new PieChart.Data("Coach", rs.getInt("nb")));
            }
        }
        pie_age.setData(pietypeData);
        pie_age.setTitle("Les utilisateurs par type");
        pie_age.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });

    }

    public void domainStat() throws SQLException {
        piedomainData = FXCollections.observableArrayList();
        String req = "SELECT domaine, COUNT(*) AS nb FROM utilisateur WHERE type =3 GROUP BY domaine";
        Statement st = SlowlifeDB.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            piedomainData.add(new PieChart.Data((rs.getString("domaine")), rs.getInt("nb")));
        }
        pie_age.setData(piedomainData);
        pie_age.setData(piedomainData);
        pie_age.setTitle("Les coachs par domaine");
        pie_age.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });

    }
     public void genreStat() throws SQLException {
        piegenreData = FXCollections.observableArrayList();
        String req = "SELECT genre, COUNT(*) AS nb FROM utilisateur GROUP BY genre";
        Statement st = SlowlifeDB.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            piegenreData.add(new PieChart.Data((rs.getString("genre")), rs.getInt("nb")));
        }
        pie_age.setData(piegenreData);
        pie_age.setTitle("Les utilisateurs par genre");
        pie_age.getData().forEach(data -> {
            String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
            Tooltip toolTip = new Tooltip(percentage);
            Tooltip.install(data.getNode(), toolTip);
        });

    }

    @FXML
    private void selon(ActionEvent event) throws SQLException {
        if (stat_cmb.getValue().equals("L'age des Utilisateurs")) {
            ageStat();

        } else if (stat_cmb.getValue().equals("Le type des Utilisateurs")) {
            typeStat();
        } else if (stat_cmb.getValue().equals("Le domaine des Coachs")) {
            domainStat();
        }else if (stat_cmb.getValue().equals("Le genre des Utilisateurs")) {
            genreStat();
        }
    }

}
