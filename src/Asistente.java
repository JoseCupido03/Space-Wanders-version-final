import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Asistente de misión: una ventanita abajo a la derecha (se arrastra con el mouse)
 * que describe la situación antes de cada decisión. También escribe el aviso en la
 * consola y lo dice en voz alta.
 *
 * Uso:  Asistente.iniciar();            // una vez, al empezar main
 *       Asistente.ayuda("situación");   // antes de cada decisión
 */
public class Asistente {

    private static JWindow ventana;
    private static JLabel cara;
    private static JTextArea texto;
    private static javax.swing.Timer reloj;

    /** Abre el asistente abajo a la derecha. Llámalo una vez al empezar main. */
    public static void iniciar() {
        if (GraphicsEnvironment.isHeadless()) return;
        try {
            SwingUtilities.invokeAndWait(() -> crearVentana());
        } catch (Exception e) {
            ventana = null;   // sin ventana, el juego sigue normal en consola
        }
    }

    private static void crearVentana() {
        ventana = new JWindow();
        ventana.setAlwaysOnTop(true);
        ventana.setFocusableWindowState(false);   // no le roba el teclado a la consola

        Color fondo = new Color(12, 14, 34);
        Color cian = new Color(0, 240, 255);
        Color amarillo = new Color(255, 230, 0);

        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.setBackground(fondo);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(cian, 2),
                BorderFactory.createEmptyBorder(8, 10, 10, 10)));

        cara = new JLabel("(•‿•)");
        cara.setForeground(amarillo);
        cara.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));

        JLabel titulo = new JLabel("  ASISTENTE · arrástrame");
        titulo.setForeground(cian);
        titulo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));

        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.setOpaque(false);
        cabecera.add(cara, BorderLayout.WEST);
        cabecera.add(titulo, BorderLayout.CENTER);

        texto = new JTextArea();
        texto.setEditable(false);
        texto.setFocusable(false);
        texto.setHighlighter(null);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setOpaque(false);
        texto.setForeground(Color.WHITE);
        texto.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        texto.setText("¡Hola! Soy tu asistente. Antes de cada decisión te explicaré la situación.");

        panel.add(cabecera, BorderLayout.NORTH);
        panel.add(texto, BorderLayout.CENTER);
        ventana.setContentPane(panel);
        ventana.setSize(340, 170);

        // esquina inferior derecha de la pantalla
        Rectangle pantalla = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        ventana.setLocation(pantalla.x + pantalla.width - 340 - 20, pantalla.y + pantalla.height - 170 - 20);

        // se puede arrastrar con el mouse
        final int[] dentro = new int[2];
        MouseAdapter mover = new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                dentro[0] = e.getXOnScreen() - ventana.getX();
                dentro[1] = e.getYOnScreen() - ventana.getY();
            }
            @Override public void mouseDragged(MouseEvent e) {
                ventana.setLocation(e.getXOnScreen() - dentro[0], e.getYOnScreen() - dentro[1]);
            }
        };
        for (Component c : new Component[] { panel, cabecera, cara, titulo, texto }) {
            c.addMouseListener(mover);
            c.addMouseMotionListener(mover);
        }
        ventana.setVisible(true);
    }

    /** Escribe el mensaje en la ventanita, letra por letra, moviendo la boca. */
    public static void decir(final String mensaje) {
        if (ventana == null) return;
        SwingUtilities.invokeLater(() -> {
            if (reloj != null) reloj.stop();
            texto.setText("");
            final int[] i = { 0 };
            reloj = new javax.swing.Timer(22, e -> {
                if (i[0] < mensaje.length()) {
                    texto.append(String.valueOf(mensaje.charAt(i[0]++)));
                    cara.setText(i[0] % 4 < 2 ? "(•o•)" : "(•‿•)");
                } else {
                    cara.setText("(•‿•)");
                    ((javax.swing.Timer) e.getSource()).stop();
                }
            });
            reloj.start();
        });
    }

    /** Descripción de la situación antes de decidir: consola + ventana + voz. */
    public static void ayuda(String situacion) {
        decir(situacion);
        voz.hablar("ASISTENTE", situacion);
        System.out.println();
        System.out.println(Estilo.ROSA + Estilo.NEGRITA + "[ASISTENTE] " + Estilo.RESET
                + Estilo.BLANCO + situacion + Estilo.RESET);
    }
}