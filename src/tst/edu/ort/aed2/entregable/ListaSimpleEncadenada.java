package tst.edu.ort.aed2.entregable;

public class ListaSimpleEncadenada implements Lista {

    private Nodo primero;
    private int elementos;

    public ListaSimpleEncadenada() {
        this.elementos = 0;
        this.primero = null;
    }


    @Override
    public void agregarOrdenado(String valor) {

        Nodo nuevo = new Nodo(valor);
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            if (valor.compareTo(this.primero.getDato())<0) {
                nuevo.setSiguiente(this.primero);
                this.primero = nuevo;
            } else {
                Nodo reco = this.primero;
                Nodo atras = this.primero;;
                while (valor.compareTo(reco.getDato()) >= 0 && reco.getSiguiente() != null) {
                    atras = reco;
                    reco = reco.getSiguiente();
                }
                if (valor.compareTo(reco.getDato()) >= 0) {
                    reco.setSiguiente(nuevo);
                } else {
                    nuevo.setSiguiente(reco);
                    atras.setSiguiente(nuevo);
                }
            }
        }
        this.elementos++;
    }


        @Override
        public String eliminarPrincipio () {
            String retorno = "";
            if (!estaVacia()) {
                retorno = this.primero.getDato();
                Nodo aux = this.primero.getSiguiente();
                this.primero = aux;
                this.elementos--;
            }
            return retorno;
        }

        @Override
        public int getLargo () {
            return this.elementos;
        }

        @Override
        public boolean estaVacia () {
            return this.elementos == 0;
        }

        @Override
        public String obtener ( int indice){
            String retorno = "";
            int indices = 0;
            Nodo aux = this.primero;
            if (indice >= elementos || indice < 0) {
                return retorno;
            }
            while (aux != null) {
                if (indices == indice) {
                    retorno = aux.getDato();
                }
                aux = aux.getSiguiente();
                indices++;
            }
            return retorno;
        }

        public static class Nodo {
            private String dato;
            private Nodo siguiente;

            public Nodo(String dato) {
                this.dato = dato;
                this.siguiente = null;
            }

            public String getDato() {
                return dato;
            }

            public void setDato(String dato) {
                this.dato = dato;
            }

            public Nodo getSiguiente() {
                return siguiente;
            }

            public void setSiguiente(Nodo siguiente) {
                this.siguiente = siguiente;
            }
        }
    }
