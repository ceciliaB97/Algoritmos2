package tst.edu.ort.aed2.entregable.test;

import org.junit.jupiter.api.Test;
import tst.edu.ort.aed2.entregable.Pila;
import tst.edu.ort.aed2.entregable.PilaDinamica;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PilaDinamicaTest extends PilaTest {
    @Override
    Pila obtenerImplementacion() {
        return new PilaDinamica();
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
     * Se pregunta si una pila con elementos esta llena.
     */
    @Test
    void estaLlena_pilaConElementos_retornaFalse() {
        Pila pila = obtenerImplementacion();
        for (int i = 0; i < 1000; i++) {
            pila.push(i);
        }

        assertFalse(pila.estaLlena());
    }
}