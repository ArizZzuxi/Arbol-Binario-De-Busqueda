class VistaConsola:
    def mostrar_menu_principal(self) -> int:
        print("\n==========================================")
        print("     ÁRBOL BINARIO DE BÚSQUEDA (ABB)")
        print("==========================================")
        print("1. Buscar un valor")
        print("2. Insertar un valor")
        print("3. Recorrer el árbol (In / Pre / Post)")
        print("4. Eliminar un valor (Sucesor / Predecesor)")
        print("5. Ver estructura actual del árbol")
        print("6. Reiniciar al árbol predefinido")
        print("0. Salir")
        print("==========================================")
        return self.leer_entero("Seleccione una opción: ")

    def mostrar_menu_recorridos(self) -> int:
        print("\n--- TIPO DE RECORRIDO ---")
        print("1. In-Order   (Izquierda - Nodo - Derecha)")
        print("2. Pre-Order  (Nodo - Izquierda - Derecha)")
        print("3. Post-Order (Izquierda - Derecha - Nodo)")
        print("0. Volver")
        return self.leer_entero("Seleccione el recorrido: ")

    def mostrar_menu_eliminacion(self) -> int:
        print("\n--- MÉTODO DE ELIMINACIÓN (CASO 3: DOS HIJOS) ---")
        print("1. Por Sucesor Inorden    (menor del subárbol derecho)")
        print("2. Por Predecesor Inorden (mayor del subárbol izquierdo)")
        print("0. Volver")
        return self.leer_entero("Seleccione la variante: ")

    def leer_entero(self, mensaje: str) -> int:
        while True:
            entrada = input(mensaje).strip()
            try:
                return int(entrada)
            except ValueError:
                print("Entrada inválida. Por favor ingrese un número entero.")

    def mostrar_mensaje(self, mensaje: str):
        print(f"\n{mensaje}")