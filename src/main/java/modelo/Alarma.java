package modelo;

public class Alarma {

    private String idUsuario;
    private String nombre_accion;
    private Float precio;
    private String accion_usuario; //Definimos si el autor quiere comprar o vender una acción

    public Alarma(String idUsuario, String nombre_accion, Float precio, String accion_usuario) {
        this.idUsuario = idUsuario;
        this.nombre_accion = nombre_accion;
        this.precio = precio;
        this.accion_usuario = accion_usuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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
