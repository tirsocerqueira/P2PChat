package callback;

import modelo.Accion;
import modelo.Alarma;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * This class implements the remote interface 
 * CallbackServerInterface.
 * @author M. L. Liu
 */

public class CallbackServerImpl extends UnicastRemoteObject implements CallbackServerInterface {
   private Vector clientList;
   private ArrayList<Alarma> alarmas;
   private ArrayList<Accion> ac;

   public CallbackServerImpl() throws IOException {
      super( );
     clientList = new Vector();
     alarmas=new ArrayList<>();
     ac=new ArrayList<>();
   }

  public String sayHello( )   
    throws java.rmi.RemoteException {
      return("hello");
  }

  public void setAccionesIniciales() throws IOException {
      Document doc = Jsoup.connect("https://www.bolsamadrid.es/esp/aspx/Mercados/Precios.aspx?indice=ESI100000000&punto=indice").get();
      Elements el = doc.select("#ctl00_Contenido_tblAcciones");
      Elements acciones = el.select("tr");
      acciones.remove(0);

      //Método para guardar los primeros datos
      for (Element accion : acciones) {

          Elements valores = accion.getAllElements();
          String precio_prov=valores.get(3).text().replaceAll(",",".");
          Accion accion1=new Accion(accion.getElementsByTag("a").text(),Float.parseFloat(precio_prov));
          ac.add(accion1);

      }
  }

    public void setAcciones() throws IOException {
        Document doc2 = Jsoup.connect("https://www.bolsamadrid.es/esp/aspx/Mercados/Precios.aspx?indice=ESI100000000&punto=indice").get();
        Elements el2 = doc2.select("#ctl00_Contenido_tblAcciones");
        Elements acciones = el2.select("tr");
        acciones.remove(0);
        //Implementar el método que actualice los valores
        for (Element accion : acciones) {

            Elements valores = accion.getAllElements();
            String precio_prov=valores.get(3).text().replaceAll(",",".");
            Accion accion1=new Accion(accion.getElementsByTag("a").text(),Float.parseFloat(precio_prov));
            for (Accion accion2: ac){
                if(accion1.getNombre().equals(accion2.getNombre())){
                    ac.get(ac.indexOf(accion2)).setPrecio(accion1.getPrecio());
                }
            }

        }
    }

    public void nuevaAlarma(int codigo,String nombre,Float precio,String accion){
       Alarma alarma=new Alarma(codigo,nombre,precio,accion);
       this.alarmas.add(alarma);
    }

    public void imprimeAcciones(){
       for(Accion accion : ac){
           System.out.println(accion.getNombre());
           System.out.println(accion.getPrecio());
       }
    }

    public String comprobarAlarma(int codigo_usuario){

       String msg= "";
        for (int i=0;i<ac.size();i++){
            for (int j=0;j<alarmas.size();j++){
                if (codigo_usuario==alarmas.get(j).getCodigoHash() && ac.get(i).getNombre().equals(alarmas.get(j).getNombre_accion())&& ac.get(i).getPrecio() <= alarmas.get(j).getPrecio() && alarmas.get(j).getAccion_usuario().equals("COMPRA")){
                    msg = msg + "SE REPRODUCE LA ALARMA POR LA ACCIÓN: " + alarmas.get(j).getNombre_accion() + "CON ACCION: " + alarmas.get(j).getAccion_usuario() + "POR EL PRECIO: " + alarmas.get(j).getPrecio() + " \n";
                    alarmas.remove(alarmas.get(j));

                }
                else if (codigo_usuario==alarmas.get(j).getCodigoHash() && ac.get(i).getNombre().equals(alarmas.get(j).getNombre_accion())&& ac.get(i).getPrecio() >= alarmas.get(j).getPrecio() && alarmas.get(j).getAccion_usuario().equals("VENTA")){
                    msg = msg + "SE REPRODUCE LA ALARMA POR LA ACCIÓN: " + alarmas.get(j).getNombre_accion() + " CON ACCION: " + alarmas.get(j).getAccion_usuario() + " POR EL PRECIO: " + alarmas.get(j).getPrecio() + " \n";
                    alarmas.remove(alarmas.get(j));

                }
            }
        }
        return msg;
    }

  public synchronized void registerForCallback(CallbackClientInterface callbackClientObject) throws java.rmi.RemoteException{
      // store the callback object into the vector
      if (!(clientList.contains(callbackClientObject))) {
         clientList.addElement(callbackClientObject);
         System.out.println("Registered new client ");
      //doCallbacks();
    } // end if
  }  

// This remote method allows an object client to 
// cancel its registration for callback
// @param id is an ID for the client; to be used by
// the server to uniquely identify the registered client.
  public synchronized void unregisterForCallback(CallbackClientInterface callbackClientObject) throws java.rmi.RemoteException{
    if (clientList.removeElement(callbackClientObject)) {
      System.out.println("Unregistered client ");
    } else {
       System.out.println(
         "unregister: client wasn't registered.");
    }
  } 

  public synchronized void doCallbacks( ) throws java.rmi.RemoteException{
       String msg ="";
    // make callback to each registered client
    System.out.println(
       "**************************************\n"
        + "Callbacks initiated ---");
    for (int i = 0; i < clientList.size(); i++){
      System.out.println("doing "+ i +"-th callback\n");    
      // convert the vector object to a callback object
      CallbackClientInterface nextClient = (CallbackClientInterface)clientList.elementAt(i);
      System.out.println(nextClient.hashCode());
      msg=comprobarAlarma(nextClient.hashCode());
      // invoke the callback method
        nextClient.notifyMe(msg);
    }// end for
    System.out.println("********************************\n" +
                       "Server completed callbacks ---");
  } // doCallbacks

}// end CallbackServerImpl class   
