package callback;

import callback.CallbackClientInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CallbackClientImpl extends UnicastRemoteObject implements CallbackClientInterface {

   private String nombre;
   private CallbackClient cliente;
   public CallbackClientImpl(String nombre, CallbackClient cliente) throws RemoteException {
      super( );
      this.nombre=nombre;
      this.cliente = cliente;
   }
   public void enviarMensaje(String emisor, String mensaje) throws RemoteException {

   }
   public void notifyMe(String message, CallbackClientInterface c){
      this.cliente.anadirAmigo(message, c);
   }

   public String getNombre() {
      return nombre;
   }

}// end CallbackClientImpl class
