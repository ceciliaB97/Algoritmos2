package Utils;

import Dominio.*;

import java.util.Objects;

public class ABB {
    private NodoABB raiz;
    private int cantNodos;

    public ABB() {
        this.raiz = null;
        this.cantNodos = 0;
    }

    public NodoABB getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoABB raiz) {
        this.raiz = raiz;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public boolean esVacio() {
        return this.cantNodos == 0;
    }

    public void insertar(Object o) {
        NodoABB nuevo = new NodoABB(o, null, null);
        if (this.esVacio()) {
            this.setRaiz(nuevo);
        } else {
            insertarRecursivo(this.getRaiz(), nuevo);
        }
        this.cantNodos++;
    }

    private void insertarRecursivo(NodoABB nodo, NodoABB nuevo) {
        if (nuevo.compareTo(nodo.getDato()) > 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoABB(nuevo, null, null));
            } else {
                insertarRecursivo(nodo.getDer(), nuevo);
            }
        } else {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoABB(nuevo, null, null));
            } else {
                insertarRecursivo(nodo.getIzq(), nuevo);
            }
        }
    }

    private boolean pertenece(NodoABB nodo) {
        boolean resp = false;
        if (raiz != null) {
            resp = perteneceRecursivo(raiz, nodo);
        }
        return resp;
    }

    private boolean perteneceRecursivo(NodoABB nodo, NodoABB buscado) {
        boolean retorno = false;
        if (nodo != null) {
            if (nodo.equals(buscado)) {
                retorno = true;
            } else {
                if (nodo.compareTo(buscado) > 0) {
                    retorno = perteneceRecursivo(nodo.getDer(), buscado);
                } else if (nodo.compareTo(buscado) < 0) {
                    retorno = perteneceRecursivo(nodo.getIzq(), buscado);
                }
            }
        }
        return retorno;
    }

    private void ListarAsc() {
        if (raiz != null) {
            System.out.println("No hay hay registros.");
        } else {
            ListarRecursivo(raiz);
        }
    }

    private void ListarRecursivo(NodoABB nodo) {
        if (nodo == null) {
            System.out.println("vacio");
        } else {
            ListarRecursivo(nodo.getDer());
            ListarRecursivo(nodo.getIzq());
            System.out.println(nodo.getDato());
        }
    }

    private void ListarDesc() {
        if (raiz != null) {
            System.out.println("No hay hay registros.");
        } else {
            ListarRecursivoDesc(raiz);
        }
    }

    private void ListarRecursivoDesc(NodoABB nodo) {
        if (nodo == null) {
            System.out.println("vacio");
        } else {
            System.out.println(nodo.getDato());
            ListarRecursivo(nodo.getIzq());
            ListarRecursivo(nodo.getDer());
        }
    }


    public class NodoABB implements Comparable<Object> {
        private Object dato;
        private NodoABB der, izq;

        public NodoABB(Object dato, NodoABB der, NodoABB izq) {
            this.dato = dato;
            this.der = der;
            this.izq = izq;
        }

        public Object getDato() {
            return dato;
        }

        public void setDato(Object dato) {
            this.dato = dato;
        }

        public NodoABB getDer() {
            return der;
        }

        public void setDer(NodoABB der) {
            this.der = der;
        }

        public NodoABB getIzq() {
            return izq;
        }

        public void setIzq(NodoABB izq) {
            this.izq = izq;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Aeropuerto) {
                return ((Aeropuerto) this.getDato()).equals((Aeropuerto) o);
            } else if (o instanceof Conexion) {
                return ((Conexion) this.getDato()).equals((Conexion) o);

            } else if (o instanceof Pasajero) {
                return ((Pasajero) this.getDato()).equals((Pasajero) o);

            } else if (o instanceof Vuelo) {
                return ((Vuelo) this.getDato()).equals((Vuelo) o);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(dato);
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Aeropuerto) {
                return ((Aeropuerto) this.getDato()).compareTo((Aeropuerto) o);
            } else if (o instanceof Conexion) {
                return ((Conexion) this.getDato()).compareTo((Conexion) o);

            } else if (o instanceof Pasajero) {
                return ((Pasajero) this.getDato()).compareTo((Pasajero) o);

            } else if (o instanceof Vuelo) {
                return ((Vuelo) this.getDato()).compareTo((Vuelo) o);

            }
            return 0;
        }
    }
}
