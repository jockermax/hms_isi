/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

/**
 *
 * @author DELL
 */
import javafx.scene.control.Alert;

public class Outils {

    public static void showConfirmationMessage(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        alert.setTitle(null);
        alert.showAndWait();
    }
    
    
     public static void showErrorMessage(String titre, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setTitle(null);
        alert.showAndWait();
    
    }

}
