/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.Data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Sbin.DAO.NiContactsDAO;
import org.Nicon.Personal.LibCore.Obj.NiContact;

/**
 *
 * @author frederick
 * 
 */

/*
 * el objetivo de esta clase es ser la interface unica de datos de NiContacts y el frontend, a traves de esta
 * clase todas las demas vistas del sistema tendran acceso a la misma informacion en una plataforma unica.
 */
public class DataContact {
    
    private static ArrayList ListContacts;
    private static String sentence;
    private static ResultSet Data;
    
    private static NiContact contact;
    private static NiContactsDAO NiContactDAO;
    private static Iterator iterator;
    private static String Comparator;
    
    public DataContact(){
       
    }
    
    /*
     * este metodo se encarga de obtener toda la informacion de contactos almacenada en el backend sqlite
     * sera usado por todas las clases u objetos que necesiten de esta informacion.
     */
    public static void init(){
        try{
            NiContactDAO=new NiContactsDAO();
            ListContacts=NiContactDAO.getAllContact();           
            System.out.println("El Array de NiContacts ha sido cargado exitosamente total Size: "+ListContacts.size());
        }catch(Exception e){
           System.out.println("Ocurrio un error en DataContact.init() Detail ERROR \n"+e+" \n"+e.getCause());         
        }
    }
    
    /*
     * este metodo se encarga de agregar el nuevo contacto al arrayList de datos
     */
    public static void addContact(NiContact contact){
        ListContacts.add(contact);
        NiContactDAO=new NiContactsDAO(contact);
        NiContactDAO.createContact();
        NiContactDAO=null;
    }
    
    
    /*
     * este metodo se encarga de eliminar un contacto de la lista de contactos y del array de datos en
     * memoria
     */
    public static void deleteContact(int index){
        contact=(NiContact) ListContacts.get(index);
        int response=JOptionPane.showConfirmDialog(null, "El siguiente contacto va a ser eliminado, desea continuar?\n\n"+contact.toString(), GlobalConfigSystem.getTitleAplication(), JOptionPane.WARNING_MESSAGE);
        
            if(response==0){
                NiContactDAO=new NiContactsDAO(contact);
                NiContactDAO.deleteContact();
                ListContacts.remove(index);
                NiContactDAO=null;
            }
    }    
    
    /*
     * este metodo obtiene un bjeto de tipo NiContact almacenado en el ArrayList de contactos.
     * recibe como parametros el Indice (Posision) en la que se encuentra con respecto al modelo
     * de datos. retorna un Objeto NiContacts.
     * 
     * Creado Por:  Frederick Adolfo Salazar Sanchez
     */
    public static NiContact getContact(int index){
            
            try{
                if(index<0||index>ListContacts.size()){
                    JOptionPane.showMessageDialog(null, "El indice recibido esta fuera de rango, no se puede ejecutar la tarea.", GlobalConfigSystem.getTitleAplication(), JOptionPane.ERROR_MESSAGE);
                    contact=null;
                }else{
                   contact=(NiContact) ListContacts.get(index); 
                }                
            }catch(Exception e){
                System.out.println("Ocurrio el siguiente error en DataContact.getContact(int Index) detail Error:\n"+e.toString());
            }
        return contact;
    }
       
    
    /*
     * este metodo obtiene el ultimo contacto almacenado en el arrayList, no recie parametros y
     * retorna un objeto de Tipo NiContacts.
     */
    public static NiContact getLastContact(){       
            if(ListContacts.isEmpty()){
                contact=null;
            }else{
               contact=(NiContact) ListContacts.get(ListContacts.size()-1);
            }
           return contact;        
    }
    
    public static NiContact getFirstContact(){
        if(ListContacts.isEmpty()){
            contact=null;
        }else{
            contact=(NiContact) ListContacts.get(0);
        }
        return contact;
    }
        
    
    /*
     * Este metodo es el encargado de hacer la busqueda de informacion del contacto qe coincida
     * con los caracteres ingresados por el usuario en el NiconWidgetDesktop.
     * en versiones futuras podra buscar contactos por muchos mas parametros de busqueda.
     * 
     */
    public static NiContact SearchContact(String characters){
        int count=0;
        iterator=ListContacts.iterator();
        while(iterator.hasNext()){
            contact=(NiContact) ListContacts.get(count);
            Comparator=contact.getNombres()+" "+contact.getApellidos();
            if(Comparator.contains(characters)){
                System.out.println("Si en contro a:"+contact.getNombres());
                break;
            }
            count++;
        }
        return contact;        
    }
    
    
    /*
     * Este metodo se encarga de clonar el arrayList de contactos para crear una nueva instancia de el array
     * 
     */
    public static ArrayList CloneData(){
        return ListContacts;
    }
    
}
