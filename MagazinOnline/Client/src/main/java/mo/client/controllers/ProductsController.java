package mo.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import mo.client.utils.MessageBox;
import mo.model.Client;
import mo.model.Produs;
import mo.services.IObserver;
import mo.services.IService;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ProductsController extends UnicastRemoteObject implements Controller, IObserver, Serializable {
    public TableView<Produs> productsTable;
    IService service;
    @FXML
    Stage stage;
    Client connectedUser;
    private Parent root;

    public ProductsController() throws RemoteException {
    }

    @Override
    public void initialize() {

    }

    public void setContext(IService service, Stage stage, Client connectedUser) {
        this.service = service;
        this.stage = stage;
        this.connectedUser = connectedUser;
        initModel();
    }

    public void setRoot(Parent root){
        this.root = root;
    }

    private void initModel() {
        try {
            productsTable.getItems().clear();
            productsTable.getItems().addAll(service.findAllProducts());
        } catch (Exception e) {
            MessageBox.showErrorMessage(null,"Eroare la initModel() "+ e.getMessage());
        }
    }

    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader cloader = new FXMLLoader();
        cloader.setLocation(getClass().getResource("/mainPageView.fxml"));
        Parent root = cloader.load();
        MainPageController ctrl = cloader.getController();

        ctrl.setConnectedUser(connectedUser);
        ctrl.setContext(service,stage);
        ctrl.setProductsController(this,this.root);
        Scene scene = stage.getScene();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Magazin Online");
    }

    public void addToBasket(ActionEvent actionEvent) {
        try{
            Produs produs = productsTable.getSelectionModel().getSelectedItem();
            System.out.println(produs);
            if(produs == null)
                throw new Exception("Selecteaza un produs!\n");
            service.addProductInBasket(connectedUser,produs);
            MessageBox.showMessage(null, Alert.AlertType.INFORMATION,"Succes","Produsul a fost adaugat in cosul de cumparaturi");

        } catch (Exception e) {
            MessageBox.showErrorMessage(null,e.getMessage());
        }

    }

    @Override
    public void update() throws Exception {
        initModel();
    }
}

