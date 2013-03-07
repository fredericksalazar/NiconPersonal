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
    
    private static boolean stateOP;
    
    private static ResultSet Data;
    private static SimpleDateFormat format;
    private static Date date;
    
    private static int WidthScreen;
    private static int HeightScreen;
    private static NiconAdministratorDAO niconadminDAO;
    private static NiconAdministratorDAO niconadmin;
    
    private static String EncoderCodification;
    private static String dateFormated;
    private static String sentence;
    private static StringBuilder sb;
        
    
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
            String password = encriptationService(administrator.getPassword());
            niconadminDAO = new NiconAdministratorDAO(administrator);
            niconadminDAO.createAdministrator();
            NiconLoginSystem initLogin = new NiconLoginSystem(NiconAdministrator.GetDataAdmin());
            initLogin.setVisible(true);
            niconadminDAO=null;
            administrator=null;
        } catch (Exception e) {
            System.out.println(getInstantTime()+"\nOcurrio un error en NiconSystemAdmin.ActiveNiconPersonal():\n");
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
        
        try {
            String encriptPass = encriptationService(InputPassword);
            sentence = "select Password from administrador;";
            Data = NiConection.RestoreDataSentence(sentence);
            Data.next();
            String adminPass = Data.getString("Password");
            Data.close();
            NiConection.DisconectDB();            
                if ((adminPass.equals(encriptPass))) {
                    stateOP = true;
                }
        } catch (Exception e) {
            System.out.println("Ocurrio un error en SecurityControlSystem()\n" + e);
        }
        return stateOP;
    }

    /**
     * este metodo nos permite obtener un instante de tiempo formatearlo y convertirlo en 
     * un String que podra ser usado por la aplicacion.
     * 
     * @return String date
     * @Author Frederick Adolfo Salazar Sanchez
     */
    
    public static String getInstantTime() {
        
        try {
            format = new SimpleDateFormat("dd/MM/yyyy/HH:mm");
            date = new Date();
            dateFormated = format.format(date);
        } catch (Exception e) {
            System.out.println("Ocurrio un ERROR en SystemAdmin.GetInstantTime() detail: \n" + e);
        }
        return dateFormated;
    }

    /**
     * formatea un momento de tiempo en forma simple solo dia, mes y hora, retorna un String 
     * con el valor obtenido y podra ser accedido desde cualquier lugar del sstema, recibe como
     * parametro el objeto Date a dar formato
     * 
     * @param Dat ToFormat
     * @return String dateFormat
     */
    public static String dateFormatSimple(Date ToFormat) {
        
        if (ToFormat == null) {
            dateFormated = "el Objeto Date recibido no es valido valor=null";
        } else {
            try {
                format = new SimpleDateFormat("dd/MM/yyyy");
                dateFormated = format.format(ToFormat);
            } catch (Exception e) {
                System.out.println("Ocurrio un error en NiconSystemAdmin.dateFormatSimple():\n");
                e.printStackTrace();
            }
        }
        return dateFormated;
    }

    /**
     * Este metodo ofrece el servicio de encriptacion de un cadena de String, se usa para
     * codificar la contraseña del usuario que ingresa al sistema. recibe como parametro
     * el String a encrptar y retorna un String con el valor en MD5 del proceso.
     * 
     * @param String Input
     * @return String output
     */
    public static String encriptationService(String Input) {
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
     * Este metodo tiene com finalidad obtener el tamaño de la pantalla sobre la que se esta
     * trabajando. retorna un vector con los datos recibidos.
     * 
     * @return int [] escrenDimension
     * @Author Frderick Adolfo Salazar Sanchez
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
     * este metodo se integro el 1 de diciembre de 2012, es simple su uso solo verifica si
     * el pc del usuario tiene o no acceso a Internet. esto se hace con la finalidad de 
     * poder usar NiconPersonal en su modulo NiconMail y NiconTwiit de forma offline.
     * 
     * @return boolean stateOP
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