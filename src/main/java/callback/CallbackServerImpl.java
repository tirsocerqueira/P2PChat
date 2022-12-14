package callback;

import modelo.Accion;
import modelo.Alarma;
import modelo.Grupo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

/**
 * This class implements the remote interface 
 * CallbackServerInterface.
 * @author M. L. Liu
 */

public class CallbackServerImpl extends UnicastRemoteObject implements CallbackServerInterface {
    private ArrayList<CallbackClientInterface> clientList;
    private ArrayList<Usuario>usuarios;

    public CallbackServerImpl() throws IOException {
        super( );
        clientList = new ArrayList<>();
        usuarios=new ArrayList<>();
        this.setUsersIniciales();
    }

    public String sayHello( )
            throws java.rmi.RemoteException {
        return("hello");
    }

    public synchronized boolean registerUser (String user,String pass) throws java.rmi.RemoteException{
        for (Usuario u : this.usuarios){
            if (u.getNombre().equals(user)){
                return false;
            }
        }
        Usuario nUser = new Usuario(user,pass);
        this.usuarios.add(nUser);

        try{
            FileWriter fw = new FileWriter("usuarios.txt");
            for (Usuario u : this.usuarios){
                fw.write(u.getNombre()+","+u.getPass()+"\r\n");
            }
            fw.close();
            return true;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }


    public synchronized void registerForCallback(CallbackClientInterface callbackClientObject) throws java.rmi.RemoteException{
        // store the callback object into the vector
        if (!(clientList.contains(callbackClientObject))) {
            clientList.add(callbackClientObject);
            System.out.println("Registered new client ");
            //System.out.println(callbackClientObject.getNombre());
            doCallbacks(callbackClientObject,callbackClientObject.getNombre());
        } // end if
    }

    public synchronized void unregisterForCallback(CallbackClientInterface callbackClientObject) throws java.rmi.RemoteException{
        if (clientList.remove(callbackClientObject)) {
            System.out.println("El usuario se ha desonectado ");
        } else {
            System.out.println(
                    "Desconexion errónea");
        }
    }

    public synchronized void doCallbacks(CallbackClientInterface c, String nombre) throws java.rmi.RemoteException{
        String msg ="";
        // make callback to each registered client
        //callback solo a los amigos del usuario que se registra
        for (int i = 0; i < clientList.size(); i++){
            System.out.println("doing "+ i +"-th callback\n");
            CallbackClientInterface nextClient = (CallbackClientInterface)clientList.get(i);
            for (int j=0;j<usuarios.size();j++) {
                if( usuarios.get(j).getAmigos().contains(nombre)) {
                    msg = "Se ha conectado el usuario: " + nombre;
                    nextClient.notifyMe(msg,c);
                }
            }

        }// end for

    } // doCallbacks

    public synchronized ArrayList<CallbackClientInterface> comprobarUsuario(CallbackClientInterface cliente, String name, String pass) throws RemoteException {
        ArrayList<CallbackClientInterface> amigos = new ArrayList<>();
        for(int i=0;i<this.usuarios.size();i++){
            if (this.usuarios.get(i).getNombre().equals(name) && this.usuarios.get(i).getPass().equals(pass)){
                //Los usuarios registrados en callback serán los usuarios conectados
                registerForCallback(cliente);
                //Obtener amigos
                for(String a  : this.usuarios.get(i).getAmigos()){
                    for(CallbackClientInterface u: this.clientList){
                        if(u.getNombre().equals(a)){
                            amigos.add(u);
                        }
                    }
                }
                return amigos;
            }
        }
        return null;
    }

    private void setUsersIniciales(){
        try{
            Scanner s = new Scanner(new File("usuarios.txt"));
            //Primero obtenemos los usuarios
            while(s.hasNextLine()){
                String[] u = s.nextLine().split(",");
                Usuario user = new Usuario(u[0], u[1]);
                this.usuarios.add(user);
            }

            s.close();
            //A continuación cargamos las listas de amigos
            s = new Scanner(new File("listasAmigos.txt"));

            while(s.hasNextLine()){
                String[] a = s.nextLine().split(",");
                for (Usuario u : this.usuarios) {
                    if(u.getNombre().equals(a[0])){
                        for(int i=1;i<a.length;i++){
                            u.addAmigo(a[i]);
                        }
                    }
                }
            }

            s.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found:" + e);
        }
    }
}// end CallbackServerImpl class   
