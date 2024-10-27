package sgp;

import Usuarios.ControladorUsuario;
import java.util.Scanner;
import java.util.InputMismatchException;

public class SGP {

    public static void main(String[] args) {
        // Inicializa el escáner para entrada de usuario y el controlador de usuario
        Scanner scanner = new Scanner(System.in);
        ControladorUsuario controladorUsuario = new ControladorUsuario();
        boolean sessionActive = false;  // Indica si hay una sesión activa

        // Bucle principal para el menú de inicio de sesión
        while (true) {
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer de entrada

                switch (option) {
                    case 1:
                        // Intenta iniciar sesión y actualiza el estado de sesión
                        sessionActive = controladorUsuario.iniciarSesion(scanner);
                        break;
                    case 2:
                        System.out.println("Saliendo...");
                        return;  // Termina la ejecución del programa
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpia el buffer en caso de entrada no numérica
            }

            // Bucle para el menú principal cuando la sesión está activa
            while (sessionActive) {
                System.out.println("Menú Principal:");
                System.out.println("1. Gestión Usuario");
                System.out.println("2. Gestión Proyectos");
                System.out.println("3. Gestión Tareas");
                System.out.println("4. Gestión Incidencias");
                System.out.println("5. Gestión Reportes");
                System.out.println("6. Cerrar Sesión");
                System.out.print("Seleccione una opción: ");
                try {
                    int mainOption = scanner.nextInt();
                    scanner.nextLine(); // Limpia el buffer de entrada

                    switch (mainOption) {
                        case 1:
                            // Llama al método para gestionar usuarios
                            controladorUsuario.gestionUsuario(scanner);
                            break;
                        case 2:
                            // Llama al método para gestionar proyectos
                            controladorUsuario.gestionProyectos(scanner);
                            break;
                        case 3:
                            // Llama al método para gestionar tareas
                            controladorUsuario.gestionTareas(scanner);
                            break;
                        case 4:
                            // Llama al método para gestionar incidencias
                            controladorUsuario.gestionIncidencias(scanner);
                            break;
                        case 5:
                            // Llama al método para gestionar reportes
                            controladorUsuario.gestionReportes(scanner);
                            break;
                        case 6:
                            // Finaliza la sesión actual
                            sessionActive = false;
                            System.out.println("Sesión cerrada.");
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                    scanner.nextLine(); // Limpia el buffer en caso de entrada no numérica
                }
            }
        }
    }
}
