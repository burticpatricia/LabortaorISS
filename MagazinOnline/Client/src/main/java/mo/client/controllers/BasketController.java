package mo.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mo.client.utils.MessageBox;
import mo.model.Client;
import mo.model.Comanda;
import mo.model.Produs;
import mo.services.IService;

import java.io.IOException;

public class BasketController implements Controller {
    public TextField judet;
    public TextField oras;
    public TextField strada;
    public TextField codPostal;
    public TableView<Produs> productsTable;
    public Label pretTotal;
    private Client connectedUser;
    private ProductsController productsController;
    private Parent root;
    private IService service;
    private Stage stage;

    @Override
    public void initialize() {


    }

    public void setContext(IService service, ProductsController productsController, Parent root, Client connectedUser, Stage stage){
        this.service = service;
        this.productsController = productsController;
        this.root = root;
        this.connectedUser = connectedUser;
        this.stage = stage;
        initModel();
    }
    
    public void initModel(){
        try {
            productsTable.getItems().clear();
            var list = service.getUserBasket(connectedUser);
            productsTable.getItems().addAll(list);
            int pret = 0;
            for(Produs p: list){
                pret+=p.getPret();
            }
            pretTotal.setText(pret+" lei");
        } catch (Exception e) {
            MessageBox.showErrorMessage(null,"Eroare la initModel() "+ e.getMessage());
        }
    }

    public void placeOrder(ActionEvent actionEvent) {
        try{
            if(oras.getText().equals("") || judet.getText().equals("") || strada.getText().equals("")|| codPostal.getText().equals(""))
                throw new Exception("Va rugam sa completati toate detaliile de livrare.");
            Comanda comanda =  new Comanda(false,oras.getText(),judet.getText(),strada.getText(), codPostal.getText(), connectedUser.getCosCumparaturi(),connectedUser);
            service.placeOrder(comanda);
            MessageBox.showMessage(null, Alert.AlertType.INFORMATION,"Succes","Comanda plasata cu succes!");
            initModel();
            oras.setText("");
            judet.setText("");
            strada.setText("");
            codPostal.setText("");
        } catch (Exception e) {
            MessageBox.showErrorMessage(null,e.getMessage());
            initModel();
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
}
