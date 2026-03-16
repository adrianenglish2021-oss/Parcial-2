# TESTING — Análisis de caja negra para `Empleado`

Breve: este documento recoge el diseño de pruebas de caja negra para los tres atributos de la clase `Empleado`: `nombre`, `cargo` y `salario`. Incluye criterios de aceptación, análisis de parámetros, casos válidos y casos no válidos.

## Criterios de aceptación
- Nombre: no vacío y debe contener más de una palabra (al menos dos tokens separados por espacios).
- Cargo: debe ser uno de los valores predefinidos en `Cargo` (p. ej. `DESARROLLADOR`, `DISENADORA`, `GERENTE`, `ANALISTA`, `TESTER`).
- Salario: debe ser numérico, no negativo y no inferior al salario mínimo de convenio.

Asunción razonable (documentada): en este análisis se toma como salario mínimo de convenio el valor 15.000 (anual, mismas unidades que usa la aplicación actual). Si el proyecto ya tiene otra cifra, sustituir `CONVENIO = 15000` por la real.

---

## 1) Análisis de parámetros

| Parámetro | Tipo | Dominio esperado | Clases de equivalencia válidas | Clases de equivalencia inválidas | Observaciones |
|---|---:|---|---|---|---|
| `nombre` | String | cualquier texto Unicode | Nombre compuesto ("Ana López"), Nombre con caracteres especiales ("José María") | Vacío (""), Una sola palabra ("Juan"), Solo espacios ("   ") | Se considera válido si, tras trim(), contiene al menos dos tokens separados por espacios. |
| `cargo` | Enum / String mapeable | valores del enum `Cargo` | Valores exactos del enum (`DESARROLLADOR`, `DISENADORA`, `GERENTE`, `ANALISTA`, `TESTER`) o su representación legible ("Desarrollador") | Valor no existente ("DevOps"), cadena vacía, null | Se puede mapear desde texto mediante `Cargo.fromString()`; el mapeo debe ser case-insensitive y tolerante a acentos. |
| `salario` | double / número | Números reales >= 0 | Valores >= CONVENIO (p. ej. `15000`) y valores mayores | Valores < CONVENIO (p. ej. `14000`), negativos, NaN, null | Se exige `salario >= CONVENIO`. Si la unidad cambia (mensual/anual), ajustar CONVENIO. |

---

## 2) Casos válidos (esperan PASS)

| ID | Nombre | Cargo | Salario | Resultado esperado | Tipo de prueba |
|---:|---|---|---:|---|---|
| V1 | "Ana López" | DESARROLLADOR | 15000.0 | Creación OK (acepta, salario = 15000) | EC / Borde mínimo |
| V2 | "José María" | DISENADORA | 45000.0 | Creación OK | EC |
| V3 | "María del Mar" | GERENTE | 60000.0 | Creación OK | EC |
| V4 | "Éric Dupont" | ANALISTA | 15001.0 | Creación OK | BVA (justo por encima del mínimo) |
| V5 | "Ana María" | TESTER | 20000.5 | Creación OK (acepta decimales) | EC |
| V6 | "O'Connor Martin" | DESARROLLADOR | 30000.0 | Creación OK (apóstrofes y múltiples tokens) | EC |

Notas: `Tipo de prueba` indica si el caso cubre equivalencia (EC) o boundary value analysis (BVA).

---

## 3) Casos no válidos (esperan FAIL)

| ID | Nombre | Cargo | Salario | Resultado esperado (mensaje / motivo) | Categoría |
|---:|---|---|---:|---|---|
| N1 | "" (cadena vacía) | DESARROLLADOR | 20000.0 | Rechazar: nombre vacío | Invalid input |
| N2 | "Juan" (una sola palabra) | DISENADORA | 25000.0 | Rechazar: nombre no tiene al menos dos palabras | Invalid input |
| N3 | "   " (solo espacios) | GERENTE | 30000.0 | Rechazar: nombre vacío tras trim() | Invalid input |
| N4 | "Ana López" | "DevOps" (no en enum) | 30000.0 | Rechazar: cargo no reconocido | Invalid input / Mapping failure |
| N5 | "Ana López" | "" (vacío) | 30000.0 | Rechazar: cargo vacío | Invalid input |
| N6 | "Ana López" | null | 30000.0 | Rechazar: cargo null | Invalid input |
| N7 | "Ana López" | DESARROLLADOR | 14999.99 | Rechazar: salario por debajo de convenio | Business rule violation |
| N8 | "Ana López" | DESARROLLADOR | -1000.0 | Rechazar: salario negativo | Invalid input |
| N9 | "Ana López" | DESARROLLADOR | NaN | Rechazar: salario no numérico | Invalid input / Type error |
| N10 | null | GERENTE | 30000.0 | Rechazar: nombre null | Invalid input |

---

Recomendaciones de implementación de validaciones
- Validar `nombre`: aplicar `if (nombre == null) or nombre.trim().isEmpty() => error`; luego `String[] tokens = nombre.trim().split("\\s+"); if (tokens.length < 2) => error`.
- Validar `cargo`: usar `Cargo.fromString(input)` o requerir un `Cargo` non-null; si el mapping falla => error indicando cargo desconocido.
- Validar `salario`: comprobar `Double.isFinite(salario)` (si aplica), `salario >= 0` y `salario >= CONVENIO`.

Registro de supuestos
- CONVENIO = 15000 (unidades coherentes con la aplicación). Cambiar esta cifra si el proyecto define otro valor.

Fin del documento.
