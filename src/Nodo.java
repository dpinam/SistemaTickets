/**
 * Nodo usado por la cola de prioridad y la lista enlazada.
 * Guarda un ticket y la referencia al siguiente nodo.
 */
public class Nodo {

    private Ticket dato;
    private Nodo siguiente;

    public Nodo(Ticket dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Ticket getDato() {
        return dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
