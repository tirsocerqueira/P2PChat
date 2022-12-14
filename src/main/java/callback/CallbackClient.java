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
  private CallbackServerInterface servidor;
  private CallbackClientInterface interfazUsuario;
  private ArrayList<CallbackClientInterface> amigos;
  public CallbackClient(CallbackServerInterface servidor, String name, String pass) {
    try {
      this.amigos = new ArrayList<>();
      this.servidor = servidor;

      CallbackClientInterface callbackObj = new CallbackClientImpl(name,this);
      //Comprobamos si está registrado
      this.amigos=this.servidor.comprobarUsuario(callbackObj,name,pass);
      //Si está registrado se mete en la lista da callbacks
      String flag="active";

      //Mientras el usuario está conectado
      while(flag.equals("active")){






      }



      //Cuando queramos salimos y nos desconectamos
      this.servidor.unregisterForCallback(callbackObj);
      System.out.println("Unregistered for callback.");
      System.exit(0);
    } // end try
    catch (Exception e) {
      System.out.println(
              "Exception in CallbackClient: " + e);
    }
  }

  public boolean isLogged(){
    return this.amigos != null;
  }

  public void anadirAmigo(String message, CallbackClientInterface c){
    this.amigos.add(c);
    System.out.println(message);
  }
}