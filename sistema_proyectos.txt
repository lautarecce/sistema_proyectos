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
    telefono VARCHAR(50),
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
    estado VARCHAR(50) DEFAULT 'Pendiente',
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
('Usuario');

-- Insertar datos en la tabla sector
INSERT INTO sector (nombre_sector) VALUES 
('Desarrollo'),
('Administración'),
('Soporte');

-- Insertar datos en la tabla estado_proyecto
INSERT INTO estado_proyecto (nombre_estado) VALUES 
('En curso'),
('Finalizado'),
('Suspendido');

-- Insertar datos en la tabla usuario
INSERT INTO usuario (nombre, apellido, email, password, rol_id, sector_id, telefono) VALUES 
('Carlos', 'Recce', 'carlosrecce@gmail.com', 'admin123', 1, 2, '123456789');