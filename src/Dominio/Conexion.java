package Dominio;

/**
 * ARISTA
 */
public class Conexion /*implements Comparable<Conexion>*/ {
    private boolean existe;
    private double kilometros;
    private LSEVuelo listaVuelos;
    private String codigoAeropuertoOrigen;
    private String codigoAeropuertoDestino;

    public Conexion(String origen, String destino, double kilometros) {
        this.existe = true;
        this.kilometros = kilometros;
        this.listaVuelos = new LSEVuelo();
        this.codigoAeropuertoOrigen = origen;
        this.codigoAeropuertoDestino = destino;
    }

    public String getCodigoAeropuertoOrigen() {
        return codigoAeropuertoOrigen;
    }

    public void setCodigoAeropuertoOrigen(String codigoAeropuertoOrigen) {
        this.codigoAeropuertoOrigen = codigoAeropuertoOrigen;
    }

    public String getCodigoAeropuertoDestino() {
        return codigoAeropuertoDestino;
    }

    public void setCodigoAeropuertoDestino(String codigoAeropuertoDestino) {
        this.codigoAeropuertoDestino = codigoAeropuertoDestino;
    }

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

    public LSEVuelo getListaVuelos() {
        return listaVuelos;
    }

    public double getCostoVueloMasBarato() {
        return listaVuelos.getCostoVueloMasBarato();
    }
}
