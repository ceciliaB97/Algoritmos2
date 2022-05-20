package Dominio;

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
        return this.vertices.length == this.maxAeropuerto;
    }

    public boolean esVacio() {
        return this.vertices.length == 0;
    }

    public void agregarAeropuerto(Aeropuerto a) {
        int posLibre = obtenerPosLibre();
        this.vertices[posLibre] = a;
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
            if (vertices[i].getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    // existeVertice(origen) && existeVertice(destino)
    public boolean existeConexion(String origen, String destino) {
        boolean origenEncontrado = false;
        for (int i = 0; i < matConexion.length; i++) {
            if (vertices[i].equals(origen)) {
                origenEncontrado = true;
            }
            for (int j = 0; j < matConexion.length; j++) {
                if (vertices[j].equals(destino) && origenEncontrado) {
                    return matConexion[i][j].isExiste();
                }
            }
        }
        return false;
    }

    private int obtenerPos(Aeropuerto vert) {
        int pos = 0;
        while (pos < this.maxAeropuerto) {
            if (this.vertices[pos].equals(vert)) {
                return pos;
            }
            pos++;
        }
        return -1;
    }

    // PRE: existeVertice(origen) && existeVertice(destino) && !existeArista, grafo NO esta lleno
    public void agregarConexion(Aeropuerto origen, Aeropuerto destino, int kilometros) {
        int posOri = obtenerPos(origen);
        int posDes = obtenerPos(destino);
        matConexion[posOri][posDes].setExiste(true);
        matConexion[posOri][posDes].setKilometros(kilometros);
    }

    // PRE: existeVertice
    public void borrarAeropuerto(Aeropuerto vert) {
        int posEncontrada = this.obtenerPos(vert);
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

    /**
     * Marcar el origen con distancia cero.
     * Loop (cantidad de vertices):
     * 1) Obtener el vértice no visitado de menor costo (si hay varios cualquiera)
     * 2) Visitarlo
     * 3) Evaluar si tengo que actualizar el costo de los adyacentes NO VISITADOS.
     * <p>
     * En este ejemplo estamos utilizando dijkstra para retornar el costo minimo de ir del vertice vOrigen al vertice
     * vDestino.
     */
    public String[] viajeCostoMinimoKilometros(Aeropuerto vOrigen, Aeropuerto vDestino) {
        int cantidad = this.vertices.length;
        int posOrigen = obtenerPos(vOrigen);
        int posDestino = obtenerPos(vDestino);

        boolean[] aeropuertosVisitados = new boolean[this.maxAeropuerto];
        double[] costos = new double[this.maxAeropuerto];
        Aeropuerto[] aeropuertosAnteriores = new Aeropuerto[maxAeropuerto];

        for (int i = 0; i < this.maxAeropuerto; i++) {
            costos[i] = Integer.MAX_VALUE;
            aeropuertosAnteriores[i] = null; // aeropuertosAnteriores[i] = "-";
            //visitados[i] = false;
        }

        costos[posOrigen] = 0;
        for (int v = 0; v < cantidad; v++) {
            //1) Obtener el vértice no visitado de menor costo (si hay varios cualquiera)
            int pos = obtenerSiguienteAeropuertoNoVisitadoDeMenorCosto(costos, aeropuertosVisitados);
            if (pos != -1) {
                //2) Visitarlo
                aeropuertosVisitados[pos] = true;
                //3) Evaluar si tengo que actualizar el costo de los adyacentes NO VISITADOS.
                for (int j = 0; j < maxAeropuerto; j++) {
                    if (matConexion[pos][j].isExiste() && !aeropuertosVisitados[j]) { //los adyacentes NO VISITADOS
                        double costoNuevo = costos[pos] + matConexion[pos][j].getKilometros();
                        if (costoNuevo < costos[j]) {
                            costos[j] = costoNuevo;
                            aeropuertosAnteriores[j] = vertices[pos];
                        }
                    }
                }
            }
        }
        return retornoVueloCostoMinimo(costos[posDestino], aeropuertosAnteriores);
    }

    private String[] retornoVueloCostoMinimo(double costoMinimo, Aeropuerto[] recorridos) {
        String[] retorno = new String[2];

        retorno[0] = Double.toString(costoMinimo);
        for (Aeropuerto a : recorridos) {
            retorno[1] += a + " | ";
        }

        return retorno;
    }


    private int obtenerSiguienteAeropuertoNoVisitadoDeMenorCosto(double[] costos, boolean[] visitados) {
        int posMin = -1;
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < this.maxAeropuerto; i++) {
            if (!visitados[i] && costos[i] < min) {
                min = costos[i];
                posMin = i;
            }
        }
        return posMin;
    }
}
