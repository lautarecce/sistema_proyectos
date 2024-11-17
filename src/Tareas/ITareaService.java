package Tareas;

import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario.

public interface ITareaService {
    // Método para gestionar las operaciones relacionadas con las tareas.
    void gestionTareas(Scanner scanner);

    // Método para crear una nueva tarea.
    void crearTarea(Scanner scanner);

    // Método para modificar una tarea existente.
    void modificarTarea(Scanner scanner);

    // Método para eliminar una tarea del sistema.
    void eliminarTarea(Scanner scanner);

    // Método para asignar una tarea a un usuario.
    void asignarTarea(Scanner scanner);

    // Método para eliminar la asignación de una tarea a un usuario.
    void eliminarAsignarTarea(Scanner scanner);

    // Método para verificar el estado de una tarea (completa, en progreso, etc.).
    void verificarEstadoTarea(Scanner scanner);

    // Método para buscar tareas asociadas a un proyecto específico.
    void buscarTareasPorProyecto(Scanner scanner);

    // Método para buscar tareas asociadas a un usuario específico.
    void buscarTareasPorUsuario(Scanner scanner);

    // Método para marcar una tarea como completa.
    void marcarTareaCompleta(Scanner scanner);
}