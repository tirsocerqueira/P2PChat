package callback;

import java.rmi.*;
import java.rmi.server.*;

/**
 * This class implements the remote interface 
 * CallbackClientInterface.
 * @author M. L. Liu
 */

public class CallbackClientImpl extends UnicastRemoteObject implements CallbackClientInterface {
  
   public CallbackClientImpl() throws RemoteException {
      super( );
   }

   public String notifyMe(String message){
      System.out.println(message);
      return message;
   }      

}// end CallbackClientImpl class   
