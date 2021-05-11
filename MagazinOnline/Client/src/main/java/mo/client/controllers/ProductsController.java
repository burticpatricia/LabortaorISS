package mo.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import mo.client.utils.MessageBox;
import mo.model.Client;
import mo.model.Produs;
import mo.services.IService;

import java.io.IOException;

public class ProductsController implements Controller{
    public TableView<Produs> productsTable;
    IService service;
    @FXML
    Stage stage;
    Client connectedUser;

    @Override
    public void initialize() {

    }

    public void setContext(IService service, Stage stage, Client connectedUser) {
        this.service = service;
        this.stage = stage;
        this.connectedUser = connectedUser;
        initModel();
    }

    private void initModel() {
        try {
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
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Magazin Online");
    }
}

