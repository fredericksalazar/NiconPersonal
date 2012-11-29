package org.Nicon.Personal.GuiUser.NiconNotes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import javax.swing.*;

import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.Nicon.Personal.Data.DataNotes;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Obj.NiconNotes;
import org.Nicon.Personal.LibCore.Sbin.DAO.NiconNotesDAO;
import org.Nicon.Personal.LibCore.Sbin.NiconSystemAdmin;

public class GuiAdminNotes extends JPanel implements ActionListener, MouseListener {

    private Font A1;
    private Font A2;
    private Font A3;
    
    private Color InterfaceColor;
    
    private JPanel NiconNotesPanel;
    private JLabel JLtitulo;
    private JLabel JLEditNote;
    private JLabel JLDeleteNote;
    private JLabel JLExportNote;
    private JLabel JLshowState;
    private JLabel JLCreateNote;
    private JLabel JLFecha;
    private JLabel JLinfo;
    
    private JTable ListNotes;
    private DefaultTableModel NotesModel;
    private JScrollPane Jscroll;
    private JScrollPane JSBookNote;
    
    private JComboBox NotesBookList;
    private JTextField JTTitleNote;
    private JTextArea JAdescripcion;
    private JTextArea JTDescriptionNote;
    
    private JButton JBCreateNote;
    private JButton JBEditNote;
    private JButton JBDeleteNote;
    private JButton JBExportNote;
    private JButton JBSaveNote;
    private JButton JBCancelNote;
    
    private JMenuBar JMBarNotes;
    private JMenu   JMNotas;
    private JMenu   JMActions;
    
    private TitledBorder NotesBorder;
    
    private String[] note = new String[2];
    private String ResourceImages;
    private String[] Desc_Book = {"Libro de Notas Personales", "Libro de Notas Universidad", "Libro de Notas Trabajo", "Libro de Notas E-books"};
    private ArrayList ArrayNotes;
    private NiconNotes tmp;
    
    private int index;    
    private int buttonPositionY;
    private int buttonPositionX;
    
    private NiconNotes Note;
    private boolean state;
    private JMenuItem JMICreate;
    private JMenuItem JMIdelete;
    private JMenuItem JMIedit;

