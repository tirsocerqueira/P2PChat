package modelo;

public class Accion {

    private String nombre;
    private String simbolo;
    private Float valor;

    public Accion(String nombre, String simbolo, Float valor) {
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
