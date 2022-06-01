package Dominio;

import tst.edu.ort.aed2.entregable.Cola;
import tst.edu.ort.aed2.entregable.ColaImp;

public class GrfAeropuerto {
    private int maxAeropuerto;
    private Aeropuerto[] vertices;
    private Conexion[][] matConexion;

    public GrfAeropuerto(int maxAeropuerto) {
        this.maxAeropuerto = maxAeropuerto;
        this.vertices = new Aeropuerto[maxAeropuerto];
        this.matConexion = new Conexion[maxAeropuerto][maxAeropuerto];
    }

    public boolean estaLleno() {
        int contadorCompleto = 0;
        for (Aeropuerto aereopuerto : vertices) {
            if (aereopuerto != null) {
                contadorCompleto++;
            }
        }
        return contadorCompleto == maxAeropuerto;
    }

    public boolean esVacio() {
        int contadorInCompleto = 0;
        for (Aeropuerto aeropuerto : vertices) {
            if (aeropuerto == null) {
                contadorInCompleto++;
            }
        }
        return contadorInCompleto == maxAeropuerto;
    }

    public void agregarAeropuerto(Aeropuerto a) {
        int posLibre = obtenerPosLibre();
        if (posLibre >= 0) {
            this.vertices[posLibre] = a;
        }
    }

    private int obtenerPosLibre() {
        int pos = 0;
        while (pos < this.maxAeropuerto) {
            if (this.vertices[pos] == null) {
                return pos;
            }
            pos++;
        }
        return -1;
    }

    public boolean existeAeropuerto(String codigo) {
        int i;
        for (i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public Aeropuerto obtenerAeropuerto(String codigo) {
        int i;
        for (i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].getCodigo().equals(codigo)) {
                return vertices[i];
            }
        }
        return null;
    }

    // existeVertice(origen) && existeVertice(destino)
    public boolean existeConexion(String origen, String destino) {
//        boolean origenEncontrado = false;
//        for (int i = 0; i < matConexion.length; i++) {
//            if (vertices[i] != null && vertices[i].getCodigo().equals(origen)) {
//                origenEncontrado = true;
//            }
//            for (int j = 0; j < matConexion.length; j++) {
//                if (vertices[j] != null && vertices[j].getCodigo().equals(destino) && origenEncontrado) {
//                    if (matConexion[i][j] != null) {
//                        return matConexion[i][j].isExiste();
//                    }
//                }
//            }
//        }

        int posOr, posDedt;
        posOr = this.obtenerPos(origen);
        posDedt = this.obtenerPos(destino);
        Conexion encontrado = matConexion[posOr][posDedt];

        if (encontrado != null) {
            return encontrado.isExiste();
        }

        return false;
    }

    private int obtenerPosCodigo(String vertCodigo) {
        int pos = 0;
        while (pos < this.maxAeropuerto) {
            if (this.vertices[pos] != null && this.vertices[pos].getCodigo().equals(vertCodigo)) {
                return pos;
            }
            pos++;
        }
        return -1;
    }

    private int obtenerPos(String vert) {
        int pos = 0;
        while (pos < this.maxAeropuerto) {
            if (this.vertices[pos] != null && this.vertices[pos].getCodigo().equals(vert)) {
                return pos;
            }
            pos++;
        }
        return -1;
    }


    // PRE: existeVertice(origen) && existeVertice(destino) && !existeArista, grafo NO esta lleno
    public void agregarConexion(String origen, String destino, double kilometros) {
        int posOri = obtenerPos(origen);
        int posDes = obtenerPos(destino);
        Conexion nuevaConexion = new Conexion(origen, destino, kilometros);
        matConexion[posOri][posDes] = nuevaConexion;

    }

    // PRE: existeVertice
    public void borrarAeropuerto(Aeropuerto vert) {
        int posEncontrada = this.obtenerPos(vert.getCodigo());
        this.vertices[posEncontrada] = null;
        //borrar en matriz
        for (int i = 0; i < this.vertices.length; i++) {
            Conexion encontradaI = null;
            if (i == posEncontrada) {
                encontradaI = this.matConexion[posEncontrada][posEncontrada];
                break;
            } else {
                encontradaI = this.matConexion[posEncontrada][i];
            }
            encontradaI.setExiste(false);
            for (int j = 0; j < this.vertices.length; j++) {
                if (j == posEncontrada) {
                    Conexion encontradaJ = this.matConexion[i][j];
                    encontradaJ.setExiste(false);
                }
            }
        }
    }

    public RetornoDecimalTexto viajeCostoMinimoDolares(String vOrigen, String vDestino) {
        int cantidad = this.vertices.length;
        int posOrigen = obtenerPosCodigo(vOrigen);
        int posDestino = obtenerPosCodigo(vDestino);
        Aeropuerto arpOrigen = this.obtenerAeropuerto(vOrigen);

        String concatAeropuertos = arpOrigen + "|";
        double sumatoriaCosto = 0.0;
        RetornoDecimalTexto retornoNuevo = null;
        boolean[] aeropuertosVisitados = new boolean[this.vertices.length];
        double[] costos = new double[this.vertices.length];
        Aeropuerto[] aeropuertosAnteriores = new Aeropuerto[this.vertices.length];

        for (int i = 0; i < cantidad; i++) {
            costos[i] = Integer.MAX_VALUE;
            aeropuertosAnteriores[i] = null;
        }

        costos[posOrigen] = 0;
        for (int v = 0; v < cantidad; v++) {
            int pos = obtenerSiguienteAeropuertoNoVisitadoDeMenorCosto(costos, aeropuertosVisitados);
            if (pos != -1) {
                aeropuertosVisitados[pos] = true;
                for (int j = 0; j < cantidad; j++) {
                    if (matConexion[pos][j] != null && matConexion[pos][j].isExiste() && !aeropuertosVisitados[j]) {
                        LSEVuelo vuelosConexion = matConexion[pos][j].getListaVuelos();
                        if (!vuelosConexion.estaVacia()) {
                            double costoNuevo = costos[pos] + vuelosConexion.getCostoVueloMasBarato();
                            if (costoNuevo < costos[j]) {
                                costos[j] = costoNuevo;
                                aeropuertosAnteriores[j] = vertices[pos];
                                if (!vertices[pos].equals(arpOrigen)) {
                                    concatAeropuertos += vertices[pos] + "|";
                                }
                            }
                        }

                    }
                }
            }
        }
        sumatoriaCosto = costos[posDestino];
        concatAeropuertos += this.obtenerAeropuerto(vDestino).toString();
        retornoNuevo = new RetornoDecimalTexto(sumatoriaCosto, concatAeropuertos);

        return retornoNuevo;
    }

