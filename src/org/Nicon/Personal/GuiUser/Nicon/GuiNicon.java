package org.Nicon.Personal.GuiUser.Nicon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import org.Nicon.Personal.GuiUser.Information.GuiAbout;
import org.Nicon.Personal.GuiUser.NiContacts.GuiContacts;
import org.Nicon.Personal.GuiUser.Nicon.Twitt.PostTwitt;
import org.Nicon.Personal.GuiUser.NiconNotes.GuiAdminNotes;
import org.Nicon.Personal.LibCore.NiconTwitt.NiconTwitt;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Obj.NiconAdministrator;

/*
 * @author Frederick Adolfo Salazar Sanchez
 * @serial NPC00003
 * @version 0.3.5
 * 
 * GuiNicon es la GUI principal del sistema. con un diseño avanzado y contenedor donde todos los componentes de 
 * tipo modulo sera almacenados. se le haran modificaciones con el paso del tiempo siempre buscando la simplicidad
 * y la elegancia.
 * en tiempo futuro el DashBoard tendra notificacion vie internet sobre contactos, notas y noticias
 * 
 */
public class GuiNicon extends JFrame implements ActionListener, MouseListener, WindowListener {

    
    private String ResourceImages;
    private Properties Languaje;
    
    private static int IndexTab;
    private static int selectedIndex = 0;
    private int index;
    public static int WidthScreen;
    public static int HeightScreen;
    
    private Font A1;
    private Font A2;
    private Font A3;
    private Color ForegroundColor;
    
    private JPanel DashPanel;
    private JPanel FrontPanel;
    private JPanel TemporalPanel;
    
    private JLabel NameAdmin;
    private JLabel LastNameAdmin;
    private JLabel AlternativeName;
    private JLabel CurrentDate;
    private JLabel CurrentHour;
    private JLabel CurrentMonth;
    private JLabel JLNicontacts;
    private JLabel JLNiconMail;
    private JLabel JLNiconNotes;
    private JLabel NiconInfo;
    private JLabel NiconBackground;
    private JLabel NiconTwittInformation;
    
    private Date time;
    private SimpleDateFormat format;
    private SimpleDateFormat HourFormat;
    private SimpleDateFormat MontFormat;
    
    private JButton JBcontacts;
    private JButton JBNiconMail;
    private JButton JBNiconNotes;
    private JButton JBNiconTwitt;
    
    private JComboBox JCNiconOptions;
    private TitledBorder NiconBorder;
    private static JTabbedPane JTabs;
    
    private GuiContacts tabContacts;
    private NiconAdministrator NiconAdmin;
    private NiconTwitt twitter;

    public GuiNicon(NiconAdministrator NiconAdmin) {

        setSize(800, 740);
        setTitle(GlobalConfigSystem.getTitleAplication());
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        this.NiconAdmin = NiconAdmin;
        ResourceImages = GlobalConfigSystem.getIconsPath();
        Initcomponents();
        LoadComponents();
        LoadHomePanel();
    }

    public GuiNicon() {
        setSize(800, 740);
        setTitle(GlobalConfigSystem.getTitleAplication());
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        ResourceImages = GlobalConfigSystem.getIconsPath();
        Initcomponents();
        LoadComponents();
        LoadHomePanel();
    }

    private void Initcomponents() {
        
        Languaje=GlobalConfigSystem.getLanguajeProperties();
        String NiconOptions [] = {Languaje.getProperty("MeMenu1"),Languaje.getProperty("MeMenu2"),Languaje.getProperty("MeMenu3"), Languaje.getProperty("MeMenu4")};
        
        A1 = new Font("Verdana", 0, 48);
        A2 = new Font("Verdana", 0, 18);
        A3 = new Font("Verdana", 0, 20);
        
        NiconBorder = BorderFactory.createTitledBorder(GlobalConfigSystem.getTitleAplication());
        NiconBorder.setTitleColor(Color.lightGray);
        NiconBorder.setTitleFont(new Font("Verdana", 0 + Font.ITALIC, 12));

        time = new Date();
        format = new SimpleDateFormat("E-dd");
        HourFormat = new SimpleDateFormat("hh:mm");
        MontFormat = new SimpleDateFormat("MMMM/yyyy");

        DashPanel = new JPanel();
        DashPanel.setBackground(GlobalConfigSystem.getNiconPersonalColorInterface());
        DashPanel.setBounds(0, 0, 680, 650);
        DashPanel.setLayout(null);
        DashPanel.setBorder(NiconBorder);

        JCNiconOptions = new JComboBox(NiconOptions);
        JCNiconOptions.setBounds(580, 30, 180, 25);
        JCNiconOptions.addActionListener(this);

        FrontPanel = new JPanel();
        FrontPanel.setBackground(GlobalConfigSystem.getNiconPersonalColorInterface());
        FrontPanel.setBounds(0, 0, 800, 740);
        FrontPanel.setLayout(null);

        JTabs = new JTabbedPane();
        JTabs.setBounds(10, 40, 765, 660);
        JTabs.addTab("NiconDash", new ImageIcon(getClass().getResource(ResourceImages + "NiconDash.png")), DashPanel);

        this.addWindowListener(this);

    }

