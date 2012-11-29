/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.i18n;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frederick
 */
public class Nicon_i18n extends Properties{
    
    private Properties Languaje;
    private String Nicon_Lang;
    
    public Nicon_i18n(){
        Nicon_Lang="Nicon-i18n-es.properties";
        loadProperties();
    }
    
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
    
    private void loadProperties(){
        try {
            System.out.println("Iniciando la configuracion de traduccion con: "+Nicon_Lang);
            this.load(this.getClass().getResourceAsStream("/org/Nicon/Personal/LibCore/i18n/"+Nicon_Lang));
            System.out.println("La configuracion de i18n fue cargada exitosamente ..."+this.getClass().getName());
            
        } catch (IOException ex) {
            Logger.getLogger(Nicon_i18n.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
