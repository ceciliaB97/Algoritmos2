package Dominio;

import java.util.Comparator;

public class AbbPasajero {

    private Nodo raiz;
    private Comparator<Pasajero> comparadorPasajero;


    public AbbPasajero(Comparator<Pasajero> comparador) {
    this.comparadorPasajero=comparador;
    }

    public void insertar(Pasajero pasajero) {
        if (raiz == null) {
            raiz = new Nodo(pasajero);
        } else {
            insertarRecursivo(raiz, pasajero);
        }
    }


    private void insertarRecursivo(Nodo nodo, Pasajero pasajero) {
        if (comparadorPasajero.compare(nodo.getDato(),pasajero) < 0) {
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

    public Pasajero existeCedula(String cedula) {
        Pasajero p = new Pasajero(cedula, "", "", "");
        p = existeCedula(raiz, p, contador);
        return p;
    }

    private int contador = 0;

    public int getVueltas() {
        return contador;
    }

    private Pasajero existeCedula(Nodo nodo, Pasajero p, int cont) {
        Pasajero ret = p;
        cont++;
        if (nodo != null) {
            if (nodo.getDato().equals(ret)) {
                ret = p;
            } else {
                if (nodo.getDato().compareTo(p) < 0) {
                    ret = existeCedula(nodo.getDer(), p, cont);
                } else if (nodo.getDato().compareTo(p) > 0) {
                    ret = existeCedula(nodo.getIzq(), p, cont);
                }
            }
        }
        return ret;
    }

    public boolean pertenece(Pasajero pasajero) {
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
                if (comparadorPasajero.compare(nodo.getDato(),pasajero)< 0) {
                    retorno = perteneceRecursivo(nodo.getDer(), pasajero);
                } else if (comparadorPasajero.compare(nodo.getDato(),pasajero) > 0) {
                    retorno = perteneceRecursivo(nodo.getIzq(), pasajero);
                }
            }
        }
        return retorno;
    }

    //06. Listar pasajeros por categoría
    public void listarPasajerosPorCategoria() {
        if (this.raiz != null) {
            listarPasajerosPorCategoriaRecursivo(this.raiz);
        } else {
            System.out.println("No hay pasajeros.");
        }
    }

    private void listarPasajerosPorCategoriaRecursivo(Nodo nodoPasajero) {
        if (nodoPasajero != null) {
            listarPasajerosPorCategoriaRecursivo(nodoPasajero.getDer());
            listarPasajerosPorCategoriaRecursivo(nodoPasajero.getIzq());
            System.out.println(nodoPasajero.getDato());
        }
    }
    public String listarPasajerosAsc() {
        String retorno = "";
        if (raiz != null) {
            retorno ="No hay hay registros.";
        } else {
            retorno= ListarRecursivoAsc(raiz);
        }
        return  retorno;
    }

    private String ListarRecursivoAsc(Nodo nodo) {
        String cadena="";
        if (nodo == null) {
            cadena = "Vacio";
        } else {
            cadena += ListarRecursivoAsc(nodo.getDer());
            cadena += ListarRecursivoAsc(nodo.getIzq());
            cadena += nodo.getDato().toString() +" | ";
        }
        return cadena;
    }

    public String listarPasajeroDesc() {
        String retorno = "";
        if (raiz != null) {
            retorno ="No hay hay registros.";
        } else {
            retorno = ListarRecursivoDesc(raiz);
        }
        return retorno;
    }

    private String ListarRecursivoDesc(Nodo nodo) {
        String cadena="";
        if (nodo == null) {
            cadena = "Vacio";
        } else {
            cadena = nodo.getDato().toString() +" | ";
            cadena +=ListarRecursivoDesc(nodo.getIzq());
            cadena +=ListarRecursivoDesc(nodo.getDer());

        }
        return cadena;
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

    }
}