    public JPanel GuiAdminNotes() {

        ResourceImages = GlobalConfigSystem.getIconsPath();
        
        InterfaceColor = new Color(255, 71, 0);
        
        buttonPositionY = 56;
        buttonPositionX = 340;

        A1 = new Font("Verdana", 0, 32);
        A2 = new Font("Verdana", 0, 14);
        A3 = new Font("verdana", 2, 15);

        NotesBorder = BorderFactory.createTitledBorder("Mis Notas");
        NotesBorder.setTitleColor(InterfaceColor);
        NotesBorder.setTitleFont(new Font("Verdana", 0 + Font.ITALIC, 12));

        NiconNotesPanel = new JPanel();
        NiconNotesPanel.setLayout(null);
        NiconNotesPanel.setBackground(new Color(35, 35, 35));
        NiconNotesPanel.setBorder(NotesBorder);
        
        JMBarNotes=new JMenuBar();
        JMBarNotes.setBounds(284, 23, 475, 20);
        
        JMNotas=new JMenu("Notas");
        JMActions=new JMenu("Acciones");
        
        JMICreate=new JMenuItem("Crear Nota");
        JMICreate.setToolTipText("Crear una nota ");
        
        JMIdelete=new JMenuItem("Eliminar Nota");
        JMIdelete.setToolTipText("Eliminar una nota");
        
        JMIedit=new JMenuItem("Editar Nota");
        JMIedit.setToolTipText("Editar informacion de una nota");
        
        JMNotas.add(JMICreate);
        JMNotas.add(JMIdelete);
        JMNotas.add(JMIedit);
        
        JMBarNotes.add(JMNotas);
        JMBarNotes.add(JMActions);
        
        
        JBCreateNote = new JButton();
        JBCreateNote.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "NiconNotesAddBT.png")));
        JBCreateNote.setBounds(buttonPositionX, buttonPositionY, 70, 70);
        JBCreateNote.addActionListener(this);

        JLCreateNote = new JLabel("Nueva");
        JLCreateNote.setForeground(InterfaceColor);
        JLCreateNote.setBounds(355, buttonPositionY + 75, 100, 20);
        JLCreateNote.setFont(A2);

        JBEditNote = new JButton();
        JBEditNote.setBounds(buttonPositionX + 100, buttonPositionY, 70, 70);
        JBEditNote.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "NiconEditNote.png")));
        JBEditNote.addActionListener(this);

        JLEditNote = new JLabel("Editar");
        JLEditNote.setForeground(InterfaceColor);
        JLEditNote.setBounds(450, buttonPositionY + 75, 100, 20);
        JLEditNote.setFont(A2);

        JBDeleteNote = new JButton();
        JBDeleteNote.setBounds(buttonPositionX + 200, buttonPositionY, 70, 70);
        JBDeleteNote.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "NiconRemoveBT.png")));
        JBDeleteNote.addActionListener(this);

        JLDeleteNote = new JLabel("Eliminar");
        JLDeleteNote.setForeground(InterfaceColor);
        JLDeleteNote.setBounds(548, buttonPositionY + 75, 150, 20);
        JLDeleteNote.setFont(A2);

        JBExportNote = new JButton();
        JBExportNote.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "NiconExportBT.png")));
        JBExportNote.setBounds(buttonPositionX + 300, buttonPositionY, 70, 70);
        JBExportNote.addActionListener(this);

        JLExportNote = new JLabel("Exportar");
        JLExportNote.setForeground(InterfaceColor);
        JLExportNote.setBounds(648, buttonPositionY + 75, 100, 20);
        JLExportNote.setFont(A2);

        JBSaveNote = new JButton("Guardar");
        JBSaveNote.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "Floppy.png")));
        JBSaveNote.setBounds(420, 490, 130, 35);
        JBSaveNote.addActionListener(this);

        JBCancelNote = new JButton("Cancelar");
        JBCancelNote.setIcon(new ImageIcon(getClass().getResource(ResourceImages + "Stop.png")));
        JBCancelNote.setBounds(560, 490, 130, 35);
        JBCancelNote.addActionListener(this);

        NotesModel = new DefaultTableModel();
        NotesModel.addColumn("Titulo");

        LoadDataBackend();

        NotesBookList = new JComboBox(Desc_Book);
        NotesBookList.setBounds(0, 125, 320, 25);
        NotesBookList.addActionListener(this);

        ListNotes = new JTable(NotesModel);
        ListNotes.getColumnModel().getColumn(0).setPreferredWidth(35);
        ListNotes.setShowGrid(false);
        ListNotes.setSelectionBackground(InterfaceColor);
        ListNotes.setSelectionForeground(Color.BLACK);
        ListNotes.setFont(new Font("Verdana", 0, 15));
        ListNotes.setRowHeight(35);
        ListNotes.addMouseListener(this);
        ListNotes.setTableHeader(null);
        Jscroll = new JScrollPane(this.ListNotes);
        Jscroll.setBounds(5, 20, 280, 550);

        JTTitleNote = new JTextField("Titulo:  ");
        JTTitleNote.setBackground(new Color(255, 255, 200));
        JTTitleNote.setBounds(365,210, 350, 25);
        JTTitleNote.setFont(A3);
        JTTitleNote.setEditable(false);

        JTDescriptionNote = new JTextArea("Descripción:  ");
        JTDescriptionNote.setEditable(false);
        JTDescriptionNote.setLineWrap(true);
        JTDescriptionNote.setBackground(new Color(255, 255, 200));
        JTDescriptionNote.setFont(this.A3);

        JSBookNote = new JScrollPane(this.JTDescriptionNote);
        JSBookNote.setBorder(null);
        JSBookNote.setOpaque(false);
        JSBookNote.setBorder(new BevelBorder(2));
        JSBookNote.setBounds(365,235, 350, 210);

        JLFecha = new JLabel(" Fecha de Creación:  ");

        JLinfo = new JLabel("Notas almacenadas:  " + this.NotesModel.getRowCount());
        JLinfo.setForeground(InterfaceColor);
        JLinfo.setBounds(160, 575, 500, 20);

        NiconNotesPanel.add(JTTitleNote);
        NiconNotesPanel.add(Jscroll);
        NiconNotesPanel.add(JSBookNote);
        NiconNotesPanel.add(JBCreateNote);
        NiconNotesPanel.add(JBEditNote);
        NiconNotesPanel.add(JBDeleteNote);
        NiconNotesPanel.add(JBExportNote);
        NiconNotesPanel.add(JLCreateNote);
        NiconNotesPanel.add(JLEditNote);
        NiconNotesPanel.add(JLDeleteNote);
        NiconNotesPanel.add(JLExportNote);
        NiconNotesPanel.add(JLinfo);
        NiconNotesPanel.add(JMBarNotes);

        return NiconNotesPanel;
    }
    /*
     * Manejamos todos los eventos a los que el usuario tiene acceso
     */

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == JBCreateNote) {
            SetWriteOnlyForm();
        }
        
        if (e.getSource() == JBCancelNote) {
            SetReadOnlyForm();
        }
        
        if (e.getSource() == JBSaveNote) {
            boolean GetAndSaveNote = getAndSaveNote();
            if (GetAndSaveNote == true) {
                SetReadOnlyForm();
                ShowConfirmState(0);
                UpdateCountView();
            } else {
                ShowConfirmState(1);
            }
        }

        if (e.getSource() == JBDeleteNote) {
            index = ListNotes.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(null, "debe seleccionar una nota para eliminar", GlobalConfigSystem.getTitleAplication(), 0);
            } else {
                NiconNotes delete = (NiconNotes) ArrayNotes.get(index);
                DataNotes.deleteNote(delete);
                reloadDataNotesList();
                UpdateCountView();
            }
        }

        if (e.getSource() == JBEditNote) {
            index = ListNotes.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(null, "debe seleccionar una nota para editarla", "NiconPersonal v 0.3.5", 0);
            } else {
                NiconNotes edit = (NiconNotes) ArrayNotes.get(index);
                SetWriteOnlyForm();
                JTTitleNote.setText(edit.getTitulo());
                JTDescriptionNote.setText(edit.getDescripcion());
//        edit.DeleteNote(edit.getcodigo());
            }
        }
        
        if(e.getSource()==this.JBExportNote){
            exportNoteToTxt();
        }
    }

    /*
     * Este metodo es el encargado de obtener el arraya de datos cargados desde Init, posteriormente hace el
     * llamado a el metodo encargado de montar todos los datos cargado a el modelo de datos de Notas para 
     * mostrarlos en la interfaz grafica
     */
    private void LoadDataBackend() {

        ArrayNotes = DataNotes.cloneDataNotes();
        LoadListNotes();
    }

    private void reloadDataNotesList() {
        NotesModel.getDataVector().removeAllElements();
        LoadListNotes();
    }

    /*
     * Se ajusta el panel para el modo de escritura de nota, los componentes de ingreso de informacion se limpian
     * y ponen en modo editar true
     */
    private void SetWriteOnlyForm() {
        JTTitleNote.setText("");
        JTDescriptionNote.setText("");
        JTTitleNote.setEditable(true);
        JTDescriptionNote.setEditable(true);
        JBCreateNote.setEnabled(false);
        JBEditNote.setEnabled(false);
        JBDeleteNote.setEnabled(false);
        JBExportNote.setEnabled(false);
        NiconNotesPanel.add(JBSaveNote);
        NiconNotesPanel.add(JBCancelNote);
        NiconNotesPanel.repaint();
    }

    /*
     * Se ajusta el panel para el modo de lectura, los campos de informacion dejan de ser editables y pasan a ser
     * estaticos y muestra de informacion.
     */
    private void SetReadOnlyForm() {
        JTTitleNote.setText("Titulo");
        JTDescriptionNote.setText("Descripción:");
        JTTitleNote.setEditable(false);
        JTDescriptionNote.setEditable(false);
        JBCreateNote.setEnabled(true);
        JBEditNote.setEnabled(true);
        JBDeleteNote.setEnabled(true);
        JBExportNote.setEnabled(true);
        NiconNotesPanel.remove(JBSaveNote);
        NiconNotesPanel.remove(JBCancelNote);
        NiconNotesPanel.repaint();
    }

    /*
     * Metdo que se encarga de cargar todas las notas almacenadas en el backend, por ahora se una una instruccion for
     * pero esta pensado modificacion para aumentar rendimiento
     */
    private void LoadListNotes() {

        for (int x = 0; x < ArrayNotes.size(); x++) {
            tmp = ((NiconNotes) ArrayNotes.get(x));
            note[0] = tmp.getTitulo();
            NotesModel.addRow(note);
        }
    }
    
    private void exportNoteToTxt(){
        Note=DataNotes.getNote(this.GetSelectedNote());
        NiconNotesDAO NoteDAO=new NiconNotesDAO(Note);
        NoteDAO.exportNoteToTxt();
        NoteDAO=null;
    }

    /*
     * Este metodo es usado al momento de decidir guardar una nueva nota en el backend. obtiene los datos los valida
     * y los envia al Sbin para su posterior almacenamiento seguro
     */
    private boolean getAndSaveNote() {

      state = false;

        try {
            String titulo = JTTitleNote.getText();
            String Nota = JTDescriptionNote.getText();

            if ((titulo.equals("")) || (Nota.equals(""))) {
                JOptionPane.showMessageDialog(null, "Ingrese el titulo o la descripcion de la Nota", GlobalConfigSystem.getTitleAplication(), 0);
            } else {
                NiconNotes Nueva = new NiconNotes(titulo, Nota, NiconSystemAdmin.GetInstantTime());
                int CreateNote = DataNotes.addNote(Nueva);
                if (CreateNote == 0) {
                    state = true;
                    NotesModel.getDataVector().removeAllElements();
                    LoadListNotes();
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un Error en GuiAdminNOtes.GetAndSaveNote() Detail:\n" + e);
        }
        return state;
    }

    private boolean getAndSaveNote(int i) {
        try {
            String titulo = JTTitleNote.getText();
            String Nota = JTDescriptionNote.getText();

            if ((titulo.equals("")) || (Nota.equals(""))) {
                JOptionPane.showMessageDialog(null, "Ingrese el titulo o la descripcion de la Nota", GlobalConfigSystem.getTitleAplication(), 0);
            } else {
                Note = new NiconNotes(titulo, Nota, NiconSystemAdmin.GetInstantTime());
                int CreateNote = DataNotes.editNote(Note);
                if (CreateNote == 0) {
                    state = true;
                    NotesModel.getDataVector().removeAllElements();
                    LoadListNotes();
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrio un Error en GuiAdminNOtes.GetAndSaveNote() Detail:\n" + e);
        }
        return state;
    }


    /*
     * Este metodo se encarga de actualizar el contador de notas del frontend
     */
    private void UpdateCountView() {
        JLinfo.setText("Notas almacenadas:  " + this.NotesModel.getRowCount());
    }

    private int GetSelectedNote() {
        int row = -1;
        row = this.ListNotes.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Para Ejecutar la tarea, seleccione una nota", GlobalConfigSystem.getTitleAplication(), 2);
        }
        return row;
    }

    private void ShowConfirmState(int estate) {
        if (estate == 0) {
            JLshowState = new JLabel("Su nota ha Sido creada Correctamente ...");
        } else {
            JLshowState = new JLabel("Su nota no se creo ocurrió un Error  ...");
            JLshowState.setForeground(Color.red);
        }
        JLshowState.setForeground(InterfaceColor);
        JLshowState.setBounds(320, 400, 500, 18);
        JLshowState.setFont(new Font("Verdana", 2, 20));
        NiconNotesPanel.add(JLshowState);
        NiconNotesPanel.repaint();
        Timer TimeShow = new Timer(4100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                NiconNotesPanel.remove(JLshowState);
                NiconNotesPanel.repaint();
            }
        });
        TimeShow.start();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        index = ListNotes.getSelectedRow();
        tmp = ((NiconNotes) ArrayNotes.get(index));
        JTTitleNote.setText("  Titulo: " + String.valueOf(tmp.getTitulo()));
        JTDescriptionNote.setText("Descripción: \n\n" + String.valueOf(tmp.getDescripcion()));
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
}