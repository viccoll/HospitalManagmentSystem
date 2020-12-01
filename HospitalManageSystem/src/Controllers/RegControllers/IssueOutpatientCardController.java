package Controllers.RegControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class IssueOutpatientCardController {
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
    private Button confirmOutPatientCardButton;
    @FXML
    private Button clearButton;

    @FXML
    private TextField phoneTextField;
    @FXML
    private ComboBox<?> streetComboBox;
    @FXML
    private TextField houseNumberTextField;
    @FXML
    private TextField flatNumberTextField;
    @FXML
    private TextField corpusNumberTextField;
    @FXML
    private TextField passportSeriesTextField;
    @FXML
    private TextField passportNumberTextField;
    @FXML
    private DatePicker birthdayDatePicker;
    @FXML
    private RadioButton manRadio;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton womanRadio;

    @FXML
    void initialize(){

    }
}
