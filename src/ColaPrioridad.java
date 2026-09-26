/**
 * Cola de prioridad implementada con nodos enlazados.
 *
 * Los tickets se insertan ya ordenados, de modo que el frente de la cola
 * siempre contiene el ticket que debe atenderse primero:
 * mayor prioridad y, en caso de empate, el más antiguo.
 *
 * Costos: encolar O(n), verFrente O(1), desencolar O(1).
 */
public class ColaPrioridad {

    private Nodo frente;
    private int tamanio;

    public ColaPrioridad() {
        this.frente = null;
        this.tamanio = 0;
    }

    /** Inserta un ticket en la posición que le corresponde según su prioridad. */
    public void encolar(Ticket ticket) {
        Nodo nuevo = new Nodo(ticket);

        // Caso 1: cola vacía o el nuevo ticket va antes que el frente actual
        if (frente == null || ticket.tieneMasPrioridadQue(frente.getDato())) {
            nuevo.setSiguiente(frente);
            frente = nuevo;
        } else {
            // Caso 2: avanzar hasta encontrar el lugar donde insertarlo
            Nodo actual = frente;
            while (actual.getSiguiente() != null
                    && !ticket.tieneMasPrioridadQue(actual.getSiguiente().getDato())) {
                actual = actual.getSiguiente();
            }
            nuevo.setSiguiente(actual.getSiguiente());
            actual.setSiguiente(nuevo);
        }
        tamanio++;
    }

    /** Devuelve el ticket del frente sin sacarlo, o null si la cola está vacía. */
    public Ticket verFrente() {
        if (estaVacia()) {
            return null;
        }
        return frente.getDato();
    }

    /** Saca y devuelve el ticket del frente, o null si la cola está vacía. */
    public Ticket desencolar() {
        if (estaVacia()) {
            return null;
        }
        Ticket ticket = frente.getDato();
        frente = frente.getSiguiente();
        tamanio--;
        return ticket;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }
}
