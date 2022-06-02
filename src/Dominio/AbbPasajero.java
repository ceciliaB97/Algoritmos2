package Dominio;

import uy.edu.ort.aed2.obligatorio.Sistema;
import uy.edu.ort.aed2.obligatorio.Sistema.Categoria;

import java.util.Comparator;

public class AbbPasajero {

    private Nodo raiz;
    private int contador;

    public AbbPasajero() {
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
        } else if (nodo.getDato().compareTo(pasajero) > 0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new Nodo(pasajero));
            } else {
                insertarRecursivo(nodo.getIzq(), pasajero);
            }
        }
    }

    public Pasajero existeCedula(String cedula) {
        Pasajero p = new Pasajero(cedula, "", "", "");
        this.contador = 0;
        p = existeCedula(raiz, p, contador);

        return p;
    }

    public int getVueltas() {
        return contador;
    }

    private Pasajero existeCedula(Nodo nodo, Pasajero p, int cont) {
        Pasajero ret = p;

        if (nodo != null) {
            ++cont;
            this.contador = cont;
            if (nodo.getDato().equals(ret)) {
                return nodo.getDato();
            } else {
                if (nodo.getDato().compareTo(p) < 0) {
                    return existeCedula(nodo.getDer(), p, cont);
                } else if (nodo.getDato().compareTo(p) > 0) {
                    return existeCedula(nodo.getIzq(), p, cont);
                }
            }
        }
        return null;
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
    public String listarPasajerosPorCategoria(String categoria) {
        if (this.raiz != null) {
            return listarPasajerosPorCategoriaRecursivo(this.raiz, categoria, 0);
        } else {
            return "No hay pasajeros";
        }

    }

    private String listarPasajerosPorCategoriaRecursivo(Nodo nodoPasajero, String categoria, int contador) {
        String retorno = "";
        if (nodoPasajero != null) {
            if (categoria.equals(nodoPasajero.getDato().getCategoria())) {
                if (contador > 0) {
                    retorno += "|";
                }
                retorno += nodoPasajero.getDato().toString();
                contador++;
            }
            retorno += listarPasajerosPorCategoriaRecursivo(nodoPasajero.getDer(), categoria, contador);
            retorno += listarPasajerosPorCategoriaRecursivo(nodoPasajero.getIzq(), categoria, contador);
        }
        return retorno;
    }

    public String listarPasajerosAsc() {
        String retorno = "";
        if (raiz != null) {
            retorno = ListarRecursivoAsc(raiz.getIzq());
            retorno += raiz.getDato().toString() + "|";
            retorno += ListarRecursivoAsc(raiz.getDer());
            retorno = retorno.substring(0, retorno.length() - 1);
        }
        return retorno;
    }

    private String ListarRecursivoAsc(Nodo nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena += ListarRecursivoAsc(nodo.getIzq());
            cadena += nodo.getDato().toString() + "|";
            cadena += ListarRecursivoAsc(nodo.getDer());
        }
        return cadena;
    }

    public String listarPasajeroDesc() {
        String retorno = "";
        if (raiz != null) {
            retorno += ListarRecursivoDesc(raiz.getDer());
            retorno += raiz.getDato().toString() + "|";
            retorno += ListarRecursivoDesc(raiz.getIzq());
            retorno = retorno.substring(0, retorno.length() - 1);
        }
        return retorno;
    }

    private String ListarRecursivoDesc(Nodo nodo) {
        String cadena = "";
        if (nodo != null) {
            cadena += ListarRecursivoDesc(nodo.getDer());
            cadena += nodo.getDato().toString() + "|";
            cadena += ListarRecursivoDesc(nodo.getIzq());
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

    }
}
