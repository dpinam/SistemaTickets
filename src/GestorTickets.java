/**
 * Lógica del sistema: une la cola de tickets pendientes con la lista de resueltos.
 * No imprime nada; el menú se encarga de mostrar los resultados al usuario.
 */
public class GestorTickets {

    private final ColaPrioridad pendientes;
    private final ListaEnlazada resueltos;

    public GestorTickets() {
        this.pendientes = new ColaPrioridad();
        this.resueltos = new ListaEnlazada();
    }

    /** Crea un ticket nuevo, lo agrega a la cola de pendientes y lo devuelve. */
    public Ticket crearTicket(String descripcion, String nombreCompleto, Prioridad prioridad) {
        Ticket ticket = new Ticket(descripcion, nombreCompleto, prioridad);
        pendientes.encolar(ticket);
        return ticket;
    }

    /** Busca un ticket resuelto por id. Devuelve null si no está en la lista. */
    public Ticket buscarResuelto(int id) {
        return resueltos.buscarPorId(id);
    }

    /** Indica si alguna vez se creó un ticket con ese id. */
    public boolean existeTicket(int id) {
        return id >= 1 && id <= Ticket.getCantidad();
    }

    /** Devuelve el ticket al frente de la cola sin sacarlo (null si no hay). */
    public Ticket verSiguientePendiente() {
        return pendientes.verFrente();
    }

    /**
     * Resuelve el ticket al frente de la cola: le asigna la fecha de resolución
     * y lo pasa a la lista de resueltos. Devuelve null si no hay pendientes.
     */
    public Ticket resolverSiguiente() {
        Ticket ticket = pendientes.desencolar();
        if (ticket != null) {
            ticket.resolver();
            resueltos.insertarAlFinal(ticket);
        }
        return ticket;
    }

    public int cantidadPendientes() {
        return pendientes.getTamanio();
    }

    public int cantidadResueltos() {
        return resueltos.getTamanio();
    }
}
