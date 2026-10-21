import java.util.Scanner;

public class personajes {

    // Códigos de colores ANSI para darle estética de videojuego cyberpunk/RPG
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String VERDE = "\u001B[32m";
    public static final String MORADO = "\u001B[35m";

    public void decirPersonaje(String personaje, String mensaje) {
        Caja.decir(personaje, mensaje);
    }

    public void decirPersonajeViejo(String personaje, String mensaje) {
        System.out.println(CYAN + "┌────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(CYAN + "│ " + AMARILLO + "[ •ᴗ• ] " + CYAN + "TRANSIMISIÓN DE: " + MORADO + personaje.toUpperCase() + CYAN + "         │" + RESET);
        System.out.println(CYAN + "├────────────────────────────────────────────────────────┤" + RESET);
        System.out.print(CYAN + "│ " + RESET);

        // Efecto de escritura letra por letra
        for (char c : mensaje.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(15); // Velocidad del texto en milisegundos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\n" + CYAN + "└────────────────────────────────────────────────────────┘\n" + RESET);
    }

    // (Ya no se usa: ahora el menú de buscar piezas / codificar está en la clase Mision)
    public void moverse() {
        Scanner sc = new Scanner(System.in);
        byte eleccion, destino;
        boolean opcionValida = false; // Control de bucle para el menú principal

        // --- MENÚ PRINCIPAL DE ACCIÓN ---
        do {
            System.out.println("¿Qué tarea deseas hacer primero?\r\n"
                    + "1. Explorar la nave. \r\n"
                    + "2. Codificar a ARIS 9.");
            System.out.print("Tu elección: ");
            eleccion = sc.nextByte();
            sc.nextLine(); // Limpieza de buffer

            switch (eleccion) {
                case 1:
                    boolean destinoValido = false; // Control de bucle para las cámaras

                    // --- SUB-MENÚ: SELECCIÓN DE CÁMARA ---
                    do {
                        System.out.println("¿A qué camara de la Novaeklis quieres investigar?");
                        System.out.println("Digite el numero de la camara que quieres investigar.");
                        System.out.println("1. Bobeda \r\n"
                                + "2. Camara de hibernación\r\n"
                                + "3. Almacen \r\n"
                                + "4. Laboratorio \r\n"
                                + "5. Exterior ");
                        System.out.print("Cámara elegida: ");
                        destino = sc.nextByte();
                        sc.nextLine(); // Limpieza de buffer

                        if (destino >= 1 && destino <= 5) {
                            destinoValido = true; // El número de cámara está entre 1 y 5
                        } else {
                            System.out.println("\n[ERROR] Cámara no válida, elija un número del 1 al 5.\n");
                        }
                    } while (!destinoValido);

                    opcionValida = true; // Termina el menú principal con éxito
                    break;

                case 2:
                    // Aquí integramos al personaje animado dando las instrucciones del sistema
                    decirPersonaje("ARIS 9", "Prepárate para la codificación. El estilo de codificación es distinto al que estás acostumbrado; deberás recordar casi todo lo visto en el periodo.");
                    opcionValida = true;
                    break;

                default:
                    System.out.println("\n[ERROR] Opción no válida, por favor elige 1 o 2.\n");
                    break;
            }
        } while (!opcionValida);
    }

    /** Minijuego de codificación: devuelve true si acierta al menos 4 de 6 preguntas. */
    public boolean codificar(){
        Teclado sc = new Teclado();
        byte respuesta;
        boolean preguntaValida; // Variable para controlar cada pregunta de la trivia
        int aciertos = 0;

        // Presentación inicial del minijuego con el personaje
        decirPersonaje("SISTEMA DE PRUEBAS", "Iniciando protocolo de evaluación de programación. Responde correctamente para reconfigurar la IA.");

        // --- PREGUNTA A ---
        preguntaValida = false;
        do {
            System.out.println("A. ¿Como se llama el bucle que repite un codigo una cantidad de veces ya definidas? \r\n"
                    + "1. while\r\n"
                    + "2. for\r\n"   // Respuesta correcta
                    + "3. do while \r\n");
            System.out.print("Tu respuesta: ");
            respuesta = sc.nextByte();
            sc.nextLine();

            switch (respuesta) {
                case 1:
                case 3:
                    System.out.println("Respuesta incorrecta.\n");
                    preguntaValida = true;
                    break;
                case 2:
                    System.out.println("Respuesta correcta.\n"); aciertos++;
                    preguntaValida = true;
                    break;
                default:
                    System.out.println("\n[ERROR] Opción no válida, digite 1, 2 o 3.\n");
                    break;
            }
        } while (!preguntaValida);

        // --- PREGUNTA B ---
        preguntaValida = false;
        do {
            System.out.println("B. ¿Cual es el codigo de condicionales que se evalua, despues que el if no sea valido?\r\n"
                    + "1. else \r\n"
                    + "2. if else \r\n"
                    + "3. else if \r\n");
            System.out.print("Tu respuesta: ");
            respuesta = sc.nextByte();
            sc.nextLine();

            switch (respuesta) {
                case 1:
                case 2:
                    System.out.println("Respuesta incorrecta.\n");
                    preguntaValida = true;
                    break;
                case 3:
                    System.out.println("Respuesta correcta.\n"); aciertos++;
                    preguntaValida = true;
                    break;
                default:
                    System.out.println("\n[ERROR] Opción no válida, digite 1, 2 o 3.\n");
                    break;
            }
        } while (!preguntaValida);

        // --- PREGUNTA C ---
        preguntaValida = false;
        do {
            System.out.println("C. ¿Qué linea de codigo imprime un mensaje de forma válida en Java? \r\n"
                    + "1. System.out.println(); \r\n"
                    + "2. switch () case 1: \r\n"
                    + "3. System.out.println(\" \");\r\n");
            System.out.print("Tu respuesta: ");
            respuesta = sc.nextByte();
            sc.nextLine();

            switch (respuesta) {
                case 1:
                    System.out.println("Respuesta correcta.\n"); aciertos++;
                    preguntaValida = true;
                    break;
                case 2:
                case 3:
                    System.out.println("Respuesta incorrecta.\n");
                    preguntaValida = true;
                    break;
                default:
                    System.out.println("\n[ERROR] Opción no válida.\n");
                    break;
            }
        } while (!preguntaValida);

        // --- PREGUNTA D ---
        preguntaValida = false;
        do {
            System.out.println("D. ¿Qué codigo evita multiples if, else if y else? \r\n"
                    + "1. switch \r\n"
                    + "2. do while \r\n"
                    + "3. for \r\n");
            System.out.print("Tu respuesta: ");
            respuesta = sc.nextByte();
            sc.nextLine();

            switch (respuesta) {
                case 1:
                    System.out.println("Respuesta correcta.\n"); aciertos++;
                    preguntaValida = true;
                    break;
                case 2:
                case 3:
                    System.out.println("Respuesta incorrecta.\n");
                    preguntaValida = true;
                    break;
                default:
                    System.out.println("\n[ERROR] Opción no válida.\n");
                    break;
            }
        } while (!preguntaValida);

        // --- PREGUNTA E ---
        preguntaValida = false;
        do {
            System.out.println("E. ¿Qué es POO? \r\n"
                    + "1. Programación Orientada a Opciones. \r\n"
                    + "2. Programación Obsticulada por Orientaciones. \r\n"
                    + "3. Programación Orientada a Objetos.\r\n");
            System.out.print("Tu respuesta: ");
            respuesta = sc.nextByte();
            sc.nextLine();

            switch (respuesta) {
                case 1:
                case 2:
                    System.out.println("Respuesta incorrecta.\n");
                    preguntaValida = true;
                    break;
                case 3:
                    System.out.println("Respuesta correcta.\n"); aciertos++;
                    preguntaValida = true;
                    break;
                default:
                    System.out.println("\n[ERROR] Opción no válida.\n");
                    break;
            }
        } while (!preguntaValida);

        // --- PREGUNTA F ---
        preguntaValida = false;
        do {
            System.out.println("F. ¿Con qué se planea una aplicación con la POO? \r\n"
                    + "1. Con los diagramas de clases. \r\n"
                    + "2. Estructura de codigo. \r\n"
                    + "3. Tener todos los metodos y codigo en el main.\r\n");
            System.out.print("Tu respuesta: ");
            respuesta = sc.nextByte();
            sc.nextLine();

            switch (respuesta) {
                case 1:
                    System.out.println("Respuesta correcta.\n"); aciertos++;
                    preguntaValida = true;
                    break;
                case 2:
                case 3:
                    System.out.println("Respuesta incorrecta.\n");
                    preguntaValida = true;
                    break;
                default:
                    System.out.println("\n[ERROR] Opción no válida.\n");
                    break;
            }
        } while (!preguntaValida);

        System.out.println("Aciertos: " + aciertos + " de 6");
        return aciertos >= 4;
    }
}