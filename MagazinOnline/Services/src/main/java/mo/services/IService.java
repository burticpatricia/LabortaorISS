package mo.services;

import mo.model.Client;
import mo.model.Produs;

import java.util.List;

public interface IService {
    void login(Client user, IObserver observer) throws Exception;
    void logout(Client user, IObserver observer) throws Exception;
    List<Produs> findAllProducts() throws Exception;
}
