package Usuarios;

// Importaciones necesarias para la conexión a la base de datos y otras funcionalidades.
import ConexionBD.ConexionBD;
import java.sql.*;
import Tareas.ControladorTarea;
import Reporte.ControladorReporte;
import Proyectos.ControladorProyecto;
import Incidencias.ControladorIncidencia;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

// Clase ControladorUsuario que implementa la interfaz IUsuarioService.
public class ControladorUsuario implements IUsuarioService {

    // Lista para almacenar usuarios.
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    // Usuario actualmente autenticado.
    private Usuario usuarioActual;
    // Controladores para gestionar otras entidades.
    private ControladorProyecto controladorProyecto = new ControladorProyecto();
    private ControladorTarea controladorTarea = new ControladorTarea();
    private ControladorIncidencia controladorIncidencia = new ControladorIncidencia();
    private ControladorReporte controladorReporte = new ControladorReporte();

    // Constructor que carga los usuarios desde la base de datos al inicializar la clase.
    public ControladorUsuario() {
        cargarUsuariosDesdeBD();
    }

    // Método privado para cargar usuarios desde la base de datos.
    private void cargarUsuariosDesdeBD() {
        try (Connection connection = ConexionBD.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM usuario WHERE rol_id = 1")) {

            // Itera sobre los resultados y crea objetos Usuario.
            while (resultSet.next()) {
                String idUsuario = String.valueOf(resultSet.getInt("id_usuario"));
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String email = resultSet.getString("email");
                String telefono = resultSet.getString("telefono");
                String contraseña = resultSet.getString("password");
                String sectorTrabajo = "";
                int rolId = resultSet.getInt("rol_id");
                // Agrega el nuevo usuario a la lista.
                usuarios.add(new Usuario(idUsuario, nombre, apellido, email, telefono, contraseña, sectorTrabajo, rolId));
            }
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al cargar usuarios.
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

    // Método para iniciar sesión del usuario.
    @Override
    public boolean iniciarSesion(Scanner scanner) {
        // Solicita email y contraseña al usuario.
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine().trim();

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuario WHERE email = ? AND password = ?")) {
            // Configura el PreparedStatement con los datos ingresados.
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, contraseña);
            ResultSet resultSet = preparedStatement.executeQuery();
            // Si se encuentra un usuario, se establece como usuarioActual.
            if (resultSet.next()) {
                usuarioActual = new Usuario(
                        String.valueOf(resultSet.getInt("id_usuario")),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("email"),
                        resultSet.getString("telefono"),
                        resultSet.getString("password"),
                        "",
                        resultSet.getInt("rol_id")
                );
                System.out.println("Inicio de sesión exitoso.");
                return true;
            }
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al iniciar sesión.
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
        // Mensaje si las credenciales son incorrectas.
        System.out.println("Credenciales incorrectas.");
        return false;
    }

    // Método para gestionar usuarios (solo accesible para administradores).
    @Override
    public void gestionUsuario(Scanner scanner) {
        // Verifica si el usuario actual es un administrador.
        if (usuarioActual != null && usuarioActual.getRolId() == 1) {
            while (true) {
                // Muestra el menú de gestión de usuarios.
                System.out.println("Gestión Usuario:");
                System.out.println("1. Registrar Usuario");
                System.out.println("2. Modificar Datos");
                System.out.println("3. Buscar Usuario");
                System.out.println("4. Eliminar Usuario");
                System.out.println("5. Volver");
                System.out.print("Seleccione una opción: ");
                try {
                    int option = scanner.nextInt();
                    scanner.nextLine();

                    // Ejecuta la opción seleccionada.
                    switch (option) {
                        case 1:
                            registrarUsuario(scanner); // Llama al método para registrar un nuevo usuario.
                            break;
                        case 2:
                            modificarDatos(scanner); // Llama al método para modificar datos de un usuario existente.
                            break;
                        case 3:
                            buscarUsuario(scanner); // Llama al método para buscar un usuario por ID.
                            break;
                        case 4:
                            eliminarUsuario(scanner); // Llama al método para eliminar un usuario por ID.
                            break;
                        case 5:
                            return; // Sale del menú de gestión de usuarios.
                        default:
                            System.out.println("Opción no válida."); // Mensaje de error para opción no válida.
                            break;
                    }
                } catch (InputMismatchException e) {
                    // Manejo de excepciones para entradas no válidas.
                    System.out.println("Por favor, ingrese un número válido.");
                    scanner.nextLine(); // Limpia el buffer del scanner.
                }
            }
        } else {
            // Mensaje de acceso denegado si el usuario no es administrador.
            System.out.println("Acceso denegado. Solo los administradores pueden gestionar usuarios.");
        }
    }

    // Método para registrar un nuevo usuario.
    @Override
    public void registrarUsuario(Scanner scanner) {
        // Solicita datos del nuevo usuario.
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        // Solicita el sector de trabajo del nuevo usuario.
        System.out.println("Seleccione el sector de trabajo:");
        System.out.println("1. Desarrollo");
        System.out.println("2. Administración");
        System.out.println("3. Soporte");
        int sectorSeleccionado = scanner.nextInt();
        scanner.nextLine();

        // Asigna el ID del sector basado en la selección.
        int sectorId = (sectorSeleccionado == 1) ? 1 : (sectorSeleccionado == 2) ? 2 : 3;

        // Solicita el rol del nuevo usuario.
        System.out.println("Seleccione el rol:");
        System.out.println("1. Administrador");
        System.out.println("2. Usuario");
        int rolSeleccionado = scanner.nextInt();
        scanner.nextLine();

        // Asigna el ID del rol basado en la selección.
        int rolId = (rolSeleccionado == 1) ? 1 : 2;

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuario (nombre, apellido, email, password, rol_id, sector_id) VALUES (?, ?, ?, ?, ?, ?)")) {
            // Configura el PreparedStatement con los datos del nuevo usuario.
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, contraseña);
            preparedStatement.setInt(5, rolId);
            preparedStatement.setInt(6, sectorId);
            preparedStatement.executeUpdate(); // Ejecuta la inserción en la base de datos.
            System.out.println("Usuario registrado exitosamente."); // Mensaje de éxito.
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al registrar usuario.
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    // Método para modificar los datos de un usuario existente.
    @Override
    public void modificarDatos(Scanner scanner) {
        System.out.print("Ingrese el ID del usuario que desea modificar: ");
        String idUsuario = scanner.nextLine();

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?")) {
            // Busca el usuario por ID.
            preparedStatement.setInt(1, Integer.parseInt(idUsuario));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Solicita nuevos datos para el usuario.
                System.out.print("Nuevo Nombre: ");
                String nuevoNombre = scanner.nextLine();
                System.out.print("Nuevo Apellido: ");
                String nuevoApellido = scanner.nextLine();
                System.out.print("Nuevo Teléfono: ");
                String nuevoTelefono = scanner.nextLine();

                // Solicita el nuevo sector de trabajo.
                System.out.println("Seleccione el nuevo sector de trabajo:");
                System.out.println("1. Desarrollo");
                System.out.println("2. Administración");
                System.out.println("3. Soporte");
                int nuevoSectorSeleccionado = scanner.nextInt();
                scanner.nextLine();

                // Asigna el ID del nuevo sector basado en la selección.
                int nuevoSectorId = (nuevoSectorSeleccionado == 1) ? 1 : (nuevoSectorSeleccionado == 2) ? 2 : 3;

                // Solicita una nueva contraseña, si se desea cambiar.
                System.out.print("Nueva Contraseña (deje en blanco para no cambiar): ");
                String nuevaContraseña = scanner.nextLine();

                try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE usuario SET nombre = ?, apellido = ?, password = ?, sector_id = ? WHERE id_usuario = ?")) {
                    // Configura el PreparedStatement con los nuevos datos.
                    updateStatement.setString(1, nuevoNombre);
                    updateStatement.setString(2, nuevoApellido);
                    if (!nuevaContraseña.isEmpty()) {
                        updateStatement.setString(3, nuevaContraseña); // Cambia la contraseña si se proporciona.
                    } else {
                        updateStatement.setString(3, resultSet.getString("password")); // Mantiene la contraseña actual si no se proporciona una nueva.
                    }
                    updateStatement.setInt(4, nuevoSectorId);
                    updateStatement.setInt(5, Integer.parseInt(idUsuario));
                    updateStatement.executeUpdate(); // Ejecuta la actualización en la base de datos.
                    System.out.println("Datos del usuario modificados exitosamente."); // Mensaje de éxito.
                }
            } else {
                System.out.println("Usuario no encontrado."); // Mensaje si el usuario no existe.
            }
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al modificar datos.
            System.out.println("Error al modificar datos: " + e.getMessage());
        }
    }

    // Método para buscar un usuario por ID.
    @Override
    public void buscarUsuario(Scanner scanner) {
        System.out.print("Ingrese el ID del usuario: ");
        String idUsuario = scanner.nextLine();

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM usuario WHERE id_usuario = ?")) {
            // Busca el usuario por ID.
            preparedStatement.setInt(1, Integer.parseInt(idUsuario));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Muestra los datos del usuario encontrado.
                System.out.println("Usuario encontrado: " + resultSet.getString("nombre") + " " + resultSet.getString("apellido"));
            } else {
                System.out.println("Usuario no encontrado."); // Mensaje si el usuario no existe.
            }
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al buscar usuario.
            System.out.println("Error al buscar usuario: " + e.getMessage());
        }
    }

    // Método para eliminar un usuario por ID.
    @Override
    public void eliminarUsuario(Scanner scanner) {
        System.out.print("Ingrese el ID del usuario a eliminar: ");
        String idUsuario = scanner.nextLine();

        try (Connection connection = ConexionBD.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usuario WHERE id_usuario = ?")) {
            // Configura el PreparedStatement para eliminar el usuario.
            preparedStatement.setInt(1, Integer.parseInt(idUsuario));
            int rowsAffected = preparedStatement.executeUpdate(); // Ejecuta la eliminación en la base de datos.
            if (rowsAffected > 0) {
                System.out.println("Usuario eliminado exitosamente."); // Mensaje de éxito.
            } else {
                System.out.println("Usuario no encontrado."); // Mensaje si el usuario no existe.
            }
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error al eliminar usuario.
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }
}
