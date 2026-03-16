package com.example.empleados;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoValidationTest {

    @Test
    void V1_creacion_valida_borde_minimo() {
        Empleado e = new Empleado("Ana López", Cargo.DESARROLLADOR, 15000.0);
        assertEquals("Ana López", e.getNombre());
        assertEquals(Cargo.DESARROLLADOR, e.getCargo());
        assertEquals(15000.0, e.getSalario());
    }

    @Test
    void V2_creacion_valida() {
        Empleado e = new Empleado("José María", Cargo.DISENADORA, 45000.0);
        assertNotNull(e);
    }

    @Test
    void V3_creacion_valida() {
        Empleado e = new Empleado("María del Mar", Cargo.GERENTE, 60000.0);
        assertNotNull(e);
    }

    @Test
    void V4_creacion_valida_bva() {
        Empleado e = new Empleado("Éric Dupont", Cargo.ANALISTA, 15001.0);
        assertNotNull(e);
    }

    @Test
    void V5_creacion_valida_decimales() {
        Empleado e = new Empleado("Ana María", Cargo.TESTER, 20000.5);
        assertNotNull(e);
    }

    @Test
    void V6_creacion_valida_caracteres_especiales() {
        Empleado e = new Empleado("O'Connor Martin", Cargo.DESARROLLADOR, 30000.0);
        assertNotNull(e);
    }

    @Test
    void N1_nombre_vacio() {
        assertThrows(IllegalArgumentException.class,
            () -> new Empleado("", Cargo.DESARROLLADOR, 20000.0));
    }

    @Test
    void N2_nombre_una_palabra() {
        assertThrows(IllegalArgumentException.class,
            () -> new Empleado("Juan", Cargo.DISENADORA, 25000.0));
    }

    @Test
    void N3_nombre_solo_espacios() {
        assertThrows(IllegalArgumentException.class,
            () -> new Empleado("   ", Cargo.GERENTE, 30000.0));
    }

    @Test
    void N4_cargo_no_enumerado() {
        // Simular cargo no reconocido: null
        Cargo unknown = null;
        assertThrows(IllegalArgumentException.class,
            () -> new Empleado("Ana López", unknown, 30000.0));
    }

    @Test
    void N5_cargo_vacio() {
        assertThrows(IllegalArgumentException.class,
            () -> new Empleado("Ana López", Cargo.fromString("") , 30000.0));
    }

    @Test
    void N6_cargo_null() {
        assertThrows(IllegalArgumentException.class,
            () -> new Empleado("Ana López", (Cargo) null, 30000.0));
    }

    @Test
    void N7_salario_inferior_convenio() {
        assertThrows(IllegalArgumentException.class,
            () -> new Empleado("Ana López", Cargo.DESARROLLADOR, 14999.99));
    }

    @Test
    void N8_salario_negativo() {
        assertThrows(IllegalArgumentException.class,
            () -> new Empleado("Ana López", Cargo.DESARROLLADOR, -1000.0));
    }

    @Test
    void N9_salario_NaN() {
        assertThrows(IllegalArgumentException.class,
            () -> new Empleado("Ana López", Cargo.DESARROLLADOR, Double.NaN));
    }

    @Test
    void N10_nombre_null() {
        assertThrows(IllegalArgumentException.class,
            () -> new Empleado(null, Cargo.GERENTE, 30000.0));
    }
}
