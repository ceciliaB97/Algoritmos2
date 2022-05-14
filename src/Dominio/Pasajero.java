package Dominio;

import java.util.Objects;

/**
 * ARBOL BINARIO DE BUSQUEDA
 * ORDENADO POR CEDULA
 */
public class Pasajero implements Comparable<Pasajero> {
    private String cedula;
    private String nombre;
    private String telefono;
    private String categoria;

    public Pasajero(String cedula, String nombre, String telefono, String categoria) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.categoria = categoria;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return cedula + "; " + nombre + "; " + telefono + "; " + categoria + " | ";
    }

    @Override
    public int compareTo(Pasajero o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasajero pasajero = (Pasajero) o;
        return Objects.equals(cedula, pasajero.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }
}
