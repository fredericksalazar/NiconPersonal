/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.Sbin;

import org.Nicon.Personal.LibCore.i18n.Nicon_i18n;
import java.awt.Color;
import java.util.Properties;

/**
 * La clase GlobalConfigSystem planea convertirse en una interfaz de configuracion para todo el sistema, nos da acceso
 * rápido a las configuracion desde cualquier lugar del programa. usa metodos estaticos que proveen el acceso inmediato
 * a sus datos.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 * 
 */
public class GlobalConfigSystem {
    
    private static String TitleAplication;
    
    private static Color ForegroundAplicationText;
    private static Color ForegroundDashPanel;
    private static Color NiconPersonalColorInterface;
    
    private static String NameAplication;
    private static String AlternativeName;
    private static String CurrentVersion;   
    
    private static String IconsPath;
    private static String ExportPath;    
    private static String HeaderFile;
    
    private static final String CONSUMER_KEY="q5NXXmv4aezbn2c8ul9YA";
    private static final String CONSUMER_SECRET="sOy1jJpHMm3RIo9h1zE4fiNjtSjiEPttvzOaRJI";
    
    private static Nicon_i18n Languaje;
       
    public GlobalConfigSystem(){
        
        NameAplication="NiconPersonal";
        AlternativeName="Venus";
        CurrentVersion="0.3.41";
        IconsPath="/org/Nicon/Personal/GuiUser/Icons/";
        ExportPath="./exportaciones";
        HeaderFile="\n\n"
                + "Aplicación:    " + GlobalConfigSystem.getNameAplication() + "\n"
                + "Versión:       " + GlobalConfigSystem.getCurrentVersion() + "\n"
                + "Nombre clave:  " + GlobalConfigSystem.getAlternativeName() + "\n"
                + "Fecha creación:" + NiconSystemAdmin.GetInstantTime() + "\n"
                + "--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- \n";
        
        ForegroundAplicationText=new java.awt.Color(210, 210, 210);
        ForegroundDashPanel=new Color(18, 151, 220);
        NiconPersonalColorInterface=new java.awt.Color(35, 35, 35);
        Languaje=new Nicon_i18n();        
    }

    public static String getAlternativeName() {
        return AlternativeName;
    }

    public static String getCurrentVersion() {
        return CurrentVersion;
    }

    public static String getNameAplication() {
        return NameAplication;
    }
    
    public static String getTitleAplication() {
        return TitleAplication=NameAplication+" "+CurrentVersion+" "+AlternativeName;
    }

    public static Color getForegroundAplicationText() {
        return ForegroundAplicationText;
    }
    
    public static Color getNiconPersonalColorInterface(){
        return NiconPersonalColorInterface;
    }
    
    public static Color getForegroundDashPanel(){
        return ForegroundDashPanel;
    }   
    
    public static String getIconsPath(){
        return IconsPath;
    }
    
    public static String getExportPath(){
        return ExportPath;
    }
    
    public static String getHeaderFile(){
        return HeaderFile;
    }

    public static String getCONSUMER_KEY() {
        return CONSUMER_KEY;
    }

    public static String getCONSUMER_SECRET() {
        return CONSUMER_SECRET;
    }
    
    public static Properties getLanguajeProperties(){
        return Languaje;
    }
}
