# 📦 Sistema de Inventario — Spring Boot + PostgreSQL

> API REST desarrollada con arquitectura en capas (Layered Architecture), siguiendo buenas prácticas de desarrollo con Spring Boot 3.x, JPA/Hibernate y PostgreSQL.

------

## 📑 Índice

1. [🚀 Cómo ejecutar el proyecto](#-cómo-ejecutar-el-proyecto)
2. [🔍 Instalación de Swagger](#-instalación-de-swagger)
3. [⚙️ Configuración application.properties](#️-configuración-applicationproperties)
4. [🛠️ Tecnologías](#️-tecnologías)
5. [🗃️ Entidades del sistema](#️-entidades-del-sistema)
6. [🗂️ Estructura del proyecto](#️-estructura-del-proyecto)
7. [🏛️ Arquitectura que usamos](#️-arquitectura-que-usamos)

---

## 🚀 Cómo ejecutar el proyecto

### Requisitos previos
- Java 21 instalado
- Maven instalado
- PostgreSQL corriendo con la base de datos `db_inventario` creada

### Paso 1 — Limpia y compila el proyecto
```bash
mvn clean install
```

### Paso 2 — Corre el proyecto
```bash
mvn spring-boot:run
```

### Paso 3 — Verifica que levantó correctamente
En la consola debes ver:
```
Started InventarioApplication in X.XXX seconds
```

### Paso 4 — Abre Swagger en el navegador
```
http://localhost:8080/swagger-ui.html
```

> ⚠️ Si el proyecto no levanta, revisa que el `application.properties` tenga los datos correctos de tu PostgreSQL.

---

## 🔍 Instalación de Swagger

Swagger permite probar todos los endpoints de la API desde el navegador sin necesidad de Postman.

### 1. Agrega la dependencia en `pom.xml`

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

### 2. Agrega en `application.properties`

```properties
# Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
```

### 3. URL de acceso

```
http://localhost:8080/swagger-ui.html
```

---

## ⚙️ Configuración `application.properties`

Ubicación: `src/main/resources/application.properties`

```properties
# =============================================
# Conexión a PostgreSQL
# =============================================
spring.datasource.url=jdbc:postgresql://localhost:5432/db_inventario
spring.datasource.username=postgres
spring.datasource.password=tu_password

# =============================================
# JPA / Hibernate
# =============================================
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# =============================================
# Servidor
# =============================================
server.port=8080
spring.application.name=Inventario

# =============================================
# Swagger
# =============================================
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
```

> ⚠️ Cambia `tu_password` por tu contraseña real de PostgreSQL.

---

## 🛠️ Tecnologías

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Spring Boot | 3.5.12 | Framework principal |
| Spring Data JPA | 3.x | Acceso a datos |
| Hibernate | 6.x | ORM (mapeo objeto-relacional) |
| PostgreSQL | 15+ | Base de datos |
| Lombok | Latest | Reducción de código repetitivo |
| Maven | 3.x | Gestión de dependencias |
| SpringDoc OpenAPI | 2.5.0 | Documentación y pruebas con Swagger |

---

## 🗃️ Entidades del sistema

| Entidad | Tabla | Descripción |
|---|---|---|
| `Rol` | `roles` | Roles del sistema (ADMIN, VENDEDOR, etc.) |
| `Categoria` | `categorias` | Categorías de productos |
| `Producto` | `productos` | Productos del inventario |
| `Usuario` | `usuarios` | Usuarios del sistema |
| `Proveedor` | `proveedores` | Proveedores de productos |
| `SalidaInventario` | `salidas_inventario` | Cabecera de salidas |
| `DetalleSalida` | `detalle_salida` | Líneas de cada salida |
| `EntradaInventario` | `entradas_inventario` | Cabecera de entradas |
| `DetalleEntrada` | `detalle_entrada` | Líneas de cada entrada |

---

## 🗂️ Estructura del proyecto

```
src/main/java/com/sistema/Inventario/
│
├── InventarioApplication.java     ← Clase principal, punto de entrada
│
├── Controllers/
│   ├── RolController.java
│   ├── CategoriaController.java
│   └── ...
│
├── DTOs/
│   ├── RolDTO.java
│   ├── CategoriaDTO.java
│   └── ...
│
├── models/
│   ├── Rol.java
│   ├── Categoria.java
│   └── ...
│
├── Repository/
│   ├── RolRepository.java
│   ├── CategoriaRepository.java
│   └── ...
│
└── Services/
    ├── IRolService.java
    ├── ICategoriaService.java
    └── Impl/
        ├── RolServiceImple.java
        ├── CategoriaServiceImple.java
        └── ...
```

---

## 🏛️ Arquitectura que usamos

Este proyecto implementa la **Arquitectura en Capas (Layered Architecture)**, también conocida como **N-Tier Architecture**. Es el patrón más utilizado en aplicaciones empresariales con Spring Boot.

```
[ Angular / Postman / Swagger ]
            ↓
      [ Controller ]   →  Recibe la petición HTTP
            ↓
         [ DTO ]       →  Valida los datos de entrada
            ↓
      [ IService ]     →  Define el contrato (interfaz)
            ↓
     [ ServiceImpl ]   →  Ejecuta la lógica de negocio
            ↓
     [ Repository ]    →  Accede a la base de datos
            ↓
        [ Model ]      →  Representa la tabla
            ↓
      [ PostgreSQL ]
```

### ¿Para qué sirve cada capa?

| Archivo | Responsabilidad |
|---|---|
| `Model` | Representa la tabla de BD. Sin lógica de negocio |
| `DTO` | Datos que viajan por HTTP. Contiene las validaciones |
| `Repository` | Único punto de acceso a la BD. Hereda CRUD de JpaRepository |
| `IService` | Interfaz que define el contrato de operaciones disponibles |
| `ServiceImpl` | Toda la lógica de negocio vive aquí |
| `Controller` | Recibe HTTP, llama al Service, devuelve respuesta |

### ⚠️ La regla de oro

```
Controller  →  solo habla con  →  Service
Service     →  solo habla con  →  Repository
Repository  →  solo habla con  →  Base de datos
```

> ❌ Nunca el Controller llama directamente al Repository.
> ✅ Siempre pasa por el Service.

### ✅ ¿Por qué es buena práctica?

| Ventaja | Descripción |
|---|---|
| **Organización** | Cada archivo tiene una sola responsabilidad (principio SRP) |
| **Mantenimiento** | Si cambia la BD, solo tocas el Repository |
| **Escalabilidad** | Puedes crecer sin romper lo que ya funciona |
| **Testeable** | Cada capa se puede probar de forma independiente |
| **Trabajo en equipo** | Cada desarrollador puede trabajar en una capa distinta |

---

*Proyecto desarrollado con fines de aprendizaje — Spring Boot + Angular + PostgreSQL*
