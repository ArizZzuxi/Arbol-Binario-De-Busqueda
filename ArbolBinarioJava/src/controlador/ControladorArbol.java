package controlador;

import modelo.ArbolBST;
import vista.VistaConsola;

public class ControladorArbol {
    private final ArbolBST modelo;
    private final VistaConsola vista;

    public ControladorArbol(ArbolBST modelo, VistaConsola vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrarMensaje("Árbol predefinido cargado correctamente:\n" + modelo.obtenerEstructura());

        int opcion;
        do {
            opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1:
                    manejarBusqueda();
                    break;
                case 2:
                    manejarInsercion();
                    break;
                case 3:
                    manejarRecorridos();
                    break;
                case 4:
                    manejarEliminacion();
                    break;
                case 5:
                    vista.mostrarMensaje("Estructura actual del árbol:\n" + modelo.obtenerEstructura());
                    break;
                case 6:
                    modelo.cargarArbolPredefinido();
                    vista.mostrarMensaje("Árbol reiniciado a su estado inicial:\n" + modelo.obtenerEstructura());
                    break;
                case 0:
                    vista.mostrarMensaje("Saliendo del programa...");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        vista.cerrar();
    }

    private void manejarBusqueda() {
        int valor = vista.leerEntero("Ingrese el valor a buscar: ");
        String resultado = modelo.buscarConRuta(valor);
        vista.mostrarMensaje(resultado);
    }

    private void manejarInsercion() {
        int valor = vista.leerEntero("Ingrese el valor a insertar: ");
        if (modelo.insertarValor(valor)) {
            vista.mostrarMensaje("Valor " + valor + " insertado correctamente como hoja.\n"
                    + modelo.obtenerEstructura());
        } else {
            vista.mostrarMensaje("El valor " + valor + " ya existe en el árbol. No se permiten duplicados.");
        }
    }

    private void manejarRecorridos() {
        int tipo = vista.mostrarMenuRecorridos();
        switch (tipo) {
            case 1:
                vista.mostrarMensaje("Recorrido In-Order:\n" + modelo.obtenerInorden());
                break;
            case 2:
                vista.mostrarMensaje("Recorrido Pre-Order:\n" + modelo.obtenerPreorden());
                break;
            case 3:
                vista.mostrarMensaje("Recorrido Post-Order:\n" + modelo.obtenerPostorden());
                break;
            case 0:
                break;
            default:
                vista.mostrarMensaje("Opción de recorrido no válida.");
        }
    }

    private void manejarEliminacion() {
        int tipo = vista.mostrarMenuEliminacion();
        if (tipo == 0) {
            return;
        }
        if (tipo != 1 && tipo != 2) {
            vista.mostrarMensaje("Opción de eliminación no válida.");
            return;
        }

        int valor = vista.leerEntero("Ingrese el valor a eliminar: ");
        boolean eliminado;
        String metodoUsado;

        if (tipo == 1) {
            eliminado = modelo.eliminarPorSucesor(valor);
            metodoUsado = "Sucesor Inorden";
        } else {
            eliminado = modelo.eliminarPorPredecesor(valor);
            metodoUsado = "Predecesor Inorden";
        }

        if (eliminado) {
            vista.mostrarMensaje("Valor " + valor + " eliminado usando " + metodoUsado + ".\n"
                    + modelo.obtenerEstructura()
                    + "In-Order actualizado: " + modelo.obtenerInorden());
        } else {
            vista.mostrarMensaje("El valor " + valor + " no existe en el árbol.");
        }
    }
}