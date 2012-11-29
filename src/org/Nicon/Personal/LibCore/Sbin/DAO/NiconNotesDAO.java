/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.Sbin.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.Nicon.Personal.LibCore.Obj.NiconNotes;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Sbin.NiConection;
import org.Nicon.Personal.LibCore.Sbin.NiconFileAdministrator;


/**
 *
 * @author frederick
 */
public class NiconNotesDAO {
    
    private NiconNotes note;
    private String sentence;
    private int ExecuteSentence;
    
    private boolean state=false;
    private int count;
    private ArrayList ListNotes;
    private ResultSet dataBackend;
    private String NameFile;
    private NiconFileAdministrator NFCadmin;
    
    /**
     * 
     * @param Note
     */
    public NiconNotesDAO(NiconNotes Note){
        note=Note;
    }

    /**
     * 
     */
    public NiconNotesDAO() {
        
    }
    
    /**
     * 
     * @return
     */
    public boolean createNote(){
        try {
            System.out.println("Iniciando creación de nota...");
            sentence = "Insert into notas (Codigo,Titulo,Nota_Desc,Fecha_Gen)  values(null,'" + note.getTitulo() + "','" + note.getDescripcion() + "','" + note.getFecha_creacion() + "');";
            ExecuteSentence = NiConection.ExecuteSentence(sentence);
            System.out.println(sentence);
                if (ExecuteSentence == 0) {
                     state=true;
                     System.out.println("La nota se creó exitosamente");
                }else{
                     System.out.println("La nota no se creó, ocurrio un error ...");
                }
                NiConection.DisconectDB();
    } catch (Exception e) {
      System.out.println("Ocurrio el siguiente ERROR en NiconNotes.CreateNote() detail: \n" + e);
    }
        return state;
    }
    
    /**
     * 
     * @return
     */
    public boolean deleteNote(){
        
        try {
            System.out.println("Iniciando eliminacion de nota");
            sentence = "delete from notas where Codigo= " +note.getcodigo() + " and Titulo='"+note.getTitulo()+"';";
            ExecuteSentence = NiConection.ExecuteSentence(sentence);
                if(ExecuteSentence==0){
                    state=true;
                }
        }catch (Exception e){
            System.out.println("Ocurrio un Error en NiconNotes.DeleteNote() Detail: \n" + e);
        }
        return state;
    }
    
    /**
     * 
     * @return
     */
    public boolean editNote(){
        
        try{
           if(note!=null){
               deleteNote();
                state = createNote();
                    if(state==true){
                        state=true;
                    }else{
                        state=false;
                    }
           }else{
               state=false;
           }  
        }catch(Exception e){
            
        }
        return state;
    }
    
    /**
     * Encargado de Obtener un listado de todas las notas almacenadas en el Backend y cargarlas
     * a un ArrayList de NiconNotes. 
     * @return ArrayList con todas las notas encontradas
     */
    public ArrayList getAllNotes(){
        count = 0;
        ListNotes = new ArrayList();
        try {
            sentence = "select * from notas;";
            dataBackend = NiConection.RestoreDataSentence(sentence);
      
                while (dataBackend.next()) {
                    note = new NiconNotes(dataBackend.getInt(1), dataBackend.getString(2), dataBackend.getString(3), dataBackend.getString(4));
                    ListNotes.add(count, note);
                    count += 1;
                }
                dataBackend.close();
                NiConection.DisconectDB();
        }catch(Exception e){
            System.out.println("Ocurrio un error en NiconNotesDAO.getAllNotes() detail error:\n "+e);
        }   
        return ListNotes;
    }
    
    /**
     * Este metodo es el encargado de Organizar un objeto NiconNote y preparalo para exportar
     * a un archivo de text en la carpeta de exportaciones. usa la interface de NiconFileAdministrator
     * para el escritura en el disco.
     */
    public void exportNoteToTxt(){
        if(note==null){
            JOptionPane.showMessageDialog(null, "El Objeto Nota recibido es null", GlobalConfigSystem.getTitleAplication(), JOptionPane.ERROR_MESSAGE);
        }else{
            NameFile=note.getTitulo()+".txt";
            NFCadmin=new NiconFileAdministrator(GlobalConfigSystem.getExportPath(),NameFile,note.toStringExport());
            NFCadmin.writeFileText();
        }
    }
    
    public void exportallNotesToTxt(){
        
    }
    
    public void exportNoteObject(){
        
    }
   
}
