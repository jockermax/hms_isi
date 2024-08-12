/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author DELL
 */
public class Database {
     public static Connection connectDB(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect  = DriverManager.getConnection("jdbc:mysql://localhost:3306/memoire_ihm","root","");
            return connect;
            }catch(Exception e)
        {
           System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        }
        return null;


    }
}
