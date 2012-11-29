/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.GuiUser.NiContacts;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;
import org.Nicon.Personal.Data.DataContact;
import org.Nicon.Personal.LibCore.Obj.NiContact;
import org.Nicon.Personal.LibCore.Sbin.DAO.NiContactsDAO;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Sbin.NiconSystemAdmin;

/**
 *
 * @author frederick
 */
public class GuiCreateContact extends JFrame implements ActionListener {
    
    private String ResourceImages;

    private Font A1;
    private Font A2;
    private JPanel JPmain;
    private JLabel JLtitulo;
    private JLabel JLInNombres;
    private JTextField JTInNombres;
    private JLabel JLInApellidos;
    private JTextField JTInApellidos;
    private JLabel JLInAlias;
    private JTextField JTInAlias;
    private JLabel JlInDireccion;
    private JTextField JTInDireccion;
    private JLabel JLciudad;
    private JTextField JTciudad;
    private JLabel JLcelular;
    private JTextField JTcelular;
    private JLabel Jlfijo;
    private JTextField JTfijo;
    private JLabel JLemail;
    private JTextField JTemail;
    private JLabel JLfecha;
    private JDateChooser JCfecha;
    private JLabel JLgrupo;
    private JComboBox JCgrupo;
    private JButton JBgrabar;
    private JButton JBcancelar;
    private String nombres;
    private String apellidos;
    private String apodo;
    private String direccion;
    private String ciudad;
    private String celular;
    private String fijo;
    private String email;
    private Date fecha_nac;
    private String group;
    private String NodataInput = "Dato sin asignar";
    private NiContact Contact;
    private String fechaParse;

    public GuiCreateContact() {
        this.setTitle(GlobalConfigSystem.getTitleAplication());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.ResourceImages=GlobalConfigSystem.getIconsPath();
        this.setSize(600, 570);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        InitComponents();
    }

