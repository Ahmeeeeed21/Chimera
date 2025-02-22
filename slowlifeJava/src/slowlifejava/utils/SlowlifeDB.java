/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slowlifejava.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SlowlifeDB {
    private Connection cnx;
    private final String URL="jdbc:mysql://127.0.0.1:3306/slowlife";
    private final String USERNAME="root";
    private final String PASSWORD="";
    
    private static SlowlifeDB instance=null;
    
    private SlowlifeDB() {
        try {
            cnx=DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(SlowlifeDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static SlowlifeDB getInstance() {
        if (instance==null)
            instance=new SlowlifeDB();
        return instance;
    }
    
    public Connection getConnection() {
        return cnx;
    }
}
