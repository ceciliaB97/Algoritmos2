package tst.edu.ort.aed2.entregable;

public class ColaImp<T> implements Cola<T>{
    private NodoCola<T> inicio;
    private NodoCola<T> fin;
    private int largo;

    @Override
    public void encolar(T dato) {
        if (this.inicio == null) {
            inicio = new NodoCola<T>(dato);
            fin = inicio;
        } else {
            fin.setSig(new NodoCola<T>(dato));
            fin = fin.getSig();
        }
        this.largo++;
    }

    @Override
    // Pre: !esVacia()
    public T desencolar() {
        T dato = this.inicio.getDato();
        inicio = inicio.getSig();
        this.largo--;
        if (this.inicio == null) {
            fin = null;
        }
        return dato;
    }

    @Override
    public boolean esVacia() {
        return this.largo == 0;
    }

    public static class NodoCola<T> {
        private T dato;
        private NodoCola<T> sig;

        public NodoCola(T dato, NodoCola<T> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public NodoCola(T dato) {
            this.dato = dato;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public NodoCola<T> getSig() {
            return sig;
        }

        public void setSig(NodoCola<T> sig) {
            this.sig = sig;
        }

        @Override
        public String toString() {
            return dato + "";
        }
    }

}
