package app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HistorialNavegacion {

    private static final Logger logger = Logger.getLogger(HistorialNavegacion.class.getName());

    private final List<String> historial;
    private int posicionActual;

    public HistorialNavegacion() {
        this.historial = new ArrayList<>();
        this.posicionActual = -1;
    }

    public void visitarPagina(String pagina) {
        if (pagina == null || pagina.isBlank()) {
            logger.warning("La página no puede estar vacía.");
            return;
        }

        eliminarPaginasAdelante();
        historial.add(pagina);
        posicionActual++;
        logger.info(() -> "Visitando: " + pagina);
    }

    public void irAtras() {
        if (puedeIrAtras()) {
            posicionActual--;
            logger.info(() -> "Página actual: " + historial.get(posicionActual));
        } else {
            logger.warning("No se puede ir atrás.");
        }
    }

    public void irAdelante() {
        if (puedeIrAdelante()) {
            posicionActual++;
            logger.info(() -> "Página actual: " + historial.get(posicionActual));
        } else {
            logger.warning("No se puede ir adelante.");
        }
    }

    public void buscarEnHistorial(String palabra) {
        if (palabra == null || palabra.isBlank()) {
            logger.warning("La palabra de búsqueda no puede estar vacía.");
            return;
        }

        String textoBuscado = palabra.toLowerCase();
        boolean encontrada = false;

        logger.info(() -> "Resultados de búsqueda para: " + palabra);
        for (int i = 0; i < historial.size(); i++) {
            String pagina = historial.get(i);
            if (pagina.toLowerCase().contains(textoBuscado)) {
                String indicador = (i == posicionActual) ? " <- actual" : "";
                logger.info(() -> "- " + pagina + indicador);
                encontrada = true;
            }
        }

        if (!encontrada) {
            logger.info("No se encontraron coincidencias.");
        }
    }

    public void limpiarHistorial() {
        historial.clear();
        posicionActual = -1;
        logger.info("Historial limpiado correctamente.");
    }

    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            logger.info("El historial está vacío.");
            return;
        }

        logger.info("=== HISTORIAL DE NAVEGACIÓN ===");
        for (int i = 0; i < historial.size(); i++) {
            String indicador = (i == posicionActual) ? " <- actual" : "";
            int indice = i;
            logger.info(() -> (indice + 1) + ". " + historial.get(indice) + indicador);
        }
    }

    public String obtenerPaginaActual() {
        if (posicionActual >= 0 && posicionActual < historial.size()) {
            return historial.get(posicionActual);
        }
        return "No hay página actual.";
    }

    private boolean puedeIrAtras() {
        return posicionActual > 0;
    }

    private boolean puedeIrAdelante() {
        return posicionActual >= 0 && posicionActual < historial.size() - 1;
    }

    private void eliminarPaginasAdelante() {
        if (posicionActual < historial.size() - 1) {
            historial.subList(posicionActual + 1, historial.size()).clear();
        }
    }
}