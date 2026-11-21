from modelo.nodo import Nodo


class ArbolBST:
    def __init__(self):
        self.raiz = None
        self.cargar_arbol_predefinido()

    # Crea el árbol base de las diapositivas: raíz 50, hijos 30 y 70, hojas 20, 40, 60 y 80
    def cargar_arbol_predefinido(self):
        self.raiz = None
        valores_iniciales = [50, 30, 70, 20, 40, 60, 80]
        for valor in valores_iniciales:
            self.raiz = self._insertar(self.raiz, valor)

    # ==================== BÚSQUEDA ====================

    def existe(self, valor: int) -> bool:
        return self._buscar_nodo(self.raiz, valor) is not None

    def _buscar_nodo(self, actual: Nodo, valor: int):
        if actual is None or actual.valor == valor:
            return actual
        if valor < actual.valor:
            return self._buscar_nodo(actual.izq, valor)
        return self._buscar_nodo(actual.der, valor)

    def buscar_con_ruta(self, valor: int) -> str:
        ruta = []
        encontrado = self._buscar_rec(self.raiz, valor, ruta)
        ruta_texto = " -> ".join(ruta)
        if encontrado:
            return f"Búsqueda EXITOSA. Valor {valor} encontrado.\nRuta: {ruta_texto}"
        return f"Búsqueda FALLIDA. El valor {valor} no existe en el árbol.\nRuta: {ruta_texto}"

    def _buscar_rec(self, actual: Nodo, valor: int, ruta: list) -> bool:
        if actual is None:
            ruta.append("null")
            return False
        ruta.append(str(actual.valor))
        if valor == actual.valor:
            return True
        if valor < actual.valor:
            return self._buscar_rec(actual.izq, valor, ruta)
        return self._buscar_rec(actual.der, valor, ruta)

    # ==================== INSERCIÓN ====================

    def insertar_valor(self, valor: int) -> bool:
        if self.existe(valor):
            return False  # Evita duplicados
        self.raiz = self._insertar(self.raiz, valor)
        return True

    def _insertar(self, actual: Nodo, valor: int) -> Nodo:
        if actual is None:
            return Nodo(valor)
        if valor < actual.valor:
            actual.izq = self._insertar(actual.izq, valor)
        elif valor > actual.valor:
            actual.der = self._insertar(actual.der, valor)
        return actual

    # ==================== RECORRIDOS ====================

    def obtener_inorden(self) -> str:
        if self.raiz is None:
            return "(Árbol vacío)"
        resultado = []
        self._inorden(self.raiz, resultado)
        return " ".join(resultado)

    def _inorden(self, n: Nodo, resultado: list):
        if n is None:
            return
        self._inorden(n.izq, resultado)
        resultado.append(str(n.valor))
        self._inorden(n.der, resultado)

    def obtener_preorden(self) -> str:
        if self.raiz is None:
            return "(Árbol vacío)"
        resultado = []
        self._preorden(self.raiz, resultado)
        return " ".join(resultado)

    def _preorden(self, n: Nodo, resultado: list):
        if n is None:
            return
        resultado.append(str(n.valor))
        self._preorden(n.izq, resultado)
        self._preorden(n.der, resultado)

    def obtener_postorden(self) -> str:
        if self.raiz is None:
            return "(Árbol vacío)"
        resultado = []
        self._postorden(self.raiz, resultado)
        return " ".join(resultado)

    def _postorden(self, n: Nodo, resultado: list):
        if n is None:
            return
        self._postorden(n.izq, resultado)
        self._postorden(n.der, resultado)
        resultado.append(str(n.valor))

    # ==================== MÍNIMO Y MÁXIMO ====================

    def _minimo(self, n: Nodo) -> Nodo:  # Para el sucesor
        while n.izq is not None:
            n = n.izq
        return n

    def _maximo(self, n: Nodo) -> Nodo:  # Para el predecesor
        while n.der is not None:
            n = n.der
        return n

    # ==================== ELIMINACIÓN ====================

    def eliminar_por_sucesor(self, valor: int) -> bool:
        if not self.existe(valor):
            return False
        self.raiz = self._eliminar_con_sucesor(self.raiz, valor)
        return True

    def _eliminar_con_sucesor(self, actual: Nodo, valor: int):
        if actual is None:
            return None  # No existe
        if valor < actual.valor:
            actual.izq = self._eliminar_con_sucesor(actual.izq, valor)
        elif valor > actual.valor:
            actual.der = self._eliminar_con_sucesor(actual.der, valor)
        else:
            if actual.izq is None:
                return actual.der  # Casos 1 y 2
            if actual.der is None:
                return actual.izq  # Caso 2
            suc = self._minimo(actual.der)  # Caso 3: sucesor inorden
            actual.valor = suc.valor
            actual.der = self._eliminar_con_sucesor(actual.der, suc.valor)
        return actual

    def eliminar_por_predecesor(self, valor: int) -> bool:
        if not self.existe(valor):
            return False
        self.raiz = self._eliminar_con_predecesor(self.raiz, valor)
        return True

    def _eliminar_con_predecesor(self, actual: Nodo, valor: int):
        if actual is None:
            return None  # No existe
        if valor < actual.valor:
            actual.izq = self._eliminar_con_predecesor(actual.izq, valor)
        elif valor > actual.valor:
            actual.der = self._eliminar_con_predecesor(actual.der, valor)
        else:
            if actual.izq is None:
                return actual.der  # Casos 1 y 2
            if actual.der is None:
                return actual.izq  # Caso 2
            pre = self._maximo(actual.izq)  # Caso 3: predecesor inorden
            actual.valor = pre.valor
            actual.izq = self._eliminar_con_predecesor(actual.izq, pre.valor)
        return actual

    # ==================== VISUALIZACIÓN DE ESTRUCTURA ====================

    def obtener_estructura(self) -> str:
        if self.raiz is None:
            return "(Árbol vacío)"
        lineas = []
        self._construir_estructura(self.raiz, "", "(Raiz) ", True, lineas)
        return "\n".join(lineas) + "\n"

    def _construir_estructura(self, nodo: Nodo, prefijo: str, etiqueta: str, es_ultimo: bool, lineas: list):
        if nodo is None:
            return
        conector = "\\-- " if es_ultimo else "|-- "
        lineas.append(f"{prefijo}{conector}{etiqueta}{nodo.valor}")
        nuevo_prefijo = prefijo + ("    " if es_ultimo else "|   ")
        if nodo.izq is not None:
            self._construir_estructura(nodo.izq, nuevo_prefijo, "(Izq) ", nodo.der is None, lineas)
        if nodo.der is not None:
            self._construir_estructura(nodo.der, nuevo_prefijo, "(Der) ", True, lineas)