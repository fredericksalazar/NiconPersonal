/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.Sbin.DAO;

import java.sql.ResultSet;
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
    private ResultSet dataDB;

    /**
     * 
     */
    public NiconAdministratorDAO() {
    }

    /**
     * 
     * @param niconadmin
     */
    public NiconAdministratorDAO(NiconAdministrator niconadmin) {
        this.niconadmin = niconadmin;
    }

    /**
     * 
     * @return
     */
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
    
    /**
     * Este metodo se encarga de verificar el estado de un administrador dentro de el Backend SQLite
     * en caso de estar null la libreta no ha sido activada aun y retorna como estado false.
     * @return boolean state.
     */
    public boolean verifyStateNiconAdmin(){
        try{
            sentence="select * from administrador;";
            dataDB=NiConection.RestoreDataSentence(sentence);
                if(dataDB.next()){
                    //el objeto administrador ya se encuentra registrado en el Backend
                    state=true;
                }else{
                    state=false;
                }
                dataDB.close();
                NiConection.DisconectDB();
        }catch(Exception e) {
            System.out.println("Un error ha ocurrido en NiconAdministratorDAO.verifiyStateNiconAdmin():");
            e.printStackTrace();
        }
        return state;
    }
    
    /*
   * Este metodo sera el encargado de grabar en la base de datos los cambios realizados en la contraseña de el 
   * administrador de NiconPersonal, recibe la password encriptada y se encarga de actualizar la base de datos
   * 
   * Fecha de creacion: 17/junio/2012
   */
  
    /**
     * 
     * @param Password
     * @param Name
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
