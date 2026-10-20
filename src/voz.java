import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class voz {
    private static final Queue<String> colaTextos = new LinkedList<>();
    private static boolean reproduciendo = false;

    public static synchronized void hablar(String texto) {
        colaTextos.add(texto);
        if (!reproduciendo) {
            reproducirSiguiente();
        }
    }

    private static void reproducirSiguiente() {
        if (colaTextos.isEmpty()) {
            reproduciendo = false;
            return;
        }
        reproduciendo = true;
        String texto = colaTextos.poll();

        new Thread(() -> {
            try {
                String textoLimpio = texto.replace("'", "").replace("\n", " ");
                // Usamos una variable de PowerShell para asegurarnos de que hable y espere
                String comando = "powershell -Command \"Add-Type -AssemblyName System.Speech; $synth = New-Object System.Speech.Synthesis.SpeechSynthesizer; $synth.Speak('" + textoLimpio + "');\"";
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", comando);
                Process proceso = pb.start();
                proceso.waitFor(); // Espera a que termine de hablar esta frase antes de pasar a la que sigue
            } catch (Exception e) {
                // Silenciar errores para que el juego no se detenga
            }
            reproducirSiguiente(); // Pasa al siguiente texto en la fila
        }).start();
    }
}