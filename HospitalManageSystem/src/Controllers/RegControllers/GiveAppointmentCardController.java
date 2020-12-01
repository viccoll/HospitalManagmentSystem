package Controllers.RegControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GiveAppointmentCardController {
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
    private TextField surnamePatientTextField;
    @FXML
    private TextField namePatientTextField;
    @FXML
    private TextField patronymicPatientTextField;
    @FXML
    private TextField departmentTextField;


    @FXML
    private TableView<?> patientTableWithCard;

    @FXML
    private TableColumn<?, ?> fullnamePatientColumn;
    @FXML
    private TableColumn<?, ?> appointmentCardIdColumn;
    @FXML
    private TableColumn<?, ?> eventDateColumn;
    @FXML
    private TableColumn<?, ?> eventTimeColumn;
    @FXML
    private TableColumn<?, ?> fullnameDoctorColumn;
    @FXML
    private Button confirmPatientButton;
    @FXML
    private Button generateAppointmentCardButton;

    @FXML
    void initialize(){

    }
}
