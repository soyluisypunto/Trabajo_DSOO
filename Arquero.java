public class Arquero extends Soldado{
    private int flechas;
    public Arquero(String nombre, int vida, int fila, int columna, int ejercito, int flechas){
        super(nombre, vida, fila, columna, ejercito);
        this.flechas= flechas;
    }
     public int getFlechas() {
        return flechas;
    }

    public void setFlechas(int flechas) {
        this.flechas = flechas;
    }
    @Override
    public void habilidad_particular(){
        if(flechas>0){
            flechas--;
            System.out.println(getNombre()+" disparando una flecha.\nFlechas restantes: "+flechas);
        } else{
            System.out.println("No quedan flechas para "+getNombre());
        }
    }
}