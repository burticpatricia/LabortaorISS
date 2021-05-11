package mo.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import mo.model.Client;
import mo.services.IObserver;
import mo.services.IService;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MainPageController extends UnicastRemoteObject implements Controller, IObserver, Serializable {
    @FXML
    private Label completeName;
    IService service;
    @FXML
    Stage stage;
    Client connectedUser;
    public MainPageController() throws RemoteException {
    }

    @Override
    public void initialize() {

    }

    public void setConnectedUser(Client user) {
        this.connectedUser = user;
    }

    public void setContext(IService service, Stage primaryStage) {
        this.service = service;
        this.stage = primaryStage;
        completeName.setText(connectedUser.getNumeUtilizator());
    }

    public void allProducts(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/productsView.fxml"));
        Parent root = loader.load();
        ProductsController productsController = loader.getController();

        productsController.setContext(service,stage,connectedUser);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Toate Produsele");
    }
}
