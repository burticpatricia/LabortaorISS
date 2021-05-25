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

public class MainPageController extends UnicastRemoteObject implements Controller, Serializable {
    @FXML
    private Label completeName;
    IService service;
    @FXML
    Stage stage;
    Client connectedUser;
    private ProductsController productsController;
    private Parent root;
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

    public void setProductsController(ProductsController productsController, Parent root){
        this.productsController = productsController;
        this.root = root;
    }

    public void allProducts(ActionEvent actionEvent) throws IOException {
        productsController.setContext(service,stage,connectedUser);
        productsController.setRoot(root);
//        Scene scene = new Scene(root);
        Scene scene = stage.getScene();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Toate Produsele");
    }

    public void myBasket(ActionEvent actionEvent) throws IOException {
        FXMLLoader cloader = new FXMLLoader();
        cloader.setLocation(getClass().getResource("/basketView.fxml"));
        Parent root = cloader.load();
        BasketController ctrl = cloader.getController();

        ctrl.setContext(service,productsController,this.root,connectedUser, stage);
        Scene scene = stage.getScene();
        scene.setRoot(root);
        stage.setScene(scene);
    }

    public void myOrders(ActionEvent actionEvent) throws IOException {
        FXMLLoader cloader = new FXMLLoader();
        cloader.setLocation(getClass().getResource("/ordersView.fxml"));
        Parent root = cloader.load();
        OrdersController ctrl = cloader.getController();

        ctrl.setContext(productsController,this.root,service,stage,connectedUser);
        Scene scene = stage.getScene();
        scene.setRoot(root);
        stage.setScene(scene);
    }
}
