# Sistema de Tickets — Práctica 1 (SOFT-10 Estructuras de Datos)

Aplicación de consola en Java para gestionar tickets en línea.

## Estructuras de datos

| Estructura | Uso | Implementación |
|---|---|---|
| `ColaPrioridad` | Tickets pendientes | Nodos enlazados; inserción ordenada (prioridad ALTA > MEDIA > BAJA; si empatan, el más antiguo primero) |
| `ListaEnlazada` | Tickets resueltos | Lista enlazada simple con inserción al final y búsqueda por `id` |

## Clases

- `Ticket`: id (contador estático `cantidad`), descripcion, nombreCompleto, prioridad, fechaCreacion, fechaResolucion (inicia en `null`).
- `Prioridad`: enum BAJA / MEDIA / ALTA.
- `Nodo`: nodo que guarda un ticket y la referencia al siguiente.
- `ColaPrioridad`, `ListaEnlazada`: estructuras propias.
- `GestorTickets`: lógica que conecta la cola y la lista.
- `Menu`: interfaz de línea de comandos con validación de datos.
- `Main`: punto de entrada.

## Funcionalidades

**Usuario:** crear ticket · buscar ticket resuelto por id (si no está resuelto, se indica que está pendiente).

**Administrador:** ver el ticket al frente de la cola · resolver el ticket al frente (asigna `fechaResolucion` y lo pasa a la lista de resueltos).

## Cómo ejecutar

En VS Code: abrir la carpeta, abrir `src/Main.java` y presionar **Run**.

Desde terminal:

```
javac -d bin src/*.java
java -cp bin Main
```
