/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.Sbin;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author frederick
 */
public class NiconFileAdministrator {
    
    private Object object;
    private ObjectInputStream OIS;
    private ObjectOutputStream OOS;
    private FileOutputStream FOS;
    private FileInputStream FIS;
    
    private File NiconFile;
    private FileWriter NiconWriter;
    private Object data;
    
    private String Path;
    private String content;
    private String NameFile;
    
    private boolean state;
    
    public NiconFileAdministrator(){
        
    }
    
    public NiconFileAdministrator(File NiconFile){
        this.NiconFile=NiconFile;
    }
    
    public NiconFileAdministrator(String Path,String NameFile,String content){
        this.content=content;
        this.Path=Path;
        this.NameFile=NameFile;
    }
    
    public NiconFileAdministrator(Object object,String Path,String NameFile){
        this.object=object;
        this.NameFile=NameFile;
        this.Path=Path;
    }
    
    
    public void writeFileText(){
        try {
            NiconFile=new File(this.Path,this.NameFile);
            verifyDirExists(Path);
            if(verifyFileExistComplete(NameFile, Path)){
              System.out.println("Iniciando la escritura de datos en "+NiconFile.getName()+" ...");
              NiconWriter=new FileWriter(NiconFile); 
              NiconWriter.write(GlobalConfigSystem.getHeaderFile()+"\n"+this.content);
              NiconWriter.close();              
              JOptionPane.showMessageDialog(null, "El archivo ha sido creado exitosamente\n\n"
                                                + "File Details:\n"
                                                + "Name File:  "+NiconFile.getName()+"\n"
                                                + "Total Size:  "+NiconFile.length()+"\n"
                                                + "Directory:    "+NiconFile.getPath(), GlobalConfigSystem.getTitleAplication(), JOptionPane.INFORMATION_MESSAGE);
              System.out.println("El archivo "+NiconFile.getName()+" Ha sido escrito exitosamente, cargando aplicación ...");
              Desktop.getDesktop().open(NiconFile);
            }          
        } catch (IOException ex) {
            Logger.getLogger(NiconFileAdministrator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeFileObject(){
        try{
           NiconFile=new File(this.Path,this.NameFile);
           verifyDirExists(Path);
            if(verifyFileExistComplete(NameFile, Path)){
              OOS=new ObjectOutputStream(new FileOutputStream(NiconFile)); 
              OOS.writeObject(object);
              OOS.close();
              System.out.println("El objeto "+NameFile+" ha sido escrito correctamente en: "+Path);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Object readFileObject(String PathFile){
        try{
           OIS=new ObjectInputStream(new FileInputStream(PathFile));
           data= OIS.readObject();
           OIS.close();
        }catch(Exception e){
            
        }
        return data;
    }
    
    
    
    /*
     * Este metodo se encarga de verificar si un directorio existe o no, en caso de ser negativo, lo crea
     * recibe como parametro el PATH del directorio a verificar.
     *
     * Creado por: Frederick Adolfo Salazar Sanchez
     */
    
    public void verifyDirExists(String Path) {
        System.out.println("Verificando la existencia del directorio"+Path+"...");
        NiconFile = new File(Path);
        state = NiconFile.exists();
        if (state == false) {
            System.out.println("El directorio "+Path+" no existe ....");
            System.out.println("Creando directorio: " + Path);
            NiconFile.mkdir();
            System.out.println("El direcotiro ha sido creado exitosamente ...");
            NiconFile=null;
        }
    }  
    
    /*
     * este metodo permite verificar su un archivo existe dentro de un directorio determinado
     * recibe como parametros el nombre del archivo a buscar y el Path del directorio. retorna
     * un boolean con la respuesta de si el usuario desea reemplazarlo o no. en caso de que 
     * el usuario desee reemplazarlo retornará true, en caso contrario retornará false.
     * 
     * Creado por:   Frederick Adolfo Salazar Sanchez
     */
    public boolean verifyFileExistComplete(String FileName,String Path){        
        System.out.println("Verificando la existencia del Archivo: "+Path+"/"+FileName);
        NiconFile=new File(Path,FileName);
        
        if(NiconFile.exists()){
            System.out.println("El archivo "+Path+"/"+FileName+"  ya existe consultando acción...");
            int response=JOptionPane.showConfirmDialog(null, "El archivo : "+FileName+"\nYa existe ¿Desea reemplazarlo?", GlobalConfigSystem.getTitleAplication(), JOptionPane.YES_NO_OPTION);
            if(response==0){
                System.out.println("El archivo "+FileName+" será reescrito, preparando escritura de datos...");
                state=true;
            } else {
                System.out.println("El archivo "+FileName+"  No será reescrito cancelando operaciones ...");
                state=false;
            }
        }else{
            System.out.println("El archivo "+FileName+" No existe, preparando escritura de datos ...");
            state=true;
        }
        return state;
    }
    
    public boolean verifyFileExistSimple(String FileName, String Path){
        System.out.println("Verificando la existencia del Archivo: "+Path+"/"+FileName);
        NiconFile=new File(Path,FileName);
            if(NiconFile.exists()){
                state=true;
            }else{
                state=false;
            }
            return state;
    }
    
     /*
     * Este metodo es usado para listar todos los archivos que se encuentran en un directorio especificado, recibe
     * como parametro el PATH del directorio
     * 
     * V 0.1
     * Fecha creacion ocutbre 17 - 2012
     */
    public void listDir(String Path) {
        try {
            NiconFile = new File(Path);
            String[] objects = NiconFile.list();

            if (objects == null) {
                System.out.println("No se encuentran archivos en el directorio: " + NiconFile.getName());
            } else {
                for (int i = 0; i < objects.length; i++) {
                    System.out.println(objects[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al listar: " + NiconFile.getName());
        }
    }
}
