package Reporte;

import java.util.ArrayList; 
import java.util.Scanner; 
import java.util.InputMismatchException; 

public class ControladorReporte {
    private ArrayList<Reporte> reportes = new ArrayList<>(); // Lista para almacenar los reportes

    public ControladorReporte() {
        // Simulación de reportes iniciales
        reportes.add(new Reporte("1", "1", "Asignación de recursos exitosa"));
        reportes.add(new Reporte("2", "2", "Cambiar medicamentos vencidos"));
    }

    public void gestionReportes(Scanner scanner) {
        System.out.println("Gestión de Reportes:");
        System.out.println("1. Generar Reporte");
        System.out.println("2. Eliminar Reporte");
        System.out.println("3. Buscar Reporte");
        System.out.print("Seleccione una opción: ");
        try {
            int option = scanner.nextInt(); // Lee la opción elegida por el usuario
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    generarReporte(scanner); // Llama a la función para generar un nuevo reporte
                    break;
                case 2:
                    eliminarReporte(scanner); // Llama a la función para eliminar un reporte
                    break;
                case 3:
                    buscarReporte(scanner); // Llama a la función para buscar un reporte
                    break;
                default:
                    System.out.println("Opción no válida."); // Mensaje para opción no válida
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido."); // Manejo de errores para entrada no válida
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    public void generarReporte(Scanner scanner) {
        System.out.print("ID del proyecto: ");
        String idProyecto = scanner.nextLine(); // Lee el ID del proyecto
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine(); // Lee la descripción del reporte
        reportes.add(new Reporte(String.valueOf(reportes.size() + 1), idProyecto, descripcion)); // Crea y agrega el nuevo reporte
        System.out.println("Reporte generado exitosamente."); // Mensaje de éxito
    }

    public void eliminarReporte(Scanner scanner) {
        System.out.print("ID del reporte: ");
        String idReporte = scanner.nextLine(); // Lee el ID del reporte a eliminar
        for (int i = 0; i < reportes.size(); i++) { // Itera sobre la lista de reportes
            if (reportes.get(i).getId().equals(idReporte)) { // Busca el reporte por ID
                reportes.remove(i); // Elimina el reporte encontrado
                System.out.println("Reporte eliminado exitosamente."); // Mensaje de éxito
                return;
            }
        }
        System.out.println("Reporte no encontrado."); // Mensaje si no se encuentra el reporte
    }

    public void buscarReporte(Scanner scanner) {
        System.out.print("ID del reporte: ");
        String idReporte = scanner.nextLine(); // Lee el ID del reporte a buscar
        for (Reporte reporte : reportes) { // Itera sobre la lista de reportes
            if (reporte.getId().equals(idReporte)) { // Busca el reporte por ID
                System.out.println("Reporte encontrado:");
                System.out.println("Descripción: " + reporte.getDescripcion()); // Muestra la descripción del reporte
                return;
            }
        }
        System.out.println("Reporte no encontrado."); // Mensaje si no se encuentra el reporte
    }
}
