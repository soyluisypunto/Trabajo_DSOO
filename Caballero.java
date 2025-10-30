public class Caballero extends Soldado {
    private String arma;
    private boolean montado;

    public Caballero(String nombre, int vida, int fila, int columna, int ejercito, String arma) {
        super(nombre, vida, fila, columna, ejercito);
        this.arma = "espada";
        this.montado = false;
    }
    public String getArma(){
        return arma;
    }
    public void setArma(String arma){
        this.arma = arma;
    }

    public void montar() {
        if (!montado) {
            montado = true;
            arma = "lanza";
            System.out.println(getNombre() + " monta su caballo y cambia su arma a lanza.");
            envestir();
        } else {
            System.out.println(getNombre() + " ya está montado.");
        }
    }

    public void desmontar() {
        if (montado) {
            montado = false;
            arma = "espada";
            System.out.println(getNombre() + " desmonta y se prepara para defender.");
        } else {
            System.out.println(getNombre() + " ya está desmontado.");
        }
    }

    public void envestir() {
        if (montado) {
            System.out.println(getNombre() + " realiza una envestida montado (3 ataques).");
        } else {
            System.out.println(getNombre() + " realiza una envestida desmontado (2 ataques).");
        }
    }

    @Override
    public void habilidad_particular() {
        envestir();
    }
}