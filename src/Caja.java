import java.util.ArrayList;
import java.util.List;

/** Caja de transmisión: el texto se parte en líneas y siempre queda dentro del marco. */
public class Caja {

    private static final int ANCHO = 58;   // ancho interior de la caja

    public static void decir(String personaje, String mensaje) {
        String c = Estilo.CIAN, y = Estilo.AMARILLO, r = Estilo.RESET;
        String nombre = personaje.toUpperCase();
        String color = nombre.startsWith("ARIS") ? Estilo.VIOLETA : Estilo.CIAN;

        // salta a la columna del borde derecho, así el marco siempre queda alineado
        String borde = "\u001B[" + (ANCHO + 2) + "G" + c + "│" + r;

        System.out.println(c + "┌" + repetir('─', ANCHO) + "┐" + r);
        System.out.println(c + "│ " + y + "[ •ᴗ• ] " + c + "TRANSMISIÓN: " + color + nombre + borde);
        System.out.println(c + "├" + repetir('─', ANCHO) + "┤" + r);

        for (String linea : partir(mensaje, ANCHO - 2)) {
            System.out.print(c + "│ " + r);
            Estilo.prompt(linea, color);       // texto letra por letra, con el color del personaje
            System.out.println(borde);
        }

        System.out.println(c + "└" + repetir('─', ANCHO) + "┘" + r + "\n");
    }

    private static List<String> partir(String texto, int max) {
        List<String> lineas = new ArrayList<String>();
        StringBuilder linea = new StringBuilder();
        for (String palabra : texto.replace("\r", " ").replace("\n", " ").trim().split("\\s+")) {
            if (linea.length() > 0 && linea.length() + 1 + palabra.length() > max) {
                lineas.add(linea.toString());
                linea.setLength(0);
            }
            if (linea.length() > 0) linea.append(' ');
            linea.append(palabra);
        }
        if (linea.length() > 0) lineas.add(linea.toString());
        return lineas;
    }

    private static String repetir(char c, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(c);
        return sb.toString();
    }
}