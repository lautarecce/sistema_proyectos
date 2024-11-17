package Reporte;

import Entidades.Entidad; // Importa la clase Entidad, de la cual Reporte heredará.

 // Clase Reporte que hereda de Entidad, y representa los reportes que se realizan en base a los distintos proyectos.
public class Reporte extends Entidad {

    private String idProyecto; // Atributo que almacena el ID del proyecto asociado al reporte.
    private String descripcion; // Atributo que almacena la descripción del reporte.

    // Constructor de la clase Reporte
    public Reporte(String idReporte, String idProyecto, String descripcion) {
        super(idReporte); // Llama al constructor de la clase padre (Entidad) para inicializar el ID del reporte.
        this.idProyecto = idProyecto; // Inicializa el ID del proyecto.
        this.descripcion = descripcion; // Inicializa la descripción del reporte.
    }

    // Métodos Getters y Setters para acceder y modificar los atributos de los reportes
    public String getIdProyecto() {
        return idProyecto; // Devuelve el ID del proyecto.
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto; // Establece un nuevo ID para el proyecto.
    }

    public String getDescripcion() {
        return descripcion; // Devuelve la descripción del reporte.
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; // Establece una nueva descripción para el reporte.
    }
}