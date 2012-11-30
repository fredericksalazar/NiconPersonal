/************************************************************************
 * NiconPersonal v 0.2.1 All Rights Reserved (C) 2012 NiconSystem Inc.  *
 * Florida valle del cauca 318 437 4382 / fredefass01@gmail.com         *
 * NiconPersonal es desarrollado por NiconSystem Inc. todos los derechos*
 * reservados, la copia, distribución o comercialización sin una autori *
 * zación debida sera considerada como ilegal.                          *
 * NiconPersonal es diseñada, desarrollada y mantenida por el Ingeniero *
 * Frederick Adolfo Salazar Sanchez.                        *
 *                                                                      *
 * Esta clase NiconLogin es la encargada de administrar el incio        *
 * de sesion grafica hace uso de la clase SystemAdmin para la gestion y *
 * Verificacion de ingreso.                                             *
 * **********************************************************************/
package org.Nicon.Personal.GuiUser.Nicon;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.*;
import org.Nicon.Personal.Data.DataContact;
import org.Nicon.Personal.Data.DataNotes;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Obj.NiconAdministrator;
import org.Nicon.Personal.LibCore.Sbin.Init;
import org.Nicon.Personal.LibCore.Sbin.NiconSystemAdmin;

/*
 * NiconLoginSystem es la interfaz mediante la cual se le permite al usuario ingresar al frontend de NiconPersonal
 * hace uso de las clases de administracion de seguridad para la encriptacion y verificacion de los campos de ingreso
 *  
 */
public class NiconLoginSystem extends JDialog implements ActionListener {

    private NiconAdministrator DataAdmin;
    private JPanel LoginPanel;
    private JLabel LoginTitle;
    private JLabel NameAdmin;
    private JLabel LoginInformation;
    private JLabel AccesInformation;
    private Font A1;
    private Font A2;
    private JPasswordField InputPassword;
    private JButton ButtonAcces;
    private int CountDefError;
    private String strDefError;
    private Timer TimeShowAccesInf;
    private Properties Languaje;


    /*
     * Recibimos como parametro una objeto de tipo Administrador, ello para poder hacer un saludo mas personalizado
     * la ventana de login viene sin decoracion de borde y es mas minimalista simple y rapida. se tiene como regla
     * que llevara un contador de intentos fallidos, al momento de causar 3 intentos fallidos se cerrará la aplicacion  
     */
    public NiconLoginSystem(NiconAdministrator DataAdmin) {
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        this.DataAdmin = DataAdmin;
        CountDefError = 0;
        InitComponentes();
        LoadComponentes();
        setVisible(true);
    }

    /*
     * Se incia el diseño de la interfaz gráfica de Login, sera simple pero elegante
     * al momento de ingresar tendra solo 3 oportunidades de logeo. de lo contrario la
     * libreta se cerrara
     */
    private void InitComponentes() {

        Languaje = GlobalConfigSystem.getLanguajeProperties();

        A1 = new Font("Droid", 0, 45);
        A2 = new Font("Droid Sans", 0, 20);

        LoginPanel = new JPanel();
        LoginPanel.setLayout(null);
        LoginPanel.setBackground(GlobalConfigSystem.getNiconPersonalColorInterface());

        LoginTitle = new JLabel(Languaje.getProperty("JLLoginTitle"));
        LoginTitle.setBounds(10, 8, 400, 50);
        LoginTitle.setForeground(new java.awt.Color(18, 151, 220));
        LoginTitle.setFont(A1);

        NameAdmin = new JLabel("<html>" + DataAdmin.getNombres() + "<br>" + DataAdmin.getApellidos() + "</br></html>");
        NameAdmin.setBounds(30, 55, 400, 80);
        NameAdmin.setFont(A2);
        NameAdmin.setForeground(Color.white);

        LoginInformation = new JLabel("Powered By NiconSystem Inc.                                     " + GlobalConfigSystem.getTitleAplication());
        LoginInformation.setBounds(10, 230, 500, 20);
        LoginInformation.setFont(new Font("Droid Sans", Font.ITALIC, 11));
        LoginInformation.setForeground(new java.awt.Color(107, 183, 0));

        InputPassword = new JPasswordField();
        InputPassword.setBounds(80, 160, 350, 25);
        InputPassword.setForeground(Color.gray);
        InputPassword.setToolTipText(Languaje.getProperty("TooltipInputPassword"));

        AccesInformation = new JLabel();
        AccesInformation.setBounds(80, 195, 400, 25);
        AccesInformation.setForeground(Color.yellow);
        AccesInformation.setFont(new Font("Droid Sans", Font.ITALIC, 15));

        ButtonAcces = new JButton();
        ButtonAcces.setBounds(435, 158, 30, 30);
        ButtonAcces.addActionListener(this);
        ButtonAcces.setToolTipText(Languaje.getProperty("TooltipButtonOK"));
        ButtonAcces.setIcon(new ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath() + "NiconOK.png")));
        this.getRootPane().setDefaultButton(ButtonAcces);
    }

    private void LoadComponentes() {
        LoginPanel.add(LoginTitle);
        LoginPanel.add(NameAdmin);
        LoginPanel.add(InputPassword);
        LoginPanel.add(ButtonAcces);
        LoginPanel.add(LoginInformation);
        getContentPane().add(LoginPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ButtonAcces) {
            NiconVerifyIdentity();
        }
    }

    /*
     * Este metodo es el encargado de verificar la credencial de ingreso del usuario. hace uso de las librerias
     * de NiconSystemAdmin para la verificacion de identidad.
     */
    private void NiconVerifyIdentity() {

        String Input = String.valueOf(InputPassword.getPassword());

        if (Input.equals("")) {
            ShowMessageLogin(1);
        } else {
            boolean accesControl = NiconSystemAdmin.verifyCredentialsUser(Input);
            System.out.println("Iniciando verificación de credenciales de seguridad ....");

            if (accesControl == true) {
                System.out.println("Verificación terminada, acceso concedido ...");
                Init.Initialize();
                GuiNicon FrontEnd = new GuiNicon(DataAdmin);
                FrontEnd.setVisible(true);
                dispose();
            } else {
                if (CountDefError == 2) {
                    System.out.println("El acceso ha sido denegado ... el sistema terminara ahora");
                    System.exit(0);
                } else {
                    System.out.println("Verficacion terminada, Acceso Denegado ...");
                    ShowMessageLogin(0);
                    TimeShowAccesInf.start();
                    InputPassword.setText("");
                    CountDefError++;
                }
            }
        }
    }

    /*
     * El metodo ShowMessageLogin es el encargado de mostrar una informacion sobre el acceso al FrontEnd, pueden darse
     * dos casos 1 la contraseña no ingresada o contraseña invalida.
     */
    private void ShowMessageLogin(int ErrorID) {
        if (ErrorID == 0) {
            strDefError = Languaje.getProperty("JLErrorLogin1");
        } else {
            strDefError = Languaje.getProperty("JLErrorLogin2");
        }
        AccesInformation.setText(strDefError);
        LoginPanel.add(AccesInformation);
        LoginPanel.repaint();
        TimeShowAccesInf = new Timer(2500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                LoginPanel.remove(AccesInformation);
                LoginPanel.repaint();
            }
        });
    }
}