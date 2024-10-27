package Proyectos; 

import java.util.Date; 
import Entidades.Entidad; 

// Clase Proyecto que hereda de la clase Entidad, y representa los proyectos que realizan los usuarios de la sectorial.
public class Proyecto extends Entidad {
    private String nombre; 
    private String descripcion;
    private Date fechaInicio; 

    // Constructor de la clase Proyecto
    public Proyecto(String idProyecto, String nombre, String descripcion, Date fechaInicio) {
        super(idProyecto); // Llama al constructor de la clase padre con el id del proyecto
        this.nombre = nombre; 
        this.descripcion = descripcion; 
        this.fechaInicio = fechaInicio; 
    }

    // Métodos getter y setter para acceder y modificar los atributos de los proyectos

    public String getNombre() {
        return nombre; 
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; 
    }

    public String getDescripcion() {
        return descripcion; 
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; 
    }

    public Date getFechaInicio() {
        return fechaInicio; 
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio; 
    }
}
