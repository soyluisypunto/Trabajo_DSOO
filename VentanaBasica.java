import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaBasica extends JFrame {

    private JTextArea consola;  
    private JButton botonIniciar;

    public VentanaBasica() {
        setTitle("Videojuego de Estrategia - Versión Básica");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Videojuego de Estrategia", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        consola = new JTextArea();
        consola.setEditable(false);
        consola.setBackground(Color.BLACK);
        consola.setForeground(Color.GREEN);
        consola.setFont(new Font("Consolas", Font.PLAIN, 14));
        add(new JScrollPane(consola), BorderLayout.CENTER);

        botonIniciar = new JButton("Iniciar Juego");
        botonIniciar.setFont(new Font("Arial", Font.BOLD, 16));
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });
        add(botonIniciar, BorderLayout.SOUTH);
    }

    private void iniciarJuego() {
        consola.setText("");

        String[] territorios = {"bosque", "campo abierto", "montaña", "desierto", "playa"};
        String territorio = territorios[new java.util.Random().nextInt(territorios.length)];

        String[] reinos = {"Inglaterra", "Francia", "Castilla-Aragón", "Moros", "Sacro Imperio Romano-Germánico"};
        java.util.Random rnd = new java.util.Random();
        String r1 = reinos[rnd.nextInt(reinos.length)];
        String r2;
        do {
            r2 = reinos[rnd.nextInt(reinos.length)];
        } while (r2.equals(r1));

        consola.append("Territorio: " + territorio + "\n");
        consola.append("Reino 1: " + r1 + "\n");
        consola.append("Reino 2: " + r2 + "\n\n");

        mapa m = new mapa(territorio, r1, r2);

        m.generarEjercitos();
        consola.append("Ejércitos generados.\n");

        m.mostrarTablero();
        m.mostrarDistribucion();
        m.mostrarResumen();

        consola.append("Juego completado.\n");
    }
}
