import java.util.Scanner;

public class juego {

    Scanner sc = new Scanner(System.in);
    private String player;
public static void decir(String personaje, String mensaje) {
    Caja.decir(personaje, mensaje);
}

public static void decirViejo(String personaje, String mensaje) {
        String CYAN = "\u001B[36m";
        String AMARILLO = "\u001B[33m";
        String MORADO = "\u001B[35m";
        String RESET = "\u001B[0m";

        System.out.println(CYAN + "┌────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(CYAN + "│ " + AMARILLO + "[ •ᴗ• ] " + CYAN + "TRANSMISIÓN: " + MORADO + personaje.toUpperCase() + CYAN + "         │" + RESET);
        System.out.println(CYAN + "├────────────────────────────────────────────────────────┤" + RESET);
        System.out.print(CYAN + "│ " + RESET);
        
        for (char c : mensaje.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\n" + CYAN + "└────────────────────────────────────────────────────────┘\n" + RESET);
    }

    public void jugador() {
        String eleccion;
        boolean opcionValida = false; 
        
        do {
            System.out.println("[Digite si quieres elegir nombres preestablecidos o si quieres un nombre personalizado]");
            System.out.println("1. preestablecidos");
            System.out.println("2. personalizado");
            System.out.print("Tu elección: ");
            eleccion = sc.nextLine();
            
        
        
            
            if (eleccion.equalsIgnoreCase("preestablecidos") || eleccion.equals("1")) {
                System.out.println("Si deseas nombres masculinos. Digite el nombre del personaje que quieres");
                System.out.println("1. Kael.");
                System.out.println("2. Draven.");
                System.out.println("Si deseas nombres femeninos. Digite el nombre del personaje que quieres");
                System.out.println("3. Elys.");
                System.out.println("4. Lyra.");
                System.out.print("Nombre del personaje: ");
                player = sc.nextLine();
                opcionValida = true;
            }
            else if (eleccion.equalsIgnoreCase("personalizado") || eleccion.equals("2")) {
                System.out.println("Escribe el nombre que quieres para tu personaje");
                System.out.print("Nombre: ");
                player = sc.nextLine();
                opcionValida = true;
            }
            else {
                System.out.println("\n[ERROR] Opcion no valida, por favor elige una valida.\n");
            }
        } while (!opcionValida);
    }
public String getPlayer() {
    if (player.equals("1")) return "Kael";
    if (player.equals("2")) return "Draven";
    if (player.equals("3")) return "Elys";
    if (player.equals("4")) return "Lyra";
    return player;
}

public String getPlayerViejo() {
    return player;
    }

    // Historia del juego con los diálogos integrados al personaje animado
    public static void mostrarhistoria(String player) {
        Scanner sc = new Scanner(System.in);
        int eleccion, eleccion2, eleccion3, eleccion4, eleccion5;
        boolean opcionValida;
        
        int actitudConAxel = 0; 

        System.out.println(player + ":" + " Aqui " + player + " en la bitácora 1.0. El despegue de la nave Novaeklis 3 ha sido un éxito.\r\n"
                + "ARIS 9, nuestra IA de confianza, es la que maneja la nave. En cuanto al equipo, somos un equipo de \r\n"
                + "seis personas para " + "explorar el exoplaneta Aetherion 3, que se encuentra en el sistema Heliox 505");
        sc.nextLine();
        System.out.println("Aunque el viaje es de 80 años luz hasta Aetherion 3, la nave puede viajar al 99 % de la velocidad \r\n"
                + "de la luz, según los cálculos de Nova, nuestra física principal.");
        System.out.println(" Espero que este mensaje llegue al Gobierno Mundial y…");
        sc.nextLine();
        
        // Aquí aparece ARIS 9 dando las instrucciones con el personaje animado
        decir("ARIS 9", "Atención a todos los pasajeros de Nova Sapientia 3. Se les solicita que entren a sus cápsulas de hibernación de manera inmediata. Iniciando protocolo de aceleración al 99 % de la velocidad de la luz.");
        sc.nextLine();
        
        System.out.println(player + ":" + " Esa es nuestra señal. Aetherion 3, prepárate… la raza humana va a por ti. Fin de la bitácora.");
        System.out.println("");
        System.out.println("Axel: ¿No crees que te excediste en tu dramatismo en la bitácora, " + player + "?");
        
        // --- DECISIÓN 1 ---
        opcionValida = false;
        do {
            System.out.println("Elige cómo responderle a Axel:");
            System.out.println("1. [Serio] No lo creo, nos vemos dentro de unos momentos.");
            System.out.println("2. [Bromista] Mejor piensa en qué plantas debemos plantar en Aetherion 3.");
            System.out.println("3. [Inseguro] Solo... intento calmar los nervios antes de dormir por 80 años.");
            System.out.print("Tu elección: ");
            eleccion = sc.nextInt();
            sc.nextLine();
            
            switch(eleccion) {
                case 1:
                    System.out.println(player + ": No lo creo, nos vemos dentro de unos momentos.");
                    System.out.println("Axel: Siempre tan estricto. Nos vemos del otro lado.");
                    actitudConAxel = 1;
                    opcionValida = true;
                    break;
                case 2:
                    System.out.println(player + ": Mejor piensa en qué plantas debemos plantar en Aetherion 3.");
                    System.out.println("Axel: Jajaja, cuenta con eso. Buscaré semillas de café espacial en mis sueños.");
                    actitudConAxel = 2;
                    opcionValida = true;
                    break;
                case 3:
                    System.out.println(player + ": Solo... intento calmar los nervios antes de dormir por 80 años.");
                    System.out.println("Axel: Hey, tranquilo. Todo saldrá bien. ARIS 9 nunca nos ha fallado.");
                    actitudConAxel = 3;
                    opcionValida = true;
                    break;
                default:
                    System.out.println("\n[ERROR] Opción no válida, por favor elija de nuevo.\n");
                    break;
            }
        } while (!opcionValida);

        System.out.println("\n“Todos los tripulantes de la nave entraron a sus cápsulas de hibernación y el viaje comenzó con normalidad…\r\n"
                + "hasta que, en algún momento del trayecto…”\r\n");
        sc.nextLine();
        
        // Aviso de emergencia con ARIS 9 animado
        decir("ARIS 9", "¡Alerta alerta! Anomalía detectada en los escudos externos. Activando protocolo de despertar inmediato. Calentamiento corporal. Estimulación cardíaca y neurológica. Re-activación muscular. Estímulo sensorial.");
        sc.nextLine();
        
        System.out.println("Darian: ARIS 9, dinos qué está pasando. ¡Ahhh… qué mareos tengo! Y tú, Tarek, ¿por qué me siento así?");
        System.out.println("");
        System.out.println("Tarek: Es por la forma en que nos despertaron, fue demasiado brusca. ARIS 9, inyéctanos NeuroBalance X9. ¡Ahora!");
        sc.nextLine();
        
        decir("ARIS 9", "Prioridad uno, la nave está sufriendo daños críticos en los escudos. Revisión urgente requerida por Darian. Probabilidad de fallo estructural del 37 % y aumentando. Prioridad dos: preparando inyecciones de NeuroBalance X9. Tiempo estimado para aplicación: dos minutos.");
        sc.nextLine();
        
        System.out.println("Darian: ¡No tenemos tanto tiempo! ¿No oíste, Tarek?\r\n");
        System.out.println("");
        System.out.println("Nova: No seas impulsivo, Darian. Es mejor esperar los dos minutos antes de revisar los escudos.");
        System.out.println("");
        System.out.println("Darian: ARIS me mencionó a mí en el sistema de respaldo manual, no a ti. ¿Entendiste, Nova?");
        sc.nextLine();
        
        // --- DECISIÓN 2 ---
        opcionValida = false;
        do {
            System.out.println("El ambiente está tenso. ¿Qué harás?");
            System.out.println("1. Apoyar a Nova (Priorizar la seguridad médica)");
            System.out.println("2. Apoyar a Darian (Priorizar la reparación inmediata)");
            System.out.print("Tu elección: ");
            eleccion2 = sc.nextInt();
            sc.nextLine();
            
            switch(eleccion2) {
                case 1:
                    System.out.println(player + ": Debes tranquilizarte, Darian. Escucha lo que te dicen Nova y Tarek. Un error por mareo empeorará todo.");    
                    System.out.println("Nova: Gracias, " + player + ". Al menos alguien mantiene la cabeza fría.");
                    opcionValida = true;
                    break;
                case 2:
                    System.out.println(player + ": No hay tiempo para esperar los dos minutos. Darian, ve a revisar los escudos. Te acompaño.");
                    System.out.println("Darian: ¡Así se habla! Al menos tú entiendes la gravedad de la estructura.");
                    opcionValida = true;
                    break;
                default:
                    System.out.println("\n[ERROR] Opción no válida, por favor elija de nuevo.\n");
                    break;
            }
        } while (!opcionValida);

        if (eleccion2 == 1) {
            System.out.println("\n“Darian no escucha a nadie, golpea el panel y se dirige a la sala de control corriendo.”");
            sc.nextLine();
            decir("ARIS 9", "Inyecciones listas. Aplicación recomendada antes de manipular sistemas críticos.");
            System.out.println("");
            System.out.println("Zyra: " + player + ", ve por Darian y dale su inyección antes de que, por el mareo, cometa un error catastrófico.");
            sc.nextLine();
            System.out.println("“Te inyectas el NeuroBalance X9 sintiendo una descarga fría de energía y corres hacia la sala de control.”");
            System.out.println(player + ": ¡Darian, inyéctate el NeuroBalance X9 ahora!");
            sc.nextLine();
            
            decir("ARIS 9", "Escudos destruidos. Activando protocolo de seguridad en la sala de control. Desactivando energía en las demás partes de la nave. Cerrando herméticamente las puertas de la sala de control.");
            sc.nextLine();
            
            System.out.println("Estado actual en la sala de control:\n"
                    + "- Darian Zenith Kryos — Ingeniero espacial.\r\n"
                    + "- " + player + " — Ingeniero en sistemas.\r\n"
                    + "- Tripulación restante aislada en el módulo de hibernación.\r\n"
                    + "- Optimización drástica de recursos en proceso.");
            sc.nextLine();
            System.out.println("“Darian y tú miran fijamente el monitor de la sala de control. Un silencio sepulcral inunda la habitación.”");
            System.out.println("Darian: ARIS... ¿Qué pasó con los demás? ¡Responde!");
            sc.nextLine();
            
            decir("ARIS 9", "No se reporta estabilidad vital en la cámara de hibernación. Comunicación y energía desconectadas al 100%. Mis funciones actuales están limitadas solo para la sala de control.");
            
            System.out.println("Darian: ARIS, ¿cómo demonios se desestabilizaron los escudos tan rápido?");
            sc.nextLine();
            
            decir("ARIS 9", "Los escudos fueron pulverizados al entrar de imprevisto al cinturón de asteroides del sistema Heliox Prime.");
            
            System.out.println("Darian: ¿Cómo pasó? ¿Es esto posible?");
            sc.nextLine();
            
            // --- DECISIÓN 3 ---
            opcionValida = false;
            do {
                System.out.println("Darian está colapsando mentalmente. ¿Cómo actúas?");
                System.out.println("1. [Agresivo] Confrontar a Darian y culparlo directamente.");
                System.out.println("2. [Calmado] No confrontarlo y analizar las lecturas de ARIS.");
                System.out.println("3. [Empático] Consolarlo, ambos cometieron fallas.");
                System.out.print("Tu elección: ");
                eleccion3 = sc.nextInt();
                sc.nextLine();
                
                switch (eleccion3) {
                    case 1:
                        System.out.println(player + ": ¿Aún lo dudas? Tú tuviste la idea ridícula de salir corriendo sin inyectarte.\n"
                                + "¿Y qué pasó? Dejaste bloqueados a los demás y ahora estamos aquí varados. ¡Sus muertes son tu culpa!");
                        sc.nextLine();
                        System.out.println("Darian: ¿Solo yo tengo la culpa? ¡Tú y los demás me frenaron en la discusión! Si no me hubieran retrasado, habría salvado los escudos.");
                        opcionValida = true;
                        break;
                    case 2:
                        System.out.println(player + ": Discutir no los traerá de vuelta, Darian. Guarda silencio y miremos las pantallas.");
                        System.out.println("“Darian aprieta los puños pero desvía la mirada hacia el radar de ARIS 9.“");
                        opcionValida = true;
                        break;
                    case 3:
                        System.out.println(player + ": No podíamos saber que los asteroides golpearían justo ahora, Darian. Fue una cadena de mala suerte... pero estamos vivos.");
                        System.out.println("Darian: ...Es una pesadilla, " + player + ". Nova, Axel... todos se han ido.");
                        opcionValida = true;
                        break;
                    default:
                        System.out.println("\n[ERROR] Opción no válida, intente de nuevo.\n");
                        break;
                }
            } while (!opcionValida);

            decir("ARIS 9", "Activando protocolo de emergencia: Misión Fallida. Para regresar a la Tierra de forma segura se necesitan: 3 placas estructurales y la confirmación manual de la redirección de energía. También se requiere una recodificación nanotecnológica para la adaptación del motor de impulsos.");
            sc.nextLine();
            
            System.out.println(player + ": Ya oíste. Si queremos sobrevivir, saldré a buscar esas 3 placas por la nave.\n"
                    + "Tú planea con ARIS la implementación física en los motores, yo me encargo de los sistemas.");
            sc.nextLine();  
            System.out.println("Darian: Está bien... He de admitir que mantienes los cables en su lugar. No quiero terminar flotando en este vacío.");
            
        } else if (eleccion2 == 2) { 
            System.out.println(player + ": No hay tiempo. Vamos a la sala de control de inmediato. Yo manejo la distribución de energía desde la consola y tú la estructura.");
            System.out.println("");
            System.out.println("“Darian y tú se dirigen a la sala de control esquivando las alertas de la nave.”");
            sc.nextLine();
            System.out.println("Darian: ¡Rápido! Yo me encargo de restablecer manualmente los acopladores mecánicos y tú asegúrate de que ARIS 9 no bloquee los accesos.\n ¿Listo?");
            sc.nextLine();
            System.out.println(player + ": ¡Listo! ¡Sincronizando frecuencias!");
            
            decir("ARIS 9", "Ejecución de comandos no autorizada por bypass manual. Activando protocolo de seguridad de emergencia en la sala de control. Desactivando sub-estaciones de energía restantes. Cerrando mamparos blindados. Escudos externos totalmente destruidos por colisión de masa rocosa. Estado de la tripulación en celdas criogénicas: Crítico -> Desconexión de soporte vital completada por ahorro energético.");
            sc.nextLine();
            
            System.out.println("Estado actual en la sala de control:\n"
                    + "- Darian Zenith Kryos — Ingeniero espacial.\r\n"
                    + "- " + player + " — Ingeniero en sistemas.\r\n"
                    + "- Módulo de hibernación aislado sin atmósfera.");
            sc.nextLine();
            System.out.println("“Miras a Darian. Sus manos tiemblan sobre las palancas inútiles de control estructural.”");
            sc.nextLine();
            System.out.println("Darian: ARIS... Dime que es un error de lectura. ¿Qué pasó con el resto del equipo?");
            System.out.println("");
            
            decir("ARIS 9", "No se reporta estabilidad vital. Fugas térmicas masivas en el bloque B. Mis funciones están limitadas estrictamente a este perímetro.");
            sc.nextLine();
            
            System.out.println("Darian: No... no... nos apresuramos todo lo que pudimos... ¿Cómo se destruyeron los escudos?");
            System.out.println("");
            
            decir("ARIS 9", "Colisión directa con fragmentos hiper-densos en el cinturón de asteroides Heliox Prime.");
            sc.nextLine();
            
            // --- DECISIÓN 4 ---
            opcionValida = false;
            do {
                System.out.println("El pánico está ganando terreno. ¿Cuál es tu postura?");
                System.out.println("1. [Realista] Aceptar la tragedia e intentar buscar una salida.");
                System.out.println("2. [Colapso] Perder los estribos ante la IA.");
                System.out.print("Tu elección: ");
                eleccion4 = sc.nextInt();
                sc.nextLine();
                
                switch (eleccion4) {
                    case 1:
                        System.out.println(player + ": Los dejamos morir por correr hacia aquí, Darian... ¡Están muertos! ¿Ahora qué hacemos? ¿Nos quedamos a esperar que nos destruya otro asteroide?");
                        System.out.println("");
                        System.out.println("Darian: No lo sé, " + player + ". Las lecturas estructurales dan asco, no hay piezas de repuesto en este panel. ¡No veo una solución!");
                        System.out.println("");
                        System.out.println(player + ": Siempre hay un maldito parche en el software. ARIS, calcula la probabilidad de supervivencia si canibalizamos piezas internas.");
                        opcionValida = true;
                        break;
                    case 2:
                        System.out.println(player + ": “Te quedas petrificado procesando las palabras de ARIS 9. Darian golpea la pantalla rota del monitor.”");
                        System.out.println("Darian: ¡Maldita máquina! ¡Tu trabajo era advertirnos antes!");
                        
                        decir("ARIS 9", "El sensor de largo alcance fue dañado en el despegue inicial. Mi base de datos carecía de esa información.");
                        
                        opcionValida = true;
                        break;
                    default:
                        System.out.println("\n[ERROR] Opción no válida, intente de nuevo.\n");
                        break;
                }
            } while (!opcionValida);
        }

        decir("ARIS 9", "Analizando las 3 placas estructurales necesarias en los sectores internos y las reservas de nanotecnologías. Configuración posible: se puede manufacturar una cápsula de escape de corto alcance para dos personas.");
        System.out.println("");
        System.out.println(player + ": Hazlo de inmediato. Comienza la impresión en 3D de los eyectores.");
        sc.nextLine();
        
        decir("ARIS 9", "El proceso tardará exactamente 25 minutos.");
        
        System.out.println("Darian: ¿ARIS, la antena de hiper-frecuencia sirve? ¿Has avisado al Gobierno Mundial de nuestro estado?");
        sc.nextLine();
        
        decir("ARIS 9", "Envié un pulso codificado de emergencia. No hay respuesta directa... Esperen. Detectando señal entrante encriptada del Gobierno Mundial. ¿Establecer enlace?");
        sc.nextLine();
        
        System.out.println("Darian: Sí, ponla en altavoz.");
        System.out.println("");
        System.out.println("???: Supervivientes de la nave Novaeklis 3, aquí el Control de Misión terrestre. ARIS 9 nos ha transmitido los reportes de telemetría.");
        sc.nextLine();
        System.out.println("Lamentamos profundamente la pérdida del resto de los científicos... Sin embargo, deben saber que la Tierra enfrenta una crisis económica global absoluta por escasez de recursos.");
        System.out.println("No tenemos los fondos ni la tecnología remanente para enviar una misión de rescate por ustedes.");
        sc.nextLine();
        System.out.println("???: El destino de la misión recae en ustedes dos. Tienen dos opciones definitivas: usar el motor modificado para regresar a la Tierra o continuar hacia Aetherion 3 y realizar el despliegue de reconocimiento.");
        sc.nextLine();
        System.out.println(player + ": ¿Tú qué opinas, Darian? ¿Qué hacemos?");
        System.out.println("");
        System.out.println("Darian: Yo elegiría seguir con la misión. Si volvemos a un planeta en ruinas sin respuestas, todos los sacrificios de Nova, Tarek y los demás habrán sido en vano.");
        sc.nextLine();
        System.out.println(player + ": Mmmm....");
        sc.nextLine();
        
        // --- DECISIÓN 5 ---
        opcionValida = false;
        do {
            System.out.println("El futuro de la humanidad y tu propia supervivencia dependen de esto. Elige el destino:");
            System.out.println("1. Continuar el viaje hacia el exoplaneta Aetherion 3");
            System.out.println("2. Abortar misión y regresar a la Tierra");
            System.out.print("Tu elección: ");
            eleccion5 = sc.nextInt();
            sc.nextLine();
            
            switch(eleccion5) {
                case 1:
                    System.out.println(player + ": Está bien... Gobierno Mundial, iniciaremos el protocolo de aproximación. Seguiremos con la exploración de Aetherion 3.");
                    System.out.println("");
                    System.out.println("Darian: ¡Excelente decisión, " + player + "! Sabía que tenías madera de pionero.");
                    sc.nextLine();
                    System.out.println("???: Entendido, Novaeklis 3. Aunque debes codificar la matriz lógica de ARIS para adaptar las directrices de exploración y que los bloqueos automáticos no congelen los propulsores.");
                    System.out.println("");
                    System.out.println("<< Iniciando Minijuego de Codificación de Sistemas Avanzados >>");
                    System.out.println("“Darian y tú aseguran sus arneses en la pequeña cápsula impresa que se separa del cascarón roto de la nave rumbo al planeta.”");
                    System.out.println(player + ": ¿Por qué te emocionaste tanto cuando dije que seguiría con la misión?");
                    sc.nextLine();
                    System.out.println("Darian: Pues la verdad...");
                    
                    if (actitudConAxel == 2) {
                        System.out.println("Darian: Hubiera preferido explorar un mundo desconocido con un ingeniero ingenioso que hable de plantas espaciales, que quedarme encerrado con los reportes lógicos de ARIS, jajaja.");
                    } else {
                        System.out.println("Darian: Hubiera preferido explorar con un compañero de carne y hueso que entienda el valor del riesgo, que quedarme solo con una IA fría.");
                    }
                    sc.nextLine();
                    
                    decir("ARIS 9", "Tiempo aproximado para la inserción atmosférica en Aetherion 3: 30 minutos.");
                    
                    System.out.println(player + ": Ya oíste... Estamos a minutos de tocar suelo virgen. Aunque las bajas en el camino pesen demasiado.");
                    sc.nextLine();
                    System.out.println("Darian: Sé que no soy un tipo fácil de tratar, " + player + "... y lamento haber actuado de forma tan impulsiva allá atrás. Pudo habernos matado a todos.");
                    System.out.println("");
                    
                    // --- DECISIÓN 6 ---
                    boolean opcionValida6 = false;
                    do {
                        System.out.println("¿Cómo reaccionas ante las disculpas del ingeniero?");
                        System.out.println("1. Aceptar las disculpas y mirar hacia adelante.");
                        System.out.println("2. Cortar el drama y pedirle a ARIS un análisis del terreno.");
                        System.out.println("3. Aprovechar el momento para hacerle una pregunta personal.");
                        System.out.print("Tu elección: ");
                        int eleccion6 = sc.nextInt();
                        sc.nextLine();
                        
                        switch(eleccion6) {
                            case 1:
                                System.out.println(player + ": Lo hecho, hecho está, Darian. Ya no podemos cambiar el pasado. Si no hubieras tenido las agallas de acoplar esos motores manuales, ninguno estaría aquí.");
                                System.out.println("Darian: Supongo que hacemos un buen equipo después de todo.");
                                opcionValida6 = true;
                                break;
                            case 2:
                                System.out.println(player + ": Deja el remordimiento para los filósofos, Darian. Ya estamos aquí. ARIS, despliega el espectrómetro y danos un análisis exterior de Aetherion 3 para localizar zonas de aterrizaje.");
                                sc.nextLine();
                                
                                decir("ARIS 9", "Procesando espectrografía... Escaneando cuadrante delta... El terreno presenta planicies estables. Iniciando secuencia de descenso guiado.");
                                
                                opcionValida6 = true;
                                break;
                            case 3:
                                boolean preguntaValida = false;
                                do {
                                    System.out.println("¿Qué quieres saber de tu único compañero?");
                                    System.out.println("1. ¿Por qué aceptaste esta misión suicida de forma voluntaria?");
                                    System.out.println("2. ¿De verdad te emociona explorar este desierto biológico?");
                                    System.out.println("3. ¿Estás realmente preparado para lo que encontremos abajo?");
                                    System.out.print("Tu pregunta: ");
                                    int pregunta = sc.nextInt();
                                    sc.nextLine();
                                    
                                    switch(pregunta) {
                                        case 1:
                                            System.out.println(player + ": ¿Por qué aceptaste esta misión originalmente, Darian?");
                                            sc.nextLine();
                                            System.out.println("Darian: Siempre quise dejar una marca imborrable en la historia, construir algo que sobreviviera a mi propia muerte. Cuando el Gobierno Mundial abrió las vacantes, no lo dudé.");
                                            System.out.println("¿Y tú? ¿Por qué dejar tu vida en la Tierra?");
                                            sc.nextLine();
                                            System.out.println(player + ": Quería ser el ingeniero en sistemas que diseñara las redes del nuevo mundo. Que las futuras generaciones supieran que mis líneas de código permitieron su civilización.");
                                            System.out.println("Darian: Jaja, un idealista de los datos. Bueno, parece que ambos tendremos nuestro nombre en los libros.");
                                            sc.nextLine();
                                            preguntaValida = true;
                                            break;
                                        case 2:
                                            System.out.println(player + ": ¿Te genera emoción real explorar Aetherion 3 bajo estas condiciones?");
                                            System.out.println("");
                                            System.out.println("Darian: Por supuesto. Ser los primeros humanos en respirar en este sistema... es un honor que borra cualquier miedo.");
                                            sc.nextLine();
                                            System.out.println(player + ": Recuerda que compartimos el crédito, yo controlo la navegación de esta lata.");
                                            System.out.println("");
                                            System.out.println("Darian: Está bien, compartiremos las estatuas que nos hagan en el campamento base.");
                                            sc.nextLine();
                                            preguntaValida = true;
                                            break;
                                        case 3:
                                            System.out.println(player + ": ¿Estás preparado para lo que nos espere allá abajo? No sabemos si la atmósfera tiene patógenos o anomalías.");
                                            System.out.println("");
                                            System.out.println("Darian: Nadie lo está realmente. Pero nací para resolver problemas de presión y metal. No voy a retroceder ahora.");
                                            sc.nextLine();
                                            System.out.println(player + ": Excelente. Mantén esa energía cuando crucemos la termósfera.");
                                            preguntaValida = true;
                                            break;
                                        default:
                                            System.out.println("\n[ERROR] Pregunta no válida, selecciona 1, 2 o 3.\n");
                                            break;
                                    }
                                } while (!preguntaValida);
                                opcionValida6 = true;
                                break;
                            default:
                                System.out.println("\n[ERROR] Opción no válida, elija de nuevo.\n");
                                break;
                        }
                    } while (!opcionValida6);
                    
                    opcionValida = true;
                    break;
                    
                case 2:
                    System.out.println(player + ": Matriz de navegación reconfigurada. Gobierno Mundial, hemos determinado abortar la exploración. Regresamos a casa con los restos.");
                    System.out.println("");
                    System.out.println("???: Entendido, tripulación. Los hangares orbitales automatizados estarán listos para su reingreso. Buen viaje de vuelta.");
                    System.out.println("");
                    
                    decir("ARIS 9", "Calculando ventanas hiperbólicas de retorno... Modificando vectores de empuje. Trayectoria fija hacia el Sistema Solar.");
                    sc.nextLine();
                    
                    System.out.println("FIN ALTERNATIVO: El Retorno de los Fantasmas de la Novaeklis.");
                    sc.nextLine();
                    opcionValida = true;
                    break;
                    
                default:
                    System.out.println("\n[ERROR] Opción no válida, elija de nuevo.\n");
                    break;
            }
        } while (!opcionValida);

        System.out.println("Fin de la simulación histórica...");
    }
}