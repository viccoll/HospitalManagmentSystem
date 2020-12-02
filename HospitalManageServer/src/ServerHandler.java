import DBHandlers.EmployeeDBHandler;
import DBHandlers.SpecialtyDBHandler;
import Models.Address;
import Models.Employee;
import Models.PersonalAccount;

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
            String action = request.readObject().toString();
            System.out.println(action);
            PersonalAccount personalAccount = (PersonalAccount)request.readObject();
            respond.writeObject(personalAccount.authorize());
            System.out.println("id"+personalAccount.getId());
            Employee employee = new Employee();
            if(employee.findEmployee(personalAccount.getId())){
                respond.writeObject(employee);
                System.out.println(employee.toString());
            }
            else System.out.println("Ничего не нашли");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




}