    private void LoadComponents() {
        FrontPanel.add(JTabs);
        FrontPanel.add(JCNiconOptions);
        getContentPane().add(FrontPanel);
    }

    /*
     * Este metodo es el encargado de crear el DashBoard de GuiNicon, configurar y ejecutar las acciones
     * básicas de la interfaz principal, hace uso de NiconAdmin a traves de este objeto obtiene toda la
     * informacion correspondiente de el administrador de la suite de información.
     * 
     * Version:  0.1.2
     */
    private void LoadHomePanel() {
//        twitter=new NiconTwitt();
//        InformationTwitterAccount twitterInfo=new InformationTwitterAccount();
//        twitter.getConfigurationAccount();
//        twitterInfo=twitter.getAcountBasicInformation();
        
        NiconAdmin = NiconAdministrator.GetDataAdmin();

        NameAdmin = new JLabel(" " + NiconAdmin.getNombres());
        NameAdmin.setForeground(GlobalConfigSystem.getForegroundDashPanel());
        NameAdmin.setBounds(0, 18, 700, 58);
        NameAdmin.setFont(A1);

        LastNameAdmin = new JLabel("   " + NiconAdmin.getApellidos());
        LastNameAdmin.setForeground(GlobalConfigSystem.getForegroundDashPanel());
        LastNameAdmin.setBounds(0, 67, 200, 30);
        LastNameAdmin.setFont(A3);

        AlternativeName = new JLabel("   " + NiconAdmin.getCiudad());
        AlternativeName.setForeground(GlobalConfigSystem.getForegroundDashPanel());
        AlternativeName.setBounds(0, 97, 400, 30);
        AlternativeName.setFont(A3);

        CurrentDate = new JLabel(format.format(time));
        CurrentDate.setBounds(560, 18, 400, 60);
        CurrentDate.setForeground(GlobalConfigSystem.getForegroundDashPanel());
        CurrentDate.setFont(new Font("Verdana", 0, 55));

        CurrentMonth = new JLabel(MontFormat.format(time));
        CurrentMonth.setBounds(560, 75, 400, 30);
        CurrentMonth.setForeground(GlobalConfigSystem.getForegroundDashPanel());
        CurrentMonth.setFont(new Font("Verdana", 0, 30));

        CurrentHour = new JLabel(HourFormat.format(time));
        CurrentHour.setForeground(GlobalConfigSystem.getForegroundDashPanel());
        CurrentHour.setBounds(560, 113, 400, 30);
        CurrentHour.setFont(new Font("Verdana", 0, 30));

        JBcontacts = new JButton();
        JBcontacts.setBackground(Color.darkGray);
        JBcontacts.setBounds(100, 200, 130, 130);
        JBcontacts.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "NiContactBT.png")));
        JBcontacts.setToolTipText(Languaje.getProperty("TooltipJBContact"));
        JBcontacts.addMouseListener(this);
        JBcontacts.addActionListener(this);

        JLNicontacts = new JLabel("  Nicontacts");
        JLNicontacts.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        JLNicontacts.setFont(this.A2);
        JLNicontacts.setBounds(105, 345, 200, 20);

        JBNiconMail = new JButton();
        JBNiconMail.setBounds(320, 200, 130, 130);
        JBNiconMail.setBackground(Color.darkGray);
        JBNiconMail.setToolTipText(Languaje.getProperty("TooltipJBNiconMail"));
        JBNiconMail.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "NiconMailBT.png")));
        JBNiconMail.addMouseListener(this);

        JLNiconMail = new JLabel(" NiconMail");
        JLNiconMail.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        JLNiconMail.setFont(this.A2);
        JLNiconMail.setBounds(335, 345, 200, 20);

        JBNiconNotes = new JButton();
        JBNiconNotes.setBackground(Color.darkGray);
        JBNiconNotes.setBounds(545, 200, 130, 130);
        JBNiconNotes.setToolTipText(Languaje.getProperty("TooltipJBNiconNotes"));
        JBNiconNotes.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "NiconNotesBT.png")));
        JBNiconNotes.addMouseListener(this);
        JBNiconNotes.addActionListener(this);

        JLNiconNotes = new JLabel(" NiconNotes");
        JLNiconNotes.setForeground(GlobalConfigSystem.getForegroundAplicationText());
        JLNiconNotes.setBounds(555, 345, 200, 20);
        JLNiconNotes.setFont(this.A2);
        
        JBNiconTwitt=new JButton();
        JBNiconTwitt.setToolTipText(Languaje.getProperty("TooltipJBNiconTwitt"));
        JBNiconTwitt.setIcon(new javax.swing.ImageIcon(getClass().getResource(ResourceImages+"NiconTwitt.png")));
        JBNiconTwitt.addActionListener(this);
        JBNiconTwitt.setBounds(690,550,60, 60);
        
