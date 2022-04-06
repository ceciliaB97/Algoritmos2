package Dominio;

public class Conexion {
    private String codigoAeropuertoOrigen;
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
}
