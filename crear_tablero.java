import java.util.*;

public class crear_tablero{
    private final int tamano = 10;
    private ArrayList<ArrayList<Soldado>> tablero = new ArrayList<>();
    private ArrayList<Soldado> ejercito1 = new ArrayList<>();
    private ArrayList<Soldado> ejercito2 = new ArrayList<>();

    public void inicializarTablero() {
        for (int i = 0; i < tamano; i++) {
            ArrayList<Soldado> fila = new ArrayList<>();
            for (int j = 0; j < tamano; j++) {
                fila.add(null);
            }
            tablero.add(fila);
        }
    }

    public ArrayList<Soldado> crearEjercito(String nombreEjercito, int cantidad, String prefijo,int idEjercito) {
        ArrayList<Soldado> ejercito = new ArrayList<>();

        for (int i = 0; i < cantidad; i++) {
            String nombre = "Soldado" + i + prefijo;

            int vida = (int)(Math.random() * 5) + 1;

            int fila, columna;
            do {
                fila = (int)(Math.random() * tamano);
                columna = (int)(Math.random() * tamano);
            } while (tablero.get(fila).get(columna) != null);
            Soldado s = new Soldado(nombre, vida, fila, columna, idEjercito);

            tablero.get(fila).set(columna, s);
            ejercito.add(s);
        }

        return ejercito;
    }

    public void mostrarTablero() {
        System.out.println("\nTABLERO:");
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                Soldado s = tablero.get(i).get(j);
                if (s == null) {
                    System.out.print("[ ] ");
                } else {
                    if (s.getEjercito() == 1) {
                        System.out.print("[A] ");
                    } else {
                        System.out.print("[B] ");
                    }
                }
            }
            System.out.println();
        }
    }

    public void mostrarMayorVida(ArrayList<Soldado> ejercito, int idEjercito) {
        Soldado max = ejercito.get(0);
        for (Soldado s : ejercito) {
            if (s.getVida() > max.getVida()) {
                max = s;
            }
        }
        System.out.println("Mayor vida Ejército " + idEjercito + ": " + max);

    }

    public void mostrarPromedioVida(ArrayList<Soldado> ejercito, int idEjercito) {
        int suma=0;
        int contador=0;
        for(Soldado s: ejercito){
            suma+= s.getVida();
            contador++;
        }
        double promedio= (double) suma/contador;
        System.out.println("El promedio de la vida del ejercito "+idEjercito+" es de "+promedio);
    }

    public void mostrarOrdenCreacion(ArrayList<Soldado> ejercito, int idEjercito) {
       System.out.println("Ejercito numero "+idEjercito);
        for(Soldado s: ejercito){
        System.out.println(s);
       }
    }

    public void mostrarRanking(ArrayList<Soldado> ejercito, int idEjercito) {
        System.out.println("\nRanking Ejercito "+idEjercito+" (ordenacion)");
        ArrayList<Soldado> copia1 = new ArrayList<>(ejercito);
        burbuja(copia1);
        for (Soldado s : copia1) {
            System.out.println(s);
        }

        copia1.sort(Comparator.comparing(Soldado::getVida).reversed());
    }

    public void burbuja(ArrayList<Soldado> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (lista.get(j).getVida() < lista.get(j + 1).getVida()) {
                    Soldado repeticion= lista.get(j);
                     lista.set(j, lista.get(j+1)); 
                    lista.set(j+1, repeticion);
                }
            }
        }
    }

    public void Ganador() {
        int suma1=0, suma2=0;
        for (Soldado soldado : ejercito1) {
            suma1+= soldado.getVida();
        }
        for (Soldado soldado : ejercito2) {
            suma2+= soldado.getVida();
        }
        System.out.println("\nPoder total Ejército 1: " + suma1);
        System.out.println("Poder total Ejército 2: " + suma2);

        if (suma1 > suma2) {
            System.out.println("El Ejercito 1 gana la batalla");
        } else if (suma2 > suma1) {
            System.out.println("El Ejercito 2 gana la batalla");
        } else {
            System.out.println("Empate");
        }
    }
}