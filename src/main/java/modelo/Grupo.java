package modelo;

import callback.Usuario;

import java.util.ArrayList;

public class Grupo {

    private String nombre_grupo;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Usuario> usuarios_conectados;

    public Grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
        this.usuarios = new ArrayList<>();
        this.usuarios_conectados = new ArrayList<>();
    }

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Usuario> getUsuarios_conectados() {
        return usuarios_conectados;
    }

    public void setUsuarios_conectados(ArrayList<Usuario> usuarios_conectados) {
        this.usuarios_conectados = usuarios_conectados;
    }

    public void addUsers(Usuario usuario){
        usuarios.add(usuario);
    }

    public void addUsersConnected(Usuario usuario){
        usuarios.add(usuario);
    }
}
