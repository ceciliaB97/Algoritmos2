package tst.edu.ort.aed2.entregable.test;

import org.junit.jupiter.api.Test;
import tst.edu.ort.aed2.entregable.Pila;

import static org.junit.jupiter.api.Assertions.*;


public abstract class PilaTest {

    /**
     * Devuelve la implementacion de pila a testear.
     *
     * @return
     */
    abstract Pila obtenerImplementacion();

    /**
     * Se agregan elementos a la pila.
     */
    @Test
    void push_pilaEstaVacia_seAgreganElementosAPila() {
        Pila pila = obtenerImplementacion();
        pila.push(1);
        pila.push(2);

        assertFalse(pila.estaVacia());
        assertEquals(2, pila.getLargo());
    }

    /**
     * Se elimina el unico elemento perteneciente a una pila.
     */
    @Test
    void pop_pilaConUnElemento_seRemueveElementoDeLaPila() {
        Pila pila = obtenerImplementacion();
        pila.push(1);

        assertEquals(1, pila.pop());
        assertTrue(pila.estaVacia());
    }

    /**
     * Dada una pila con dos elementos, se elimina el elemento de la cima.
     */
    @Test
    void pop_pilaConDosElementos_seRemueveElementoDeLaPila() {
        Pila pila = obtenerImplementacion();
        pila.push(1);
        pila.push(2);

        assertEquals(2, pila.pop());
        assertFalse(pila.estaVacia());
        assertEquals(1, pila.pop());
        assertTrue(pila.estaVacia());
    }

    /**
     * Dado un pila con un elemento, se elimina el elemento de la cima.
     */
    @Test
    void top_pilaConUnElemento_retornaElementoEnLaCima() {
        Pila pila = obtenerImplementacion();
        pila.push(1);

        assertEquals(1, pila.top());
        assertEquals(1, pila.top());
        assertEquals(1, pila.top());
        assertEquals(1, pila.top());
        assertEquals(1, pila.top());

        assertFalse(pila.estaVacia());
    }

    /**
     * Se obtiene el largo de una pila vacia.
     */
    @Test
    void getLargo_pilaEstaVacia_retorna0() {
        Pila pila = obtenerImplementacion();

        assertEquals(0, pila.getLargo());
    }

    /**
     * Se obtiene el largo de una pila con dos elementos.
     */
    @Test
    void getLargo_pilaCon2Elementos_retorna2() {
        Pila pila = obtenerImplementacion();
        pila.push(1);
        pila.push(2);

        assertEquals(2, pila.getLargo());
    }

    /**
     * Se pregunta si una pila vacia esta vacia.
     */
    @Test
    void estaVacia_pilaSinElementos_returnaTrue() {
        Pila pila = obtenerImplementacion();

        assertTrue(pila.estaVacia());
    }

    /**
     * Se pregunta si una pila con elementos esta vacia.
     */
    @Test
    void estaVacia_pilaConElementos_returnaFalse() {
        Pila pila = obtenerImplementacion();
        pila.push(1);

        assertFalse(pila.estaVacia());
    }
}
