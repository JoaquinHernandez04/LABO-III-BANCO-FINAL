package ar.edu.utn.frbb.tup.presentation.input;

import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.TipoCuenta;
import ar.edu.utn.frbb.tup.model.exception.CuentaAlreadyExistsException;
import ar.edu.utn.frbb.tup.service.CuentaService;
import ar.edu.utn.frbb.tup.service.ClienteService;

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
        long dniCliente = scanner.nextLong();
        scanner.nextLine(); // Consumir la nueva línea

        Cliente cliente = clienteService.buscarClientePorDni(dniCliente);
        if (cliente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

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
        scanner.nextLine();
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


