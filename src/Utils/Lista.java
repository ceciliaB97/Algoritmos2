package Utils;

public class Lista {

    private NodoLista primero;
    private NodoLista ultimo;
    private int cantNodos;

    public Lista() {
        this.primero = null;
        this.ultimo = null;
        this.cantNodos = 0;
    }

    public NodoLista getPrimero() {
        return primero;
    }

    public void setPrimero(NodoLista primero) {
        this.primero = primero;
    }

    public NodoLista getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoLista ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public boolean esVacia(){
        return this.cantNodos == 0;
    }

    public void insertarFinal(NodoLista n){
        if(this.esVacia()){
            this.primero = n;
            this.ultimo = n;
        } else {
            this.ultimo.setSig(n);
            this.setUltimo(n);
            this.cantNodos++;
        }
    }

    public void insertarInicio(NodoLista n){
        if(this.esVacia()){
            this.primero = n;
            this.ultimo = n;
        } else {
            n.setSig(this.primero);
            this.primero = n;
            this.cantNodos++;
        }
    }

    public NodoLista obtenerNodo(Object o){
        if(!this.esVacia()){
            NodoLista aux = this.primero;
            while (aux.getSig() != null) {
                if (aux.getDato() == o) {
                    return aux;
                }
                aux = aux.getSig();
            }
        }
    return null;
    }

    public void Listar() {
        NodoLista aux = this.getPrimero();
        while (aux!=null){
            System.out.print(aux.getDato() + " - ");
            aux=aux.getSig();
        }
    }

    /*
    * */
    public class NodoLista {
        private Object dato;
        private NodoLista sig;

        public NodoLista(Object dato, NodoLista sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public Object getDato() {
            return dato;
        }

        public void setDato(Object dato) {
            this.dato = dato;
        }

        public NodoLista getSig() {
            return sig;
        }

        public void setSig(NodoLista sig) {
            this.sig = sig;
        }
    }
}
