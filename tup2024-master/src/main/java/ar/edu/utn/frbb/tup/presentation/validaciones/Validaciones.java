package ar.edu.utn.frbb.tup.presentation.validaciones;

import ar.edu.utn.frbb.tup.service.exception.DniErroneoException;

public class Validaciones {
    public static void validarDni(long dni) {
        try {
            if (dni < 1000000 || dni > 99999999) {
                throw new DniErroneoException("Invalid DNI");
            }

        } catch (DniErroneoException e) {
            System.out.println(e.getMessage());
        }
    }
}
