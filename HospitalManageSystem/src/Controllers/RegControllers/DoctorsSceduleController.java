package Controllers.RegControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DoctorsSceduleController {
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
    private ComboBox<?> specialtyComboBox;

    @FXML
    private TableView<?> doctorsTable;

    @FXML
    private TableColumn<?, ?> idDocColumn;

    @FXML
    private TableColumn<?, ?> surnameColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> patronymicColumn;

    @FXML
    private TableColumn<?, ?> officeNumberColumn;

    @FXML
    private TableColumn<?, ?> operatingTimeColumn;

    @FXML
    private Button confirmButton;

    @FXML
    private DatePicker dataPicker;

    @FXML
    void initialize(){

    }
}
