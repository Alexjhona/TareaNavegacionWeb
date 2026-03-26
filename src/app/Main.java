package app;

import java.util.Scanner;

public class Main {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HistorialNavegacion historial = new HistorialNavegacion();

        int opcion;

        do {
            System.out.println("\n=== MENÚ HISTORIAL ===");
            System.out.println("1. Visitar página");
            System.out.println("2. Ir atrás");
            System.out.println("3. Ir adelante");
            System.out.println("4. Mostrar historial");
            System.out.println("5. Buscar en historial");
            System.out.println("6. Limpiar historial");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese URL: ");
                    String url = sc.nextLine();
                    historial.visitarPagina(url);
                    break;

                case 2:
                    historial.irAtras();
                    break;

                case 3:
                    historial.irAdelante();
                    break;

                case 4:
                    historial.mostrarHistorial();
                    break;

                case 5:
                    System.out.print("Buscar: ");
                    String palabra = sc.nextLine();
                    historial.buscarEnHistorial(palabra);
                    break;

                case 6:
                    historial.limpiarHistorial();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);

        sc.close();
    }
}