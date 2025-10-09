import java.util.*;

public class Videojuego3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        crear_tablero tabla = new crear_tablero();
        tabla.inicializarTablero();

        int n1= (int) (Math.random()*10+1);
        int n2= (int) (Math.random()*10+1);
        
        ArrayList<Soldado> ejercito1 = tabla.crearEjercito("Ejercito 1", n1, "X1", 1);
        ArrayList<Soldado> ejercito2 = tabla.crearEjercito("Ejercito 2", n2, "X2", 2);

        
        tabla.mostrarTablero();

      
        System.out.println("\n--- Soldados del Ejército 1 (A)---");
        for (int i = 0; i < ejercito1.size(); i++) {
            System.out.println(i + ".- " + ejercito1.get(i));
        }

        System.out.println("\n--- Soldados del Ejército 2 (B)---");
        for (int i = 0; i < ejercito2.size(); i++) {
            System.out.println(i + ".- " + ejercito2.get(i));
        }
        int turno= (int) ((Math.random()*2)+1);
        while(!tabla.ejercitoVacio(ejercito1) && !tabla.ejercitoVacio(ejercito2)){
            System.out.println();
            System.out.println("-----Turno del ejercito ("+turno+")-----");
            tabla.mostrarTablero();
            System.out.println("Ingrese la fila del soldado a mover (0-9)");
            int fila=sc.nextInt();
            System.out.println("Ingrese la columna del soldado a mover (0-9)");
            int columna=sc.nextInt();

            System.out.println("Ingrese dirección (arriba,abajo,derecha,izquierda)");
            String movimiento=sc.next().toLowerCase();

            tabla.moverSoldado(turno,fila,columna,movimiento,sc);

            if(turno==1){
                turno=2;
            }else{
                turno=1;
            }
        }
        if (tabla.ejercitoVacio(ejercito1))
            System.out.println("Gana el ejercito 2");
        else
            System.out.println("Gana el ejercito 1");
        sc.close();
    }
}
