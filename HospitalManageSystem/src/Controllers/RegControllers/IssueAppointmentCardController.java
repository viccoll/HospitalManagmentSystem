package Controllers.RegControllers;

import Configs.FXMLConfigs;
import Models.Employee;
import Models.Patient;
import Models.Specialty;
import ServerHandlers.ClientHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class IssueAppointmentCardController {

    @FXML
    private Label fullnameRegLabel;

    @FXML
    private Button desktopRegButton;
    @FXML
    private Button issueAppointmentCardButton;
    @FXML
    private Button giveAppointmentCardButton;
    @FXML
    private Button issueOutpatientCardButton;
    @FXML
    private Button getDoctorScheduleButton;
    @FXML
    private Button editReqProfileButton;


    @FXML
    private Button returnBackButton;

    @FXML
    private ComboBox<Specialty> specialtyComboBox;

    @FXML
    private ComboBox<String> doctorComboBox;

    @FXML
    private TableView<Employee> doctorsTable;
    @FXML
    private TableColumn<Employee,String> fullnameColumn;
    @FXML
    private TableColumn<Employee,String> eventDateColumn;
    @FXML
    private TableColumn<Employee,String> eventTimeColumn;
    @FXML
    private TableColumn<Employee,String> statusColumn;
    @FXML
    private TableColumn<Employee,String> officeNumberColumn;
    @FXML
    private TableView<?> AppointmentTable;


    @FXML
    private TextField surnamePatientTextField;
    @FXML
    private TextField namePatientTextField;
    @FXML
    private TextField patronymicPatientTextField;
    @FXML
    private TextField departmentTextField;


    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, String> surnameColumn;
    @FXML
    private TableColumn<Patient, String> nameColumn;
    @FXML
    private TableColumn<Patient, String> patronymicColumn;
    @FXML
    private TableColumn<Patient, String> departmentColumn;

    @FXML
    private Button confirmDoctorButton;

    @FXML
    private Button confirmPatientButton;

    private final ClientHandler clientHandler = ClientHandler.getClient();
    @FXML
    void initialize(){
        updateSpecialtiesComboBox();
        desktopRegButton.setOnAction(event -> {
            desktopRegButton.getScene().getWindow().hide();
            clientHandler.sendMessage("desktopRegButton");
            changeScene(FXMLConfigs.regAccount);
        });
        getDoctorScheduleButton.setOnAction(event -> {
            getDoctorScheduleButton.getScene().getWindow().hide();
            clientHandler.sendMessage("regDoctorScedule");
            changeScene(FXMLConfigs.regDoctorScedule);
        });
        editReqProfileButton.setOnAction((event -> {
            editReqProfileButton.getScene().getWindow().hide();
            clientHandler.sendMessage("regEditProfile");
            changeScene(FXMLConfigs.regEditProfile);
        }));
        giveAppointmentCardButton.setOnAction(event -> {
            giveAppointmentCardButton.getScene().getWindow().hide();
            clientHandler.sendMessage("regGiveAppointmentCard");
            changeScene(FXMLConfigs.regGiveAppointmentCard);
        });
        issueAppointmentCardButton.setOnAction(event -> {
            issueAppointmentCardButton.getScene().getWindow().hide();
            clientHandler.sendMessage("regIssueAppointmentCard");
            changeScene(FXMLConfigs.regIssueAppointmentCard);
        });
        issueOutpatientCardButton.setOnAction(event -> {
            issueOutpatientCardButton.getScene().getWindow().hide();
            clientHandler.sendMessage("regIssueOutpatientCard");
            changeScene(FXMLConfigs.regIssueOutpatientCard);
        });
        returnBackButton.setOnAction(event -> {
            returnBackButton.getScene().getWindow().hide();
            clientHandler.sendMessage("returnBack");
            changeScene("../Views/authorization.fxml");
        });
    }
    private void callAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
    private void changeScene(String fxmlPath) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    private void updateSpecialtiesComboBox() {
        clientHandler.sendMessage("updateSpecialtiesComboBox");
        boolean isUpdateSuccessfully = (boolean) clientHandler.readObject();
        if(isUpdateSuccessfully) {
            ArrayList<Specialty> specialtyArrayList = (ArrayList<Specialty>)clientHandler.readObject();
            Specialty.update(specialtyArrayList);
        }
        specialtyComboBox.setItems(Specialty.listSpecialties);
    }
}
