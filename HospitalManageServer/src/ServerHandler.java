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

            while (true) {

                PersonalAccount personalAccount = (PersonalAccount) request.readObject();
                boolean isAuthorize = personalAccount.authorize();
                respond.writeObject(isAuthorize);

                if (isAuthorize) {
                    Employee employee = new Employee();

                    boolean isEmployeeFound = employee.findEmployee(personalAccount.getId());
                    respond.writeObject(isEmployeeFound);

                    if (isEmployeeFound) respond.writeObject(employee);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
