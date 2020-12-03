import Configs.DBConst;
import DBHandlers.AddressDBHandler;
import DBHandlers.EmployeeDBHandler;
import DBHandlers.PatientDBHandler;
import DBHandlers.SpecialtyDBHandler;
import Models.Employee;
import Models.Patient;
import Models.Specialty;
import Models.Street;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegServerHandler {
    ObjectInputStream request; //Принятие
    ObjectOutputStream respond; //Отправка


    public RegServerHandler(ObjectInputStream request, ObjectOutputStream respond) {
        this.request = request;
        this.respond = respond;
    }

    public void addOutPatientCard() {
        try {
            Patient patient = (Patient)request.readObject();
            PatientDBHandler patientDBHandler = new PatientDBHandler();
            boolean isPatientAdded = patientDBHandler.addRecord(patient);
            respond.writeObject(isPatientAdded);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateStreetComboBox() {
        AddressDBHandler addressDBHandler = new AddressDBHandler();
        ResultSet resSet = addressDBHandler.getAllStreetRecords();
        if(resSet != null){
            try {
                respond.writeObject(true);
                try {

                    Street.listStreets.clear();
                    while (resSet.next())
                    {
                        Street.listStreets.add(new Street(resSet.getInt(DBConst.STREET_ID),
                                resSet.getString(DBConst.STREET_NAME)));

                    }
                    respond.writeObject(Street.listStreets);

                }
                catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                respond.writeObject(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void updateSpecialtiesComboBox(String choice) {
        SpecialtyDBHandler specialtyDBHandler = new SpecialtyDBHandler();
        ResultSet resSet = specialtyDBHandler.getAllStreetRecords();
        if(resSet != null){
            try {
                respond.writeObject(true);
                try {

                    Specialty.listSpecialties.clear();
                    while (resSet.next())
                    {
                        if(choice.equals("all"))  {
                            Specialty.listSpecialties.add(new Specialty(resSet.getInt(DBConst.SPECIALTY_ID),
                                    resSet.getString(DBConst.SPECIALTY_NAME)));
                        }
                        else if(choice.equals("doctors")){
                            if(resSet.getInt(DBConst.SPECIALTY_ID)>2)
                            {
                                Specialty.listSpecialties.add(new Specialty(resSet.getInt(DBConst.SPECIALTY_ID),
                                        resSet.getString(DBConst.SPECIALTY_NAME)));
                            }
                        }
                    }
                    respond.writeObject(Specialty.listSpecialties);

                }
                catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                respond.writeObject(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getDoctorsSchedule(Specialty specialty) {
        EmployeeDBHandler employeeDBHandler = new EmployeeDBHandler();
        ResultSet resSet = employeeDBHandler.findRecordByID(specialty.getId());
        if(resSet != null){
            try {
                respond.writeObject(true);
                try {
                    Employee.employeesList.clear();
                    while (resSet.next())
                    {
                        Employee employee = new Employee();
                        employee.setSurname(resSet.getString(DBConst.EMPLOYEE_SURNAME));
                        employee.setName(resSet.getString(DBConst.EMPLOYEE_NAME));
                        employee.setPatronymic(resSet.getString(DBConst.EMPLOYEE_PATRONYMIC));
                        employee.setOffice(resSet.getString(DBConst.EMPLOYEE_OFFICE));
                        employee.setWorkTime(resSet.getString(DBConst.EMPLOYEE_WORK_TIME));
                        Employee.employeesList.add(employee);
                    }
                    respond.writeObject( Employee.employeesList);

                }
                catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                respond.writeObject(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeLogin(Employee employeeLP) {
        EmployeeDBHandler employeeDBHandler = new EmployeeDBHandler();
        ResultSet resSet = employeeDBHandler.findRecordByLogin(Employee.mainEmployee.getLogin());
        if(resSet != null){
            try {
                respond.writeObject(true);
                try {
                    Employee.employeesList.clear();
                    while (resSet.next())
                    {
                        Employee employee = new Employee();
                        employee.setPassword(resSet.getString(DBConst.EMPLOYEE_PASSWORD));
                        employeeLP.setId(resSet.getInt(DBConst.EMPLOYEE_ID));
                        Employee.employeesList.add(employee);
                    }
                    System.out.println(Employee.employeesList.size());
                    if(Employee.employeesList.size() == 1)
                    {
                        respond.writeObject(true);

                        if(Employee.employeesList.get(0).getPassword().equals(employeeLP.getPassword())){
                            respond.writeObject(true);
                            employeeDBHandler.editLoginRecord(employeeLP);
                        }
                        else respond.writeObject(false);
                    }
                    else respond.writeObject(false);
                }
                catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                respond.writeObject(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changePassword(Employee employeeLP) {
        EmployeeDBHandler employeeDBHandler = new EmployeeDBHandler();
        ResultSet resSet = employeeDBHandler.findRecordByLogin(Employee.mainEmployee.getLogin());
        if(resSet != null){
            try {
                respond.writeObject(true);
                try {
                    Employee.employeesList.clear();
                    while (resSet.next())
                    {
                        Employee employee = new Employee();
                        employee.setPassword(resSet.getString(DBConst.EMPLOYEE_PASSWORD));
                        employeeLP.setId(resSet.getInt(DBConst.EMPLOYEE_ID));
                        Employee.employeesList.add(employee);
                    }
                    if(Employee.employeesList.size() == 1)
                    {
                        respond.writeObject(true);

                        if(Employee.employeesList.get(0).getPassword().equals(Employee.mainEmployee.getPassword())){
                            respond.writeObject(true);
                            employeeDBHandler.editPasswordRecord(employeeLP);
                        }
                        else respond.writeObject(false);
                    }
                    else respond.writeObject(false);
                }
                catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                respond.writeObject(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
