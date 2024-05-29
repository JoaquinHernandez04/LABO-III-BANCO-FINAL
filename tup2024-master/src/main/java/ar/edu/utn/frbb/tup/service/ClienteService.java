package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.exception.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.DataBase.SummitCliente;

public class ClienteService {



    public void darDeAltaCliente(Cliente cliente) throws ClienteAlreadyExistsException {
        if (SummitCliente.findByDni(String.valueOf(cliente.getDni())) != null) {
            throw new ClienteAlreadyExistsException("Ya existe un cliente con DNI " + cliente.getDni());
        }

        if (cliente.getEdad() < 18) {
            throw new IllegalArgumentException("El cliente debe ser mayor a 18 años");
        }

        // Guardar cliente en la "base de datos" (archivo)
        SummitCliente.escribirEnArchivo(cliente);
    }

}

