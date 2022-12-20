package client;


import java.rmi.*;
import java.rmi.server.*;
import server.Usuario;

/**
 * This class implements the remote interface
 ClientInterface.
 * @author M. L. Liu
 */

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {
   private final Cliente cliente;

   public ClientImpl(Cliente cliente) throws RemoteException {
      super( );
      this.cliente = cliente;
   }

   @Override
   public void recibirMensaje(String nombre, String mensage) throws java.rmi.RemoteException{
      this.cliente.recibirMensaje(nombre, mensage);
   }

   @Override
   public void conexionAmigo(Usuario amigo) throws java.rmi.RemoteException{
      this.cliente.conexionAmigo(amigo);
   }
   @Override
   public void desconexionAmigo(Usuario amigo) throws java.rmi.RemoteException{
      this.cliente.desconexionAmigo(amigo);
   }


}// end ClientImpl class
