package Dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pasajero {
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
    public String toString(){
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

    public static boolean validarCedula(String cedula){
        boolean resultado = false;
        Pattern cedulaConOchoCifras = Pattern.compile("^[\\d][\\.][\\d][\\d][\\d][\\.][\\d][\\d][\\d][\\-][\\d]$");
        Pattern cedulaConSieteCifras = Pattern.compile("^[\\d][\\d][\\d][\\.][\\d][\\d][\\d][\\-][\\d]$");
        Matcher comparadorOchoCifras = cedulaConOchoCifras.matcher(cedula);
        Matcher comparadorSieteCifras = cedulaConSieteCifras.matcher(cedula);
        if(comparadorOchoCifras.matches()||comparadorSieteCifras.matches()){
            resultado= true;
        }
        return resultado;
    }
    public boolean datosValidos(){
        boolean categoriaInvalida=this.categoria==null || this.categoria.isBlank() || this.categoria.isEmpty();
        boolean nombreInvalido=this.nombre==null||this.nombre.isEmpty() || this.nombre.isBlank();
        boolean telefonoInvalido=this.telefono==null||this.telefono.isEmpty() || this.telefono.isBlank();
        boolean cedulaInvalida=this.cedula==null||this.cedula.isEmpty() || this.cedula.isBlank();
        if(categoriaInvalida||nombreInvalido||telefonoInvalido||cedulaInvalida){
            return false;
        }

        return true;
    }
}
