# tiendagrupo4frontend
Frontend (REST Client) elaborado con Spring Boot para la tienda virtual del equipo 4 del grupo 3 ciclo 3.

## Requisitos Técnicos
- Java JDK 11, Apache Maven 3, Apache Tomcat 9, MySQL 8.
- En eclipse IDE, se puede importar como proyecto de Maven.
- En el archivo *application.properties* se especifica el puerto donde se ejecuta la aplicación.
- También, en el archivo *application.properties* se especifica el URL de la API Backend.
- Para iniciar sesión, se elige un *usuario* y su *password* de la tabla *usuarios* en la base de datos MySQL.

```Java Properties
server.port = 8080

servidorapi.url = http://localhost:5000
```