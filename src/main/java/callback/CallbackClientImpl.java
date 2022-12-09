package callback;

import callback.CallbackClientInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallbackClientImpl extends UnicastRemoteObject implements CallbackClientInterface {

   private String nombre;
   private String pass;
   public CallbackClientImpl(String nombre,String pass) throws RemoteException {
      super( );
      this.nombre=nombre;
      this.pass=pass;

   }
   public void enviarMensaje(String emisor, String mensaje) throws RemoteException {

   }
   public String notifyMe(String message){
      System.out.println(message);
      return message;
   }

   public String getNombre() {
      return nombre;
   }

   public String getPass() {
      return pass;
   }

}// end CallbackClientImpl class
