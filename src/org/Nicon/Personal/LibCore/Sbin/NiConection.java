package org.Nicon.Personal.LibCore.Sbin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class NiConection {

    public static Connection Conection;
    public static Statement Statement;
    public static ResultSet SaveData;
    public static String Driver = "org.sqlite.JDBC";
    public static String Urldb = "Nicon.db";
    public static boolean dbconection = false;

    /*
     * El metodo conectDB es el metodo encargado de regular la conexion con el backend de SQlite.
     * este metodo es invocado libremente desde cualquier lugar del sistema para obtener acceso
     * a los metodo de consulta, carga y creacion de registros
     * 
     * @Version 0.1
     * 
     */
    public static void ConectDB() {

        try {
            Class.forName(Driver);
            Conection = DriverManager.getConnection("jdbc:sqlite:" +Urldb);
            Statement = Conection.createStatement();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "ERROR al inciar Nicon, el driver de conección org.sqlite.JDBC no se encuentra\npor favor comuniquese a soporte tecnico para recuperar su sistema enviando \nun email a:  NiconSupport@gmail.com", GlobalConfigSystem.getTitleAplication(), 0);
            System.exit(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Un ERROR inesperado sucedio mientras se inciaba el sistema, el sistema finalizará ahora.\n" + e, GlobalConfigSystem.getTitleAplication(), 0);
            System.exit(0);
        }
    }

    /*
     * Este metodo me desconecta de la base de datos, 
     */
    public static void DisconectDB() {
        try {
            Conection.close();
            Statement.close();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "Ocurrio el siguiente error al cerrar la aplicacion\n" + error, GlobalConfigSystem.getTitleAplication(), 0);
        }
    }

    public static String SetDir() {
        String directorio = System.getProperty("java.class.path");
        File dir = new File(directorio);
        String directorioPadre = dir.getParent();
        return directorioPadre;
    }

    public static int ExecuteSentence(String sql) {
        int estateSql = -1;
        try {
            ConectDB();
            Statement.execute(sql);
            estateSql = 0;
            DisconectDB();
        } catch (SQLException error) {
            System.out.println("Ocurrio El siguiente error en Conection.ExecuteSentence(String sql) detail:\n" + error);
        }
        return estateSql;
    }

    public static ResultSet RestoreDataSentence(String sql) {
        try {
            ConectDB();
            SaveData = Statement.executeQuery(sql);
        } catch (Exception error) {
        }
        return SaveData;
    }

    private boolean VerifiStateConection() {
        boolean dbstate = true;
        try {
            if (Conection.isValid(0)) {
                dbstate = true;
            }
        } catch (Exception error) {
            System.out.println("Error en VerifyStateConection()\n" + error);
        }
        return dbstate;
    }

    public static boolean VerifyTableExist(String name) {
        boolean exist = false;
        try {
            SaveData = RestoreDataSentence("select * from " + name + ";");
            if (SaveData.next()) {
                exist = true;
            }
            DisconectDB();
        } catch (SQLException ex) {
        }
        return exist;
    }
}