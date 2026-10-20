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

        while (opcion != 3) {
            limpiarPantalla();

            // Indicador visual dinámico en la cabecera
            String estadoSonido = sonidoActivo ? NEON_VERDE + "ON " : NEON_ROJO + "OFF";

        Pantalla.menu(sonidoActivo);
          if (false) {   // menú viejo, ya no se usa
            System.out.println(NEON_MAGENTA
                    + "◢◤ [QUANTUM_GRID_CORE v5.0] ═══════════════════════════════════════════ ◥◣" + RESET);
            System.out.println(NEON_CYAN + "  █▀▀█ █▀▀█ █▀▀█ █▀▀ █▀▀   █   █ █▀▀█ █▄  █ █▀▀▄ █▀▀ █▀▀█ █▀▀  " + RESET);
            System.out.println(NEON_CYAN + "  ▀▀▄▄ █▄▄█ █▄▄█ █   █▀▀   █ █ █ █▄▄█ █ █ █ █  █ █▀▀ █▄▄▀ ▀▀█  " + RESET);
            System.out.println(NEON_CYAN + "  █▄▄█ █    █  █ ▀▀▀ ▀▀▀   ▀▄▀▄▀ █  █ █  ▀█ █▄▀  ▀▀▀ █  █ ▀▀▀  " + RESET);
            System.out.println(NEON_MAGENTA
                    + "═══════════════════════════════════════════════════════════ AUTH_LINK_OK ══" + RESET);
            System.out.println(NEON_CYAN
                    + " ║  ⚡ MATRIZ: CONECTADA  │  🔋 ENERGÍA: 100%  │  🔊 AUDIO: " + estadoSonido + NEON_CYAN + "          ║" + RESET);
            System.out.println(NEON_CYAN + " ║ ──────────────────────  " + BLANCO_BRILLANTE + "[ MENU PRINCIPAL ]"
                    + NEON_CYAN + "  ────────────────────── ║" + RESET);
            System.out.println(
                    NEON_CYAN + " ╠════════════════════════════════════════════════════════════════════════╣" + RESET);
            System.out.println(NEON_CYAN + " ║ " + BLANCO_BRILLANTE
                    + " 📊 [SYS_MOD_01] ──> INICIAR SIMULACIÓN DE LA NAVE                     " + NEON_CYAN + "║"
                    + RESET);
            System.out.println(NEON_CYAN + " ║ " + BLANCO_BRILLANTE
                    + " ⚙️  [SYS_MOD_02] ──> INSTRUCCIONES DE JUEGO                         " + NEON_CYAN + "║"
                    + RESET);
            System.out.println(NEON_CYAN + " ║ " + NEON_ROJO
                    + " 🔋 [SYS_MOD_03] ──> APAGAR TERMINAL CUÁNTICA                           " + NEON_CYAN + "║"
                    + RESET);
            System.out.println(
                    NEON_CYAN + " ╠════════════════════════════════════════════════════════════════════════╣" + RESET);
            System.out.println(NEON_MAGENTA
                    + "◥◣ COMANDOS DE AUDIO: Escribe 'on' u 'off' para controlar la música       ◢◤" + RESET);
            System.out.print(NEON_CYAN + "🧠 SELECCIONE MÓDULO O COMANDO > " + RESET);
          }
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
                juego.mostrarhistoria(jugador.getPlayer());

                personajes cod = new personajes();
                cod.codificar();

                nave camara = new nave();
                camara.bobeda(jugador.getPlayer());

                nave camara2 = new nave();
                camara2.hibernacion(jugador.getPlayer());

                nave camara3 = new nave();
                camara3.almacen(jugador.getPlayer());

                nave camara4 = new nave();
                camara4.laboratorio(jugador.getPlayer());

                nave camara5 = new nave();
                camara5.exterior(jugador.getPlayer());

                personajes personajes = new personajes();
                personajes.moverse();

                System.out.println(NEON_MAGENTA
                        + "\n🛰️ Crónica pausada. Presiona ENTER para volver al menú principal..." + RESET);
                sc.nextLine(); 
                break;

            case 2:
                limpiarPantalla();
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
                System.out.println(
                        NEON_MAGENTA + "╚════════════════════════════════════════════════════════════╝" + RESET);
                System.out.print(NEON_VERDE + "\nPresiona ENTER para replegar el manual táctico..." + RESET);
                sc.nextLine(); 
                break;

            case 3:
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
                        NEON_ROJO + "⚠️ ENTRADA ERRÓNEA. Ingrese una credencial (1, 2, 3) o comandos (on/off).\n" + RESET);
                esperar(1500);
                break;
            }
        }
        sc.close();
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