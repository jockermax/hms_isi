/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author DELL
 */
public class DoctorMainFormController implements Initializable {

    @FXML
    private Label Current_Form;

    @FXML
    private Button logout_btn;

    @FXML
    private TextArea appointments_address;

    @FXML
    private TextField appointments_appointmentID;

    @FXML
    private Button appointments_btn;

    @FXML
    private TableColumn<?, ?> appointments_col_action;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_contactNumber;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_date;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateDelete;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateModify;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_description;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_gender;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_name;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_status;

    @FXML
    private TextField appointments_description;

    @FXML
    private TextField appointments_diagnosis;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private ComboBox<String> appointments_gender;

    @FXML
    private TextField appointments_mobileNumber;

    @FXML
    private TextField appointments_name;

    @FXML
    private ComboBox<String> appointments_status;

    @FXML
    private TableView<AppointmentData> appointments_tableView;

    @FXML
    private TextField appointments_treatment;

    @FXML
    private BarChart<?, ?> dashboad_chart_DD;

    @FXML
    private AreaChart<?, ?> dashboad_chart_PD;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_appointmentDate;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_name;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_description;

    @FXML
    private TableColumn<AppointmentData, String> dashboad_col_status;

    @FXML
    private TableView<AppointmentData> dashboad_tableView;

    @FXML
    private Label dashboard_IP;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_tA;

    @FXML
    private Label date_time;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Button patients_Confirmbtn;

    @FXML
    private TextField patients_CurrentID;

    @FXML
    private Label patients_PA_CurrentID;

    @FXML
    private Label patients_PA_Password;

    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Button patients_PI_addBtn;

    @FXML
    private Label patients_PI_address;

    @FXML
    private Label patients_PI_gender;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Button patients_PI_recordBtn;

    @FXML
    private TextArea patients_address;

    @FXML
    private Button patients_btn;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private ComboBox<String> patients_gender;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextField patients_password;

    @FXML
    private TextField patients_patientName;

    @FXML
    private Button profile_btn;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private DatePicker appoinment_schedule;

    @FXML
    private Button appointment_clearBtn;

    @FXML
    private Button appointment_deleteBtn;

    @FXML
    private Button appointment_insertBtn;

    @FXML
    private Button appointment_updateBtn;

    // profile 
    @FXML
    private AnchorPane profile_form;

    @FXML
    private Circle profile_circleimage;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_doctorID;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_name;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_email;

    @FXML
    private TextField profile_name;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private TextArea profile_address;

    @FXML
    private ComboBox<String> profile_specialized;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private Button appointmentSearchBtn;

    @FXML
    private TextField appointments_searchField;

//    DATABASE TOOLSs
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Image image;

