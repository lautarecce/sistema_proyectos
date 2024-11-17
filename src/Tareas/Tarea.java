package Tareas;

import Entidades.Entidad;

// Clase Tarea que hereda de Entidad, y representa las tareas del sistema que se asignan a los usuarios.
public class Tarea extends Entidad {

    private String nombre;
    private String estado;
    private String idProyecto;
    private String idUsuario;

    // Constructor que inicializa los atributos de Tarea
    public Tarea(String idTarea, String nombre, String estado, String idProyecto, String idUsuario) {
        super(idTarea);
        this.nombre = nombre;
        this.estado = estado;
        this.idProyecto = idProyecto;
        this.idUsuario = idUsuario;
    }

    // Métodos getter y setter para acceder y modificar los atributos de las tareas
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
