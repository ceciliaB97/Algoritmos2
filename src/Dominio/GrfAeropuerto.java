package Dominio;

public class GrfAeropuerto {
    private int maxAeropuerto;
    private Aeropuerto[]vertice;
    private Conexion[][] matConexion;

    public GrfAeropuerto(int maxAeropuerto) {
        this.maxAeropuerto = maxAeropuerto;
    }
}
