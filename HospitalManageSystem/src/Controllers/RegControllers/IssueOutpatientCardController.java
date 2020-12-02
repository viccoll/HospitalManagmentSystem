package Controllers.RegControllers;

import Models.Address;
import Models.Patient;
import ServerHandlers.ClientHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

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
    private ComboBox<String> streetComboBox;
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
    private ToggleGroup genderGroup;


    @FXML
    void initialize(){
        birthdayDatePicker.setValue(LocalDate.now());
        clearButton.setOnAction(event -> clearFields());
        confirmOutPatientCardButton.setOnAction(event -> addOutPatientCard());
    }

    private void addOutPatientCard() {
        String surnamePatient = surnamePatientTextField.getText().trim();
        String namePatient = namePatientTextField.getText().trim();
        String patronymicPatient = patronymicPatientTextField.getText().trim();
        String birthdayDate = birthdayDatePicker.getValue().toString();

        String gender = ((RadioButton)genderGroup.getSelectedToggle()).getText();
        String street = streetComboBox.getValue();

        String houseNumber = houseNumberTextField.getText().trim();
        String flatNumber = flatNumberTextField.getText().trim();
        String corpusNumber = corpusNumberTextField.getText().trim();

        String passportSeries = passportSeriesTextField.getText().trim();
        String passportNumber = passportNumberTextField.getText().trim();
        String phone = phoneTextField.getText().trim();

        String regex = ".*\\d+.*";
        String regexP = ".*\\D+.*";
        String regexPhone ="^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";
        if(surnamePatient.equals("") || namePatient.equals("")
                || patronymicPatient.equals("") //|| street == null//
                ||!birthdayDatePicker.getValue().isBefore(LocalDate.now())
                || houseNumber.equals("") || flatNumber.equals("")
                || corpusNumber.equals("") || passportSeries.equals("")
                || passportNumber.equals("") || phone.equals("")
                || surnamePatient.matches(regex) || namePatient.matches(regex)
                || patronymicPatient.matches(regex) || houseNumber.matches(regexP)
                || flatNumber.matches(regexP) || corpusNumber.matches(regexP)
                || passportSeries.matches(regex) || passportSeries.length() !=2
                || passportNumber.matches(regexP) || passportNumber.length() !=7
                || phone.length() !=13 || !phone.matches(regexPhone)){

            callAlert("Не все поля введены корректно!");
        }
        else {
            Patient patient = new Patient();
            ClientHandler clientHandler = ClientHandler.getClient();
            clientHandler.sendMessage("addOutPatientCard");
            patient.setSurname(surnamePatient);
            patient.setName(namePatient);
            patient.setPatronymic(patronymicPatient);
            patient.setBirthday(birthdayDate);
            patient.setPassportNumber(Integer.parseInt(passportNumber));
            patient.setPassportSeries(passportSeries);
            patient.setPhoneNumber(passportNumber);
            Address address = new Address(street,Integer.parseInt(flatNumber),
                    Integer.parseInt(houseNumber),Integer.parseInt(corpusNumber));
            patient.setAddress(address);
            clientHandler.sendObject(patient);
        }

    }

    private void clearFields() {
        surnamePatientTextField.setText("");
        namePatientTextField.setText("");
        patronymicPatientTextField.setText("");
        birthdayDatePicker.setValue(LocalDate.now());
        updateStreetComboBox();
        houseNumberTextField.setText("");
        flatNumberTextField.setText("");
        corpusNumberTextField.setText("");
        passportSeriesTextField.setText("");
        passportNumberTextField.setText("");
        phoneTextField.setText("");
    }

    private void updateStreetComboBox() {
    }
    private void callAlert(String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }
}
