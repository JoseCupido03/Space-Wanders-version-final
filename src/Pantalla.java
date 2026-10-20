/** Pantalla de inicio grande: título en letras gigantes y menú que ocupa todo el ancho de la terminal. */
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

    public static void menu(boolean sonidoActivo) {
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

        // --- caja de estado ---
        System.out.println(cian + "╔" + repetir('═', W) + "╗" + r);

        String estado = sonidoActivo ? verde + "ON" : rojo + "OFF";
        int c2 = 2 + W / 3, c3 = 2 + 2 * W / 3;
        System.out.println(cian + "║" + col(4) + "⚡ MATRIZ: CONECTADA"
                + col(c2 - 2) + "│" + col(c2) + "🔋 ENERGÍA: 100%"
                + col(c3 - 2) + "│" + col(c3) + "🔊 AUDIO: " + estado + cian + col(W + 2) + "║" + r);

        int rel = W - 24;
        System.out.println(cian + "║ " + repetir('─', rel / 2) + "  " + blanco + "[ MENU PRINCIPAL ]" + cian + "  "
                + repetir('─', rel - rel / 2) + col(W + 2) + "║" + r);
        System.out.println(cian + "╠" + repetir('═', W) + "╣" + r);

        int sang = Math.max(3, (W - 50) / 2);
        System.out.println(fila(sang, blanco + "📊 [SYS_MOD_01] ──> INICIAR SIMULACIÓN DE LA NAVE", W));
        System.out.println(fila(sang, blanco + "⚙️ [SYS_MOD_02] ──> INSTRUCCIONES DE JUEGO", W));
        System.out.println(fila(sang, rojo + "🔋 [SYS_MOD_03] ──> APAGAR TERMINAL CUÁNTICA", W));

        System.out.println(cian + "╠" + repetir('═', W) + "╣" + r);

        String cmd = "◥◣ COMANDOS DE AUDIO: Escribe 'on' u 'off' para controlar la música ◢◤";
        System.out.println(mag + repetir(' ', Math.max(0, (W + 2 - cmd.length()) / 2)) + cmd + r);
        System.out.print(cian + "🧠 SELECCIONE MÓDULO O COMANDO > " + r);
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