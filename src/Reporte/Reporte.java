package Reporte;

import Entidades.Entidad;

// Clase Reporte que hereda de Entidad, y representa los reportes que se realizan en base a los distintos proyectos.
public class Reporte extends Entidad {

    private String idProyecto;
    private String descripcion;

    // Constructor de la clase Reporte
    public Reporte(String idReporte, String idProyecto, String descripcion) {
        super(idReporte); // Llama al constructor de la clase padre (Entidad)
        this.idProyecto = idProyecto;
        this.descripcion = descripcion;
    }

    // Métodos Getters y Setters para acceder y modificar los atributos de los repoertes
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
