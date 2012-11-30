package org.Nicon.Personal.GuiUser.NiContacts;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.Nicon.Personal.Data.DataContact;
import org.Nicon.Personal.LibCore.Sbin.ExportNiContacts;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Obj.NiContact;
import org.Nicon.Personal.LibCore.Sbin.DAO.NiContactsDAO;

/*
 * GuiNicontacts es la interfaz de frontend encargada de interactuar con el usuario, dotada para administrar toda
 * la informacion de contactos y las posibles herramientas de la que puede ser proveida, se basa en JPanel y 
 * retorna un JPanel con el diseño que posteriormente sera cargado en el Dash de NiconPersonal.
 */
public class GuiContacts extends JPanel implements ActionListener, MouseListener {

    private static String[] VectorContact = new String[2];
    private String DataSearch = "";
    private String ResourceImages = GlobalConfigSystem.getIconsPath();
    
    private int CountAccesLoad = 0;
    private int buttonPositionY;
    private int buttonPositionX;
    private int index;
    
    private Color InterfaceColor;
    
    private TitledBorder NiContactsBorder;
    private JPanel NicontactsPanel;
    
    private Font A1;
    private Font A2;
    
    private JLabel JLNewContact;
    private JLabel JLUpdateContact;
    private JLabel JLDeleteContact;
    private JLabel JLViewContact;
    private JLabel JLExportContact;
    
    private JLabel JLContactID;
    private JLabel JLalias;
    private JLabel JLMovil;
    private JLabel JLFijo;
    private JLabel JLEmail;
    private JLabel JLDireccion;
    private JLabel JLCiudad;
    private JLabel JLGrupo;
    private JLabel JLCountContacts;
    private JLabel JLPhoto;
    
    private JLabel JLNombres;   
    private JLabel JLTmpContactID;    
    private JLabel JLTmpAlias;    
    private JLabel JlTmpMovil;    
    private JLabel JLTmpFijo;    
    private JLabel JLTmEmail;    
    private JLabel JLTmpDireccion;    
    private JLabel JLTmpCiudad;    
    private JLabel JLTmpGrupo;    
    
    private JButton JBnuevoCon;
    private JButton JBupdateCon;
    private JButton JBIdeleteCon;
    private JButton JBdeleteCon;
    private JButton JBseeCon;
    private JButton JBLeft;
    private JButton JBRight;
    
    private JComboBox JCGrupos;
    
    private JMenuBar NiconBar;
    private JMenu JMcontacto;
    private JMenu JMTools;
    private JMenu JMNavegar;
    
    private JMenuItem JMcrearC;
    private JMenuItem JMeditC;
    private JMenuItem JMdelC;
    private JMenuItem JMver;
    private JMenuItem JMexpConTxt;
    private JMenuItem JMexpTxt;
    private JMenuItem JMexpCsv;
    private JMenuItem JMFirst;
    private JMenuItem JMLast;
    private JMenuItem JMNext;
    private JMenuItem JMPrev;
    
    private JSeparator JScontact1;
       
    private static JTable NiContactsTable;
    private static TableRowSorter sorter;
    private static DefaultTableModel Model;
    
    private JScrollPane Jscroll;
    private JTextField JTSearchCon;
    private static ArrayList ListContactData;
    
    private ExportNiContacts export;    
    private NiContact contact;
    private NiContactsDAO contactDAO;    
    private Properties Languaje;
    
    
    public GuiContacts() {
        buttonPositionY = 56;
        buttonPositionX = 340;
    }

