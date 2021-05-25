package mo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Comanda extends Entity implements Serializable {
    private LocalDate data;
    private boolean livrata;
    private String oras;
    private String judet;
    private String strada;
    private String codPostal;
    private Set<Produs> produse;
    private Client client;

    public Comanda(boolean livrata, String oras, String judet, String strada, String codPostal, Set<Produs> produse, Client client) {
        this.data = LocalDate.now();
        this.livrata = livrata;
        this.oras = oras;
        this.judet = judet;
        this.strada = strada;
        this.codPostal = codPostal;
        this.produse = produse;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public Set<Produs> getProduse() {
        return produse;
    }

    public void setProduse(Set<Produs> produse) {
        this.produse = produse;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Comanda() {
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isLivrata() {
        return livrata;
    }

    public void setLivrata(boolean livrata) {
        this.livrata = livrata;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
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
        if (!(o instanceof Comanda)) return false;
        Comanda comanda = (Comanda) o;
        return isLivrata() == comanda.isLivrata() &&
                Objects.equals(getData(), comanda.getData()) &&
                Objects.equals(getOras(), comanda.getOras()) &&
                Objects.equals(getJudet(), comanda.getJudet()) &&
                Objects.equals(getStrada(), comanda.getStrada()) &&
                Objects.equals(getCodPostal(), comanda.getCodPostal()) &&
                Objects.equals(getProduse(), comanda.getProduse()) &&
                Objects.equals(getClient(), comanda.getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData(), isLivrata(), getOras(), getJudet(), getStrada(), getCodPostal(), getProduse(), getClient());
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "data=" + data +
                ", livrata=" + livrata +
                ", oras='" + oras + '\'' +
                ", judet='" + judet + '\'' +
                ", strada='" + strada + '\'' +
                ", codPostal='" + codPostal + '\'' +
                ", produse=" + produse +
                ", client=" + client +
                ", id = " + this.getId()+
                "} ";
    }
}
