package tst.edu.ort.aed2.entregable.test;


import org.junit.jupiter.api.Test;
import tst.edu.ort.aed2.entregable.Lista;

import tst.edu.ort.aed2.entregable.ListaSimpleEncadenada;

import static org.junit.jupiter.api.Assertions.*;

public class ListaTest {
    /**
     * @return La implementacion de la lista
     */
    private Lista nuevaImplementacion() {
        return new ListaSimpleEncadenada();
    }

    /**
     * Se agregan 5 elementos al principio de una lista vacia.
     */
    @Test
    void agregarAlPrincipio_agrego5Elementos() {
        Lista lista = nuevaImplementacion();
        lista.agregarAlPrincipio(1);
        lista.agregarAlPrincipio(3);
        lista.agregarAlPrincipio(2);
        lista.agregarAlPrincipio(4);
        lista.agregarAlPrincipio(5);

        assertEquals(5, lista.getLargo());
        assertEquals(5, lista.obtener(0));
        assertEquals(4, lista.obtener(1));
        assertEquals(2, lista.obtener(2));
        assertEquals(3, lista.obtener(3));
        assertEquals(1, lista.obtener(4));
    }

    /**
     * Se agregan 5 elementos al final de una lista vacia.
     */
    @Test
    void agregarAlFinal_agrego5Elementos() {
        Lista lista = nuevaImplementacion();
        lista.agregarAlFinal(1);
        lista.agregarAlFinal(3);
        lista.agregarAlFinal(2);
        lista.agregarAlFinal(4);
        lista.agregarAlFinal(5);

        assertEquals(5, lista.getLargo());
        assertEquals(1, lista.obtener(0));
        assertEquals(3, lista.obtener(1));
        assertEquals(2, lista.obtener(2));
        assertEquals(4, lista.obtener(3));
        assertEquals(5, lista.obtener(4));
    }

    /**
     * Se agregan 5 elementos al principio y al final de una lista vacia.
     */
    @Test
    void agregarAlFinal_agregarAlPrincipio_agrego5Elementos() {
        Lista lista = nuevaImplementacion();
        lista.agregarAlFinal(1);
        lista.agregarAlPrincipio(3);
        lista.agregarAlPrincipio(2);
        lista.agregarAlFinal(4);
        lista.agregarAlPrincipio(5);

        assertEquals(5, lista.getLargo());
        assertEquals(5, lista.obtener(0));
        assertEquals(2, lista.obtener(1));
        assertEquals(3, lista.obtener(2));
        assertEquals(1, lista.obtener(3));
        assertEquals(4, lista.obtener(4));
    }

    /**
     * Se elimina el unico elemento que contiene una lista.
     */
    @Test
    void eliminarPrincipio_listConUnSoloElemento_eliminoAlPrincipio_laListaQuedaVacia() {
        Lista lista = nuevaImplementacion();
        lista.agregarAlPrincipio(1);

        lista.eliminarPrincipio();

        assertTrue(lista.estaVacia());
        assertEquals(0, lista.getLargo());
    }

    /**
     * Dada una lista con dos elementos, se elimina el primer elemento.
     */
    @Test
    void eliminarPrincipio_listConDosElementos_eliminoAlPrincipio_laListaQuedaConUnElemento() {
        Lista lista = nuevaImplementacion();
        lista.agregarAlPrincipio(1);
        lista.agregarAlFinal(2);

        lista.eliminarPrincipio();

        assertFalse(lista.estaVacia());
        assertEquals(1, lista.getLargo());
        assertEquals(2, lista.obtener(0));
    }

    /**
     * Se obtine el largo de una lista vacia.
     */
    @Test
    void getLargo_listaVacia_retorna0() {
        Lista lista = nuevaImplementacion();

        assertEquals(0, lista.getLargo());
    }

    /**
     * Se obtiene el largo de una lista que contiene cinco elementos.
     */
    @Test
    void getLargo_listaCon5Elementos_retorna5() {
        Lista lista = nuevaImplementacion();
        lista.agregarAlPrincipio(1);
        lista.agregarAlPrincipio(2);
        lista.agregarAlFinal(3);
        lista.agregarAlFinal(4);
        lista.agregarAlPrincipio(5);

        assertEquals(5, lista.getLargo());
    }


    /**
     * Se pregunta si una lista esta vacia.
     */
    @Test
    void estaVacia_listaSinElementos_retornaTrue() {
        Lista lista = nuevaImplementacion();

        assertTrue(lista.estaVacia());
    }

    /**
     * Se pregunta si una lista con elementos esta vacia.
     */
    @Test
    void estaVacia_listaConElementos_retornaFalse() {
        Lista lista = nuevaImplementacion();
        lista.agregarAlPrincipio(1);

        assertFalse(lista.estaVacia());
    }

    /**
     * Dada una lista con dos elementos, se obtiene el elemento en la segunda posicion.
     */
    @Test
    void obtener_listaContineDosElementos_obtengoElementoEnPoscion1_retornaElemento() {
        Lista lista = nuevaImplementacion();
        lista.agregarAlPrincipio(1);
        lista.agregarAlFinal(2);

        assertEquals(2, lista.obtener(1));
    }

    /**
     * Dada una lista con un elemento, se obtiene el elemento en la primer posicion.
     */
    @Test
    void obtener_listaContineUnElementos_obtengoElementoEnPoscion0_retornaElemento() {
        Lista lista = nuevaImplementacion();
        lista.agregarAlPrincipio(1);

        assertEquals(1, lista.obtener(0));
    }
}