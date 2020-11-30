package Controllers;

import ServerHandlers.ClientHandler;
import javafx.fxml.FXML;

public class AuthorizationController {

    private ClientHandler client;

    @FXML
    public void initialize(){
        client = new ClientHandler("127.0.0.1", "3000");
    }


}
