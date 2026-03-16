package com.example.empleados;

class Empleado {
    private String nombre;
    private Cargo cargo;
    private double salario;

    public Empleado(String nombre, Cargo cargo, double salario) {
        validateNombre(nombre);
        validateCargo(cargo);
        validateSalario(salario);
        this.nombre = nombre.trim();
        this.cargo = cargo;
        this.salario = salario;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { validateNombre(nombre); this.nombre = nombre.trim(); }
    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { validateCargo(cargo); this.cargo = cargo; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { validateSalario(salario); this.salario = salario; }

    private static void validateNombre(String nombre) {
        if (nombre == null) throw new IllegalArgumentException("nombre null");
        String t = nombre.trim();
        if (t.isEmpty()) throw new IllegalArgumentException("nombre vacío");
        String[] tokens = t.split("\\s+");
        if (tokens.length < 2) throw new IllegalArgumentException("nombre debe contener al menos dos palabras");
    }

    private static void validateCargo(Cargo cargo) {
        if (cargo == null) throw new IllegalArgumentException("cargo inválido");
    }

    private static void validateSalario(double salario) {
        if (!Double.isFinite(salario)) throw new IllegalArgumentException("salario no finito");
        if (salario < 0) throw new IllegalArgumentException("salario negativo");
        if (salario < Constantes.CONVENIO) throw new IllegalArgumentException("salario por debajo de convenio");
    }
}
