/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.Data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import org.Nicon.Personal.LibCore.Sbin.DAO.NiconNotesDAO;
import org.Nicon.Personal.LibCore.Obj.NiconNotes;

/**
 *
 * @author frederick
 */
public class DataNotes {
    
    private static ArrayList ListNotes;
    private static String sentence;
    private static ResultSet Data; 
    private static NiconNotes notes;
    private static NiconNotesDAO NoteDAO;
    private static Iterator iterator;
    private static String comparador;
    
    private static boolean state;
    private static int response=-1;
    
    public static void Init(){
        int count=0;
        try{
           NoteDAO=new NiconNotesDAO();
           ListNotes=NoteDAO.getAllNotes();
           System.out.println("Todos las Notas han sido cargadas a DataNotes total Size: "+ListNotes.size());
        }catch(Exception e){
            System.out.println("Ocurrio un error en DataNotes.init() Detail ERROR \n"+e+" \n"+e.getCause());         
        }
        
    }
    
    /*
     * Este metodo es el encargado de ingresar una nueva nota al sistema, recibe como argumento un 
     * Objeto NiconNotes y lo invoca para guardar su informacion, en este paso espera la respuesta 
     * de la NiConection si la ejecucion de la sentencia ha sido exitosa recibe y procede a agregar
     * la nueva nota al array de Notas. en caso de que no se haya podido guardar la nota en el backend retorna
     * -1
     * 
     * Version 0.1
     */
   
     public static int addNote(NiconNotes note){
     
         NoteDAO=new NiconNotesDAO(note);
         state = NoteDAO.createNote();
         
         if(state==true){
           ListNotes.add(note);
           response=0;
           NoteDAO=null;
         }              
         return response;
     }
     
     /*
      * este metodo permite eliminar una nota del ArrayList de notas, hace el llamado a NiconNoteDAO
      * para hacer la eliminacion del backend SQLite.
      * recibe como parametro el objeto nota a eliminar, retorna 0 en caso de operacion exitosa y 
      * -1 en caso de oepracion fallida.
      * @param NiconNotes note
      */
     public static int deleteNote(NiconNotes note){
          
         NoteDAO=new NiconNotesDAO(note);
         state = NoteDAO.deleteNote();
            if(state==true){
                ListNotes.remove(note);
                response=0;
                NoteDAO=null;
            }
         return response;
     }
     
     public static int editNote(NiconNotes note){
         NoteDAO=new NiconNotesDAO(note);
         state=NoteDAO.editNote();
            if(state==true){
                ListNotes.remove(note);
                ListNotes.add(note);
                response=0;
                note=null;
            }
            return response;
     }
     
     public static NiconNotes getNote(int index){
         return (NiconNotes) ListNotes.get(index);
     }
            
      /*
       * Este metodo es el encargado de permitir la clonacion de el arrayList de Notas cargada en Init
         retorna ListNotes
       */
    
    public static ArrayList cloneDataNotes(){
        return ListNotes;
    }
    
}
