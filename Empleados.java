/**
 * Gestión simple de una colección de {@link Empleado}.
 *
 * <p>Proporciona operaciones para dar de alta empleados, aumentar sus salarios
 * y mostrar el listado por consola. Internamente usa un array redimensionable
 * y mantiene el número actual de elementos en {@code size}.</p>
 */
public class Empleados {
    /** Array que contiene los empleados. Accesible como `empleados.lista` por petición. */
    public Empleado[] lista; // se pide usar 'lista' para evitar empleados.empleados

    /** Número actual de empleados almacenados en {@link #lista}. */
    private int size; // número actual de empleados en la lista

    /**
     * Crea una colección de empleados con la capacidad inicial indicada.
     *
     * @param capacidadInicial capacidad inicial del array interno
     */
    public Empleados(int capacidadInicial) {
        if (capacidadInicial < 0) {
            capacidadInicial = 0;
        }
        this.lista = new Empleado[capacidadInicial];
        this.size = 0;
    }

    /**
     * Crea una colección de empleados con capacidad por defecto.
     */
    public Empleados() {
        this(Constantes.DEFAULT_CAPACITY);
    }

    /**
     * Añade un empleado al final de la lista. Si el array interno está lleno,
     * se redimensiona automáticamente (doblando la capacidad +1).
     *
     * @param empleado empleado a añadir
     */
    public void darAltaEmpleado(Empleado empleado) {
        if (size == lista.length) {
            // redimensionar (doblar tamaño + 1 para el caso inicial 0)
            Empleado[] nuevo = new Empleado[lista.length * 2 + 1];
            System.arraycopy(lista, 0, nuevo, 0, lista.length);
            lista = nuevo;
        }
        lista[size++] = empleado;
    }

    /**
     * Aumenta el salario de todos los empleados en el porcentaje indicado.
     *
     * @param porcentaje porcentaje de aumento (por ejemplo 5 para 5%)
     */
    public void aumentarSalario(double porcentaje) {
        for (int i = 0; i < size; i++) {
            Empleado e = lista[i];
            if (e != null) {
                double nuevoSalario = e.getSalario() * (1 + porcentaje / 100);
                e.setSalario(nuevoSalario);
            }
        }
    }

    /**
     * Muestra por consola el listado de empleados (usa {@link Empleado#toString()}).
     */
    public void mostrarListado() {
        System.out.println(Constantes.LISTA_EMPLEADOS);
        for (int i = 0; i < size; i++) {
            System.out.println(lista[i]);
        }
    }
}
