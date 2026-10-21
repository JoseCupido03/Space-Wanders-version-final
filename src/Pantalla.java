/**
 * Pantalla de inicio grande: título en letras gigantes y menú que ocupa casi todo el ancho de la terminal.
 * También dibuja la pantalla de CONTROLES (guía rápida del juego).
 */
public class Pantalla {

    /**
     * Ancho interior del menú. El título grande mide 165 columnas, así que NO lo bajes de 167.
     * Si el marco se ve partido es que tu terminal es más angosta: agrándala o baja este número (mínimo 167).
     */
    public static int ANCHO = 172;

    // Título grande (165 columnas). Todas las filas se igualan para que no se descuadre.
    private static final String[] TITULO = igualar(new String[] {
        "  █████████  ███████████    █████████     █████████  ██████████    █████   ███   █████   █████████   ██████   █████ ██████████   ██████████ ███████████    █████████",
        " ███░░░░░███░░███░░░░░███  ███░░░░░███   ███░░░░░███░░███░░░░░█   ░░███   ░███  ░░███   ███░░░░░███ ░░██████ ░░███ ░░███░░░░███ ░░███░░░░░█░░███░░░░░███  ███░░░░░███",
        "░███    ░░░  ░███    ░███ ░███    ░███  ███     ░░░  ░███  █ ░     ░███   ░███   ░███  ░███    ░███  ░███░███ ░███  ░███   ░░███ ░███  █ ░  ░███    ░███ ░███    ░░░",
        "░░█████████  ░██████████  ░███████████ ░███          ░██████       ░███   ░███   ░███  ░███████████  ░███░░███░███  ░███    ░███ ░██████    ░██████████  ░░█████████",
        " ░░░░░░░░███ ░███░░░░░░   ░███░░░░░███ ░███          ░███░░█       ░░███  █████  ███   ░███░░░░░███  ░███ ░░██████  ░███    ░███ ░███░░█    ░███░░░░░███  ░░░░░░░░███",
        " ███    ░███ ░███         ░███    ░███ ░░███     ███ ░███ ░   █     ░░░█████░█████░    ░███    ░███  ░███  ░░█████  ░███    ███  ░███ ░   █ ░███    ░███  ███    ░███",
        "░░█████████  █████        █████   █████ ░░█████████  ██████████       ░░███ ░░███      █████   █████ █████  ░░█████ ██████████   ██████████ █████   █████░░█████████",
        " ░░░░░░░░░  ░░░░░        ░░░░░   ░░░░░   ░░░░░░░░░  ░░░░░░░░░░         ░░░   ░░░      ░░░░░   ░░░░░ ░░░░░    ░░░░░ ░░░░░░░░░░   ░░░░░░░░░░ ░░░░░   ░░░░░  ░░░░░░░░░"
    });

    private static String[] igualar(String[] filas) {
        int max = 0;
        for (String f : filas) max = Math.max(max, f.length());
        for (int i = 0; i < filas.length; i++) {
            StringBuilder sb = new StringBuilder(filas[i]);
            while (sb.length() < max) sb.append(' ');
            filas[i] = sb.toString();
        }
        return filas;
    }

    // Letras gigantes de la palabra CONTROLES (6 filas cada una)
    private static final String[][] LETRAS = {
        { " ██████╗", "██╔════╝", "██║     ", "██║     ", "╚██████╗", " ╚═════╝" },                          // C
        { " ██████╗ ", "██╔═══██╗", "██║   ██║", "██║   ██║", "╚██████╔╝", " ╚═════╝ " },                  // O
        { "███╗   ██╗", "████╗  ██║", "██╔██╗ ██║", "██║╚██╗██║", "██║ ╚████║", "╚═╝  ╚═══╝" },             // N
        { "████████╗", "╚══██╔══╝", "   ██║   ", "   ██║   ", "   ██║   ", "   ╚═╝   " },                  // T
        { "██████╗ ", "██╔══██╗", "██████╔╝", "██╔══██╗", "██║  ██║", "╚═╝  ╚═╝" },                          // R
        { " ██████╗ ", "██╔═══██╗", "██║   ██║", "██║   ██║", "╚██████╔╝", " ╚═════╝ " },                  // O
        { "██╗     ", "██║     ", "██║     ", "██║     ", "███████╗", "╚══════╝" },                          // L
        { "███████╗", "██╔════╝", "█████╗  ", "██╔══╝  ", "███████╗", "╚══════╝" },                          // E
        { "███████╗", "██╔════╝", "███████╗", "╚════██║", "███████║", "╚══════╝" }                           // S
    };

    /** Menú principal (la voz se toma del estado actual de voz.activa). */
    public static void menu(boolean sonidoActivo) {
        menu(sonidoActivo, voz.activa);
    }

