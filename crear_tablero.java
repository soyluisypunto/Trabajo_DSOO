import java.util.*;

public class crear_tablero{
    private final int tamano = 10;
    private ArrayList<ArrayList<Soldado>> tablero = new ArrayList<>();

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

            int tipo = (int)(Math.random() * 4); // 0=Espadachin, 1=Arquero, 2=Caballero, 3=Lancero
            Soldado s;

            switch (tipo) {
                case 0: // Espadachin
                    vida = (int)(Math.random() * 3) + 8; 
                    s = new Espadachin("Espadachin " + i + prefijo, vida, fila, columna, idEjercito, (int)(Math.random() * 10 + 20),10,8);
                    break;
                case 1: // Arquero
                    vida = (int)(Math.random() * 3) + 3; 
                    s = new Arquero("Arquero " + i + prefijo, vida, fila, columna, idEjercito, (int)(Math.random() * 5 + 5),7,3);
                    break;
                case 2: // Caballero
                    vida = (int)(Math.random() * 3) + 10;
                    String arma = (Math.random() > 0.5) ? "Espada larga" : "Lanza corta";
                    s = new Caballero("Caballero " + i + prefijo, vida, fila, columna, idEjercito, arma,13,7);
                    break;
                default: // Lancero
                    vida = (int)(Math.random() * 4) + 5; 
                    s = new Lancero("Lancero " + i + prefijo, vida, fila, columna, idEjercito, (int)(Math.random() * 15 + 10),5,10);
                    break;
                }

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

    public void Ganador(ArrayList<Soldado> ejercito1, ArrayList<Soldado> ejercito2) {
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
    public boolean ejercitoVacio(ArrayList<Soldado> ejercito) {
        return ejercito.isEmpty();
    }
    public void moverSoldado(int turno, int fila, int columna, String movimiento,Scanner sc){
        Soldado s=tablero.get(fila).get(columna);
        if(s==null || s.getEjercito() !=turno){
            System.out.println("No hay un soldado en esta casilla");
            return;
        }
        int nueva_fila=fila;
        int nueva_columna=columna;

        switch (movimiento) {
                case "arriba": nueva_fila--;break;
                case "abajo":nueva_fila++;break;
                case "izquierda":nueva_columna--;break;
                case "derecha":nueva_columna++;break;
                default: System.out.println("Direccion erronea");return;
            }
        if (nueva_fila < 0 || nueva_fila >= 10 || nueva_columna < 0 || nueva_columna >= 10) {
            System.out.println("Movimiento fuera del tablero.");
            return;
        }
        Soldado destino= tablero.get(nueva_fila).get(nueva_columna);
        if (destino == null) {
            tablero.get(nueva_fila).set(nueva_columna, s);
            tablero.get(fila).set(columna, null);
            s.setFila(nueva_fila);
            s.setColumna(nueva_columna);
        } else if (destino.getEjercito() != turno) {
            batalla(s, destino, nueva_fila, nueva_columna,sc);
        } else {
            System.out.println("Ya hay un soldado aliado ahí.");
        }
    }
    public void batalla(Soldado s1, Soldado s2, int fila, int col,Scanner sc){
        int suma_vida= s1.getVida() + s2.getVida();
        int numero_aleatorio1, numero_aleatorio2;
        double porcentaje1, porcentaje2;
        do{
            numero_aleatorio1= (int) ((Math.random()*suma_vida)+1);
            numero_aleatorio2= suma_vida - numero_aleatorio1;
        }while(numero_aleatorio1 == numero_aleatorio2);
            porcentaje1 = ((double) numero_aleatorio1 / suma_vida) * 100;
            porcentaje2 = ((double) numero_aleatorio2 / suma_vida) * 100;
        System.out.println("------Numeros Aleatorios:------");
        System.out.println("El soldado "+s1.getNombre()+": "+numero_aleatorio1);
        System.out.println("El soldado "+s2.getNombre()+": "+numero_aleatorio2);
        System.out.println("------Probabilidades de ganar------");
        System.out.println("El soldado "+s1.getNombre()+": "+porcentaje1);
        System.out.println("El soldado "+s2.getNombre()+": "+porcentaje2);
        if (porcentaje1>porcentaje2) {
            System.out.println("Por lo tanto el soldado "+s1.getNombre()+" gana");
            s1.setVida(s1.getVida()+1);
            tablero.get(s2.getFila()).set(s2.getColumna(), s1);
            tablero.get(s1.getFila()).set(s1.getColumna(), null);
            System.out.println(s2.getNombre() + " ha muerto.");
            s2.setVida(0);
        } else {
            System.out.println("Por lo tanto el soldado "+s2.getNombre()+" gana");
            s2.setVida(s2.getVida()+1);
            tablero.get(s1.getFila()).set(s1.getColumna(), s2);
            tablero.get(s2.getFila()).set(s2.getColumna(), null);
            System.out.println(s1.getNombre() + " ha muerto.");
            s1.setVida(0);
        }
        System.out.println("Batalla finalizada.\n");
        }
}