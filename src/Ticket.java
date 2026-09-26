import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Representa un ticket del sistema.
 *
 * Los atributos descripcion, nombreCompleto, prioridad y fechaCreacion se asignan
 * al crear el ticket. La fechaResolucion empieza en null y se establece cuando
 * un administrador resuelve el ticket.
 */
public class Ticket {

    /** Contador estático compartido por todos los tickets; genera ids consecutivos. */
    private static int cantidad = 0;

    /** Formato usado para mostrar las fechas en pantalla. */
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final int id;
    private final String descripcion;
    private final String nombreCompleto;
    private final Prioridad prioridad;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;

    /**
     * Crea un ticket nuevo. El id se toma del contador estático y la fecha de
     * creación es el momento actual.
     */
    public Ticket(String descripcion, String nombreCompleto, Prioridad prioridad) {
        cantidad++;
        this.id = cantidad;
        this.descripcion = descripcion;
        this.nombreCompleto = nombreCompleto;
        this.prioridad = prioridad;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaResolucion = null; // Aún no ha sido resuelto
    }

    /** Marca el ticket como resuelto asignando la fecha y hora actuales. */
    public void resolver() {
        this.fechaResolucion = LocalDateTime.now();
    }

    /** Indica si el ticket ya fue resuelto. */
    public boolean estaResuelto() {
        return fechaResolucion != null;
    }

    /**
     * Indica si este ticket debe atenderse antes que otro.
     * Primero gana la prioridad más alta; si empatan, gana el más antiguo (id menor).
     */
    public boolean tieneMasPrioridadQue(Ticket otro) {
        if (this.prioridad.getNivel() != otro.prioridad.getNivel()) {
            return this.prioridad.getNivel() > otro.prioridad.getNivel();
        }
        return this.id < otro.id;
    }

    // ----- Getters -----

    public static int getCantidad() {
        return cantidad;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    @Override
    public String toString() {
        String resolucion = (fechaResolucion == null)
                ? "Pendiente"
                : fechaResolucion.format(FORMATO_FECHA);

        return "Ticket #" + id + "\n"
                + "  Usuario:          " + nombreCompleto + "\n"
                + "  Descripcion:      " + descripcion + "\n"
                + "  Prioridad:        " + prioridad + "\n"
                + "  Fecha creacion:   " + fechaCreacion.format(FORMATO_FECHA) + "\n"
                + "  Fecha resolucion: " + resolucion;
    }
}
