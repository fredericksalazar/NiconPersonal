package org.Nicon.Personal.LibCore.Sbin;

import org.Nicon.Personal.LibCore.Obj.NiconAdministrator;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.Nicon.Personal.GuiUser.Nicon.NiconLoginSystem;
import org.Nicon.Personal.LibCore.Sbin.DAO.NiconAdministratorDAO;

/**
 * Esta clase NiconSystemAdmin es la encargada de administrar opciones solo de interes de la 
 * aplicacion. provee metodos estaticos que ayudan al sistema a administrarse de forma efectiva
 * su objetivo principal es servir los metodos esenciales que ayuden al sistema a manipular todas
 * las posibles funciones criticas que el sistema requiera.
 * @author frederick
 */
public class NiconSystemAdmin {

    private static final char[] HEXADECIMAL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
        'c', 'd', 'e', 'f'};
    private static boolean StateActivationSystem = false;
    private static boolean stateOP;
    private static String EncoderCodification;
    private static ResultSet Data;
    private static SimpleDateFormat format;
    private static Date date;
    private static StringBuilder sb;
    private static String sentence;
    private static int WidthScreen;
    private static int HeightScreen;
    private static NiconAdministratorDAO niconadminDAO;
    private static NiconAdministratorDAO niconadmin;

    
    
    /**
     * Este metodo estatico provee una herramienta de validacion de estado del sistema, hace uso de 
     * la informaicon que entrega NiconAdministratorDAO para saber si existe un dueño y administrador
     * de la suite, en caso de existir un Administrador en el sistema retorna true, en caso contrario
     * retornara false haciendo referencia a que debe activarse el sistema.
     * 
     * @return boolean stado del sistema
     * @Author Frederick Adolfo Salazar Sanchez
     */
    
    public static boolean verifyStateSystem() {
        niconadmin = new NiconAdministratorDAO();
        return niconadmin.verifyStateNiconAdmin();
    }
    

    /**
     * Este metodo es el encargado de hacer el registro y activacion de la suite recibiendo
     * parametros para su posterior registro en el backend de informacion. este registro proveera
     * de un administrador al sistema
     * 
     * @param NiconAdministrator administrador
     * @Author Frederick Adolfo Salazar Sanchez
     */
    public static void activeNiconPersonal(NiconAdministrator administrator) {

        try {
            String password = EncryptationSecurityService(administrator.getPassword());
            niconadminDAO = new NiconAdministratorDAO(administrator);
            niconadminDAO.createAdministrator();
            NiconLoginSystem initLogin = new NiconLoginSystem(NiconAdministrator.GetDataAdmin());
            initLogin.setVisible(true);
            niconadminDAO=null;
            administrator=null;
        } catch (Exception e) {
            System.out.println(NiconSystemAdmin.GetInstantTime()+"\nOcurrio un error en NiconSystemAdmin.ActiveNiconPersonal():\n");
            e.printStackTrace();
        }
    }

    
    /**
     * Este metodo permite verificar las credenciales de ingreso de un usuario que esta intentando
     * ingresar al sistema, solo recibe como parametro la contraseña dado que no necesita usuario.
     * la econtraseña ingresada es encriptada en MD5 y comprobada con la almacenada en el backend
     * si coinciden retorna un boolean con estado true y permite el ingreso del usuario en caso
     * contrario retorna false y denega el ingreso.
     * 
     * 
     * @param String InputPassword 
     * @return boolean stateOP
     * @Author Frederick Adolfo Salazar Sanchez
     */
    
    public static boolean verifyCredentialsUser(String InputPassword) {
        boolean acces = false;
        try {
            String encriptPass = EncryptationSecurityService(InputPassword);
            sentence = "select Password from administrador;";
            Data = NiConection.RestoreDataSentence(sentence);
            Data.next();
            String adminPass = Data.getString("Password");
            Data.close();
            NiConection.DisconectDB();
            if ((adminPass.equals(encriptPass))) {
                acces = true;
                sentence = null;
                encriptPass = null;
                InputPassword = null;
                adminPass = null;
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error en SecurityControlSystem()\n" + e);
        }
        return acces;
    }

    /**
     * 
     * @return
     */
    public static String GetInstantTime() {
        String NowTime = "";
        format = new SimpleDateFormat("dd/MM/yyyy/HH:mm");
        try {
            date = new Date();
            NowTime = format.format(date);
        } catch (Exception e) {
            System.out.println("Ocurrio un ERROR en SystemAdmin.GetInstantTime() detail: \n" + e);
        }
        return NowTime;
    }

    /**
     * 
     * @param ToFormat
     * @return
     */
    public static String dateFormatSimple(Date ToFormat) {
        String SingleDate = "";
        if (ToFormat == null) {
            SingleDate = "No se ha ingresado la fecha";
        } else {
            try {
                format = new SimpleDateFormat("dd/MM/yyyy");
                SingleDate = format.format(ToFormat);
            } catch (Exception e) {
            }
        }

        return SingleDate;
    }

    /**
     * 
     * @param Input
     * @return
     */
    public static String EncryptationSecurityService(String Input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(Input.getBytes());
            sb = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                int low = (int) (bytes[i] & 0x0f);
                int high = (int) ((bytes[i] & 0xf0) >> 4);
                sb.append(HEXADECIMAL[high]);
                sb.append(HEXADECIMAL[low]);
            }
        } catch (NoSuchAlgorithmException AE) {
            System.out.println("Ocurrio un ERROR en NiconSystemAdmin.EncriptationSecurityService DetailError: \n" + AE);
        }
        return sb.toString();
    }

    /**
     * 
     * @return
     */
    public static int[] DimensionScreen() {
        int ValueDimension[] = new int[2];
        Dimension Screen = Toolkit.getDefaultToolkit().getScreenSize();
        WidthScreen = Screen.width;
        HeightScreen = Screen.height;
        ValueDimension[0] = WidthScreen;
        ValueDimension[1] = HeightScreen;
        return ValueDimension;
    }

    /**
     * 
     * @return
     */
    public static boolean verifyInternetConection() {
        try {
            String url = "www.twitter.com";
            int port = 80;
            Socket conect = new Socket(url, port);
            if (conect.isConnected()) {
                System.out.println("El sistema se encuentra conectado a Internet ...");
                stateOP = true;
            }
        } catch (Exception e) {
            System.out.println("No se encontro conexion a internet ...");
            stateOP = false;
        }
        return stateOP;
    }
}