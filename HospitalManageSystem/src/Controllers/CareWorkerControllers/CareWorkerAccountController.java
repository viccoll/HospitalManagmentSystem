package Controllers.CareWorkerControllers;

import Configs.ChangeScene;
import Configs.FXMLConfigs;
import Models.Employee;
import ServerHandlers.ClientHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class CareWorkerAccountController {


    @FXML
    private Button desktopCareWorkerButton;
    @FXML
    private Button getSceduleCareWorkerButton;
    @FXML
    private Button getPatientsCareWorkerButton;
    @FXML
    private Button startAppointmentWithoutOrderButton;
    @FXML
    private Button editCareWorkerProfileButton;
    @FXML
    private Button  issueAppointment;

    @FXML
    private Button returnBackButton;

    @FXML
    private Label surnameCareWorkerLabel;
    @FXML
    private Label nameCareWorkerLabel;
    @FXML
    private Label patronymicCareWorkerLabel;
    @FXML
    private Label birthdayDateCareWorkerLabel;

    private final ClientHandler clientHandler = ClientHandler.getClient();
    @FXML
    void initialize() {

      updateCareWorkerAccountData();
        editCareWorkerProfileButton.setOnAction(event -> {
            editCareWorkerProfileButton.getScene().getWindow().hide();
            clientHandler.sendMessage("editCareWorkerProfile");
            ChangeScene.change(FXMLConfigs.careWorkerEditAccount,getClass());
        });
        returnBackButton.setOnAction(event -> {
            returnBackButton.getScene().getWindow().hide();
            clientHandler.sendMessage("returnBack");
            ChangeScene.change(FXMLConfigs.authorization, getClass());
        });
        issueAppointment.setOnAction(event -> {
            issueAppointment.getScene().getWindow().hide();
            clientHandler.sendMessage("issueAppointment");
            ChangeScene.change(FXMLConfigs.careWorkerIssueAppointment, getClass());
        });

    }

    private void updateCareWorkerAccountData() {
        surnameCareWorkerLabel.setText(Employee.mainEmployee.getSurname());
        nameCareWorkerLabel.setText(Employee.mainEmployee.getName());
        patronymicCareWorkerLabel.setText(Employee.mainEmployee.getPatronymic());
        int age = Period.between(LocalDate.parse(Employee.mainEmployee.getBirthday(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd")),LocalDate.now() ).getYears();
        birthdayDateCareWorkerLabel.setText(Employee.mainEmployee.getBirthday() +" ("+ age +")");
    }

}
