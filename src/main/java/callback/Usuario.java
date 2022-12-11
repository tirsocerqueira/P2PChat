package callback;

import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String pass;
    //Guardamos grupos a los que pertenece
    private ArrayList<String>grupos;
    private ArrayList<String>amigos;

    public Usuario(String nombre, String pass, ArrayList<String> grupos,ArrayList<String>amigos) {
        this.nombre = nombre;
        this.pass = pass;
        this.grupos = grupos;
        this.amigos = amigos;
    }

    public Usuario(String nombre, String pass){
        this.nombre = nombre;
        this.pass = pass;
        this.amigos = new ArrayList<>();
        this.grupos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public ArrayList<String> getGrupos() {
        return grupos;
    }

    public void setGrupos(ArrayList<String> grupos) {
        this.grupos = grupos;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public ArrayList<String> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<String> amigos) {
        this.amigos = amigos;
    }

    public void addGrupo(String grupo){
        this.grupos.add(grupo);
    }

    public void addAmigo(String amigo){
        this.amigos.add(amigo);
    }

    @Override
    public String toString() {
        String txt = "Usuario: " + this.nombre + "\nAmigos: ";
        for (String u : this.amigos) {
            txt += u + ", ";
        }
        return txt;
    }
}