    public void viajeCostoMinimoKilometros(String vOrigen, String vDestino) {
        if (!vOrigen.equals(vDestino)) {
            int posOrigen = obtenerPos(vOrigen);
            int posDestino = obtenerPos(vDestino);

            Aeropuerto arpOrigen = this.obtenerAeropuerto(vOrigen);
            this.concatAeropuertos = arpOrigen + "|";
            this.sumatoriaKms = 0.0;
            boolean[] visitados = new boolean[this.vertices.length];
            double[] costosKms = new double[this.vertices.length];

            int i;
            for (i = 0; i < this.vertices.length; i++) {
                costosKms[i] = Double.MAX_VALUE;
            }

            costosKms[posOrigen] = 0;
            int j;
            for (j = 0; j < this.vertices.length; j++) {
                int pos = obtenerSiguienteAeropuertoNoVisitadoDeMenorCosto(costosKms, visitados);
                if (pos != -1) {
                    visitados[pos] = true;
                    int k;
                    for (k = 0; k < this.maxAeropuerto; k++) {
                        if (matConexion[pos][k] != null && matConexion[pos][k].isExiste() && !visitados[k]) {
                            double costoKmsNuevo = this.matConexion[pos][k] != null ? costosKms[pos] + matConexion[pos][k].getKilometros() : costosKms[pos];
                            if (costoKmsNuevo < costosKms[k]) {
                                costosKms[k] = costoKmsNuevo;
                                if (k == posDestino && !arpOrigen.equals(vertices[pos])) {
                                    this.concatAeropuertos += vertices[pos] + "|";
                                }
                            }
                        }
                    }
                }
            }
            this.sumatoriaKms += costosKms[posDestino];
            this.concatAeropuertos += this.obtenerAeropuerto(vDestino).toString();
        }
    }

    private double sumatoriaKms;
    private String concatAeropuertos;

    public double getSumatoriaKms() {
        return this.sumatoriaKms;
    }

    public String getConcatAeropuertos() {
        return this.concatAeropuertos;
    }

    private String[] retornoVueloCostoMinimo(double costoMinimo, String recorridos) {
        String[] retorno = new String[2];

        retorno[0] = Double.toString(costoMinimo);
        retorno[1] = recorridos.substring(0, recorridos.length() - 1).replace("null", "");

        return retorno;
    }


    private int obtenerSiguienteAeropuertoNoVisitadoDeMenorCosto(double[] costos, boolean[] visitados) {
        int posMin = -1;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < this.maxAeropuerto; i++) {
            if (!visitados[i] && costos[i] < min) {
                min = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }


    public boolean existeCaminoAeropuetos(String vertOrigen, String vertDestino) {
        int inicio = obtenerPosCodigo(vertOrigen);
        int fin = obtenerPosCodigo(vertDestino);
        boolean[] visitados = new boolean[vertices.length];
        Cola<Integer> cola = new ColaImp<>();
        cola.encolar(inicio);
        visitados[inicio] = true;
        while (!cola.esVacia()) {
            int pos = cola.desencolar();
            for (int j = 0; j < vertices.length; j++) {
                if (matConexion[pos][j] != null && matConexion[pos][j].isExiste() && !visitados[j]) {
                    cola.encolar(j);
                    visitados[j] = true;
                    if (j == fin) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Conexion obtenerConexion(String codigoOrigen, String codigoDestino) {
        Conexion retorno = null;
        boolean origenEncontrado = false;
        for (int i = 0; i < matConexion.length; i++) {
            if (vertices[i] != null && vertices[i].getCodigo().equals(codigoOrigen)) {
                origenEncontrado = true;
            }
            for (int j = 0; j < matConexion.length; j++) {
                if (vertices[j] != null && vertices[j].getCodigo().equals(codigoDestino) && origenEncontrado) {
                    if (matConexion[i][j] != null && matConexion[i][j].isExiste()) {
                        retorno = matConexion[i][j];
                    }
                }
            }
        }
        return retorno;
    }

    public String obtenerEscalasAereopuertoOrigen(String codigoAereopuertoOrigen, int cantidadEscalas) {
        String cadena = "";
        int posicionAereopuerto = obtenerPos(codigoAereopuertoOrigen);
        return cadena;
    }

    public class RetornoDecimalTexto {
        private double decimal;
        private String texto;

        public RetornoDecimalTexto(double decimal, String texto) {
            this.decimal = decimal;
            this.texto = texto;
        }

        public double getDecimal() {
            return decimal;
        }

        public String getTexto() {
            return texto;
        }
    }
}
