package uy.edu.ort.aed2.obligatorio;

public class Main {
    private static final int D = 0; // disponible
    private static final int O = 1; // ocupado
    private static final int N = 2; // no disponible

    public static void main(String[] args) {

        SistemaImp sistema = new SistemaImp();
        sistema.inicializarSistema(10);
        sistema.registrarPasajero("3.222.422-2", "Pepe", "551115-055", Sistema.Categoria.A);
        sistema.registrarPasajero("222.422-2", "Pepe", "551115-055", Sistema.Categoria.A);
        sistema.registrarPasajero("1.211.222-2", "Pepe", "551115-055", Sistema.Categoria.A);
        sistema.registrarPasajero("522.322-2", "Maria", "551115-055", Sistema.Categoria.C);
        sistema.registrarPasajero("5.222.522-2", "Gloria", "551115-055", Sistema.Categoria.A);
        sistema.registrarPasajero("6.222.522-2", "Victtoria", "551115-055", Sistema.Categoria.A);
        sistema.registrarPasajero("7.222.522-2", "roberto", "03031452", Sistema.Categoria.D);

        Retorno ret = sistema.buscarPasajero("522.322-2");
        System.out.println(ret.valorString);
        System.out.println(ret.valorEntero);

    }
}
