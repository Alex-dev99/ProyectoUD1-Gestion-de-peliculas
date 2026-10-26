# Gestion de Peliculas

API REST para gestionar peliculas, sus directores y sus actores.

## Objetivo

Esta aplicacion permite dar de alta directores, actores y peliculas, y asignar actores al reparto de cada pelicula. Cada pelicula esta asociada a un unico director. El objetivo es ofrecer una API REST sencilla para practicar el desarrollo de servicios con Spring Boot: entidades JPA, validacion de datos, manejo de errores con ProblemDetail y documentacion con OpenAPI.

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- Spring Data JPA
- Spring Validation (Jakarta Bean Validation)
- H2 como base de datos en memoria
- Lombok
- springdoc-openapi 3.1.1 (Swagger UI)
- Maven

## Como ejecutar el proyecto

Requisitos: Java 17 y Maven instalados.

1. Clonar el repositorio o descargar el codigo.
2. Desde la raiz del proyecto, ejecutar:

   ```
   mvn spring-boot:run
   ```

   O bien compilar y ejecutar el jar:

   ```
   mvn clean package
   java -jar target/gestion-peliculas-0.0.1-SNAPSHOT.jar
   ```

3. La API queda disponible en `http://localhost:8080`.
4. La documentacion interactiva (Swagger UI) esta en `http://localhost:8080/swagger-ui/index.html`.
5. La consola de H2 esta en `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:basedatos`, usuario `sa`, sin contrasena).

Al arrancar, la base de datos se crea vacia. No hay datos de ejemplo precargados.

## Modelo de datos

La aplicacion tiene tres entidades: `Director`, `Actor` y `Pelicula`.

**Director**
- id (Long, clave primaria autogenerada)
- nombre (String)
- anioNacimiento (Integer)

**Actor**
- id (Long, clave primaria autogenerada)
- nombre (String)

**Pelicula**
- id (Long, clave primaria autogenerada)
- titulo (String, unico)
- genero (String)
- fechaEstreno (LocalDate)
- director (relacion muchos a uno con `Director`)
- actores (relacion muchos a muchos con `Actor`)

Relaciones:
- Una pelicula tiene un unico director. Un director puede estar asociado a varias peliculas (relacion N:1 desde Pelicula hacia Director).
- Una pelicula puede tener varios actores. Un actor puede participar en varias peliculas (relacion N:M entre Pelicula y Actor).

Las validaciones de los datos de entrada se aplican en los DTOs (`EditActorDTO`, `EditDirectorDTO`, `EditPeliculaDTO`), no en las entidades.

## Endpoints

### Directores (`/api/v1/directores`)

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| POST   | `/api/v1/directores` | Crea un director |
| GET    | `/api/v1/directores` | Lista todos los directores |
| GET    | `/api/v1/directores/{id}` | Obtiene un director por id |
| PUT    | `/api/v1/directores/{id}` | Actualiza un director |
| DELETE | `/api/v1/directores/{id}` | Elimina un director |

### Actores (`/api/v1/actores`)

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| POST   | `/api/v1/actores` | Crea un actor |
| GET    | `/api/v1/actores` | Lista todos los actores |

### Peliculas (`/api/v1/peliculas`)

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| POST   | `/api/v1/peliculas` | Crea una pelicula asociada a un director |
| GET    | `/api/v1/peliculas` | Lista todas las peliculas |
| GET    | `/api/v1/peliculas/{peliculaId}` | Obtiene una pelicula con su director y reparto |
| PUT    | `/api/v1/peliculas/{id}` | Actualiza una pelicula |
| DELETE | `/api/v1/peliculas/{id}` | Elimina una pelicula |
| POST   | `/api/v1/peliculas/{peliculaId}/actores/{actorId}` | Asigna un actor al reparto de una pelicula |

## Ejemplo de peticion y respuesta

### Crear un director

Peticion:

```
POST /api/v1/directores
Content-Type: application/json

{
  "nombre": "Christopher Nolan",
  "anioNacimiento": 1970
}
```

Respuesta (201 Created):

```json
{
  "id": 1,
  "nombre": "Christopher Nolan",
  "anioNacimiento": 1970
}
```

### Crear una pelicula

Peticion:

```
POST /api/v1/peliculas
Content-Type: application/json

{
  "titulo": "Inception",
  "genero": "Sci-Fi",
  "fechaEstreno": "2010-07-16",
  "directorId": 1
}
```

Respuesta (201 Created):

```json
{
  "id": 1,
  "titulo": "Inception",
  "genero": "Sci-Fi",
  "fechaEstreno": "2010-07-16",
  "director": {
    "id": 1,
    "nombre": "Christopher Nolan"
  },
  "actores": []
}
```

### Asignar un actor a una pelicula

Peticion:

```
POST /api/v1/peliculas/1/actores/1
```

Respuesta (201 Created): devuelve la pelicula con el actor ya incluido en el array `actores`.

## Respuestas de error

Los errores se devuelven con el formato `ProblemDetail` de Spring. La estructura incluye `type`, `title`, `status`, `detail` y, cuando aplica, la propiedad `invalid-params` con el detalle de los campos que fallaron.

### 400 Bad Request

Se devuelve cuando los datos enviados no cumplen las validaciones. Por ejemplo:

- Nombre de director o actor vacio.
- Titulo o genero de pelicula vacio.
- `fechaEstreno` o `directorId` ausentes al crear una pelicula.
- Año de nacimiento de director que implica menos de 18 anos.
- Identificador de path menor que 1.

Ejemplo de respuesta:

```json
{
  "type": "https://api/v1/errors/bad-request",
  "title": "Error de validacion",
  "status": 400,
  "detail": "Error de validacion",
  "invalid-params": [
    {
      "object": "editDirectorDTO",
      "message": "El nombre no puede estar vacio",
      "field": "nombre",
      "rejectedValue": ""
    }
  ]
}
```

### 404 Not Found

Se devuelve cuando se busca un recurso que no existe. Por ejemplo:

- `GET /api/v1/directores/{id}` con un id inexistente.
- `GET` o `PUT /api/v1/peliculas/{id}` con un id inexistente.
- `POST /api/v1/peliculas` con un `directorId` que no existe.
- `POST /api/v1/peliculas/{peliculaId}/actores/{actorId}` con una pelicula o un actor inexistente.

Ejemplo de respuesta:

```json
{
  "type": "https://api/v1/errors/not-found",
  "title": "Recurso no encontrado",
  "status": 404,
  "detail": "director no encontrado cuya id sea: 99"
}
```

### 409 Conflict

Se devuelve cuando la operacion entra en conflicto con el estado actual de los datos. Por ejemplo:

- `POST /api/v1/peliculas` con un titulo que ya existe.
- `POST /api/v1/peliculas/{peliculaId}/actores/{actorId}` cuando el actor ya esta asignado a esa pelicula.

Ejemplo de respuesta:

```json
{
  "type": "https://api/v1/errors/already-exists",
  "title": "Recurso ya existente",
  "status": 409,
  "detail": "La pelicula con titulo: Inception ya existe en el sistema"
}
```

## Documentacion interactiva

Con la aplicacion arrancada, la documentacion OpenAPI esta disponible en:

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- JSON OpenAPI: `http://localhost:8080/v3/api-docs`

Cada endpoint incluye su descripcion y los codigos de error previstos.