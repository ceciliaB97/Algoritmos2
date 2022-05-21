package uy.edu.ort.aed2.obligatorio;

import Dominio.*;

import static uy.edu.ort.aed2.obligatorio.Sistema.Clase.*;

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
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        int inicioJ, topeJ, j = 0, cont = 0;

        if (cantidad <= 0) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        switch (unaClase) {
            case Primera:
                inicioJ = 0;
                topeJ = 2;
                recorrerMatriz(matriz, 0, j, inicioJ, topeJ, cont);
                break;
            case Ejecutiva:
                inicioJ = 3;
                topeJ = 6;
                recorrerMatriz(matriz, 0, j, inicioJ, topeJ, cont);
                break;
            case Turista:
                inicioJ = 7;
                topeJ = matriz[0].length - 1;
                recorrerMatriz(matriz, 0, j, inicioJ, topeJ, cont);
                break;
            default:
                return new Retorno(Retorno.Resultado.ERROR_2);
        }

        return retorno;
    }

    private void recorrerMatriz(int[][] m, int i, int j, int inicioJ, int topeJ, int cont) {
        if (j >= inicioJ) {
            System.out.print(m[i][j] + " ");
        }
        if (i != m.length - 1 || j != m[i].length - 1) {
            if (j == topeJ) {
                i++;
                j = 0;
                cont++;
                System.out.println();
            } else {
                j++;
            }
            if (cont < m.length) {
                recorrerMatriz(m, i, j, inicioJ, topeJ, cont);
            }
        }
    }
}

