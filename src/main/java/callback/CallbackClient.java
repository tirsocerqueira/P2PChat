package callback;

import java.io.*;
import java.rmi.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
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
  private ArrayList<CallbackClientInterface> amigos;
  public CallbackClient() {
    try {
      this.amigos = new ArrayList<>();
      Scanner sc = new Scanner(System.in);

      String registryURL = "rmi://localhost:1099/P2PChat";
      Boolean comprobacion;
      // find the remote object and cast it to an
      //   interface object
      CallbackServerInterface h = (CallbackServerInterface)Naming.lookup(registryURL);
      System.out.println("Lookup completed " );

      //LÓGICA DE LOGIN
      System.out.println("Escriba su usuario: ");
      String name = sc.nextLine();
      System.out.println("Escriba su contraseña: ");
      String pass = sc.nextLine();


      CallbackClientInterface callbackObj = new CallbackClientImpl(name,this);
      //Comprobamos si está registrado
      comprobacion=h.comprobarUsuario(callbackObj,name,pass);
      //Si está registrado se mete en la lista da callbacks
      String flag="active";

      //Mientras el usuario está conectado
      while(flag.equals("active")){






      }



      //Cuando queramos salimos y nos desconectamos
      h.unregisterForCallback(callbackObj);
      System.out.println("Unregistered for callback.");
      System.exit(0);
    } // end try
    catch (Exception e) {
      System.out.println(
              "Exception in CallbackClient: " + e);
    }
  }

  public void anadirAmigo(String message, CallbackClientInterface c){
    this.amigos.add(c);
    System.out.println(message);
  }

  public static void main(String[] args) {
    CallbackClient c = new CallbackClient();
  }
}