package Tareas;

import java.util.InputMismatchException; // Importa la excepción para manejar entradas no válidas
import ConexionBD.ConexionBD; // Importa la clase para manejar la conexión a la base de datos
import java.sql.*; // Importa las clases necesarias para trabajar con SQL
import java.util.ArrayList; // Importa la clase ArrayList para almacenar tareas
import java.util.Scanner; // Importa la clase Scanner para leer entradas del usuario

public class ControladorTarea implements ITareaService { // Clase que implementa la interfaz ITareaService

    private ArrayList<Tarea> tareas = new ArrayList<>(); // Lista para almacenar las tareas

    public ControladorTarea() { // Constructor de la clase
        cargarTareasDesdeBD(); // Llama al método para cargar tareas desde la base de datos
    }

    private void cargarTareasDesdeBD() { // Método privado para cargar tareas desde la base de datos
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos
                 Statement statement = connection.createStatement(); // Crea un objeto Statement para ejecutar consultas
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM tarea")) { // Ejecuta la consulta para obtener todas las tareas

            while (resultSet.next()) { // Itera sobre los resultados de la consulta
                String idTarea = String.valueOf(resultSet.getInt("id_tarea")); // Obtiene el ID de la tarea
                String nombre = resultSet.getString("nombre_tarea"); // Obtiene el nombre de la tarea
                String estado = resultSet.getString("estado"); // Obtiene el estado de la tarea
                String idProyecto = String.valueOf(resultSet.getInt("proyecto_id")); // Obtiene el ID del proyecto asociado
                String idUsuario = String.valueOf(resultSet.getInt("asignado_a")); // Obtiene el ID del usuario asignado

                if (nombre != null && !nombre.isEmpty()) { // Verifica que el nombre no sea nulo ni vacío
                    tareas.add(new Tarea(idTarea, nombre, estado, idProyecto, idUsuario)); // Agrega la tarea a la lista
                }
            }
        } catch (SQLException e) { // Maneja excepciones de SQL
            System.out.println("Error al cargar tareas: " + e.getMessage()); // Imprime el mensaje de error
        }
    }

    @Override
    public void gestionTareas(Scanner scanner) { // Método para gestionar las tareas
        while (true) { // Bucle infinito para mostrar el menú de gestión
            System.out.println("Gestión de Tareas:"); // Muestra el encabezado
            System.out.println("1. Crear Tarea"); // Opción para crear tarea
            System.out.println("2. Modificar Tarea"); // Opción para modificar tarea
            System.out.println("3. Eliminar Tarea"); // Opción para eliminar tarea
            System.out.println("4. Asignar Tarea"); // Opción para asignar tarea
            System.out.println("5. Eliminar Asignación de Tarea"); // Opción para eliminar asignación
            System.out.println("6. Verificar Estado de Tarea"); // Opción para verificar estado
            System.out.println("7. Buscar Tareas por Proyecto"); // Opción para buscar tareas por proyecto
            System.out.println("8. Buscar Tareas por Usuario"); // Opción para buscar tareas por usuario
            System.out.println("9. Marcar Tarea como Completa"); // Opción para marcar tarea como completa
            System.out.println("10. Volver"); // Opción para volver
            System.out.print("Seleccione una opción: "); // Solicita al usuario seleccionar una opción

            try {
                int option = scanner.nextInt(); // Lee la opción seleccionada
                scanner.nextLine(); // Limpia el buffer del scanner

                switch (option) { // Evalúa la opción seleccionada
                    case 1:
                        crearTarea(scanner); // Llama al método para crear tarea
                        break;
                    case 2:
                        modificarTarea(scanner); // Llama al método para modificar tarea
                        break;
                    case 3:
                        eliminarTarea(scanner); // Llama al método para eliminar tarea
                        break;
                    case 4:
                        asignarTarea(scanner); // Llama al método para asignar tarea
                        break;
                    case 5:
                        eliminarAsignarTarea(scanner); // Llama al método para eliminar asignación de tarea
                        break;
                    case 6:
                        verificarEstadoTarea(scanner); // Llama al método para verificar estado de tarea
                        break;
                    case 7:
                        buscarTareasPorProyecto(scanner); // Llama al método para buscar tareas por proyecto
                        break;
                    case 8:
                        buscarTareasPorUsuario(scanner); // Llama al método para buscar tareas por usuario
                        break;
                    case 9:
                        marcarTareaCompleta(scanner); // Llama al método para marcar tarea como completa
                        break;
                    case 10:
                        return; // Sale del bucle y finaliza la gestión de tareas
                    default:
                        System.out.println("Opción no válida."); // Mensaje de error para opción no válida
                        break;
                }
            } catch (InputMismatchException e) { // Maneja excepciones de entrada no válida
                System.out.println("Por favor, ingrese un número válido."); // Mensaje de error
                scanner.nextLine(); // Limpia el buffer del scanner
            }
        }
    }

    @Override
    public void crearTarea(Scanner scanner) { // Método para crear una nueva tarea
        System.out.print("Nombre: "); // Solicita el nombre de la tarea
        String nombre = scanner.nextLine(); // Lee el nombre
        System.out.print("Descripción: "); // Solicita la descripción de la tarea
        String descripcion = scanner.nextLine(); // Lee la descripción
        System.out.print("ID del proyecto: "); // Solicita el ID del proyecto
        String idProyecto = scanner.nextLine(); // Lee el ID del proyecto
        System.out.print("ID del usuario al que se le asigna la tarea: "); // Solicita el ID del usuario
        String idUsuario = scanner.nextLine(); // Lee el ID del usuario

        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tarea (nombre_tarea, descripcion, proyecto_id, asignado_a) VALUES (?, ?, ?, ?)")) { // Prepara la consulta para insertar la tarea
            preparedStatement.setString(1, nombre); // Establece el nombre de la tarea
            preparedStatement.setString(2, descripcion); // Establece la descripción de la tarea
            preparedStatement.setInt(3, Integer.parseInt(idProyecto)); // Establece el ID del proyecto
            preparedStatement.setInt(4, Integer.parseInt(idUsuario)); // Establece el ID del usuario
            preparedStatement.executeUpdate(); // Ejecuta la inserción
            System.out.println("Tarea creada exitosamente."); // Mensaje de éxito
        } catch (SQLException e) { // Maneja excepciones de SQL
            System.out.println("Error al crear tarea: " + e.getMessage()); // Mensaje de error
        }
    }

    @Override
    public void modificarTarea(Scanner scanner) { // Método para modificar una tarea existente
        System.out.print("ID de la tarea: "); // Solicita el ID de la tarea
        String idTarea = scanner.nextLine(); // Lee el ID de la tarea
        for (Tarea tarea : tareas) { // Itera sobre la lista de tareas
            if (tarea.getId().equals(idTarea)) { // Verifica si la tarea existe
                System.out.print("Nuevo Nombre: "); // Solicita el nuevo nombre
                String nombre = scanner.nextLine(); // Lee el nuevo nombre
                System.out.print("Nueva Descripción: "); // Solicita la nueva descripción
                String descripcion = scanner.nextLine(); // Lee la nueva descripción

                try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos
                         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tarea SET nombre_tarea = ?, descripcion = ? WHERE id_tarea = ?")) { // Prepara la consulta para actualizar la tarea
                    preparedStatement.setString(1, nombre); // Establece el nuevo nombre
                    preparedStatement.setString(2, descripcion); // Establece la nueva descripción
                    preparedStatement.setInt(3, Integer.parseInt(idTarea)); // Establece el ID de la tarea
                    preparedStatement.executeUpdate(); // Ejecuta la actualización
                    System.out.println("Tarea modificada exitosamente."); // Mensaje de éxito
                } catch (SQLException e) { // Maneja excepciones de SQL
                    System.out.println("Error al modificar tarea: " + e.getMessage()); // Mensaje de error
                }
                return; // Sale del método
            }
        }
        System.out.println("Tarea no encontrada."); // Mensaje si la tarea no existe
    }

    @Override
    public void eliminarTarea(Scanner scanner) { // Método para eliminar una tarea
        System.out.print("ID de la tarea: "); // Solicita el ID de la tarea
        String idTarea = scanner.nextLine(); // Lee el ID de la tarea
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos
                 PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tarea WHERE id_tarea = ?")) { // Prepara la consulta para eliminar la tarea
            preparedStatement.setInt(1, Integer.parseInt(idTarea)); // Establece el ID de la tarea
            int rowsAffected = preparedStatement.executeUpdate(); // Ejecuta la eliminación
            if (rowsAffected > 0) { // Verifica si se eliminó alguna fila
                System.out.println("Tarea eliminada exitosamente."); // Mensaje de éxito
            } else {
                System.out.println("Tarea no encontrada."); // Mensaje si la tarea no existe
            }
        } catch (SQLException e) { // Maneja excepciones de SQL
            System.out.println("Error al eliminar tarea: " + e.getMessage()); // Mensaje de error
        }
    }

    @Override
    public void asignarTarea(Scanner scanner) { // Método para asignar una tarea a un usuario
        System.out.print("ID de la tarea: "); // Solicita el ID de la tarea
        String idTarea = scanner.nextLine(); // Lee el ID de la tarea
        System.out.print("ID del usuario al que se le asigna la tarea: "); // Solicita el ID del usuario
        String idUsuario = scanner.nextLine(); // Lee el ID del usuario
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos
                 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tarea SET asignado_a = ? WHERE id_tarea = ?")) { // Prepara la consulta para asignar la tarea
            preparedStatement.setInt(1, Integer.parseInt(idUsuario)); // Establece el ID del usuario
            preparedStatement.setInt(2, Integer.parseInt(idTarea)); // Establece el ID de la tarea
            int rowsAffected = preparedStatement.executeUpdate(); // Ejecuta la actualización
            if (rowsAffected > 0) { // Verifica si se actualizó alguna fila
                System.out.println("Tarea asignada exitosamente."); // Mensaje de éxito
            } else {
                System.out.println("Tarea no encontrada."); // Mensaje si la tarea no existe
            }
        } catch (SQLException e) { // Maneja excepciones de SQL
            System.out.println("Error al asignar tarea: " + e.getMessage()); // Mensaje de error
        }
    }

    @Override
    public void eliminarAsignarTarea(Scanner scanner) { // Método para eliminar la asignación de una tarea
        System.out.print("ID de la tarea: "); // Solicita el ID de la tarea
        String idTarea = scanner.nextLine(); // Lee el ID de la tarea
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos
                 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tarea SET asignado_a = NULL WHERE id_tarea = ?")) { // Prepara la consulta para eliminar la asignación
            preparedStatement.setInt(1, Integer.parseInt(idTarea)); // Establece el ID de la tarea
            int rowsAffected = preparedStatement.executeUpdate(); // Ejecuta la actualización
            if (rowsAffected > 0) { // Verifica si se actualizó alguna fila
                System.out.println("Asignación de tarea eliminada exitosamente."); // Mensaje de éxito
            } else {
                System.out.println("Tarea no encontrada."); // Mensaje si la tarea no existe
            }
        } catch (SQLException e) { // Maneja excepciones de SQL
            System.out.println("Error al eliminar asignación de tarea: " + e.getMessage()); // Mensaje de error
        }
    }

    @Override
    public void verificarEstadoTarea(Scanner scanner) { // Método para verificar el estado de una tarea
        System.out.print("ID de la tarea: "); // Solicita el ID de la tarea
        String idTarea = scanner.nextLine(); // Lee el ID de la tarea
        for (Tarea tarea : tareas) { // Itera sobre la lista de tareas
            if (tarea.getId().equals(idTarea)) { // Verifica si la tarea existe
                System.out.println("Estado de la tarea: " + tarea.getEstado()); // Muestra el estado de la tarea
                return; // Sale del método
            }
        }
        System.out.println("Tarea no encontrada."); // Mensaje si la tarea no existe
    }

    @Override
    public void buscarTareasPorProyecto(Scanner scanner) { // Método para buscar tareas por proyecto
        System.out.print("ID del proyecto: "); // Solicita el ID del proyecto
        String idProyecto = scanner.nextLine(); // Lee el ID del proyecto
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tarea WHERE proyecto_id = ?")) { // Prepara la consulta para buscar tareas por proyecto
            preparedStatement.setInt(1, Integer.parseInt(idProyecto)); // Establece el ID del proyecto
            ResultSet resultSet = preparedStatement.executeQuery(); // Ejecuta la consulta
            while (resultSet.next()) { // Itera sobre los resultados
                System.out.println("Tarea encontrada: " + resultSet.getString("nombre_tarea")); // Muestra el nombre de la tarea encontrada
            }
        } catch (SQLException e) { // Maneja excepciones de SQL
            System.out.println("Error al buscar tareas por proyecto: " + e.getMessage()); // Mensaje de error
        }
    }

    @Override
    public void buscarTareasPorUsuario(Scanner scanner) { // Método para buscar tareas por usuario
        System.out.print("ID del usuario: "); // Solicita el ID del usuario
        String idUsuario = scanner.nextLine(); // Lee el ID del usuario
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tarea WHERE asignado_a = ?")) { // Prepara la consulta para buscar tareas por usuario
            preparedStatement.setInt(1, Integer.parseInt(idUsuario)); // Establece el ID del usuario
            ResultSet resultSet = preparedStatement.executeQuery(); // Ejecuta la consulta
            while (resultSet.next()) { // Itera sobre los resultados
                System.out.println("Tarea encontrada: " + resultSet.getString("nombre_tarea")); // Muestra el nombre de la tarea encontrada
            }
        } catch (SQLException e) { // Maneja excepciones de SQL
            System.out.println("Error al buscar tareas por usuario: " + e.getMessage()); // Mensaje de error
        }
    }

    @Override
    public void marcarTareaCompleta(Scanner scanner) { // Método para marcar una tarea como completa
        System.out.print("ID de la tarea: "); // Solicita el ID de la tarea
        String idTarea = scanner.nextLine(); // Lee el ID de la tarea
        try (Connection connection = ConexionBD.getConnection(); // Establece la conexión a la base de datos
                 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tarea SET estado = 'Completa' WHERE id_tarea = ?")) { // Prepara la consulta para marcar la tarea como completa
            preparedStatement.setInt(1, Integer.parseInt(idTarea)); // Establece el ID de la tarea
            int rowsAffected = preparedStatement.executeUpdate(); // Ejecuta la actualización
            if (rowsAffected > 0) { // Verifica si se actualizó alguna fila
                System.out.println("Tarea marcada como completa."); // Mensaje de éxito
            } else {
                System.out.println("Tarea no encontrada."); // Mensaje si la tarea no existe
            }
        } catch (SQLException e) { // Maneja excepciones de SQL
            System.out.println("Error al marcar tarea como completa: " + e.getMessage()); // Mensaje de error
        }
    }
}
