package Reporte;

import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario.
import java.util.InputMismatchException; // Importa la excepción para manejar entradas no válidas.
import ConexionBD.ConexionBD; // Importa la clase para manejar la conexión a la base de datos.
import java.sql.*; // Importa las clases necesarias para trabajar con SQL.

public class ControladorReporte implements IReporteService {
    // Método para gestionar las operaciones de reportes.
    public void gestionReportes(Scanner scanner) {
        while (true) { // Bucle para mostrar el menú de gestión de reportes.
            System.out.println("Gestión de Reportes:");
            System.out.println("1. Generar Reporte"); // Opción para generar un nuevo reporte.
            System.out.println("2. Eliminar Reporte"); // Opción para eliminar un reporte existente.
            System.out.println("3. Buscar Reporte"); // Opción para buscar un reporte por ID.
            System.out.println("4. Volver"); // Opción para salir del menú.
            System.out.print("Seleccione una opción: ");

            try {
                int option = scanner.nextInt(); // Lee la opción seleccionada por el usuario.
                scanner.nextLine(); // Limpia el buffer del scanner.

                // Ejecuta la opción seleccionada.
                switch (option) {
                    case 1:
                        generarReporte(scanner); // Llama al método para generar un nuevo reporte.
                        break;
                    case 2:
                        eliminarReporte(scanner); // Llama al método para eliminar un reporte.
                        break;
                    case 3:
                        buscarReporte(scanner); // Llama al método para buscar un reporte.
                        break;
                    case 4:
                        return; // Sale del menú de gestión de reportes.
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
    public void generarReporte(Scanner scanner) {
        // Solicita al usuario los datos necesarios para generar un nuevo reporte.
        System.out.print("ID del proyecto: ");
        String idProyecto = scanner.nextLine(); // Lee el ID del proyecto.
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine(); // Lee la descripción del reporte.

        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos.
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO reporte (id_proyecto, descripcion) VALUES (?, ?)")) {
            // Configura el PreparedStatement con los datos del nuevo reporte.
            preparedStatement.setInt(1, Integer.parseInt(idProyecto)); // Convierte el ID del proyecto a entero.
            preparedStatement.setString(2, descripcion); // Establece la descripción.
            preparedStatement.executeUpdate(); // Ejecuta la inserción en la base de datos.
            System.out.println("Reporte generado exitosamente."); // Mensaje de éxito.
        } catch (SQLException e) { // Manejo de excepciones en caso de error al generar reporte.
            System.out.println("Error al generar reporte: " + e.getMessage());
        }
    }

    @Override
    public void eliminarReporte(Scanner scanner) {
        System.out.print("ID del reporte: "); // Solicita el ID del reporte a eliminar.
        String idReporte = scanner.nextLine(); // Lee el ID del reporte.
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos.
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM reporte WHERE id_reporte = ?")) {
            // Configura el PreparedStatement para eliminar el reporte.
            preparedStatement.setInt(1, Integer.parseInt(idReporte)); // Convierte el ID del reporte a entero.
            int rowsAffected = preparedStatement.executeUpdate(); // Ejecuta la eliminación en la base de datos.
            if (rowsAffected > 0) {
                System.out.println("Reporte eliminado exitosamente."); // Mensaje de éxito.
            } else {
                System.out.println("Reporte no encontrado."); // Mensaje si el reporte no existe.
            }
        } catch (SQLException e) { // Manejo de excepciones en caso de error al eliminar reporte.
            System.out.println("Error al eliminar reporte: " + e.getMessage());
        }
    }

    @Override
    public void buscarReporte(Scanner scanner) {
        System.out.print("ID del reporte: "); // Solicita el ID del reporte a buscar.
        String idReporte = scanner.nextLine(); // Lee el ID del reporte.
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos.
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM reporte WHERE id_reporte = ?")) {
            // Configura el PreparedStatement para buscar el reporte.
            preparedStatement.setInt(1, Integer.parseInt(idReporte)); // Convierte el ID del reporte a entero.
            ResultSet resultSet = preparedStatement.executeQuery(); // Ejecuta la consulta en la base de datos.
            if (resultSet.next()) { // Verifica si se encontró un reporte.
                System.out.println("Reporte encontrado:"); // Mensaje de éxito.
                System.out.println("Descripción: " + resultSet.getString("descripcion")); // Muestra la descripción del reporte.
            } else {
                System.out.println("Reporte no encontrado."); // Mensaje si el reporte no existe.
            }
        } catch (SQLException e) { // Manejo de excepciones en caso de error al buscar reporte.
            System.out.println("Error al buscar reporte: " + e.getMessage());
        }
    }
}