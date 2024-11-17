package sgp;

import java.util.InputMismatchException; // Importa la excepción para manejar entradas no válidas.
import Usuarios.IUsuarioService; // Importa la interfaz para el servicio de usuarios.
import Usuarios.ControladorUsuario; // Importa la clase que implementa el servicio de usuarios.
import Tareas.ITareaService; // Importa la interfaz para el servicio de tareas.
import Tareas.ControladorTarea; // Importa la clase que implementa el servicio de tareas.
import Proyectos.IProyectoService; // Importa la interfaz para el servicio de proyectos.
import Proyectos.ControladorProyecto; // Importa la clase que implementa el servicio de proyectos.
import Reporte.IReporteService; // Importa la interfaz para el servicio de reportes.
import Reporte.ControladorReporte; // Importa la clase que implementa el servicio de reportes.
import Incidencias.IIncidenciaService; // Importa la interfaz para el servicio de incidencias.
import Incidencias.ControladorIncidencia; // Importa la clase que implementa el servicio de incidencias.
import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario.

public class SGP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Crea un objeto Scanner para leer la entrada del usuario.
        // Inicializa los controladores para cada servicio.
        IUsuarioService controladorUsuario = new ControladorUsuario();
        ITareaService controladorTarea = new ControladorTarea();
        IProyectoService controladorProyecto = new ControladorProyecto();
        IReporteService controladorReporte = new ControladorReporte();
        IIncidenciaService controladorIncidencia = new ControladorIncidencia();
        boolean sessionActive = false; // Variable para controlar el estado de la sesión.

        while (true) { // Bucle principal de la aplicación.
            System.out.println("1. Iniciar Sesión"); // Opción para iniciar sesión.
            System.out.println("2. Salir"); // Opción para salir de la aplicación.
            System.out.print("Seleccione una opción: ");
            try {
                int option = scanner.nextInt(); // Lee la opción seleccionada por el usuario.
                scanner.nextLine(); // Limpia el buffer del scanner.

                // Ejecuta la opción seleccionada.
                switch (option) {
                    case 1:
                        sessionActive = controladorUsuario.iniciarSesion(scanner); // Llama al método para iniciar sesión.
                        break;
                    case 2:
                        System.out.println("Saliendo..."); // Mensaje de salida.
                        return; // Sale de la aplicación.
                    default:
                        System.out.println("Opción no válida."); // Mensaje de error para opción no válida.
                        break;
                }
            } catch (InputMismatchException e) { // Manejo de excepciones para entradas no válidas.
                System.out.println("Por favor, ingrese un número válido."); // Mensaje de error.
                scanner.nextLine(); // Limpia el buffer del scanner.
            }

            // Bucle para el menú principal, que se ejecuta mientras la sesión esté activa.
            while (sessionActive) {
                System.out.println("Menú Principal:"); // Muestra el menú principal.
                System.out.println("1. Gestión Usuario"); // Opción para gestionar usuarios.
                System.out.println("2. Gestión Proyectos"); // Opción para gestionar proyectos.
                System.out.println("3. Gestión Tareas"); // Opción para gestionar tareas.
                System.out.println("4. Gestión Incidencias"); // Opción para gestionar incidencias.
                System.out.println("5. Gestión Reportes"); // Opción para gestionar reportes.
                System.out.println("6. Cerrar Sesión"); // Opción para cerrar sesión.
                System.out.print("Seleccione una opción: ");
                try {
                    int mainOption = scanner.nextInt(); // Lee la opción seleccionada por el usuario.
                    scanner.nextLine(); // Limpia el buffer del scanner.

                    // Ejecuta la opción seleccionada en el menú principal.
                    switch (mainOption) {
                        case 1:
                            controladorUsuario.gestionUsuario(scanner); // Llama al método para gestionar usuarios.
                            break;
                        case 2:
                            controladorProyecto.gestionProyectos(scanner); // Llama al método para gestionar proyectos.
                            break;
                        case 3:
                            controladorTarea.gestionTareas(scanner); // Llama al método para gestionar tareas.
                            break;
                        case 4:
                            controladorIncidencia.gestionIncidencias(scanner); // Llama al método para gestionar incidencias.
                            break;
                        case 5:
                            controladorReporte.gestionReportes(scanner); // Llama al método para gestionar reportes.
                            break;
                        case 6:
                            sessionActive = false;
                            System.out.println("Sesión cerrada."); // Mensaje de cierre de sesión.
                            break;
                        default:
                            System.out.println("Opción no válida."); // Mensaje de error para opción no válida.
                            break;
                    }
                } catch (InputMismatchException e) { // Manejo de excepciones para entradas no válidas en el menú principal.
                    System.out.println("Por favor, ingrese un número válido."); // Mensaje de error.
                    scanner.nextLine(); // Limpia el buffer del scanner.
                }
            }
        }
    }
}
