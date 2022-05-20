package uy.edu.ort.aed2.obligatorio;

import Dominio.*;

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
        Retorno ret = new Retorno(Retorno.Resultado.OK);


        return ret;
    }

    public Pasajero buscarPasajeroRec(String cedula) {
        return new Pasajero("", "", "", "");
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
        pasajeros.listarPasajerosPorCategoria();
        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {
        if (aeropuertos.estaLleno()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        if (codigo.isEmpty() || nombre.isEmpty()) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
        if (aeropuertos.existeAeropuerto(codigo)) {
            return new Retorno(Retorno.Resultado.ERROR_3);
        }
        aeropuertos.agregarAeropuerto(new Aeropuerto(codigo, nombre));
        return new Retorno(Retorno.Resultado.OK);
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
        if (combustible < 0 || combustible < 0 || costoEnDolares < 0) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            if (codigoAeropuertoDestino.isEmpty() || codigoAeropuertoDestino.isBlank() || codigoAeropuertoOrigen.isEmpty() || codigoAeropuertoOrigen.isBlank()) {
                return new Retorno(Retorno.Resultado.ERROR_2);
            } else {
                if (!aeropuertos.existeAeropuerto(codigoAeropuertoOrigen)) {
                    return new Retorno(Retorno.Resultado.ERROR_3);
                } else {
                    if (!aeropuertos.existeAeropuerto(codigoAeropuertoDestino)) {
                        return new Retorno(Retorno.Resultado.ERROR_4);
                    } else {
                        if (!aeropuertos.existeConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino)) {
                            return new Retorno(Retorno.Resultado.ERROR_5);
                        } else {
                            Vuelo vueloCodigoEcontrado = vuelos.obtenerVueloCodigo(codigoDeVuelo);
                            if (vueloCodigoEcontrado == null) {
                                return new Retorno(Retorno.Resultado.ERROR_5);
                            } else {
                                Vuelo vueloCodigoConexionEncontrado = vuelos.obtenerVueloCodigoConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoDeVuelo);
                                if (vueloCodigoConexionEncontrado == null) {
                                    return new Retorno(Retorno.Resultado.ERROR_6);
                                } else {
                                    vueloCodigoConexionEncontrado.setCombustible(combustible);
                                    vueloCodigoConexionEncontrado.setCostoEnDolares(costoEnDolares);
                                    vueloCodigoConexionEncontrado.setMinutos(minutos);
                                    return new Retorno(Retorno.Resultado.OK);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoDeOrigen, int cantidad) {
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        if(codigoAeropuertoDestino.isBlank() || codigoAeropuertoDestino.isEmpty()){
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        if(codigoAeropuertoOrigen.isBlank() || codigoAeropuertoOrigen.isEmpty()){
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        boolean existeCamino = aeropuertos.existeCaminoAeropuetos(codigoAeropuertoOrigen, codigoAeropuertoDestino);

        if(existeCamino){
            String[] vectorRetorno = aeropuertos.viajeCostoMinimoKilometros(codigoAeropuertoOrigen, codigoAeropuertoDestino);
            Retorno ret = new Retorno(Retorno.Resultado.OK);
            ret.valorEntero = Integer.parseInt(vectorRetorno[0]);
            ret.valorString = vectorRetorno[1];
        }

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
