import java.util.Scanner;

/**
 * Interfaz de línea de comandos (CLI) del sistema.
 * Muestra el menú principal, el menú de usuario y el menú de administrador,
 * y valida lo que se escribe para que el programa no se caiga por datos inválidos.
 */
public class Menu {

    private final Scanner scanner;
    private final GestorTickets gestor;

    public Menu(Scanner scanner, GestorTickets gestor) {
        this.scanner = scanner;
        this.gestor = gestor;
    }

    /** Ciclo del menú principal. Termina cuando el usuario elige Salir. */
    public void iniciar() {
        int opcion;
        do {
            mostrarTitulo("SISTEMA DE TICKETS");
            System.out.println("1. Menu de usuario");
            System.out.println("2. Menu de administrador");
            System.out.println("0. Salir");
            opcion = leerEntero("Seleccione una opcion: ", 0, 2);

            switch (opcion) {
                case 1:
                    menuUsuario();
                    break;
                case 2:
                    menuAdministrador();
                    break;
                default:
                    System.out.println("\nGracias por usar el sistema. Hasta luego!");
            }
        } while (opcion != 0);
    }

    // ==================== MENÚ DE USUARIO ====================

    private void menuUsuario() {
        int opcion;
        do {
            mostrarTitulo("MENU DE USUARIO");
            System.out.println("1. Crear ticket");
            System.out.println("2. Buscar ticket resuelto");
            System.out.println("0. Volver");
            opcion = leerEntero("Seleccione una opcion: ", 0, 2);

            switch (opcion) {
                case 1:
                    crearTicket();
                    break;
                case 2:
                    buscarTicket();
                    break;
                default:
                    break; // Volver al menú principal
            }
        } while (opcion != 0);
    }

    private void crearTicket() {
        mostrarTitulo("CREAR TICKET");
        String nombre = leerTextoNoVacio("Nombre completo: ");
        String descripcion = leerTextoNoVacio("Descripcion del problema: ");

        System.out.println("Prioridad: 1. Baja  2. Media  3. Alta");
        int nivel = leerEntero("Seleccione la prioridad: ", 1, 3);
        Prioridad prioridad = Prioridad.values()[nivel - 1];

        Ticket ticket = gestor.crearTicket(descripcion, nombre, prioridad);
        System.out.println("\nTicket creado con exito. Guarde su numero: #" + ticket.getId());
        pausar();
    }

    private void buscarTicket() {
        mostrarTitulo("BUSCAR TICKET RESUELTO");
        int id = leerEntero("Numero de ticket: ", 1, Integer.MAX_VALUE);

        Ticket ticket = gestor.buscarResuelto(id);
        if (ticket != null) {
            System.out.println("\nTicket encontrado:\n" + ticket);
        } else if (gestor.existeTicket(id)) {
            System.out.println("\nEl ticket #" + id + " esta pendiente de resolucion.");
        } else {
            System.out.println("\nNo existe ningun ticket con el numero #" + id + ".");
        }
        pausar();
    }

    // ================= MENÚ DE ADMINISTRADOR =================

    private void menuAdministrador() {
        int opcion;
        do {
            mostrarTitulo("MENU DE ADMINISTRADOR");
            System.out.println("Pendientes: " + gestor.cantidadPendientes()
                    + " | Resueltos: " + gestor.cantidadResueltos());
            System.out.println("1. Ver ticket al frente de la cola");
            System.out.println("2. Resolver ticket al frente de la cola");
            System.out.println("0. Volver");
            opcion = leerEntero("Seleccione una opcion: ", 0, 2);

            switch (opcion) {
                case 1:
                    verFrente();
                    break;
                case 2:
                    resolverFrente();
                    break;
                default:
                    break; // Volver al menú principal
            }
        } while (opcion != 0);
    }

    private void verFrente() {
        Ticket ticket = gestor.verSiguientePendiente();
        if (ticket == null) {
            System.out.println("\nNo hay tickets pendientes.");
        } else {
            System.out.println("\nSiguiente ticket por atender:\n" + ticket);
        }
        pausar();
    }

    private void resolverFrente() {
        Ticket ticket = gestor.resolverSiguiente();
        if (ticket == null) {
            System.out.println("\nNo hay tickets pendientes por resolver.");
        } else {
            System.out.println("\nTicket resuelto:\n" + ticket);
        }
        pausar();
    }

    // ================== MÉTODOS DE APOYO ==================

    /** Lee un número entero dentro de un rango; repite la pregunta si el dato es inválido. */
    private int leerEntero(String mensaje, int minimo, int maximo) {
        while (true) {
            System.out.print(mensaje);
            String linea = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(linea);
                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // Se ignora: se muestra el mensaje de error abajo
            }
            System.out.println("Dato invalido. Intente de nuevo.");
        }
    }

    /** Lee un texto que no puede quedar vacío. */
    private String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Este campo es obligatorio.");
        }
    }

    private void mostrarTitulo(String titulo) {
        System.out.println("\n========================================");
        System.out.println("  " + titulo);
        System.out.println("========================================");
    }

    private void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
