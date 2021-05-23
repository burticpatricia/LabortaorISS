package mo.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private ProductsController productsController;
    Stage loginStage;
    Parent root;

    public LoginController() throws RemoteException {
    }

    @Override
    public void initialize() {

    }

    public void setContext(IService service, Stage loginStage, ProductsController productsController, Parent root){
        this.service = service;
        this.loginStage = loginStage;
        this.productsController = productsController;
        this.root = root;
    }

    public void handleLogin(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        try{
            Client user = new Client(username,password);
            System.out.println("handleLogin()...before service.login()");
            user = service.login(user,productsController);
            System.out.println("handleLogin()...after service.login()");
            loginStage.close();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/mainPageView.fxml"));
            Parent mroot = loader.load();
            MainPageController ctrl = loader.getController();
            ctrl.setConnectedUser(user);
            ctrl.setContext(service,primaryStage);
            ctrl.setProductsController(productsController,root);
            Scene scene = new Scene(mroot);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Magazin Online");
            primaryStage.show();
        } catch (Exception e) {
            MessageBox.showErrorMessage(null,e.getMessage());
        }
    }
}
