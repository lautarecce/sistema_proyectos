package Incidencias;

import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario.

public interface IIncidenciaService {
    // Método para gestionar las operaciones relacionadas con las incidencias.
    void gestionIncidencias(Scanner scanner);

    // Método para registrar una nueva incidencia.
    void registrarIncidencia(Scanner scanner);

    // Método para eliminar una incidencia existente.
    void eliminarIncidencia(Scanner scanner);

    // Método para buscar incidencias asociadas a un proyecto específico.
    void buscarIncidenciaPorProyecto(Scanner scanner);
}