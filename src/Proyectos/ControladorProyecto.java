package Proyectos;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date; 
import java.util.InputMismatchException;

public class ControladorProyecto {
    private ArrayList<Proyecto> proyectos = new ArrayList<>(); // Lista para almacenar los proyectos

    public ControladorProyecto() {
        // Simulación de proyectos
        proyectos.add(new Proyecto("1", "Proyecto Farmamed", "Solicitud de medicamentos por vía de excepción provinciales", new Date()));
        proyectos.add(new Proyecto("2", "Proyecto Premo", "Solicitud de medicamentos oncológicos provinciales", new Date()));
    }

    public void gestionProyectos(Scanner scanner) {
        // Muestra las opciones de gestión de proyectos al usuario
        System.out.println("Gestión de Proyectos:");
        System.out.println("1. Crear Proyecto");
        System.out.println("2. Modificar Proyecto");
        System.out.println("3. Eliminar Proyecto");
        System.out.println("4. Buscar Proyecto");
        System.out.print("Seleccione una opción: ");
        
        try {
            int option = scanner.nextInt(); // Leer la opción seleccionada
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    crearProyecto(scanner); // Llamar al método para crear un proyecto
                    break;
                case 2:
                    modificarProyecto(scanner); // Llamar al método para modificar un proyecto
                    break;
                case 3:
                    eliminarProyecto(scanner); // Llamar al método para eliminar un proyecto
                    break;
                case 4:
                    buscarProyecto(scanner); // Llamar al método para buscar un proyecto
                    break;
                default:
                    System.out.println("Opción no válida."); // Mensaje de opción no válida
                    break;
            }
        } catch (InputMismatchException e) {
            // Manejar la excepción si la entrada no es un número válido
            System.out.println("Por favor, ingrese un número válido.");
            scanner.nextLine(); // Limpiar el buffer
        }
    }

    public void crearProyecto(Scanner scanner) {
        // Solicitar datos para crear un nuevo proyecto
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine(); // Leer el nombre del proyecto
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine(); // Leer la descripción del proyecto
        proyectos.add(new Proyecto(String.valueOf(proyectos.size() + 1), nombre, descripcion, new Date())); // Agregar el nuevo proyecto a la lista
        System.out.println("Proyecto creado exitosamente."); // Mensaje de éxito
    }

    public void modificarProyecto(Scanner scanner) {
        // Solicitar el ID del proyecto a modificar
        System.out.print("ID del proyecto: ");
        String idProyecto = scanner.nextLine(); // Leer el ID del proyecto
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId().equals(idProyecto)) { // Verificar si el proyecto existe
                // Solicitar los nuevos datos
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Descripción: ");
                String descripcion = scanner.nextLine();
                // Modificar los datos del proyecto
                proyecto.setNombre(nombre);
                proyecto.setDescripcion(descripcion);
                System.out.println("Proyecto modificado exitosamente."); // Mensaje de éxito
                return;
            }
        }
        System.out.println("Proyecto no encontrado."); // Mensaje si el proyecto no existe
    }

    public void eliminarProyecto(Scanner scanner) {
        // Solicitar el ID del proyecto a eliminar
        System.out.print("ID del proyecto: ");
        String idProyecto = scanner.nextLine(); // Leer el ID del proyecto
        for (int i = 0; i < proyectos.size(); i++) {
            if (proyectos.get(i).getId().equals(idProyecto)) { // Verificar si el proyecto existe
                proyectos.remove(i); // Eliminar el proyecto de la lista
                System.out.println("Proyecto eliminado exitosamente."); // Mensaje de éxito
                return;
            }
        }
        System.out.println("Proyecto no encontrado."); // Mensaje si el proyecto no existe
    }

    public void buscarProyecto(Scanner scanner) {
        // Solicitar el ID del proyecto a buscar
        System.out.print("ID del proyecto: ");
        String idProyecto = scanner.nextLine(); // Leer el ID del proyecto
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId().equals(idProyecto)) { // Verificar si el proyecto existe
                // Mostrar información del proyecto encontrado
                System.out.println("Proyecto encontrado:");
                System.out.println("Nombre: " + proyecto.getNombre());
                System.out.println("Descripción: " + proyecto.getDescripcion());
                return;
            }
        }
        System.out.println("Proyecto no encontrado."); // Mensaje si el proyecto no existe
    }
}
