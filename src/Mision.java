/**
 * Fase de reparación: después del desastre el jugador elige entre buscar las piezas
 * (explorar la nave) o codificar a ARIS 9 (responder preguntas), hasta completar
 * las 3 placas estructurales y la codificación.
 *
 * Si quieres que baste con hacer UNA de las dos cosas, cambia || por && en el while.
 */
public class Mision {

    private static final Teclado sc = new Teclado();

    public static void reparar(String player) {
        nave mapa = new nave();
        personajes ia = new personajes();
        boolean[] revisada = new boolean[6];     // cámaras 1 a 5
        int placas = 0;
        boolean codificado = false;

        ia.decirPersonaje("ARIS 9", "Para reparar el motor se necesitan 3 placas estructurales y una recodificación nanotecnológica.");

        while (placas < 3 || !codificado) {
            Asistente.ayuda("Debes reunir 3 placas estructurales y recodificar a ARIS 9 (mínimo 4 de 6 respuestas correctas). "
                    + "Llevas " + placas + " de 3 placas y la codificación está " + (codificado ? "completa" : "pendiente") + ".");
            System.out.println("1. Buscar las piezas (explorar la nave)");
            System.out.println("2. Codificar a ARIS 9 (responder preguntas)");

            if (leer(1, 2) == 1) {
                if (placas >= 3) {
                    System.out.println("\n[INFO] Ya tienes las 3 placas.\n");
                } else {
                    placas += explorar(player, mapa, revisada);
                }
            } else {
                if (codificado) {
                    System.out.println("\n[INFO] La codificación ya está completa.\n");
                } else {
                    codificado = ia.codificar();
                    if (!codificado) {
                        ia.decirPersonaje("ARIS 9", "Recodificación fallida. Necesitas al menos 4 respuestas correctas. Inténtalo de nuevo.");
                    }
                }
            }
        }
        ia.decirPersonaje("ARIS 9", "Tareas completadas: 3 placas estructurales y recodificación del motor.");
    }

    /** Devuelve 1 si en esa cámara se encontró una placa, 0 si no. */
    private static int explorar(String player, nave mapa, boolean[] revisada) {
        Asistente.ayuda("Elige qué cámara revisar. No todas tienen piezas y cada cámara solo se puede revisar una vez.");
        System.out.println("1. Bóveda");
        System.out.println("2. Cámara de hibernación");
        System.out.println("3. Almacén");
        System.out.println("4. Laboratorio");
        System.out.println("5. Exterior");

        int sala = leer(1, 5);
        if (revisada[sala]) {
            System.out.println("\n[INFO] Ya revisaste esa cámara, elige otra tarea.\n");
            return 0;
        }
        revisada[sala] = true;

        boolean placa;
        switch (sala) {
            case 1:  placa = mapa.bobeda(player);       break;
            case 2:  placa = mapa.hibernacion(player);  break;
            case 3:  placa = mapa.almacen(player);      break;
            case 4:  placa = mapa.laboratorio(player);  break;
            default: placa = mapa.exterior(player);     break;
        }
        return placa ? 1 : 0;
    }

    private static int leer(int min, int max) {
        while (true) {
            System.out.print("Tu elección: ");
            int n = sc.nextInt();
            if (n >= min && n <= max) return n;
            System.out.println("\n[ERROR] Opción no válida, elige un número del " + min + " al " + max + ".\n");
        }
    }
}