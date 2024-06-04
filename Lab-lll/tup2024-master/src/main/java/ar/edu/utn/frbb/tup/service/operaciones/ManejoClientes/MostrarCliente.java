package ar.edu.utn.frbb.tup.service.operaciones.ManejoClientes;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class MostrarCliente {

    private static final String NOMBRE_ARCHIVO = "C:\\Users\\Uriel\\Desktop\\Lab-lll\\tup2024-master\\src\\main\\java\\ar\\edu\\utn\\frbb\\tup\\persistence\\DataBase\\Clientes.txt";

    public static void mostrarCliente(String dni) {
        List<String> clientes = new ArrayList<>();

        try (BufferedReader lector = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length > 1 && campos[1].trim().equals(dni.trim())) {
                    clientes.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (clientes.isEmpty()) {
            System.out.println("No se encontraron clientes con el DNI: " + dni);
        } else {
            System.out.println("Clientes con el DNI: " + dni);
            for (String cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    
}
