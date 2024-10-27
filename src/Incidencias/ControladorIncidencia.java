package Incidencias;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ControladorIncidencia {
    // Lista que almacena las incidencias
    private ArrayList<Incidencia> incidencias = new ArrayList<>();

    public ControladorIncidencia() {
        // Simulación de incidencias iniciales
        incidencias.add(new Incidencia("1", "1", "Error en nombre del proyecto"));
        incidencias.add(new Incidencia("2", "2", "Falta de recursos para proyecto"));
    }

    public void gestionIncidencias(Scanner scanner) {
        System.out.println("Gestión de Incidencias:");
        System.out.println("1. Registrar Incidencia");
        System.out.println("2. Eliminar Incidencia");
        System.out.println("3. Buscar Incidencia por Proyecto");
        System.out.print("Seleccione una opción: ");
        
        try {
            int option = scanner.nextInt(); // Lee la opción del usuario
            scanner.nextLine(); // Limpiar el buffer

            // Procesa la opción seleccionada
            switch (option) {
                case 1:
                    registrarIncidencia(scanner); // Llama al método para registrar una incidencia
                    break;
                case 2:
                    eliminarIncidencia(scanner); // Llama al método para eliminar una incidencia
                    break;
                case 3:
                    buscarIncidenciaPorProyecto(scanner); // Llama al método para buscar una incidencia
                    break;
                default:
                    System.out.println("Opción no válida."); // Manejo de opción no válida
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido."); // Manejo de entrada no válida
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    public void registrarIncidencia(Scanner scanner) {
        // Solicita al usuario el ID del proyecto y la descripción de la incidencia
        System.out.print("ID del proyecto: ");
        String idProyecto = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        
        // Agrega la nueva incidencia a la lista
        incidencias.add(new Incidencia(String.valueOf(incidencias.size() + 1), idProyecto, descripcion));
        System.out.println("Incidencia registrada exitosamente.");
    }

    public void eliminarIncidencia(Scanner scanner) {
        // Solicita el ID de la incidencia a eliminar
        System.out.print("ID de la incidencia: ");
        String idIncidencia = scanner.nextLine();
        
        // Busca y elimina la incidencia con el ID proporcionado
        for (int i = 0; i < incidencias.size(); i++) {
            if (incidencias.get(i).getId().equals(idIncidencia)) {
                incidencias.remove(i);
                System.out.println("Incidencia eliminada exitosamente.");
                return; // Sale del método tras la eliminación
            }
        }
        System.out.println("Incidencia no encontrada."); // Mensaje si no se encuentra la incidencia
    }

    public void buscarIncidenciaPorProyecto(Scanner scanner) {
        // Solicita el ID del proyecto para buscar incidencias asociadas
        System.out.print("ID del proyecto: ");
        String idProyecto = scanner.nextLine();
        
        // Busca incidencias relacionadas con el ID del proyecto
        for (Incidencia incidencia : incidencias) {
            if (incidencia.getIdProyecto().equals(idProyecto)) {
                System.out.println("Incidencia encontrada:");
                System.out.println("Descripción: " + incidencia.getDescripcion());
            }
        }
    }
}
