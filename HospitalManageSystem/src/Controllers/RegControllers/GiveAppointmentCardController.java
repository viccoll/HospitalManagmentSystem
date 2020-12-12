package Controllers.RegControllers;

import Configs.FXMLConfigs;
import Models.Appointment;
import Models.Employee;
import Models.Patient;
import ServerHandlers.ClientHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class GiveAppointmentCardController {

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
    private TextField surnamePatientTextField;
    @FXML
    private TextField namePatientTextField;
    @FXML
    private TextField patronymicPatientTextField;

    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, Integer> appointmentCardIdColumn;
    @FXML
    private TableColumn<Appointment, String > eventDateColumn;
    @FXML
    private TableColumn<Appointment, String> eventTimeColumn;
    @FXML
    private TableColumn<Appointment, Integer> patientIdColumn;
    @FXML
    private TableColumn<Appointment, Integer> employeeId;

    @FXML
    private Button confirmPatientButton;

    @FXML
    private Button generateAppointmentCardButton;

    @FXML
    private Button returnBackButton;

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> surnameColumn;
    @FXML
    private TableColumn<Employee, String> doctorNameColumn;
    @FXML
    private TableColumn<Employee, String> officeNumberColumn;


    private final ClientHandler clientHandler = ClientHandler.getClient();

    @FXML
    void initialize(){
        confirmPatientButton.setOnAction(event -> {
            confirmPatient();
        });
        generateAppointmentCardButton.setOnAction(event -> {
            if (appointmentTable.getSelectionModel().getSelectedItem()!=null)
            {
                clientHandler.sendObject("generateAppointmentCard");
                Appointment appointment = appointmentTable.getSelectionModel().getSelectedItem();
                 clientHandler.sendObject(appointment);
            }
            else callAlert("Талон не был выбран.");
        });

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
            changeScene(FXMLConfigs.authorization);
        });
    }

    private void confirmPatient() {
        clientHandler.sendMessage("confirmPatient");
        Patient patient = new Patient();
        String surnamePatient = surnamePatientTextField.getText().trim();
        String namePatient = namePatientTextField.getText().trim();
        String patronymicPatient = patronymicPatientTextField.getText().trim();
        String regex = ".*\\d+.*";
        if (surnamePatient.equals("") || namePatient.equals("")
                || patronymicPatient.equals("") || surnamePatient.matches(regex)
                || namePatient.matches(regex) || patronymicPatient.matches(regex)) {
            callAlert("Не все поля введены корректно!");
            clientHandler.sendObject(false);
        } else {
            clientHandler.sendObject(true);
            patient.setSurname(surnamePatient);
            patient.setName(namePatient);
            patient.setPatronymic(patronymicPatient);
            clientHandler.sendObject(patient);
            boolean isPatientFounded = (Boolean) clientHandler.readObject();

            if(isPatientFounded){
                boolean isUpdate = (Boolean) clientHandler.readObject();

                if(isUpdate){
                    int size = (int) clientHandler.readObject();
                    Appointment.appointmentsList.clear();
                    for(int i=0;i<size;i++)
                    {
                        Appointment.appointmentsList.add((Appointment) clientHandler.readObject());
                    }
                    int sizeDoctors = (int) clientHandler.readObject();
                    Employee.employeesList.clear();
                    for(int i=0;i<sizeDoctors;i++)
                    {
                        Employee.employeesList.add((Employee) clientHandler.readObject());
                    }
                    appointmentTable.refresh();
                    eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
                    eventTimeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
                    appointmentCardIdColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
                    patientIdColumn.setCellValueFactory(new PropertyValueFactory<>("IdPatient"));
                    employeeId.setCellValueFactory(new PropertyValueFactory<>("IdEmployee"));
                    appointmentTable.setItems(Appointment.appointmentsList);

                    employeeTable.refresh();
                    surnameColumn.setCellValueFactory(new PropertyValueFactory<>("Surname"));
                    doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
                    officeNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Office"));
                    employeeTable.setItems(Employee.employeesList);

                    if(Appointment.appointmentsList.size() == 0) callAlert("Талоны не найдены");
                }
                else callAlert("Талоны не найдены.");
            }
            else callAlert("Пациент не найден.");

        }
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
}
