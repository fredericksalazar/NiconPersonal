/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package org.Nicon.Personal.LibCore.Sbin;

import org.Nicon.Personal.LibCore.i18n.Nicon_i18n;
import java.awt.Color;
import java.util.Properties;

/**
 * La clase GlobalConfigSystem planea convertirse en una interfaz de configuracion para todo el sistema, nos da acceso
 * rápido a las configuracion desde cualquier lugar del programa, usa metodos estaticos que proveen el acceso inmediato
 * a sus datos.
 * 
 * @author Frederick Adolfo Salazar Sanchez
 * 
 */
public class GlobalConfigSystem {
    
    private static String generalNameAplication;
    
    private static Color ForegroundAplicationText;
    private static Color ForegroundDashPanel;
    private static Color NiconPersonalColorInterface;
    
    private static String nameAplication;
    private static String alternativeName;
    private static String currentVersion;   
    
    private static String IconsPath;
    private static String ExportPath;    
    private static String HeaderFile;
    
    private static final String CONSUMER_KEY="q5NXXmv4aezbn2c8ul9YA";
    private static final String CONSUMER_SECRET="sOy1jJpHMm3RIo9h1zE4fiNjtSjiEPttvzOaRJI";
    
    private static Nicon_i18n languajeProperties;
       
    public GlobalConfigSystem(){
        
        nameAplication="NiconPersonal";
        alternativeName="Venus";
        currentVersion="0.3.45";
        IconsPath="/org/Nicon/Personal/GuiUser/Icons/";
        ExportPath="./exportaciones";
        HeaderFile="\n\n"
                + "Aplicación:    " + GlobalConfigSystem.getNameAplication() + "\n"
                + "Versión:       " + GlobalConfigSystem.getCurrentVersion() + "\n"
                + "Nombre clave:  " + GlobalConfigSystem.getAlternativeName() + "\n"
                + "Fecha creación:" + NiconSystemAdmin.getInstantTime() + "\n"
                + "--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- --- \n";
        
        ForegroundAplicationText=new java.awt.Color(210, 210, 210);
        ForegroundDashPanel=new Color(18, 151, 220);
        NiconPersonalColorInterface=new java.awt.Color(35, 35, 35);
        languajeProperties=new Nicon_i18n();        
    }

    /**
     * Retorna el nombre en clave de la actual version de NiconPersonal
     * @return String alternativeName
     */
    public static String getAlternativeName() {
        return alternativeName;
    }

    /**
     * retorna la actual versión de NiconPersonal
     * @return String version
     */
    public static String getCurrentVersion() {
        return currentVersion;
    }

    /**
     * Retorna el Nombre de la aplicación
     * @return String nameAplication
     */
    public static String getNameAplication() {
        return nameAplication;
    }
    
    /**
     * Retorna el nombre general del sistema compuesto por el nombre de la aplicacion, la version actual y el nombre en clave
     * @return generNameAplication
     */
    public static String getTitleAplication() {
        return generalNameAplication=nameAplication+" "+currentVersion+" "+alternativeName;
    }

    /**
     * Retorna el color de fuente para los objetos de tipo texto normal
     * @return Color foregroundAplicationtext
     */
    public static Color getForegroundAplicationText() {
        return ForegroundAplicationText;
    }
    
    /**
     * retrona el color de la interfaz de NiconPersonal 
     * @return Color NiconPersonalColorInterface
     */
    public static Color getNiconPersonalColorInterface(){
        return NiconPersonalColorInterface;
    }
    
    /**
     * Retorna el colo de la fuente del Dash de NiconPersonal
     * @return foregroundDashPanel
     */
    public static Color getForegroundDashPanel(){
        return ForegroundDashPanel;
    }   
    
    /**
     * retorna el Path del set de Iconos.
     * @return iconsPath
     */
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
    
    /**
     * Este metodo retorna la configuracion de lenguaje para el sistema, hace uso del Objeto Nicon_i18ln que retornado
     * a la configuracion total del sistema
     * @return languaje
     */
    public static Properties getLanguajeProperties(){
        return languajeProperties;
    }
}
