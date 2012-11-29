/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.GuiUser.Nicon;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Obj.NiconAdministrator;
import org.Nicon.Personal.LibCore.Sbin.NiconSystemAdmin;
import org.Nicon.Personal.LibCore.Sbin.WidgetUsefulInterface;
import org.Nicon.Personal.LibCore.i18n.Nicon_i18n;

/**
 *Este sera una widgte que se posisionarà en el escritorio del usuario, en una fase inicial solo mostrará la informacion
 * como la hora y la ciudad. luego se podrá mostrar mas informacion, el widget en fase inicial no podra cerrarse solo se
 * cerrará cuando la aplicacion se cierre.
 * cuando el frontend este abierto el widget se cerrará cuando el frontend se cierre el widget aparecerá,tendrá una barra
 * de busqueda que permitirà al usuario hacer busquedas internas en el backend. al econtrar un resultado abrirá el
 * frontend con el resultado de la busqueda.
 */
public class NiconWidgetDesktop extends JDialog implements MouseListener {

    private int Widht = 300;
    private int Height = 180;
    private int[] Point;
    private JPanel WidgetPanel;
    private Color BackgroundColor;
    private Color ForegroundColor;
    private TitledBorder NiconBorder;
    private TitledBorder SearchBorder;
    private JLabel JLHour;
    private JLabel JLDayFormat;
    private JLabel JLDataAdmin;
    private SimpleDateFormat HourFormat;
    private SimpleDateFormat DayFormat;
    private JButton ActiveFrontend;
    private JButton JBSearch;
    private Date time;
    private Font HourFont;
    private JTextField SearchData;
    private Timer Temporizator;
    private String DataSearch = "";
    private String ResourceImages;
    private WidgetUsefulInterface SearchAPI;
    private Nicon_i18n Languaje;
    NiconAdministrator NiconAdmin;
    GuiNicon FrontEnd = new GuiNicon();

    public NiconWidgetDesktop(NiconAdministrator NiconAdmin) {
        this.setSize(Widht, Height);
        this.setUndecorated(true);
        Point = NiconSystemAdmin.DimensionScreen();
        this.setLocation(Point[0] - Widht - 5, Point[1] - Height - 50);
        SearchAPI = new WidgetUsefulInterface();
        ResourceImages = GlobalConfigSystem.getIconsPath();
        this.NiconAdmin = NiconAdmin;
        Languaje=new Nicon_i18n();
        InitWidget();
    }

    private void InitWidget() {       

        NiconAdmin = NiconAdministrator.GetDataAdmin();

        HourFont = new Font("Droid Sans", 0, 45);

        BackgroundColor = new java.awt.Color(35, 35, 35);
        ForegroundColor = Color.white;

        time = new Date();
        HourFormat = new SimpleDateFormat("hh:mm");
        DayFormat = new SimpleDateFormat("EEEE, dd-MMMM-yyyy ");

        WidgetPanel = new JPanel();
        WidgetPanel.setLayout(null);
        WidgetPanel.setBackground(BackgroundColor);

        NiconBorder = BorderFactory.createTitledBorder(GlobalConfigSystem.getTitleAplication());
        NiconBorder.setTitleColor(ForegroundColor);
        NiconBorder.setTitleFont(new Font("Verdana", 0 + Font.ITALIC, 10));

        ActiveFrontend = new JButton();
        ActiveFrontend.setBounds(10, 22, 30, 30);
        ActiveFrontend.setIcon(new javax.swing.ImageIcon(getClass().getResource(ResourceImages + "NiconDash.png")));
        ActiveFrontend.setToolTipText(Languaje.getProperty("TooltipJBActive"));
        ActiveFrontend.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                DesactiveWidgetDesktop();
            }
        });

        SearchBorder = BorderFactory.createTitledBorder("Buscar:");
        SearchBorder.setTitleColor(ForegroundColor);
        SearchBorder.setTitleFont(new Font("Droid Sans", 0 + Font.ITALIC, 10));

        JLHour = new JLabel(HourFormat.format(time));
        JLHour.setBounds(Height - 30, 30, 120, 40);
        JLHour.setForeground(ForegroundColor);
        JLHour.setFont(HourFont);

        JLDayFormat = new JLabel(DayFormat.format(time));
        JLDayFormat.setBounds(80, 75, 400, 22);
        JLDayFormat.setFont(new Font("Droid Sans", 0, 16));
        JLDayFormat.setForeground(ForegroundColor);

        JLDataAdmin = new JLabel(NiconAdmin.getNombres() + " " + NiconAdmin.getApellidos());
        JLDataAdmin.setForeground(ForegroundColor);
        JLDataAdmin.setBounds(50, 100, 400, 20);
        JLDataAdmin.setFont(new Font("Droid Sans", 0, 16));

        SearchData = new JTextField("Type To Search:");
        SearchData.setBounds(12, 143, 250, 22);
        SearchData.setForeground(Color.darkGray);
        SearchData.setFont(new Font("Droid Sans", 0, 9));
        SearchData.addMouseListener(this);

        JBSearch = new JButton();
        JBSearch.setBounds(258, 140, 27, 27);
        JBSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource(ResourceImages + "search.png")));
        JBSearch.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                getInputDataSearch();
            }
        });
        this.getRootPane().setDefaultButton(JBSearch);

        WidgetPanel.setBorder(NiconBorder);
        WidgetPanel.add(SearchData);
        WidgetPanel.add(JLHour);
        WidgetPanel.add(JLDayFormat);
        WidgetPanel.add(JLDataAdmin);
        WidgetPanel.add(ActiveFrontend);
        WidgetPanel.add(JBSearch);
        InitUpdateTime();
        getContentPane().add(WidgetPanel);
    }

    private void DesactiveWidgetDesktop() {
        if (!FrontEnd.isVisible()) {
            dispose();
            FrontEnd.setVisible(true);
            Temporizator.stop();
        }
    }

    private void getInputDataSearch() {
        String data = SearchData.getText();
        if (!data.equals("")) {
            SearchAPI.SearchDataInput(data);
        }

    }

    private void InitUpdateTime() {
        try {
            Temporizator = new Timer(5000, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    time = new Date();
                    JLHour.setText(HourFormat.format(time));
                    WidgetPanel.repaint();
                }
            });
            Temporizator.start();
            Temporizator.setRepeats(true);
        } catch (Exception e) {
            System.out.println("Error en InitUpdateTime() detail:  \n" + e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        SearchData.setText("");
    }

    @Override
    public void mousePressed(MouseEvent me) {
        SearchData.setText("");
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
//        SearchData.setText("Type To Search:");
    }
}
