/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javax.management.Notification;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author DELL
 */
public class AlertMessage {

    private Alert alert;
    //  private Tray tray;

    public void errorMessages(String message) {
        NotificationType type = NotificationType.ERROR;
        TrayNotification tray = new TrayNotification();
        tray.setTitle("MESSAGE D'ERREUR !");
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(2));

    }

    public void successMessages(String message) {
        NotificationType type = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle("MESSAGE D'INFORMATION !");
        tray.setMessage(message);
        tray.setNotificationType(type);
        tray.showAndDismiss(Duration.seconds(2));
    }
//
//    public void confirmationMessage(String message) {
//    NotificationType type = NotificationType.CONFIRMATION;
//    TrayNotification tray = new TrayNotification();
//    tray.setTitle("Confirmation");
//    tray.setMessage(message);
//    tray.setNotificationType(type);
//    tray.showAndWait();// attend que l'utilisateur confirme ou annule
//    }

    public boolean confirmMessage(String message) {
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Message de Confirmation");
        alert.showAndWait();
        alert.setContentText(message);
        alert.setHeaderText(null);
      //  Optional<ButtonType> option = alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
        //  return option.get().equals(ButtonType.OK);

    }

}
