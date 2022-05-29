package uy.edu.ort.aed2.obligatorio;

import Dominio.*;

public class SistemaImp implements Sistema {

    AbbPasajero pasajeros;
    LSEVuelo vuelos;
    GrfAeropuerto aeropuertos;

    private int opciones;
    private String txtLugares;

    @Override
    public Retorno inicializarSistema(int maxAeropuertos) {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        pasajeros = new AbbPasajero(new ComparadorPasajerosPorCedula());
        vuelos = new LSEVuelo();
        aeropuertos = new GrfAeropuerto(maxAeropuertos);
        return retorno;
    }

    @Override
    public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);

        if (Pasajero.datosValidos(cedula, nombre, telefono, categoria)) {
            Pasajero nuevoPasajero = new Pasajero(cedula, nombre, telefono, categoria.getTexto());
            if (nuevoPasajero.validarCedula(cedula)) {
                if (!pasajeros.pertenece(nuevoPasajero)) {
                    pasajeros.insertar(nuevoPasajero);
                    retorno.valorString = nuevoPasajero.toString();
                } else {
                    retorno.resultado = Retorno.Resultado.ERROR_3;
                    retorno.valorString = nuevoPasajero.toString();
                }
            } else {
                retorno.resultado = Retorno.Resultado.ERROR_2;
                retorno.valorString = cedula + " Cedula incorrecta se espera el siguiente formato N.NNN.NNN-N o NNN.NNN-N";
            }
        } else {
            retorno.resultado = Retorno.Resultado.ERROR_1;
            retorno.valorString = "Cedula: " + cedula + " Nombre: " + nombre + " Telefono: " + telefono + " Categoria: " + categoria;
        }
        return retorno;
    }

    @Override
    public Retorno buscarPasajero(String cedula) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        if (!Pasajero.validarCedula(cedula)) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        Pasajero p = pasajeros.existeCedula(cedula);

        if (p == null) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
        ret.valorString = p.getCedula() + ";" + p.getNombre() + ";" + p.getTelefono() + ";" + p.getCategoria();
        ret.valorEntero = pasajeros.getVueltas();
        return ret;
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        Retorno resultado = new Retorno(Retorno.Resultado.OK);
        resultado.valorString = pasajeros.listarPasajerosAsc();
        return resultado;
    }

    @Override
    public Retorno listarPasajerosDescendente() {
        Retorno resultado = new Retorno(Retorno.Resultado.OK);
        resultado.valorString = pasajeros.listarPasajeroDesc();
        return resultado;
    }

    @Override
    public Retorno listarPasajerosPorCategoría(Categoria categoria) {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        retorno.valorString = pasajeros.listarPasajerosPorCategoria(categoria.getTexto());
        return retorno;
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {
        if (aeropuertos.estaLleno()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        if (nombre == null || codigo == null || codigo.isEmpty() || nombre.isEmpty()) {
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
        int retorno = aeropuertos.agregarConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino, kilometros);
        if (retorno == 1) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        if (retorno == 2) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
        if (retorno == 3) {
            return new Retorno(Retorno.Resultado.ERROR_3);
        }
        if (retorno == 4) {
            return new Retorno(Retorno.Resultado.ERROR_4);
        }
        if (retorno == 0) {
            return new Retorno(Retorno.Resultado.OK);
        }
        return new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares) {
        Retorno resultado = new Retorno(Retorno.Resultado.OK);
        if (combustible > 0 && minutos > 0 && costoEnDolares > 0) {
            boolean codigoAereopuertoOrgValido = codigoAeropuertoOrigen != null && !codigoAeropuertoOrigen.isBlank() && !codigoAeropuertoOrigen.isEmpty();
            boolean codigoAereopuertoDesValido = codigoAeropuertoDestino != null && !codigoAeropuertoDestino.isBlank() && !codigoAeropuertoDestino.isEmpty();
            boolean codigoDeVueloValido = codigoDeVuelo != null && !codigoDeVuelo.isBlank() && !codigoDeVuelo.isEmpty();
            if (codigoAereopuertoOrgValido && codigoAereopuertoDesValido && codigoDeVueloValido) {
                if (aeropuertos.existeAeropuerto(codigoAeropuertoOrigen)) {
                    if (aeropuertos.existeAeropuerto(codigoAeropuertoDestino)) {
                        if (aeropuertos.existeConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino)) {
                            Conexion conexionEncontrada = aeropuertos.obtenerConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino);
                            LSEVuelo vuelosDeConexion = conexionEncontrada.getListaVuelos();
                            Vuelo encontrado = vuelosDeConexion.obtenerVueloCodigo(codigoDeVuelo);
                            if (encontrado == null) {
                                Vuelo nuevoVuelo = new Vuelo(codigoAeropuertoOrigen, codigoAeropuertoDestino, codigoDeVuelo, combustible, minutos, costoEnDolares);
                                vuelosDeConexion.agregarAlPrincipio(nuevoVuelo);
                            } else {
                                resultado.resultado = Retorno.Resultado.ERROR_6;
                            }
                        } else {
                            resultado.resultado = Retorno.Resultado.ERROR_5;
                        }
                    } else {
                        resultado.resultado = Retorno.Resultado.ERROR_4;
                    }
                } else {
                    resultado.resultado = Retorno.Resultado.ERROR_3;
                }
            } else {
                resultado.resultado = Retorno.Resultado.ERROR_2;
            }
        } else {
            resultado.resultado = Retorno.Resultado.ERROR_1;
        }
        return resultado;
    }

    @Override
    public Retorno actualizarVuelo(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares) {
        Retorno resultado = new Retorno(Retorno.Resultado.OK);
        if (combustible > 0 && minutos > 0 && costoEnDolares > 0) {
            boolean codigoAereopuertoOrgValido = codigoAeropuertoOrigen != null && !codigoAeropuertoOrigen.isBlank() && !codigoAeropuertoOrigen.isEmpty();
            boolean codigoAereopuertoDesValido = codigoAeropuertoDestino != null && !codigoAeropuertoDestino.isBlank() && !codigoAeropuertoDestino.isEmpty();
            boolean codigoDeVueloValido = codigoDeVuelo != null && !codigoDeVuelo.isBlank() && !codigoDeVuelo.isEmpty();
            if (codigoAereopuertoOrgValido && codigoAereopuertoDesValido && codigoDeVueloValido) {
                if (aeropuertos.existeAeropuerto(codigoAeropuertoOrigen)) {
                    if (aeropuertos.existeAeropuerto(codigoAeropuertoDestino)) {
                        if (aeropuertos.existeConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino)) {
                            Conexion conexionEncontrada = aeropuertos.obtenerConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino);
                            LSEVuelo vuelosDeConexion = conexionEncontrada.getListaVuelos();
                            Vuelo encontrado = vuelosDeConexion.obtenerVueloCodigo(codigoDeVuelo);
                            if (encontrado != null) {
                                encontrado.setCostoEnDolares(costoEnDolares);
                                encontrado.setMinutos(minutos);
                                encontrado.setCombustible(combustible);
                            } else {
                                resultado.resultado = Retorno.Resultado.ERROR_6;
                            }
                        } else {
                            resultado.resultado = Retorno.Resultado.ERROR_5;
                        }
                    } else {
                        resultado.resultado = Retorno.Resultado.ERROR_4;
                    }
                } else {
                    resultado.resultado = Retorno.Resultado.ERROR_3;
                }
            } else {
                resultado.resultado = Retorno.Resultado.ERROR_2;
            }
        } else {
            resultado.resultado = Retorno.Resultado.ERROR_1;
        }
        return resultado;
    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoDeOrigen, int cantidad) {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        if (cantidad >= 0) {
            if (aeropuertos.existeAeropuerto(codigoAeropuertoDeOrigen)) {

            } else {
                retorno.resultado = Retorno.Resultado.ERROR_2;
            }
        } else {
            retorno.resultado = Retorno.Resultado.ERROR_1;
        }
        return retorno;
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        if (codigoAeropuertoDestino != null && codigoAeropuertoDestino.isBlank() || codigoAeropuertoDestino.isEmpty()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        if (codigoAeropuertoOrigen != null && codigoAeropuertoOrigen.isBlank() || codigoAeropuertoOrigen.isEmpty()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        boolean existeCamino = aeropuertos.existeCaminoAeropuetos(codigoAeropuertoOrigen, codigoAeropuertoDestino);

        if (existeCamino) {
            String[] vectorRetorno = aeropuertos.viajeCostoMinimoKilometros(codigoAeropuertoOrigen, codigoAeropuertoDestino);
            Retorno ret = new Retorno(Retorno.Resultado.OK);
            Float valorFloat = Float.parseFloat(vectorRetorno[0]);
            int valorInt = Math.round(valorFloat);
            ret.valorEntero = valorInt;
            ret.valorString = vectorRetorno[1];
            return ret;
        } else {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
    }

    @Override
    public Retorno viajeCostoMinimoDolares(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        if (codigoAeropuertoDestino.isBlank() || codigoAeropuertoDestino.isEmpty()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        if (codigoAeropuertoOrigen.isBlank() || codigoAeropuertoOrigen.isEmpty()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        boolean existeCamino = aeropuertos.existeCaminoAeropuetos(codigoAeropuertoOrigen, codigoAeropuertoDestino);

        if (existeCamino) {
            Conexion conexionEncontrada = aeropuertos.obtenerConexion(codigoAeropuertoOrigen, codigoAeropuertoDestino);
            LSEVuelo vuelosDeConexion = conexionEncontrada.getListaVuelos();
            if (!vuelosDeConexion.estaVacia()) {
                String[] vectorRetorno = aeropuertos.viajeCostoMinimoDolares(codigoAeropuertoOrigen, codigoAeropuertoDestino);
                Retorno ret = new Retorno(Retorno.Resultado.OK);
                Float valorFloat = Float.parseFloat(vectorRetorno[0]);
                int valorInt = Math.round(valorFloat);
                ret.valorEntero = valorInt;
                ret.valorString = vectorRetorno[1];
                return ret;

            } else {
                return new Retorno(Retorno.Resultado.ERROR_2);
            }
        } else {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
    }


    @Override
    public Retorno consultaDisponibilidad(int[][] matriz, int cantidad, Clase unaClase) {
        Retorno retorno = new Retorno(Retorno.Resultado.OK);
        int inicioI, topeI, j = 0, vueltas = 0;

        if (cantidad <= 0) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        int[][] matrizT = trasponerMatriz(matriz);

        switch (unaClase) {
            case Primera:
                this.opciones = 0;
                this.txtLugares = "";
                inicioI = 0;
                topeI = 2;
                cantidad = cantidad == 1 ? cantidad : cantidad + 1;
                recorrerMatriz(matrizT, inicioI, j, topeI, vueltas, cantidad, unaClase);
                retorno.valorEntero = this.opciones;
                retorno.valorString = !txtLugares.isEmpty() ? quitarPipeFinal(this.txtLugares) : "";
                break;
            case Ejecutiva:
                this.opciones = 0;
                this.txtLugares = "";
                inicioI = 3;
                topeI = 6;
                recorrerMatriz(matrizT, inicioI, j, topeI, vueltas, cantidad, unaClase);
                retorno.valorEntero = this.opciones;
                retorno.valorString = !txtLugares.isEmpty() ? quitarPipeFinal(this.txtLugares) : "";
                break;
            case Turista:
                this.opciones = 0;
                this.txtLugares = "";
                inicioI = 7;
                topeI = matriz[0].length;
                recorrerMatriz(matrizT, inicioI, j, topeI, vueltas, cantidad, unaClase);
                retorno.valorEntero = opciones;
                retorno.valorString = !txtLugares.isEmpty() ? quitarPipeFinal(this.txtLugares) : "";
                break;
            default:
                return new Retorno(Retorno.Resultado.ERROR_2);
        }
        return retorno;
    }

    private String posicionLetra(int pos) {
        switch (pos) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
        }
        return "";
    }

    private boolean asientoLibre(int asiento) {
        return asiento == 0;
    }

    //traspongo la matriz para facilitar la recorrida
    private int[][] trasponerMatriz(int[][] matriz) {
        int[][] matrizT = new int[matriz[0].length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizT[j][i] = matriz[i][j];
            }
        }
        return matrizT;
    }

    private void recorrerMatriz(int[][] m, int i, int j, int topeI, int vueltas, int cantidad, Clase clase) {
        //controlo que no sesalte de clase con el topeI
        if (i <= topeI) {
            int cont = 0;
            String texto = "";
            //controlo que no se pase del largo de la matriz
            //en este caso máximo 6 asientos contiguos
            if (cantidad + j <= 6) {
                for (int x = cantidad + j - 1; x >= j; x--) {
                    //1 y 4 son las posiciones donde hay posible 2
                    if (cantidad != 1) {
                        boolean condColumnas = cantidad == 3 ? x == 1 || x == 4 : j == 1 || j == 4;
                        if (condColumnas) {
                            boolean condicion = cantidad == 3 ? m[i][x] == 2 : m[i][j] == 2;
                            if (condicion) {
                                if (this.txtLugares.length() <= 6 && Clase.Primera.equals(clase) && cantidad == 3 && (j == 2 || x == 2))
                                    x--;
                                x--;
                            }
                        }
                    }
                    if (asientoLibre(m[i][x])) {
                        texto += posicionLetra(x) + (i + 1) + "-";
                        cont++;
                    }
                }
            }

            //en caso de clase primera resto para validar bien con la cantidad ingresada originalmente
            if (cantidad != 1)
                cantidad = Clase.Primera.equals(clase) ? cantidad - 1 : cantidad;
            //en caso que la cantidad de asientos libres sea == a la cantidad ingresada procedo a imprimir lugares
            if (cont == cantidad) {
                opciones++;
                this.txtLugares += texto + "|";
            }
            if (cantidad != 1)
                cantidad = Clase.Primera.equals(clase) ? cantidad + 1 : cantidad;
        }
        //axioma de la funcion recursiva para recorrer la matriz
        if (i != m.length - 1 || j != m[i].length - 1) {
            if (j == m[i].length - 1) {
                i++;
                j = 0;
                vueltas++;
            } else {
                j++;
            }
            //cuento las vueltas para pegar el salto a la siguiente fila
            if (vueltas < m.length) {
                recorrerMatriz(m, i, j, topeI, vueltas, cantidad, clase);
            }
        }
    }

    private String quitarPipeFinal(String texto) {
        String txtSinPipe = texto != "" ? texto.substring(0, texto.length() - 1) : "";
        return texto.substring(texto.length() - 1) == "|" ? texto : txtSinPipe;
    }
}

