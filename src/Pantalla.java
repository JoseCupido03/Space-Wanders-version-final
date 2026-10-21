/**
 * Pantalla de inicio grande: título en letras gigantes y menú que ocupa todo el ancho de la terminal.
 * También dibuja la pantalla de CONTROLES (guía rápida del juego).
 */
public class Pantalla {

    /** Ancho interior del menú. Súbelo (150, 180...) si tu terminal es más ancha, bájalo si es angosta. */
    public static int ANCHO = 120;

    private static final String[] TITULO = {
        "███████╗██████╗  █████╗  ██████╗███████╗    ██╗    ██╗ █████╗ ███╗   ██╗██████╗ ███████╗██████╗ ███████╗",
        "██╔════╝██╔══██╗██╔══██╗██╔════╝██╔════╝    ██║    ██║██╔══██╗████╗  ██║██╔══██╗██╔════╝██╔══██╗██╔════╝",
        "███████╗██████╔╝███████║██║     █████╗      ██║ █╗ ██║███████║██╔██╗ ██║██║  ██║█████╗  ██████╔╝███████╗",
        "╚════██║██╔═══╝ ██╔══██║██║     ██╔══╝      ██║███╗██║██╔══██║██║╚██╗██║██║  ██║██╔══╝  ██╔══██╗╚════██║",
        "███████║██║     ██║  ██║╚██████╗███████╗    ╚███╔███╔╝██║  ██║██║ ╚████║██████╔╝███████╗██║  ██║███████║",
        "╚══════╝╚═╝     ╚═╝  ╚═╝ ╚═════╝╚══════╝     ╚══╝╚══╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═════╝ ╚══════╝╚═╝  ╚═╝╚══════╝"
    };

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

        int sang = Math.max(3, (W - 50) / 2);
        System.out.println(fila(sang, blanco + "📊 [SYS_MOD_01] ──> INICIAR SIMULACIÓN DE LA NAVE", W));
        System.out.println(fila(sang, blanco + "⚙️ [SYS_MOD_02] ──> INSTRUCCIONES DE JUEGO", W));
        System.out.println(fila(sang, Estilo.AMARILLO + "🎮 [SYS_MOD_03] ──> CONTROLES Y AYUDA", W));
        System.out.println(fila(sang, rojo + "🔋 [SYS_MOD_04] ──> APAGAR TERMINAL CUÁNTICA", W));

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