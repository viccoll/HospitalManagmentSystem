package Controllers.RegControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private ComboBox<String> specialtyComboBox;
    @FXML
    private ComboBox<String> doctorComboBox;

    @FXML
    private TableView<?> doctorsTable;
    @FXML
    private TableColumn<?, ?> idDocColumn;
    @FXML
    private TableColumn<?, ?> fullnameColumn;
    @FXML
    private TableColumn<?, ?> eventDateColumn;
    @FXML
    private TableColumn<?, ?> eventTimeColumn;
    @FXML
    private TableColumn<?, ?> statusColumn;
    @FXML
    private TableColumn<?, ?> officeNumberColumn;


    @FXML
    private TextField surnamePatientTextField;
    @FXML
    private TextField namePatientTextField;
    @FXML
    private TextField patronymicPatientTextField;
    @FXML
    private TextField departmentTextField;


    @FXML
    private TableView<?> patientTable;
    @FXML
    private TableColumn<?, ?> surnameColumn;
    @FXML
    private TableColumn<?, ?> nameColumn;
    @FXML
    private TableColumn<?, ?> patronymicColumn;
    @FXML
    private TableColumn<?, ?> departmentColumn;

    @FXML
    private Button confirmDoctorButton;

    @FXML
    private Button confirmPatientButton;

    @FXML
    void initialize(){

    }
}