//        NiconTwittInformation=new JLabel(twitterInfo.getStatuses()+" Tweets / "+twitterInfo.getFriends()+" Siguiendo / "+twitterInfo.getFollowers()+" Seguidores / "+twitterInfo.getLanguaje());
//        NiconTwittInformation.setForeground(GlobalConfigSystem.getForegroundDashPanel());
//        NiconTwittInformation.setFont(this.A2);
//        NiconTwittInformation.setBounds(10,560,800,25);

        NiconInfo = new JLabel("NiconPersonal is powered By NiconSystem Inc.");
        NiconInfo.setForeground(Color.lightGray);
        NiconInfo.setFont(new Font("Verdana", 2, 10));
        NiconInfo.setBounds(8, 598, 500, 22);

        InitUpdateTime();
        
        DashPanel.add(NameAdmin);
        DashPanel.add(LastNameAdmin);
        DashPanel.add(AlternativeName);
        DashPanel.add(JBcontacts);
        DashPanel.add(JLNicontacts);
        DashPanel.add(JBNiconMail);
        DashPanel.add(JLNiconMail);
        DashPanel.add(JBNiconNotes);
        DashPanel.add(JLNiconNotes);
        DashPanel.add(NiconInfo);
        DashPanel.add(CurrentDate);
        DashPanel.add(CurrentHour);
        DashPanel.add(CurrentMonth);
        DashPanel.add(NiconInfo);
        DashPanel.add(JBNiconTwitt);
