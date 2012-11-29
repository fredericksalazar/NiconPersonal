/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.Sbin;

import org.Nicon.Personal.LibCore.Obj.NiContact;
import java.util.Iterator;
import javax.swing.JPanel;
import org.Nicon.Personal.Data.DataContact;
import org.Nicon.Personal.GuiUser.NiContacts.GuiViewContact;

/**
 *
 * @author frederick
 * 
 */
public class WidgetUsefulInterface {
    
    private Iterator iterator;
    private NiContact TempContact;
    private int index;
    private JPanel TemporalPanel;
    private static GuiViewContact View;
    
    public WidgetUsefulInterface(){
       TempContact=new NiContact();
    }
    
    public boolean SearchDataInput(String characters){  
        boolean response=false;
        try{
            NiContact Contact = DataContact.SearchContact(characters);
            View=new GuiViewContact(Contact);
            View.setVisible(true);
        }catch(Exception e){
           System.out.println("Ocurrio un ERROR en WidgetUsefulInterface.SearchDataInput() \n detail error:"+e);
        }
        return response;
    }
    
    public static void CloseView(){
        View.setVisible(false);        
    }
    
    
}
