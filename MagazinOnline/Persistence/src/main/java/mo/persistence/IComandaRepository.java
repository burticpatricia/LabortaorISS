package mo.persistence;

import mo.model.Client;
import mo.model.Comanda;

import java.util.List;

public interface IComandaRepository extends ICrudRepository<Long, Comanda>{
    List<Comanda> getClientOrders(Client client);

}
