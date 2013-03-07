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

import org.Nicon.Personal.Data.DataContact;
import org.Nicon.Personal.Data.DataNotes;

/**
 * Este es la clase encargada de inicializar componentes base del sistema, ademas se encarga de verficar
 * los archivos de configuracion del sistema.
 * @author frederick
 */
public class Init {
            
    public static void Initialize(){
        /**
         * se inician los vectores de datos que almacenaran la informacion importante del sistema en memoria
         */
        DataContact.init();
        DataNotes.Init();
    }
    
}
