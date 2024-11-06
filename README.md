# Proyecto Final de Tokio School: Portal de Valoración de Películas 🎬

Este proyecto implementa un portal web para cinéfilos, donde los usuarios pueden registrar sus películas favoritas, puntuar películas y colaborar en la comunidad. El sistema consta de una aplicación web, un servicio REST y un proceso batch, todos interconectados y desarrollados con el framework **Spring Boot**.

---

Proyecto en desarrollo pueden surgir cambios en el README, pero esta es la vision general del proyecto.

---

## Objetivos del Proyecto

- Construir una plataforma interactiva de valoración de películas, siguiendo una arquitectura modular.
- Implementar un sistema seguro de autenticación y roles para gestionar accesos.
- Desarrollar un servicio REST para manejar valoraciones de usuarios, con seguridad basada en JWT.
- Crear un proceso batch automatizado para la exportación de datos de películas a archivos CSV.

## Tecnologías y Herramientas Utilizadas

### Backend
- **Java 21**: Lenguaje de programación principal.
- **Spring Boot 3.1.5**: Framework para construir aplicaciones Java robustas.
  - **Spring Web**: Para manejar el enrutamiento y los controladores HTTP.
  - **Spring Data JPA**: Para interactuar con la base de datos mediante repositorios.
  - **Spring Security**: Para la configuración de seguridad con autenticación basada en JWT.
  - **Spring Batch**: Para el procesamiento automatizado de tareas (batch processing).
  - **Lombok**: Simplificación de código eliminando boilerplate.

### Frontend
- **Thymeleaf**: Motor de plantillas para generar vistas en HTML integradas con datos del backend.
- **Bootstrap 5.3.2**: Framework CSS para diseño de interfaz y componentes responsive.

### Base de Datos
- **MariaDB 10**: Base de datos relacional utilizada para almacenar usuarios, películas, valoraciones y otros datos de la aplicación.
- **Docker**: Contenedor para desplegar y gestionar el servidor de base de datos en desarrollo y producción.

### Infraestructura y DevOps
- **Docker**: Contenedores para la aplicación y el servidor de base de datos, facilitando el despliegue y la escalabilidad.
- **Git**: Control de versiones del código.
- **Maven 3.9.5**: Herramienta de gestión de dependencias y compilación.

## Arquitectura del Proyecto

El proyecto se estructura en tres módulos independientes pero conectados:

1. **Aplicación Web**: Permite a los usuarios registrarse, iniciar sesión, buscar películas y añadir valoraciones. Se ha utilizado Spring Boot, Thymeleaf, y Bootstrap para la interfaz gráfica.
2. **Servicio REST**: Una API REST segura para la gestión de valoraciones de películas, diseñada para ser consumida por la aplicación web. Usa autenticación con JWT y gestiona las operaciones de CRUD de valoraciones.
3. **Proceso Batch**: Un proceso que se ejecuta periódicamente para exportar las películas a un archivo CSV. Este proceso se ejecuta como un job automático utilizando Spring Batch.

## Estructura del Código

La organización de este repositorio sigue un esquema modular para mayor claridad y mantenibilidad:

- **/web-application**: Código fuente de la aplicación web.
- **/rest-service**: Código fuente del servicio REST.
- **/batch-process**: Código fuente del proceso batch.
- **/docker**: Archivos de configuración de Docker para contenedores de base de datos y despliegue.
- **/sql**: Scripts SQL para la inicialización de la base de datos.

## Instalación y Ejecución del Proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/mattyys/filmWeb.git
   cd filmWeb
   ```

2. Construye el proyecto con Maven:
   ```bash
   mvn clean install
   ```

3. Ejecuta los contenedores Docker (requiere Docker instalado):
   ```bash
   docker-compose up -d
   ```

4. Ejecuta cada módulo de Spring Boot:
   ```bash
   # En cada subdirectorio (web-application, rest-service, batch-process)
   mvn spring-boot:run
   ```

## Habilidades Desarrolladas

A lo largo del desarrollo de este proyecto, se han fortalecido las siguientes habilidades:

- **Desarrollo Backend con Spring Boot**: Integración de Spring Web, JPA, y Batch en un sistema modular.
- **Seguridad en API REST**: Implementación de autenticación y autorización con JWT.
- **Despliegue de Contenedores con Docker**: Creación y administración de contenedores para el entorno de base de datos y la aplicación.
- **Control de Versiones con Git**: Gestión de cambios y colaboración en equipo.
- **Pruebas Unitarias**: Creación de tests unitarios para garantizar la robustez de los servicios y controladores.
- **Automatización de Procesos**: Configuración y ejecución de procesos batch con Spring Batch.
- **Diseño Frontend**: Desarrollo de interfaces de usuario responsivas y amigables con Thymeleaf y Bootstrap.

## Contribuciones

Las contribuciones a este proyecto son bienvenidas. Si deseas colaborar, por favor sigue los siguientes pasos:

1. Crea un fork del repositorio.
2. Crea una rama de trabajo (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -m 'Descripción de los cambios'`).
4. Envía un pull request al repositorio principal.

## Contacto

Para cualquier duda o sugerencia, no dudes en contactarme a través de [correo electrónico] o en mis redes sociales.

---

**¡Gracias por tu interés en el proyecto!**

---