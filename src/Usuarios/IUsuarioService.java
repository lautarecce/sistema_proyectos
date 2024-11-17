package Usuarios;

import java.util.Scanner; // Importa la clase Scanner para la entrada del usuario.

public interface IUsuarioService {
    // Método para iniciar sesión, retorna true si el inicio de sesión es exitoso.
    boolean iniciarSesion(Scanner scanner);

    // Método para gestionar las operaciones relacionadas con los usuarios.
    void gestionUsuario(Scanner scanner);

    // Método para registrar un nuevo usuario.
    void registrarUsuario(Scanner scanner);

    // Método para modificar los datos de un usuario existente.
    void modificarDatos(Scanner scanner);

    // Método para buscar un usuario por algún criterio (por ejemplo, ID o nombre).
    void buscarUsuario(Scanner scanner);

    // Método para eliminar un usuario del sistema.
    void eliminarUsuario(Scanner scanner);
}