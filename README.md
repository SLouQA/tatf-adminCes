# Automatización de pruebas funcionales - adminCes

Framework de automatización de pruebas funcionales para la plataforma adminCes, desarrollado con Java, Selenium WebDriver, JUnit 5 y Maven.

## Índice

1. [Interfaces](#interfaces)
2. [Patrones aplicados](#patrones-aplicados)
3. [Estructura de carpetas](#estructura-de-carpetas)
4. [Organización por módulo](#organización-por-módulo)
5. [Configuración y ejecución](#configuración-y-ejecución)
6. [Casos de prueba automatizados](#casos-de-prueba-automatizados)
7. [Datos de prueba (CSV)](#datos-de-prueba-csv)
8. [Guiones de prueba](#guiones-de-prueba)
    - Funciones auxiliares
        - [Ingresar al sitio](#ingresar-al-sitio)
        - [Login](#login)
        - [Buscar usuario en el listado](#buscar-usuario-en-el-listado)
    - Casos de prueba
        - [Crear cuenta de administrador](#crear-cuenta-de-administrador)
        - [Crear administrador con email repetido](#crear-administrador-con-email-repetido)
        - [Crear cuenta de tester](#crear-cuenta-de-tester)
        - [Crear tester con email repetido](#crear-tester-con-email-repetido)
        - [Eliminar usuario tester](#eliminar-usuario-tester)
        - [Reiniciar contraseña](#reiniciar-contraseña)
        - [Reiniciar contraseña de un usuario no administrador](#reiniciar-contraseña-de-un-usuario-no-administrador)
        - [Reiniciar contraseña con contraseñas distintas](#reiniciar-contraseña-con-contraseñas-distintas)
        - [Reiniciar contraseña con formulario vacío](#reiniciar-contraseña-con-formulario-vacío)
        - [Reiniciar contraseña de un usuario inexistente](#reiniciar-contraseña-de-un-usuario-inexistente)

## Interfaces

| Entidad  | Descripción                                                                                                       |
|----------|-------------------------------------------------------------------------------------------------------------------|
| IBrowser | Interfaz que define operaciones comunes para la gestión del navegador y la espera de elementos en una página web. |
| IVerify  | Interfaz que define métodos para verificar resultados esperados con obtenidos y agregar mensajes asociados.       |

## Patrones aplicados

| Patrón            | Entidad                   | Descripción                                                                                   |
|-------------------|---------------------------|-----------------------------------------------------------------------------------------------|
| Factory           | DriverManagerFactory      | Clase encargada de indicar que driver se debe retornar.                                       |
| Singleton         | DriverManagerSingleton    | Clase encargada de mantener una sola instancia configurada con un driver.                     |
| Page Object Model | Clases `*PO` (`pom`)      | Representan las pantallas y encapsulan la interacción con sus elementos.                      |
| Task              | Clases `*Task` (`task`)   | Agrupan secuencias de acciones del usuario y las verificaciones asociadas a cada flujo.       |
| Data              | Clases `*Data` (`data`)   | Centralizan los datos de prueba y los mensajes esperados, separándolos de la lógica del test. |
| Data-driven       | Archivos `.csv`           | Los casos parametrizados leen sus datos de archivos en `src/test/resources`.                  |
| Fluent Interface  | Element, Interaction      | Los métodos devuelven la propia instancia para poder encadenar acciones.                      |

## Estructura de carpetas

```
src
├── main
│   ├── java/com/tatf/core
│   │   ├── browser        IBrowser, BrowserImpl, BrowserFactory
│   │   ├── driver
│   │   │   ├── factory    DriverManagerFactory, DriverType
│   │   │   ├── instance   DriverManagerSingleton
│   │   │   └── manager    DriverManager, ChromeDriver
│   │   ├── element        Element
│   │   ├── find           Find
│   │   ├── interaction    Interaction
│   │   ├── util           ResourceLoader, ConfigReader
│   │   ├── verification   IVerify, VerifyImpl
│   │   └── wait           Wait
│   └── resources/js       highlight.js
└── test
    ├── java/com/tatf/adminCes
    │   ├── base           data, pom, task, test
    │   ├── createUser     data, pom, task, test
    │   ├── deletUs        data, pom, task, test
    │   ├── login          data, pom, task
    │   ├── resetPassAdmin data, pom, task, test
    │   └── viewUs         pom, task
    └── resources          archivos .csv de los casos parametrizados
```

## Organización por módulo

Cada módulo de `com.tatf.adminCes` se divide en capas:

| Capa | Paquete | Contenido                                                                  |
|------|---------|----------------------------------------------------------------------------|
| Data | `data`  | Constantes: datos de prueba y mensajes esperados.                          |
| POM  | `pom`   | Page Objects: localización e interacción con los elementos de la pantalla. |
| Task | `task`  | Flujos de acciones y métodos de verificación.                              |
| Test | `test`  | Casos de prueba. Todos extienden `BaseTest`.                               |

| Módulo         | Descripción                                                                                           |
|----------------|-------------------------------------------------------------------------------------------------------|
| base           | Ingreso al sitio, mensajes de modal (`GeneralPO`) y `BaseTest`, que abre y cierra el navegador.       |
| login          | Inicio de sesión del administrador.                                                                   |
| createUser     | Alta de administradores y de testers (Senior, Junior y Líder).                                        |
| viewUs         | Listado de usuarios ("Ver usuarios") y búsqueda de una fila por nombre, apellido, email y rol (comparación exacta).                         |
| deletUs        | Eliminación de usuarios.                                                                              |
| resetPassAdmin | Reinicio de contraseña de administradores.                                                            |

## Configuración y ejecución

Los datos de acceso se leen del archivo `config.properties` mediante `ConfigReader`. Las claves usadas son:

| Clave                    | Contenido                                |
|--------------------------|------------------------------------------|
| `adminces.url`           | URL del sitio.                           |
| `adminces.passUrl`       | Contraseña para ingresar al sitio.       |
| `adminces.usuarioAdmin`  | Email del administrador que inicia sesión. |
| `adminces.contrasenia`   | Contraseña de ese administrador.         |

El navegador se elige con la propiedad del sistema `browser` (por defecto `CHROME`).

Para ejecutar todos los tests:

```
mvn test
```

Notas:

- `BaseTest` abre el navegador antes de cada test (`@BeforeEach`) y lo cierra al terminar (`@AfterEach`), por lo que cada ejecución arranca con el sitio limpio y los casos deben ser independientes entre sí.
- `BaseTest` crea el navegador en modo debug (`getBrowser(true)`): resalta cada elemento al buscarlo y hace una pausa de 2 segundos por búsqueda. Para ejecuciones más rápidas se puede pasar `false`.
- No subir al repositorio contraseñas reales dentro de `config.properties`.

## Casos de prueba automatizados

| Guion                                               | Clase              | Método                       | CSV                     | Tipo     |
|-----------------------------------------------------|--------------------|------------------------------|-------------------------|----------|
| Crear cuenta de administrador                       | CreateAdminTest    | `crearCuentaAdmin`           | `crearAdmin.csv`        | Positivo |
| Crear administrador con email repetido              | CreateAdminTest    | `crearCuentaTestereEmailRep` | `crearAdminEmailR.csv`  | Negativo |
| Crear cuenta de tester                              | CreateTesterTest   | `crearCuentaTester`          | `crearTester.csv`       | Positivo |
| Crear tester con email repetido                     | CreateTesterTest   | `crearCuentaTestereEmailRep` | `crearTesterEmailR.csv` | Negativo |
| Eliminar usuario tester                             | DeletUsTest        | `eliminarCuentaTester`       | `deletUs.csv`           | Positivo |
| Reiniciar contraseña                                | ResetPassAdminTest | `resetPassAdminOk`           | `resetPassAdminOK.csv`  | Positivo |
| Reiniciar contraseña de un usuario no administrador | ResetPassAdminTest | `resetPassNoAdmin`           | `resetPassTester.csv`   | Negativo |
| Reiniciar contraseña con contraseñas distintas      | ResetPassAdminTest | `resetPassNoIgual`           | `resetPassNoIgual.csv`  | Negativo |
| Reiniciar contraseña con formulario vacío           | ResetPassAdminTest | `resetFormVacio`             | `resetFormVacio.csv`    | Negativo |
| Reiniciar contraseña de un usuario inexistente      | ResetPassAdminTest | `resetUsNoExiste`            | Sin CSV (datos fijos)   | Negativo |

## Datos de prueba (CSV)

Los archivos están en `src/test/resources`, usan `;` como separador, se guardan en UTF-8 y su primera fila es el encabezado.

| Archivo                 | Columnas                                                                                                                          |
|-------------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| `crearAdmin.csv`        | nombre, apellido, email, contrasenia, pais                                                                                        |
| `crearAdminEmailR.csv`  | nombre, apellido, email, contrasenia, pais, nombreD, apellidoD, emailR, contraseniaD, paisR                                       |
| `crearTester.csv`       | nombre, apellido, email, contrasenia, pais, idRol, textoRol                                                                       |
| `crearTesterEmailR.csv` | nombre, apellido, email, contrasenia, pais, idRol, nombreD, apellidoD, emailR, contraseniaD, paisR, idRol2                        |
| `deletUs.csv`           | nombre, apellido, email, rol, validEmail, validRol                                                                                |
| `resetPassAdminOK.csv`  | email, contrasenia, repCont                                                                                                       |
| `resetPassTester.csv`   | email, contrasenia, repCont                                                                                                       |
| `resetPassNoIgual.csv`  | email, contrasenia, repCont                                                                                                       |
| `resetFormVacio.csv`    | email, contrasenia, repCont                                                                                                       |

Reglas para agregar un caso nuevo:

- La cantidad de columnas del CSV debe coincidir con la cantidad de parámetros del método.
- Los parámetros se asignan por posición, no por nombre.
- Un campo vacío debe escribirse entre comillas (`""`). Si se deja sin nada, JUnit pasa `null` y `sendKeys` falla.

## Guiones de prueba

Trello - https://trello.com/b/jKzyFOT7/tatf

Convenciones:

- `{{variable}}`: dato que cambia en cada ejecución.
- `[[Función(...)]]`: llamada a una función auxiliar.
- Las credenciales del administrador (`emailAdmin`, `contraseniaAdmin`) se toman de `config.properties`.

### Funciones auxiliares

#### Ingresar al sitio

```text
Nombre: Ingresar al sitio
Descripción: Acceder a la aplicación adminCes con la contraseña del sitio
Parámetros (variables): navegador, URL, contraseniaSitio
Pasos:

* Paso 1: Abrir {{navegador}}
* Paso 2: Ingresar a {{URL}}
* Paso 3: Hacer click en el campo "Contraseña"
* Paso 4: Ingresar {{contraseniaSitio}}
* Paso 5: Hacer click en el botón de ingreso
* Paso 6: Validar título "Taller de Automatización del Testing Funcional"
```

#### Login

```text
Nombre: Login
Descripción: Iniciar sesión como administrador
Parámetros (variables): email, contrasenia
Pasos:

* Paso 1: Hacer click en "Iniciar sesión"
* Paso 2: Ingresar {{email}} en el campo "Email"
* Paso 3: Ingresar {{contrasenia}} en el campo "Contraseña"
* Paso 4: Hacer click en el botón de inicio de sesión del formulario
* Paso 5: Validar modal con texto "Sesión iniciada."
* Paso 6: Hacer click en "OK"
```

#### Buscar usuario en el listado

```text
Nombre: Buscar usuario en el listado
Descripción: Ubicar en la tabla de usuarios la fila que coincide con los datos indicados
Precondiciones: Haber ingresado al listado de usuarios (click en "Ver usuarios")
Parámetros (variables): nombre, apellido, email, rol
Pasos:

* Paso 1: Esperar a que se muestre la tabla de usuarios
* Paso 2: Recorrer las filas de la tabla
* Paso 3: Comparar las columnas Nombre, Apellido, Email y Perfil con {{nombre}}, {{apellido}}, {{email}} y {{rol}} (coincidencia exacta: distingue mayúsculas, minúsculas y tildes)
* Paso 4: Devolver la fila que coincide en las cuatro columnas, o ninguna si no hay coincidencia
```

### Casos de prueba

#### Crear cuenta de administrador

```text
Nombre: Crear cuenta de administrador
Descripción: Registrar un nuevo administrador y comprobar que puede iniciar sesión
Precondiciones: Contar con el navegador instalado, Contar con conexión a internet, Que el email {{email}} no esté registrado
Variables: nombre, apellido, email, contrasenia, pais
Funciones auxiliares: Ingresar al sitio, Login
Pasos:

* Paso 1: [[Ingresar al sitio("Chrome", "http://cestore.ces.com.uy/adminces/", contraseniaSitio)]]
* Paso 2: Hacer click en "Registrarse"
* Paso 3: Ingresar {{nombre}} en el campo "Nombre"
* Paso 4: Ingresar {{apellido}} en el campo "Apellido"
* Paso 5: Ingresar {{email}} en el campo "Email"
* Paso 6: Ingresar {{contrasenia}} en el campo "Contraseña"
* Paso 7: Ingresar {{contrasenia}} en el campo "Repetir contraseña"
* Paso 8: Ingresar {{pais}} en el campo "País"
* Paso 9: Hacer click en el botón de registro
* Paso 10: Validar modal con texto "Usuario creado."
* Paso 11: Hacer click en "OK"
* Paso 12: Validar realizando [[Login({{email}}, {{contrasenia}})]]
```

#### Crear administrador con email repetido

```text
Nombre: Crear administrador con email repetido
Descripción: Comprobar que no se puede registrar un segundo administrador con un email ya registrado
Precondiciones: Contar con el navegador instalado, Contar con conexión a internet, Que el email {{email}} no esté registrado
Variables: nombre, apellido, email, contrasenia, pais, nombreD, apellidoD, emailR, contraseniaD, paisR
Funciones auxiliares: Ingresar al sitio
Pasos:

* Paso 1: [[Ingresar al sitio("Chrome", "http://cestore.ces.com.uy/adminces/", contraseniaSitio)]]
* Paso 2: Hacer click en "Registrarse"
* Paso 3: Ingresar {{nombre}} en el campo "Nombre"
* Paso 4: Ingresar {{apellido}} en el campo "Apellido"
* Paso 5: Ingresar {{email}} en el campo "Email"
* Paso 6: Ingresar {{contrasenia}} en el campo "Contraseña"
* Paso 7: Ingresar {{contrasenia}} en el campo "Repetir contraseña"
* Paso 8: Ingresar {{pais}} en el campo "País"
* Paso 9: Hacer click en el botón de registro
* Paso 10: Validar modal con texto "Usuario creado."
* Paso 11: Hacer click en "OK"
* Paso 12: Hacer click en "Registrarse"
* Paso 13: Ingresar {{nombreD}} en el campo "Nombre"
* Paso 14: Ingresar {{apellidoD}} en el campo "Apellido"
* Paso 15: Ingresar {{emailR}} (igual a {{email}}) en el campo "Email"
* Paso 16: Ingresar {{contraseniaD}} en el campo "Contraseña"
* Paso 17: Ingresar {{contraseniaD}} en el campo "Repetir contraseña"
* Paso 18: Ingresar {{paisR}} en el campo "País"
* Paso 19: Hacer click en el botón de registro
* Paso 20: Validar modal con texto "Usuario existe."
* Paso 21: Hacer click en "OK"
```

#### Crear cuenta de tester

```text
Nombre: Crear cuenta de tester
Descripción: Registrar un tester con cada rol y comprobar que aparece en el listado de usuarios
Precondiciones: Contar con el navegador instalado, Contar con conexión a internet, Contar con un usuario administrador, Que el email {{email}} no esté registrado
Variables: emailAdmin, contraseniaAdmin, nombre, apellido, email, contrasenia, pais, idRol, textoRol
Funciones auxiliares: Ingresar al sitio, Login, Buscar usuario en el listado
Pasos:

* Paso 1: [[Ingresar al sitio("Chrome", "http://cestore.ces.com.uy/adminces/", contraseniaSitio)]]
* Paso 2: [[Login({{emailAdmin}}, {{contraseniaAdmin}})]]
* Paso 3: Hacer click en "Crear usuario"
* Paso 4: Ingresar {{nombre}} en el campo "Nombre"
* Paso 5: Ingresar {{apellido}} en el campo "Apellido"
* Paso 6: Ingresar {{email}} en el campo "Email"
* Paso 7: Ingresar {{contrasenia}} en el campo "Contraseña"
* Paso 8: Ingresar {{pais}} en el campo "País"
* Paso 9: Seleccionar el rol {{idRol}}
* Paso 10: Hacer click en el botón de registro
* Paso 11: Validar modal con texto "Usuario creado."
* Paso 12: Hacer click en "OK"
* Paso 13: Hacer click en "Ver usuarios"
* Paso 14: Validar que [[Buscar usuario en el listado({{nombre}}, {{apellido}}, {{email}}, {{textoRol}})]] devuelve una fila
```

#### Crear tester con email repetido

```text
Nombre: Crear tester con email repetido
Descripción: Comprobar que no se puede registrar un segundo tester con un email ya registrado, con el mismo rol y con otro rol
Precondiciones: Contar con el navegador instalado, Contar con conexión a internet, Contar con un usuario administrador, Que el email {{email}} no esté registrado
Variables: emailAdmin, contraseniaAdmin, nombre, apellido, email, contrasenia, pais, idRol, nombreD, apellidoD, emailR, contraseniaD, paisR, idRol2
Funciones auxiliares: Ingresar al sitio, Login
Pasos:

* Paso 1: [[Ingresar al sitio("Chrome", "http://cestore.ces.com.uy/adminces/", contraseniaSitio)]]
* Paso 2: [[Login({{emailAdmin}}, {{contraseniaAdmin}})]]
* Paso 3: Hacer click en "Crear usuario"
* Paso 4: Ingresar {{nombre}} en el campo "Nombre"
* Paso 5: Ingresar {{apellido}} en el campo "Apellido"
* Paso 6: Ingresar {{email}} en el campo "Email"
* Paso 7: Ingresar {{contrasenia}} en el campo "Contraseña"
* Paso 8: Ingresar {{pais}} en el campo "País"
* Paso 9: Seleccionar el rol {{idRol}}
* Paso 10: Hacer click en el botón de registro
* Paso 11: Validar modal con texto "Usuario creado."
* Paso 12: Hacer click en "OK"
* Paso 13: Hacer click en "Crear usuario"
* Paso 14: Ingresar {{nombreD}} en el campo "Nombre"
* Paso 15: Ingresar {{apellidoD}} en el campo "Apellido"
* Paso 16: Ingresar {{emailR}} (igual a {{email}}) en el campo "Email"
* Paso 17: Ingresar {{contraseniaD}} en el campo "Contraseña"
* Paso 18: Ingresar {{paisR}} en el campo "País"
* Paso 19: Seleccionar el rol {{idRol2}}
* Paso 20: Hacer click en el botón de registro
* Paso 21: Validar modal con texto "Usuario existe."
* Paso 22: Hacer click en "OK"
```

#### Eliminar usuario tester

```text
Nombre: Eliminar usuario tester
Descripción: Eliminar un usuario con rol de tester y comprobar que desaparece del listado
Precondiciones: Contar con el navegador instalado, Contar con conexión a internet, Contar con un usuario administrador, Que el usuario {{email}} exista en el listado
Variables: emailAdmin, contraseniaAdmin, nombre, apellido, email, rol, validEmail, validRol
Funciones auxiliares: Ingresar al sitio, Login, Buscar usuario en el listado
Pasos:

* Paso 1: [[Ingresar al sitio("Chrome", "http://cestore.ces.com.uy/adminces/", contraseniaSitio)]]
* Paso 2: [[Login({{emailAdmin}}, {{contraseniaAdmin}})]]
* Paso 3: Hacer click en "Ver usuarios"
* Paso 4: Validar que [[Buscar usuario en el listado({{nombre}}, {{apellido}}, {{email}}, {{rol}})]] devuelve una fila
* Paso 5: Hacer click en el botón de eliminar de esa fila
* Paso 6: Validar modal con texto "¿Eliminar usuario: {{email}}?"
* Paso 7: Hacer click en "OK"
* Paso 8: Validar modal con texto "Usuario eliminado."
* Paso 9: Hacer click en "OK"
* Paso 10: Validar que [[Buscar usuario en el listado({{nombre}}, {{apellido}}, {{validEmail}}, {{validRol}})]] NO devuelve ninguna fila
```

#### Reiniciar contraseña

```text
Nombre: Reiniciar contraseña
Descripción: Cambiar la contraseña de un usuario administrador
Precondiciones: Contar con el navegador instalado, Contar con conexión a internet, Contar con un usuario administrador
Variables: emailAdmin, nuevaContrasenia
Funciones auxiliares: Ingresar al sitio, Login
Pasos:

* Paso 1: [[Ingresar al sitio("Chrome", "http://cestore.ces.com.uy/adminces/", contraseniaSitio)]]
* Paso 2: Hacer click en "Reiniciar contraseña"
* Paso 3: Ingresar {{emailAdmin}} en el campo "Email"
* Paso 4: Ingresar {{nuevaContrasenia}} en el campo "Contraseña"
* Paso 5: Ingresar {{nuevaContrasenia}} en el campo "Repetir contraseña"
* Paso 6: Hacer click en el botón de reinicio de contraseña
* Paso 7: Validar modal con texto "Contraseña reiniciada."
* Paso 8: Hacer click en "OK"
* Paso 9: Validar realizando [[Login({{emailAdmin}}, {{nuevaContrasenia}})]]
```

#### Reiniciar contraseña de un usuario no administrador

```text
Nombre: Reiniciar contraseña de un usuario no administrador
Descripción: Comprobar que no se puede reiniciar la contraseña de un usuario que no es administrador
Precondiciones: Contar con el navegador instalado, Contar con conexión a internet, Contar con un usuario tester registrado
Variables: emailTester, nuevaContrasenia
Funciones auxiliares: Ingresar al sitio
Pasos:

* Paso 1: [[Ingresar al sitio("Chrome", "http://cestore.ces.com.uy/adminces/", contraseniaSitio)]]
* Paso 2: Hacer click en "Reiniciar contraseña"
* Paso 3: Ingresar {{emailTester}} en el campo "Email"
* Paso 4: Ingresar {{nuevaContrasenia}} en el campo "Contraseña"
* Paso 5: Ingresar {{nuevaContrasenia}} en el campo "Repetir contraseña"
* Paso 6: Hacer click en el botón de reinicio de contraseña
* Paso 7: Validar modal con texto "Perfil de usuario NO administrador."
* Paso 8: Hacer click en "OK"
```

#### Reiniciar contraseña con contraseñas distintas

```text
Nombre: Reiniciar contraseña con contraseñas distintas
Descripción: Comprobar que no se reinicia la contraseña si los dos campos de contraseña no coinciden
Precondiciones: Contar con el navegador instalado, Contar con conexión a internet, Contar con un usuario administrador
Variables: emailAdmin, nuevaContrasenia, repetirContrasenia
Funciones auxiliares: Ingresar al sitio
Pasos:

* Paso 1: [[Ingresar al sitio("Chrome", "http://cestore.ces.com.uy/adminces/", contraseniaSitio)]]
* Paso 2: Hacer click en "Reiniciar contraseña"
* Paso 3: Ingresar {{emailAdmin}} en el campo "Email"
* Paso 4: Ingresar {{nuevaContrasenia}} en el campo "Contraseña"
* Paso 5: Ingresar {{repetirContrasenia}} (distinta de {{nuevaContrasenia}}) en el campo "Repetir contraseña"
* Paso 6: Hacer click en el botón de reinicio de contraseña
* Paso 7: Validar modal con texto "Las contraseñas no coinciden."
* Paso 8: Hacer click en "OK"
```

#### Reiniciar contraseña con formulario vacío

```text
Nombre: Reiniciar contraseña con formulario vacío
Descripción: Comprobar que no se reinicia la contraseña si falta alguno de los datos del formulario
Precondiciones: Contar con el navegador instalado, Contar con conexión a internet
Variables: email, nuevaContrasenia, repetirContrasenia (uno o más de ellos vacíos)
Funciones auxiliares: Ingresar al sitio
Pasos:

* Paso 1: [[Ingresar al sitio("Chrome", "http://cestore.ces.com.uy/adminces/", contraseniaSitio)]]
* Paso 2: Hacer click en "Reiniciar contraseña"
* Paso 3: Ingresar {{email}} en el campo "Email" (dejarlo vacío si el valor es vacío)
* Paso 4: Ingresar {{nuevaContrasenia}} en el campo "Contraseña" (dejarlo vacío si el valor es vacío)
* Paso 5: Ingresar {{repetirContrasenia}} en el campo "Repetir contraseña" (dejarlo vacío si el valor es vacío)
* Paso 6: Hacer click en el botón de reinicio de contraseña
* Paso 7: Validar modal con texto "Formulario vacío."
* Paso 8: Hacer click en "OK"
```

#### Reiniciar contraseña de un usuario inexistente

```text
Nombre: Reiniciar contraseña de un usuario inexistente
Descripción: Comprobar que no se puede reiniciar la contraseña de un email que no está registrado
Precondiciones: Contar con el navegador instalado, Contar con conexión a internet, Que el email "prueba@yopmail.com" no esté registrado
Variables: Ninguna (datos fijos: email "prueba@yopmail.com", contraseña "213")
Funciones auxiliares: Ingresar al sitio
Pasos:

* Paso 1: [[Ingresar al sitio("Chrome", "http://cestore.ces.com.uy/adminces/", contraseniaSitio)]]
* Paso 2: Hacer click en "Reiniciar contraseña"
* Paso 3: Ingresar "prueba@yopmail.com" en el campo "Email"
* Paso 4: Ingresar "213" en el campo "Contraseña"
* Paso 5: Ingresar "213" en el campo "Repetir contraseña"
* Paso 6: Hacer click en el botón de reinicio de contraseña
* Paso 7: Validar modal con texto "Usuario NO existe."
* Paso 8: Hacer click en "OK"
```