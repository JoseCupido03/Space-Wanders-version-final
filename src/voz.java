import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Voz del juego (Windows, usa las voces instaladas en el sistema).
 * Cada personaje tiene su perfil en el método perfil(): género, número de voz,
 * velocidad y volumen. Con voz.activa = false se silencia todo.
 */
public class voz {

    /** Pon false para silenciar toda la voz. */
    public static boolean activa = true;

    private static final Queue<String[]> cola = new LinkedList<String[]>();
    private static boolean reproduciendo = false;
    private static Process actual = null;

    private static final String SCRIPT =
            "Add-Type -AssemblyName System.Speech;"
          + "$s = New-Object System.Speech.Synthesis.SpeechSynthesizer;"
          + "$todas = @($s.GetInstalledVoices() | Where-Object { $_.Enabled } | ForEach-Object { $_.VoiceInfo });"
          + "$es = @($todas | Where-Object { $_.Culture.TwoLetterISOLanguageName -eq 'es' });"
          + "if ($es.Count -eq 0) { $es = $todas };"
          + "$g = @($es | Where-Object { $_.Gender.ToString() -eq $env:VOZ_GENERO });"
          + "if ($g.Count -eq 0) { $g = $es };"
          + "if ($g.Count -gt 0) { $i = ([int]$env:VOZ_INDICE) % $g.Count; $s.SelectVoice($g[$i].Name) };"
          + "$s.Rate = [int]$env:VOZ_VELOCIDAD;"
          + "$s.Volume = [int]$env:VOZ_VOLUMEN;"
          + "$s.Speak($env:VOZ_TEXTO);";

    private static final String CODIGO =
            Base64.getEncoder().encodeToString(SCRIPT.getBytes(StandardCharsets.UTF_16LE));

    /** Voz del narrador (para las llamadas simples de main). */
    public static void hablar(String texto) {
        hablar("NARRADOR", texto);
    }

    /** Habla con la voz del personaje indicado. */
    public static synchronized void hablar(String personaje, String texto) {
        if (!activa || texto == null) return;
        String limpio = limpiar(texto);
        if (limpio.isEmpty()) return;
        cola.add(new String[] { personaje, limpio });
        if (!reproduciendo) reproducirSiguiente();
    }

    /** Corta lo que esté sonando y vacía la fila. */
    public static synchronized void callar() {
        cola.clear();
        if (actual != null) actual.destroyForcibly();
    }

    private static synchronized void reproducirSiguiente() {
        final String[] item = cola.poll();
        if (item == null) {
            reproduciendo = false;
            actual = null;
            return;
        }
        reproduciendo = true;

        new Thread(() -> {
            try {
                String[] p = perfil(item[0]);
                ProcessBuilder pb = new ProcessBuilder("powershell", "-NoProfile", "-NonInteractive",
                        "-EncodedCommand", CODIGO);
                pb.environment().put("VOZ_GENERO", p[0]);
                pb.environment().put("VOZ_INDICE", p[1]);
                pb.environment().put("VOZ_VELOCIDAD", p[2]);
                pb.environment().put("VOZ_VOLUMEN", p[3]);
                pb.environment().put("VOZ_TEXTO", item[1]);
                pb.redirectErrorStream(true);
                pb.redirectOutput(ProcessBuilder.Redirect.DISCARD);
                Process proc = pb.start();
                synchronized (voz.class) { actual = proc; }
                proc.getOutputStream().close();
                proc.waitFor();
            } catch (Exception e) {
                // si falla la voz, el juego sigue
            }
            reproducirSiguiente();
        }).start();
    }

    /** { género, número de voz de ese género, velocidad (-10 a 10), volumen (0 a 100) } */
    private static String[] perfil(String nombre) {
        String n = nombre.trim().toUpperCase();
        if (n.startsWith("ARIS") || n.startsWith("ARRIS")) return new String[] { "Male",   "0", "-3", "100" };
        switch (n) {
            case "DARIAN":    return new String[] { "Male",   "0",  "1", "100" };
            case "AXEL":      return new String[] { "Male",   "1",  "2", "100" };
            case "TAREK":     return new String[] { "Male",   "1", "-1", "100" };
            case "NOVA":      return new String[] { "Female", "0",  "1", "100" };
            case "ZYRA":      return new String[] { "Female", "1",  "2", "100" };
            case "???":       return new String[] { "Female", "0", "-2",  "90" };   // Control de Misión
            case "NARRADOR":  return new String[] { "Female", "0", "-1",  "85" };
            case "ASISTENTE": return new String[] { "Female", "1",  "0", "100" };
            case "ELYS":
            case "LYRA":      return new String[] { "Female", "1",  "0", "100" };   // jugadora
            default:          return new String[] { "Male",   "1",  "0", "100" };   // jugador
        }
    }

    private static String limpiar(String t) {
        return t.replaceAll("\u001B\\[[0-9;?]*[A-Za-z]", "")
                .replaceAll("[\u201C\u201D\"*\u2022\\[\\]<>\u00AB\u00BB]", " ")
                .replace("\r", " ").replace("\n", " ")
                .replaceAll("\\s+", " ").trim();
    }
}