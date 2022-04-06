package Dominio;

public class Pasajero {
    private String cedula;
    private String nombre;
    private String telefono;
    private Categoria categoria;

    public Pasajero(String cedula, String nombre, String telefono, Categoria categoria) {
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

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString(){
        return cedula + "; " + nombre + "; " + telefono + "; " + categoria;
    }
}
