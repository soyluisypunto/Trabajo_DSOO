public class Espadachin extends Soldado{
    private int Longitud_Espada;
    public Espadachin(String nombre, int vida, int fila , int columna, int ejercito, int Longitud_Espada, int nivelAtaque, int nivelDefensa){
        super(nombre, vida, fila, columna, ejercito, nivelAtaque, nivelDefensa);
        this.Longitud_Espada= Longitud_Espada;
    }
     public int getLongitudEspada() {
        return  Longitud_Espada;
    }

    public void setLongitudEspada(int Longitud_Espada) {
        this.Longitud_Espada= Longitud_Espada;
    }

    @Override
    public void habilidad_particular(){
       System.out.println(getNombre() + " crea un muro de escudos como defensa.");
    }
}
