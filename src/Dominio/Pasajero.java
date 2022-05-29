package Dominio;

import uy.edu.ort.aed2.obligatorio.Sistema;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean validarCedula(String cedula) {
        boolean resultado = false;
        Pattern cedulaConOchoCifras = Pattern.compile("^[1-9][\\.][\\d][\\d][\\d][\\.][\\d][\\d][\\d][\\-][\\d]$");
        Pattern cedulaConSieteCifras = Pattern.compile("^[1-9][\\d][\\d][\\.][\\d][\\d][\\d][\\-][\\d]$");
        Matcher comparadorOchoCifras = cedulaConOchoCifras.matcher(cedula);
        Matcher comparadorSieteCifras = cedulaConSieteCifras.matcher(cedula);
        if (comparadorOchoCifras.matches() || comparadorSieteCifras.matches()) {
            resultado = true;
        }
        return resultado;
    }

    public static boolean datosValidos(String cedula, String nombre, String telefono, Sistema.Categoria categoria) {
        boolean categoriaInvalida = categoria == null || categoria.getTexto().isBlank() || categoria.getTexto().isEmpty();
        boolean nombreInvalido = nombre == null || nombre.isEmpty() || nombre.isBlank();
        boolean telefonoInvalido = telefono == null || telefono.isEmpty() || telefono.isBlank();
        boolean cedulaInvalida = cedula == null || cedula.isEmpty() || cedula.isBlank();
        if (categoriaInvalida || nombreInvalido || telefonoInvalido || cedulaInvalida) {
            return false;
        }

        return true;
    }


    @Override
    public int compareTo(Pasajero o) {
        int cedula1 = Integer.parseInt(this.getCedula().replace(".", "").replace("-", ""));
        int cedula2 = Integer.parseInt(o.getCedula().replace(".", "").replace("-", ""));

        if (cedula1 > cedula2) {
            return 1;
        } else if (cedula1 < cedula2) {
            return -1;
        } else {
            return 0;
        }
    }
}
