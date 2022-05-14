package tst.edu.ort.aed2.entregable;

public interface Lista {

    /**
     * Agrega un elemento al principio de la lista
     *
     * @param valorNuevo El valor a agregar en la lista
     */
    void agregarAlPrincipio(int valorNuevo);

    /**
     * Agrega un elemento al final de la lista
     *
     * @param elementoNuevo El valor a agregar en la lista
     */
    void agregarAlFinal(int elementoNuevo);

    /**
     * Elimina el primer elemento de la lista
     * Pre:La lista no esta vacia
     *
     * @return El valor eliminado de la lista
     */
    int eliminarPrincipio();

    /**
     * Devuelve la cantidad de elementos agregados a la lista
     *
     * @return La cantidad de elementos que hay en la lista
     */
    int getLargo();

    /**
     * @return true si la lista no tiene elementos, false si hay al menos un elemento
     */
    boolean estaVacia();

    /**
     * Devuelve el valor del elemento en la posicion i, contando desde el 0.
     * Pre: El indice es valido, es decir que esta entre [0...largo -1]
     *
     * @param indice
     * @return Devuelve el valor del elemento en la posicion i, contando desde el 0.
     */
    int obtener(int indice);

}
