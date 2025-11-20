import java.io.Serializable;
public class Soldado implements Serializable {
    private String nombre;
    private int vida;
    private int fila;
    private int columna;
    private int ejercito;
    private int nivelAtaque;
    private int nivelDefensa;

public Soldado(String nombre, int vida, int fila , int columna, int ejercito, int nivelAtaque, int nivelDefensa){
    this.nombre=nombre;
    this.vida=vida;
    this.fila=fila;
    this.columna=columna;
    this.ejercito=ejercito;
    this.nivelAtaque= nivelAtaque;
    this.nivelDefensa= nivelDefensa;
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
    public void setColumna(int columna){
        this.columna=columna;
    }
    public void setFila(int fila){
        this.fila=fila;
    }
    public void setVida(int vida){
        this.vida=vida;
    }
    public String toString() {
    return nombre + " (vida=" + vida + ", fila=" + fila + ", col=" + columna + ", nivelAtaque "+ nivelAtaque+ ", nivelDefensa "+nivelDefensa+" )";
    }
    public void habilidad_particular() {
    }
    public int getNivelAtaque(){
        return nivelAtaque;
    }
    public void setNivelAtaque(int nivelAtaque){
        this.nivelAtaque= nivelAtaque;
    }
    public int getNivelDefensa(){
        return nivelDefensa;
    }
    public void setNivelDefensa(int nivelDefensa){
        this.nivelDefensa= nivelDefensa;
    }
}

