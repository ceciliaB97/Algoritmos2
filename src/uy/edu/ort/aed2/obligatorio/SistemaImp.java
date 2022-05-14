package uy.edu.ort.aed2.obligatorio;

import Dominio.AbbPasajero;
import Dominio.GrfAeropuerto;
import Dominio.LSEVuelo;

public class SistemaImp implements Sistema {

    AbbPasajero pasajeros;
    LSEVuelo vuelos;
    GrfAeropuerto aeropuertos;

    @Override
    public Retorno inicializarSistema(int maxAeropuertos) {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        pasajeros = new AbbPasajero();
        vuelos = new LSEVuelo();
        aeropuertos = new GrfAeropuerto(maxAeropuertos);
        return retorno;
    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno buscarPasajero(String cedula) {
        Retorno rec = new Retorno(Retorno.Resultado.OK);


        return rec;
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listarPasajerosDescendente() {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listarPasajerosPorCategoría(Categoria categoria) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno actualizarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoDeOrigen, int cantidad) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno viajeCostoMinimoDolares(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno consultaDisponibilidad(int[][] matriz, int cantidad, Clase unaClase) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

}
