/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;
import javafx.collections.ObservableList;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;

public class AppointmentPrinter {

    private ComboBox<String> appointmentHourComboBox;

    // Constructeur pour initialiser le ComboBox des créneaux horaires
    public AppointmentPrinter(ComboBox<String> appointmentHourComboBox) {
        this.appointmentHourComboBox = appointmentHourComboBox;
    }

    // Méthode pour imprimer les dossiers de rendez-vous (créneaux horaires)
    public void printAppointmentSlots() {
        // Créer une instance de PrinterJob
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        
        if (printerJob != null) {
            boolean proceed = printerJob.showPrintDialog(appointmentHourComboBox.getScene().getWindow());

            if (proceed) {
                // Imprimer le ComboBox contenant les créneaux horaires
                boolean printed = printerJob.printPage(appointmentHourComboBox);
                
                if (printed) {
                    printerJob.endJob();
                    showAlert("Impression réussie", "Les créneaux horaires des rendez-vous ont été imprimés avec succès.");
                } else {
                    showAlert("Erreur d'impression", "Échec de l'impression des créneaux horaires.");
                }
            }
        } else {
            showAlert("Aucune imprimante détectée", "Veuillez connecter une imprimante et réessayer.");
        }
    }

    // Méthode utilitaire pour afficher des messages d'alerte
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
