package ServerHandlers;

import DBHandlers.AppointmentDBHandler;
import Models.Appointment;
import Models.Employee;
import Models.Patient;
import ServerHandlers.RegServerHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerHandler implements Runnable {

    protected Socket clientSocket = null;
    ObjectInputStream request; //Принятие
    ObjectOutputStream respond; //Отправка

    public ServerHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            request = new ObjectInputStream(clientSocket.getInputStream());
            respond = new ObjectOutputStream(clientSocket.getOutputStream());

            while (true) {

                Employee personalAccount = new Employee();
                personalAccount.setLogin((String)request.readObject());
                personalAccount.setPassword((String)request.readObject());

                boolean isAuthorize = personalAccount.authorize();
                respond.writeObject(isAuthorize);

                if (isAuthorize) {
                        respond.writeObject(personalAccount);
                        RegServerHandler regServerHandler = new RegServerHandler(request,respond);
                        switch (personalAccount.getIdSpecialty()){
                            case 1 :{
                                String action = "";
                                action = request.readObject().toString();
                                do {
                                    switch (action){
                                        case "regIssueOutpatientCard": {
                                             if(request.readObject().toString().equals("updateStreetComboBox"))
                                             {
                                                 regServerHandler.updateStreetComboBox();
                                             }
                                             String actionDetailed = "";
                                             do {
                                                 actionDetailed = request.readObject().toString();
                                                 switch (actionDetailed){
                                                     case "addOutPatientCard":  regServerHandler.addOutPatientCard();break;
                                                     case "clearFields" : break;
                                                     default: action = actionDetailed; actionDetailed = "exit";
                                                 }
                                             }while(!actionDetailed.equals("exit"));
                                             break;
                                        }
                                        case "desktopRegButton": {break;}
                                        case "regDoctorScedule":{
                                            if(request.readObject().toString().equals("updateSpecialtiesComboBox"))
                                            {
                                                regServerHandler.updateSpecialtiesComboBox("all");
                                            }
                                            String actionDetailed = "";
                                            do {
                                                actionDetailed = request.readObject().toString();
                                                switch (actionDetailed){
                                                    case "getDoctorSchedule": {
                                                        regServerHandler.sendAllEmployeeRecords();
                                                        break;
                                                    }
                                                    default:action = actionDetailed; actionDetailed = "exit";
                                                }
                                            }while(!actionDetailed.equals("exit"));
                                            break;
                                        }
                                        case "regEditProfile" : {
                                            String actionDetailed = "";
                                            do {
                                                actionDetailed = request.readObject().toString();
                                                switch (actionDetailed){
                                                    case "changeLogin": {
                                                        Employee.mainEmployee = (Employee) request.readObject();
                                                        Employee employeeLP = (Employee) request.readObject();
                                                        regServerHandler.changeLogin(employeeLP);
                                                        break;
                                                    }
                                                    case "changePassword":{
                                                        Employee.mainEmployee = (Employee) request.readObject();
                                                        Employee employeeLP = (Employee) request.readObject();
                                                        regServerHandler.changePassword(employeeLP);
                                                        break;
                                                    }
                                                    default: action = actionDetailed; actionDetailed = "exit";
                                                }
                                            }while(!actionDetailed.equals("exit"));
                                            break;
                                        }
                                        case "regIssueAppointmentCard": {
                                            if(request.readObject().toString().equals("updateSpecialtiesComboBox"))
                                            {
                                                regServerHandler.updateSpecialtiesComboBox("doctors");
                                            }

                                            String actionDetailed = "";
                                            do {
                                                actionDetailed = request.readObject().toString();
                                                switch (actionDetailed){
                                                    case "confirmSpecialty": {
                                                        if((Boolean)request.readObject()){
                                                            if(request.readObject().toString().equals("updateDoctorsComboBox"))
                                                            {
                                                                regServerHandler.sendAllEmployeeRecords();
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "updateAppointmentTable":{
                                                        Employee doctor = (Employee) request.readObject();
                                                        System.out.println(doctor.getSurname());
                                                        regServerHandler.sendALLAppointment(doctor);
                                                        break;
                                                    }
                                                    case "findPatient":{
                                                        if((Boolean)request.readObject()){
                                                            Patient patient = (Patient) request.readObject();
                                                            regServerHandler.sendAllPatients();
                                                        }
                                                        break;
                                                    }
                                                    case "confirmIssuesButton":{
                                                        if((Boolean) request.readObject())
                                                        {
                                                            if((Boolean) request.readObject()){
                                                                Appointment appointment = (Appointment) request.readObject();
                                                                Patient patient = (Patient) request.readObject();
                                                                System.out.println(patient);
                                                                System.out.println(appointment);
                                                                AppointmentDBHandler appointmentDBHandler = new AppointmentDBHandler();
                                                                appointmentDBHandler.updateAppointment(patient,appointment);
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    default: action = actionDetailed; actionDetailed = "exit";
                                                }
                                            }while( !actionDetailed.equals("exit"));
                                            break;
                                        }
                                        case "regGiveAppointmentCard":{
                                            String actionDetailed = "";
                                            do {
                                                actionDetailed = request.readObject().toString();
                                                switch (actionDetailed){
                                                    case "confirmPatient":{
                                                        if((Boolean)request.readObject())
                                                        {
                                                            Patient patient = (Patient) request.readObject();
                                                            System.out.println(patient);
                                                            regServerHandler.findAppointments(patient);
                                                        }
                                                       break;
                                                   }
                                                    case "generateAppointmentCard":{
                                                        Appointment appointment = (Appointment) request.readObject();
                                                        regServerHandler.generateAppointmentCard(appointment);
                                                        break;
                                                    }
                                                    default: action = actionDetailed; actionDetailed = "exit";
                                                }
                                            }while( !actionDetailed.equals("exit"));
                                            break;
                                        }
                                    }
                                }while(!action.equals("returnBack"));
                            }
                            case 2:{
                                System.out.println("Админ");
                                break;
                            }
                            case 3:{
                                CareWorkerServerHandler careWorkerServerHandler = new CareWorkerServerHandler(request,respond);
                                String action = "";
                                action = request.readObject().toString();
                                do {
                                    switch (action){
                                        case "desktopCareWorker": break;
                                        case "editCareWorkerProfile":{
                                            String actionDetailed = "";
                                            do {
                                                actionDetailed = request.readObject().toString();
                                                switch (actionDetailed){
                                                    case "changeLogin": {
                                                        Employee.mainEmployee = (Employee) request.readObject();
                                                        Employee employeeLP = (Employee) request.readObject();
                                                        regServerHandler.changeLogin(employeeLP);
                                                        break;
                                                    }
                                                    case "changePassword":{
                                                        Employee.mainEmployee = (Employee) request.readObject();
                                                        Employee employeeLP = (Employee) request.readObject();
                                                        regServerHandler.changePassword(employeeLP);
                                                        break;
                                                    }
                                                    default: action = actionDetailed; actionDetailed = "exit";
                                                }
                                            }while(!actionDetailed.equals("exit"));
                                            break;
                                        }
                                        case "issueAppointment":{
                                            if(request.readObject().toString().equals("updateSpecialtiesComboBox"))
                                            {
                                                regServerHandler.updateSpecialtiesComboBox("doctors");
                                            }
                                            if(request.readObject().toString().equals("updateAppointmentTypeComboBox"))
                                            {
                                                regServerHandler.updateAppointmentTypeComboBox();
                                            }
                                            String actionDetailed = "";
                                            do {
                                                actionDetailed = request.readObject().toString();
                                                switch (actionDetailed){
                                                    case "confirmSpecialty": {
                                                        if((Boolean)request.readObject()){
                                                            if(request.readObject().toString().equals("updateDoctorsComboBox"))
                                                            {
                                                                regServerHandler.sendAllEmployeeRecords();
                                                            }
                                                        }
                                                        break;
                                                    }
                                                    case "updateAppointmentTable":{
                                                        Employee doctor = (Employee) request.readObject();
                                                        System.out.println(doctor.getSurname());
                                                        regServerHandler.sendALLAppointment(doctor);
                                                        break;
                                                    }
                                                    case "findPatient":{
                                                        if((Boolean)request.readObject()){
                                                            Patient patient = (Patient) request.readObject();
                                                            regServerHandler.sendAllPatients();
                                                        }
                                                        break;
                                                    }
                                                    case "confirmIssuesButton":{

                                                        Appointment appointment = (Appointment) request.readObject();
                                                        Patient patient = (Patient) request.readObject();
                                                        System.out.println(patient);
                                                        System.out.println(appointment);
                                                        AppointmentDBHandler appointmentDBHandler = new AppointmentDBHandler();
                                                        appointmentDBHandler.updateAppointment(patient,appointment);

                                                        break;
                                                    }
                                                    default: action = actionDetailed; actionDetailed = "exit";
                                                }
                                            }while(!actionDetailed.equals("exit"));
                                            break;
                                        }
                                    }
                                }while(!action.equals("returnBack"));
                                break;
                            }
                        }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
