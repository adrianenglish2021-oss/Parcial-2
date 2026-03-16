import java.util.Scanner; 

public class SistemaGestionEmpleados {

    // La responsabilidad de almacenar y gestionar empleados se delega a la clase Empleados

    public static void main(String[] args) {
    Empleados empleados = new Empleados(Constantes.EXAMPLE_CAPACITY);

    // dar de alta los 3 empleados de ejemplo usando el enum Cargo
    empleados.darAltaEmpleado(new Empleado("Juan", Cargo.DESARROLLADOR, 50000));
    empleados.darAltaEmpleado(new Empleado("María", Cargo.DISENADORA, 45000));
    empleados.darAltaEmpleado(new Empleado("Pedro", Cargo.GERENTE, 60000));

        Scanner scanner = new Scanner(System.in);
    System.out.print(Constantes.PROMPT_PORCENTAJE);
        double porcentaje = scanner.nextDouble();

        empleados.aumentarSalario(porcentaje);

        empleados.mostrarListado();

        scanner.close();
    }
}