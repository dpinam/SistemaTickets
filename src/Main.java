import java.util.Scanner;

/**
 * Punto de entrada del Sistema de Tickets.
 *
 * Curso: SOFT-10 Estructuras de Datos - Universidad CENFOTEC
 * Práctica 1: implementación de estructuras de datos.
 *
 * Estructuras usadas:
 *  - ColaPrioridad (nodos enlazados) para los tickets pendientes.
 *  - ListaEnlazada simple para los tickets resueltos.
 */
public class Main {

    public static void main(String[] args) {
        // Un solo Scanner para toda la aplicación
        Scanner scanner = new Scanner(System.in);

        // Lógica del sistema (estructuras de datos)
        GestorTickets gestor = new GestorTickets();

        // Interfaz de línea de comandos
        Menu menu = new Menu(scanner, gestor);
        menu.iniciar();

        scanner.close();
    }
}
