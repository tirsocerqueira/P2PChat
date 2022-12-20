package server;

import java.util.ArrayList;

import client.ClientInterface;
import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable{
    private String nombre;
    private ClientInterface interf;

    public Usuario(String nombre){
        this.nombre = nombre;
        this.interf = null;
    }

    public String getNombre(){
        return this.nombre;
    }

    public ClientInterface getIface(){
        return this.interf;
    }

    public void setIface(ClientInterface iface){
        this.interf = iface;
    }



}
