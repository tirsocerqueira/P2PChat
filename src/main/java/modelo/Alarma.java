package modelo;

public class Alarma {

    private int codigoHash;
    private String nombre_accion;
    private Float precio;
    private String accion_usuario; //Definimos si el autor quiere comprar o vender una acción

    public Alarma(int codigoHash, String nombre_accion, Float precio, String accion_usuario) {
        this.codigoHash = codigoHash;
        this.nombre_accion = nombre_accion;
        this.precio = precio;
        this.accion_usuario = accion_usuario;
    }

    public int getCodigoHash() {
        return codigoHash;
    }

    public void setCodigoHash(int codigoHash) {
        this.codigoHash = codigoHash;
    }

    public String getNombre_accion() {
        return nombre_accion;
    }

    public void setNombre_accion(String nombre_accion) {
        this.nombre_accion = nombre_accion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getAccion_usuario() {
        return accion_usuario;
    }

    public void setAccion_usuario(String accion_usuario) {
        this.accion_usuario = accion_usuario;
    }
}