//        DashPanel.add(NiconTwittInformation);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == JBcontacts) {
            index = IndexOfTabNicon("NiContacts");
            if (index == -1) {
                tabContacts = new GuiContacts();
                TemporalPanel = tabContacts.CreateGuiContacts();
                AddNewPanel(TemporalPanel, "NiContacts");
            } else {
                JTabs.setSelectedIndex(index);
            }
        }
        if (e.getSource() == JBNiconNotes) {
            index = IndexOfTabNicon("NiconNotes");
            if (index == -1) {
                GuiAdminNotes notes = new GuiAdminNotes();
                TemporalPanel = notes.GuiAdminNotes();
                AddNewPanel(TemporalPanel, "NiconNotes");
            } else {
                JTabs.setSelectedIndex(index);
            }
        }
        if (e.getSource() == JCNiconOptions) {
            index = JCNiconOptions.getSelectedIndex();
            if (index == 1) {
                CloseSession();
            }
            if (index == 3) {
                ExitNiconPersonal();
            }
            if (index == 2) {
                GuiAbout About = new GuiAbout();
                About.setVisible(true);
            }
        }
        if(e.getSource()==JBNiconTwitt){
            PostTwitt post=new PostTwitt();
            post.setVisible(true);
        }
    }

    /*
     *Este metodo es el encargado de agregar un nuevo tab al administrador de pestañas, recibe
     * como parametros el panel a cargar y el nombre que recibirá la pestaña nueva
     */
    public static void AddNewPanel(JPanel panel, String name) {

        try {
            int TotalOpenTabs = JTabs.getTabCount();
            JTabs.addTab(name, panel);
            JTabs.setSelectedIndex(TotalOpenTabs);
            JTabs.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio este error en Addpanel():  " + e);
        }
    }

    /*
     * metodo que remueve un Tab abierto en el dash
     */
    public static void RemoveTabPanel() {
        selectedIndex = JTabs.getSelectedIndex();
        JTabs.remove(selectedIndex);

    }

    /*
     * Metodo que retorna el indice de una pestaña abierta, es usado para no abrir mas pestañas ya abiertas
     */
    public static int IndexOfTabNicon(String Name) {
        IndexTab = JTabs.indexOfTab(Name);
        return IndexTab;
    }

    public static void OpenTabNicon(int index) {
        JTabs.setSelectedIndex(index);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == JBcontacts) {
            JBcontacts.setBackground(Color.gray);
        }
        if (e.getSource() == JBNiconMail) {
            JBNiconMail.setBackground(Color.gray);
        }
        if (e.getSource() == JBNiconNotes) {
            JBNiconNotes.setBackground(Color.gray);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == JBcontacts) {
            JBcontacts.setBackground(Color.darkGray);
        }
        if (e.getSource() == JBNiconMail) {
            JBNiconMail.setBackground(Color.darkGray);
        }
        if (e.getSource() == JBNiconNotes) {
            JBNiconNotes.setBackground(Color.darkGray);
        }
    }

    /*
     * InitUpdateTime es el encargado de mostrar la fecha y hora actual en el dash hace uso de Timer para mantener 
     * actualizada la vista de reloj.
     */
    private void InitUpdateTime() {

        try {
            Timer Temporizator = new Timer(5000, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    time = new Date();
                    CurrentHour.setText(GuiNicon.this.HourFormat.format(GuiNicon.this.time));
                    DashPanel.repaint();
                }
            });
            Temporizator.start();
            Temporizator.setRepeats(true);
        } catch (Exception e) {
            System.out.println("Error en InitUpdateTime() detail:  \n" + e);
        }
    }

    private void CloseSession() {
        this.setVisible(false);
        NiconLoginSystem Restart = new NiconLoginSystem(NiconAdministrator.GetDataAdmin());
        Restart.setVisible(true);
    }

    private void ExitNiconPersonal() {
        System.exit(0);
    }

    private void SetDimenSionScreen() {
        try {
            Dimension Screen = Toolkit.getDefaultToolkit().getScreenSize();
            WidthScreen = Screen.width;
            HeightScreen = Screen.height;
            setSize(WidthScreen / 2, HeightScreen / 1);
            System.out.println("El tamaño del monitor es: " + WidthScreen + " / " + HeightScreen);
        } catch (Exception e) {
        }
    }
    /*
     * metodo en fase experimental, el efecto chameleon se encarga de acondicionar toda la interfaz grafica en base
     * al Background de cada Tab. en fase de experimento que aun no es aplicada oficialmente
     */

    private void ChemeleonColor(Color Background) {
        FrontPanel.setBackground(Background);
        FrontPanel.repaint();
    }

    /*
     * Este metodo es el encargado de activar el widget de escritorio bajo las siguientes reglas:
     * WidgetDesktop isVisible when
     *    1) la ventana es minimizada
     *    2) La ventana es cerrada
     * al cumplirse alguna de las 2 sentencias el frontend pasa a hacerse invisible y activa el
     * NiconWidgetDesktop, este mismo es deshabilitado cuando desde el Widget se ordena al desactivacion
     * y la reactivacion del Frontend.
     */
    private void ActiveNiconWidgetDesktop() {
        NiconWidgetDesktop Widget = new NiconWidgetDesktop(NiconAdmin);
        dispose();
        Widget.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent we) {
    }

    @Override
    public void windowClosing(WindowEvent we) {
        ActiveNiconWidgetDesktop();
    }

    @Override
    public void windowClosed(WindowEvent we) {
    }

    @Override
    public void windowIconified(WindowEvent we) {
        ActiveNiconWidgetDesktop();
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
    }

    @Override
    public void windowActivated(WindowEvent we) {
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
    }
}
