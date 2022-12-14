package callback;

import modelo.Accion;

import java.rmi.*;
import java.util.ArrayList;

/**
 * This is a remote interface for illustrating RMI 
 * client callback.
 * @author M. L. Liu
 */

public interface CallbackServerInterface extends Remote {

  public String sayHello( )
          throws java.rmi.RemoteException;

  public boolean registerUser(
        String user,
        String pass
  ) throws java.rmi.RemoteException;

  public void unregisterForCallback(
          CallbackClientInterface callbackClientObject)
          throws java.rmi.RemoteException;

  public ArrayList<CallbackClientInterface> comprobarUsuario(CallbackClientInterface cliente, String name, String pass)
          throws RemoteException;
}


