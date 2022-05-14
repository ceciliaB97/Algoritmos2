package tst.edu.ort.aed2.entregable;

public interface Pila {
    /**
     * Agrega un elemento a la cima de la pila.
     *
     * @param valor El valor a insertar en la cima de la pila
     */
    void push(int valor);

    /**
     * Remueve el elemento de la cima de la pila.
     * Pre: La pila no esta vacia
     *
     * @return El elemento borrado de la cima.
     */
    int pop();

    /**
     * Retorna el elemento que esta en la cima de la pila.
     * Pre: La pila no esta vacia
     * Pos: El elemento no se elimina como en la operacion {@link  #pop()}
     *
     * @return El elemento que esta en la cima
     */
    int top();

    /**
     * Devuelve la cantidad de elementos que hay en la pila
     *
     * @return La cantidad de elementos que hay en la pila
     */
    int getLargo();

    /**
     * @return true si la pila no tiene elementos, false de lo contrario
     */
    boolean estaVacia();

    /**
     * @return true si y solo si la pila esta llena
     */
    boolean estaLlena();


}
