package modelo;

public class ArbolBST {
    private Nodo raiz;

    public ArbolBST() {
        cargarArbolPredefinido();
    }

    // Crea el árbol base de las diapositivas: raíz 50, hijos 30 y 70, hojas 20, 40, 60 y 80
    public void cargarArbolPredefinido() {
        this.raiz = null;
        int[] valoresIniciales = {50, 30, 70, 20, 40, 60, 80};
        for (int valor : valoresIniciales) {
            raiz = insertar(raiz, valor);
        }
    }

    // ==================== BÚSQUEDA ====================

    public boolean existe(int valor) {
        return buscarNodo(raiz, valor) != null;
    }

    private Nodo buscarNodo(Nodo actual, int valor) {
        if (actual == null || actual.valor == valor) {
            return actual;
        }
        if (valor < actual.valor) {
            return buscarNodo(actual.izq, valor);
        }
        return buscarNodo(actual.der, valor);
    }

    public String buscarConRuta(int valor) {
        StringBuilder ruta = new StringBuilder();
        boolean encontrado = buscarRec(raiz, valor, ruta);
        if (encontrado) {
            return "Búsqueda EXITOSA. Valor " + valor + " encontrado.\nRuta: " + ruta;
        }
        return "Búsqueda FALLIDA. El valor " + valor + " no existe en el árbol.\nRuta: " + ruta;
    }

    private boolean buscarRec(Nodo actual, int valor, StringBuilder ruta) {
        if (actual == null) {
            ruta.append("null");
            return false;
        }
        ruta.append(actual.valor);
        if (valor == actual.valor) {
            return true;
        }
        ruta.append(" -> ");
        if (valor < actual.valor) {
            return buscarRec(actual.izq, valor, ruta);
        }
        return buscarRec(actual.der, valor, ruta);
    }

    // ==================== INSERCIÓN ====================

    public boolean insertarValor(int valor) {
        if (existe(valor)) {
            return false; // Evita duplicados
        }
        raiz = insertar(raiz, valor);
        return true;
    }

    private Nodo insertar(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }
        if (valor < actual.valor) {
            actual.izq = insertar(actual.izq, valor);
        } else if (valor > actual.valor) {
            actual.der = insertar(actual.der, valor);
        }
        return actual;
    }

    // ==================== RECORRIDOS ====================

    public String obtenerInorden() {
        if (raiz == null) return "(Árbol vacío)";
        StringBuilder sb = new StringBuilder();
        inorden(raiz, sb);
        return sb.toString().trim();
    }

    private void inorden(Nodo n, StringBuilder sb) {
        if (n == null) return;
        inorden(n.izq, sb);
        sb.append(n.valor).append(" ");
        inorden(n.der, sb);
    }

    public String obtenerPreorden() {
        if (raiz == null) return "(Árbol vacío)";
        StringBuilder sb = new StringBuilder();
        preorden(raiz, sb);
        return sb.toString().trim();
    }

    private void preorden(Nodo n, StringBuilder sb) {
        if (n == null) return;
        sb.append(n.valor).append(" ");
        preorden(n.izq, sb);
        preorden(n.der, sb);
    }

    public String obtenerPostorden() {
        if (raiz == null) return "(Árbol vacío)";
        StringBuilder sb = new StringBuilder();
        postorden(raiz, sb);
        return sb.toString().trim();
    }

    private void postorden(Nodo n, StringBuilder sb) {
        if (n == null) return;
        postorden(n.izq, sb);
        postorden(n.der, sb);
        sb.append(n.valor).append(" ");
    }

    // ==================== MÍNIMO Y MÁXIMO ====================

    private Nodo minimo(Nodo n) { // Para el sucesor
        while (n.izq != null) {
            n = n.izq;
        }
        return n;
    }

    private Nodo maximo(Nodo n) { // Para el predecesor
        while (n.der != null) {
            n = n.der;
        }
        return n;
    }

    // ==================== ELIMINACIÓN ====================

    public boolean eliminarPorSucesor(int valor) {
        if (!existe(valor)) {
            return false;
        }
        raiz = eliminarConSucesor(raiz, valor);
        return true;
    }

    private Nodo eliminarConSucesor(Nodo actual, int valor) {
        if (actual == null) return null; // No existe
        if (valor < actual.valor) {
            actual.izq = eliminarConSucesor(actual.izq, valor);
        } else if (valor > actual.valor) {
            actual.der = eliminarConSucesor(actual.der, valor);
        } else {
            if (actual.izq == null) return actual.der; // Casos 1 y 2
            if (actual.der == null) return actual.izq; // Caso 2
            Nodo suc = minimo(actual.der);             // Caso 3: sucesor inorden
            actual.valor = suc.valor;
            actual.der = eliminarConSucesor(actual.der, suc.valor);
        }
        return actual;
    }

    public boolean eliminarPorPredecesor(int valor) {
        if (!existe(valor)) {
            return false;
        }
        raiz = eliminarConPredecesor(raiz, valor);
        return true;
    }

    private Nodo eliminarConPredecesor(Nodo actual, int valor) {
        if (actual == null) return null; // No existe
        if (valor < actual.valor) {
            actual.izq = eliminarConPredecesor(actual.izq, valor);
        } else if (valor > actual.valor) {
            actual.der = eliminarConPredecesor(actual.der, valor);
        } else {
            if (actual.izq == null) return actual.der; // Casos 1 y 2
            if (actual.der == null) return actual.izq; // Caso 2
            Nodo pre = maximo(actual.izq);             // Caso 3: predecesor inorden
            actual.valor = pre.valor;
            actual.izq = eliminarConPredecesor(actual.izq, pre.valor);
        }
        return actual;
    }

    // ==================== VISUALIZACIÓN DE ESTRUCTURA ====================

    public String obtenerEstructura() {
        if (raiz == null) return "(Árbol vacío)";
        StringBuilder sb = new StringBuilder();
        construirEstructura(raiz, "", "(Raiz) ", true, sb);
        return sb.toString();
    }

    private void construirEstructura(Nodo nodo, String prefijo, String etiqueta, boolean esUltimo, StringBuilder sb) {
        if (nodo == null) return;
        sb.append(prefijo)
          .append(esUltimo ? "\\-- " : "|-- ")
          .append(etiqueta)
          .append(nodo.valor)
          .append("\n");
        String nuevoPrefijo = prefijo + (esUltimo ? "    " : "|   ");
        if (nodo.izq != null) {
            construirEstructura(nodo.izq, nuevoPrefijo, "(Izq) ", nodo.der == null, sb);
        }
        if (nodo.der != null) {
            construirEstructura(nodo.der, nuevoPrefijo, "(Der) ", true, sb);
        }
    }
}