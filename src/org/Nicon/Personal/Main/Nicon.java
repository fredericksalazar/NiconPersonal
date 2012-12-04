
package org.Nicon.Personal.Main;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
import org.Nicon.Personal.GuiUser.Nicon.GuiActivation;
import org.Nicon.Personal.GuiUser.Nicon.NiconLoginSystem;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Obj.NiconAdministrator;
import org.Nicon.Personal.LibCore.Sbin.NiconSystemAdmin;
import org.Nicon.Personal.LibCore.Sbin.NiconUpdate;



/**
 * Esta es la clase de inicio de la aplicacion, en ella damos los primeros pasos cuando es ejecutada
 * la suite de informacion. en primer medida lo que hacemos es inicializar todas las configuraciones
 * del sistema. luego se procede a ajustar el look and feel Nimbus que sera el sistema por defecto
 * posterior a ello procedemos a validar el estado del sistema que solo pueden ser dos o activo o inactivo
 * esto lo hacemos mediante la interfaz NiconSystemAdmin que es el sistema de adminsistracion central de la 
 * suite. en caso de NO estar activada se procede a ello o de lo contrario entramos a validar
 * el usuario que desea ingresar.
 * 
 * @author Adolfo Salazar Sanchez
 * 
 */
public class Nicon{

    /**
     * 
     * @param args
     */
    public static void main(String[] args){
      
                    try{
                        GlobalConfigSystem config=new GlobalConfigSystem();
                        System.out.println("Iniciando "+GlobalConfigSystem.getTitleAplication());
                        UIManager.setLookAndFeel(new NimbusLookAndFeel());
                        boolean estado = NiconSystemAdmin.verifyStateSystem();
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