/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.Sbin.DAO;

import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.*;
import org.Nicon.Personal.Data.DataContact;
import org.Nicon.Personal.LibCore.Obj.NiContact;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Sbin.NiConection;
import org.Nicon.Personal.LibCore.Sbin.NiconFileAdministrator;

/**
 *
 * @author frederick
 */
public class NiContactsDAO {

    private NiContact Contact;
    private String sentence;
    private String NameFile;
    
    private ResultSet Data;
    private ArrayList ListContacts;
    
    private int ContactID;
    private int count;
    private int ExecuteSentence;
    
    private File NiconFile;
    private FileWriter NiconWriter;
    private NiconFileAdministrator NCFadmin;
    private boolean state;
    

    /**
     * Este constructor inicia el ArrayList que contendrá el listado de contactos del backend
     * 
     */
    public NiContactsDAO() {
        ListContacts = new ArrayList();
    }

    /**
     * Este constructor es usado para incializar el contacto al que se le ejecutará la accion
     * en la fuente de datos
     * @param Contact
     * @author Frederick Adolfo Salazar Sanchez
     * 
     */
    public NiContactsDAO(NiContact Contact) {
        this.Contact = Contact;
    }

    
    /**
     * Este metodo permite la inserccion de un objeto NiContact a el FronEnd SQlite, haciendo 
     * uso de las sentencia sql. este metodo hce parte del nucleo NiconLibCore y por tanto
     * no tiene contacto alguno con el usuario ejecutor del programa.
     * @return boolean state 
     * @author Frederick Adolfo Salazar Sanchez 
     */
    public boolean createContact() {
        
        try {
            sentence = "Insert into contactos values(" + Contact.getCodigo() + ",'" + Contact.getNombres() + "','" + Contact.getApellidos() + "','" + Contact.getApodo() + "','" + Contact.getDireccion() + "','" + Contact.getCiudad() + "','" + Contact.getCelular() + "','" + Contact.getFijo() + "','" + Contact.getEmail() + "','" + Contact.getFecha_nac() + "','" + Contact.getGrupo() + "');";
            System.out.println("Iniciando creación de contacto ...");
            ExecuteSentence = NiConection.ExecuteSentence(sentence);
            if (ExecuteSentence == 0) {
                System.out.println("El contacto ha sido creado exitosamente ...\n" + Contact.toString());
                state=true;
            } else {
                System.out.println("un error ocurrio al intentar crear el contacto");
                state=false;
            }
            NiConection.DisconectDB();
        } catch (Exception e) {
            System.out.println("Ocurrio el siguiente error en CreateContact() \n" + e);
        }
        return state;
    }

    
    /**
     * Este metodo permite la eliminacion de un objeto NiContact de el FrontEnd SQLite. lo hace
     * a traves de la sentencia SQL, al hacer la ejecución recibe un codigo int el cual especifica
     * si se ejecutó o no la orden. 
     * 
     * @author Frederick Adolfo Salazar Sanchez
     * @return boolean state representa el estado de ejecucion de las ordenes de eliminacion
     */
    public boolean deleteContact() {
        try {
            sentence = "delete from contactos where Codigo= " + Contact.getCodigo() + " and Nombres='" + Contact.getNombres() + "';";
            ExecuteSentence = NiConection.ExecuteSentence(sentence);
            if (ExecuteSentence == 0) {
                state=true;
            } else {
                state=false;
            }
            NiConection.DisconectDB();
        } catch (Exception e) {
            System.out.println("Ocurrio el siguiente ERROR en Contacto.DeleteContact() detail: " + e);
        }
        return state;
    }

