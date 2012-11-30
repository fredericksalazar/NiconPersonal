/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.Sbin;

import org.Nicon.Personal.LibCore.Obj.NiconAdministrator;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.Nicon.Personal.LibCore.Sbin.DAO.NiconAdministratorDAO;

/**
 *NiconPersonalData esta destinada a ser la clase cuyo objeto creado administrara la version y versiones de desarrollo 
 * de NiconPersonal. contendra los metodos de actualizacion de versiones y ajustara las versiones antiguas con las 
 * nuevas caracterisiticas de la aplicacion.
 * @author frederick
 */
public class NiconPersonalData {
    private static String sentence;
    
    private String NameAplication;
    private String AlternativeName;
    private String AplicationVersion;
    
    private static String Sentence;
    private static ResultSet  Data;
    private static NiconPersonalData NiconData;
    private static NiconAdministratorDAO  niconadminDAO;
    
    public NiconPersonalData(){
        
    }

    public NiconPersonalData(String NameAplication, String AlternativeName, String AplicationVersion) {
        this.NameAplication = NameAplication;
        this.AlternativeName = AlternativeName;
        this.AplicationVersion = AplicationVersion;
    }

    public void setAlternativeName(String AlternativeName) {
        this.AlternativeName = AlternativeName;
    }

    public void setAplicationVersion(String AplicationVersion) {
        this.AplicationVersion = AplicationVersion;
    }

    public void setNameAplication(String NameAplication) {
        this.NameAplication = NameAplication;
    }

    public String getAlternativeName() {
        return AlternativeName;
    }

    public String getAplicationVersion() {
        return AplicationVersion;
    }

    public String getNameAplication() {
        return NameAplication;
    }
    
    /*
     *getNiconPersonalData retorna un objeto de el tipo NiconPersonalData el cual contiene la informacion basica del
     * software como nombres, version y nombre alterativo. esto para que se haga la comprobacion de versiones en 
     * NiconUpdate.
     *
     */
    public static NiconPersonalData getNiconPersonalData(){
        try{
            Sentence="select * from NiconSysInfo;";
            Data=NiConection.RestoreDataSentence(Sentence);
            Data.next();
            NiconData=new NiconPersonalData(Data.getString(1),Data.getString(2),Data.getString(3));
            Data.close();
            NiConection.DisconectDB();
        }catch(Exception e){
            System.out.println("Ocurrio un error en NiconPersonalData.getNiconPersonalData() DetailError:\n"+e);
        }
        return NiconData;
    }
    
    /*
     * el metodo se encrarga en cada actualizacion de ajustar los valores en el backend, actualiza esa informacion para
     * posterior uso y comparaciones.
     */
    public void updateDataAplication(){
        try{
            sentence="delete from niconsysinfo where 1;";
            NiConection.ExecuteSentence(sentence);
            sentence = "insert into NiconSysInfo values('"+getNameAplication()+"','"+getAlternativeName()+"','"+getAplicationVersion()+"','AlfaT1','" + NiconSystemAdmin.GetInstantTime() + "');";
            NiConection.ExecuteSentence(sentence);
        }catch(Exception er){
            System.out.println("Ocurrio un error al intentar ajustar Datos de la aplicacion detail error\n");
            er.printStackTrace();
        }
    }
    
    /*
   * el metodo UpdateToVenus sera el encargado de acondicionar todo el software anterior a esta nnueva version
   * como podremos ver ya esta seria la segunda version de lanzamiento luego de la version inicial, los cambios de la 
   * version anterior fueron en base de NiconNotes se cambia la table y se adiciona una nueva en la cual ya se hace
   * el manejo de informacion de NiconPersonal. como su nombre y su version actual.
   * en venus hasta hoy haremos la utilizacion de el MD5 para cambiar el password asi que sera la actualizacion a nivel
   * general hecha hasta el momento.
   */
  public void UpdateToVenus(){
        try {
            NiconAdministrator administrator=NiconAdministrator.GetDataAdmin();
            sentence="select Password from administrador;";
            Data=NiConection.RestoreDataSentence(sentence);
            Data.next();
            String pass=Data.getString("Password");            
            NiConection.DisconectDB();
            String FormatPassword=NiconSystemAdmin.EncryptationSecurityService(pass);
            System.out.println("La contraseña ha sido encriptada ... ");
            NiconAdministratorDAO.SetPassWordSecret(FormatPassword, administrator.getNombres());
            System.out.println("La nueva contraseña ha sido ajustada a el administrador Value IS"+FormatPassword);            
        } catch (SQLException ex) {
        }
  }

    @Override
    public String toString() {
        return "NiconPersonalData {" + "NameAplication=" + NameAplication + ", AlternativeName=" + AlternativeName + ", AplicationVersion=" + AplicationVersion + '}';
    }
  
  
}
