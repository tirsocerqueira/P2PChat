package server;

import java.rmi.*;
import java.util.ArrayList;

/**
 * This is a remote interface for illustrating RMI
 * client callback.
 * @author M. L. Liu
 */

public interface ServerInterface extends Remote {


  public void registerForCallback(Usuario usuario)throws java.rmi.RemoteException;
  // This remote method allows an object client to
// cancel its registration for callback
  public void unregisterForCallback(Usuario usuario)throws java.rmi.RemoteException;
  public Usuario login (String usuario, String password)throws java.rmi.RemoteException;
}



