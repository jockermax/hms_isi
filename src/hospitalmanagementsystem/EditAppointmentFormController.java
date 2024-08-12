/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class EditAppointmentFormController implements Initializable {

    @FXML
    private TextField editApp_appointmentID;

    @FXML
    private TextField editApp_fullName;

    @FXML
    private ComboBox<String> editApp_gender;

    @FXML
    private TextField editApp_mobileNumber;

    @FXML
    private TextArea editApp_address;

    @FXML
    private Button editApp_updateBtn;

    @FXML
    private Button editApp_cancelBtn;

    @FXML
    private TextArea editApp_description;

    @FXML
    private TextField editApp_diagnosis;

    @FXML
    private TextField editApp_treatment;

    @FXML
    private ComboBox<String> editApp_doctor;

    @FXML
    private ComboBox<String> editApp_specialized;

    @FXML
    private ComboBox<String> editApp_status;
    @FXML
    private DatePicker editApp_schedule;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private AlertMessage tray = new AlertMessage();

    public void editAppUpdateBtn() {

        if (editApp_appointmentID.getText().isEmpty()
                || editApp_fullName.getText().isEmpty()
                || editApp_gender.getSelectionModel().getSelectedItem() == null
                || editApp_description.getText().isEmpty()
                || editApp_diagnosis.getText().isEmpty()
                || editApp_treatment.getText().isEmpty()
                || editApp_mobileNumber.getText().isEmpty()
                || editApp_address.getText().isEmpty()
                || editApp_status.getSelectionModel().getSelectedItem() == null
                || editApp_doctor.getSelectionModel().getSelectedItem() == null
                || editApp_specialized.getSelectionModel().getSelectedItem() == null
                || editApp_schedule.getValue() == null) {
            tray.errorMessages("VEUILLEZ REMPLIR TUS LES CHAMPS");
        } else {
            // TO GET THE DATE TODAY
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

            String updateData = "UPDATE appointment SET name = ?, gender = ?, description = ?, diagnosis = ?, treatment = ?, mobile_number = ?, address = ?, status = ?, doctor = ?, specialized = ?, schedule = ? WHERE appointment_id = ?";

            Connection connect = Database.connectDB();
            PreparedStatement prepare = null;

            try {
                if (tray.confirmMessage("ETES-VOUS SUR DE VOULOIR METTER A JOUR LE RENDEZ-VOUS : "
                        + editApp_appointmentID.getText() + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, editApp_fullName.getText());
                    prepare.setString(2, editApp_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(3, editApp_description.getText());
                    prepare.setString(4, editApp_diagnosis.getText());
                    prepare.setString(5, editApp_treatment.getText());
                    prepare.setString(6, editApp_mobileNumber.getText());
                    prepare.setString(7, editApp_address.getText());
                    prepare.setString(8, editApp_status.getSelectionModel().getSelectedItem());
                    prepare.setString(9, editApp_doctor.getSelectionModel().getSelectedItem());
                    prepare.setString(10, editApp_specialized.getSelectionModel().getSelectedItem());
                    prepare.setDate(11, java.sql.Date.valueOf(editApp_schedule.getValue()));
                    prepare.setString(12, editApp_appointmentID.getText());

                    prepare.executeUpdate();
                    tray.successMessages("MISE A JOUR DU RENDEZ-VOUS REUSSIE!");

                }
                editAppClearBtn();
            } catch (SQLException e) {
                e.printStackTrace();
                tray.errorMessages("ERREUR DE MISE A JOUR VEUILLEZ VERIFIER A NOUVEAU: " + e.getMessage());
            } finally {
                try {
                    if (prepare != null) {
                        prepare.close();
                    }
                    if (connect != null) {
                        connect.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void editAppClearBtn() {
        // Clear all the fields
        editApp_appointmentID.clear();
        editApp_fullName.clear();
        editApp_gender.getSelectionModel().clearSelection();
        editApp_description.clear();
        editApp_diagnosis.clear();
        editApp_treatment.clear();
        editApp_mobileNumber.clear();
        editApp_address.clear();
        editApp_status.getSelectionModel().clearSelection();
        editApp_doctor.getSelectionModel().clearSelection();
        editApp_specialized.getSelectionModel().clearSelection();
        editApp_schedule.setValue(null);

        // Optionally close the form or navigate away
        Stage stage = (Stage) editApp_cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void displayFields() {
        editApp_appointmentID.setText(Data.temp_appID);
        editApp_fullName.setText(Data.temp_appName);
        editApp_gender.getSelectionModel().select(Data.temp_appGender);
        editApp_mobileNumber.setText(Data.temp_appMobileNumber);
        editApp_address.setText(Data.temp_appAddress);
        editApp_description.setText(Data.temp_appDescription);
        editApp_diagnosis.setText(Data.temp_appDiagnosis);
        editApp_treatment.setText(Data.temp_appTreatment);
        editApp_doctor.getSelectionModel().select(Data.temp_appDoctor);
        editApp_specialized.getSelectionModel().select(Data.temp_appSpecialized);
        editApp_status.getSelectionModel().select(Data.temp_appStatus);
    }

    public void doctorList() {
        String sql = "SELECT * FROM doctor WHERE delete_date IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            while (result.next()) {
                listData.add(result.getString("doctor_id"));
            }

            editApp_doctor.setItems(listData);
            specializedList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void specializedList() {
        String sql = "SELECT * FROM doctor WHERE delete_date IS NULL AND doctor_id = '"
                + editApp_doctor.getSelectionModel().getSelectedItem() + "'";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            ObservableList listData = FXCollections.observableArrayList();
            if (result.next()) {
                listData.add(result.getString("specialized"));
            }
            editApp_specialized.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : Data.gender) {
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        editApp_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.status) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        editApp_status.setItems(listData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        doctorList();
        genderList();
        statusList();
        displayFields();
    }

}
