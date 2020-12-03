import Models.Employee;
import Models.Specialty;

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

                Employee personalAccount = (Employee) request.readObject();
                boolean isAuthorize = personalAccount.authorize();
                respond.writeObject(isAuthorize);

                if (isAuthorize) {
                    Employee employee = new Employee();

                    boolean isEmployeeFound = employee.findEmployee(personalAccount.getId());
                    respond.writeObject(isEmployeeFound);

                    if (isEmployeeFound)
                    {
                        respond.writeObject(employee);

                        switch (employee.getId()){
                            case 1 :{
                                RegServerHandler regServerHandler = new RegServerHandler(request,respond);
                                String action = "";
                                do {
                                    action = request.readObject().toString();
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
                                                     default:actionDetailed = "exit";
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
                                                        Specialty specialty = (Specialty) request.readObject();
                                                        regServerHandler.getDoctorsSchedule(specialty);
                                                        break;
                                                    }
                                                    default:actionDetailed = "exit";
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
                                                    default:actionDetailed = "exit";
                                                }
                                            }while(!actionDetailed.equals("exit"));
                                            break;
                                        }
                                        case "regIssueAppointmentCard": {
                                            if(request.readObject().toString().equals("updateSpecialtiesComboBox"))
                                            {
                                                regServerHandler.updateSpecialtiesComboBox("doctors");
                                            }
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
                                System.out.println("МеРаботник");
                                break;
                            }
                        }
                    }
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
