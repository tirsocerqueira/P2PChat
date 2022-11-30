package callback;

import java.util.Scanner;

public class ThreadScan extends Thread{

    private String accion;
    private Float precio;
    private String accion_usuario;
    public ThreadScan(String accion,Float precio,String accion_usuario) {
        this.accion=accion;
        this.precio=precio;
        this.accion_usuario=accion_usuario;
    }

    @Override
    public void run(){

        System.out.println("Introduce la nombre: ");
        Scanner scanner = new Scanner(System.in);
        setAccion(scanner.nextLine());
        System.out.println("Introduce el precio: ");
        setPrecio(Float.parseFloat(scanner.nextLine()));
        System.out.println("Introduce la accion: ");
        setAccion_usuario(scanner.nextLine());
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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
