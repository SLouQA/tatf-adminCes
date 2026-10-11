# Automatización de pruebas funcionales - adminCes

Framework de automatización de pruebas funcionales para la plataforma **adminCes**, desarrollado con Selenium WebDriver, Java, JUnit 5 y Maven. Aplica el patrón **Page Object Model (POM)** con una capa de **Tasks** y pruebas **parametrizadas** con archivos CSV.
## Índice

- [Tecnologías](#tecnologías)
- [Funcionalidades cubiertas](#funcionalidades-cubiertas)
- [Estructura del proyecto](#estructura-del-proyecto)
    - [Capas](#capas)
- [Configuración](#configuración)
    - [Elegir el navegador](#elegir-el-navegador)
- [Suites y tags](#suites-y-tags)
- [Casos de prueba automatizados](#casos-de-prueba-automatizados)
- [Datos de prueba (CSV)](#datos-de-prueba-csv)
- [Ejecución](#ejecución)
- [Reporte](#reporte)
## Tecnologías

| Herramienta | Versión |
|---|---|
| Java | 17 |
| Maven | 3.x |
| Selenium WebDriver | 4.49 |
| JUnit Jupiter (JUnit 5) | 6.1.3 |
| JUnit Platform Suite | agrupación de pruebas en suites |
| Maven Surefire | 3.5.3 |
| Allure Report | 3.0.0 (allure-maven 2.12.0) |

## Funcionalidades cubiertas

- Crear usuario (admin y tester)
- Iniciar sesión
- Ver usuarios
- Eliminar usuario
- Reiniciar contraseña del admin

## Estructura del proyecto

```
src/test/java/com/tatf/adminCes/
├── base/
│   ├── data/     IngresoData        (URL, contraseña de acceso y título esperado)
│   ├── pom/      BasePO, IngresoPO
│   ├── task/     BaseTask, IngresoTask
│   └── test/     BaseTest           (apertura/cierre del navegador e ingreso al sistema)
├── login/        data / pom / task
├── createUser/   data / pom / task / test
├── viewUs/       pom / task
├── deleteUser/   data / pom / task / test
├── resetPassAdmin/ data / pom / task / test
└── suites/       Suite_All_Positivos, Suite_All_Negativos, Suite_CreateUser_Positivos

src/test/resources/
├── config.properties    (URL, credenciales y navegador)
├── allure.properties    (carpeta de resultados de Allure)
└── *.csv                (datos de entrada de las pruebas parametrizadas)
```

### Capas

- **PO (Page Object)**: localizadores y acciones elementales sobre cada pantalla. Todos heredan de `BasePO`, que concentra lo común (email, contraseña, repetir contraseña, modal).
- **Task**: flujos de negocio compuestos por acciones de los PO (por ejemplo, `loginAdmin`, `regAdmin`, `eliminarUsuario`). No contienen verificaciones.
- **Data**: mensajes esperados y valores de configuración.
- **Test**: casos de prueba. Son los únicos que verifican resultados.

## Configuración

Los datos de entorno están en `src/test/resources/config.properties`:

```properties
browser=EDGE
browser=CHROME

adminces.url=...
adminces.passUrl=...
adminces.usuarioAdmin=...
adminces.contrasenia=...
```

### Elegir el navegador

Los navegadores soportados son **Chrome** y **Edge**. Se pueden elegir de dos formas:

1. Cambiando la clave `browser` en `config.properties`.
2. Por línea de comandos, con `-Dbrowser=...` (tiene prioridad sobre el archivo).

No hace falta descargar drivers: Selenium Manager los resuelve automáticamente.

## Suites y tags

Las pruebas están etiquetadas con `@Tag("positivo")` o `@Tag("negativo")`. Las suites (en `suites/`) usan esas etiquetas para agrupar:

| Suite | Contenido |
|---|---|
| `Suite_All_Positivos` | Todas las pruebas con tag `positivo` del paquete `com.tatf.adminCes` |
| `Suite_All_Negativos` | Todas las pruebas con tag `negativo` del paquete `com.tatf.adminCes` |
| `Suite_CreateUser_Positivos` | Clases `CreateTesterTest` y `CreateAdminTest`, excluyendo las negativas |

Surefire ejecuta las clases que coinciden con `**/Suite_*.java`.
## Casos de prueba automatizados

| Guion                                               | Clase              | Método                       | CSV                     | Tipo     |
|-----------------------------------------------------|--------------------|------------------------------|-------------------------|----------|
| Crear cuenta de administrador                       | CreateAdminTest    | `crearCuentaAdmin`           | `crearAdmin.csv`        | Positivo |
| Crear administrador con email repetido              | CreateAdminTest    | `crearCuentaAdminEmailRep`   | `crearAdminEmailR.csv`  | Negativo |
| Crear cuenta de tester                              | CreateTesterTest   | `crearCuentaTester`          | `crearTester.csv`       | Positivo |
| Crear tester con email repetido                     | CreateTesterTest   | `crearCuentaTesterEmailRep`  | `crearTesterEmailR.csv` | Negativo |
| Eliminar usuario tester                             | DeleteUserTest     | `eliminarCuentaTester`       | `deletUs.csv`           | Positivo |
| Reiniciar contraseña                                | ResetPassAdminTest | `resetPassAdminOk`           | `resetPassAdminOK.csv`  | Positivo |
| Reiniciar contraseña de un usuario no administrador | ResetPassAdminTest | `resetPassNoAdmin`           | `resetPassTester.csv`   | Negativo |
| Reiniciar contraseña con contraseñas distintas      | ResetPassAdminTest | `resetPassNoIgual`           | `resetPassNoIgual.csv`  | Negativo |
| Reiniciar contraseña con formulario vacío           | ResetPassAdminTest | `resetFormVacio`             | `resetFormVacio.csv`    | Negativo |
| Reiniciar contraseña de un usuario inexistente      | ResetPassAdminTest | `resetUsNoExiste`            | Sin CSV (datos fijos)   | Negativo |

## Datos de prueba (CSV)

Los archivos están en `src/test/resources`, usan `;` como separador, se guardan en UTF-8 y su primera fila es el encabezado.

| Archivo                 | Columnas                                                                                    |
|-------------------------|---------------------------------------------------------------------------------------------|
| `crearAdmin.csv`        | nombre, apellido, email, contrasenia, pais                                                  |
| `crearAdminEmailR.csv`  | nombre, apellido, email, contrasenia, pais, nombreD, apellidoD, emailR, contraseniaD, paisR |
| `crearTester.csv`       | nombre, apellido, email, contrasenia, pais, idRol, textoRol                                 |
| `crearTesterEmailR.csv` | nombre, apellido, email, contrasenia, pais, idRol, nombreD, apellidoD, emailR, contraseniaD, paisR, idRol2 |
| `deletUs.csv`           | nombre, apellido, email, rol                                                                |
| `resetPassAdminOK.csv`  | email, contrasenia, repCont                                                                 |
| `resetPassTester.csv`   | email, contrasenia, repCont                                                                 |
| `resetPassNoIgual.csv`  | email, contrasenia, repCont                                                                 |
| `resetFormVacio.csv`    | email, contrasenia, repCont                                                                 |

## Ejecución

Todas las suites en Chrome:

```bash
mvn clean test -Dbrowser=chrome
```

Todas las suites en Edge:

```bash
mvn clean test -Dbrowser=edge
```

Una sola suite:

```bash
mvn clean test -Dbrowser=chrome -Dtest=Suite_All_Positivos
```

> `Suite_CreateUser_Positivos` incluye pruebas que también están en `Suite_All_Positivos`, por lo que al ejecutar todas las suites esas pruebas se corren dos veces.

También se puede ejecutar desde IntelliJ con el panel **Maven** (`Execute Maven Goal`) usando los mismos comandos.

## Reporte

Los resultados se generan con **Allure** en `target/allure-results`. Para verlos:

```bash
mvn allure:serve
```

Como `clean` borra la carpeta `target`, conviene generar el reporte de una corrida antes de ejecutar la siguiente.

El resumen de resultados en Chrome y Edge está en el **Informe de Ejecución de Pruebas ([Informe finl_tatf_SLoureiro.pdf]())**.