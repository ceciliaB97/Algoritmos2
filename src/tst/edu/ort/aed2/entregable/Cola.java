package tst.edu.ort.aed2.entregable;

public interface Cola<T> {

    void encolar(T dato);

    // Pre: !esVacia()
    T desencolar();

    boolean esVacia();
}
