package ar.edu.utn.frbb.tup.presentation.input;

import ar.edu.utn.frbb.tup.model.Banco;
import ar.edu.utn.frbb.tup.model.Cliente;
import ar.edu.utn.frbb.tup.model.Cuenta;
import ar.edu.utn.frbb.tup.persistence.ClienteDao;
import ar.edu.utn.frbb.tup.persistence.CuentaDao;
import ar.edu.utn.frbb.tup.service.ClienteService;
import ar.edu.utn.frbb.tup.service.CuentaService;

public class MenuInputProcessor extends BaseInputProcessor{

    // Crear instancias de ClienteDao y CuentaDao
    ClienteDao clienteDao = new ClienteDao();
    CuentaDao cuentaDao = new CuentaDao();

    // Crear instancias de ClienteService y CuentaService con los DAOs correspondientes
    ClienteService clienteService = new ClienteService(clienteDao);
    CuentaService cuentaService = new CuentaService();

    ClienteInputProcessor clienteInputProcessor = new ClienteInputProcessor();
    CuentaInputProcessor cuentaInputProcessor = new CuentaInputProcessor( new CuentaService(), new ClienteService());
    boolean exit = false;

    public void renderMenu(Banco banco) {

        while (!exit) {
            System.out.println("Bienveido a la aplicación de Banco!");
            System.out.println("1. Crear un nuevo Cliente");
            System.out.println("2. Crear una nueva Cuenta");
            System.out.println("3. Generar un movimiento");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opción (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    clienteInputProcessor.altaCliente();
                    break;
            case 2:
                    cuentaInputProcessor.altaCuenta();
                    break;
//            case 3:
//                performTransaction();
//                break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor seleccione 1-4.");
            }
            clearScreen();
        }
    }
}
