package Incidencias;

import Entidades.Entidad;

// Clase Incidencia que hereda de Entidad, y representas los problemas que se encuentran en los distintos proyectos del sistema
public class Incidencia extends Entidad {
    private String idProyecto; 
    private String descripcion; 

    // Constructor de la clase Incidencia
    public Incidencia(String idIncidencia, String idProyecto, String descripcion) {
        super(idIncidencia);
        this.idProyecto = idProyecto; 
        this.descripcion = descripcion; 
    }

    // Métodos Getters y Setters para acceder y modificar los atributos de las incidencias
    public String getIdProyecto() {
        return idProyecto; 
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto; 
    }

    public String getDescripcion() {
        return descripcion; 
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; 
    }
}
