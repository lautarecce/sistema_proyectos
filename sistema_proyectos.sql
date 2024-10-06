-- Creamos la base de datos
CREATE DATABASE sistema_proyectos;

-- Utilizamos la base de datos creada
USE sistema_proyectos;

-- Tabla para roles
CREATE TABLE rol (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL
);

-- Tabla para sectores
CREATE TABLE sector (
    id_sector INT AUTO_INCREMENT PRIMARY KEY,
    nombre_sector VARCHAR(50) NOT NULL
);

-- Tabla para estados de proyecto
CREATE TABLE estado_proyecto (
    id_estado INT AUTO_INCREMENT PRIMARY KEY,
    nombre_estado VARCHAR(50) NOT NULL
);

-- Tabla para usuarios
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    rol_id INT,
    sector_id INT,
    FOREIGN KEY (rol_id) REFERENCES rol(id_rol),
    FOREIGN KEY (sector_id) REFERENCES sector(id_sector)
);

-- Tabla para proyectos
CREATE TABLE proyecto (
    id_proyecto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_proyecto VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha_inicio TIMESTAMP,
    fecha_fin TIMESTAMP,
    estado_id INT,
    gestor_id INT,
    FOREIGN KEY (estado_id) REFERENCES estado_proyecto(id_estado),
    FOREIGN KEY (gestor_id) REFERENCES usuario(id_usuario)
);

-- Tabla para tareas
CREATE TABLE tarea (
    id_tarea INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tarea VARCHAR(255) NOT NULL,
    descripcion TEXT,
    fecha_inicio TIMESTAMP,
    fecha_fin TIMESTAMP,
    proyecto_id INT,
    asignado_a INT,
    FOREIGN KEY (proyecto_id) REFERENCES proyecto(id_proyecto) ON DELETE CASCADE,
    FOREIGN KEY (asignado_a) REFERENCES usuario(id_usuario)
);

-- Tabla para comentarios
CREATE TABLE comentario (
    id_comentario INT AUTO_INCREMENT PRIMARY KEY,
    comentario TEXT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id INT,
    tarea_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    FOREIGN KEY (tarea_id) REFERENCES tarea(id_tarea) ON DELETE CASCADE
);

