/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class EditPatientFormController implements Initializable {

    @FXML
    private TextArea edit_address;

    @FXML
    private TextField edit_contactNumber;

    @FXML
    private ComboBox<String> edit_gender;

    @FXML
    private TextField edit_name;

    @FXML
    private TextField edit_patientID;

    @FXML
    private ComboBox<String> edit_status;

    @FXML
    private Button edit_updateBtn;

    @FXML
    private AnchorPane main_form;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    private AlertMessage tray = new AlertMessage();

    public void setField() {
        edit_patientID.setText(String.valueOf(Data.temp_PatientID));
        edit_name.setText(Data.temp_name);
        edit_gender.getSelectionModel().select(Data.temp_gender);
        edit_contactNumber.setText(String.valueOf(Data.temp_number));
        edit_address.setText(Data.temp_address);
        edit_status.getSelectionModel().select(Data.temp_status);

    }

    public void updateBtn() {

        if (edit_patientID.getText().isEmpty() || edit_name.getText().isEmpty()
                || edit_gender.getSelectionModel().getSelectedItem() == null
                || edit_contactNumber.getText().isEmpty()
                || edit_address.getText().isEmpty()
                || edit_status.getSelectionModel().getSelectedItem() == null) {
            tray.errorMessages("VEUILLEZ REMPLIR TOUS LES CHAMPS");
        } else {
            String updateData = "UPDATE patient SET full_name = ?, gender = ?"
                    + ", mobile_number = ?, address = ?, status = ?, date_modify = ? "
                    + "WHERE patient_id = '"
                    + edit_patientID.getText() + "'";
            connect = Database.connectDB();
            try {
                if (tray.confirmMessage("ETES VOUS SUR DE VOULOIR MODIFIER LE PATIENT N°: " + edit_patientID.getText()
                        + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(1, edit_name.getText());
                    prepare.setString(2, edit_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(3, edit_contactNumber.getText());
                    prepare.setString(4, edit_address.getText());
                    prepare.setString(5, edit_status.getSelectionModel().getSelectedItem());
                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    tray.successMessages("MISE A JOUR REUSSIE!");
                } else {
                    tray.errorMessages("ANNULLEE.");
                     Stage stage = (Stage) edit_updateBtn.getScene().getWindow();
                        stage.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void genderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : Data.gender) {
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        edit_gender.setItems(listData);
    }

    @SuppressWarnings("unchecked")
    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Data.status) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        edit_status.setItems(listData);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setField();
        genderList();
        statusList();
    }

}
