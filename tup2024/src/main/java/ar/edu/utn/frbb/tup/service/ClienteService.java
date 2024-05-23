package ar.edu.utn.frbb.tup.service;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.exception.ClienteAlreadyExistsException;
import ar.edu.utn.frbb.tup.model.exception.TipoCuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;

public class ClienteService {

    ClienteDao clienteDao;

    public void darDeAltaCliente(Cliente cliente) throws ClienteAlreadyExistsException {
        if (clienteDao.findByDni(cliente.getDni()) != null) {
            throw new ClienteAlreadyExistsException("Ya existe un cliente con DNI " + cliente.getDni());
        }

        if (cliente.getEdad() < 18) {
            throw new IllegalArgumentException("El cliente debe ser mayor a 18 años");
        }

        clienteDao.save(cliente);
    }
    
    public ClienteService() {
        this.clienteDao = new ClienteDao();
    }
    public Cliente buscarClientePorDni(long dni) {
        return clienteDao.findByDni(dni);
    }

    public ClienteService(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public void agregarCuenta(Cuenta cuenta, Cliente cliente) throws TipoCuentaAlreadyExistsException {
        if (cliente.tieneCuenta(cuenta.getTipoCuenta(), cuenta.getMoneda())) {
            throw new TipoCuentaAlreadyExistsException("El cliente ya posee una cuenta de ese tipo y moneda");
        }
        cliente.addCuenta(cuenta);
    }
}
