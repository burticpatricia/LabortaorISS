package mo.server;

import mo.model.Client;
import mo.model.Produs;
import mo.persistence.IClientRepository;
import mo.persistence.IProdusRepository;
import mo.services.IObserver;
import mo.services.IService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Service implements IService {
    private IClientRepository clientRepository;
    private IProdusRepository produsRepository;
    private Map<String, IObserver> loggedUsers;

    private static final int NUMBER_OF_THREADS = 5;

    public Service(IClientRepository clientRepository, IProdusRepository produsRepository) {
        this.clientRepository = clientRepository;
        this.produsRepository = produsRepository;
        loggedUsers = new ConcurrentHashMap<>();
    }

    @Override
    public void login(Client user, IObserver observer) throws Exception {
        System.out.println("Service: enter login()...");
        Client userR = clientRepository.findByUsernameAndPassword(user.getNumeUtilizator(),user.getParola());
        System.out.println("Service: login(): userR obtained "+userR);
        if(userR == null) {
            System.out.println("USER IS NULL");
            throw new Exception("Parola sau nume de utilizator incorecte!\n");
        }
        if(loggedUsers.get(userR.getNumeUtilizator())!=null) {
            System.out.println("User deja logat");
            throw new Exception("User already logged in!\n");
        }
        loggedUsers.put(userR.getNumeUtilizator(), observer);

    }

    @Override
    public void logout(Client user, IObserver observer) throws Exception {
        IObserver localClient = loggedUsers.remove(user.getNumeUtilizator());
        if(localClient == null)
            throw new Exception("User "+user.getNumeUtilizator()+" is not logged in");

    }

    @Override
    public List<Produs> findAllProducts() throws Exception {
        return produsRepository.findAll();
    }
}
