from modelo.arbol_bst import ArbolBST
from vista.vista_consola import VistaConsola


class ControladorArbol:
    def __init__(self, modelo: ArbolBST, vista: VistaConsola):
        self.modelo = modelo
        self.vista = vista

    def iniciar(self):
        self.vista.mostrar_mensaje(
            f"Árbol predefinido cargado correctamente:\n{self.modelo.obtener_estructura()}"
        )

        while True:
            opcion = self.vista.mostrar_menu_principal()
            if opcion == 1:
                self._manejar_busqueda()
            elif opcion == 2:
                self._manejar_insercion()
            elif opcion == 3:
                self._manejar_recorridos()
            elif opcion == 4:
                self._manejar_eliminacion()
            elif opcion == 5:
                self.vista.mostrar_mensaje(
                    f"Estructura actual del árbol:\n{self.modelo.obtener_estructura()}"
                )
            elif opcion == 6:
                self.modelo.cargar_arbol_predefinido()
                self.vista.mostrar_mensaje(
                    f"Árbol reiniciado a su estado inicial:\n{self.modelo.obtener_estructura()}"
                )
            elif opcion == 0:
                self.vista.mostrar_mensaje("Saliendo del programa...")
                break
            else:
                self.vista.mostrar_mensaje("Opción no válida. Intente nuevamente.")

    def _manejar_busqueda(self):
        valor = self.vista.leer_entero("Ingrese el valor a buscar: ")
        resultado = self.modelo.buscar_con_ruta(valor)
        self.vista.mostrar_mensaje(resultado)

    def _manejar_insercion(self):
        valor = self.vista.leer_entero("Ingrese el valor a insertar: ")
        if self.modelo.insertar_valor(valor):
            self.vista.mostrar_mensaje(
                f"Valor {valor} insertado correctamente como hoja.\n"
                f"{self.modelo.obtener_estructura()}"
            )
        else:
            self.vista.mostrar_mensaje(
                f"El valor {valor} ya existe en el árbol. No se permiten duplicados."
            )

    def _manejar_recorridos(self):
        tipo = self.vista.mostrar_menu_recorridos()
        if tipo == 1:
            self.vista.mostrar_mensaje(f"Recorrido In-Order:\n{self.modelo.obtener_inorden()}")
        elif tipo == 2:
            self.vista.mostrar_mensaje(f"Recorrido Pre-Order:\n{self.modelo.obtener_preorden()}")
        elif tipo == 3:
            self.vista.mostrar_mensaje(f"Recorrido Post-Order:\n{self.modelo.obtener_postorden()}")
        elif tipo == 0:
            return
        else:
            self.vista.mostrar_mensaje("Opción de recorrido no válida.")

    def _manejar_eliminacion(self):
        tipo = self.vista.mostrar_menu_eliminacion()
        if tipo == 0:
            return
        if tipo not in (1, 2):
            self.vista.mostrar_mensaje("Opción de eliminación no válida.")
            return

        valor = self.vista.leer_entero("Ingrese el valor a eliminar: ")

        if tipo == 1:
            eliminado = self.modelo.eliminar_por_sucesor(valor)
            metodo_usado = "Sucesor Inorden"
        else:
            eliminado = self.modelo.eliminar_por_predecesor(valor)
            metodo_usado = "Predecesor Inorden"

        if eliminado:
            self.vista.mostrar_mensaje(
                f"Valor {valor} eliminado usando {metodo_usado}.\n"
                f"{self.modelo.obtener_estructura()}"
                f"In-Order actualizado: {self.modelo.obtener_inorden()}"
            )
        else:
            self.vista.mostrar_mensaje(f"El valor {valor} no existe en el árbol.")