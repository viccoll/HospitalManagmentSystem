package Controllers.CareWorkerControllers;

import Configs.AlertScene;
import Models.*;
import ServerHandlers.ClientHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class IssueAppointmentController {
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
    private Button returnBackButton;
    @FXML
    private Button issueAppointment;


    @FXML
    private Button confirmAppointmentType;
    @FXML
    private TableView<Appointment> AppointmentTable;
    @FXML
    private TableColumn<Employee,String> eventDateColumn;
    @FXML
    private TableColumn<Employee,String> eventTimeColumn;
    @FXML
    private TableColumn<Employee,String> statusColumn;


    @FXML
    private TableColumn<Employee,String> officeNumberColumn;
    @FXML
    private TableView<Employee> doctorsTable;
    @FXML
    private TableColumn<Employee,String> fullnameColumn;


    @FXML
    private TextField surnamePatientTextField;
    @FXML
    private TextField namePatientTextField;
    @FXML
    private TextField patronymicPatientTextField;

    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, String> surnameColumn;
    @FXML
    private TableColumn<Patient, String> nameColumn;
    @FXML
    private TableColumn<Patient, String> patronymicColumn;

    @FXML
    private ComboBox<Specialty> specialtyComboBox;
    @FXML
    private ComboBox<Employee> doctorComboBox;

    @FXML
    private Button confirmSpecialty;
    @FXML
    private Button confirmAC;
    @FXML
    private Button findPatientButton;
    @FXML
    private Button confirmDoctor;
    @FXML
    private ComboBox<AppointmentType> appointmentTypeComboBox;


    @FXML
    private Button confirmIssuesButton;
    private final ClientHandler clientHandler = ClientHandler.getClient();

    private Appointment mainAppointment = new Appointment();

    private Employee mainEmployee = new Employee();
    private AppointmentType mainAType;


    @FXML
    void initialize(){
        updateSpecialtiesComboBox();
        updateAppointmentTypeComboBox();
        confirmSpecialty.setOnAction(event -> {
            clientHandler.sendMessage("confirmSpecialty");
            Specialty specialty = specialtyComboBox.getValue();
            if(specialty != null){
                clientHandler.sendObject(true);
                updateDoctorsComboBox(specialty);
            }
            else
            {
                AlertScene.callAlert("Cпециальность не была выбрана.");
                clientHandler.sendObject(true);
            }
        });

        confirmAC.setOnAction(event -> {
            if( AppointmentTable.getSelectionModel().getSelectedItem() != null){
                mainAppointment = AppointmentTable.getSelectionModel().getSelectedItem();
                AlertScene.callAlert("Талон выбран.");
            }
            else AlertScene.callAlert("Талон не был выбран.");
        });

        findPatientButton.setOnAction(event -> findPatient());

        confirmDoctor.setOnAction(event -> {
            Employee employee = doctorComboBox.getValue();
            if(employee!=null) {
                updateAppointmentTable(employee);
            }
            else AlertScene.callAlert("Доктор не был выбран.");

        });

        confirmAppointmentType.setOnAction(event -> {
            AppointmentType appointmentType = appointmentTypeComboBox.getValue();
            if(appointmentType!=null) {
                mainAppointment.setIdType(appointmentType.getId());
                AlertScene.callAlert("Тип направления выбран.");
            }
            else AlertScene.callAlert("Тип направления не был выбран.");
        });

        confirmIssuesButton.setOnAction(event -> {

            if(mainAppointment == null){
                AlertScene.callAlert("Талон не был выбран.");
            }
            else if(mainAppointment.getIdType()==0)
          {
               AlertScene.callAlert("Тип направления не был выбран.");
            }
            else {
                if (patientTable.getSelectionModel().getSelectedItem()!=null)
                {
                    clientHandler.sendMessage("confirmIssuesButton");
                    Patient patient = patientTable.getSelectionModel().getSelectedItem();
                    clientHandler.sendObject(mainAppointment);
                    clientHandler.sendObject(patient);
                    AlertScene.callAlert("Талон успешно оформлен.");
                    updateAppointmentTable(mainEmployee);
                }
                else {

                    AlertScene.callAlert("Пациент не был выбран.");
                }
            }
        });

    }

    private void updateAppointmentTypeComboBox() {
        clientHandler.sendMessage("updateAppointmentTypeComboBox");
        boolean isUpdateSuccessfully = (boolean) clientHandler.readObject();
        if(isUpdateSuccessfully) {
            ArrayList<AppointmentType> typeArrayList = new ArrayList<>();
            AppointmentType.appointmentTypeList.clear();
            int size = (int) clientHandler.readObject();
            for(int i=0; i< size;i++){
                typeArrayList.add((AppointmentType) clientHandler.readObject());
            }
            AppointmentType.appointmentTypeList.addAll(typeArrayList);
        }
        appointmentTypeComboBox.setItems(AppointmentType.appointmentTypeList);
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
    private void updateDoctorsComboBox(Specialty specialty) {
        clientHandler.sendMessage("updateDoctorsComboBox");
        boolean isUpdateSuccessfully = (boolean) clientHandler.readObject();
        if(isUpdateSuccessfully) {
            Employee.employeesList.clear();
            ArrayList<Employee> arrayList = (ArrayList<Employee>)clientHandler.readObject();
            ArrayList<Employee> newArr = new ArrayList<>();
            for (Employee employee : arrayList) {
                if (employee.getIdSpecialty() == specialty.getId()) newArr.add(employee);
            }
            Employee.employeesList.addAll(newArr);
            doctorComboBox.setItems(Employee.employeesList);
        }
    }
    private void findPatient() {
        clientHandler.sendMessage("findPatient");
        Patient patient = new Patient();
        String surnamePatient = surnamePatientTextField.getText().trim();
        String namePatient = namePatientTextField.getText().trim();
        String patronymicPatient = patronymicPatientTextField.getText().trim();
        String regex = ".*\\d+.*";
        if(surnamePatient.equals("") || namePatient.equals("")
                || patronymicPatient.equals("") || surnamePatient.matches(regex)
                || namePatient.matches(regex) || patronymicPatient.matches(regex))
        {
            AlertScene.callAlert("Не все поля введены корректно!");
            clientHandler.sendObject(false);
        }
        else {
            clientHandler.sendObject(true);
            patient.setSurname(surnamePatient);
            patient.setName(namePatient);
            patient.setPatronymic(patronymicPatient);
            clientHandler.sendObject(patient);
            boolean isUpdateSuccessfully = (boolean) clientHandler.readObject();
            if(isUpdateSuccessfully) {
                boolean isNotEmpty = (boolean) clientHandler.readObject();
                if(isNotEmpty)
                {
                    ArrayList<Patient> arrayList = (ArrayList< Patient>)clientHandler.readObject();
                    Patient.patientsList.clear();
                    for (Patient patientS : arrayList) {
                        if (patientS.getSurname().equals(patient.getSurname())
                                && patientS.getName().equals(patient.getName())
                                && patientS.getPatronymic().equals(patient.getPatronymic()))
                        {
                            Patient.patientsList.add(patientS);
                        }
                    }
                    if(Patient.patientsList.size() == 0)  AlertScene.callAlert("Пациент не найден.");
                    patientTable.refresh();
                    surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
                    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
                    patientTable.setItems(Patient.patientsList);

                }
                else  AlertScene.callAlert("Пациент не найден.");
            }
            else  AlertScene.callAlert("Данные не найдены. Повторите снова.");
        }
    }

    private void updateAppointmentTable( Employee employee ) {

        clientHandler.sendMessage("updateAppointmentTable");
        clientHandler.sendObject(employee);
        boolean isUpdateSuccessfully = (boolean) clientHandler.readObject();

        if(isUpdateSuccessfully) {

            Appointment.appointmentsList.clear();
            ObservableList<Employee> newEmployee = FXCollections.observableArrayList();
            ObservableList<Appointment> newArr= FXCollections.observableArrayList();
            ArrayList<Appointment> appointments = new ArrayList<>();
            newEmployee.clear();
            newArr.clear();
            int size = (int) clientHandler.readObject();
            for(int i=0;i<size;i++)
            {
                appointments.add((Appointment) clientHandler.readObject());
            }
            for (Appointment appointment : appointments) {
                if(appointment.getIdEmployee()==employee.getId())
                {
                    if (appointment.isStatus() && LocalDate.parse(appointment.getDate()).isAfter(LocalDate.now()))
                    {
                        newArr.add(appointment);
                        newEmployee.add(employee);
                    }
                }
            }
            doctorsTable.getItems().removeAll(newEmployee);
            AppointmentTable.getItems().removeAll(newArr);
            doctorsTable.refresh();
            fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            officeNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Office"));
            doctorsTable.refresh();
            doctorsTable.setItems(newEmployee);
            AppointmentTable.refresh();
            eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
            eventTimeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("Status"));
            AppointmentTable.setItems(newArr);
            if(newArr.size() == 0 || newEmployee.size() == 0) {
                AlertScene.callAlert("Талоны не найдены");
            }
            mainEmployee = employee;
        }
        else AlertScene.callAlert("Данные не найдены. Повторите снова.");
    }
}
