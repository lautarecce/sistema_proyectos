package Usuarios;

import Entidades.Entidad;

// Clase Usuario que hereda de Entidad, representa un usuario con información personal y laboral
public class Usuario extends Entidad {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String contraseña;
    private String sectorTrabajo;

    // Constructor que inicializa el usuario con sus atributos principales
    public Usuario(String idUsuario, String nombre, String apellido, String email, String telefono, String contraseña, String sectorTrabajo) {
        super(idUsuario); // Llama al constructor de la clase base Entidad con el idUsuario
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.sectorTrabajo = sectorTrabajo;
    }

    // Métodos Getters y Setters para acceder y modificar los atributos del usuario
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña ;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getSectorTrabajo() {
        return sectorTrabajo;
    }

    public void setSectorTrabajo(String sectorTrabajo) {
        this.sectorTrabajo = sectorTrabajo;
    }

    // Método para modificar algunos datos del usuario
    public void modificarDatos(String nombre, String apellido, String telefono, String sectorTrabajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.sectorTrabajo = sectorTrabajo;
    }
}
