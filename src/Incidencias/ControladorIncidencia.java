package Incidencias;

import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario.
import java.util.InputMismatchException; // Importa la excepción para manejar entradas no válidas.
import ConexionBD.ConexionBD; // Importa la clase para manejar la conexión a la base de datos.
import java.sql.*; // Importa las clases necesarias para trabajar con SQL.

public class ControladorIncidencia implements IIncidenciaService {

    // Método para gestionar las incidencias.
    public void gestionIncidencias(Scanner scanner) {
        while (true) { // Bucle para mostrar el menú de gestión de incidencias.
            System.out.println("Gestión de Incidencias:");
            System.out.println("1. Registrar Incidencia"); // Opción para registrar una nueva incidencia.
            System.out.println("2. Eliminar Incidencia"); // Opción para eliminar una incidencia existente.
            System.out.println("3. Buscar Incidencia por Proyecto"); // Opción para buscar incidencias asociadas a un proyecto.
            System.out.println("4. Volver"); // Opción para salir del menú.
            System.out.print("Seleccione una opción: ");

            try {
                int option = scanner.nextInt(); // Lee la opción seleccionada por el usuario.
                scanner.nextLine(); // Limpia el buffer del scanner.

                // Ejecuta la opción seleccionada.
                switch (option) {
                    case 1:
                        registrarIncidencia(scanner); // Llama al método para registrar una nueva incidencia.
                        break;
                    case 2:
                        eliminarIncidencia(scanner); // Llama al método para eliminar una incidencia.
                        break;
                    case 3:
                        buscarIncidenciaPorProyecto(scanner); // Llama al método para buscar incidencias por ID de proyecto.
                        break;
                    case 4:
                        return; // Sale del menú de gestión de incidencias.
                    default:
                        System.out.println("Opción no válida."); // Mensaje de error para opción no válida.
                        break;
                }
            } catch (InputMismatchException e) { // Manejo de excepciones para entradas no válidas.
                System.out.println("Por favor, ingrese un número válido."); // Mensaje de error.
                scanner.nextLine(); // Limpia el buffer del scanner.
            }
        }
    }

    @Override
    public void registrarIncidencia(Scanner scanner) {
        // Solicita al usuario los datos necesarios para registrar una nueva incidencia.
        System.out.print("ID del proyecto: ");
        String idProyecto = scanner.nextLine(); // Lee el ID del proyecto.
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine(); // Lee la descripción de la incidencia.

        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos.
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO incidencia (id_proyecto, descripcion) VALUES (?, ?)")) {
            // Configura el PreparedStatement con los datos de la nueva incidencia.
            preparedStatement.setInt(1, Integer.parseInt(idProyecto)); // Establece el ID del proyecto.
            preparedStatement.setString(2, descripcion); // Establece la descripción.
            preparedStatement.executeUpdate(); // Ejecuta la inserción en la base de datos.
            System.out.println("Incidencia registrada exitosamente."); // Mensaje de éxito.
        } catch (SQLException e) { // Manejo de excepciones en caso de error al registrar incidencia.
            System.out.println("Error al registrar incidencia: " + e.getMessage());
        }
    }

    @Override
    public void eliminarIncidencia(Scanner scanner) {
        // Solicita al usuario el ID de la incidencia a eliminar.
        System.out.print("ID de la incidencia: ");
        String idIncidencia = scanner.nextLine(); // Lee el ID de la incidencia.
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos.
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM incidencia WHERE id_incidencia = ?")) {
            // Configura el PreparedStatement para eliminar la incidencia.
            preparedStatement.setInt(1, Integer.parseInt(idIncidencia)); // Establece el ID de la incidencia a eliminar.
            int rowsAffected = preparedStatement.executeUpdate(); // Ejecuta la eliminación en la base de datos.
            if (rowsAffected > 0) {
                System.out.println("Incidencia eliminada exitosamente."); // Mensaje de éxito.
            } else {
                System.out.println("Incidencia no encontrada."); // Mensaje si la incidencia no existe.
            }
        } catch (SQLException e) { // Manejo de excepciones en caso de error al eliminar incidencia.
            System.out.println("Error al eliminar incidencia: " + e.getMessage());
        }
    }

    @Override
    public void buscarIncidenciaPorProyecto(Scanner scanner) {
        // Solicita al usuario el ID del proyecto para buscar incidencias asociadas.
        System.out.print("ID del proyecto: ");
        String idProyecto = scanner.nextLine(); // Lee el ID del proyecto.
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos.
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM incidencia WHERE id_proyecto = ?")) {
            // Configura el PreparedStatement para buscar incidencias por ID de proyecto.
            preparedStatement.setInt(1, Integer.parseInt(idProyecto)); // Establece el ID del proyecto.
            ResultSet resultSet = preparedStatement.executeQuery(); // Ejecuta la consulta en la base de datos.
            boolean found = false; // Variable para verificar si se encontraron incidencias.
            while (resultSet.next()) { // Itera sobre los resultados de la consulta.
                found = true; // Marca que se encontró al menos una incidencia.
                System.out.println("Incidencia encontrada:"); // Mensaje de éxito.
                System.out.println("Descripción: " + resultSet.getString("descripcion")); // Muestra la descripción de la incidencia.
            }
            if (!found) {
                System.out.println("No se encontraron incidencias para este proyecto."); // Mensaje si no hay incidencias.
            }
        } catch (SQLException e) { // Manejo de excepciones en caso de error al buscar incidencias.
            System.out.println("Error al buscar incidencias por proyecto: " + e.getMessage());
        }
    }
}
