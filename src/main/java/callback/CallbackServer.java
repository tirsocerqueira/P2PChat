package callback;

import modelo.Accion;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class represents the object server for a distributed
 * object of class Callback, which implements the remote 
 * interface CallbackInterface.
 * @author M. L. Liu
 */

public class CallbackServer  {
  public static void main(String args[]) {
    String  registryURL;
    try{     

      startRegistry(1099);
      CallbackServerImpl exportedObj = new CallbackServerImpl();
      registryURL = "rmi://localhost:1099/P2PChat";
      Naming.rebind(registryURL, exportedObj);
      System.out.println("Callback Server ready.");
    }// end try
    catch (Exception re) {
      System.out.println(
        "Exception in HelloServer.main: " + re);
    } // end catch
  } // end main

  //This method starts a RMI registry on the local host, if
  //it does not already exists at the specified port number.
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
