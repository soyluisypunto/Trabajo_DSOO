import java.util.Scanner;

public class Caballero extends Soldado{
    private String arma;
    public Caballero(String nombre, int vida, int fila, int columna, int ejercito){
        super(nombre, vida, fila, columna, ejercito);
    }
    public void habilidad_particular(){
        Scanner sc= new Scanner(System.in);
        int num= (int) (Math.random()*2+1);
        if(num==1){
            arma= "lanza";
            System.out.println("El caballero esta montando.Desea desmontar? S/N");
            char respuesta = sc.next().toLowerCase().charAt(0);
            if(respuesta=='s'){
                System.out.println("Cambiando arma");
                arma = "espada";
                
            }
        }
    }
}