# Gestión de Empleados — Proyecto ejemplo

Resumen
- Proyecto de ejemplo en Java para la gestión simple de empleados: modelos, lista de empleados, operaciones básicas y pruebas unitarias.
- Incluye validaciones en la clase `Empleado`, un enum `Cargo`, una clase `Empleados` que encapsula la colección, y una aplicación principal `SistemaGestionEmpleados`.
- Análisis de pruebas en `TESTING.md` y tests JUnit 5 en `src/test/java`.

Autor y versión
- Autor: (indica tu nombre aquí)
- Versión: 1.0-SNAPSHOT

Requisitos previos
- Java 11+ instalado y `java`/`javac` en PATH.
- Para ejecutar tests con un gestor: Apache Maven instalado (`mvn` en PATH). Alternativa: usar el jar `junit-platform-console-standalone`.

Cómo compilar y ejecutar (Windows PowerShell)
1. Compilar y ejecutar sin Maven (rápido, para probar main):
   - Compilar:
     ```
     cd /d d:\2EVALentornosARC
     javac -d out src\main\java\com\example\empleados\*.java *.java
     ```
     (o compilar los .java en la raíz si existen)
   - Ejecutar:
     ```
     java -cp out SistemaGestionEmpleados
     ```
2. Con Maven (recomendado si quieres ejecutar tests):
   - Ejecutar:
     ```
     cd /d d:\2EVALentornosARC
     mvn clean package
     mvn test
     ```

Ejecutar tests sin Maven (alternativa)
- Descargar `junit-platform-console-standalone-<version>.jar` (ej. 1.9.3) y luego:
  ```
  # compilar clases
  javac -d target\classes src\main\java\com\example\empleados\*.java
  # compilar tests (ajusta ruta al jar)
  javac -cp "target\classes;junit-platform-console-standalone-1.9.3.jar" -d target\test-classes src\test\java\com\example\empleados\*.java
  # ejecutar tests
  java -jar junit-platform-console-standalone-1.9.3.jar --class-path "target\classes;target\test-classes" --scan-class-path
  ```

Estructura del repositorio (resumen)
- pom.xml — configuración Maven y dependencias (JUnit 5).
- src/main/java/com/example/empleados/
  - Cargo.java — enum con los cargos permitidos y mapeo desde texto.
  - Empleado.java — modelo con validaciones (nombre, cargo, salario) y getters/setters.
  - Empleados.java — clase que gestiona la colección (`lista`) y operaciones (alta, aumentar salario, mostrar).
  - Constantes.java — textos y números fijos centralizados.
- SistemaGestionEmpleados.java — aplicación principal (main) que usa `Empleados`.
- src/test/java/com/example/empleados/
  - EmpleadoValidationTest.java — tests JUnit 5 (casos válidos e inválidos según TESTING.md).
- TESTING.md — análisis de caja negra (parámetros, casos válidos / no válidos).

Documentación (Javadoc)
- Las clases principales incluyen JavaDoc. Para generar HTML con Maven:
  ```
  mvn javadoc:javadoc
  ```
  El resultado se encontrará en `target/site/apidocs`.

Notas sobre las validaciones
- Nombre: no nulo, no vacío y al menos dos palabras.
- Cargo: debe ser un valor del enum `Cargo` (uso de `Cargo.fromString` para mapeo).
- Salario: finito, >= 0 y >= CONVENIO (Constantes.CONVENIO = 15000.0 por defecto).

Consejos para evitar errores al ejecutar tests
- Asegurarse de tener Maven en PATH (`mvn -v`).
  - Instalar en Windows con winget: `winget install --id Apache.Maven -e`
  - O descargar desde https://maven.apache.org/download.cgi y añadir `bin` al PATH.
- En VS Code instalar Java Extension Pack y Java Test Runner para ejecutar tests desde el IDE.

Contribución
- Abrir un issue o enviar pull request con pequeñas mejoras (validaciones adicionales, i18n, encapsulación de `lista`).

Licencia
- Indica aquí la licencia deseada (p. ej. MIT) o elimina esta sección.

Fin
- Para cualquier cambio que quieras que aplique en el repositorio (añadir más tests, convertir `lista` en privada con iterador, internacionalización, etc.), indica la tarea y
