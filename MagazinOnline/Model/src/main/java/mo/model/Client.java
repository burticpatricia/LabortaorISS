package mo.model;

import java.io.Serializable;
import java.util.Objects;

public class Client extends Entity implements Serializable {
    private String numeComplet;
    private String numeUtilizator;
    private String parola;

    public Client(String numeComplet, String numeUtilizator, String parola) {
        this.numeComplet = numeComplet;
        this.numeUtilizator = numeUtilizator;
        this.parola = parola;
    }

    public Client(String username, String password) {
        this.numeUtilizator = username;
        this.parola = password;
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
                ", parola='" + parola + '\'' +
                '}';
    }
}
