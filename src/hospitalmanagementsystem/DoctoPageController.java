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
import java.sql.SQLException;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DoctoPageController implements Initializable {

    @FXML
    private CheckBox login_CheckBox;

    @FXML
    private TextField login_DoctorID;

    @FXML
    private TextField login_ShowPassword;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private PasswordField login_password;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private CheckBox register_CheckBox;

    @FXML
    private Hyperlink register_LoginHere;

    @FXML
    private Button register_SIgnupBtn;

    @FXML
    private TextField register_ShowPassword;

    @FXML
    private TextField register_doctorID;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_fullName;

    @FXML
    private PasswordField register_password;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage tray = new AlertMessage();

    public void tooltip() {
        Tooltip userTip = new Tooltip("Entrez votre nom d'utilisateur");
        login_DoctorID.setTooltip(userTip);
        userTip.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);"
                + "-fx-cursor: hand;"
                + "-fx-text-fill: #fff;"
                + "-fx-font-size: 14px;"
                + "-fx-font-family: Arial;");

        Tooltip passTip = new Tooltip("Entrez votre mot de passe");
        login_password.setTooltip(passTip);
        passTip.setStyle("-fx-background-color: linear-gradient(to bottom right,  #a413a1, #64308e);"
                + "-fx-cursor: hand;"
                + "-fx-text-fill: #fff;"
                + "-fx-font-size: 14px;"
                + "-fx-font-family: Arial;");
    }

    @FXML
    void loginAccount(ActionEvent event) throws SQLException {

        if (login_DoctorID.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            tray.errorMessages("ID DU DOCTEUR OU MOT DE PASSE INCORRECT");
        } else {
            String sql = "SELECT * FROM doctor WHERE doctor_id = ? AND password = ? AND delete_date is NULL";
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
                String checkStatus = "SELECT status FROM doctor WHERE doctor_id = '"
                        + login_DoctorID.getText() + "' AND password = '"
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
                    prepare.setString(1, login_DoctorID.getText());
                    prepare.setString(2, login_password.getText());

                    result = prepare.executeQuery();

                    if (result.next()) {
                        Data.doctor_id = result.getString("doctor_id");
                        Data.doctor_name = result.getString("full_name");

                        tray.successMessages("CONNEXION REUSSIE");
                        //Lier le doctor main form
                        Parent root = FXMLLoader.load(getClass().getResource("DoctorMainForm.fxml"));
                        Stage stage = new Stage();

                        stage.setTitle("Hosiptal Management System | Doctor Main Form");
                        stage.setScene(new Scene(root));
                        stage.show();

                        //TO HIDE YOUR DOCTOR PAGE FORM
                        login_loginBtn.getScene().getWindow().hide();

                    } else {
                        tray.errorMessages("ID DU DOCTEUR OU MOT DE PASSE INCORRECT");
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

    @FXML
    void registerAccount(ActionEvent event) {

        if (register_fullName.getText().isEmpty()
                || register_email.getText().isEmpty()
                || register_password.getText().isEmpty()
                || register_password.getText().isEmpty()) {
            tray.errorMessages("REMPLISSEZ TOUS LES CHAMPS VIDES");
        } else {

            String checkDoctorID = "SELECT * FROM doctor WHERE doctor_id = ' "
                    + register_doctorID.getText() + "'";
            connect = Database.connectDB();

            try {
                if (!register_ShowPassword.isVisible()) {
                    if (!register_ShowPassword.getText().equals(register_password.getText())) {
                        register_ShowPassword.setText(register_password.getText());
                    }
                } else {
                    if (!register_ShowPassword.getText().equals(register_password.getText())) {

                        register_password.setText(register_ShowPassword.getText());
                    }

                }

                prepare = connect.prepareStatement(checkDoctorID);
                result = prepare.executeQuery();

                if (result.next()) {
                    tray.errorMessages(register_doctorID.getText() + "deja pris");

                } else if (register_password.getText().length() < 8) {
                    tray.errorMessages("MOT DE PASSE INVALIDE, AU MOINS 8 CARACTEERES ");
                } else {
                    /* String innerData = "INSERT INTO doctor(full_name, email, doctor_id, password, date, status)"
                            +"VALUES (?,?,?,?,?,?)";*/
                    String innerData = "INSERT INTO doctor(full_name, email, doctor_id, password, date, status)VALUES (?,?,?,?,?,?)";

                    //  connect = Database.connectDB();
                    prepare = connect.prepareStatement(innerData);
                    java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(1, register_fullName.getText());
                    prepare.setString(2, register_email.getText());
                    prepare.setString(3, register_doctorID.getText());
                    prepare.setString(4, register_password.getText());
                    prepare.setString(5, String.valueOf(sqlDate));
                    prepare.setString(6, "Confirmez");

                    prepare.executeUpdate();

                    tray.successMessages("CREATION DE COMPTE REUSSIE");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void registerShowPassword(ActionEvent event) {
        if (register_CheckBox.isSelected()) {
            register_ShowPassword.setText(register_password.getText());
            register_ShowPassword.setVisible(true);
            register_ShowPassword.setVisible(false);
        } else {
            register_ShowPassword.setText(register_password.getText());
            register_ShowPassword.setVisible(false);
            register_ShowPassword.setVisible(true);

        }
    }

    public void registerDoctorID() {
        String doctorID = "DID-";
        int tempID = 0;
        String sql = "SELECT MAX(id) FROM doctor";
        connect = Database.connectDB();
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {

                tempID = result.getInt("MAX(id)");

            }

            if (tempID == 0) {
                tempID += 1;
                doctorID += tempID;
            } else {
                doctorID += (tempID + 1);
            }
            register_doctorID.setText(doctorID);
            register_doctorID.setDisable(true);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @FXML
    void switchForm(ActionEvent event) {
        if (event.getSource() == register_LoginHere) {
            login_form.setVisible(true);
            register_form.setVisible(false);

        } else if (event.getSource() == login_registerHere) {
            login_form.setVisible(false);
            register_form.setVisible(true);
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

    public void switchPage() {

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
        } else if (login_user.getSelectionModel().getSelectedItem() == "Doctor Portal") {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("DoctoPage.fxml"));
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
        }
        login_user.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        registerDoctorID();
        userList();
        tooltip();
    }

}
