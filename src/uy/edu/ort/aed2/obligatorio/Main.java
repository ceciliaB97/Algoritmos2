package uy.edu.ort.aed2.obligatorio;

public class Main {
    private static final int D = 0; // disponible
    private static final int O = 1; // ocupado
    private static final int N = 2; // no disponible

    public static void main(String[] args) {

        SistemaImp imp = new SistemaImp();

        final int[][] asientos = {
                //    1  2  3          4  5  6  7          8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
                //    P  P  P  /* - */ E  E  E  E  /* - */ T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T  T
                /*A*/{D, D, D, /* - */ O, O, D, O, /* - */ O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*B*/{N, N, N, /* - */ D, O, D, O, /* - */ O, D, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O, O},
                /*C*/{D, D, D, /* - */ D, O, D, O, /* - */ O, D, O, O, O, O, O, O, O, D, O, O, O, O, O, O, O, O, O},
                /*D*/{D, D, D, /* - */ D, O, D, O, /* - */ O, D, O, O, O, O, O, O, O, D, O, O, O, O, O, O, O, O, O},
                /*E*/{N, N, N, /* - */ D, O, O, O, /* - */ O, D, O, O, O, O, O, O, O, D, O, O, O, O, O, O, O, O, O},
                /*F*/{D, D, D, /* - */ O, O, O, O, /* - */ O, O, O, O, O, O, O, O, O, D, O, O, O, O, O, O, O, O, O}
        };
        imp.consultaDisponibilidad(asientos, 4, Sistema.Clase.Ejecutiva);
    }
}
