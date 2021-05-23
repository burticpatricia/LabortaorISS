package mo.server;

import mo.model.Client;
import mo.model.Comanda;
import mo.model.Produs;
import mo.persistence.IClientRepository;
import mo.persistence.IComandaRepository;
import mo.persistence.IProdusRepository;
import mo.services.IObserver;
import mo.services.IService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Service implements IService {
    private IClientRepository clientRepository;
    private IProdusRepository produsRepository;
    private IComandaRepository comandaRepository;
    private Map<String, IObserver> loggedUsers;

    private static final int NUMBER_OF_THREADS = 5;

    public Service(IClientRepository clientRepository, IProdusRepository produsRepository, IComandaRepository comandaRepository) {
        this.clientRepository = clientRepository;
        this.produsRepository = produsRepository;
        this.comandaRepository = comandaRepository;
        loggedUsers = new ConcurrentHashMap<>();
    }

    @Override
    public Client login(Client user, IObserver observer) throws Exception {
        System.out.println("Service: enter login()...");
        Client userR = clientRepository.findByUsernameAndPassword(user.getNumeUtilizator(),user.getParola());
        System.out.println("Service: login(): userR obtained "+userR);
        if(userR == null) {
            System.out.println("USER IS NULL");
            throw new Exception("Parola sau nume de utilizator incorecte!\n");
        }
        if(loggedUsers.get(userR.getNumeUtilizator())!=null) {
            System.out.println("User deja logat");
            throw new Exception("Utiliozator deja logat!\n");
        }
        loggedUsers.put(userR.getNumeUtilizator(), observer);
        return userR;

    }

    @Override
    public void logout(Client user, IObserver observer) throws Exception {
        IObserver localClient = loggedUsers.remove(user.getNumeUtilizator());
        if(localClient == null)
            throw new Exception("Utilizatorul "+user.getNumeUtilizator()+" nu este logat!");

    }

    @Override
    public List<Produs> findAllProducts() throws Exception {
        return produsRepository.findAll();
    }

    @Override
    public void addProductInBasket(Client user, Produs product) throws Exception {
        if(clientRepository.findByUsernameAndPassword(user.getNumeUtilizator(), user.getParola()).getCosCumparaturi().contains(product))
            throw new Exception("Produs deja adaugat in cosul de cunparaturi!\n");
        if(product.getStoc()>0) {
            var list = user.getCosCumparaturi();
            list.add(product);
            user.setCosCumparaturi(list);
            clientRepository.update(user);
            product.setStoc(product.getStoc() - 1);
            produsRepository.update(product);
            notifyClients();
        }
        else
            throw new Exception("Produsul nu mai este in stoc!\n");
    }

    @Override
    public void placeOrder(Comanda order) throws Exception {
        System.out.println("Comanda ");
        order.getClient().getCosCumparaturi().forEach(System.out::println);
        if(order.getClient().getCosCumparaturi().size() == 0)
            throw new Exception("Nu aveti niciun produs adaugat in cos");
        else if(order.getProduse().stream().anyMatch(x->x.getStoc()==0)) {
            var produse = order.getProduse();
            var stocIndisponibil = produse.stream().filter(x->x.getStoc()==0).collect(Collectors.toList());
            stocIndisponibil.forEach(produse::remove);
            var client = order.getClient();
            client.setCosCumparaturi(produse);
            clientRepository.update(client);
            throw new Exception("Anumite produse nu mai sunt disponibile si au fost eliminate din cos automat! Reincarcati pagina!");
        }
        else{
            comandaRepository.add(order);
            var client = order.getClient();
            client.setCosCumparaturi(new HashSet<>());
            clientRepository.update(client);
        }
    }

    @Override
    public List<Produs> getUserBasket(Client client) {
        return new ArrayList<>(clientRepository.findByUsernameAndPassword(client.getNumeUtilizator(), client.getParola()).getCosCumparaturi());
    }

    private void notifyClients() {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        loggedUsers.forEach((id, client) -> {
            if (client != null) {
                System.out.println("Notifying client [" + id + "]");
                try {
                    client.update();
                } catch (Exception exception) {
                    System.err.println("Error at updating the client: " + exception.getMessage());
                }
            }
        });
        executorService.shutdown();
    }
}
