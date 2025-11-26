import java.util.*;
import java.io.Serializable;

public class mapa implements Serializable{
    private crear_tablero tablero;
    private String territorio;
    private String reino1;
    private String reino2;
    private Soldado[] ejercito1;            
    private ArrayList<Soldado> ejercito2;      

    public mapa(String territorio, String reino1, String reino2) {
        this.tablero = new crear_tablero();
        this.tablero.inicializarTablero();
        this.territorio = territorio;
        this.reino1 = reino1;
        this.reino2 = reino2;
    }

    public void mostrarTablero() {
        tablero.mostrarTablero();
    }

    public void generarEjercitos() {
        int n1 = (int) (Math.random() * 10 + 1);
        int n2 = (int) (Math.random() * 10 + 1);

        // ejército 1 → arreglo
        ArrayList<Soldado> temp1 = tablero.crearEjercito(reino1, n1, "X1", 1);
        ejercito1 = temp1.toArray(new Soldado[0]);

        // ejército 2 → ArrayList
        ejercito2 = tablero.crearEjercito(reino2, n2, "X2", 2);

        aplicarBonusTerritorio(ejercito1, reino1);
        aplicarBonusTerritorio(ejercito2, reino2);
    }

    private void aplicarBonusTerritorio(Soldado[] ejercito, String reino) {
        boolean aplica = false;
        switch (reino) {
            case "Inglaterra": aplica = territorio.equals("bosque"); break;
            case "Francia": aplica = territorio.equals("campo abierto"); break;
            case "Castilla-Aragón": aplica = territorio.equals("montaña"); break;
            case "Moros": aplica = territorio.equals("desierto"); break;
            case "Sacro Imperio Romano-Germánico":
                aplica = territorio.equals("bosque") || territorio.equals("playa") || territorio.equals("campo abierto");
                break;
        }
        if (aplica) {
            for (Soldado s : ejercito) {
                s.setVida(s.getVida() + 1);
            }
        }
    }

    private void aplicarBonusTerritorio(ArrayList<Soldado> ejercito, String reino) {
        boolean aplica = false;
        switch (reino) {
            case "Inglaterra": aplica = territorio.equals("bosque"); break;
            case "Francia": aplica = territorio.equals("campo abierto"); break;
            case "Castilla-Aragón": aplica = territorio.equals("montaña"); break;
            case "Moros": aplica = territorio.equals("desierto"); break;
            case "Sacro Imperio Romano-Germánico":
                aplica = territorio.equals("bosque") || territorio.equals("playa") || territorio.equals("campo abierto");
                break;
        }
        if (aplica) {
            for (Soldado s : ejercito) {
                s.setVida(s.getVida() + 1);
            }
        }
    }

