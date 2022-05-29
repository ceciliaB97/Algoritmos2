package Dominio;

import java.util.Objects;

/**
 * GRAFO
 */
public class Aeropuerto implements Comparable<Aeropuerto> {
    private String codigo;
    private String nombre;

    public Aeropuerto(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString(){
        return codigo + ";" + nombre;
    }

    @Override
    public int compareTo(Aeropuerto o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aeropuerto that = (Aeropuerto) o;
        return codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
