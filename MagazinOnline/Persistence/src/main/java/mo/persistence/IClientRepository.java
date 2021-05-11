package mo.persistence;

import mo.model.Client;

public interface IClientRepository extends ICrudRepository<Long, Client> {
    Client findByUsernameAndPassword(String username, String password);
}
