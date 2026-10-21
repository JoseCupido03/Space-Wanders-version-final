/**
 * Transiciones entre pantallas: una cortina de colores que cae (cian -> magenta),
 * el título del módulo que se "descifra" y una barra de carga.
 *
 *   Transicion.entrar("CONTROLES");   // al abrir una pantalla
 *   Transicion.salir();               // al volver al menú principal
 */
public class Transicion {

    private static final int FILAS = 24;
    private static final String[] PATRON = { "█", "▓", "▒", "░" };

    /** Cortina de bloques con degradado de color que cae por la pantalla. */
    public static void cortina() {
        int ancho = Pantalla.ANCHO + 2;
        for (int f = 0; f < FILAS; f++) {
            double t = f / (double) (FILAS - 1);
            String color = Estilo.rgb((int) (255 * t), (int) (240 - 190 * t), (int) (255 - 35 * t));
            String bloque = PATRON[f % PATRON.length];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ancho; i++) sb.append(bloque);
            System.out.println(color + sb + Estilo.RESET);
        }
        main.esperar(250);
    }

    /** Entrada a un módulo: cortina, título descifrándose y barra de carga. */
    public static void entrar(String modulo) {
        cortina();
        main.limpiarPantalla();
        System.out.println();
        Estilo.descifrar("◢◤  ACCEDIENDO AL MÓDULO: " + modulo + "  ◥◣", Estilo.CIAN);
        main.ejecutarCargaCuantica("CARGANDO " + modulo, 100);
        main.limpiarPantalla();
    }

    /** Regreso al menú principal: cortina y aviso de retorno. */
    public static void salir() {
        cortina();
        main.limpiarPantalla();
        System.out.println();
        Estilo.descifrar("◢◤  REGRESANDO AL MENÚ PRINCIPAL  ◥◣", Estilo.MAGENTA);
        main.esperar(400);
    }
}