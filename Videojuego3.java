import java.util.*;

public class Videojuego3 {
    public static void main(String[] args) {
        String[] territorios = {"bosque", "campo abierto", "montaña", "desierto", "playa"};
        String territorio = territorios[new Random().nextInt(territorios.length)];

        String[] reinos = {"Inglaterra", "Francia", "Castilla-Aragón", "Moros", "Sacro Imperio Romano-Germánico"};
        Random rnd = new Random();
        String r1 = reinos[rnd.nextInt(reinos.length)];
        String r2;
        do {
            r2 = reinos[rnd.nextInt(reinos.length)];
        } while (r2.equals(r1));

        System.out.println("Territorio: " + territorio + ", Reino1: " + r1 + ", Reino2: " + r2);

        mapa m = new mapa(territorio, r1, r2);
        m.generarEjercitos();
        m.mostrarTablero();
        m.mostrarDistribucion();
        m.mostrarResumen();
    }
}
