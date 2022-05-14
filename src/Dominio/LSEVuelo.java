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

    private class NodoVuelo {
        Vuelo nodo;
        NodoVuelo sig;

        public NodoVuelo(Vuelo nodo) {
            this.nodo = nodo;
        }

        public Vuelo getNodo() {
            return nodo;
        }

        public void setNodo(Vuelo nodo) {
            this.nodo = nodo;
        }

        public NodoVuelo getSig() {
            return sig;
        }

        public void setSig(NodoVuelo sig) {
            this.sig = sig;
        }
    }
}
