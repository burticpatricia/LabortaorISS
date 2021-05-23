package mo.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Client extends Entity implements Serializable {
    private String numeComplet;
    private String numeUtilizator;
    private String parola;
    private Set<Produs> cosCumparaturi;
    private Set<Comanda> comenzi;

    public Client(String numeComplet, String numeUtilizator, String parola) {
        this.numeComplet = numeComplet;
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
        cosCumparaturi = new HashSet<>();
        comenzi = new HashSet<>();
    }

    public Client(String username, String password) {
        this.numeUtilizator = username;
        this.parola = password;
    }

    public Set<Comanda> getComenzi() {
        return comenzi;
    }

    public void setComenzi(Set<Comanda> comenzi) {
        this.comenzi = comenzi;
    }

    public Client(){}

    public String getNumeComplet() {
        return numeComplet;
    }

    public void setNumeComplet(String numeComplet) {
        this.numeComplet = numeComplet;
    }

    public String getNumeUtilizator() {
        return numeUtilizator;
    }

    public void setNumeUtilizator(String numeUtilizator) {
        this.numeUtilizator = numeUtilizator;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(getNumeComplet(), client.getNumeComplet()) &&
                Objects.equals(getNumeUtilizator(), client.getNumeUtilizator()) &&
                Objects.equals(getParola(), client.getParola());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumeComplet(), getNumeUtilizator(), getParola());
    }

    @Override
    public String toString() {
        return "mo.model.Client{" +
                "numeComplet='" + numeComplet + '\'' +
                ", numeUtilizator='" + numeUtilizator + '\'' +
                ", parola='" + parola + '\'' + " id = "+this.getId()+
                '}';
    }

    public Set<Produs> getCosCumparaturi() {
        return cosCumparaturi;
    }

    public void setCosCumparaturi(Set<Produs> cosCumparaturi) {
        this.cosCumparaturi = cosCumparaturi;
    }
}