    private void InitComponents() {
        
        
        
        A1 = new Font("Verdana", Font.PLAIN, 32);
        A2 = new Font("Verdana", Font.BOLD, 14);

        JPmain = new JPanel();
        JPmain.setLayout(null);
        JPmain.setBackground(new java.awt.Color(35, 35, 35));
        JPmain.setBounds(0, 0, 600, 570);

        JLtitulo = new JLabel("Nuevo Contacto Nicon");
        JLtitulo.setForeground(Color.white);
        JLtitulo.setFont(A1);
        JLtitulo.setBounds(120, 10, 500, 30);

        JLInNombres = new JLabel("- Ingrese Los Nombres:");
        JLInNombres.setForeground(Color.white);
        JLInNombres.setBounds(20, 90, 200, 20);
        JLInNombres.setFont(A2);

        JTInNombres = new JTextField();
        JTInNombres.setBounds(300, 85, 250, 25);

        JLInApellidos = new JLabel("- Ingrese los Apellidos:");
        JLInApellidos.setForeground(Color.white);
        JLInApellidos.setFont(A2);
        JLInApellidos.setBounds(20, 130, 200, 20);

        JTInApellidos = new JTextField();
        JTInApellidos.setBounds(300, 125, 250, 25);

        JLInAlias = new JLabel("- Ingrese un apodo");
        JLInAlias.setForeground(Color.white);
        JLInAlias.setFont(A2);
        JLInAlias.setBounds(20, 170, 200, 20);

        JTInAlias = new JTextField();
        JTInAlias.setBounds(300, 165, 250, 25);

        JlInDireccion = new JLabel("- Ingrese la Dirección:");
        JlInDireccion.setForeground(Color.white);
        JlInDireccion.setFont(A2);
        JlInDireccion.setBounds(20, 210, 200, 20);

        JTInDireccion = new JTextField();
        JTInDireccion.setBounds(300, 205, 250, 25);

        JLciudad = new JLabel("- Ingrese la ciudad:");
        JLciudad.setForeground(Color.white);
        JLciudad.setFont(A2);
        JLciudad.setBounds(20, 250, 200, 20);

        JTciudad = new JTextField();
        JTciudad.setBounds(300, 245, 250, 25);

        JLcelular = new JLabel("- Ingrese el Número celular:");
        JLcelular.setForeground(Color.white);
        JLcelular.setFont(A2);
        JLcelular.setBounds(20, 290, 250, 20);

        JTcelular = new JTextField();
        JTcelular.setBounds(300, 285, 250, 25);

        Jlfijo = new JLabel("- Ingrese el Número fijo:");
        Jlfijo.setForeground(Color.white);
        Jlfijo.setFont(A2);
        Jlfijo.setBounds(20, 330, 200, 20);

        JTfijo = new JTextField();
        JTfijo.setBounds(300, 325, 250, 25);

        JLemail = new JLabel("- Ingrese el Email:");
        JLemail.setForeground(Color.white);
        JLemail.setFont(A2);
        JLemail.setBounds(20, 370, 200, 20);

        JTemail = new JTextField();
        JTemail.setBounds(300, 365, 250, 25);

        JLfecha = new JLabel("- Ingrese la fecha de Nacimiento:");
        JLfecha.setForeground(Color.white);
        JLfecha.setFont(A2);
        JLfecha.setBounds(20, 410, 300, 20);

        JCfecha = new JDateChooser();
        JCfecha.setBounds(300, 410, 250, 25);

        JLgrupo = new JLabel("- Seleccione el grupo del contacto:");
        JLgrupo.setForeground(Color.white);
        JLgrupo.setFont(A2);
        JLgrupo.setBounds(20, 450, 300, 20);

        String Grupos[] = {"Seleccione un grupo", "Familia", "Amigos", "Trabajo", "Universidad"};
        JCgrupo = new JComboBox(Grupos);
        JCgrupo.setBounds(300, 445, 250, 25);

        JBgrabar = new JButton(" Aceptar ");
        JBgrabar.setIcon(new javax.swing.ImageIcon(getClass().getResource(ResourceImages+"Floppy.png")));
        JBgrabar.addActionListener(this);
        JBgrabar.setBounds(430, 490, 120, 30);

        JBcancelar = new JButton(" Cancelar ");
        JBcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource(ResourceImages+"Stop.png")));
        JBcancelar.addActionListener(this);
        JBcancelar.setBounds(300, 490, 120, 30);

        JPmain.add(JLtitulo);
        JPmain.add(JLInNombres);
        JPmain.add(JTInNombres);
        JPmain.add(JLInApellidos);
        JPmain.add(JTInApellidos);
        JPmain.add(JLInAlias);
        JPmain.add(JTInAlias);
        JPmain.add(JlInDireccion);
        JPmain.add(JTInDireccion);
        JPmain.add(JLciudad);
        JPmain.add(JTciudad);
        JPmain.add(JLcelular);
        JPmain.add(JTcelular);
        JPmain.add(Jlfijo);
        JPmain.add(JTfijo);
        JPmain.add(JLemail);
        JPmain.add(JTemail);
        JPmain.add(JLfecha);
        JPmain.add(JCfecha);
        JPmain.add(JLgrupo);
        JPmain.add(JCgrupo);
        JPmain.add(JBgrabar);
        JPmain.add(JBcancelar);

        this.getContentPane().add(JPmain);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == JBcancelar) {
            int response = JOptionPane.showConfirmDialog(rootPane, "¿ Esta seguro que desea salir?", "NiconPersonal v 0.3.5 APR Venus", JOptionPane.YES_NO_CANCEL_OPTION);
            if (response == 0) {
                this.dispose();
            }
        }
        if (e.getSource() == JBgrabar) {
            SaveDataContact();
        }
    }

    private void SaveDataContact() {
        int state = -1;
        NiContactsDAO ContactDAO=new NiContactsDAO();
        nombres = this.JTInNombres.getText();
        apellidos = this.JTInApellidos.getText();
        apodo = this.JTInAlias.getText();
        direccion = this.JTInDireccion.getText();
        ciudad = this.JTciudad.getText();
        celular = this.JTcelular.getText();
        fijo = this.JTfijo.getText();
        email = this.JTemail.getText();
        fecha_nac = JCfecha.getDate();
        group = String.valueOf(this.JCgrupo.getSelectedItem());

        if (nombres.equals("") || apellidos.equals("") || apodo.equals("") || celular.equals("") || email.equals("") || group.equals("Seleccione un grupo")) {
            JOptionPane.showMessageDialog(null, "Hay datos importantes sin ingresar.");
        } else {
            if (direccion.equals("")) {
                direccion = NodataInput;
            }
            if (ciudad.equals("")) {
                ciudad = NodataInput;
            }
            if (fijo.equals("")) {
                fijo = NodataInput;
            }          
            Contact = new NiContact(ContactDAO.createContactID(), nombres, apellidos, apodo, direccion, ciudad, celular, fijo, email,NiconSystemAdmin.DateFormatSingle(fecha_nac), group);
            DataContact.addContact(Contact);            
            GuiContacts.ReloadDataContactList();
            setVisible(false);
        }
    }
}
