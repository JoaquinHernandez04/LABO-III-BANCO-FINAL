package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
public class CuentaService {
    
    CuentaDao cuentaDao = new CuentaDao();
    
    public void darDeAltaCuenta(Cuenta cuenta) throws CuentaAlreadyExistsException { //Devuelve un error si ya existe una cuenta con el mismo N° de Cuenta
        if (cuentaDao.find(cuenta.getNumeroCuenta()) != null) {
            throw new CuentaAlreadyExistsException("Ya existe una cuenta con N° de Cuenta " + cuenta.getNumeroCuenta());
        }
    }
}
