from controlador.controlador_arbol import ControladorArbol
from modelo.arbol_bst import ArbolBST
from vista.vista_consola import VistaConsola


def main():
    modelo = ArbolBST()
    vista = VistaConsola()
    controlador = ControladorArbol(modelo, vista)
    controlador.iniciar()


if __name__ == "__main__":
    main()