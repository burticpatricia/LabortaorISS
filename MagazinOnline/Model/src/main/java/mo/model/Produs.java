package mo.model;

import java.io.Serializable;
import java.util.Objects;

public class Produs extends Entity implements Serializable {
    private String denumire;
    private String producator;
    private Double pret;
    private Integer stoc;

    public Produs(String denumire, String producator, Double pret, Integer stoc) {
        this.denumire = denumire;
        this.producator = producator;
        this.pret = pret;
        this.stoc = stoc;
    }

    public Produs() {
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public Integer getStoc() {
        return stoc;
    }

    public void setStoc(Integer stoc) {
        this.stoc = stoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produs)) return false;
        Produs produs = (Produs) o;
        return Objects.equals(getDenumire(), produs.getDenumire()) &&
                Objects.equals(getProducator(), produs.getProducator()) &&
                Objects.equals(getPret(), produs.getPret()) &&
                Objects.equals(getStoc(), produs.getStoc());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDenumire(), getProducator(), getPret(), getStoc());
    }

    @Override
    public String toString() {
        return "mo.model.Produs{" +
                "denumire='" + denumire + '\'' +
                ", producator='" + producator + '\'' +
                ", pret=" + pret +
                ", stoc=" + stoc +
                '}';
    }
}
