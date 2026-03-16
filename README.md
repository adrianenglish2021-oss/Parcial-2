# Sistema de Gestión de Empleados

Resumen
- Proyecto Java sencillo para gestionar empleados: modela empleados, cargos y operaciones básicas (alta, listado, aumentar salario).
- Incluye validaciones y suite de pruebas JUnit 5 basada en análisis de caja negra (ver `TESTING.md`).

Metadatos
| Campo | Valor |
|---|---|
| Proyecto | Sistema de Gestión de Empleados |
| Versión | 1.0-SNAPSHOT |
| Autor | (indicar nombre del autor) |
| Licencia | MIT (ajustar si procede) |

Estado del código
- Código principal en `src/main/java/com/example/empleados`.
- Tests JUnit 5 en `src/test/java/com/example/empleados`.
- `pom.xml` incluido para construir y ejecutar tests con Maven.

Estructura del repositorio
| Ruta | Descripción |
|---|---|
| `src/main/java/com/example/empleados/Empleado.java` | Clase modelo `Empleado` con validaciones y JavaDoc. |
| `src/main/java/com/example/empleados/Empleados.java` | Clase que gestiona la colección de empleados (alta, aumento, listado). |
| `src/main/java/com/example/empleados/Cargo.java` | Enum con los cargos profesionales posibles y utilidades de mapeo. |
| `src/main/java/com/example/empleados/Constantes.java` | Constantes de UI y valores por defecto (incluye `CONVENIO`). |
| `SistemaGestionEmpleados.java` | Clase con `main` que usa `Empleados` para ejecutar la demo. |
| `src/test/java/com/example/empleados/EmpleadoValidationTest.java` | Tests JUnit 5 (casos válidos e inválidos definidos en `TESTING.md`). |
| `TESTING.md` | Análisis de caja negra para `Empleado`. |
| `pom.xml` | Descripción del proyecto y dependencias (JUnit 5). |
| `README.md` | Este documento. |

Descripción del código (resumen)
- Empleado
  - Atributos: `nombre` (String), `cargo` (Cargo), `salario` (double).
  - Validaciones: nombre no nulo/no vacío y >= 2 palabras; cargo no nulo y debe pertenecer al enum; salario finito, >= 0 y >= convenio (`Constantes.CONVENIO`).
  - Getters/setters y `toString()` con formato consistente.
- Empleados
  - Atributo `lista` (array de `Empleado`) y `size`.
  - Métodos: `darAltaEmpleado(Empleado)`, `aumentarSalario(double porcentaje)`, `mostrarListado()`.
- Cargo
  - Enum con valores predefinidos (DESARROLLADOR, DISENADORA, GERENTE, ANALISTA, TESTER).
  - Método `fromString(String)` tolerante a mayúsculas/acento para mapear textos.
- Constantes
  - Textos UI, formatos y valores por defecto (`CONVENIO`, `DEFAULT_CAPACITY`, etc.).

Requisitos y dependencias
- Java 11+ (ajustar en `pom.xml` si es necesario).
- Maven (recomendado) para compilar y ejecutar tests.
  - Alternativa: compilar con `javac` y ejecutar tests con `junit-platform-console-standalone.jar`.

Comandos habituales (Maven)
| Acción | Comando |
|---|---|
| Compilar | mvn -q compile |
| Ejecutar la aplicación (desde la raíz si `SistemaGestionEmpleados` está compilada) | mvn -q exec:java -Dexec.mainClass="SistemaGestionEmpleados" (requiere plugin exec o ejecutar con java) |
| Ejecutar tests | mvn -q test |

Instrucciones rápidas (sin Maven)
1. Compilar clases:
   - javac -d out src\main\java\com\example\empleados\*.java SistemaGestionEmpleados.java
2. Compilar tests (descargar junit-platform-console-standalone si es necesario) y ejecutar con el jar.

Tests y calidad
- Todos los casos del `TESTING.md` están implementados en `EmpleadoValidationTest.java` (V1–V6 válidos y N1–N10 inválidos).
- Los tests verifican la política de validación para `nombre`, `cargo` y `salario`.
- Ejecutar `mvn test` para ver resultados. Si Maven no está disponible, usar la alternativa con `junit-platform-console-standalone.jar` (ver instrucciones en `TESTING.md` y en los comentarios del proyecto).

Uso rápido
1. Abrir terminal en la raíz del proyecto.
2. Compilar con Maven: `mvn compile`.
3. Ejecutar: compilar y lanzar `SistemaGestionEmpleados` o ejecutar desde IDE.
4. Para pruebas: `mvn test`.

Notas y recomendaciones
- Ajusta `Constantes.CONVENIO` si el convenio real difiere de 15000.
- Para internacionalización futura, reemplazar `Constantes` por archivos `messages_*.properties`.
- Considerar encapsular `Empleados.lista` (hacer privado) y exponer acceso inmutable si se busca mejor encapsulación.

Cómo contribuir
- Fork, crear rama feature, abrir PR con descripción de cambios y tests asociados.
- Mantener tests verdes y actualizar `TESTING.md` si las reglas de validación cambian.

Contacto / Autoría
- Añadir tus datos de contacto y/o enlace GitHub aquí.

Licencia
- Incluir fichero `LICENSE` si es necesario; por defecto se propone MIT.
