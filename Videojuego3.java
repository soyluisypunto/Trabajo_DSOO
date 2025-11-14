import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

public class Videojuego3 extends JFrame {

    private JTextArea consola;
    private JButton btnGenerar, btnTablero, btnDistribucion, btnResumen;
    private mapa m;

    public Videojuego3() {

        setTitle("Videojuego Medieval");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        consola = new JTextArea();
        consola.setEditable(false);
        consola.setBackground(Color.BLACK);
        consola.setForeground(Color.GREEN);
        consola.setFont(new Font("Consolas", Font.PLAIN, 14));
        add(new JScrollPane(consola), BorderLayout.CENTER);

        redirigirSalida();

        JPanel panelBotones = new JPanel(new GridLayout(1, 4));

        btnGenerar = new JButton("Generar mapa");
        btnTablero = new JButton("Mostrar tablero");
        btnDistribucion = new JButton("Distribución");
        btnResumen = new JButton("Resumen");

        panelBotones.add(btnGenerar);
        panelBotones.add(btnTablero);
        panelBotones.add(btnDistribucion);
        panelBotones.add(btnResumen);

        add(panelBotones, BorderLayout.SOUTH);

        btnGenerar.addActionListener(e -> generar());

        btnTablero.addActionListener(e -> {
            if (m != null)
                m.mostrarTablero();
            else
                System.out.println("Primero genera el mapa.\n");
        });

        btnDistribucion.addActionListener(e -> {
            if (m != null)
                m.mostrarDistribucion();
            else
                System.out.println("Primero genera el mapa.\n");
        });

        btnResumen.addActionListener(e -> {
            if (m != null)
                m.mostrarResumen();
            else
                System.out.println("Primero genera el mapa.\n");
        });

        setVisible(true);
    }

    private void generar() {
        String[] territorios = {"bosque", "campo abierto", "montaña", "desierto", "playa"};
        String territorio = territorios[new Random().nextInt(territorios.length)];

        String[] reinos = {"Inglaterra", "Francia", "Castilla-Aragón", "Moros", "Sacro Imperio Romano-Germánico"};
        Random rnd = new Random();

        String r1 = reinos[rnd.nextInt(reinos.length)];
        String r2;
        do {
            r2 = reinos[rnd.nextInt(reinos.length)];
        } while (r2.equals(r1));

        System.out.println("Territorio: " + territorio);
        System.out.println("Reino 1: " + r1);
        System.out.println("Reino 2: " + r2);
        System.out.println("-----------------------------------");

        m = new mapa(territorio, r1, r2);
        m.generarEjercitos();

        System.out.println("Ejercitos generados correctamente.\n");
    }

    private void redirigirSalida() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                consola.append(String.valueOf((char) b));
                consola.setCaretPosition(consola.getDocument().getLength());
            }
        };
        PrintStream ps = new PrintStream(out, true);
        System.setOut(ps);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Videojuego3());
    }
}
