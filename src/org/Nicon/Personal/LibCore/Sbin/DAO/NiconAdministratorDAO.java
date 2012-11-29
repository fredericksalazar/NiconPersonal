/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.Sbin.DAO;

import org.Nicon.Personal.LibCore.Obj.NiconAdministrator;
import org.Nicon.Personal.LibCore.Sbin.NiConection;

/**
 *
 * @author frederick
 */
public class NiconAdministratorDAO {
    private static String sentence;

    private NiconAdministrator niconadmin;
    private int ExecuteSentence;
    private boolean state;

    public NiconAdministratorDAO() {
    }

    public NiconAdministratorDAO(NiconAdministrator niconadmin) {
        this.niconadmin = niconadmin;
    }

    public boolean createAdministrator() {
        try {
            String sql = "Insert into administrador values('" + niconadmin.getNombres() + "','" + niconadmin.getApellidos() + "','" + niconadmin.getDireccion() + "','" + niconadmin.getCiudad() + "','" + niconadmin.getCelular() + "','" + niconadmin.getEmail() + "','" + niconadmin.getLoggin() + "','" + niconadmin.getPassword() + "');";
            ExecuteSentence = NiConection.ExecuteSentence(sql);
            if (ExecuteSentence == 0) {
                state = true;
            } else {
                state = false;
            }
            niconadmin = null;
        } catch (Exception e) {
            System.out.println("Error en regist Admin" + e);
        }
        return state;
    }
    
    /*
   * Este metodo sera el encargado de grabar en la base de datos los cambios realizados en la contraseña de el 
   * administrador de NiconPersonal, recibe la password encriptada y se encarga de actualizar la base de datos
   * 
   * Fecha de creacion: 17/junio/2012
   */
  
  public static void SetPassWordSecret(String Password,String Name){
      try{
          sentence="update administrador set Password='"+Password+"' where Nombres='"+Name+"';";
          System.out.println(sentence);
          NiConection.ExecuteSentence(sentence);
          sentence=null;
      }catch(Exception e){
          System.out.println("Ocurrio un ERROR en NiconAdministrator.SetPasswordSecret() DetailERROR:\n"+e);
      }      
  }
}
