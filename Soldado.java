public class Soldado {
    private String nombre;
    private int vida;
    private int fila;
    private int columna;
    private int ejercito;

public Soldado(String nombre, int vida, int fila , int columna, int ejercito){
    this.nombre=nombre;
    this.vida=vida;
    this.fila=fila;
    this.columna=columna;
    this.ejercito=ejercito;
    }
    public String getNombre(){
    return nombre;
    }
    public int getVida(){
        return vida;
    }
    public int getFila(){
        return fila;
    }
    public int getColumna(){
        return columna;
    }
    public int getEjercito() {
        return ejercito;
    }
    public String toString() {
    return nombre + " (vida=" + vida + ", fila=" + fila + ", col=" + columna + ")";
    }
}

