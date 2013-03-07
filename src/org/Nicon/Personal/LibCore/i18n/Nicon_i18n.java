/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package org.Nicon.Personal.LibCore.i18n;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Nicon_i18ln representan la configuracion de el idioma en el cual se desea usar NiconPersonal, este objeto ayuda a 
 * cargar, y ajustar la informacion de traduccion de NiconPersonal, lo lenguajes actuales soportados son el español de
 * colombia y el Ingles.
 * 
 * @author frederick
 */
public class Nicon_i18n extends Properties{
    
    private String Nicon_Lang;
    
    /**
     * Este constructor por defecto se encarga de obtener el paquete de traduccion para el idioma español del sistema.
     */
    public Nicon_i18n(){
        Nicon_Lang="Nicon-i18n-es.properties";
        loadProperties();
    }
    
    /**
     * este constructor permite iniciar Nicon_i18ln recibiendo el idioma que se desea cargar en la interfaz
     * @param Languaje 
     */
    public Nicon_i18n(String Languaje){
        
        if(Languaje.equals("Español")){
            Nicon_Lang="Nicon-i18n-es.properties";
            loadProperties();
        }
        if(Languaje.equals("Ingles")){
            Nicon_Lang="Nicon-i18n-en.properties";
            loadProperties();
        }
    }
    
    /**
     * carga el archivo de propiedades del idioma en el que se ha deseado ajustar la interfaz grafica.
     */
    private void loadProperties(){
        try {
            this.load(this.getClass().getResourceAsStream("/org/Nicon/Personal/LibCore/i18n/"+Nicon_Lang));            
        } catch (IOException ex) {
            Logger.getLogger(Nicon_i18n.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
