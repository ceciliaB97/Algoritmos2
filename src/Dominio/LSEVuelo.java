package Dominio;

public class LSEVuelo {
    NodoVuelo primero;
    NodoVuelo ultimo;
    int cantidad;

    public LSEVuelo() {
        this.primero = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    public NodoVuelo getPrimero() {
        return primero;
    }

    public void setPrimero(NodoVuelo primero) {
        this.primero = primero;
    }

    public NodoVuelo getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoVuelo ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad() {
        this.cantidad++;
    }

    public void agregarAlPrincipio(Vuelo vuelo) {
        NodoVuelo nuevo = new NodoVuelo(vuelo);
        if (this.primero == null) {
            this.primero = nuevo;
            this.ultimo = nuevo;
        } else {
            nuevo.setSig(this.primero);
            this.primero = nuevo;
        }
        this.cantidad++;
    }

    public void agregarAlFinal(Vuelo vuelo) {
        NodoVuelo nuevo = new NodoVuelo(vuelo);
        if (this.ultimo == null) {
            this.ultimo = nuevo;
            this.primero = nuevo;
        } else {
            this.ultimo.setSig(nuevo);
            this.ultimo = nuevo;
        }
        this.cantidad++;
    }

    public Vuelo eliminarPrincipio() {
        NodoVuelo aux = this.primero;
        if (this.primero.getSig() == null) {
            this.primero = null;
        } else {
            this.primero = this.primero.getSig();
        }
        this.cantidad--;
        return aux.getVuelo();
    }

    public int getLargo() {
        return this.cantidad;
    }

    public boolean estaVacia() {
        return this.cantidad == 0;
    }

    /**
     * Obtiene el vuelo por el codigo y la conexion
     *
     * @param aeropuertoOrigen
     * @param aeropuertoDestino
     * @param codigoVuelo
     * @return
     */
    public Vuelo obtenerVueloCodigoConexion(String aeropuertoOrigen, String aeropuertoDestino, String codigoVuelo) {
        Vuelo vueloAuxiliar = new Vuelo(aeropuertoOrigen, aeropuertoDestino, codigoVuelo, -1, -1, -1);
        return recorrerRECCodigoConexion(this.primero, vueloAuxiliar, 0);
    }

    private Vuelo recorrerRECCodigoConexion(NodoVuelo actual, Vuelo vueloAuxiliar, int contador) {
        if (actual.getVuelo().equals(vueloAuxiliar) && actual != null) {
            return actual.getVuelo();
        } else {
            if (contador <= cantidad && actual.getSig() != null) {
                return recorrerRECCodigoConexion(actual.getSig(), vueloAuxiliar, contador + 1);
            }
        }
        return null;
    }

    public Vuelo obtenerVueloPorOrigen(String aeropuertoOrigen) {
        return recorrerRECVueloOrigen(this.primero, aeropuertoOrigen, 0);
    }

    private Vuelo recorrerRECVueloOrigen(NodoVuelo actual, String aeropuertoOrigen, int contador) {
        if (actual.getVuelo().getCodigoAeropuertoOrigen().equals(aeropuertoOrigen) && actual != null) {
            return actual.getVuelo();
        } else {
            if (contador <= cantidad && actual.getSig() != null) {
                return recorrerRECVueloOrigen(actual.getSig(), aeropuertoOrigen, contador + 1);
            }
        }
        return null;
    }

    public Vuelo obtenervueloPorDestino(String aeropuertoDestino) {
        return recorrerRECVueloDestino(this.primero, aeropuertoDestino, 0);
    }

    private Vuelo recorrerRECVueloDestino(NodoVuelo actual, String aeropuertoDestino, int contador) {
        if (actual.getVuelo().getCodigoAeropuertoDestino().equals(aeropuertoDestino) && actual != null) {
            return actual.getVuelo();
        } else {
            if (contador <= cantidad && actual.getSig() != null) {
                return recorrerRECVueloDestino(actual.getSig(), aeropuertoDestino, contador + 1);
            }
        }
        return null;
    }

    /**
     * Obtiene el vuelo por el codigo
     *
     * @param codigoVuelo
     * @return
     */
    public Vuelo obtenerVueloCodigo(String codigoVuelo) {
        return recorrerRECCodigo(this.primero, codigoVuelo, 0);
    }

    public Vuelo recorrerRECCodigo(NodoVuelo actual, String codigoVuelo, int contador) {
        if (actual != null && actual.getVuelo().getCodigoVuelo().equals(codigoVuelo)) {
            return actual.getVuelo();
        } else {
            if (contador <= cantidad && actual != null && actual.getSig() != null) {
                return recorrerRECCodigo(actual.getSig(), codigoVuelo, contador + 1);
            }
        }
        return null;
    }

    public double getCostoVueloMasBarato() {
        double costoMinimo = Double.MAX_VALUE;
        if (!estaVacia()) {
            NodoVuelo aux = this.primero;
            for (int i = 0; i < cantidad && aux != null; i++) {
                Vuelo v = aux.getVuelo();
                if (v.getCostoEnDolares() < costoMinimo) {
                    costoMinimo = v.getCostoEnDolares();
                }
                aux = aux.getSig();
            }
        }
        return costoMinimo;
    }

    private class NodoVuelo {
        Vuelo vuelo;
        NodoVuelo sig;

        public NodoVuelo(Vuelo nodo) {
            this.vuelo = nodo;
        }

        public Vuelo getVuelo() {
            return vuelo;
        }

        public void setVuelo(Vuelo vuelo) {
            this.vuelo = vuelo;
        }

        public NodoVuelo getSig() {
            return sig;
        }

        public void setSig(NodoVuelo sig) {
            this.sig = sig;
        }
    }
}