    private final AlertMessage tray = new AlertMessage();
    private Integer appointmentID;

//    ObservableList<AppointmentData> seachrvModelObservableList = FXCollections.observableArrayList();
//  
//    public void searchRv() {
//       
//    }
    public void dashboardDisplayIP() {
        String sql = "SELECT COUNT(id) FROM patient WHERE status = 'Inactive' AND doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getIP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getIP = result.getInt("COUNT(id)");
            }
            dashboard_IP.setText(String.valueOf(getIP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayTP() {
        String sql = "SELECT COUNT(id) FROM patient WHERE doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getTP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(getTP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardDisplayAP() {
        String sql = "SELECT COUNT(id) FROM patient WHERE status = 'Active' AND doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getAP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getAP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(getAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardDisplayTA() {
        String sql = "SELECT COUNT(id) FROM appointment WHERE status = 'Active' AND doctor = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();
        int getTA = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                getTA = result.getInt("COUNT(id)");
            }
            dashboard_tA.setText(String.valueOf(getTA));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<AppointmentData> dashboardAppointmentTableView() {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE doctor = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData aData;
            while (result.next()) {
                aData = new AppointmentData(result.getInt("appointment_id"),
                        result.getString("name"), result.getString("description"),
                        result.getDate("date"), result.getString("status"));

                listData.add(aData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<AppointmentData> dashboardGetData;

    public void dashboardDisplayData() {
        dashboardGetData = dashboardAppointmentTableView();

        dashboad_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dashboad_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboad_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dashboad_col_appointmentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dashboad_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboad_tableView.setItems(dashboardGetData);
    }
    // COURBES DES NOMBRES DE PATIENTS

    public void dashboardNOP() {

        dashboad_chart_PD.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM patient WHERE doctor = '"
                + Data.doctor_id + "' GROUP BY TIMESTAMP(date) ASC LIMIT 8";
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_PD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
// COURBES DES NOMBRES DE RENDEZ VOUS

    public void dashboardNOA() {

        dashboad_chart_DD.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM appointment WHERE doctor = '"
                + Data.doctor_id + "' GROUP BY TIMESTAMP(date) ASC LIMIT 7";
        connect = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboad_chart_DD.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void patientAddBtn() {

        if (patients_PA_CurrentID.getText().isEmpty()
                || patients_PA_Password.getText().isEmpty()
                || patients_PA_dateCreated.getText().isEmpty()
                || patients_PI_patientName.getText().isEmpty()
                || patients_PI_gender.getText().isEmpty()
                || patients_PI_mobileNumber.getText().isEmpty()
                || patients_PI_address.getText().isEmpty()) {
            tray.errorMessages("VEUILLEZ VERIFIER A NOUVEAU");
        } else {

            Database.connectDB();
            try {
                String doctorName = "";
                String doctorSpecialized = "";

                String getDoctor = "SELECT * FROM doctor WHERE doctor_id = '"
                        + nav_adminID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(getDoctor);

                if (result.next()) {
                    doctorName = result.getString("full_name");
                    doctorSpecialized = result.getString("specialized");
                }
                // CHECK IF THE PATIENT ID THAT THE DOCTORS WANT TO INSERT/ADD IS EXISTING ALREADY
                String checkPatientID = "SELECT * FROM patient WHERE patient_id = '"
                        + patients_PA_CurrentID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(checkPatientID);
                if (result.next()) {
                    tray.errorMessages(patients_PA_CurrentID.getText() + " EXISTE DEJA");
                } else {
                    String insertData = "INSERT INTO patient (patient_id, password, full_name, mobile_number, "
                            + "address, doctor, specialized, date, "
                            + "status) "
                            + "VALUES(?,?,?,?,?,?,?,?,?)";
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, patients_PA_CurrentID.getText());
                    prepare.setString(2, patients_PA_Password.getText());
                    prepare.setString(3, patients_PI_patientName.getText());
                    prepare.setString(4, patients_PI_mobileNumber.getText());
                    prepare.setString(5, patients_PI_address.getText());
                    prepare.setString(6, nav_adminID.getText());
                    prepare.setString(7, doctorSpecialized);
                    prepare.setString(8, "" + sqlDate);
                    prepare.setString(9, "Confirmez");

                    prepare.executeUpdate();

                    tray.successMessages("AJOUT REUSSI!");
                    // POUR EFFACER CERTAINS CHAMPS ET LABEL
                    patientClearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void patientRecordBtn() {
        try {
            // LINK THE NAME OF YOUR FXML FOR RECORD PAGE
            Parent root = FXMLLoader.load(getClass().getResource("RecordPageForm.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Hospital Management System | Dossier des Patients");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void patientClearFields() {
        patients_CurrentID.clear();
        patients_patientName.clear();
        patients_gender.getSelectionModel().clearSelection();
        patients_mobileNumber.clear();
        patients_password.clear();
        patients_address.clear();

        patients_PA_CurrentID.setText("");
        patients_PA_Password.setText("");
        patients_PA_dateCreated.setText("");

        patients_PI_patientName.setText("");
        patients_PI_gender.setText("");
        patients_PI_mobileNumber.setText("");
        patients_PI_address.setText("");
    }

    public void patientConfirmbtn() {
        // VERIFIER SI TOUS LES CHAMPS SONT VIDES
        if (patients_CurrentID.getText().isEmpty()
                || patients_patientName.getText().isEmpty()
                || patients_gender.getSelectionModel().getSelectedItem() == null
                || patients_mobileNumber.getText().isEmpty()
                || patients_password.getText().isEmpty()
                || patients_address.getText().isEmpty()) {
            tray.errorMessages("VEUILLEZ REMPLIR TOUS LES CHAMPS");
        } else {

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            // POUR AFFICHER LES DONNEES DU COMPTE PERSONNEL
            patients_PA_CurrentID.setText(patients_CurrentID.getText());
            patients_PA_Password.setText(patients_password.getText());
            patients_PA_dateCreated.setText(String.valueOf(sqlDate));
            // POUR AFFICHER LES DONNEES DES INFORMATIONS PERSONNELLES
            patients_PI_patientName.setText(patients_patientName.getText());
            patients_PI_gender.setText(patients_gender.getSelectionModel().getSelectedItem());
            patients_PI_mobileNumber.setText(patients_mobileNumber.getText());
            patients_PI_address.setText(patients_address.getText());

        }

    }

    private void patientGenderList() {
        List<String> listG = new ArrayList();

        for (String data : Data.gender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);
        patients_gender.setItems(listData);
    }

    //BUTTON DE MODIFICATION DE TOUS LES CHAMPS
    public void appointmentUpdateBtn() {

        if (appointments_appointmentID.getText().isEmpty()
                || appointments_name.getText().isEmpty()
                || appointments_gender.getSelectionModel().getSelectedItem() == null
                || appointments_mobileNumber.getText().isEmpty()
                || appointments_description.getText().isEmpty()
                || appointments_address.getText().isEmpty()
                || appointments_status.getSelectionModel().getSelectedItem() == null
                || appoinment_schedule.getValue() == null) {
            tray.errorMessages(" VEUILEZ REMPLIR TOUS LES CHAMPS VIDES ");
        } else {
            // TO GET THE DATE TODAY
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

            String updateData = "UPDATE appointment SET name = '" + appointments_name.getText()
                    + "', gender = '" + appointments_gender.getSelectionModel().getSelectedItem()
                    + "', mobile_number = '" + appointments_mobileNumber.getText()
                    + "', description = '" + appointments_description.getText()
                    + "', address = '" + appointments_address.getText()
                    + "', status = '" + appointments_status.getSelectionModel().getSelectedItem()
                    + "', schedule = '" + appoinment_schedule.getValue()
                    + "', date_modify = '" + sqlDate + "' WHERE appointment_id = '"
                    + appointments_appointmentID.getText() + "'";

            connect = Database.connectDB();

            try {
                if (tray.confirmMessage(" ÊTES-VOUS SUR DE VOULOIR MODIFIER CE RENDEZ-VOUS : "
                        + appointments_appointmentID.getText() + " ? ")) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    tray.successMessages("MISE A JOUR RÉUSSIE!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // TO SELECT THE DATA PER ROW IN THE TABLE
    public void appointmentSelect() {

        AppointmentData appData = appointments_tableView.getSelectionModel().getSelectedItem();
        int num = appointments_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        appointments_appointmentID.setText("" + appData.getAppointmentID());
        appointments_name.setText(appData.getName());
        appointments_gender.getSelectionModel().select(appData.getGender());
        appointments_mobileNumber.setText("" + appData.getMobileNumber());
        appointments_description.setText(appData.getDescription());
        appointments_diagnosis.setText(appData.getDiagnosis());
        appointments_treatment.setText(appData.getTreatment());
        appointments_address.setText(appData.getAddress());
        appointments_status.getSelectionModel().select(appData.getStatus());

    }

    //BUTTON D'EFFACEMENT DE TOUS LES CHAMPS
    public void appointmentClearBtn() {
        appointments_appointmentID.clear();
        appointments_name.clear();
        appointments_gender.getSelectionModel().clearSelection();
        appointments_mobileNumber.clear();
        appointments_description.clear();
        appointments_treatment.clear();
        appointments_diagnosis.clear();
        appointments_address.clear();
        appointments_status.getSelectionModel().clearSelection();
        appoinment_schedule.setValue(null);
    }

    //BUTTON D'INSERTION D'UN RENDEZ VOUS
    public void appointmentInsertBtn() {

//        CHECK IF THE FIELD(S) ARE EMPTY
        if (appointments_appointmentID.getText().isEmpty()
                || appointments_name.getText().isEmpty()
                || appointments_gender.getSelectionModel().getSelectedItem() == null
                || appointments_mobileNumber.getText().isEmpty()
                || appointments_description.getText().isEmpty()
                || appointments_address.getText().isEmpty()
                || appointments_status.getSelectionModel().getSelectedItem() == null
                || appoinment_schedule.getValue() == null) {
            tray.errorMessages("Veuillez remplir tous les champs vides");
        } else {
            String checkAppointmentID = "SELECT * FROM appointment WHERE appointment_id = "
                    + appointments_appointmentID.getText();
            connect = Database.connectDB();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkAppointmentID);

                if (result.next()) {
                    tray.errorMessages(appointments_appointmentID.getText() + " was already taken");
                } else {
                    String getSpecialized = "";
                    String getDoctorData = "SELECT * FROM doctor WHERE doctor_id = '"
                            + Data.doctor_id + "'";

                    statement = connect.createStatement();
                    result = statement.executeQuery(getDoctorData);

                    if (result.next()) {
                        getSpecialized = result.getString("specialized");
                    }

                    String insertData = "INSERT INTO appointment (appointment_id, name, gender"
                            + ", description, diagnosis, treatment, mobile_number"
                            + ", address, date, status, doctor, specialized, schedule) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1, appointments_appointmentID.getText());
                    prepare.setString(2, appointments_name.getText());
                    prepare.setString(3, appointments_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, appointments_description.getText());
                    prepare.setString(5, appointments_diagnosis.getText());
                    prepare.setString(6, appointments_treatment.getText());
                    prepare.setString(7, appointments_mobileNumber.getText());
                    prepare.setString(8, appointments_address.getText());

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    prepare.setString(9, "" + sqlDate);
                    prepare.setString(10, appointments_status.getSelectionModel().getSelectedItem());
                    prepare.setString(11, Data.doctor_id);
                    prepare.setString(12, getSpecialized);
                    prepare.setString(13, "" + appoinment_schedule.getValue());

                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    tray.successMessages("Successully added!");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void appointmentGetAppointmentID() {
        String sql = " SELECT MAX(appointment_id) FROM appointment";
        connect = Database.connectDB();
        int tempAppID = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if (result.next()) {
                tempAppID = result.getInt("MAX(appointment_id)");
            }
            if (tempAppID == 0) {
                tempAppID += 1;
            } else {
                tempAppID += 1;
            }
            appointmentID = tempAppID;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //BUTTON DE SUPPRESSION D'UN RENDEZ VOUS
    public void appointmentDeleteBtn() {

        if (appointments_appointmentID.getText().isEmpty()) {
            tray.errorMessages("VEUILLEZ CHOISIR UN RENDEZ-VOUS SVP");
        } else {
            String updateData = "UPDATE appointment SET date_delete = ? WHERE appointment_id = ?";

            connect = Database.connectDB();

            try {
                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                if (tray.confirmMessage("ÊTES-VOUS SUR DE VOULOIR SUPPRIMER LE RENDEZ-VOUS N°: "
                        + appointments_appointmentID.getText() + " ? ")) {
                    prepare = connect.prepareStatement(updateData);

                    prepare.setDate(1, sqlDate);
                    prepare.setString(2, appointments_appointmentID.getText());
                    prepare.executeUpdate();

                    appointmentShowData(); // Met à jour l'affichage pour exclure les rendez-vous supprimés
                    appointmentAppointmentID(); // Réinitialise l'ID de rendez-vous sélectionné
                    appointmentClearBtn(); // Efface les champs

                    tray.successMessages("SUPPRESSION RÉUSSIE!");
                } else {
                    tray.errorMessages("ANNULÉE.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void appointmentAppointmentID() {
        appointmentGetAppointmentID();

        appointments_appointmentID.setText("" + appointmentID);
        appointments_appointmentID.setDisable(true);

    }

    public void appointmentGenderList() {
        List<String> listG = new ArrayList<>();
        for (String data : Data.gender) {
            listG.add(data);
        }
        ObservableList listData
                = FXCollections.observableArrayList(listG);
        appointments_gender.setItems(listData);
    }

    public void appointmentStatusList() {
        List<String> listS = new ArrayList<>();
        for (String data : Data.status) {

            listS.add(data);
        }

        ObservableList listData
                = FXCollections.observableArrayList(listS);
        appointments_status.setItems(listData);
    }

    public ObservableList<AppointmentData> AppointmentGetData() {
        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM appointment WHERE date_delete IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData appData;

            while (result.next()) {

//                Integer appointmentID, String name, String gender,
//            Long mobileNumber, String description, String diagnosis, String treatment, String address,
//            Date date, Date dateModify, Date dateDelete, String status, Date schedule
                appData = new AppointmentData(result.getInt("id"), result.getInt("appointment_id"),
                        result.getString("name"), result.getString("gender"), result.getLong("mobile_number"),
                        result.getString("description"), result.getString("diagnosis"),
                        result.getString("treatment"), result.getString("address"),
                        result.getString("doctor"), result.getString("specialized"),
                        result.getDate("date"), result.getDate("date_modify"),
                        result.getDate("date_delete"), result.getString("status"),
                        result.getDate("schedule"));

                //POUR STOCKER TOUTES LES DONNEES
                listData.add(appData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listData;
    }

    public ObservableList<AppointmentData> appoinmentListData;

    public void appointmentShowData() {
        appoinmentListData = AppointmentGetData();
        appointments_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        appointments_tableView.setItems(appoinmentListData);
    }
// INSERTION D'UN IMAGE

    //           Path copy = Paths.get("C:\\Users\\DELL\\Documents\\NetBeansProjects\\yalla-pitie\\src\\Directory\\"
    public void profileUpdateBtn() {

        connect = Database.connectDB();

        if (profile_doctorID.getText().isEmpty()
                || profile_name.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_gender.getSelectionModel().getSelectedItem() == null
                || profile_mobileNumber.getText().isEmpty()
                || profile_address.getText().isEmpty()
                || profile_specialized.getSelectionModel().getSelectedItem() == null
                || profile_status.getSelectionModel().getSelectedItem() == null) {
            tray.errorMessages("Please fill all blank fields");
        } else {
            // CHECK IF THE PATH IS NULL 
            if (Data.path == null || "".equals(Data.path)) {
                String updateData = "UPDATE doctor SET full_name = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobileNumber.getText());
                    prepare.setString(5, profile_address.getText());
                    prepare.setString(6, profile_specialized.getSelectionModel().getSelectedItem());
                    prepare.setString(7, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    tray.successMessages("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String updateData = "UPDATE doctor SET full_name = ?, email = ?"
                        + ", gender = ?, mobile_number = ?, address = ?, image = ?, specialized = ?, status = ?, modify_date = ?"
                        + " WHERE doctor_id = '"
                        + Data.doctor_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobileNumber.getText());
                    prepare.setString(5, profile_address.getText());
                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    // LINK YOUR DIRECTORY FOLDER
                    Path copy = Paths.get("C:\\dossier_prive\\hms_isi\\src\\Directory\\" + Data.doctor_id + ".jpg");

                    try {
                        // TO PUT THE IMAGE FILE TO YOUR DIRECTORY FOLDER
                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    prepare.setString(6, copy.toAbsolutePath().toString());
                    prepare.setString(7, profile_specialized.getSelectionModel().getSelectedItem());
                    prepare.setString(8, profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(9, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    tray.successMessages("Updated Successfully!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public void profileDisplayImages() {
//
//        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
//                + Data.doctor_id + "'";
//        String temp_path1 = "";
//        String temp_path2 = "";
//        connect = Database.connectDB();
//
//        try {
//            prepare = connect.prepareStatement(selectData);
//            result = prepare.executeQuery();
//
//            if (result.next()) {
//                temp_path1 = "File:" + result.getString("image");
//                temp_path2 = "File:" + result.getString("image");
//
//                if (result.getString("image") != null) {
//                    image = new Image(temp_path1, 1012, 22, false, true);
//                    top_profile.setFill(new ImagePattern(image));
//
//                    image = new Image(temp_path2, 128, 103, false, true);
//                    profile_circleimage.setFill(new ImagePattern(image));
//                }
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void profileDisplayImages() {

        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        String temp_path1 = "";
        String temp_path2 = "";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                String imagePath = result.getString("image");

                if (imagePath != null && !imagePath.isEmpty()) {
                    // Construire les chemins des images
                    temp_path1 = "file:/" + imagePath; // Chemin pour l'image principale
                    temp_path2 = "file:/" + imagePath; // Chemin pour l'image du cercle

                    // Vérifier si l'image peut être chargée
                    File file = new File(imagePath);
                    if (file.exists()) {
                        // Charger l'image pour top_profile
                        image = new Image(temp_path1, 1012, 22, false, true);
                        top_profile.setFill(new ImagePattern(image));

                        // Charger l'image pour profile_circleimage
                        image = new Image(temp_path2, 128, 103, false, true);
                        profile_circleimage.setFill(new ImagePattern(image));
                    } else {
                        System.out.println("L'image n'existe pas à l'emplacement : " + imagePath);
                    }
                } else {
                    System.out.println("Aucun chemin d'image disponible dans la base de données.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileLabels() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_label_doctorID.setText(result.getString("doctor_id"));
                profile_label_name.setText(result.getString("full_name"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileChangeProfile() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new ExtensionFilter("Open Image", "*png", "*jpg", "*jpeg"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());

        if (file != null) {
            Data.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 128, 103, false, true);
            profile_circleimage.setFill(new ImagePattern(image));
        }

    }

    public void profileFields() {
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if (result.next()) {
                profile_doctorID.setText(result.getString("doctor_id"));
                profile_name.setText(result.getString("full_name"));
                profile_email.setText(result.getString("email"));
                profile_gender.getSelectionModel().select(result.getString("gender"));
                profile_mobileNumber.setText(result.getString("mobile_number"));
                profile_address.setText(result.getString("address"));
                profile_specialized.getSelectionModel().select(result.getString("specialized"));
                profile_status.getSelectionModel().select(result.getString("status"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Data.gender) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        profile_gender.setItems(listData);

    }

    private String[] specialization = {"Allergologue", "Dermatologue", "Pediatre", "Ophthalmologue", "Gynecologue", "Cardiologue", "Neurologue", "Immunologue", "Endocrinologue", "Hématologue", "Oncologue", "Neurologue", "Psychologue", "Rhumatologue", "Radiologue", "Pathologue"};

    public void profileSpecializedList() {

        List<String> listS = new ArrayList<>();

        for (String data : specialization) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_specialized.setItems(listData);
    }

    public void profileStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : Data.status) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);
    }

    public void displayAdminIdNumberName() {
        String name = Data.doctor_name;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        nav_username.setText(name);
        nav_adminID.setText(Data.doctor_id);
        top_username.setText(name);
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == patients_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        } else if (event.getSource() == appointments_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);
        } else if (event.getSource() == profile_btn) {
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
        }
    }

    public void logoutBtn() {

        try {
            if (tray.confirmMessage("ETES VOUS SUR DE VOULOIR QUITTER?")) {
                Data.doctor_id = "";
                Data.doctor_name = "";
                Parent root = FXMLLoader.load(getClass().getResource("DoctoPage.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                // TO HIDE YOUR MAIN FORM
                logout_btn.getScene().getWindow().hide();

                Data.doctor_id = "";
                Data.doctor_name = "";
                Data.temp_PatientID = 0;
                Data.temp_name = "";
                Data.temp_gender = "";
                Data.temp_number = Long.parseLong("0");
                Data.temp_address = "";
                Data.temp_status = "";
                Data.temp_date = "";
                Data.temp_path = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void runTime() {
        new Thread() {
            public void run() {
                // Utiliser Locale.FRENCH pour le formatage en français
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.FRENCH);

                // Timeline pour le défilement
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
                    // Met à jour le texte de l'horloge
                    String currentTime = format.format(new Date());
                    date_time.setText(currentTime);

                    // Défilement du texte
                    double width = date_time.getLayoutBounds().getWidth();
                    date_time.setTranslateX(date_time.getTranslateX() - 10); // Ajustez la valeur pour la vitesse du défilement

                    // Reset du positionnement quand le texte est complètement sorti de l'écran
                    if (date_time.getTranslateX() < -width) {
                        date_time.setTranslateX(1000); // Remplacez 1000 par la largeur de votre fenêtre
                    }
                }));

                // Démarre le Timeline pour qu'il s'exécute chaque demi-seconde (500 ms)
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();

                // Boucle pour garder le thread en vie
                while (true) {
                    try {
                        Thread.sleep(1000); // 1000 ms = 1s
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

// Méthode pour charger tous les rendez-vous après la recherche ou réinitialisation
    public void loadAllAppointments() {
        String sql = "SELECT * FROM appointment WHERE doctor = '" + Data.doctor_id + "'";

        connect = Database.connectDB();
        ObservableList<AppointmentData> allData = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                AppointmentData appointment = new AppointmentData(
                        result.getInt("appointment_id"),
                        result.getString("name"),
                        result.getString("gender"),
                        result.getLong("mobile_number"),
                        result.getString("description"),
                        result.getString("address"),
                        result.getString("status"),
                        result.getDate("schedule")
                );
                allData.add(appointment);
            }

            appointments_tableView.setItems(allData); // Réinitialiser la table avec toutes les données

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void filterAppointments() {
        String searchTerm = appointments_searchField.getText().trim();
        String filterBy = ""; // Initialize filterBy string

        // Check which filter options are selected
        if (!searchTerm.isEmpty()) {
            filterBy += " (name LIKE '%" + searchTerm + "%' OR mobile_number LIKE '%" + searchTerm + "%')";
        }

        if (appointments_status.getSelectionModel().getSelectedItem() != null) {
            String selectedStatus = appointments_status.getSelectionModel().getSelectedItem();
            if (!filterBy.isEmpty()) {
                filterBy += " AND ";
            }
            filterBy += " status = '" + selectedStatus + "'";
        }

        // Add filtering by date
        if (appoinment_schedule.getValue() != null) {
            java.sql.Date selectedDate = java.sql.Date.valueOf(appoinment_schedule.getValue());
            if (!filterBy.isEmpty()) {
                filterBy += " AND ";
            }
            filterBy += " schedule = '" + selectedDate + "'";
        }

        // Build the final query with filtering conditions
        String sql = "SELECT * FROM appointment WHERE doctor = '" + Data.doctor_id + "'";
        if (!filterBy.isEmpty()) {
            sql += " AND " + filterBy;
        }

        ObservableList<AppointmentData> filteredData = FXCollections.observableArrayList();

        connect = Database.connectDB();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData aData;
            while (result.next()) {
                aData = new AppointmentData(
                        result.getInt("appointment_id"),
                        result.getString("name"),
                        result.getString("gender"),
                        result.getLong("mobile_number"),
                        result.getString("description"),
                        result.getString("address"),
                        result.getString("status"),
                        result.getDate("schedule")
                );
                filteredData.add(aData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update the table view with filtered data
        appointments_tableView.setItems(filteredData);

        // Réinitialiser la table si aucun résultat n'est trouvé
        if (filteredData.isEmpty()) {
            loadAllAppointments();
        }
    }

// Méthode pour charger tous les rendez-vous
    public void loadAllAppointmentss() {
        String sql = "SELECT * FROM appointment WHERE doctor = '" + Data.doctor_id + "'";

        connect = Database.connectDB();
        ObservableList<AppointmentData> allData = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                AppointmentData appointment = new AppointmentData(
                        result.getInt("appointment_id"),
                        result.getString("name"),
                        result.getString("gender"),
                        result.getLong("mobile_number"),
                        result.getString("description"),
                        result.getString("address"),
                        result.getString("status"),
                        result.getDate("schedule")
                );
                allData.add(appointment);
            }

            appointments_tableView.setItems(allData); // Réinitialiser la table avec toutes les données

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

//    private ObservableList<AppointmentData> appointmentListrv = FXCollections.observableArrayList();
//    private FilteredList<AppointmentData> filteredAppointments;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        displayAdminIdNumberName();
        runTime();

        dashboardDisplayIP();
        dashbboardDisplayTP();
        dashboardDisplayAP();
        dashboardDisplayTA();
        dashboardDisplayData();
        dashboardNOP();
        dashboardNOA();

        appointmentShowData();
        appointmentGenderList();
        appointmentStatusList();
        appointmentAppointmentID();
        //    appointmentGetAppointmentID();

        patientGenderList();

        //profile
        profileSpecializedList();
        profileStatusList();
        profileGenderList();
        profileFields(); // Pour afficher tous les details aux champs
        profileLabels();
        profileDisplayImages(); // POUR AFFICHER LA PHOTO DE PROFIL DU DOCTEUR

        // recherche
        // Ajouter un listener pour le champ de recherche
        appointments_searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterAppointments(); // Appeler le filtrage dès que le texte change
        });

        // Ajouter un listener pour la sélection du statut (ComboBox)
        appointments_status.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            filterAppointments(); // Appeler le filtrage dès que le statut change
        });

        // Ajouter un listener pour le DatePicker (appoinment_schedule)
        appoinment_schedule.valueProperty().addListener((observable, oldValue, newValue) -> {
            filterAppointments(); // Appeler le filtrage dès que la date change
        });

        // Charger tous les rendez-vous lors de l'initialisation
        loadAllAppointments();
    }

}
////////////////