    /*
     * Este metodo permite actualizar la informacion de un contacto, la actualizacion se hace en el 
     * Backend SQLIte mediante la sentencia de sql, recibe como parametros el IDcontacts, los nombres
     * el campo a actualizar y el nnuevo dato.
     * no retorna ningun parametro.
     * 
     * @param codigo
     * @param nombres
     * @param campo
     * @param dato
     */
    public void updateContact(int codigo, String nombres, String campo, String dato) {
        try {
            sentence = "update contactos set " + campo + "='" + dato + "' where Codigo=" + codigo + " and Nombres='" + nombres + "';";
            System.out.println(sentence);
            ExecuteSentence = NiConection.ExecuteSentence(sentence);
            if (ExecuteSentence == 0) {
                state=true;
            } else {
                state=false;
            }
        } catch (Exception e) {
            System.out.println("A error had ocurred in NiContactsDAO.update() detail error:\n" + e.getStackTrace());
        }
    }

   
    /**
     * Este metodo permite obtener mediante el controlador JDBC todos los contactos almacenados
     * en el Backend SQLite. y los carga a un ArrayList que contiene todos los Objetos
     * NiContact. 
     * 
     * @return ArrayList ListContacts con todos los contactos 
     * @author Frederick Adolfo Salazar Sanchez
     */
    public ArrayList getAllContact() {
        try {
            count = 0;
            sentence = "select * from contactos;";
            Data = NiConection.RestoreDataSentence(sentence);
            while (Data.next()) {
                Contact = new NiContact(Data.getInt(1), Data.getString(2), Data.getString(3), Data.getString(4), Data.getString(5), Data.getString(6), Data.getString(7), Data.getString(8), Data.getString(9), Data.getString(10), Data.getString(11));
                ListContacts.add(count, Contact);
                count += 1;
            }
            NiConection.DisconectDB();
            Data.close();
            Contact=null;
        } catch (Exception e) {
            System.out.println("Ocurrió un error en NiContactsDAO.getAllContacts() detail error:\n"+e.getStackTrace());
        }
        return ListContacts;
    }
    
    /**
     *  Este metodo permite cargar todos los contactos almcenados en los diferentes grupos determinados
     *  del sistema.
     *  @param String Group este parametro recibe el grupo que se quiere cargar en el arrayList
     *  @return ArrayList ListContacts con todos los objetos NiContact del grupo recibido
     * @author Frederick Adolfo Salazar Sanchez
     */
    public ArrayList getContactsByGroup(String Group){
        try{
            count = 0;
            sentence = "select * from contactos where Grupo='"+Group+"';";
            Data = NiConection.RestoreDataSentence(sentence);
      
                while (Data.next()) {
                    Contact = new NiContact(Data.getInt(1), Data.getString(2), Data.getString(3), Data.getString(4), Data.getString(5), Data.getString(6), Data.getString(7), Data.getString(8), Data.getString(9), Data.getString(10), Data.getString(11));
                    ListContacts.add(Contact);
                    count += 1;
                }
                NiConection.DisconectDB();
                Data.close();
                Contact=null;                
        } catch (Exception e) {
            System.out.println("Ocurrio el siguiente Error en Contacto.GetGroupFamilia() detail: " + e);
        }
        return ListContacts;
    }

    /**
     * Este metodo se encarga de generar el ContactID para cada objeto NiContact, no recibe 
     * parametros pero usa el Objeto NiContact recibido en el metodo constructor definido.
     * @return int ContactID con el codigo de el objeto NiContact
     * @author Frederick Adolfo Salazar Sanchez
     */
    public int createContactID() {
        Contact = DataContact.getLastContact();            
            if(Contact==null){
                ContactID=1;
            }else{            
                ContactID = Contact.getCodigo() + 1;
                Contact = null;        
            }
        return ContactID;
    }   
    
    /*
     * este metodo tiene como finalidad permitir exportar la informacion de un contacto recibido
     * a un archivo en formato de texto de plano en la ruta de exportaciones. retorna el valor 
     * booleano true en caso de que la operacion sea exitosa y false en caso de no terminarla
     * 
     * Creado por: Frederick Adolfo Salazar Sanchez
     */
    /**
     * 
     */
    public void exportContactToFileTxt() {
        try {
            if(Contact==null){
                JOptionPane.showMessageDialog(null, "El Objeto contacto recibido es inválido, verifique e intente de nuevo", GlobalConfigSystem.getTitleAplication(), JOptionPane.ERROR_MESSAGE);
            }else{
                NameFile=Contact.getNombres()+"_"+Contact.getApellidos()+".txt";
                NCFadmin=new NiconFileAdministrator(GlobalConfigSystem.getExportPath(),NameFile,Contact.toStringExport());
                NCFadmin.writeFileText();
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error en NiContactsDAO.exportContactToTxt()");
            e.printStackTrace();
        }
    }
       
    public void exportAllContactsToTxt(){
        
    }
    
    /*
     * Este metodo tiene como finalidad crear un archivo con una copia de seguridad de
     * todos los contactos almacenados dentro de la base de datos. este archivo de objetos
     * podrá ser luego precargado y realmacenado en la base de datos o bakend.
     */
    public void cretaeBackupNiContacts(){
        
    }
}
