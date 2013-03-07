/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */


package org.Nicon.Personal.LibCore.Obj;

import org.Nicon.Personal.LibCore.Sbin.NiConection;
import java.sql.ResultSet;

public class NiconAdministrator {

    private static String sentence;
    private String Nombres;
    private String Apellidos;
    private String Direccion;
    private String Ciudad;
    private String Celular;
    private String Email;
    private String Loggin;
    private String Password;
    private static ResultSet DataAdmin;

    public NiconAdministrator(String Nombres, String Apellidos, String Direccion, String Ciudad, String Celular, String Email, String Loggin, String Password) {
        this.Nombres = Nombres;
        this.Apellidos = Apellidos;
        this.Direccion = Direccion;
        this.Ciudad = Ciudad;
        this.Celular = Celular;
        this.Email = Email;
        this.Loggin = Loggin;
        this.Password = Password;
    }

    public NiconAdministrator() {
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setLoggin(String Loggin) {
        this.Loggin = Loggin;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getApellidos() {
        return this.Apellidos;
    }

    public String getCelular() {
        return this.Celular;
    }

    public String getCiudad() {
        return this.Ciudad;
    }

    public String getDireccion() {
        return this.Direccion;
    }

    public String getEmail() {
        return this.Email;
    }

    public String getLoggin() {
        return this.Loggin;
    }

    public String getNombres() {
        return this.Nombres;
    }

    public String getPassword() {
        return this.Password;
    }

    public static NiconAdministrator GetDataAdmin() {
        NiconAdministrator admin = new NiconAdministrator();
        try {
            sentence = "select Nombres,Apellidos,Direccion,Ciudad,Celular from administrador;";
            DataAdmin = NiConection.RestoreDataSentence(sentence);
            DataAdmin.next();
            admin = new NiconAdministrator(DataAdmin.getString("Nombres"), DataAdmin.getString("Apellidos"), DataAdmin.getString("Direccion"), DataAdmin.getString("Ciudad"), DataAdmin.getString("Celular"), "", "", "");
            NiConection.DisconectDB();
        } catch (Exception e) {
            System.out.println("currio un error en Administrador.GetDataAdmin() detail:  \n" + e);
        }
        return admin;
    }
}