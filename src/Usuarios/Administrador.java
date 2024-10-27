package Usuarios;

// Clase que representa a un administrador, que es un tipo específico de Usuario
public class Administrador extends Usuario {
    
    // Constructor de la clase Administrador, que inicializa sus atributos heredados de Usuario
    public Administrador(String idAdministrador, String nombre, String apellido, String email, String telefono, String contraseña, String sectorTrabajo) {
        // Llama al constructor de la clase padre (Usuario) para inicializar los atributos
        super(idAdministrador, nombre, apellido, email, telefono, contraseña, sectorTrabajo);
    }
}
