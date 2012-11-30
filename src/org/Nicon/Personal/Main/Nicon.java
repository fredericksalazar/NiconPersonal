
package org.Nicon.Personal.Main;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
import org.Nicon.Personal.GuiUser.Nicon.GuiActivation;
import org.Nicon.Personal.GuiUser.Nicon.NiconLoginSystem;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Obj.NiconAdministrator;
import org.Nicon.Personal.LibCore.Sbin.NiconSystemAdmin;
import org.Nicon.Personal.LibCore.Sbin.NiconUpdate;



public class Nicon{

        public static void main(String[] args){
      
                    try{
                        GlobalConfigSystem config=new GlobalConfigSystem();
                        System.out.println("Iniciando "+GlobalConfigSystem.getTitleAplication());
                        UIManager.setLookAndFeel(new NimbusLookAndFeel());
                        boolean estado = NiconSystemAdmin.VerifyStateSystem();
                        System.out.println("Verificando estado del sistema ..."); 
                            if (!estado) {
                                   System.out.println("Iniciando activación de "+GlobalConfigSystem.getTitleAplication());
                                   GuiActivation activar = new GuiActivation();
                                   activar.setLocationRelativeTo(null);
                                   activar.setVisible(true);
                           } else {
                                  System.out.println("Iniciando componentes de "+GlobalConfigSystem.getTitleAplication());
                                  NiconUpdate.VerifySystemVersion();
                                  NiconLoginSystem Login = new NiconLoginSystem(NiconAdministrator.GetDataAdmin());
                                  Login.setVisible(true);
                           }
                    }
                    catch (Exception ex){
                            System.out.println("Ocurrio un error en Nicon.StaticVoidMain() DetailError:\n"+ex.toString());
                    }
        }
}