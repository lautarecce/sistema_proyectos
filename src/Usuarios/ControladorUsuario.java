package Usuarios;

import Tareas.ControladorTarea;
import Reporte.ControladorReporte;
import Proyectos.ControladorProyecto;
import Incidencias.ControladorIncidencia;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ControladorUsuario {

    private ArrayList<Usuario> usuarios = new ArrayList<>(); // Lista de usuarios registrados
    private Usuario usuarioActual; // Usuario actualmente autenticado en el sistema
    private ControladorProyecto controladorProyecto = new ControladorProyecto(); // Controlador para gestionar proyectos
    private ControladorTarea controladorTarea = new ControladorTarea(); // Controlador para gestionar tareas
    private ControladorIncidencia controladorIncidencia = new ControladorIncidencia(); // Controlador para gestionar incidencias
    private ControladorReporte controladorReporte = new ControladorReporte(); // Controlador para gestionar reportes

    public ControladorUsuario() {
        // Simulación de usuarios para el sistema
        usuarios.add(new Usuario("1", "Lautaro", "Recce", "lrecce@gmail.com", "123456789", "prueba1", "Desarrollo"));
        usuarios.add(new Administrador("2", "Carlos", "Recce", "crecce@gmail.com", "987654321", "prueba2", "Mesa de ayuda"));
    }

    public boolean iniciarSesion(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        // Verifica las credenciales ingresadas contra los usuarios registrados
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getContraseña().equals(contraseña)) {
                usuarioActual = usuario;
                System.out.println("Inicio de sesión exitoso.");
                return true;
            }
        }
        System.out.println("Credenciales incorrectas.");
        return false;
    }

    public void gestionUsuario(Scanner scanner) {
        // Comprueba que el usuario actual sea un administrador para permitir la gestión de usuarios
        if (usuarioActual instanceof Administrador) {
            System.out.println("Gestión Usuario:");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Modificar Datos");
            System.out.println("3. Buscar Usuario");
            System.out.println("4. Eliminar Usuario");
            System.out.print("Seleccione una opción: ");
            try {
                int option = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer del scanner

                // Ejecuta la opción seleccionada
                switch (option) {
                    case 1:
                        registrarUsuario(scanner);
                        break;
                    case 2:
                        modificarDatos(scanner);
                        break;
                    case 3:
                        buscarUsuario(scanner);
                        break;
                    case 4:
                        eliminarUsuario(scanner);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpia el buffer si ocurre una excepción
            }
        } else {
            System.out.println("Acceso denegado. Solo los administradores pueden gestionar usuarios.");
        }
    }

    // Métodos para gestionar usuarios
    // Registra un nuevo usuario en el sistema
    private void registrarUsuario(Scanner scanner) {
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
        System.out.print("Sector de trabajo: ");
        String sectorTrabajo = scanner.nextLine();

        // Añade el usuario a la lista de usuarios registrados
        usuarios.add(new Usuario(String.valueOf(usuarios.size() + 1), nombre, apellido, email, telefono, contraseña, sectorTrabajo));
        System.out.println("Usuario registrado exitosamente.");
    }

    // Modifica los datos del usuario actualmente autenticado
    private void modificarDatos(Scanner scanner) {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Sector de trabajo: ");
        String sectorTrabajo = scanner.nextLine();

        usuarioActual.setNombre(nombre);
        usuarioActual.setApellido(apellido);
        usuarioActual.setTelefono(telefono);
        usuarioActual.setSectorTrabajo(sectorTrabajo);
        System.out.println("Datos modificados exitosamente.");
    }

    // Busca y muestra la información de un usuario por su correo electrónico
    private void buscarUsuario(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Busca al usuario en la lista y muestra sus datos (si lo encuentra)
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                System.out.println("Usuario encontrado:");
                System.out.println("Nombre: " + usuario.getNombre());
                System.out.println("Apellido: " + usuario.getApellido());
                System.out.println("Teléfono: " + usuario.getTelefono());
                System.out.println("Sector de trabajo: " + usuario.getSectorTrabajo());
                return;
            }
        }
        System.out.println("Usuario no encontrado.");
    }

    // Elimina un usuario de la lista si coincide con el correo electrónico ingresado
    private void eliminarUsuario(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();

        // Busca y elimina al usuario (si existe)
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().equals(email)) {
                usuarios.remove(i);
                System.out.println("Usuario eliminado exitosamente.");
                return;
            }
        }
        System.out.println("Usuario no encontrado.");
    }

    // Métodos para gestionar proyectos, tareas, incidencias y reportes, delegando al controlador correspondiente
    public void gestionProyectos(Scanner scanner) {
        controladorProyecto.gestionProyectos(scanner);
    }

    public void gestionTareas(Scanner scanner) {
        controladorTarea.gestionTareas(scanner);
    }

    public void gestionIncidencias(Scanner scanner) {
        controladorIncidencia.gestionIncidencias(scanner);
    }

    public void gestionReportes(Scanner scanner) {
        controladorReporte.gestionReportes(scanner);
    }
}
