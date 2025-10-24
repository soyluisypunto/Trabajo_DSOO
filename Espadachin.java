public class Espadachin extends Soldado{
    private int Longitud_Espada;
    private Acciones accion;
    public Espadachin(String nombre, int vida, int fila , int columna, int ejercito){
        super(nombre, vida, fila, columna, ejercito);
    }


    @Override
    public void habilidad_particular(){
        System.out.println("Creando muro de defensa");
        accion.setActitud("Defensivo");
    }
}
