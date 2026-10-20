import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioEspacial implements Runnable {
    private String rutaArchivo;
    private Clip clip;
    private boolean enEjecucion = true;

    public AudioEspacial(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void run() {
        try {
            File archivoSonido = new File(rutaArchivo);
            if (!archivoSonido.exists()) {
                System.out.println("\n\u001B[31m[ERROR AUDIO] No se encontró el archivo en: " + rutaArchivo + "\u001B[0m");
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoSonido);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            
            // Loop continuo para música ambiental de fondo
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            // Mantiene el hilo vivo mientras el audio se reproduzca
            while (enEjecucion) {
                Thread.sleep(100);
            }

        } catch (Exception e) {
            System.out.println("\n\u001B[31m[ERROR AUDIO] Error al inicializar el hardware de sonido.\u001B[0m");
        }
    }

    // Detiene el audio de forma segura y libera los recursos del sistema
    public void detener() {
        this.enEjecucion = false;
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
