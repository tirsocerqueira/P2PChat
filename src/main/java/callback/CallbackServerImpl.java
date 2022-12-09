package callback;

import modelo.Accion;
import modelo.Alarma;
import modelo.Grupo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
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
        clientList = new ArrayList();
        usuarios=new ArrayList<>();
        this.setUsersIniciales(usuarios);
    }

    public String sayHello( )
            throws java.rmi.RemoteException {
        return("hello");
    }

    public synchronized boolean registerUser (CallbackClientInterface client,String user,String pass){

        for (int i=0;i<clientList.size();i++){
            if (clientList.get(i).equals(client)){
                return false;
            }
        }
        clientList.add(client);
        return true;
    }


    public synchronized void registerForCallback(CallbackClientInterface callbackClientObject) throws java.rmi.RemoteException{
        // store the callback object into the vector
        if (!(clientList.contains(callbackClientObject))) {
            clientList.add(callbackClientObject);
            System.out.println("Registered new client ");
            //System.out.println(callbackClientObject.getNombre());
            doCallbacks(callbackClientObject.getNombre());
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

    public synchronized void doCallbacks(String nombre) throws java.rmi.RemoteException{
        String msg ="";
        // make callback to each registered client
        for (int i = 0; i < clientList.size(); i++){
            System.out.println("doing "+ i +"-th callback\n");
            CallbackClientInterface nextClient = (CallbackClientInterface)clientList.get(i);
            for (int j=0;j<usuarios.size();j++) {
                if( usuarios.get(j).getAmigos().contains(nombre)) {
                    msg = "Se ha conectado el uuario: " + nombre;
                    nextClient.notifyMe(msg);
                }
            }

        }// end for

    } // doCallbacks

    public synchronized boolean comprobarUsuario(CallbackClientInterface cliente) throws RemoteException {

        for(int i=0;i<usuarios.size();i++){
            if (usuarios.get(i).getNombre().equals(cliente.getNombre()) && usuarios.get(i).getPass().equals(cliente.getPass())){
                //Los usuarios registrados en callback serán los usuarios conectados
                registerForCallback(cliente);
                return true;
            }
        }
        return false;
    }

    private void setUsersIniciales(ArrayList<Usuario> usuarios){

        Usuario tirso=new Usuario("tirso","1234",new ArrayList<>(),new ArrayList<>());
        Usuario iago=new Usuario("iago","1234",new ArrayList<>(),new ArrayList<>());
        Usuario juan=new Usuario("juan","1234",new ArrayList<>(),new ArrayList<>());
        Usuario pedro=new Usuario("pedro","1234",new ArrayList<>(),new ArrayList<>());

        //Añadimos usuarios de prueba
        usuarios.add(tirso);
        usuarios.add(iago);
        usuarios.add(juan);
        usuarios.add(pedro);

        //Añadimos usuarios a grupo
        tirso.addGrupo("A");
        iago.addGrupo("A");
        juan.addGrupo("B");
        pedro.addGrupo("B");

        //Añadimos amigos
        tirso.addAmigo(iago.getNombre());
        iago.addAmigo(tirso.getNombre());
        juan.addAmigo(pedro.getNombre());
        pedro.addAmigo(juan.getNombre());
    }
}// end CallbackServerImpl class   
