/**
 * CopyRigth (C) 2013 NiconSystem Incorporated. 
 * 
 * NiconSystem Inc.
 * Cll 9a#6a-09 Florida Valle del cauca Colombia
 * 318 437 4382
 * fredefass01@gmail.com
 * desarrollador-mantenedor: Frederick Adolfo Salazar Sanchez.
 */

package org.Nicon.Personal.GuiUser.Nicon;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.*;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Obj.NiconAdministrator;
import org.Nicon.Personal.LibCore.Sbin.Init;
import org.Nicon.Personal.LibCore.Sbin.NiconSystemAdmin;

/***
 * NiconLogiSystem es el sistema de logeo de NiconPersonal, provee una interfaz grafica simple que ofrece un
 * inicio rapidos, seguro y eficiente, NiconLoginSystem propone un ingreso de máximo 3 intentos en los cuales
 * sino inicia correctamente el sistema se cerrará, NiconLoginSystem extiende Jdialog para mostrar sus 
 * componentes graficos.
 * 
 * @author Frederick Adolfo Salazar Sanchez
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
    
    private boolean accesControl;
    private String inputPass;


    /**
     * El constructor por defecto debe recibir u objeto del Tipo NiconAdministrator con el cual se mostrarán 
     * datos basicos del administrador del sistema para poder visualizarlos de forma predeterminada al inicio
     * del sistema.
     * @param DataAdmin 
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

    
    private void InitComponentes() {
        /**
         * se obtiene el lenguaje que usará el sistema por defecto el cual ajustará todos el lenguaje
         * de los componentes obteniendo los valores del objeto properties cargado previamente 
         */
        Languaje = GlobalConfigSystem.getLanguajeProperties();

        A1 = new Font("Verdana", 0, 45);
        A2 = new Font("Verdana", 0, 20);

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

    /**
     * este metodo es el encargado de validar el ingreso del usuario, obtiene el password que el usuario ha
     * ingresado en el campo de texto y usa el API de NiconsystemAdmin para validar su acceso, en caso de que
     * los datos ingresasdos sean nulos o no sean compatibles mostrará un mensaje de error en la parte inferior
     * del JDialog, en caso de que el numero de intentos fallidos sea de 3 entonces cerrárá la aplicacion.
     */
    private void NiconVerifyIdentity() {
        inputPass = String.valueOf(InputPassword.getPassword());
        
            if (inputPass.equals("")) {
                ShowMessageLogin(1);
            } else {
                accesControl = NiconSystemAdmin.verifyCredentialsUser(inputPass);
                    if (accesControl) {                    
                            Init.Initialize();
                            GuiNicon FrontEnd = new GuiNicon(DataAdmin);
                            FrontEnd.setVisible(true);
                            dispose();                   
                    } else {
                        if (CountDefError == 2) {
                                System.exit(0);
                        } else {
                                ShowMessageLogin(0);
                                TimeShowAccesInf.start();
                                InputPassword.setText("");
                                CountDefError++;
                        }
                    }
            }
    }

    /**
     * este metodo permite mostrar los mensajes de error al momento de intentar ingresar al sistema
     * @param ErrorID 
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