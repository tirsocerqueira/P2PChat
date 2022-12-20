/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import server.Usuario;

/**
 *
 * @author Usuario
 */
public interface ClientInterface
        extends java.rmi.Remote{

    public void recibirMensaje(String nombre, String mensage) throws java.rmi.RemoteException;
    public void conexionAmigo(Usuario amigo) throws java.rmi.RemoteException;
    public void desconexionAmigo(Usuario amigo) throws java.rmi.RemoteException;

} // end interface
