package Dominio;

import java.util.Comparator;

public class AbbPasajero {

    private Nodo raiz;
    //COMPARADOR


    public AbbPasajero(/*COMPARTOR*/) {
        /*COMPARADOR = COMPARATOR*/
    }

    private class Nodo {
        private Pasajero dato;
        private Nodo izq;
        private Nodo der;

        public void setIzq(Nodo izq) {
            this.izq = izq;
        }

        public void setDer(Nodo der) {
            this.der = der;
        }

        public Nodo(Pasajero dato) {
            this.dato = dato;
        }

        public Nodo getIzq() {
            return izq;
        }

        public Nodo getDer() {
            return der;
        }

        public Pasajero getDato() {
            return dato;

        }

        public boolean esHoja() {
            return izq == null && der == null;
        }

        //comparador por cedula..
    }
}
