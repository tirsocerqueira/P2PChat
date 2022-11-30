package callback;

import java.io.*;
import java.rmi.*;
import java.util.Scanner;

/**
 * This class represents the object client for a
 * distributed object of class CallbackServerImpl, 
 * which implements the remote interface 
 * CallbackServerInterface.  It also accepts callback
 * from the server.
 * 
 *
 * 
 * @author M. L. Liu
 */

public class CallbackClient {

  public static void main(String args[]) {
    try {
      int RMIPort;         
      String hostName;
      InputStreamReader is = 
        new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);
      System.out.println("Enter the RMIRegistry host namer:");
      hostName = br.readLine();
      System.out.println("Enter the RMIregistry port number:");
      String portNum = br.readLine();
      RMIPort = Integer.parseInt(portNum); 
      System.out.println("Enter how many seconds to stay registered:");
      String timeDuration = br.readLine();
      int time = Integer.parseInt(timeDuration);
      String registryURL = "rmi://localhost:" + portNum + "/IBEXTrade";
      // find the remote object and cast it to an 
      //   interface object
      CallbackServerInterface h = (CallbackServerInterface)Naming.lookup(registryURL);
      System.out.println("Lookup completed " );

      CallbackClientInterface callbackObj = new CallbackClientImpl();
      // register for callback
      h.registerForCallback(callbackObj);
      System.out.println("Registered for callback.");
      String salida="";
      //h.nuevaAlarma("ACCIONA", (float) 180.4,"VENTA");
      while(!salida.equals("s")) {
        ThreadScan scan = new ThreadScan("", (float) 0, "");
        scan.run();
        h.nuevaAlarma(callbackObj.hashCode(),scan.getAccion(), scan.getPrecio(), scan.getAccion_usuario());
        salida=scan.getAccion();
      }
        //DORMIR PROCESO
        //try {
          //Thread.sleep(time * 1000);
        //}
        //catch (InterruptedException ex){ // sleep over
        //}
      h.unregisterForCallback(callbackObj);
      System.out.println("Unregistered for callback.");

    } // end try 
    catch (Exception e) {
      System.out.println(
        "Exception in CallbackClient: " + e);
    } // end catch
  } //end main
}//end class
