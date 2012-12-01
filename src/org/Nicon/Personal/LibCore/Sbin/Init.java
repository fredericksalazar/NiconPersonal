/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.Sbin;

import org.Nicon.Personal.Data.DataContact;
import org.Nicon.Personal.Data.DataNotes;
import org.Nicon.Personal.LibCore.NiconTwitt.NiconTwitt;

/**
 *
 * @author frederick
 */
public class Init {
    
    private static NiconTwitt TwitterInit;
        
    public static void Initialize(){
        System.out.println("Iniciando interfaces de operacion ...");
        TwitterInit=new NiconTwitt();
        DataContact.init();
        DataNotes.Init();
        NiconSystemAdmin.verifyInternetConection();
        NiconTwitt.searchUser("@Juancarlos");
    }
    
}
