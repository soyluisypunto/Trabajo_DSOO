import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Videojuego3 {
    static mapa juego;

    public static void main(String[] args) {
        JFrame ventana = new JFrame("VIDEOJUEGO");
        ventana.setSize(800, 900);
        ventana.setLayout(new BorderLayout());

        // ------ Menú ------
        JMenuBar panel_botones = new JMenuBar();
        JMenu archivo = new JMenu("Archivo");
        JMenuItem nuevo = new JMenuItem("Nuevo");
        JMenuItem abrir = new JMenuItem("Abrir");
        JMenuItem guardar = new JMenuItem("Guardar");
        JMenuItem salir = new JMenuItem("Salir");

        archivo.add(nuevo);
         archivo.add(abrir);
          archivo.add(guardar);
           archivo.add(salir);

        JMenu ver = new JMenu("Ver");
        JMenuItem mostrar_consola = new JMenuItem("Mostrar Consola");
        ver.add(mostrar_consola);

        JMenu ayuda = new JMenu("Ayuda");
        JMenuItem sobre_juego = new JMenuItem("Sobre el juego");
        ayuda.add(sobre_juego);

        panel_botones.add(archivo);
        panel_botones.add(ver);
        panel_botones.add(ayuda);
        ventana.setJMenuBar(panel_botones);

        // Panel Tablero
        JPanel panelTablero = new JPanel();
        panelTablero.setLayout(new GridLayout(10,10));
        ventana.add(panelTablero, BorderLayout.CENTER);

        // Consola
        JTextArea consola = new JTextArea(8, 0); // altura 8 filas
        consola.setEditable(false);
        consola.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane tamaño_ilimitado = new JScrollPane(consola);
        tamaño_ilimitado.setVisible(false);
        ventana.add(tamaño_ilimitado, BorderLayout.SOUTH);

        // Mostrar/ocultar consola
        mostrar_consola.addActionListener(e -> {
            tamaño_ilimitado.setVisible(!tamaño_ilimitado.isVisible());
            ventana.revalidate();
            ventana.repaint();
        });

        // Redirigir System.out a la consola
        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                consola.append(String.valueOf((char) b));
                consola.setCaretPosition(consola.getDocument().getLength());
            }
        });
        System.setOut(ps);
        System.setErr(ps);

        // Acción Nuevo
        nuevo.addActionListener(e -> {
            consola.setText("");
            panelTablero.removeAll();

            String[] territorios = {"bosque","campo abierto","montaña","desierto"};
            String territorio = territorios[new java.util.Random().nextInt(territorios.length)];

            String[] reinos = {"Inglaterra", "Francia", "Castilla-Aragón", "Moros", "Sacro Imperio Romano-Germánico"};
            java.util.Random rnd = new java.util.Random();
            String r1 = reinos[rnd.nextInt(reinos.length)];
            String r2;
            do {
                r2 = reinos[rnd.nextInt(reinos.length)];
            } while (r2.equals(r1));

            System.out.println("Territorio: " + territorio);
            System.out.println("Reino 1: " + r1);
            System.out.println("Reino 2: " + r2 + "\n");

            juego = new mapa(territorio, r1, r2);
            juego.generarEjercitos();

            crearTableroGrafico(panelTablero, juego);

            System.out.println("Ejércitos generados.");
            juego.mostrarTablero();
            juego.mostrarDistribucion();
            juego.mostrarResumen();

            panelTablero.revalidate();
            panelTablero.repaint();
        });
        
        //Acción al presionar sobre el juego 
        sobre_juego.addActionListener(e -> {
            String info = "Videojuego de Estrategia - Versión 1.0\n"
                        + "Autor: Luis\n"
                        + "Descripción: Simulación de batalla entre ejércitos en diferentes territorios.\n"
                        + "El juego permite crear ejércitos, ver el tablero y observar la distribución de fuerzas.";
            
            JOptionPane.showMessageDialog(
                ventana,
                info,
                "Sobre el juego",
                JOptionPane.INFORMATION_MESSAGE
            );
        });
        // Salir
        salir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                ventana,
                "¿Estás seguro de que deseas salir?",
                "Salir",
                JOptionPane.YES_NO_OPTION
            );
            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        // Guardar
        guardar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(ventana) == JFileChooser.APPROVE_OPTION) {
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(fileChooser.getSelectedFile()))) {
                        oos.writeObject(juego);
                    System.out.println("Juego guardado correctamente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        // Abrir
        abrir.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(ventana) == JFileChooser.APPROVE_OPTION) {
                try (ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(fileChooser.getSelectedFile()))) {
                    juego = (mapa) ois.readObject();
                    crearTableroGrafico(panelTablero, juego);
                    panelTablero.revalidate();
                    panelTablero.repaint();
                    System.out.println("Juego cargado correctamente.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }

    private static void crearTableroGrafico(JPanel panelTablero, mapa juego) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Soldado s = juego.getTablero().getTablero().get(i).get(j);
                JLabel celda = new JLabel();
                celda.setOpaque(true);
                celda.setHorizontalAlignment(SwingConstants.CENTER);
                celda.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if (s == null) celda.setBackground(Color.LIGHT_GRAY);
                else if (s.getEjercito() == 1) {
                    celda.setBackground(Color.BLUE);
                    celda.setText("A");
                    celda.setForeground(Color.WHITE);
                } else {
                    celda.setBackground(Color.RED);
                    celda.setText("B");
                    celda.setForeground(Color.WHITE);
                }
                panelTablero.add(celda);
            }
        }
    }
}
