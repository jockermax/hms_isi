package hospitalmanagementsystem;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
    private ComboBox<String> login_user;  // Change to specific type

    @FXML
    private TextField login_username;

    @FXML
    private CheckBox register_CheckBox;

    @FXML
    private Hyperlink register_LoginHere;

    @FXML
    private Button register_SignupBtn; // Fix typo

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

    // Database tools
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private final AlertMessage tray = new AlertMessage();  // Ensure AlertMessage is defined

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userList();
        tooltip();  // Initialize tooltips
    }

    public void tooltip() {
        Tooltip userTip = new Tooltip("Entrez votre nom d'utilisateur");
        login_username.setTooltip(userTip);
        userTip.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);"
                + "-fx-cursor: hand;"
                + "-fx-text-fill: #fff;"
                + "-fx-font-size: 14px;"
                + "-fx-font-family: Arial;");

        Tooltip passTip = new Tooltip("Entrez votre mot de passe");
        login_password.setTooltip(passTip);
        passTip.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);"
                + "-fx-cursor: hand;"
                + "-fx-text-fill: #fff;"
                + "-fx-font-size: 14px;"
                + "-fx-font-family: Arial;");
    }

    public void loginAccount() {
        if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
            tray.errorMessages("NOM OU MOT DE PASSE INCORRECT");
            return; // Return early
        }

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, login_username.getText());
            prepare.setString(2, login_password.getText());
            result = prepare.executeQuery();

            if (result.next()) {
                // Retrieve the username and id
                Data.admin_username = login_username.getText();
                Data.admin_id = result.getInt("admin_id"); // Using getInt directly

                tray.successMessages("CONNEXION REUSSIE");
                Parent root = FXMLLoader.load(getClass().getResource("AdminMainForm.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Hospital Management System | Admin Portal");
                stage.setScene(new Scene(root));
                stage.show();
                login_loginBtn.getScene().getWindow().hide();
            } else {
                tray.errorMessages("NOM OU MOT DE PASSE INCORRECT");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            tray.errorMessages("Erreur de connexion à la base de données");
        } catch (IOException e) {
            e.printStackTrace();
            tray.errorMessages("Erreur de chargement de la page d'administration");
        }
    }

    public void registerAccount() {
        if (register_email.getText().isEmpty() || register_username.getText().isEmpty() || register_password.getText().isEmpty()) {
            tray.errorMessages("VEUILLEZ REMPLIR TOUS LES CHAMPS VIDES");
            return; // Return early
        }

        String checkUsername = "SELECT * FROM admin WHERE username = ?";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(checkUsername);
            prepare.setString(1, register_username.getText());
            result = prepare.executeQuery();

            if (result.next()) {
                tray.errorMessages(register_username.getText() + " EXISTE DEJA");
            } else if (register_password.getText().length() < 8) {
                tray.errorMessages("VOTRE MOT DE PASSE DOIT CONTENIR AU MOINS 8 CARACTERES");
            } else {
                String innerData = "INSERT INTO admin (email, username, password, date) VALUES (?, ?, ?, ?)";
                Date date = new Date(System.currentTimeMillis());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare = connect.prepareStatement(innerData);
                prepare.setString(1, register_email.getText());
                prepare.setString(2, register_username.getText());
                prepare.setString(3, register_password.getText());
                prepare.setDate(4, sqlDate);
                prepare.executeUpdate();
                tray.successMessages("VOTRE COMPTE A ETE CREE AVEC SUCCES");
                registerClear();
                login_form.setVisible(true);
                register_form.setVisible(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            tray.errorMessages("Erreur de connexion à la base de données");
        }
    }

    public void registerShowPassword() {
        if (register_CheckBox.isSelected()) {
            register_ShowPassword.setText(register_password.getText());
            register_ShowPassword.setVisible(true);
            register_password.setVisible(false);
        } else {
            register_password.setText(register_ShowPassword.getText());
            register_password.setVisible(true);
            register_ShowPassword.setVisible(false);
        }
    }

    public void registerClear() {
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_ShowPassword.clear();
    }

    public void loginShowPassword() {
        if (login_CheckBox.isSelected()) {
            login_ShowPassword.setText(login_password.getText());
            login_ShowPassword.setVisible(true);
            login_password.setVisible(false);
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
        ObservableList<String> listData = FXCollections.observableList(listU);
        login_user.setItems(listData);
    }

    public void switchPage() {
        String selectedItem = login_user.getSelectionModel().getSelectedItem();
        try {
            Parent root;
            if (selectedItem.equals("Admin portal")) {
                root = FXMLLoader.load(getClass().getResource("AdminMainForm.fxml"));
            } else if (selectedItem.equals("Doctor Portal")) {
                root = FXMLLoader.load(getClass().getResource("DoctoPage.fxml"));
            } else if (selectedItem.equals("Patient portal")) {
                root = FXMLLoader.load(getClass().getResource("PatientPage.fxml"));
            } else {
                return; // Return if no valid selection
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestion de Rendez-vous Hopital");
            stage.setMinWidth(355);
            stage.setMinHeight(650);
            stage.show();
            login_user.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
            tray.errorMessages("Erreur de chargement de la page sélectionnée");
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == login_registerHere) {
            login_form.setVisible(false);
            register_form.setVisible(true);
        } else if (event.getSource() == register_LoginHere) {
            login_form.setVisible(true);
            register_form.setVisible(false);
        }
    }

    @FXML
    public void exit(ActionEvent event) {
        System.exit(0);
    }
}
