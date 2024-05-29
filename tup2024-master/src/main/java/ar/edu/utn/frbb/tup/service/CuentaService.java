package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.exception.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.DataBase.SummitCliente;
import ar.edu.utn.frbb.tup.persistence.DataBase.SummitCuenta;

public class CuentaService {
    public void darDeAltaCuenta(Cuenta cuenta) throws CuentaAlreadyExistsException {

        SummitCuenta.escribirEnArchivo(cuenta);
    }

  }
