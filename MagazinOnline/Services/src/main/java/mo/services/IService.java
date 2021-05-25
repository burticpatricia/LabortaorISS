package mo.services;

import mo.model.Client;
import mo.model.Comanda;
import mo.model.Produs;

import java.util.List;

public interface IService {
    Client login(Client user, IObserver observer) throws Exception;
    List<Produs> findAllProducts() throws Exception;
    void addProductInBasket(Client user, Produs product) throws Exception;
    void placeOrder(Comanda order) throws Exception;
    List<Produs> getUserBasket(Client client);
    List<Comanda> getUserOrders(Client client);
    void cancelOrder(Comanda order) throws Exception;
}
