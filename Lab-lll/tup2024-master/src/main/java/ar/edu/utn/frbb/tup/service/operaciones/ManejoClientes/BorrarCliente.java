package ar.edu.utn.frbb.tup.service.operaciones.ManejoClientes;
import java.io.*;
import java.nio.file.*;
import java.util.*;
public class BorrarCliente {
    private static final String NOMBRE_ARCHIVO = "C:\\Users\\Uriel\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Clientes.txt";

    public static void borrarCliente(String DNI) {
        List<String> Clientes = new ArrayList<>();
        boolean clienteEncontrado = false;

        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (!campos[0].equals(DNI)) {
                    Clientes.add(linea);
                } else {
                    clienteEncontrado = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (clienteEncontrado) {
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO))) {
                for (String cliente : Clientes) {
                    escritor.write(cliente);
                    escritor.newLine();
                }
                System.out.println("El cliente con DNI " + DNI + " ha sido eliminado.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El cliente con DNI " + DNI + " no existe.");
        }
    }
    
}
