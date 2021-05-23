package mo.services;

import java.rmi.Remote;

public interface IObserver extends Remote {
    void update() throws Exception;
}
