package org.Nicon.Personal.LibCore.Sbin;

import org.Nicon.Personal.LibCore.Obj.NiconAdministrator;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.Nicon.Personal.GuiUser.Nicon.NiconLoginSystem;
import org.Nicon.Personal.LibCore.Sbin.DAO.NiconAdministratorDAO;
/*
 * @author Ing Frederick Adolfo Salazar Sanchez
 * @serial NPC00003
 * @version 0.3.5
 */

public class NiconSystemAdmin {

    private static final char[] HEXADECIMAL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
        'c', 'd', 'e', 'f'};
    private static boolean StateActivationSystem = false;
    private static String EncoderCodification;
    private static ResultSet Data;
    private static NiConection conexion = new NiConection();
    private static SimpleDateFormat format;
    private static Date date;
    private static StringBuilder sb;
    private static String sentence;
    private static int WidthScreen;
    private static int HeightScreen;
    private static NiconAdministratorDAO niconadminDAO;
    private static NiconAdministratorDAO niconadmin;

    /*
     * Este metodo es el encargado de la verificar al inicio del sistema si la aplicacion ha sido ejecutada
     * y en caso tal si esta inicializada.
     */
    public static boolean VerifyStateSystem() {
        niconadmin = new NiconAdministratorDAO();
        return niconadmin.verifyStateNiconAdmin();
    }

    public static void ActiveSystem(String nombres, String apellidos, String direccion, String ciudad, String celular, String email, String loggin, String pass) {
        try {

            String password = EncryptationSecurityService(pass);
            NiconAdministrator admin = new NiconAdministrator(nombres, apellidos, direccion, ciudad, celular, email, loggin, password);
            niconadminDAO = new NiconAdministratorDAO(admin);
            niconadminDAO.createAdministrator();
            NiconLoginSystem init = new NiconLoginSystem(NiconAdministrator.GetDataAdmin());
            init.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error en active system");
        }
    }

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

    /*
     * Este metodo obtiene un momento del tiempo y es formateado para usar como informacion del sistema
     * se usa para llevar un registro de actividades y puede ser invocado desde cualquier lugar del
     * sistema
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

    public static String DateFormatSingle(Date ToFormat) {
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

    /*
     * Este metodo usa el sistema de encrptacion MD5 para darle seguridad a las contraseñas
     * usadas en el sistema. recibe como parametro la frase a encrpitar y retorna el valor
     * encriptado que produce el algortimo MD5
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

    /*
     * Este metodo hace el calculo de el tamaño de la pantalla. retorna un array con los datos indexado asi:
     * WidhtScreen=0 HeightScreen=1.
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
}