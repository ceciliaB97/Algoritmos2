package Dominio;

public class GrfAeropuerto {
    private int maxAeropuerto;
    private Aeropuerto[] vertice;
    private Conexion[][] matConexion;

    public GrfAeropuerto(int maxAeropuerto) {
        this.maxAeropuerto = maxAeropuerto;
        this.vertice = new Aeropuerto[maxAeropuerto];
        this.matConexion = new Conexion[maxAeropuerto][maxAeropuerto];
    }

    public boolean estaLleno() {
        return this.vertice.length == this.maxAeropuerto;
    }

    public void agregarAeropuerto(Aeropuerto a) {
        int posLibre = obtenerPosLibre();
        this.vertice[posLibre] = a;
    }

    private int obtenerPosLibre() {
        int pos = 0;
        while (pos < this.maxAeropuerto) {
            if (this.vertice[pos] == null) {
                return pos;
            }
            pos++;
        }
        return -1;
    }

    public boolean existeAeropuerto(String codigo) {
        int i;
        for (i = 0; i < vertice.length; i++) {
            if (vertice[i].getCodigo().equals(codigo)) {
                return true;
            }
        }

        return false;
    }
}
