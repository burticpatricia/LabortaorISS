package mo.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mo.client.utils.MessageBox;
import mo.model.Client;
import mo.model.Comanda;
import mo.model.Produs;
import mo.services.IService;

import java.io.IOException;

public class OrdersController implements Controller {
    public TextField adresa;
    public TextArea produse;
    @FXML
    private TableView<Comanda> ordersTable;
    Stage stage;
    ProductsController productsController;
    Parent root;
    IService service;
    Client connectedUser;


    @Override
    public void initialize() {
        ordersTable.setRowFactory(tv->{
            TableRow<Comanda> row = new TableRow<>();
            row.setOnMouseClicked(event->{
                if(event.getClickCount() >= 1 && (! row.isEmpty())){
                    Comanda comanda = row.getItem();
                    adresa.setText(comanda.getJudet()+", "+comanda.getOras()+", "+comanda.getStrada()+", "+comanda.getCodPostal());
                    String prodString = "";
                    for (Produs x : comanda.getProduse()) {
                        prodString += x.getDenumire() + '\n';
                    }
                    produse.setText(prodString);
                }
            });
            return row;
        });

    }

    public void setContext(ProductsController productsController, Parent root, IService service, Stage stage, Client connectedUser){
        this.productsController = productsController;
        this.root = root;
        this.service = service;
        this.stage = stage;
        this.connectedUser = connectedUser;
        initModel();
    }

    private void initModel() {
        try {
            ordersTable.getItems().clear();
            ordersTable.getItems().addAll(service.getUserOrders(connectedUser));
        } catch (Exception e) {
            MessageBox.showErrorMessage(null,"Eroare la initModel() "+ e.getMessage());
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader cloader = new FXMLLoader();
        cloader.setLocation(getClass().getResource("/mainPageView.fxml"));
        Parent root = cloader.load();
        MainPageController ctrl = cloader.getController();

        ctrl.setConnectedUser(connectedUser);
        ctrl.setContext(service,stage);
        ctrl.setProductsController(productsController,this.root);
        Scene scene = stage.getScene();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Magazin Online");
    }

    public void cancelOrder(ActionEvent actionEvent) {
        Comanda comanda = ordersTable.getSelectionModel().getSelectedItem();
        try {
            if(comanda == null)
                throw new Exception("Selectati o comanda!\n");
            service.cancelOrder(comanda);
            initModel();
            adresa.setText("");
            produse.setText("");
        } catch (Exception e) {
            MessageBox.showErrorMessage(null,e.getMessage());
        }
    }
}
