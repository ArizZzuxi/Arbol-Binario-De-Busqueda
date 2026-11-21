package controlador;

import modelo.ArbolBST;
import vista.VistaConsola;

public class AplMain {
    public static void main(String[] args) {
        ArbolBST modelo = new ArbolBST();
        VistaConsola vista = new VistaConsola();
        ControladorArbol controlador = new ControladorArbol(modelo, vista);
        controlador.iniciar();
    }
}