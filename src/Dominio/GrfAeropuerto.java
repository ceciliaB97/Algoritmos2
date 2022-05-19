package Dominio;

public class GrfAeropuerto {
    private int maxAeropuerto;
    private Aeropuerto[] vertices;
    private Conexion[][] matConexion;

    public GrfAeropuerto(int maxAeropuerto) {
        this.maxAeropuerto = maxAeropuerto;
        this.vertices = new Aeropuerto[maxAeropuerto];
        this.matConexion = new Conexion[maxAeropuerto][maxAeropuerto];
    }

    public boolean estaLleno() {
        return this.vertices.length == this.maxAeropuerto;
    }

    public boolean esVacio() {
        return this.vertices.length == 0;
    }

    public void agregarAeropuerto(Aeropuerto a) {
        int posLibre = obtenerPosLibre();
        this.vertices[posLibre] = a;
    }

    private int obtenerPosLibre() {
        int pos = 0;
        while (pos < this.maxAeropuerto) {
            if (this.vertices[pos] == null) {
                return pos;
            }
            pos++;
        }
        return -1;
    }

    public boolean existeAeropuerto(String codigo) {
        int i;
        for (i = 0; i < vertices.length; i++) {
            if (vertices[i].getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    // existeVertice(origen) && existeVertice(destino)
    public boolean existeConexion(String origen, String destino) {
        boolean origenEncontrado = false;
        for(int i = 0; i < matConexion.length; i++){
            if(vertices[i].equals(origen)){
                origenEncontrado = true;
            }
            for(int j = 0; j < matConexion.length; j++){
                if(vertices[j].equals(destino) && origenEncontrado){
                    return matConexion[i][j].isExiste();
                }
            }
        }
        return false;
    }

    private int obtenerPos(Aeropuerto vert) {
        int pos = 0;
        while(pos < this.maxAeropuerto){
            if(this.vertices[pos].equals(vert)){
                return pos;
            }
            pos++;
        }
        return -1;
    }

    // PRE: existeVertice(origen) && existeVertice(destino) && !existeArista, grafo NO esta lleno
    public void agregarConexion(Aeropuerto origen, Aeropuerto destino, int kilometros) {
        int posOri = obtenerPos(origen);
        int posDes = obtenerPos(destino);
        matConexion[posOri][posDes].setExiste(true);
        matConexion[posOri][posDes].setKilometros(kilometros);
    }

    // PRE: existeVertice
    public void borrarAeropuerto(Aeropuerto vert) {
        int posEncontrada = this.obtenerPos(vert);
        this.vertices[posEncontrada] = null;
        //borrar en matriz
        for(int i = 0; i < this.vertices.length; i++){
            Conexion encontradaI = null;
            if(i == posEncontrada){
                encontradaI = this.matConexion[posEncontrada][posEncontrada];
                break;
            } else {
                encontradaI = this.matConexion[posEncontrada][i];
            }
            encontradaI.setExiste(false);
            for(int j = 0; j < this.vertices.length; j++){
                if(j == posEncontrada){
                    Conexion encontradaJ = this.matConexion[i][j];
                    encontradaJ.setExiste(false);
                }
            }
        }
    }
}
