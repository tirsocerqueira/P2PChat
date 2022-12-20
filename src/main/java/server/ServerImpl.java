package server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import client.ClientInterface;

/**
 * This class implements the remote interface
 ServerInterface.
 * @author M. L. Liu
 */

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    // Declaramos la conexion a mysql

    private ArrayList<Usuario> conectados;
    private ArrayList<UsuarioBD> usuarios;
    private ArrayList<Relacion> relaciones;

    public ServerImpl() throws RemoteException {
        super( );
        conectados = new ArrayList();
        usuarios= new ArrayList();
        relaciones= new ArrayList();
        setUsuariosIniciales();
        relacionesIniciales();
        System.out.println(usuarios.size());
        // conectamos con la BD
    }

    @Override
    public synchronized void registerForCallback(
            Usuario usuario)
            throws java.rmi.RemoteException{
        if (!(conectados.contains(usuario))) {
            conectados.add(usuario);
            System.out.println(": Cliente " + usuario.getNombre() + " conectado.");
            notificarConexAmigos(usuario);
            notificarAmigosConectados(usuario);
        } // end if
    }

    // This remote method allows an object client to
// cancel its registration for callback
// @param id is an ID for the client; to be used by
// the server to uniquely identify the registered client.
    @Override
    public synchronized void unregisterForCallback(
            Usuario usuario)
            throws java.rmi.RemoteException{
        if (conectados.contains(usuario)) {
            conectados.remove(usuario);
            System.out.println("Cliente " + usuario.getNombre() + " desconectado.");
            notificarDesconexAmigos(usuario);
        } else {
            System.out.println(
                    "unregister: client wasn't registered.");
        }
    }

    private void notificarConexAmigos(Usuario usuario) throws java.rmi.RemoteException{

        for (Usuario usr: amigosConectados(usuario.getNombre())){
            if(!usr.equals(usuario)){
                ClientInterface nextClient = (ClientInterface)usr.getIface();
                nextClient.conexionAmigo(usuario);
            }
        }
    } // doCallbacks

    private void notificarDesconexAmigos(Usuario usuario) throws java.rmi.RemoteException{
        for (Usuario usr: amigosConectados(usuario.getNombre())){
            if(!usr.equals(usuario)){
                ClientInterface nextClient = (ClientInterface)usr.getIface();
                nextClient.desconexionAmigo(usuario);
            }
        }
    } // doCallbacks

    private void notificarAmigosConectados(Usuario usuario) throws java.rmi.RemoteException{
        ClientInterface client = (ClientInterface)usuario.getIface();
        for (Usuario usr: amigosConectados(usuario.getNombre())){
            if(!usr.equals(usuario)){
                client.conexionAmigo(usr);
            }
        }
    }


    @Override
    public Usuario login (String usuario, String password)throws java.rmi.RemoteException{
        //Compruebo si ya está logeado (evito que haya dos instancias con el mismo usuario
        for (Usuario conectado : conectados) {
            if (conectado.getNombre().equals(usuario)) {
                return null;
            }
        }

        for (int i=0;i<usuarios.size();i++){
            System.out.println(usuario);
            System.out.println(usuarios.get(i).getNombre());
            System.out.println(password);
            System.out.println(usuarios.get(i).getPass());
            if(usuarios.get(i).getNombre().contains(usuario) && usuarios.get(i).getPass().contains(password)){
                Usuario u = new Usuario(usuario);
                return u;
            }
        }

        return null;

    }


    private ArrayList<Usuario> amigosConectados(String usuario){
        ArrayList<Usuario> amigosConectados = new ArrayList<>();

        for (int i=0;i<relaciones.size();i++){
            if (!relaciones.get(i).getUsuario1().equals(usuario)) {
                for (Usuario usr : conectados) {
                    if (usr.getNombre().equals(relaciones.get(i).getUsuario1())) {
                        amigosConectados.add(usr);
                    }
                }
            }
            if (!relaciones.get(i).getUsuario2().equals(usuario)) {
                for (Usuario usr : conectados) {
                    if (usr.getNombre().equals(relaciones.get(i).getUsuario2())) {
                        amigosConectados.add(usr);
                    }
                }
            }
        }
        return amigosConectados;
    }




    private void setUsuariosIniciales(){

        UsuarioBD tirso=new UsuarioBD("tirso","1234");
        UsuarioBD iago=new UsuarioBD("iago","1234");
        UsuarioBD pedro=new UsuarioBD("pedro","1234");
        UsuarioBD juan=new UsuarioBD("juan","1234");

        usuarios.add(tirso);
        usuarios.add(iago);
        usuarios.add(pedro);
        usuarios.add(juan);
    }

    private void relacionesIniciales(){

        Relacion relacion1=new Relacion("tirso","iago");
        Relacion relacion2=new Relacion("tirso","pedro");
        Relacion relacion3=new Relacion("pedro","iago");
        Relacion relacion4=new Relacion("pedro","juan");

        relaciones.add(relacion1);
        relaciones.add(relacion2);
        relaciones.add(relacion3);
        relaciones.add(relacion4);
    }

}// end ServerImpl class