-- Tabla para historial de cambios
CREATE TABLE historial_cambios (
    id_cambio INT AUTO_INCREMENT PRIMARY KEY,
    entidad VARCHAR(100),
    descripcion_cambio TEXT,
    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_entidad INT,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Tabla para asignaciones
CREATE TABLE asignaciones (
    id_asignaciones INT AUTO_INCREMENT PRIMARY KEY,
    fecha_asignacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tarea_id INT,
    usuario_id INT,
    FOREIGN KEY (tarea_id) REFERENCES tarea(id_tarea) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE
);

-- Insertar datos en la tabla rol
INSERT INTO rol (nombre_rol) VALUES 
('Administrador'),
('Gestor'),
('Usuario');

-- Insertar datos en la tabla sector
INSERT INTO sector (nombre_sector) VALUES 
('Desarrollo'),
('Administración'),
('Soporte');

-- Insertar datos en la tabla estado_proyecto
INSERT INTO estado_proyecto (nombre_estado) VALUES 
('En progreso'),
('Finalizado'),
('En planificación');

-- Insertar datos en la tabla usuario
INSERT INTO usuario (nombre, apellido, email, password, rol_id, sector_id) VALUES 
('Ramiro', 'Fernández', 'ramafer@gmail.com', 'casa123', 1, 2),
('María', 'Castaña', 'maria.castaña@gmail.com', 'pala456', 2, 3),
('Carlos', 'Sánchez', 'carlos.sanchez@gmail.com', 'avion789', 3, 1),
('Ana', 'Simino', 'ana.simino@gmail.com', 'mesa123', 1, 1),
('Pedro', 'Guastavino', 'pedroguasta@gmail.com', 'ropero456', 2, 1);

-- Insertar datos en la tabla proyecto
INSERT INTO proyecto (nombre_proyecto, descripcion, fecha_inicio, fecha_fin, estado_id, gestor_id) VALUES 
('Proyecto Farmamed', 'Solicitud de medicamentos por vía de excepción provinciales', '2024-01-05 09:00:00', '2023-06-30 17:00:00', 1, 1),
('Proyecto Premo', 'Solicitud de medicamentos oncológicos provinciales', '2023-05-10 10:03:14', '2023-07-30 17:00:00', 2, 2),
('Proyecto Siraco', 'Gestión de ración de alimentos en cocido', '2023-12-05 09:00:00', '2023-09-30 17:21:00', 3, 3),
('Proyecto Sicap', 'Gestión de facturación hospitalaria provincial', '2024-04-01 09:00:00', '2023-12-31 09:07:00', 1, 1),
('Proyecto Diagnose', 'Gestión de historia clínica web de pacientes de hospitales públicos provinciales', '2023-12-12 06:50:00', '2023-11-30 17:00:00', 2, 2);

-- Insertar datos en la tabla tarea
INSERT INTO tarea (nombre_tarea, descripcion, fecha_inicio, fecha_fin, proyecto_id, asignado_a) VALUES 
('Agregar reporte de solicitudes de Misoprostol', 'Reporte trimestral sobre medicamento de solicitud', '2023-01-01 09:34:00', '2023-02-01 17:12:00', 1, 1),
('Registrar firma automática para auditoría', 'Firma automática para la comisión provincial de medicamento', '2023-01-15 12:23:00', '2023-03-01 17:00:22', 1, 2),
('Añadir nuevos medicamentos a la lista desplegable', 'Agregar en la base de datos correspondiente los nuevos medicamentos', '2023-02-01 09:12:00', '2023-04-01 13:00:12', 2, 3),
('Cambiar logo de Santa Fe provincia', 'Se debe reemplazar el logo de la gestión anterior por la de la gestión actual', '2023-03-01 08:14:00', '2023-05-01 17:22:00', 3, 1),
('Configurar el cálculo automático de la facturación de los empleados', 'Calcular el 2% sobre el total facturado del hospital para abonarlo por recibo a los empleados', '2023-04-01 13:12:11', '2023-06-01 12:33:33', 4, 2);

-- Insertar datos en la tabla comentario
INSERT INTO comentario (comentario, fecha_creacion, usuario_id, tarea_id) VALUES 
('Se terminó de armar el script para ejecutar la consulta', CURRENT_TIMESTAMP, 3, 1),
('Esperar a que se firme la resolución correspondiente', CURRENT_TIMESTAMP, 2, 2),
('El frontend ya esta hecho, solo falta armar el script', CURRENT_TIMESTAMP, 1, 3),
('Tarea realizada', CURRENT_TIMESTAMP, 5, 4),
('Utilizar Javascript', CURRENT_TIMESTAMP, 4, 5);

-- Insertar datos en la tabla historial_cambios
INSERT INTO historial_cambios (entidad, descripcion_cambio, id_entidad, usuario_id) VALUES 
('proyecto', 'Se creó el proyecto "Farmamed"', 1, 1),
('tarea', 'Se creó la tarea "Agregar reporte de solicitudes de Misoprostol"', 1, 1),
('usuario', 'Se agregó a "Ramiro Fernández"', 1, 1),
('usuario', 'Se agregó a "María Castaña"', 2, 1),
('proyecto', 'Se completó el proyecto "Farmamed"', 1, 1);

-- Insertar datos en la tabla asignaciones
INSERT INTO asignaciones (tarea_id, usuario_id) VALUES 
(1, 2),
(2, 3),
(3, 3),
(4, 1),
(5, 2);

-- Consultar todos los usuarios
SELECT * FROM usuario;

-- Consultar todos los proyectos
SELECT * FROM proyecto;

-- Consultar todas las tareas de un proyecto específico
SELECT * FROM tarea WHERE proyecto_id = 1;

-- Borrar un proyecto por id (esto también borrará tareas y comentarios asociados)
DELETE FROM proyecto WHERE id_proyecto = 4;

-- Borrar un usuario por id (esto también borrará comentarios asociados)
DELETE FROM usuario WHERE id_usuario = 5;

-- Borrar un comentario por id
DELETE FROM comentario WHERE id_comentario = 3;
