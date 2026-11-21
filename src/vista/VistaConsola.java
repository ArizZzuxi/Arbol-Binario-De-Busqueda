package vista;

import java.util.Scanner;

public class VistaConsola {
    private final Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n==========================================");
        System.out.println("     ÁRBOL BINARIO DE BÚSQUEDA (ABB)");
        System.out.println("==========================================");
        System.out.println("1. Buscar un valor");
        System.out.println("2. Insertar un valor");
        System.out.println("3. Recorrer el árbol (In / Pre / Post)");
        System.out.println("4. Eliminar un valor (Sucesor / Predecesor)");
        System.out.println("5. Ver estructura actual del árbol");
        System.out.println("6. Reiniciar al árbol predefinido");
        System.out.println("0. Salir");
        System.out.println("==========================================");
        return leerEntero("Seleccione una opción: ");
    }

    public int mostrarMenuRecorridos() {
        System.out.println("\n--- TIPO DE RECORRIDO ---");
        System.out.println("1. In-Order   (Izquierda - Nodo - Derecha)");
        System.out.println("2. Pre-Order  (Nodo - Izquierda - Derecha)");
        System.out.println("3. Post-Order (Izquierda - Derecha - Nodo)");
        System.out.println("0. Volver");
        return leerEntero("Seleccione el recorrido: ");
    }

    public int mostrarMenuEliminacion() {
        System.out.println("\n--- MÉTODO DE ELIMINACIÓN (CASO 3: DOS HIJOS) ---");
        System.out.println("1. Por Sucesor Inorden    (menor del subárbol derecho)");
        System.out.println("2. Por Predecesor Inorden (mayor del subárbol izquierdo)");
        System.out.println("0. Volver");
        return leerEntero("Seleccione la variante: ");
    }

    public int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número entero.");
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
    }

    public void cerrar() {
        scanner.close();
    }
}