    public static void menu(boolean sonidoActivo, boolean vozActiva) {
        int W = ANCHO;
        String cian = Estilo.CIAN, mag = Estilo.MAGENTA, r = Estilo.RESET;
        String blanco = Estilo.NEGRITA + Estilo.BLANCO, rojo = Estilo.ROJO, verde = Estilo.VERDE;

        // --- barra superior ---
        String izq = "◢◤ [QUANTUM_GRID_CORE v5.0] ";
        String der = " ◥◣";
        System.out.println(mag + izq + repetir('═', W + 2 - izq.length() - der.length()) + der + r);

        // --- título gigante, centrado, con degradado y aparición línea por línea ---
        StringBuilder arte = new StringBuilder();
        for (int i = 0; i < TITULO.length; i++) {
            int sangria = Math.max(0, (W + 2 - TITULO[i].length()) / 2);
            arte.append(repetir(' ', sangria)).append(TITULO[i]);
            if (i < TITULO.length - 1) arte.append('\n');
        }
        Estilo.banner(arte.toString(), Estilo.NEON);

        String auth = " AUTH_LINK_OK ══";
        System.out.println(mag + repetir('═', W + 2 - auth.length()) + auth + r);

        // --- caja de estado (matriz, energía, música y voz) ---
        System.out.println(cian + "╔" + repetir('═', W) + "╗" + r);

        String estado = sonidoActivo ? verde + "ON" : rojo + "OFF";
        String estadoVoz = vozActiva ? verde + "ON" : rojo + "OFF";
        int c2 = 2 + W / 4, c3 = 2 + W / 2, c4 = 2 + 3 * W / 4;
        System.out.println(cian + "║" + col(4) + "⚡ MATRIZ: CONECTADA"
                + col(c2 - 2) + "│" + col(c2) + "🔋 ENERGÍA: 100%"
                + col(c3 - 2) + "│" + col(c3) + "🔊 MÚSICA: " + estado + cian
                + col(c4 - 2) + "│" + col(c4) + "🎙 VOZ: " + estadoVoz + cian
                + col(W + 2) + "║" + r);

        System.out.println(tituloCaja("[ MENU PRINCIPAL ]", W));
        System.out.println(cian + "╠" + repetir('═', W) + "╣" + r);

        // --- opciones: cada una es un botón grande con su propio marco ---
        System.out.println(fila(3, "", W));
        System.out.println(boton("📊", "1", "INICIAR SIMULACIÓN DE LA NAVE", Estilo.VERDE, W));
        System.out.println(boton("⚙️", "2", "INSTRUCCIONES DE JUEGO", Estilo.AZUL, W));
        System.out.println(boton("🎮", "3", "CONTROLES Y AYUDA", Estilo.AMARILLO, W));
        System.out.println(boton("🔋", "4", "APAGAR TERMINAL CUÁNTICA", Estilo.ROJO, W));
        System.out.println(fila(3, "", W));

        System.out.println(cian + "╠" + repetir('═', W) + "╣" + r);

        String cmd = "◥◣ AUDIO: 'on' / 'off' = música   ·   'voz on' / 'voz off' = voces ◢◤";
        System.out.println(mag + repetir(' ', Math.max(0, (W + 2 - cmd.length()) / 2)) + cmd + r);
        System.out.print(cian + "🧠 SELECCIONE MÓDULO O COMANDO > " + r);
    }

