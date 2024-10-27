package Tareas;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ControladorTarea {

    // Lista para almacenar las tareas
    private ArrayList<Tarea> tareas = new ArrayList<>();

    public ControladorTarea() {
        // Inicializa el controlador con algunas tareas simuladas
        tareas.add(new Tarea("1", "Agregar reporte de solicitudes de Misoprostol", "Pendiente", "1", "1"));
        tareas.add(new Tarea("2", "Registrar firma automática para auditoría", "En progreso", "1", "2"));
    }

    public void gestionTareas(Scanner scanner) {
        System.out.println("Gestión de Tareas:");
        System.out.println("1. Crear Tarea");
        System.out.println("2. Modificar Tarea");
        System.out.println("3. Eliminar Tarea");
        System.out.println("4. Asignar Tarea");
        System.out.println("5. Eliminar Asignación de Tarea");
        System.out.println("6. Verificar Estado de Tarea");
        System.out.println("7. Buscar Tareas por Proyecto");
        System.out.println("8. Buscar Tareas por Usuario");
        System.out.println("9. Marcar Tarea como Completa");
        System.out.print("Seleccione una opción: ");
        try {
            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            // Ejecuta la opción seleccionada por el usuario
            switch (option) {
                case 1:
                    crearTarea(scanner);
                    break;
                case 2:
                    modificarTarea(scanner);
                    break;
                case 3:
                    eliminarTarea(scanner);
                    break;
                case 4:
                    asignarTarea(scanner);
                    break;
                case 5:
                    eliminarAsignarTarea(scanner);
                    break;
                case 6:
                    verificarEstadoTarea(scanner);
                    break;
                case 7:
                    buscarTareasPorProyecto(scanner);
                    break;
                case 8:
                    buscarTareasPorUsuario(scanner);
                    break;
                case 9:
                    marcarTareaCompleta(scanner);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.nextLine(); // Limpiar el buffer en caso de error
        }
    }

    public void crearTarea(Scanner scanner) {
        // Solicita datos para crear una nueva tarea y la agrega a la lista
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Estado: ");
        String estado = scanner.nextLine();
        System.out.print("ID del proyecto: ");
        String idProyecto = scanner.nextLine();
        System.out.print("ID del usuario: ");
        String idUsuario = scanner.nextLine();
        tareas.add(new Tarea(String.valueOf(tareas.size() + 1), nombre, estado, idProyecto, idUsuario));
        System.out.println("Tarea creada exitosamente.");
    }

    public void modificarTarea(Scanner scanner) {
        // Modifica los datos de una tarea existente (si se encuentra)
        System.out.print("ID de la tarea: ");
        String idTarea = scanner.nextLine();
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(idTarea)) {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Estado: ");
                String estado = scanner.nextLine();
                tarea.setNombre(nombre);
                tarea.setEstado(estado);
                System.out.println("Tarea modificada exitosamente.");
                return;
            }
        }
        System.out.println("Tarea no encontrada.");
    }

    public void eliminarTarea(Scanner scanner) {
        // Elimina una tarea específica de la lista
        System.out.print("ID de la tarea: ");
        String idTarea = scanner.nextLine();
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId().equals(idTarea)) {
                tareas.remove(i);
                System.out.println("Tarea eliminada exitosamente.");
                return;
            }
        }
        System.out.println("Tarea no encontrada.");
    }

    public void asignarTarea(Scanner scanner) {
        // Asigna una tarea a un usuario específico
        System.out.print("ID de la tarea: ");
        String idTarea = scanner.nextLine();
        System.out.print("ID del usuario: ");
        String idUsuario = scanner.nextLine();
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(idTarea)) {
                tarea.setIdUsuario(idUsuario);
                System.out.println("Tarea asignada exitosamente.");
                return;
            }
        }
        System.out.println("Tarea no encontrada.");
    }

    public void eliminarAsignarTarea(Scanner scanner) {
        // Elimina la asignación de una tarea a un usuario específico
        System.out.print("ID de la tarea: ");
        String idTarea = scanner.nextLine();
        System.out.print("ID del usuario: ");
        String idUsuario = scanner.nextLine();
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(idTarea) && tarea.getIdUsuario().equals(idUsuario)) {
                tarea.setIdUsuario(null);
                System.out.println("Asignación de tarea eliminada exitosamente.");
                return;
            }
        }
        System.out.println("Asignación de tarea no encontrada.");
    }

    public void verificarEstadoTarea(Scanner scanner) {
        // Verifica y muestra el estado de una tarea específica
        System.out.print("ID de la tarea: ");
        String idTarea = scanner.nextLine();
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(idTarea)) {
                System.out.println("Estado de la tarea: " + tarea.getEstado());
                return;
            }
        }
        System.out.println("Tarea no encontrada.");
    }

    public void buscarTareasPorProyecto(Scanner scanner) {
        // Muestra tareas asociadas a un proyecto específico
        System.out.print("ID del proyecto: ");
        String idProyecto = scanner.nextLine();
        for (Tarea tarea : tareas) {
            if (tarea.getIdProyecto().equals(idProyecto)) {
                System.out.println("Tarea encontrada:");
                System.out.println("Nombre: " + tarea.getNombre());
                System.out.println("Estado: " + tarea.getEstado());
            }
        }
    }

    public void buscarTareasPorUsuario(Scanner scanner) {
        // Muestra tareas asignadas a un usuario específico
        System.out.print("ID del usuario: ");
        String idUsuario = scanner.nextLine();
        for (Tarea tarea : tareas) {
            if (tarea.getIdUsuario().equals(idUsuario)) {
                System.out.println("Tarea encontrada:");
                System.out.println("Nombre: " + tarea.getNombre());
                System.out.println("Estado: " + tarea.getEstado());
            }
        }
    }

    public void marcarTareaCompleta(Scanner scanner) {
        // Cambia el estado de una tarea a "Completa"
        System.out.print("ID de la tarea: ");
        String idTarea = scanner.nextLine();
        for (Tarea tarea : tareas) {
            if (tarea.getId().equals(idTarea)) {
                tarea.setEstado("Completa");
                System.out.println("Tarea marcada como completa.");
                return;
            }
        }
        System.out.println("Tarea no encontrada.");
    }
}
