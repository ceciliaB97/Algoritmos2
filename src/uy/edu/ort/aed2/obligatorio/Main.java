package uy.edu.ort.aed2.obligatorio;

public class Main {
    private static final int D = 0; // disponible
    private static final int O = 1; // ocupado
    private static final int N = 2; // no disponible

    public static void main(String[] args) {

        SistemaImp sistema = new SistemaImp();
        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{O, O, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*B*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*C*/{O, D, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*D*/{O, D, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*E*/{N, N, N, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D},
                /*F*/{O, O, O, /* - */ D, D, D, D, /* - */ D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D, D}
        };

        int cantidad = 2;
        final Retorno respuesta = sistema.consultaDisponibilidad(asientos, 2, Sistema.Clase.Primera);

        System.out.println(respuesta.valorString);
        System.out.println(respuesta.valorEntero);

    }
}
