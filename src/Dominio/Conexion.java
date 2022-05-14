package Dominio;

import java.util.Objects;

/**
 * ARISTA
 */
public class Conexion /*implements Comparable<Conexion>*/ {
    private boolean existe;
    private double kilometros;

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public double getKilometros() {
        return kilometros;
    }

    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    public Conexion() {
        this.existe = false;
        this.kilometros = 0.0;

    }


   /* private String codigoAeropuertoOrigen;
    private String codigoAeropuertoDestino;
    private double kilometros;

    public Conexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
        this.kilometros = kilometros;
    }

    public String getCodigoAeropuertoOrigen() {
        return codigoAeropuertoOrigen;
    }

    public String getCodigoAeropuertoDestino() {
        return codigoAeropuertoDestino;
    }

    public double getKilometros() {
        return kilometros;
    }

    @Override
    public int compareTo(Conexion o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conexion conexion = (Conexion) o;
        return Double.compare(conexion.kilometros, kilometros) == 0 && Objects.equals(codigoAeropuertoOrigen, conexion.codigoAeropuertoOrigen) && Objects.equals(codigoAeropuertoDestino, conexion.codigoAeropuertoDestino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoAeropuertoOrigen, codigoAeropuertoDestino, kilometros);
    }*/
}
