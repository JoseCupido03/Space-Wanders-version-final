import java.util.Scanner;

/**
 * Teclado: funciona como el Scanner, pero cada vez que el juego espera un ENTER
 * muestra "Presiona ENTER para continuar". Además, nextInt() y nextByte()
 * ya no se caen si el jugador escribe una letra en lugar de un número.
 */
public class Teclado {

    private final Scanner in = new Scanner(System.in);
    private boolean limpiezaPendiente = false;   // el nextLine() que sigue a un nextInt() no es una pausa

    public int nextInt() {
        while (true) {
            try {
                int n = Integer.parseInt(in.nextLine().trim());
                limpiezaPendiente = true;
                voz.callar();                     // al elegir, se corta la voz anterior
                return n;
            } catch (NumberFormatException e) {
                System.out.println("\n[ERROR] Escribe solo el número de tu opción.\n");
                System.out.print("Tu elección: ");
            }
        }
    }

    public byte nextByte() {
        int n = nextInt();
        return (byte) Math.max(Byte.MIN_VALUE, Math.min(Byte.MAX_VALUE, n));
    }

    public String nextLine() {
        if (limpiezaPendiente) {                  // es la "limpieza" después de nextInt/nextByte
            limpiezaPendiente = false;
            return "";
        }
        Estilo.avisoEnter();                      // muestra "Presiona ENTER para continuar"
        String linea = in.nextLine();
        Estilo.borrarAviso();                     // lo borra y corta la voz
        return linea;
    }
}