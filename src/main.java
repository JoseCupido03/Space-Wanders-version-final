import java.util.Scanner;

public class main {

    // Paleta de colores Cyberpunk de alta intensidad
    public static final String RESET = "\u001B[0m";
    public static final String NEON_CYAN = "\u001B[36m";
    public static final String NEON_MAGENTA = "\u001B[35m";
    public static final String NEON_VERDE = "\u001B[32m";
    public static final String NEON_ROJO = "\u001B[31m";
    public static final String NEON_AZUL = "\u001B[34m";
    public static final String BLANCO_BRILLANTE = "\u001B[37;1m";

    public static void main(String[] args) {
        Estilo.iniciar();
        Asistente.iniciar();   // abre la ventanita del asistente (abajo a la derecha)

        // === AUDIO EN SEGUNDO PLANO (Ruta Actualizada a spaces.wav) ===
        AudioEspacial musicaFondo = new AudioEspacial("sonido/spaces.wav");
        new Thread(musicaFondo).start();
        boolean sonidoActivo = true; // Control del estado del audio
        // =====================================================

        Scanner sc = new Scanner(System.in);
        String entrada = "";
        int opcion = 0;

        // Secuencia de arranque del sistema operativo de la nave
        limpiarPantalla();
        mostrarArranqueSujeto();

        while (opcion != 4) {
            limpiarPantalla();

            Pantalla.menu(sonidoActivo, voz.activa);
            entrada = sc.nextLine().trim().toLowerCase(); // Lee toda la línea para evitar bugs

            // === CONTROL DE COMANDOS DE AUDIO DIRECTOS ===
            if (entrada.equals("off")) {
                if (sonidoActivo) {
                    System.out.println(NEON_ROJO + "\n🔇 Desactivando sistemas de audio..." + RESET);
                    musicaFondo.detener();
                    sonidoActivo = false;
                } else {
                    System.out.println(NEON_CYAN + "\n[INFO] El audio ya está apagado." + RESET);
                }
                esperar(1200);
                continue; // Recarga el menú principal para reflejar el cambio
            }

            if (entrada.equals("on")) {
                if (!sonidoActivo) {
                    System.out.println(NEON_VERDE + "\n🔊 Reiniciando transmisiones de audio..." + RESET);
                    musicaFondo = new AudioEspacial("sonido/spaces.wav");
                    new Thread(musicaFondo).start();
                    sonidoActivo = true;
                } else {
                    System.out.println(NEON_CYAN + "\n[INFO] El audio ya está encendido." + RESET);
                }
                esperar(1200);
                continue; // Recarga el menú
            }

            // === CONTROL DE VOCES (portada): "voz on", "voz off" o "voz" para alternar ===
            if (entrada.equals("voz on") || entrada.equals("voz off") || entrada.equals("voz")) {
                boolean activar = entrada.equals("voz on") || (entrada.equals("voz") && !voz.activa);
                if (activar) {
                    if (voz.activa) {
                        System.out.println(NEON_CYAN + "\n[INFO] Las voces ya están activadas." + RESET);
                    } else {
                        voz.activa = true;
                        System.out.println(NEON_VERDE + "\n🎙 Voces activadas." + RESET);
                        voz.hablar("Voces activadas");
                    }
                } else {
                    if (voz.activa) {
                        voz.callar();
                        voz.activa = false;
                        System.out.println(NEON_ROJO + "\n🔇 Voces desactivadas." + RESET);
                    } else {
                        System.out.println(NEON_CYAN + "\n[INFO] Las voces ya están desactivadas." + RESET);
                    }
                }
                esperar(1200);
                continue; // Recarga el menú principal para reflejar el cambio
            }

            // === PROCESAMIENTO DE OPCIONES NUMÉRICAS ===
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println(NEON_ROJO + "\n⚠️ [ALERTA] COMANDO IMPREVISTO O ENTRADA CORRUPTA." + RESET);
                esperar(1500);
                opcion = 0; // Resetea la opción para que no salga del bucle
                continue;
            }

            switch (opcion) {
            case 1:
                limpiarPantalla();
                ejecutarCargaCuantica("SINCRONIZANDO INTERFAZ NEURAL", 45);
                ejecutarCargaCuantica("ESTABILIZANDO ENERGÍA DEL MOTOR WORMHOLE", 100);
                System.out.println(NEON_VERDE + "\n[CONEXIÓN SEGURA] DESPLEGANDO AL COMANDANTE...\n" + RESET);
                esperar(1200);
                limpiarPantalla();

                // Lógica de tu juego
                juego jugador = new juego();
                jugador.jugador();
                String nombre = jugador.getPlayer();

                juego.mostrarhistoria(nombre);   // 1) historia con decisiones + desastre
                Mision.reparar(nombre);          // 2) buscar piezas / codificar
                juego.decisionFinal(nombre);     // 3) seguir la misión o volver a la Tierra

                System.out.println(NEON_MAGENTA
                        + "\n🛰️ Crónica pausada. Presiona ENTER para volver al menú principal..." + RESET);
                sc.nextLine();
                break;

            case 2:
                Transicion.entrar("INSTRUCCIONES");
                System.out.println(
                        NEON_MAGENTA + "╔════════════════ 📖 MANUAL DE INSTRUCCIONES ════════════════╗" + RESET);
                System.out.println("  1. Tu misión es explorar y asegurar las cámaras de la nave.");
                System.out.println("  2. Avanzarás en orden por los distintos sectores vitales: ");
                System.out.println("     • La Bóveda (asegurar recursos e inventario).");
                System.out.println("     • Hibernación (despertar o verificar tripulación).");
                System.out.println("     • El Almacén (organizar suministros de emergencia).");
                System.out.println("     • El Laboratorio (analizar muestras del exterior).");
                System.out.println("  3. Interactúa con los personajes para descifrar códigos.");
                System.out.println("  4. Toma decisiones sabias en cada sala para poder sobrevivir.");
                System.out.println("  5. Usa números válidos en la terminal para evitar bloqueos.");
                System.out.println("  6. Revisa CONTROLES (opción 3) para ver cómo apagar la música y las voces.");
                System.out.println(
                        NEON_MAGENTA + "╚════════════════════════════════════════════════════════════╝" + RESET);
                System.out.print(NEON_VERDE + "\nPresiona ENTER para volver al menú principal..." + RESET);
                sc.nextLine();
                Transicion.salir();
                break;

            case 3:
                Transicion.entrar("CONTROLES");
                Pantalla.controles(sonidoActivo, voz.activa);
                System.out.print(NEON_VERDE + "\n⏎ Presiona ENTER para volver al menú principal..." + RESET);
                sc.nextLine();
                Transicion.salir();
                break;

            case 4:
                limpiarPantalla();
                System.out.println(
                        NEON_ROJO + "☢️ Desconectando reactores principales... Cierre de sesión exitoso." + RESET);
                System.out.println(NEON_AZUL + "¡Hasta la próxima, Comandante!" + RESET);
                if (sonidoActivo) {
                    musicaFondo.detener();
                }
                esperar(1500);
                break;

            default:
                System.out.println(
                        NEON_ROJO + "⚠️ ENTRADA ERRÓNEA. Ingrese una opción (1, 2, 3, 4) o comandos (on/off, voz on/voz off).\n" + RESET);
                esperar(1500);
                break;
            }
        }
        sc.close();
        System.exit(0);   // cierra también la ventanita del asistente y la voz
    }

    public static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++)
                System.out.println();
        }
    }

    public static void mostrarArranqueSujeto() {
        System.out.print(NEON_AZUL + "INICIALIZANDO TERMINAL CUÁNTICA [ANUBIS_OS]" + RESET);
        voz.hablar("Inicializando terminal cuantica Anubis OS");
        for (int i = 0; i < 3; i++) {
            esperar(300);
            System.out.print(NEON_AZUL + "." + RESET);
        }
        System.out.println();

        System.out.println(NEON_CYAN + "📡 Sincronizando con satélites del sector espacial..." + RESET);
        voz.hablar("Sincronizando con satelites del sector espacial");
        esperar(500);
        System.out.println(NEON_MAGENTA + "💾 Extrayendo códigos de las cámaras de la nave..." + RESET);
        voz.hablar("Extrayendo codigos de las camaras de la nave");
        esperar(500);
        System.out.println(NEON_VERDE + "⚡ INTERFAZ LISTA. ¡BIENVENIDO COMANDANTE!\n" + RESET);
        voz.hablar("Interfaz lista. Bienvenido comandante");
        esperar(800);
    }

    public static void ejecutarCargaCuantica(String proceso, int limitePorcentaje) {
        int barraTamaño = 20;
        System.out.print(NEON_CYAN + "[Cargando] " + proceso + "\n" + RESET);

        for (int i = 0; i <= barraTamaño; i++) {
            int porcentaje = (i * limitePorcentaje) / barraTamaño;
            System.out.print("\r" + NEON_CYAN + "[ " + RESET);

            for (int j = 0; j < i; j++) {
                System.out.print(NEON_MAGENTA + "█" + RESET);
            }
            for (int j = i; j < barraTamaño; j++) {
                System.out.print(" ");
            }

            System.out.print(NEON_CYAN + " ] " + NEON_VERDE + porcentaje + "%" + RESET);
            esperar(70);
        }
        System.out.println("\n");
    }
}