    public void mostrarResumen() {
        System.out.println("\n=== TABLERO ===");
        tablero.mostrarTablero();

        System.out.println("\n--- Ejercito " + reino1 + " ---");
        mostrarEjercitoArreglo(ejercito1, 1);
        tablero.mostrarMayorVida(new ArrayList<>(Arrays.asList(ejercito1)), 1);
        tablero.mostrarPromedioVida(new ArrayList<>(Arrays.asList(ejercito1)), 1);
        tablero.mostrarRanking(new ArrayList<>(Arrays.asList(ejercito1)), 1);

        System.out.println("\n--- Ejercito " + reino2 + " ---");
        tablero.mostrarOrdenCreacion(ejercito2, 2);
        tablero.mostrarMayorVida(ejercito2, 2);
        tablero.mostrarPromedioVida(ejercito2, 2);
        tablero.mostrarRanking(ejercito2, 2);

        // Resumen de batalla
        System.out.println("\n--- Resumen de Batalla ---");
        int total1 = sumarVidaEjercito(ejercito1);
        int total2 = sumarVidaEjercito(ejercito2);
        double porcentaje1 = (double) total1 / (total1 + total2) * 100;
        double porcentaje2 = (double) total2 / (total1 + total2) * 100;

        System.out.println("Ejercito 1: " + reino1 + ": " + total1 + " " + String.format("%.2f", porcentaje1) + "% de probabilidad de victoria");
        System.out.println("Ejercito 2: " + reino2 + ": " + total2 + " " + String.format("%.2f", porcentaje2) + "% de probabilidad de victoria");

        if (total1 > total2) {
            System.out.println("El ganador es el ejército 1 de: " + reino1 + ".");
        } else if (total2 > total1) {
            System.out.println("El ganador es el ejército 2 de: " + reino2 + ".");
        } else {
            System.out.println("Empate.");
        }
    }
    public void mostrarResumenTableroJFRAME() {
        System.out.println("\n--- Ejercito " + reino1 + " ---");
        mostrarEjercitoArreglo(ejercito1, 1);

        System.out.println("\n--- Ejercito " + reino2 + " ---");
        tablero.mostrarOrdenCreacion(ejercito2, 2);

        // Resumen de batalla
        System.out.println("\n--- Resumen de Batalla ---");
        int total1 = sumarVidaEjercito(ejercito1);
        int total2 = sumarVidaEjercito(ejercito2);
        double porcentaje1 = (double) total1 / (total1 + total2) * 100;
        double porcentaje2 = (double) total2 / (total1 + total2) * 100;

        System.out.println("Ejercito 1: " + reino1 + ": " + total1 + " " + String.format("%.2f", porcentaje1) + "% de probabilidad de victoria");
        System.out.println("Ejercito 2: " + reino2 + ": " + total2 + " " + String.format("%.2f", porcentaje2) + "% de probabilidad de victoria");

        if (total1 > total2) {
            System.out.println("El ganador es el ejército 1 de: " + reino1 + ".");
        } else if (total2 > total1) {
            System.out.println("El ganador es el ejército 2 de: " + reino2 + ".");
        } else {
            System.out.println("Empate.");
        }
    }

    private void mostrarEjercitoArreglo(Soldado[] ejercito, int id) {
        System.out.println("Ejercito número " + id);
        for (Soldado s : ejercito) {
            System.out.println(s);
        }
    }

    private int sumarVidaEjercito(Soldado[] ejercito) {
        int suma = 0;
        for (Soldado s : ejercito) {
            suma += s.getVida();
        }
        return suma;
    }

    private int sumarVidaEjercito(ArrayList<Soldado> ejercito) {
        int suma = 0;
        for (Soldado s : ejercito) {
            suma += s.getVida();
        }
        return suma;
    }

    public void mostrarDistribucion() {
        mostrarDistribucionEjercito1();
        mostrarDistribucionEjercito2();
    }

    public void mostrarDistribucionEjercito1() {
        int espadachin = 0, arquero = 0, caballero = 0, lancero = 0;
        for (Soldado s : ejercito1) {
            if (s instanceof Espadachin) espadachin++;
            else if (s instanceof Arquero) arquero++;
            else if (s instanceof Caballero) caballero++;
            else if (s instanceof Lancero) lancero++;
        }

        System.out.println("Ejercito 1: " + reino1);
        System.out.println("Cantidad total de soldados creados: " + ejercito1.length);
        System.out.println("Espadachines: " + espadachin);
        System.out.println("Arqueros: " + arquero);
        System.out.println("Caballeros: " + caballero);
        System.out.println("Lanceros: " + lancero);
    }

    public void mostrarDistribucionEjercito2() {
        int espadachin = 0, arquero = 0, caballero = 0, lancero = 0;
        for (Soldado s : ejercito2) {
            if (s instanceof Espadachin) espadachin++;
            else if (s instanceof Arquero) arquero++;
            else if (s instanceof Caballero) caballero++;
            else if (s instanceof Lancero) lancero++;
        }

        System.out.println("Ejercito 2: " + reino2);
        System.out.println("Cantidad total de soldados creados: " + ejercito2.size());
        System.out.println("Espadachines: " + espadachin);
        System.out.println("Arqueros: " + arquero);
        System.out.println("Caballeros: " + caballero);
        System.out.println("Lanceros: " + lancero);
    }
    public crear_tablero getTablero() {
    return tablero;
    }
}
