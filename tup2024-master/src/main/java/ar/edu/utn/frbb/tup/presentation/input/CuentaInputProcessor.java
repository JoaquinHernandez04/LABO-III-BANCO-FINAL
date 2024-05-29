package ar.edu.utn.frbb.tup.presentation.input;

import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.service.ClienteService;
import ar.edu.utn.frbb.tup.service.CuentaService;
import ar.edu.utn.frbb.tup.persistence.DataBase.SummitCliente;
import ar.edu.utn.frbb.tup.persistence.DataBase.SummitCuenta;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CuentaInputProcessor extends BaseInputProcessor {
    private final CuentaService cuentaService;
    private final ClienteService clienteService;

    public CuentaInputProcessor(CuentaService cuentaService, ClienteService clienteService) {
        this.cuentaService = cuentaService;
        this.clienteService = clienteService;
    }

    public void altaCuenta() {
        Scanner scanner = new Scanner(System.in);
        Cuenta cuenta = new Cuenta();
        clearScreen();

        // Buscar cliente por DNI para vincular la cuenta
        System.out.println("Ingrese el DNI del cliente para vincular la cuenta:");
        long dni = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea

        Cliente cliente = SummitCuenta.buscarClientePorDni(dni);

        if (cliente == null) {
            System.out.println("No se encontró un cliente con el DNI ingresado.");
            return;
        }

        // Asociar el cliente a la cuenta
        cuenta.setTitular(cliente);

        System.out.println("Ingrese el nombre de la cuenta:");
        String nombreCuenta = scanner.nextLine();
        cuenta.setNombre(nombreCuenta);

        System.out.println("Ingrese el tipo de cuenta (CORRIENTE, AHORRO):");
        String tipoCuentaStr = scanner.nextLine().toUpperCase();
        TipoCuenta tipoCuenta = TipoCuenta.valueOf(tipoCuentaStr);
        cuenta.setTipoCuenta(tipoCuenta);

        System.out.println("Ingrese el saldo inicial de la cuenta:");
        int saldoInicial = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        cuenta.setBalance(saldoInicial);

        System.out.println("Ingrese la moneda de la cuenta (ARS, USD, EUR):");
        String moneda = scanner.nextLine();
        cuenta.setMoneda(moneda);

        cuenta.setFechaCreacion(LocalDateTime.now());

        try {
            cuentaService.darDeAltaCuenta(cuenta);
            
            System.out.println("Cuenta registrada con éxito.");
        } catch (CuentaAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        clearScreen();
    }
}




