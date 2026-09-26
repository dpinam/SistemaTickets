/**
 * Lista enlazada simple donde se guardan los tickets resueltos.
 * Se mantiene una referencia al último nodo para insertar al final en O(1).
 */
public class ListaEnlazada {

    private Nodo cabeza;
    private Nodo cola;
    private int tamanio;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }

    /** Agrega un ticket al final de la lista. */
    public void insertarAlFinal(Ticket ticket) {
        Nodo nuevo = new Nodo(ticket);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            cola.setSiguiente(nuevo);
        }
        cola = nuevo;
        tamanio++;
    }

    /**
     * Busca un ticket por su id recorriendo la lista desde la cabeza.
     * Devuelve el ticket si lo encuentra, o null si no está.
     */
    public Ticket buscarPorId(int id) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getDato().getId() == id) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }
}
