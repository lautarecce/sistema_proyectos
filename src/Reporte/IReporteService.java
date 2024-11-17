package Reporte;

import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario.

public interface IReporteService {
    // Método para gestionar las operaciones relacionadas con los reportes.
    void gestionReportes(Scanner scanner);

    // Método para generar un nuevo reporte.
    void generarReporte(Scanner scanner);

    // Método para eliminar un reporte existente.
    void eliminarReporte(Scanner scanner);

    // Método para buscar un reporte específico.
    void buscarReporte(Scanner scanner);
}