/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.Update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author frederick
 */
public class NiconUpdater {
    
    private String URL;
    private String File;
    private String currentFile;
    private File FileUpdate;
    
    private URL url;
    private URLConnection conection;
    
    public NiconUpdater(){
        URL="https://www.box.com/files/0/f/215851998/1/f_1699887608";
        getFileUpdate();
    }
    
    private void getFileUpdate(){
        try{
            System.out.println("Iniciando la coneccion a: "+URL);
            url=new URL(URL);
            conection=url.openConnection();
            System.out.println("Coneccion establecida\n"+conection.toString()+"\n"+conection.getContentType());
            InputStream is = conection.getInputStream();

            // Fichero en el que queremos guardar el contenido
            FileOutputStream fos = new FileOutputStream("./Config/nicon.rar");

            // buffer para ir leyendo.
            byte [] array = new byte[1000];
            System.out.println("Obteniendo archivo ...");
            // Primera lectura y bucle hasta el final
            int leido = is.read(array);
                while (leido > 0) {
                    fos.write(array,0,leido);
                    leido=is.read(array);
                }

            // Cierre de conexion y fichero.
            is.close();
            fos.close();
            System.out.println("Archivo Descargado");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
