package mo.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mo.client.utils.MessageBox;
import mo.model.Client;
import mo.services.IService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class LoginController extends UnicastRemoteObject implements Controller{
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    IService service;
    private MainPageController mainPageController;
    Stage loginStage;
    Parent root;

    public LoginController() throws RemoteException {
    }

    @Override
    public void initialize() {

    }

    public void setContext(IService service, Stage loginStage, MainPageController mainPageController, Parent root){
        this.service = service;
        this.loginStage = loginStage;
        this.mainPageController = mainPageController;
        this.root = root;
    }

    public void handleLogin(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try{
            Client user = new Client(username,password);
            System.out.println("handleLogin()...before service.login()");
            service.login(user,mainPageController);
            System.out.println("handleLogin()...after service.login()");
            loginStage.close();
            Stage primaryStage = new Stage();
            mainPageController.setConnectedUser(user);
            mainPageController.setContext(service,primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Magazin Online");
            primaryStage.show();
        } catch (Exception e) {
            MessageBox.showErrorMessage(null,e.getMessage());
        }
    }
}
