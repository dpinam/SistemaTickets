/**
 * Niveles de prioridad que puede tener un ticket.
 * El valor numérico se usa para ordenar la cola: mientras más alto, antes se atiende.
 */
public enum Prioridad {
    BAJA(1),
    MEDIA(2),
    ALTA(3);

    private final int nivel;

    Prioridad(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}
