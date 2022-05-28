package Dominio;

import java.util.Comparator;

public class ComparadorPasajerosPorCedula implements Comparator<Pasajero> {
    @Override
    public int compare(Pasajero o1, Pasajero o2) {
        return o1.getCedula().compareTo(o2.getCedula());
    }
}
