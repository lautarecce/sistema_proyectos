package Entidades;

// Clase base de las clases modelos.
public class Entidad {
    protected String id; // Identificador único de la entidad, protegido para acceso en clases derivadas

    // Constructor que inicializa el id de la entidad
    public Entidad(String id) {
        this.id = id;
    }

    // Método getter para obtener el valor del id
    public String getId() {
        return id;
    }

    // Método setter para modificar el valor del id
    public void setId(String id) {
        this.id = id;
    }
}
