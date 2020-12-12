package Controllers;
import Configs.AlertScene;
import Configs.ChangeScene;
import Models.Employee;
import ServerHandlers.ClientHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class AuthorizationController {

    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;
    @FXML
    private Button authorizationButton;

    @FXML
    public void initialize(){

        authorizationButton.setOnAction(event -> {

            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();

            Employee.mainEmployee.setLogin(login);
            Employee.mainEmployee.setPassword(password);

            ClientHandler clientHandler = ClientHandler.getClient();

            clientHandler.sendObject(login);
            clientHandler.sendObject(password);
            /*Авторизовался или нет - принимает ответ с сервера*/
            boolean isAuthorize = (boolean)clientHandler.readObject();

                if(isAuthorize) {
                        Employee.mainEmployee = (Employee) clientHandler.readObject();
                        switch (Employee.mainEmployee.getIdSpecialty()){
                            case 1 :{
                                authorizationButton.getScene().getWindow().hide();
                                ChangeScene.change("../Views/Reg/regAccount.fxml",getClass());
                                break;
                            }
                            case 2:{
                                authorizationButton.getScene().getWindow().hide();
                                ChangeScene.change("../Views/Admin/administratorAccount.fxml",getClass());
                                break;
                            }
                            default: {
                                authorizationButton.getScene().getWindow().hide();
                                ChangeScene.change("../Views/CareWorker/careWorkerAccount.fxml",getClass());
                                break;
                            }
                        }
                    }
                    else AlertScene.callAlert("Не удалось авторизоваться");
        });
    }
}
