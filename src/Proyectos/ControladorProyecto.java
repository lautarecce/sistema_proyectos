package Proyectos;

import java.util.ArrayList; // Importa la clase ArrayList para almacenar proyectos.
import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario.
import java.util.Date; // Importa la clase Date para manejar fechas.
import java.util.InputMismatchException; // Importa la excepción para manejar entradas no válidas.
import ConexionBD.ConexionBD; // Importa la clase para manejar la conexión a la base de datos.
import java.sql.*; // Importa las clases necesarias para trabajar con SQL.

public class ControladorProyecto implements IProyectoService {

    private ArrayList<Proyecto> proyectos = new ArrayList<>(); // Lista para almacenar proyectos.

    // Constructor de la clase ControladorProyecto.
    public ControladorProyecto() {
        cargarProyectosDesdeBD(); // Carga los proyectos desde la base de datos al inicializar.
    }

    // Método para cargar proyectos desde la base de datos.
    private void cargarProyectosDesdeBD() {
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos.
                 Statement statement = connection.createStatement(); // Crea un Statement para ejecutar consultas.
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM proyecto")) { // Ejecuta la consulta para obtener todos los proyectos.

            while (resultSet.next()) { // Itera sobre los resultados de la consulta.
                String idProyecto = String.valueOf(resultSet.getInt("id_proyecto")); // Obtiene el ID del proyecto.
                String nombre = resultSet.getString("nombre_proyecto"); // Obtiene el nombre del proyecto.
                String descripcion = resultSet.getString("descripcion"); // Obtiene la descripción del proyecto.
                Date fechaInicio = resultSet.getTimestamp("fecha_inicio"); // Obtiene la fecha de inicio del proyecto.
                // Agrega el proyecto a la lista solo si tiene un nombre válido.
                if (nombre != null && !nombre.isEmpty()) {
                    proyectos.add(new Proyecto(idProyecto, nombre, descripcion, fechaInicio)); // Crea y agrega el proyecto a la lista.
                }
            }
        } catch (SQLException e) { // Manejo de excepciones en caso de error al cargar proyectos.
            System.out.println("Error al cargar proyectos: " + e.getMessage());
        }
    }

    @Override
    public void gestionProyectos(Scanner scanner) {
        while (true) { // Bucle para mostrar el menú de gestión de proyectos.
            System.out.println("Gestión de Proyectos:");
            System.out.println("1. Crear Proyecto"); // Opción para crear un nuevo proyecto.
            System.out.println("2. Modificar Proyecto"); // Opción para modificar un proyecto existente.
            System.out.println("3. Eliminar Proyecto"); // Opción para eliminar un proyecto.
            System.out.println("4. Buscar Proyecto"); // Opción para buscar un proyecto por ID.
            System.out.println("5. Volver"); // Opción para salir del menú.
            System.out.print("Seleccione una opción: ");

            try {
                int option = scanner.nextInt(); // Lee la opción seleccionada por el usuario.
                scanner.nextLine(); // Limpia el buffer del scanner.

                // Ejecuta la opción seleccionada.
                switch (option) {
                    case 1:
                        crearProyecto(scanner); // Llama al método para crear un nuevo proyecto.
                        break;
                    case 2:
                        modificarProyecto(scanner); // Llama al método para modificar un proyecto existente.
                        break;
                    case 3:
                        eliminarProyecto(scanner); // Llama al método para eliminar un proyecto.
                        break;
                    case 4:
                        buscarProyecto(scanner); // Llama al método para buscar un proyecto.
                        break;
                    case 5:
                        return; // Sale del menú de gestión de proyectos.
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
    public void crearProyecto(Scanner scanner) {
        // Solicita al usuario los datos necesarios para crear un nuevo proyecto.
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine(); // Lee el nombre del proyecto.
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine(); // Lee la descripción del proyecto.

        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos.
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO proyecto (nombre_proyecto, descripcion, fecha_inicio, estado_id, gestor_id) VALUES (?, ?, NOW(), ?, ?)")) {
            // Configura el PreparedStatement con los datos del nuevo proyecto.
            preparedStatement.setString(1, nombre); // Establece el nombre del proyecto.
            preparedStatement.setString(2, descripcion); // Establece la descripción del proyecto.
            preparedStatement.setInt(3, 1); // Estado por defecto.
            preparedStatement.setInt(4, 1); // Gestor por defecto.
            preparedStatement.executeUpdate(); // Ejecuta la inserción en la base de datos.
            System.out.println("Proyecto creado exitosamente."); // Mensaje de éxito.
        } catch (SQLException e) { // Manejo de excepciones en caso de error al crear proyecto.
            System.out.println("Error al crear proyecto: " + e.getMessage());
        }
    }

    @Override
    public void modificarProyecto(Scanner scanner) {
        System.out.print("ID del proyecto: "); // Solicita el ID del proyecto a modificar.
        String idProyecto = scanner.nextLine(); // Lee el ID del proyecto.
        for (Proyecto proyecto : proyectos) { // Itera sobre la lista de proyectos.
            if (proyecto.getId().equals(idProyecto)) { // Verifica si el ID coincide.
                System.out.print("Nuevo Nombre: "); // Solicita el nuevo nombre.
                String nombre = scanner.nextLine(); // Lee el nuevo nombre.
                System.out.print("Nueva Descripción: "); // Solicita la nueva descripción.
                String descripcion = scanner.nextLine(); // Lee la nueva descripción.

                try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos.
                         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE proyecto SET nombre_proyecto = ?, descripcion = ? WHERE id_proyecto = ?")) {
                    // Configura el PreparedStatement para modificar el proyecto.
                    preparedStatement.setString(1, nombre); // Establece el nuevo nombre.
                    preparedStatement.setString(2, descripcion); // Establece la nueva descripción.
                    preparedStatement.setInt(3, Integer.parseInt(idProyecto)); // Establece el ID del proyecto a modificar.
                    preparedStatement.executeUpdate(); // Ejecuta la actualización en la base de datos.
                    System.out.println("Proyecto modificado exitosamente."); // Mensaje de éxito.
                } catch (SQLException e) { // Manejo de excepciones en caso de error al modificar proyecto.
                    System.out.println("Error al modificar proyecto: " + e.getMessage());
                }
                return; // Sale del método después de modificar el proyecto.
            }
        }
        System.out.println("Proyecto no encontrado."); // Mensaje si el proyecto no existe.
    }

    @Override
    public void eliminarProyecto(Scanner scanner) {
        System.out.print("ID del proyecto: "); // Solicita el ID del proyecto a eliminar.
        String idProyecto = scanner.nextLine(); // Lee el ID del proyecto.
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos.
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM proyecto WHERE id_proyecto = ?")) {
            // Configura el PreparedStatement para eliminar el proyecto.
            preparedStatement.setInt(1, Integer.parseInt(idProyecto)); // Establece el ID del proyecto a eliminar.
            int rowsAffected = preparedStatement.executeUpdate(); // Ejecuta la eliminación en la base de datos.
            if (rowsAffected > 0) {
                System.out.println("Proyecto eliminado exitosamente."); // Mensaje de éxito.
            } else {
                System.out.println("Proyecto no encontrado."); // Mensaje si el proyecto no existe.
            }
        } catch (SQLException e) { // Manejo de excepciones en caso de error al eliminar proyecto.
            System.out.println("Error al eliminar proyecto: " + e.getMessage());
        }
    }

    @Override
    public void buscarProyecto(Scanner scanner) {
        System.out.print("ID del proyecto: "); // Solicita el ID del proyecto a buscar.
        String idProyecto = scanner.nextLine(); // Lee el ID del proyecto.
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos.
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM proyecto WHERE id_proyecto = ?")) {
            // Configura el PreparedStatement para buscar el proyecto.
            preparedStatement.setInt(1, Integer.parseInt(idProyecto)); // Establece el ID del proyecto a buscar.
            ResultSet resultSet = preparedStatement.executeQuery(); // Ejecuta la consulta en la base de datos.
            if (resultSet.next()) { // Verifica si se encontró un proyecto.
                System.out.println("Proyecto encontrado:"); // Mensaje de éxito.
                System.out.println("Nombre: " + resultSet.getString("nombre_proyecto")); // M uestra el nombre del proyecto.
                System.out.println("Descripción: " + resultSet.getString("descripcion")); // Muestra la descripción del proyecto.
            } else {
                System.out.println("Proyecto no encontrado."); // Mensaje si el proyecto no existe.
            }
        } catch (SQLException e) { // Manejo de excepciones en caso de error al buscar proyecto.
            System.out.println("Error al buscar proyecto: " + e.getMessage());
        }
    }
}