    /** Pantalla de controles: explica cómo se juega. */
    public static void controles(boolean sonidoActivo, boolean vozActiva) {
        int W = ANCHO;
        String cian = Estilo.CIAN, mag = Estilo.MAGENTA, r = Estilo.RESET;
        String blanco = Estilo.NEGRITA + Estilo.BLANCO, rojo = Estilo.ROJO, verde = Estilo.VERDE;

        // --- barra superior ---
        String izq = "◢◤ [PANEL DE CONTROLES] ";
        String der = " ◥◣";
        System.out.println(mag + izq + repetir('═', W + 2 - izq.length() - der.length()) + der + r);

        // --- título gigante "CONTROLES" con degradado ---
        StringBuilder arte = new StringBuilder();
        for (int fila = 0; fila < 6; fila++) {
            StringBuilder linea = new StringBuilder();
            for (int k = 0; k < LETRAS.length; k++) {
                linea.append(LETRAS[k][fila]);
                if (k < LETRAS.length - 1) linea.append(' ');
            }
            int sangria = Math.max(0, (W + 2 - linea.length()) / 2);
            arte.append(repetir(' ', sangria)).append(linea);
            if (fila < 5) arte.append('\n');
        }
        Estilo.banner(arte.toString(), Estilo.GALAXIA);

        String auth = " GUÍA RÁPIDA ══";
        System.out.println(mag + repetir('═', W + 2 - auth.length()) + auth + r);

        // --- caja con los controles ---
        System.out.println(cian + "╔" + repetir('═', W) + "╗" + r);
        System.out.println(tituloCaja("[ CÓMO SE JUEGA ]", W));
        System.out.println(cian + "╠" + repetir('═', W) + "╣" + r);

        filaControl(Estilo.AMARILLO, "⏎ ENTER", "Presiona ENTER para avanzar al siguiente bloque de la historia.");
        filaControl(Estilo.VERDE, "1 · 2 · 3", "En cada decisión escribe el NÚMERO de tu opción y presiona ENTER.");
        filaControl(Estilo.CIAN, "on / off", "Escribe 'on' u 'off' en el menú principal para encender o apagar la música.");
        filaControl(Estilo.ROSA, "voz on / voz off", "Escribe 'voz on' o 'voz off' en el menú principal para activar o silenciar las voces.");
        filaControl(Estilo.VIOLETA, "ASISTENTE", "La ventanita de abajo a la derecha te explica la situación antes de decidir.");
        filaControl(Estilo.VIOLETA, "", "Puedes arrastrarla con el mouse para moverla de lugar.");
        filaControl(Estilo.NARANJA, "ENTER + VOZ", "Si presionas ENTER mientras alguien habla, la voz se corta y la historia avanza.");
        filaControl(Estilo.ROJO, "ERRORES", "Si escribes una letra en lugar de un número, el juego te pedirá intentarlo de nuevo.");
        filaControl(Estilo.AZUL, "MISIÓN", "Tras el desastre: busca 3 placas en las cámaras y codifica a ARIS 9 (mín. 4 de 6).");

        System.out.println(cian + "╠" + repetir('═', W) + "╣" + r);

        String musica = sonidoActivo ? verde + "ON" : rojo + "OFF";
        String vozEstado = vozActiva ? verde + "ON" : rojo + "OFF";
        int sang = Math.max(3, (W - 56) / 2);
        System.out.println(fila(sang, blanco + "ESTADO ACTUAL  ►  " + cian + "🔊 MÚSICA: " + musica
                + cian + "   │   🎙 VOZ: " + vozEstado, W));
        System.out.println(cian + "╚" + repetir('═', W) + "╝" + r);
    }

    /** Un botón de 3 filas con marco de color. Para hacerlo más ancho cambia "ancho". */
    private static String boton(String icono, String numero, String texto, String color, int W) {
        int ancho = 76;
        int x = Math.max(3, (W + 2 - ancho) / 2 + 1);          // columna donde empieza el botón
        String c = Estilo.CIAN;
        String linea = repetir('─', ancho - 2);
        String lado = c + col(W + 2) + "║" + Estilo.RESET;
        return c + "║" + col(x) + color + "┌" + linea + "┐" + lado + "\n"
             + c + "║" + col(x) + color + "│" + col(x + 3) + icono + " " + Estilo.NEGRITA + Estilo.BLANCO
                 + "[" + numero + "]  " + texto + Estilo.RESET + color + col(x + ancho - 1) + "│" + lado + "\n"
             + c + "║" + col(x) + color + "└" + linea + "┘" + lado;
    }

    /** Una fila de la guía: etiqueta de color a la izquierda, explicación a la derecha. */
    private static void filaControl(String color, String etiqueta, String texto) {
        System.out.println(Estilo.CIAN + "║" + col(4) + color + Estilo.NEGRITA + etiqueta + Estilo.RESET
                + col(27) + Estilo.CIAN + "│" + col(30) + Estilo.BLANCO + texto
                + Estilo.CIAN + col(ANCHO + 2) + "║" + Estilo.RESET);
    }

    /** Título de sección dentro de una caja, con líneas a los lados. */
    private static String tituloCaja(String titulo, int W) {
        int rel = W - 2 - (titulo.length() + 4);
        return Estilo.CIAN + "║ " + repetir('─', rel / 2) + "  " + Estilo.NEGRITA + Estilo.BLANCO + titulo
                + Estilo.RESET + Estilo.CIAN + "  " + repetir('─', rel - rel / 2) + col(W + 2) + "║" + Estilo.RESET;
    }

    private static String fila(int sangria, String contenido, int W) {
        return Estilo.CIAN + "║" + col(sangria + 2) + contenido + Estilo.CIAN + col(W + 2) + "║" + Estilo.RESET;
    }

    /** Salta a una columna de la terminal: así el marco derecho siempre queda alineado, aunque haya emojis. */
    private static String col(int n) {
        return "\u001B[" + n + "G";
    }

    private static String repetir(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(c);
        return sb.toString();
    }
}