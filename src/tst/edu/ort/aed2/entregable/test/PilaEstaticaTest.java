package tst.edu.ort.aed2.entregable.test;

import org.junit.jupiter.api.Test;
import tst.edu.ort.aed2.entregable.Pila;
import tst.edu.ort.aed2.entregable.PilaEstatica;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PilaEstaticaTest extends PilaTest {
    private static final int CANTIDAD_POR_DEFECTO = 10;

    @Override
    Pila obtenerImplementacion() {
        return new PilaEstatica(CANTIDAD_POR_DEFECTO);
    }

    /**
     * Se pregunta si una pila vacia esta llena.
     */
    @Test
    void estaLlena_pilaVacia_retornaFalse() {
        Pila pila = obtenerImplementacion();

        assertFalse(pila.estaLlena());
    }

    /**
     * Se pregunta si una pila llena esta llena.
     */
    @Test
    void estaLlena_pilaLlena_retornaTrue() {
        Pila pila = obtenerImplementacion();
        for (int i = 0; i < CANTIDAD_POR_DEFECTO; i++) {
            pila.push(i);
        }

        assertTrue(pila.estaLlena());
    }

    /**
     * Se pregunta si una pila que no esta llena esta llena.
     */
    @Test
    void estaLlena_pilaNoLlena_retornaFalse() {
        Pila pila = obtenerImplementacion();
        for (int i = 0; i < CANTIDAD_POR_DEFECTO - 1; i++) {
            pila.push(i);
        }

        assertFalse(pila.estaLlena());
    }

    @Test
    public void chequearPilaLlena() {
        Pila p = this.obtenerImplementacion();
        for (int i = 0; i < 10; i++) {
            p.push(1);
        }
        assertTrue(p.estaLlena());
    }

}