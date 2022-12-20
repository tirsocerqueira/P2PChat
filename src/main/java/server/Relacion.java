/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author tcerq
 */
public class Relacion {

    private String usuario1;
    private String usuario2;

    public Relacion(String usuario1,String usuario2){
        this.usuario1=usuario1;
        this.usuario2=usuario2;
    }

    /**
     * @return the usuario1
     */
    public String getUsuario1() {
        return usuario1;
    }

    /**
     * @param usuario1 the usuario1 to set
     */
    public void setUsuario1(String usuario1) {
        this.usuario1 = usuario1;
    }

    /**
     * @return the usuario2
     */
    public String getUsuario2() {
        return usuario2;
    }

    /**
     * @param usuario2 the usuario2 to set
     */
    public void setUsuario2(String usuario2) {
        this.usuario2 = usuario2;
    }


}
