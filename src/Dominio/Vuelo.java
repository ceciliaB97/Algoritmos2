package Dominio;

import java.util.Objects;

/**
 * Lista simplemente enlazada
 */
public class Vuelo implements Comparable<Vuelo> {
    private String codigoAeropuertoOrigen;
    private String codigoAeropuertoDestino;
    private String codigoVuelo;
    private double combustible;
    private double minutos;
    private double costoEnDolares;

    public Vuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoVuelo, double combustible, double minutos, double costoEnDolares) {
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
        this.codigoVuelo = codigoVuelo;
        this.combustible = combustible;
        this.minutos = minutos;
        this.costoEnDolares = costoEnDolares;
    }

    public String getCodigoAeropuertoOrigen() {
        return codigoAeropuertoOrigen;
    }

    public String getCodigoAeropuertoDestino() {
        return codigoAeropuertoDestino;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public double getCombustible() {
        return combustible;
    }

    public double getMinutos() {
        return minutos;
    }

    public double getCostoEnDolares() {
        return costoEnDolares;
    }

    @Override
    public int compareTo(Vuelo o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return Objects.equals(codigoAeropuertoOrigen, vuelo.codigoAeropuertoOrigen) && Objects.equals(codigoAeropuertoDestino, vuelo.codigoAeropuertoDestino) && Objects.equals(codigoVuelo, vuelo.codigoVuelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoVuelo);
    }
}
