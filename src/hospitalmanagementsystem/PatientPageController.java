/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author DELL
 */
public class PatientPageController implements Initializable {

    @FXML
    private CheckBox login_CheckBox;

    @FXML
    private TextField login_ShowPassword;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_patientID;

    @FXML
    private ComboBox<?> login_user;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private final AlertMessage tray = new AlertMessage();
    
// 
    @FXML
    void loginAccount( ) {
        if (login_patientID.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            tray.errorMessages("ID DU PATIENT OU MOT DE PASSE INCORRECT");
        } else {
            String sql = "SELECT * FROM patient WHERE patient_id = ? AND password = ? AND date_delete is NULL";
            connect = Database.connectDB();
            try {
                if (!login_ShowPassword.isVisible()) {
                    if (!login_ShowPassword.getText().equals(login_password.getText())) {
                        login_ShowPassword.setText(login_password.getText());
                    }
                } else {
                    if (!login_ShowPassword.getText().equals(login_password.getText())) {
                        login_password.setText(login_ShowPassword.getText());
                    }
                }

                // VERIFIER SI LE STATUS DU DOCTEUR EST CONFRIRME
                String checkStatus = "SELECT status FROM patient WHERE patient_id = '"
                        + login_patientID.getText() + "' AND password = '"
                        + login_password.getText() + "' AND status = 'Confirmez' ";
                prepare = connect.prepareStatement(checkStatus);
                result = prepare.executeQuery();

                if (result.next()) {

                    if (!login_ShowPassword.isVisible()) {
                        if (!login_ShowPassword.getText().equals(login_password.getText())) {
                            login_ShowPassword.setText(login_password.getText());
                        }
                    } else {
                        if (!login_ShowPassword.getText().equals(login_password.getText())) {
                            login_password.setText(login_ShowPassword.getText());
                        }
                    }

                    tray.errorMessages("BESOIN DE LA CONFIRMATION DE L'ADMIN");
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, login_patientID.getText());
                    prepare.setString(2, login_password.getText());

                    result = prepare.executeQuery();

                    if (result.next()) {
                        tray.successMessages("CONNEXION REUSSIE");
                    } else {
                        tray.errorMessages("ID DU PATIENT OU MOT DE PASSE INCORRECT");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void loginShowPassword(ActionEvent event) {
        if (login_CheckBox.isSelected()) {
            login_ShowPassword.setText(login_password.getText());
            login_password.setVisible(false);
            login_ShowPassword.setVisible(true);
        } else {
            login_password.setText(login_ShowPassword.getText());
            login_password.setVisible(true);
            login_ShowPassword.setVisible(false);
        }
    }

    public void userList() {

        List<String> listU = new ArrayList<>();
        for (String data : Users.user) {
            listU.add(data);
        }
        ObservableList listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    @FXML
    void switchPage(ActionEvent event) {

        if (login_user.getSelectionModel().getSelectedItem() == "Admin portal") {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                stage.setTitle("Hospital Management System");
                stage.setMinWidth(355);
                stage.setMinHeight(650);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (login_user.getSelectionModel().getSelectedItem() == "Patient Portal") {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("PatientPage.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                stage.setTitle("Hospital Management System");
                stage.setMinWidth(355);
                stage.setMinHeight(650);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (login_user.getSelectionModel().getSelectedItem() == "Patient portal") {

        }
        login_user.getScene().getWindow().hide();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userList();

    }

}
