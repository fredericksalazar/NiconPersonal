/************************************************************************
 * NiconPersonal v 0.3.5 All Rights Reserved (C) 2012 NiconSystem Inc.  *
 * Florida valle del cauca 318 437 4382 / fredefass01@gmail.com         *
 * NiconPersonal es desarrollado por NiconSystem Inc. todos los derechos*
 * reservados, la copia, distribución o comercialización sin una autori *
 * zación debida sera considerada como ilegal.                          *
 * NiconPersonal es diseñada, desarrollada y mantenida por el Ingeniero *
 * Frederick Adolfo Salazar Sanchez. los derechos de copia y sucecion   *
 * seran concedidos a Nicol Sofia Salazar Perez.                        *
 *                                                                      *
 *                                                                      *
 * **********************************************************************/

package org.Nicon.Personal.Main;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.UIManager;
import org.Nicon.Personal.GuiUser.Nicon.GuiActivation;
import org.Nicon.Personal.GuiUser.Nicon.NiconLoginSystem;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Obj.NiconAdministrator;
import org.Nicon.Personal.LibCore.Sbin.NiconSystemAdmin;
import org.Nicon.Personal.LibCore.Sbin.NiconUpdate;
/*
 * @author Frederick Adolfo Salazar Sanchez
 * @serial NPC00001
 * @version 0.3.30
 * 
 */
public class Nicon{
    
    /*
     * NiconPersonal es una suite de informacion personal desarrollada en Java, compuesta de 2 grandes componentes
     * que interactuan entre si para brindar la mejor aplicacion de informacion personal del mercado. el sistema
     * de almacenamiento de datos es el Backend SQlite con el que NiconPersonal se interelaciona.
     * 
     * Mantenedor principal:    Frederick Adolfo Salazar Sanchez
     * Canal de desarrollo:     Alfa
     * Nombre Aplicacion:       NiconPersonal
     * Nombre Alternativo:      Venus
     * Version del sistema      0.3.30
     * 
     * Patrocinado por:         NiconSystem Incorporated.
     * Florida valle del cauca - Colombia - 57 + 318 437 4382 - Cra 12#10a-30
     * 
     * Version actual: 0.3.20 fecha 13-oct-2012
     */
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