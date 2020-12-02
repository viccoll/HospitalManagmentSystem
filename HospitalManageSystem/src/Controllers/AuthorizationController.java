package Controllers;

import Configs.FXMLConfigs;
import Models.Employee;
import Models.PersonalAccount;
import ServerHandlers.ClientHandler;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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
            PersonalAccount personalAccount = new PersonalAccount();
            personalAccount.setLogin(loginField.getText().trim());
            personalAccount.setPassword(passwordField.getText().trim());
            ClientHandler clientHandler = ClientHandler.getClient();
            clientHandler.sendMessage("authorization");
            clientHandler.sendObject(personalAccount);

            if((boolean)clientHandler.readObject()) {
                Employee employee = new Employee((Employee) clientHandler.readObject());

                switch (employee.getId()){
                    case 1 :{
                        authorizationButton.getScene().getWindow().hide();
                        changeScene("../Views/Reg/regAccount.fxml");
                    }
                    case 2:{
                        authorizationButton.getScene().getWindow().hide();
                        changeScene("../Views/Admin/administratorAccount.fxml");
                    }
                    case 3:{
                        authorizationButton.getScene().getWindow().hide();
                        changeScene("../Views/CareWorker/careWorkerAccount.fxml");
                    }                }

                System.out.println("Пользовтель успешно авторизовался");
            }
            else System.out.println("Пользовтель не авторизовался");

        });
    }
    private void changeScene(String fxmlPath) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fxmlPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = new Stage();
        assert root != null;
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
