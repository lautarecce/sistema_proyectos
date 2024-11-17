package Proyectos;

import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario.

public interface IProyectoService {
    // Método para gestionar las operaciones relacionadas con los proyectos.
    void gestionProyectos(Scanner scanner);

    // Método para crear un nuevo proyecto.
    void crearProyecto(Scanner scanner);

    // Método para modificar un proyecto existente.
    void modificarProyecto(Scanner scanner);

    // Método para eliminar un proyecto del sistema.
    void eliminarProyecto(Scanner scanner);

    // Método para buscar un proyecto específico.
    void buscarProyecto(Scanner scanner);
}