/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.*;
import javafx.scene.control.Tooltip;

/**
 *
 * @author DELL
 */
public class FXMLDocumentController implements Initializable {

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
    private Hyperlink login_registerHere;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private TextField login_username;

    @FXML
    private CheckBox register_CheckBox;

    @FXML
    private Hyperlink register_LoginHere;

    @FXML
    private Button register_SIgnupBtn;

    @FXML
    private TextField register_ShowPassword;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_username;

    // LES OUTILS DE LA BASE DE DONNEES
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage tray = new AlertMessage();

    public void tooltip() {

        Tooltip userTip = new Tooltip("Entrez votre nom d'utilisateur");
        login_username.setTooltip(userTip);
        userTip.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                + "    -fx-cursor: hand;\n"
                + "    -fx-text-fill: #fff;\n"
                + "    -fx-font-size: 14px;\n"
                + "    -fx-font-family: Arial;");

        Tooltip passTip = new Tooltip("Entrez votre mot de passe");
        login_password.setTooltip(passTip);
        //Chsnger la couleur du background
        passTip.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                + "    -fx-cursor: hand;\n"
                + "    -fx-text-fill: #fff;\n"
                + "    -fx-font-size: 14px;\n"
                + "    -fx-font-family: Arial;");

    }

    public void loginAccount() throws SQLException {

        if (login_username.getText().isEmpty()
                || login_password.getText().isEmpty()) {
            tray.errorMessages("NOM OU MOT DE PASSE INCORRECT");
        } else {
            // WNOUS VERIFIONS SI LE USERNAME QUE L'UTILISATEUR A ENTRE
            // EXISTE DEJA DANS NOTRE BASE DE DONNEES
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ? ";
            //String checkUsername = "SELECT * FROM admin WHERE username =? AND password = ? ";
            connect = Database.connectDB();
            try {
                if (!login_ShowPassword.isVisible()) {
                    if (!login_ShowPassword.getText().equals(login_ShowPassword.getText())) {
                        login_ShowPassword.setText(login_ShowPassword.getText());
                    }
                } else {
                    if (!login_ShowPassword.getText().equals(login_ShowPassword.getText())) {
                        login_ShowPassword.setText(login_ShowPassword.getText());
                    }
                }
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());
                result = prepare.executeQuery();
                if (result.next()) {
                    // POUR RETOURNER LE USERNAME
                    Data.admin_username = login_username.getText();
                    Data.admin_id = Integer.parseInt(result.getString("admin_id"));

                    //VERIFIE SI LE MDP ET USERNAME EST BON
                    tray.successMessages("CONNEXION REUSSIE");
                    // LA LIGNE DE MAIN FORM POUR ADMIN
                    Parent root = FXMLLoader.load(getClass().getResource("AdminMainForm.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Hospital Management Sytem | Admin Portal");
                    stage.setScene(new Scene(root));
                    stage.show();

                    // POUR CACHER MA PAGE D'ADMIN (LOGIN FORM)
                    login_loginBtn.getScene().getWindow().hide();
                } else {
                    //VERIFIE SI LE MDP ET USERNAME EST MAUVAIS

                    tray.errorMessages("NOM OU MOT MOT DE PASSE INCORRECT");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void registerAccount() {
        if (register_email.getText().isEmpty()
                || register_username.getText().isEmpty()
                || register_password.getText().isEmpty()) {
            //CREONS NOTRE MESSAGE D'ALERTE

            tray.errorMessages("VEUILLEZ REMPLIR TOUS LES CHAMPS VIDES ");
        } else {
            // NOUS VERIFIONS SI LE USERNAME QUE L'UTILISATEUR A ENTRE
            // EXISTE DEJA DANS NOTRE BASE DE DONNEES
            String checkUsername = "SELECT * FROM admin WHERE username = ' "
                    + register_username.getText() + " ' ";
            connect = Database.connectDB();
            try {

                if (register_CheckBox.isVisible()) {
                    if (!register_ShowPassword.getText().equals(register_password.getText())) {
                        register_ShowPassword.setText(register_password.getText());
                    }
                } else {
                    if (!register_password.getText().equals(register_ShowPassword.getText())) {
                        register_ShowPassword.setText(register_password.getText());
                    }
                }

                //db
                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();
                if (result.next()) {
                    tray.errorMessages(register_username.getText() + "EXISTE DEJA");
                    // verifier si le mdp est minimum 8 caracteres
                } else if (register_password.getText().length() < 8) {
                    tray.errorMessages("VOTRE MOT DE PASSE DOIT CONTENIR AU MOINS 8 CARACTERES");
                } else {

                    // Pour inserer les donnees a la base de donnees
                    String innerData = "INSERT INTO admin (email,username,password,date)VALUES(?,?,?,?)";
                    // Date date = new Date();
                    Date date = new Date(System.currentTimeMillis());

                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(innerData);
                    prepare.setString(1, register_email.getText());
                    prepare.setString(2, register_username.getText());
                    prepare.setString(3, register_password.getText());
                    prepare.setString(4, String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    tray.successMessages("VOTRE COMPTE A ETE CREE AVEC SUCCES");
                    registerClear();
                    // POUR SE DEPLACER DE LA FORM A LA LOGIN FORM
                    login_form.setVisible(true);
                    register_form.setVisible(false);
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    public void registerShowPassword() {
        if (register_CheckBox.isSelected()) {
            register_ShowPassword.setText(register_ShowPassword.getText());
            register_ShowPassword.setVisible(true);
            register_ShowPassword.setVisible(false);
        } else {
            register_password.setText(register_ShowPassword.getText());
            register_ShowPassword.setVisible(false);
            register_ShowPassword.setVisible(true);
        }
    }
    //vider tous les champs

    public void registerClear() {
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_ShowPassword.clear();

    }

    public void loginShowPassword() {
        if (login_CheckBox.isSelected()) {
            login_ShowPassword.setText(login_ShowPassword.getText());
            login_ShowPassword.setVisible(true);
            login_ShowPassword.setVisible(false);
        } else {
            register_password.setText(login_ShowPassword.getText());
            login_ShowPassword.setVisible(false);
            login_ShowPassword.setVisible(true);
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
                stage.setTitle("Gestion de Rendez vous hopital");
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
                stage.setTitle("Gestion de Rendez vous hopital");
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
                stage.setTitle("Gestion de Rendez vous Hopital");
                stage.setMinWidth(355);
                stage.setMinHeight(650);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        login_user.getScene().getWindow().hide();
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == login_registerHere) {
            //La page de registration va montrer
            login_form.setVisible(false);
            register_form.setVisible(true);
        } else if (event.getSource() == register_LoginHere) {
            //La page de login va montrer
            login_form.setVisible(true);
            register_form.setVisible(false);

        }

    }

    @FXML
    public void exit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userList();
    }

}
