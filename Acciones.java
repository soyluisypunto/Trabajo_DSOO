public class Acciones {
    private Soldado soldado;
    private int nivelAtaque;
    private int nivelDefensa;
    private int velocidad;
    private boolean vive;
    private int vida_actual;
    private String actitud;

    public int getvida_actual() {
        return vida_actual;
    }
    public void setvida_actual(int vida_actual) {
        this.vida_actual = vida_actual;
    }
    public String getActitud() {
        return actitud;
    }
    public void setActitud(String actitud) {
        this.actitud = actitud;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Acciones(Soldado soldado) {
        this.soldado = soldado;
        this.nivelAtaque = 5; // Valor predeterminado
        this.nivelDefensa = 5; // Valor predeterminado
        this.velocidad = 0; // Valor predeterminado
        this.vive = true;
        this.vida_actual = soldado.getVida(); // Vida inicial
        this.actitud = "neutral"; // Actitud inicial
    }
    public void atacar() {
        if (vive){
            System.out.println("El soldado " + soldado.getNombre() + " ataca con nivel de ataque " + nivelAtaque);
            actitud = "agresivo";
        } else {
            System.out.println("El soldado " + soldado.getNombre() + " no puede atacar porque está muerto.");
        }
    }

    public void defender() {
        if (vive){
            System.out.println("El soldado " + soldado.getNombre() + " se defiende con nivel de defensa " + nivelDefensa);
            actitud = "defensivo";
        } else {
            System.out.println("El soldado " + soldado.getNombre() + " no puede defenderse porque está muerto.");
        }
    }
    public void avanzar(){
        if (vive){
            velocidad += 1;
            System.out.println("El soldado " + soldado.getNombre() + " avanza. Velocidad actual: " + velocidad);
            actitud = "avanzando";
        } else {
            System.out.println("El soldado " + soldado.getNombre() + " no puede avanzar porque está muerto.");
        }
    }
    public void retroceder(){
        if (vive && velocidad > 0){
            velocidad -= 1;
            System.out.println("El soldado " + soldado.getNombre() + " retrocede. Velocidad actual: " + velocidad);
            actitud = "fuga";
        } else if (!vive) {
            System.out.println("El soldado " + soldado.getNombre() + " no puede retroceder porque está muerto.");
        } else {
            System.out.println("El soldado " + soldado.getNombre() + " ya está detenido y no puede retroceder más.");
        }
    }
    public void recibirDanio(int danio) {
        if (vive){
            vida_actual -= danio;
            System.out.println("El soldado " + soldado.getNombre() + " recibe " + danio + " de daño. Vida actual: " + vida_actual);
            if (vida_actual <= 0) {
                vive = false;
                vida_actual = 0;
                System.out.println("El soldado " + soldado.getNombre() + " ha muerto.");
                actitud = "muerto";
            } else {
                actitud = "herido";
            }
        } else {
            System.out.println("El soldado " + soldado.getNombre() + " ya está muerto y no puede recibir más daño.");
        }
    }
    public void huir(){
        if (vive){
            velocidad += 2;
            System.out.println("El soldado " + soldado.getNombre() + " está huyendo. Velocidad actual: " + velocidad);
            actitud = "fuga";
        } else {
            System.out.println("El soldado " + soldado.getNombre() + " no puede huir porque está muerto.");
        }
    }
    public boolean vive() {
        return vive;
    }
}