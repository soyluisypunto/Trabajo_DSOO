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

      
        System.out.println("\n--- Soldados del Ejército 1 ---");
        for (int i = 0; i < ejercito1.size(); i++) {
            System.out.println(i + ".- " + ejercito1.get(i));
        }

        System.out.println("\n--- Soldados del Ejército 2 ---");
        for (int i = 0; i < ejercito2.size(); i++) {
            System.out.println(i + ".- " + ejercito2.get(i));
        }


        System.out.print("\nSeleccione un ejército (1 o 2): ");
        int opcionEjercito = sc.nextInt();

        ArrayList<Soldado> ejercitoSeleccionado= null;

        if(opcionEjercito==1){
            ejercitoSeleccionado = ejercito1;
        }else if(opcionEjercito==2){
            ejercitoSeleccionado = ejercito2;
        }else{
            System.out.println("El ejercito que escogiste no existe");
        }

        System.out.print("Seleccione el número del soldado: ");
        int num_soldado = sc.nextInt();

        Soldado elegido = ejercitoSeleccionado.get(num_soldado);

        Acciones accion = new Acciones(elegido);

        int opcion;
        do {
            System.out.println("\nAcciones para " + elegido.getNombre() + ":");
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            System.out.println("3. Avanzar");
            System.out.println("4. Retroceder");
            System.out.println("5. Recibir daño");
            System.out.println("6. Huir");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 : accion.atacar(); break;
                case 2 : accion.defender(); break;
                case 3 : accion.avanzar(); break;
                case 4 : accion.retroceder(); break;
                case 5 : {
                    System.out.print("Ingrese cantidad de daño: ");
                    int dano = sc.nextInt();
                    accion.recibirDanio(dano); break;
                }
                case 6 : accion.huir(); break;
                case 0 : System.out.println("Saliendo..."); break;
                default : System.out.println("Opción inválida");
            }
        } while (opcion != 0);

        sc.close();
    }
}
