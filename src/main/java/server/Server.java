package server;


import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

/**
 * This class represents the object server for a distributed
 * object of class Callback, which implements the remote
 * interface CallbackInterface.
 * @author M. L. Liu
 */

public class Server  {

  public static void main(String args[]) {
    String registryURL;

    try{
      startRegistry(1099);
      ServerImpl exportedObj = new ServerImpl();
      registryURL = "rmi://localhost:1099/P2PChat";
      Naming.rebind(registryURL, exportedObj);
      System.out.println(exportedObj + "Callback Server ready.");
    }// end try
    catch (Exception re) {
      System.out.println("Exception in Server.main: " + re);
    } // end catch
  } // end main

  private static void startRegistry(int RMIPortNum)
          throws RemoteException{
    try {
      Registry registry =
              LocateRegistry.getRegistry(RMIPortNum);
      registry.list( );
      // This call will throw an exception
      // if the registry does not already exist
    }
    catch (RemoteException e) {
      // No valid registry at that port.
      Registry registry =
              LocateRegistry.createRegistry(RMIPortNum);
    }
  } // end startRegistry

} // end class
