public class nave {
    // Teclado = Scanner con aviso de "Presiona ENTER para continuar"
    Teclado sc = new Teclado();

    // Cada cámara devuelve true si el jugador encontró una placa estructural.

    public boolean bobeda(String player) {
        System.out.println(player + ": Aqui almacenamos todo lo necesario para la investigación, y ahora solo quedan... Cenizas \r\n"
                + "Pero si no quiero, morir aqui me debo de apresurar. ¿Donde estara esa pieza?.");
        System.out.println("");
        sc.nextLine();

        for(int i = 0; i < 3; i++) {
            System.out.print("buscando...... ");
        }
        System.out.println("");
        System.out.println("!La encontre¡,perfecto");
        System.out.println("");
        System.out.println("Darian: " + player + " Te hablo desde el comunicador del traje ¿Has encontrado alguna placa estructural?");
        System.out.println("");
        System.out.println("Si acabo de encontrar una.");
        System.out.println("");
        System.out.println("Perfecto " + player + " sigue asi.");
        return true;
    }

    public boolean hibernacion(String player) {
        System.out.println(player + ": Aqui deberiamos de estar todos y no solo Darian y io, ¿Qué hubiera pasado si?...\r\n"
                + "ya no importa lo que hubiera pasado, no puedo cambiar nada.\r\n"
                + "Ahora me debo de concentrar en hayar esa pieza.");
        sc.nextLine();

        for(int i = 0; i < 3; i++) {
            System.out.print("buscando...... ");
        }
        System.out.println("");
        System.out.println("No, no hay ni una pieza aqui.... Debere de seguir buscando.");
        System.out.println("");
        System.out.println("Darian: " + player + " Te hablo desde el comunicador del traje ¿Has encontrado alguna placa estructural?");
        System.out.println("");
        System.out.println("No en la camara de hibernación no hay nada.");
        System.out.println("");
        System.out.println("Sigue buscando " + player + " debemos de salir de aqui.");
        System.out.println("Si lo se Darian.");
        return false;
    }

    public boolean almacen(String player) {
        System.out.println(player + ": Aqui estaba toda nuestra reserva de comida, por si Aetherion 3 no tennia fuente de comida\r\n"
                + "Pero ahora ya no queda casi nada.... Bueno a lo que vine, a buscar esa pieza.");
        sc.nextLine();

        for(int i = 0; i < 3; i++) {
            System.out.print("buscando...... ");
        }
        System.out.println("");
        System.out.println("!La encontre¡,perfecto");
        System.out.println("");
        System.out.println("Darian: " + player + " Te hablo desde el comunicador del traje ¿Has encontrado alguna placa estructural?");
        System.out.println("");
        System.out.println("Si acabo de encontrar una");
        System.out.println("");
        System.out.println("Perfecto " + player + " sigue asi.");
        return true;
    }

    public boolean laboratorio(String player) {
        System.out.println(player + ":Aqui Nova, Zyra, Axel y Tarek estarian haciendo sus cosas....\r\n"
                + "Aunque no se que estarian haciendo, y que todo este inservible por la acción de ARIS es...\r\n"
                + "Decepcionate, debo de concentrarme en buscar esa pieza.");
        sc.nextLine();

        for(int i = 0; i < 3; i++) {
            System.out.print("buscando...... ");
        }
        System.out.println("");
        System.out.println("!La encontre¡,perfecto");
        System.out.println("");
        System.out.println("Darian: " + player + " Te hablo desde el comunicador del traje ¿Has encontrado alguna placa estructural?");
        System.out.println("");
        System.out.println("Si acabo de encontrar una");
        System.out.println("");
        System.out.println("Perfecto " + player + " sigue asi.");
        return true;
    }

    public boolean exterior(String player){
        byte eleccion;
        boolean opcionValida = false; // Variable para controlar el bucle

        System.out.println(player + ":¿Enserio deberia de estar buscando esa pieza en el exterior?");
        sc.nextLine();
        System.out.println("Darian: Es enserio genio de los codigos, buscando una pieza pequeña comparado a lo que nos rodea\r\n"
                + "¿Estas demente o el espacio ya te volvio loco?");
        sc.nextLine();

        Asistente.ayuda("Darian se burla de buscar una pieza en el exterior. Elige cómo responderle; en todos los casos te mandará de vuelta a las cámaras.");

        // Iniciamos el do-while para obligar a dar una respuesta correcta (1, 2 o 3)
        do {
            System.out.println("“Elegir que responder a Darian digitando ”\r\n"
                    + "1. Ni yo se exactamente, pense que una pieza de esas pudo haber salido de las camaras.\r\n"
                    + "2. Tu no me dijiste donde buscar exactamente Darian. \r\n"
                    + "3. Darian ¿Como me hiciste una llamada sin que te aceptara?");
            System.out.print("Tu elección: ");

            eleccion = sc.nextByte();
            sc.nextLine(); // limpieza (Teclado ya la maneja sola)

            switch (eleccion) {
                case 1:
                    System.out.println(player + ": Ni yo se exactamente, pense que una pieza de esas pudo haber salido de las camaras.");
                    System.out.println("Darian: Si ese hubiera sido el caso ARRIS no nos hubiera dicho lo de las piezas, por favor " + player + "\r\n"
                            + "Ahora ve a las camaras y busca las placas estructurales o las que te hagan falta.");
                    sc.nextLine();
                    System.out.println("Ok ok esta bien ya me apuro.");
                    opcionValida = true; // Opción válida, saldrá del bucle
                    break;

                case 2:
                    System.out.println(player + ":Tu no me dijiste donde buscar exactamente Darian");
                    System.out.println("");
                    System.out.println("Darian: Si, pero tampoco no te dije que buscaras en el vacio.." + player + "\r\n"
                            + "Ahora ve a las camaras y busca las placas estructurales o las que te hagan falta.");
                    sc.nextLine();
                    System.out.println("Ok ok esta bien ya me apuro.");
                    opcionValida = true; // Opción válida
                    break;

                case 3:
                    System.out.println(player + ":Darian ¿Como me hiciste una llamada sin que te aceptara?");
                    System.out.println("Darian: ¿No te acuerdas que los trajes estan programados para hacer eso si preguntarte?");
                    System.out.println(player + ": La verdad que no.");
                    System.out.println("Darian: Ya veo que no. Ahora ve a las camaras y busca las placas estructurales o las que te hagan falta.");
                    sc.nextLine();
                    System.out.println("Ok ok esta bien ya me apuro.");
                    opcionValida = true; // Opción válida
                    break;

                default:
                    System.out.println("\n[ERROR] Opción no válida, por favor elige 1, 2 o 3.\n");
                    break;
            }
        } while (!opcionValida);

        return false;   // en el exterior no hay placa
    }
}