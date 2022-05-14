package Dominio;

import java.util.Comparator;

public class AbbPasajero {
    private Nodo raiz;
    //COMPARADOR


    public AbbPasajero(/*COMPARTOR*/) {
        /*COMPARADOR = COMPARATOR*/
    }

    public void insertar(Pasajero pasajero) {
        if (raiz == null) {
            raiz = new Nodo(pasajero);
        } else {
            insertarRecursivo(raiz, pasajero);
        }
    }

    private void insertarRecursivo(Nodo nodo, Pasajero pasajero) {
        if (nodo.getDato().compareTo(pasajero) < 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new Nodo(pasajero));
            } else {
                insertarRecursivo(nodo.getDer(), pasajero);
            }
        } else {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new Nodo(pasajero));
            } else {
                insertarRecursivo(nodo.getIzq(), pasajero);
            }
        }
    }

    private boolean pertenece(Pasajero pasajero) {
        boolean resp = false;
        if (raiz != null) {
            resp = perteneceRecursivo(raiz, pasajero);
        }
        return resp;
    }

    private boolean perteneceRecursivo(Nodo nodo, Pasajero pasajero) {
        boolean retorno = false;
        if (nodo != null) {
            if (nodo.getDato().equals(pasajero)) {
                retorno = true;
            } else {
                if (nodo.getDato().compareTo(pasajero) < 0) {
                    retorno = perteneceRecursivo(nodo.getDer(), pasajero);
                } else if (nodo.getDato().compareTo(pasajero) > 0) {
                    retorno = perteneceRecursivo(nodo.getIzq(), pasajero);
                }
            }
        }
        return retorno;
    }

    //06. Listar pasajeros por categoría
    public void listarPasajerosPorCategoria(){
        if(this.raiz != null){
            listarPasajerosPorCategoriaRecursivo(this.raiz);
        } else {
            System.out.println("No hay pasajeros.");
        }
    }

    private void listarPasajerosPorCategoriaRecursivo(Nodo nodoPasajero) {
        if(nodoPasajero != null){
            listarPasajerosPorCategoriaRecursivo(nodoPasajero.getDer());
            listarPasajerosPorCategoriaRecursivo(nodoPasajero.getIzq());
            System.out.println(nodoPasajero.getDato());
        }
    }

    /**
     * Nodo de ABB Pasajero
     */

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
