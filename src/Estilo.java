import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ESTILO: colores, degradados y animaciones de texto para la consola.
 *
 * Copia este archivo a tu carpeta src/ (junto a main.java) y úsalo así:
 *
 *   Estilo.iniciar();                                       // una vez, al empezar main
 *   Estilo.escribir("Hola, piloto.");                       // degradado neón, letra por letra
 *   Estilo.escribir("¡Alerta!", Estilo.ROJO);               // un solo color
 *   Estilo.dialogo("Kael", Estilo.CIAN, "Aquí Kael...");    // un personaje habla
 *   Estilo.arcoiris("¡Misión cumplida!");                   // cada letra de un color
 *
 * MODO AUTOMÁTICO (el más fácil): cambia System.out.println( por Estilo.println(
 * y System.out.print( por Estilo.print( en tus archivos. Estilo detecta solo quién
 * habla (Darian, Axel, ARIS 9...), las opciones 1. 2. 3., los [ERROR], la narración
 * entre “comillas” y los dibujos/cajas, y le da a cada cosa su color y animación.
 *
 * Si un texto ya trae colores propios (por ejemplo NEON_CYAN + "hola" + RESET),
 * se respetan tal cual y solo se le agrega la animación.
 *
 * NOVEDADES: deja una línea en blanco antes de que hable alguien, muestra el
 * aviso "Presiona ENTER para continuar" (avisoEnter / borrarAviso) y hace que
 * cada personaje hable con su voz (voz.hablar).
 */
public class Estilo {

    // =====================================================================
    //  AJUSTES (se pueden cambiar desde cualquier parte del juego)
    // =====================================================================

    /** false = todo sale al instante (útil para probar el juego sin esperar). */
    public static boolean animar = true;

    /** Milisegundos entre letra y letra. Menos = más rápido. */
    public static int msPorLetra = 18;

    // =====================================================================
    //  COLORES (24 bits, tonos neón)
    // =====================================================================

    public static final String RESET   = "\u001B[0m";
    public static final String NEGRITA = "\u001B[1m";

    public static final String CIAN     = rgb(0, 240, 255);
    public static final String MAGENTA  = rgb(255, 50, 220);
    public static final String VERDE    = rgb(57, 255, 20);
    public static final String ROJO     = rgb(255, 70, 90);
    public static final String AMARILLO = rgb(255, 230, 0);
    public static final String NARANJA  = rgb(255, 150, 30);
    public static final String VIOLETA  = rgb(175, 120, 255);
    public static final String ROSA     = rgb(255, 110, 190);
    public static final String AZUL     = rgb(70, 150, 255);
    public static final String BLANCO   = rgb(240, 240, 255);
    public static final String GRIS     = rgb(120, 125, 145);

    /** Degradados: { color inicial, color final } en RGB. */
    public static final int[][] NEON    = { {0, 240, 255},  {255, 50, 220} };  // cian -> magenta
    public static final int[][] FUEGO   = { {255, 235, 0},  {255, 50, 60}  };  // amarillo -> rojo
    public static final int[][] GALAXIA = { {110, 90, 255}, {255, 90, 200} };  // violeta -> rosa
    public static final int[][] OCEANO  = { {0, 255, 170},  {40, 120, 255} };  // verde agua -> azul

    /** Cualquier color que quieras: Estilo.rgb(255, 128, 0). */
    public static String rgb(int r, int g, int b) {
        return "\u001B[38;2;" + r + ";" + g + ";" + b + "m";
    }

    // =====================================================================
    //  PREPARACIÓN
    // =====================================================================

    /** Pon true para ver el aviso "[ESTILO ACTIVO]" al arrancar (sirve para comprobar que funciona). */
    public static boolean avisar = true;

    public static void iniciar() {
        // 1) Windows: consola en UTF-8 (si falla, no pasa nada)
        try {
            if (System.getProperty("os.name", "").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "chcp 65001 > nul").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // seguimos igual
        }
        // 2) Interceptar System.out: todo lo que imprima el juego pasa por Estilo
        try {
            real = new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8");
            System.setOut(new Interceptor(real));
            if (avisar) {
                real.println(NEGRITA + VERDE + "[ESTILO ACTIVO]" + RESET);
            }
        } catch (Exception e) {
            System.out.println("[ESTILO] No pudo iniciar: " + e);
        }
    }

    // =====================================================================
    //  AVISO "PRESIONA ENTER" (lo usa la clase Teclado)
    // =====================================================================

    /** Muestra "Presiona ENTER para continuar" en gris, sin animación. */
    public static void avisoEnter() {
        o().print(GRIS + "   \u25B6 Presiona ENTER para continuar..." + RESET);
        o().flush();
    }

    /** Borra el aviso cuando el jugador ya presionó ENTER y corta la voz que estaba sonando. */
    public static void borrarAviso() {
        o().print("\u001B[1A\u001B[2K\r");
        o().flush();
        voz.callar();
    }

    // =====================================================================
    //  TEXTO CON EFECTO "MÁQUINA DE ESCRIBIR"
    // =====================================================================

    /** Degradado neón (cian a magenta), letra por letra. */
    public static void escribir(String texto) {
        teclear(texto, pintorDegradado(NEON), msPorLetra, true);
    }

    /** Un solo color, letra por letra. */
    public static void escribir(String texto, String color) {
        teclear(texto, pintorSolido(color), msPorLetra, true);
    }

    /** Un solo color con velocidad propia (ms entre letras). */
    public static void escribir(String texto, String color, int ms) {
        teclear(texto, pintorSolido(color), ms, true);
    }

    /** Degradado a elección: Estilo.escribir("hola", Estilo.FUEGO). */
    public static void escribir(String texto, int[][] degradado) {
        teclear(texto, pintorDegradado(degradado), msPorLetra, true);
    }

    /** Cada letra de un color del arcoíris. */
    public static void arcoiris(String texto) {
        teclear(texto, pintorIris(), msPorLetra, true);
    }

    /** Un personaje habla: nombre en negrita y su frase escrita en su color. */
    public static void dialogo(String quien, String color, String texto) {
        o().print(NEGRITA + color + "[" + quien + "] " + RESET);
        teclear(texto, pintorSolido(color), msPorLetra, true);
    }

    /** Escribe la pregunta con color y deja el cursor al final, listo para tu scanner.nextLine(). */
    public static void prompt(String texto) {
        teclear(texto, pintorSolido(AMARILLO), msPorLetra, false);
    }

    public static void prompt(String texto, String color) {
        teclear(texto, pintorSolido(color), msPorLetra, false);
    }

    // =====================================================================
    //  EFECTOS ESPECIALES
    // =====================================================================

    /** Arte ASCII o cajas de menú: aparece línea por línea con un degradado en diagonal. */
    public static void banner(String arte) {
        banner(arte, NEON);
    }

    public static void banner(String arte, int[][] degradado) {
        String[] lineas = arte.split("\n", -1);
        int ancho = 1;
        for (String l : lineas) {
            ancho = Math.max(ancho, l.codePointCount(0, l.length()));
        }
        double total = ancho + lineas.length * 2.0;

        for (int fila = 0; fila < lineas.length; fila++) {
            String linea = lineas[fila];
            StringBuilder sb = new StringBuilder();
            if (linea.indexOf('\u001B') >= 0) {
                sb.append(linea);                       // ya trae sus colores: se deja igual
            } else {
                int col = 0;
                for (int k = 0; k < linea.length(); ) {
                    int cp = linea.codePointAt(k);
                    k += Character.charCount(cp);
                    if (cp != ' ') {
                        sb.append(mezcla(degradado[0], degradado[1], (col + fila * 2.0) / total));
                    }
                    sb.appendCodePoint(cp);
                    col++;
                }
            }
            sb.append(RESET);
            o().println(sb);
            o().flush();
            dormir(60);
        }
    }

    /**
     * Efecto "hackeo": las letras aparecen como símbolos al azar y se van
     * descifrando de izquierda a derecha. Ideal para alertas y títulos cortos
     * (que quepan en una sola línea de la consola).
     */
    public static void descifrar(String texto, String color) {
        if (!animar) {
            o().println(color + texto + RESET);
            return;
        }
        final String ruido = "!<>-_\\/[]{}=+*^?#%$&01";
        Random azar = new Random();
        int[] cps = texto.codePoints().toArray();
        int n = cps.length;
        int ventana = 6;

        o().print("\u001B[?25l");                // oculta el cursor mientras anima
        try {
            for (int paso = 0; paso <= n + ventana; paso++) {
                StringBuilder sb = new StringBuilder("\r");
                for (int i = 0; i < n; i++) {
                    if (i >= paso) {
                        sb.append(' ');
                    } else if (cps[i] == ' ' || i < paso - ventana) {
                        sb.append(color).appendCodePoint(cps[i]);
                    } else {
                        sb.append(GRIS).append(ruido.charAt(azar.nextInt(ruido.length())));
                    }
                }
                sb.append(RESET);
                o().print(sb);
                o().flush();
                dormir(30);
            }
        } finally {
            o().println(RESET + "\u001B[?25h");  // vuelve a mostrar el cursor
        }
    }

    /**
     * Ola de colores: el arcoíris se desplaza por el texto durante X milisegundos.
     * Ideal para el título del juego (una sola línea).
     */
    public static void ola(String texto, int milisegundos) {
        if (!animar) {
            teclear(texto, pintorIris(), 0, true);
            return;
        }
        int[] cps = texto.codePoints().toArray();
        int cuadros = Math.max(1, milisegundos / 40);

        o().print("\u001B[?25l");
        try {
            for (int f = 0; f < cuadros; f++) {
                StringBuilder sb = new StringBuilder("\r");
                for (int i = 0; i < cps.length; i++) {
                    if (cps[i] == ' ') {
                        sb.append(' ');
                    } else {
                        sb.append(matiz(i * 14 + f * 18)).appendCodePoint(cps[i]);
                    }
                }
                sb.append(RESET);
                o().print(sb);
                o().flush();
                dormir(40);
            }
        } finally {
            o().println(RESET + "\u001B[?25h");
        }
    }

    /** Pausa dramática (se salta si animar = false). */
    public static void pausa(int ms) {
        dormir(ms);
    }

    /** Borra la pantalla y deja el cursor arriba. */
    public static void limpiar() {
        o().print("\u001B[2J\u001B[H");
        o().flush();
    }


    // =====================================================================
    //  MODO AUTOMÁTICO: reemplazo directo de System.out.println / print
    // =====================================================================

    public static void println(String texto) { imprimir(texto, true); }
    public static void println()             { o().println(); ultimaVacia = true; }
    public static void println(Object o)     { imprimir(String.valueOf(o), true); }
    public static void print(String texto)   { imprimir(texto, false); }
    public static void print(char c) {
        if (colorHabla != null && !Character.isWhitespace(c)) o().print(colorHabla + c + RESET);
        else o().print(c);
        o().flush();
    }
    public static void print(Object o)       { imprimir(String.valueOf(o), false); }

    private static final Pattern HABLA =
            Pattern.compile("^([\\p{L}\\p{N}?][\\p{L}\\p{N} ?]{0,15}):\\s*\\S.*$");
    private static final Pattern OPCION = Pattern.compile("^\\s*\\d+\\.\\s.*$");
    private static final Pattern LISTA  = Pattern.compile("^\\s*[\u2022\\-]\\s.*$");
    private static final String NARRA   = "\u001B[3m" + rgb(195, 180, 240);
    private static final String LISTA_C = rgb(150, 200, 255);

    private static void imprimir(String texto, boolean salto) {
        if (texto == null) texto = "null";
        boolean conColor = texto.indexOf('\u001B') >= 0;
        int visibles = visibles(texto);

        // ¿lo último que se imprimió fue una línea en blanco? (para separar bien los diálogos)
        boolean estabaVacia = ultimaVacia;
        ultimaVacia = salto && (visibles == 0 || texto.endsWith("\n"));

        // Cosas muy cortas o barras de carga (empiezan con \r): salen directo
        if (texto.startsWith("\r") || (conColor && visibles <= 8) || (!conColor && visibles <= 1)) {
            o().print(texto);
            if (salto) o().println();
            o().flush();
            return;
        }

        if (conColor) {
            Matcher mt = TRANSMISION.matcher(texto);
            if (mt.find()) {                            // cabecera de una caja: quién habla
                colorHabla = colorDe(mt.group(1).trim());
                o().print(texto);
                if (salto) o().println();
                o().flush();
                dormir(40);
                return;
            }
            if (texto.indexOf('\u2514') >= 0) colorHabla = null;   // cierre de la caja (└)
            if (cuentaArte(texto) >= 3) {               // cajas y logos: aparecen de golpe
                o().print(texto);
                if (salto) o().println();
                o().flush();
                dormir(40);
            } else {                                    // texto con sus colores: se anima tal cual
                teclear(texto, null, msPorLetra, salto);
            }
            return;
        }

        String[] lineas = texto.replace("\r", "").split("\n", -1);
        String heredado = null;                         // color que continúa en la línea siguiente
        for (int i = 0; i < lineas.length; i++) {
            String linea = lineas[i];
            String t = linea.trim();
            boolean nl = i < lineas.length - 1 || salto;

            if (t.isEmpty()) {
                heredado = null;
                if (nl) o().println();
                continue;
            }
            Matcher m = HABLA.matcher(linea);
            if (m.matches() && !t.startsWith("[") && !t.startsWith("FIN") && !t.startsWith("Fin de")) {
                String nombre = m.group(1);
                String color = colorDe(nombre);
                if (i == 0 && !estabaVacia) o().println();                 // línea en blanco antes de que hable alguien
                voz.hablar(nombre, linea.substring(m.end(1) + 1));         // voz del personaje
                o().print(NEGRITA + color + nombre + ":" + RESET);
                heredado = color;
                teclear(linea.substring(m.end(1) + 1), pintorSolido(color), msPorLetra, nl);
            } else if (t.startsWith("\u201C") || NARRA.equals(heredado)) {
                if (i == 0 && !estabaVacia) o().println();
                voz.hablar("NARRADOR", t);                                 // voz del narrador
                heredado = (t.endsWith("\u201D") || t.endsWith("\u201C")) ? null : NARRA;
                teclear(linea, pintorSolido(NARRA), msPorLetra, nl);
            } else if (t.contains("[ERROR]")) {
                teclear(linea, pintorSolido(ROJO), msPorLetra, nl);
            } else if (OPCION.matcher(linea).matches()) {
                teclear(linea, pintorSolido(VERDE), Math.max(1, msPorLetra / 2), nl);
            } else if (LISTA.matcher(linea).matches()) {
                teclear(linea, pintorSolido(LISTA_C), msPorLetra, nl);
            } else if (t.startsWith("<<")) {
                teclear(linea, pintorSolido(MAGENTA), msPorLetra, nl);
            } else if (t.startsWith("FIN") || t.startsWith("Fin de")) {
                teclear(linea, pintorIris(), msPorLetra, nl);
            } else if (!salto && i == lineas.length - 1 && (t.endsWith(":") || t.endsWith(">"))) {
                teclear(linea, pintorSolido(AMARILLO), msPorLetra, nl);   // pregunta que espera respuesta
            } else if (heredado != null) {
                teclear(linea, pintorSolido(heredado), msPorLetra, nl);   // sigue hablando el mismo
            } else {
                teclear(linea, pintorDegradado(NEON), msPorLetra, nl);
            }
        }
    }

    private static String colorDe(String nombre) {
        String n = nombre.trim().toUpperCase();
        if (n.startsWith("ARIS") || n.startsWith("ARRIS")) return VIOLETA;
        switch (n) {
            case "DARIAN": return NARANJA;
            case "AXEL":   return VERDE;
            case "NOVA":   return AZUL;
            case "ZYRA":   return ROSA;
            case "TAREK":  return AMARILLO;
            case "???":    return ROJO;
            default:       return CIAN;                 // el jugador
        }
    }

    private static int visibles(String s) {
        int total = 0;
        for (int k = 0; k < s.length(); ) {
            int cp = s.codePointAt(k);
            if (cp == 27) { k = finDeCodigo(s, k); continue; }
            total++;
            k += Character.charCount(cp);
        }
        return total;
    }

    /** Cuenta símbolos de cajas, bloques y flechas (para reconocer dibujos y menús). */
    private static int cuentaArte(String s) {
        int n = 0;
        for (int k = 0; k < s.length(); ) {
            int cp = s.codePointAt(k);
            if ((cp >= 0x2500 && cp <= 0x259F) || (cp >= 0x25E2 && cp <= 0x25E5)) n++;
            k += Character.charCount(cp);
        }
        return n;
    }

    /** La salida real de la consola (System.out queda interceptado por Estilo). */
    private static PrintStream real = System.out;
    private static String colorHabla = null;            // color del personaje que está hablando en una caja
    private static boolean ultimaVacia = true;          // ¿lo último impreso fue una línea en blanco?

    private static PrintStream o() { return real; }

    /** Intercepta System.out: todo lo que tu juego imprima pasa por Estilo. */
    private static class Interceptor extends PrintStream {
        Interceptor(PrintStream destino) throws java.io.UnsupportedEncodingException {
            super(destino, true, "UTF-8");
        }
        @Override public void print(String s)   { imprimir(s, false); }
        @Override public void println(String s) { imprimir(s, true); }
        @Override public void print(Object x)   { imprimir(String.valueOf(x), false); }
        @Override public void println(Object x) { imprimir(String.valueOf(x), true); }
        @Override public void println()         { real.println(); real.flush(); ultimaVacia = true; }
        @Override public void print(char c)     { Estilo.print(c); }
    }

    private static final Pattern TRANSMISION =
            Pattern.compile("TRANS\\p{L}*(?: DE)?:\\s*(?:\u001B\\[[0-9;]*m)*([^\u001B]+)");

    // =====================================================================
    //  INTERNO
    // =====================================================================

    /** Decide el color de cada letra: i = posición de la letra, total = cuántas hay. */
    private interface Pintor {
        String color(int i, int total);
    }

    private static Pintor pintorSolido(final String color) {
        return (i, total) -> color;
    }

    private static Pintor pintorDegradado(final int[][] g) {
        return (i, total) -> mezcla(g[0], g[1], total <= 1 ? 0 : (double) i / (total - 1));
    }

    private static Pintor pintorIris() {
        return (i, total) -> matiz(total <= 1 ? 0 : 300.0 * i / (total - 1));
    }

    /** El motor del efecto máquina de escribir. */
    private static void teclear(String texto, Pintor pintor, int ms, boolean salto) {
        if (texto.indexOf('\u001B') >= 0) {
            pintor = null;                              // el texto ya trae colores: se respetan
        }
        int total = texto.codePointCount(0, texto.length());
        int n = texto.length();
        int i = 0;
        String ultimo = "";

        for (int k = 0; k < n; ) {
            int cp = texto.codePointAt(k);

            if (cp == 27) {                             // código de color incluido en el texto
                int fin = finDeCodigo(texto, k);
                o().print(texto.substring(k, fin));
                k = fin;
                continue;
            }

            int largo = Character.charCount(cp);
            int siguiente = (k + largo < n) ? texto.codePointAt(k + largo) : -1;

            if (pintor != null && !Character.isWhitespace(cp)) {
                String c = pintor.color(i, total);
                if (!c.equals(ultimo)) {
                    o().print(c);
                    ultimo = c;
                }
            }
            o().print(texto.substring(k, k + largo));
            o().flush();

            i++;
            k += largo;
            dormir(retardo(cp, siguiente, ms));
        }

        o().print(RESET);
        if (salto) {
            o().println();
        }
        o().flush();
    }

    /** Pausas naturales: más largas en los puntos y las comas, como si alguien escribiera. */
    private static int retardo(int cp, int siguiente, int ms) {
        boolean finDePalabra = siguiente == -1 || Character.isWhitespace(siguiente);
        if (cp == '\n') return ms * 10;
        if (cp == ' ') return ms / 2;
        if (finDePalabra && (cp == '.' || cp == '!' || cp == '?' || cp == ':' || cp == '\u2026')) return ms * 9;
        if (finDePalabra && (cp == ',' || cp == ';')) return ms * 5;
        return ms;
    }

    /** Dónde termina un código ANSI que empieza en la posición k (ESC [ ... letra). */
    private static int finDeCodigo(String s, int k) {
        int j = k + 1;
        if (j < s.length() && s.charAt(j) == '[') {
            j++;
            while (j < s.length() && !(s.charAt(j) >= '@' && s.charAt(j) <= '~')) {
                j++;
            }
        }
        return Math.min(j + 1, s.length());
    }

    private static String mezcla(int[] a, int[] b, double t) {
        t = Math.max(0, Math.min(1, t));
        return rgb((int) Math.round(a[0] + (b[0] - a[0]) * t),
                   (int) Math.round(a[1] + (b[1] - a[1]) * t),
                   (int) Math.round(a[2] + (b[2] - a[2]) * t));
    }

    /** Color del arcoíris según el ángulo (0 a 360 grados). */
    private static String matiz(double grados) {
        double h = (((grados % 360) + 360) % 360) / 60.0;
        int x = (int) (255 * (1 - Math.abs(h % 2 - 1)));
        int r, g, b;
        switch ((int) h) {
            case 0:  r = 255; g = x;   b = 0;   break;
            case 1:  r = x;   g = 255; b = 0;   break;
            case 2:  r = 0;   g = 255; b = x;   break;
            case 3:  r = 0;   g = x;   b = 255; break;
            case 4:  r = x;   g = 0;   b = 255; break;
            default: r = 255; g = 0;   b = x;   break;
        }
        return rgb(r, g, b);
    }

    private static void dormir(long ms) {
        if (!animar || ms <= 0) return;
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}