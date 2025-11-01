public class Lancero extends Soldado {
    private int longitudLanza;
    private int nivelDefensa;

    public Lancero(String nombre, int vida, int fila, int columna, int ejercito, int longitudLanza, int nivelAtaque, int nivelDefensa) {
        super(nombre, vida, fila, columna, ejercito, nivelAtaque, nivelDefensa);
        this.longitudLanza = longitudLanza;
        this.nivelDefensa = 1;
    }

    public int getLongitudLanza() {
        return longitudLanza;
    }

    public void setLongitudLanza(int longitudLanza) {
        this.longitudLanza = longitudLanza;
    }

    @Override
    public void habilidad_particular() {
        nivelDefensa++;
        System.out.println(getNombre() + " forma un Schiltrom. Su defensa aumenta a " + nivelDefensa);
    }
}
