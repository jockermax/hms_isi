/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author DELL
 */
public class AlertMessage {

    private Alert alert;

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

    public boolean confirmMessage(String message) {
        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Message de Confirmation");
        alert.showAndWait();
        alert.setContentText(message);
        alert.setHeaderText(null);
        return alert.getResult() == ButtonType.OK;

    }

}
