package com.mycompany.practica1;
import java.util.Scanner;
import java.util.Random;

public class Practica1 {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    // Historial (máximo 100 partidas)
    static String[] historialUsuarios = new String[100];
    static int[] historialPuntos = new int[100];
    static int contadorHistorial = 0;

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    iniciarJuego();
                    break;
                case 2:
                    mostrarHistorial();
                    break;
                case 3:
                    System.out.println("      Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 3);
    }

    public static void mostrarMenu() {
        System.out.println("\n============ PAC MAN ============");
        System.out.println("     1. Iniciar Juego");
        System.out.println("     2. Historial de partidas");
        System.out.println("     3. Salir");
        System.out.print("                  Seleccione opcion: ");
    }

    public static void mostrarHistorial() {

        System.out.println("\n============= HISTORIAL DE PARTIDAS =============");
        System.out.printf("%-5s %-15s %-10s\n", "No.", "USUARIO", "PUNTEO");
        

        int numero = 1;
        for (int i = contadorHistorial - 1; i >= 0; i--) {
            System.out.printf("%-5d %-15s %-10d\n",
                    numero,
                    historialUsuarios[i],
                    historialPuntos[i]);
            numero++;
        }
    }

    public static void iniciarJuego() {

        System.out.print("   Ingrese su nombre: ");
        String usuario = sc.nextLine();

        int filas = 0;
        int columnas = 0;

        System.out.print("   Tipo de tablero (P=Pequenio, G=Grande): ");
        char tipo = sc.next().toUpperCase().charAt(0);

        if (tipo == 'P') {
            filas = 5;
            columnas = 6;
        } else if (tipo == 'G') {
            filas = 10;
            columnas = 10;
        } else {
            System.out.println("             Tipo invalido.");
            return;
        }

        int totalEspacios = filas * columnas;

        int maxPremios = (int)(totalEspacios * 0.40);
        int maxParedes = (int)(totalEspacios * 0.20);
        int maxFantasmas = (int)(totalEspacios * 0.20);

        int premios, paredes, fantasmas;

        do {
            System.out.print("    Cantidad de premios [1-" + maxPremios + "]: ");
            premios = sc.nextInt();
        } while (premios < 1 || premios > maxPremios);

        do {
            System.out.print("    Cantidad de paredes [1-" + maxParedes + "]: ");
            paredes = sc.nextInt();
        } while (paredes < 1 || paredes > maxParedes);

        do {
            System.out.print("    Cantidad de fantasmas [1-" + maxFantasmas + "]: ");
            fantasmas = sc.nextInt();
        } while (fantasmas < 1 || fantasmas > maxFantasmas);

        char[][] tablero = new char[filas][columnas];

        for (int i = 0; i < filas; i++)
            for (int j = 0; j < columnas; j++)
                tablero[i][j] = ' ';

        colocarElementos(tablero, premios, '0');
        colocarElementos(tablero, paredes, 'X');
        colocarElementos(tablero, fantasmas, '@');

        colocarElementos(tablero, 1, '$');

        mostrarTablero(tablero);

        int filaPac, colPac;

        do {
            System.out.print("        Fila inicial: ");
            filaPac = sc.nextInt();
            System.out.print("        Columna inicial: ");
            colPac = sc.nextInt();
        } while (filaPac < 0 || filaPac >= filas ||
                 colPac < 0 || colPac >= columnas ||
                 tablero[filaPac][colPac] != ' ');

        tablero[filaPac][colPac] = '<';

        jugar(tablero, usuario, filaPac, colPac, premios + 1);
    }
    public static void colocarElementos(char[][] tablero, int cantidad, char simbolo) {

        int filas = tablero.length;
        int columnas = tablero[0].length;

        int colocados = 0;

        while (colocados < cantidad) {

            int f = random.nextInt(filas); 
            int c = random.nextInt(columnas);

            if (tablero[f][c] == ' ') {
                tablero[f][c] = simbolo;
                colocados++;
            }
        }
    }


    public static void mostrarTablero(char[][] tablero) {

      

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print(" ");
                System.out.print(" " + tablero[i][j] + " ");
                  System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void jugar(char[][] tablero, String usuario,
                             int filaPac, int colPac, int totalPremios) {

        int vidas = 3;
        int puntos = 0;
        int premiosRestantes = totalPremios;

        boolean jugando = true;

        while (jugando) {
            System.out.println("                                                  ");
            System.out.println("-------------------------------------------------");
            mostrarTablero(tablero);
            System.out.println("-------------------------------------------------");
            System.out.println("      Usuario: " + usuario +
                               "\n      Puntos: " + puntos +
                               "\n      Vidas: " + vidas);

            System.out.print("Movimiento (8=Arriba,5=Abajo,4=Izq,6=Der, F=Pausa): ");
            String entrada = sc.next().toUpperCase();
            System.out.println("                                                  ");

            if (entrada.equals("F")) {
                if (menuPausa())
                    break;
                else
                    continue;
            }

            int nuevaFila = filaPac;
            int nuevaCol = colPac;

            switch (entrada) {
                case "8": nuevaFila--; break;
                case "5": nuevaFila++; break;
                case "4": nuevaCol--; break;
                case "6": nuevaCol++; break;
                default: continue;
            }

            // Bordes infinitos
            if (nuevaFila < 0) nuevaFila = tablero.length - 1;
            if (nuevaFila >= tablero.length) nuevaFila = 0;
            if (nuevaCol < 0) nuevaCol = tablero[0].length - 1;
            if (nuevaCol >= tablero[0].length) nuevaCol = 0;

            if (tablero[nuevaFila][nuevaCol] == 'X') {
                continue; 
            }

            if (tablero[nuevaFila][nuevaCol] == '@') {
                vidas--;
            }

            if (tablero[nuevaFila][nuevaCol] == '0') {
                puntos += 10;
                premiosRestantes--;
            }

            if (tablero[nuevaFila][nuevaCol] == '$') {
                puntos += 15;
                premiosRestantes--;
            }

            tablero[filaPac][colPac] = ' ';
            filaPac = nuevaFila;
            colPac = nuevaCol;
            tablero[filaPac][colPac] = '<';

            if (vidas == 0) {
                System.out.println("        ¡Perdiste!");
                jugando = false;
            }

            if (premiosRestantes == 0) {
                System.out.println("        ¡Ganaste!");
                jugando = false;
            }
        }
        
        historialUsuarios[contadorHistorial] = usuario;
        historialPuntos[contadorHistorial] = puntos;
        contadorHistorial++;   
    }


    public static boolean menuPausa() {

        int op;

        do {
            System.out.println("\n======= PAUSA =======");
            System.out.println("    3. Regresar");
            System.out.println("    4. Terminar partida");
            System.out.print("            Seleccione opciOn: ");

            op = sc.nextInt();

            if (op != 3 && op != 4) {
                System.out.println("Opcion invalida.");
            }

        } while (op != 3 && op != 4);

        return op == 4; 
    }
}