    /*
     * Este metodo es el encargado de crear la interfaz grafica de el administrador de contactos, usa un Jpanel
     * para crear y posisionar todos los componentes de la GUI, al crear, posisionar y cargar los datos que serán
     * mmostrados al usuario retorna un objeto JPanel que seraá cargado al grupo de pestañas de la GuiNicon o 
     * DashBoard principal
     */
    public JPanel CreateGuiContacts() {
        
        Languaje=GlobalConfigSystem.getLanguajeProperties();
        export = new ExportNiContacts();

        InterfaceColor = new Color(108, 172, 18);

        NiContactsBorder = BorderFactory.createTitledBorder(Languaje.getProperty("TitledBorderContact"));
        NiContactsBorder.setTitleColor(InterfaceColor);
        NiContactsBorder.setTitleFont(new Font("Verdana", 0 + Font.ITALIC, 12));

        A1 = new Font("Droid Sans", 0, 29);
        A2 = new Font("Droid Sans", 0, 14);

        NicontactsPanel = new JPanel();
        NicontactsPanel.setLayout(null);
        NicontactsPanel.setBackground(new Color(35, 35, 35));
        NicontactsPanel.setBorder(NiContactsBorder);

        NiconBar = new JMenuBar();
        NiconBar.setBounds(284, 23, 475, 20);
        
        JMcontacto = new JMenu(Languaje.getProperty("JMenuContacto"));
        JMcontacto.setToolTipText("Muestra las acciones de los contactos");
        JMcontacto.setMnemonic('c');

        JMTools = new JMenu(Languaje.getProperty("JMenuAcciones"));
        JMTools.setToolTipText("Despliega un mejor de acciones de NiContacts");
        
        JMNavegar=new JMenu(Languaje.getProperty("JMenuVer"));
        JMNavegar.setToolTipText("Despliega herramientas de navegacion de registros");
        
        JMcrearC = new JMenuItem(Languaje.getProperty("JMICrearContacto"));
        JMcrearC.setToolTipText(Languaje.getProperty("TooltipCrearContacto"));
        JMcrearC.setIcon(new javax.swing.ImageIcon(getClass().getResource(ResourceImages+"NiContactAdd.png")));
        JMcrearC.addActionListener(this);

        JMeditC = new JMenuItem(Languaje.getProperty("JMIEditarContacto"));
        JMeditC.setToolTipText(Languaje.getProperty("TooltipEditarContacto"));
        JMeditC.setIcon(new javax.swing.ImageIcon(getClass().getResource(ResourceImages+"NiconEdit.png")));
        JMeditC.addActionListener(this);

        JMdelC = new JMenuItem(Languaje.getProperty("JMIEliminarContacto"));
        JMdelC.setToolTipText(Languaje.getProperty("TooltipEliminarContacto"));
        JMdelC.setIcon(new javax.swing.ImageIcon(getClass().getResource(ResourceImages+"NiconRemove.png")));
        JMdelC.addActionListener(this);
        
        JMver=new JMenuItem(Languaje.getProperty("JMIDetallesContacto"));
        JMver.setToolTipText("Muestra todos los detalles del contacto seleccionado");
        JMver.addActionListener(this);
        
        JMexpConTxt=new JMenuItem(Languaje.getProperty("JMIExportarContacto"));
        JMexpConTxt.setToolTipText("Exporta un contacto seleccionado a un archivo .txt");
        JMexpConTxt.setIcon(new javax.swing.ImageIcon(getClass().getResource(ResourceImages+"NiconExpTxt.png")));
        JMexpConTxt.addActionListener(this);

        JMexpTxt = new JMenuItem("Exportar Lista de contactos a TXT");
        JMexpTxt.setToolTipText("Exportar el listado de contactos a un archivo NiContacts.txt");
        JMexpTxt.addActionListener(this);

        JMexpCsv = new JMenuItem("Exportar Lista de contactos a CSV");
        JMexpCsv.setToolTipText("Exportar el listado de contacttos a un archivo CSV");
        JMexpCsv.addActionListener(this);
        
        JMFirst=new JMenuItem("Ir al Primer contacto");
        JMFirst.setToolTipText("Posisiona el cursor en el primer contacto");
        JMFirst.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                selectFirstContact();
            }
        });
        
        JMLast=new JMenuItem("Ir al último contacto");
        JMLast.setToolTipText("Posisiona el cursor en el ultimo contacto");
        JMLast.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                selectLastContact();
            }
        });
        
        JMcontacto.add(JMcrearC);
        JMcontacto.add(JMeditC);
        JMcontacto.add(JMdelC);
        JMcontacto.add(JMver);
        JMcontacto.addSeparator();
        JMcontacto.add(JMexpConTxt);
        
        JMTools.add(JMexpTxt);
        JMTools.add(JMexpCsv);
        
        JMNavegar.add(JMFirst);
        JMNavegar.add(JMLast);
        
        NiconBar.add(JMcontacto);
        NiconBar.add(JMTools);
        NiconBar.add(JMNavegar);

        JBnuevoCon = new JButton();
        JBnuevoCon.setToolTipText(Languaje.getProperty("ToolTipCrearContacto"));
        JBnuevoCon.setBounds(buttonPositionX, buttonPositionY, 70, 70);
        JBnuevoCon.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "NiconAddContactBT.png")));
        JBnuevoCon.addActionListener(this);

        JLNewContact = new JLabel(Languaje.getProperty("JLNuevoContacto"));
        JLNewContact.setForeground(InterfaceColor);
        JLNewContact.setBounds(355, buttonPositionY + 75, 100, 20);
        JLNewContact.setFont(this.A2);

        JBupdateCon = new JButton();
        JBupdateCon.setToolTipText("Actualizar datos de un contacto");
        JBupdateCon.setBounds(buttonPositionX + 100, buttonPositionY, 70, 70);
        JBupdateCon.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "NiconUpdateBT.png")));
        JBupdateCon.addActionListener(this);

        JLUpdateContact = new JLabel(Languaje.getProperty("JLActualizarContacto"));
        JLUpdateContact.setForeground(InterfaceColor);
        JLUpdateContact.setBounds(442, buttonPositionY + 75, 100, 20);
        JLUpdateContact.setFont(A2);

        JBdeleteCon = new JButton();
        JBdeleteCon.setBounds(buttonPositionX + 200, buttonPositionY, 70, 70);
        JBdeleteCon.setToolTipText("Eliminar un contacto de la lista");
        JBdeleteCon.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "NiconRemoveBT.png")));
        JBdeleteCon.addActionListener(this);

        JLDeleteContact = new JLabel(Languaje.getProperty("JLEliminarContacto"));
        JLDeleteContact.setForeground(InterfaceColor);
        JLDeleteContact.setBounds(548, buttonPositionY + 75, 150, 20);
        JLDeleteContact.setFont(A2);

        JBseeCon = new JButton();
        JBseeCon.setBounds(buttonPositionX + 300, buttonPositionY, 70, 70);
        JBseeCon.setToolTipText("Ver la informacion detallada de un contacto");
        JBseeCon.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "NiconViewBT.png")));
        JBseeCon.addActionListener(this);

        JLViewContact = new JLabel(Languaje.getProperty("JLDetalles"));
        JLViewContact.setForeground(InterfaceColor);
        JLViewContact.setBounds(642, buttonPositionY + 75, 100, 20);
        JLViewContact.setFont(A2);

        JLCountContacts = new JLabel("Total contactos:");
        JLCountContacts.setForeground(InterfaceColor);
        JLCountContacts.setBounds(130, 598, 600, 20);
        
        JBLeft=new JButton(new javax.swing.ImageIcon(getClass().getResource(ResourceImages+"NiconLeftBT.png")));
        JBLeft.setBounds(658, 560, 40, 30);
        JBLeft.setToolTipText("Selecciona el contacto anterior");
        JBLeft.addActionListener(this);
        
        JBRight=new JButton(new javax.swing.ImageIcon(getClass().getResource(ResourceImages+"NiconRightBT.png")));
        JBRight.setBounds(710, 560, 40, 30);
        JBRight.setToolTipText("Selecciona el contacto siguiente");
        JBRight.addActionListener(this);
        
        JTSearchCon = new JTextField("Buscar:");
        JTSearchCon.setBounds(458, 590, 300, 25);
        JTSearchCon.setFont(new Font("Verdana", 2, 13));
        JTSearchCon.setForeground(InterfaceColor);
        JTSearchCon.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                DataSearch += String.valueOf(caracter);
                SearchContactTable(DataSearch);
            }
        });
        JTSearchCon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                GuiContacts.this.JTSearchCon.setText("");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                GuiContacts.this.JTSearchCon.setText("Buscar:");
                DataSearch = "";
            }
        });

        String[] Grupos = {"Ordenar contactos por:", "Listar todos", "Lista de Familia", "Lista de Amigos", "Lista de Trabajo", "Lista de Universidad"};
        JCGrupos = new JComboBox(Grupos);
        JCGrupos.setBounds(4, 570, 280, 25);
        JCGrupos.addActionListener(this);

        Model = new DefaultTableModel();
        Model.addColumn("Nombres");
        Model.addColumn("Alias");

        NiContactsTable = new JTable(Model);
        NiContactsTable.setTableHeader(null);
        NiContactsTable.setRowHeight(29);
        NiContactsTable.setSelectionBackground(InterfaceColor);
        NiContactsTable.setSelectionForeground(Color.BLACK);
        NiContactsTable.setFont(new Font("Verdana", 0, 15));
        NiContactsTable.addMouseListener(this);

        JLPhoto = new JLabel();
        JLPhoto.setBounds(280, 240, 150, 150);
        JLPhoto.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "photo.png")));

        Jscroll = new JScrollPane(NiContactsTable);
        Jscroll.setBounds(5, 20, 280, 550);

        JLNombres = new JLabel();
        JLNombres.setForeground(InterfaceColor);
        JLNombres.setBounds(325, 190, 600, 30);
        JLNombres.setFont(new Font("Verdana", 0, 25));

        JLContactID = new JLabel("ContactID");
        JLContactID.setForeground(Color.lightGray);
        JLContactID.setFont(this.A2);
        JLContactID.setBounds(420, 250, 200, 20);

        JLTmpContactID = new JLabel();
        JLTmpContactID.setForeground(Color.white);
        JLTmpContactID.setFont(this.A2);
        JLTmpContactID.setBounds(500, 250, 200, 20);

        JLalias = new JLabel("Alias");
        JLalias.setForeground(Color.lightGray);
        JLalias.setFont(this.A2);
        JLalias.setBounds(457, 280, 200, 20);

        JLTmpAlias = new JLabel();
        JLTmpAlias.setForeground(Color.white);
        JLTmpAlias.setFont(this.A2);
        JLTmpAlias.setBounds(500, 280, 600, 20);

        JLMovil = new JLabel("Móvil");
        JLMovil.setForeground(Color.lightGray);
        JLMovil.setFont(this.A2);
        JLMovil.setBounds(454, 310, 200, 20);

        JlTmpMovil = new JLabel();
        JlTmpMovil.setForeground(Color.white);
        JlTmpMovil.setBounds(500, 310, 500, 20);
        JlTmpMovil.setFont(this.A2);

        JLFijo = new JLabel("Fijo");
        JLFijo.setForeground(Color.lightGray);
        JLFijo.setFont(this.A2);
        JLFijo.setBounds(464, 340, 200, 20);

        JLTmpFijo = new JLabel();
        JLTmpFijo.setForeground(Color.white);
        JLTmpFijo.setFont(this.A2);
        JLTmpFijo.setBounds(500, 340, 600, 20);

        JLEmail = new JLabel("Correo-e");
        JLEmail.setForeground(Color.lightGray);
        JLEmail.setFont(this.A2);
        JLEmail.setBounds(427, 370, 200, 20);

        JLTmEmail = new JLabel();
        JLTmEmail.setForeground(Color.white);
        JLTmEmail.setFont(this.A2);
        JLTmEmail.setBounds(500, 370, 600, 20);

        JLDireccion = new JLabel("Residencia");
        JLDireccion.setForeground(Color.lightGray);
        JLDireccion.setFont(this.A2);
        JLDireccion.setBounds(417, 400, 200, 20);

        JLTmpDireccion = new JLabel();
        JLTmpDireccion.setForeground(Color.white);
        JLTmpDireccion.setFont(this.A2);
        JLTmpDireccion.setBounds(500, 400, 500, 20);

        JLCiudad = new JLabel("Ciudad");
        JLCiudad.setForeground(Color.lightGray);
        JLCiudad.setFont(this.A2);
        JLCiudad.setBounds(440, 430, 200, 20);

        JLTmpCiudad = new JLabel();
        JLTmpCiudad.setForeground(Color.white);
        JLTmpCiudad.setFont(this.A2);
        JLTmpCiudad.setBounds(500, 430, 600, 20);

        JLGrupo = new JLabel("Grupo");
        JLGrupo.setForeground(Color.lightGray);
        JLGrupo.setFont(this.A2);
        JLGrupo.setBounds(445, 460, 200, 20);

        JLTmpGrupo = new JLabel();
        JLTmpGrupo.setForeground(Color.white);
        JLTmpGrupo.setBounds(500, 460, 600, 20);
        JLTmpGrupo.setFont(this.A2);

        ListContactData = new ArrayList();
        
        getArrayListDataContact();

        JLCountContacts.setText("Total contactos:   " + String.valueOf(Model.getRowCount()));

        NicontactsPanel.add(NiconBar);
        NicontactsPanel.add(JBnuevoCon);
        NicontactsPanel.add(JBupdateCon);
        NicontactsPanel.add(JLNewContact);
        NicontactsPanel.add(JLUpdateContact);
        NicontactsPanel.add(JBdeleteCon);
        NicontactsPanel.add(JLDeleteContact);
        NicontactsPanel.add(JBseeCon);
        NicontactsPanel.add(JLViewContact);
        NicontactsPanel.add(Jscroll);
        NicontactsPanel.add(JTSearchCon);
        NicontactsPanel.add(JCGrupos);
        NicontactsPanel.add(JLNombres);
        NicontactsPanel.add(JLContactID);
        NicontactsPanel.add(JLTmpContactID);
        NicontactsPanel.add(JLalias);
        NicontactsPanel.add(JLTmpAlias);
        NicontactsPanel.add(JLMovil);
        NicontactsPanel.add(JlTmpMovil);
        NicontactsPanel.add(JLFijo);
        NicontactsPanel.add(JLTmpFijo);
        NicontactsPanel.add(JLEmail);
        NicontactsPanel.add(JLTmEmail);
        NicontactsPanel.add(JLDireccion);
        NicontactsPanel.add(JLTmpDireccion);
        NicontactsPanel.add(JLCiudad);
        NicontactsPanel.add(JLTmpCiudad);
        NicontactsPanel.add(JLGrupo);
        NicontactsPanel.add(JLTmpGrupo);
        NicontactsPanel.add(JLCountContacts);
        NicontactsPanel.add(JLPhoto);
        NicontactsPanel.add(JBLeft);
        NicontactsPanel.add(JBRight);

        return NicontactsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // se ejecuta el metodo cuando el usuario requiere crear un nuevo contacto del sistema
        if (e.getSource() == JBnuevoCon || e.getSource() == JMcrearC) {
            GuiCreateContact nuevo = new GuiCreateContact();
            nuevo.setVisible(true);
        }
        
        //se ejecuta el metodo cuando el usuario requiere eliminar un contacto del sistema
        if (e.getSource() == JBdeleteCon || e.getSource() == JMdelC) {
            int del = this.getSelectedIndexTable();
            if (del > -1) {
                DataContact.deleteContact(del);
                updateDataToJTable();
            }
        }
        
        // se hace la llamada al metodo cuando el usuario requiere editar un contacto del sistema
        if (e.getSource() == JBupdateCon || e.getSource() == JMeditC) {
            int upd = this.getSelectedIndexTable();
            if (upd > -1) {
                NiContact UPD = (NiContact) ListContactData.get(upd);
                GuiUpdateContact updatecon = new GuiUpdateContact(UPD);
                updatecon.setVisible(true);
            }
        }
        
        // se ejecuta la llamada al metodo de viewer cuando el usuario desea ver un contacto
        if (e.getSource() == JBseeCon) {
            int Viw = this.getSelectedIndexTable();
            if (Viw > -1) {
                NiContact vista = (NiContact) ListContactData.get(Viw);
                GuiViewContact view = new GuiViewContact(vista);
                view.setVisible(true);
            }
        }
        
        // se hace la llamada al metodo encargado de exportar informacion de un contacto
        if(e.getSource()==JMexpConTxt){
            exportSelectedContactTxt();
        }

            
        if (e.getSource() == JMexpTxt) {
            try {
                export.exportNiContactsToTxt();
            } catch (Exception ex) {
                Logger.getLogger(GuiContacts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == JMexpCsv) {
            export.exportNiContactsListToCvs();
        }
        
        if(e.getSource()==JBRight){
            this.showNextContact();
        }
        if(e.getSource()==JBLeft){
            this.showPreviousContact();
        }
    }

    
    /*
     * Este metodo se encuentra en fase inicial, es el encargado de buscar segun los parametros recibidos la informacion
     * basica de un contacto dentro de la lista de contactos existente en el frontEnd. hace uso de un algoritmo simple
     * de busqueda de informacion y va a ser modificado y mejorado en futuras versiones.
     */
    public int SearchContactTable(String car) {
        int index = 0;
        for (int i = 0; i < Model.getRowCount(); i++) {
            String tmp = (String) Model.getValueAt(i, 0);
            if (tmp.contains(car)) {
                NiContactsTable.changeSelection(i, 0, false, false);
                NiContact bus = (NiContact) ListContactData.get(i);
                ShowDataContact(bus);
                index = i;
                break;
            }
        }
        return index;
    }

    /*
     * Este metodo se encarga de austar la informacion de un contacto seleccionado en la
     * NiContactsTable en el panel de vision. recibe como parametro un objeto de tipo
     * NiContact y obtiene los datos para austar la presentacion.
     */
    public void ShowDataContact(NiContact Tmp) {
        JLNombres.setText(Tmp.getNombres() + " " + Tmp.getApellidos());
        JLTmpContactID.setText(String.valueOf(Tmp.getCodigo()));
        JLTmpAlias.setText(Tmp.getApodo());
        JlTmpMovil.setText(Tmp.getCelular());
        JLTmpFijo.setText(Tmp.getFijo());
        JLTmEmail.setText(Tmp.getEmail());
        JLTmpDireccion.setText(Tmp.getDireccion());
        JLTmpCiudad.setText(Tmp.getCiudad());
        JLTmpGrupo.setText(Tmp.getGrupo());
    }

    /*
     * este metodo se encarga de obtener el indice de la fila seleccionada por el usuario, es usada para
     * poder ejecutar las sentencias que se requieran por parte de cada objeto.
     * 
     * retorna un numero int con el indice de la fila seleccionada
     */
    private int getSelectedIndexTable() {
        index = -1;
        index = NiContactsTable.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún contacto", GlobalConfigSystem.getTitleAplication(), JOptionPane.ERROR_MESSAGE);
        }
        return index;
    }
    

    /*
     * Este metodo es usado para cargar todos los contactos almacenados en el backend a un ayyar de datos
     * estos datos posteriormente seran cargados al frontEnd a traves de el metodo LoadListContact().
     * tiene una variable de tipo int que se encarga de contabilizar el numero de veces que ha sido llamado
     * este metodo desde otras interfaces, en caso de ser igual a cero quiere decir que es la primer llamada
     * y pasa a cargar el array de datos obtenido desde Init. en caso de ser 1 omite la carga de datos para 
     * no rellenar con los mismos datos el array
     */
    private void getArrayListDataContact() {
        if (CountAccesLoad == 0) {
            ListContactData = DataContact.CloneData();
        }
        CountAccesLoad++;
        loadDataToJTable();
    }
    

    /*
     * Este metodo es el encargado de cargar la informacion de los contactos almacenados en el backend y cargarlos un 
     * listado de datos. posteriormente es cargado al backend haciendo uso de una JTable.
     * este metodo puede ser mejorado en versiones posteriores.
     */
    private static void loadDataToJTable() {

        NiContact tmp = new NiContact();

        try {
            for (int x = 0; x < ListContactData.size(); x++) {
                tmp = (NiContact) ListContactData.get(x);
                VectorContact[0] = tmp.getNombres();
                VectorContact[1] = tmp.getApodo();
                Model.addRow(VectorContact);
            }
        } catch (Exception e) {
            System.out.println("Ocurrio el siguiente Error en CuiContacts.LoadListContacts() detail: " + e.getStackTrace());
        }
    }

    /*
     * este metodo tiene como funcion hacer una limpieza de datos almacenados en las variables de almacenamiento
     * posteriormente hace una recarga de datos desde el backend y los carga directamente al frontend.
     */
    public static void updateDataToJTable() {

        try {
            Model.getDataVector().removeAllElements();
            loadDataToJTable();
            NiContactsTable.clearSelection();
        } catch (Exception e) {
            System.out.println("Ocurrio el siguiente ERROR en GuiContacts.RefreshDataContact() detail: " + e);
        }
    }
    
    private void exportSelectedContactTxt(){        
        index=getSelectedIndexTable();
        contactDAO=new NiContactsDAO(DataContact.getContact(index));
        contactDAO.exportContactToFileTxt();
    }
    
    public void selectFirstContact(){
        try{
           contact=DataContact.getFirstContact(); 
           ShowDataContact(contact);
           NiContactsTable.changeSelection(0, 0, false, false);
           contact=null;
        }catch(Exception e){
            
        }
    }
    
    private void showNextContact(){
        index=NiContactsTable.getSelectedRow();
            if(index==-1){
                this.selectFirstContact();
            }else{
                contact=DataContact.getContact(index++);
                ShowDataContact(contact);
                NiContactsTable.changeSelection(index++, 0, false, false);
            }
    }
    
    private void showPreviousContact(){
        index=NiContactsTable.getSelectedRow();
            if(index>NiContactsTable.getRowCount()){
                this.selectLastContact();
            }else{
                contact=DataContact.getContact(index--);
                ShowDataContact(contact);
                NiContactsTable.changeSelection(index--, 0, false, false);
            }
    }
    
    public void selectLastContact(){
        try{
           contact=DataContact.getLastContact();
           ShowDataContact(contact);
           NiContactsTable.changeSelection(NiContactsTable.getRowCount()-1, 0, false, false);
           contact=null;
        }catch(Exception e){
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == NiContactsTable) {
            int IndexSelected = NiContactsTable.getSelectedRow();
            NiContact Tmp = (NiContact) ListContactData.get(IndexSelected);
            ShowDataContact(Tmp);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public Color GetBackgroundColor() {
        return this.InterfaceColor;
    }
}