package org.Nicon.Personal.GuiUser.NiContacts;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.Nicon.Personal.LibCore.Obj.NiContact;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.Sbin.DAO.NiContactsDAO;

public class GuiUpdateContact extends JDialog implements ActionListener {
    
  private String ResourceImages;
  private JPanel JPadmin;
  private JLabel JLtitulo;
  private NiContact update = new NiContact();
  private Font A1;
  private JLabel JLcontactID;
  private JLabel JLNombres;
  private JLabel JLcampo;
  private JComboBox JCcampos;
  private JLabel JLdato;
  private JTextField JTdato;
  private JLabel JLicon;
  private JButton JBguardar;
  private JButton JBcancelar;
  private NiContactsDAO ApiContact;

  public GuiUpdateContact(NiContact update) {
    setTitle(GlobalConfigSystem.getNameAplication());
    setSize(500, 350);
    setModal(true);
    setLocationRelativeTo(null);
    ResourceImages=GlobalConfigSystem.getIconsPath();
    this.update = update;
    InitComponents();
    ApiContact=new NiContactsDAO();
  }

  private void InitComponents() {
      
    
    this.A1 = new Font("Verdana", 0, 28);

    this.JPadmin = new JPanel();
    this.JPadmin.setBackground(new java.awt.Color(35, 35, 35));
    this.JPadmin.setLayout(null);

    this.JLtitulo = new JLabel("Actualización de datos");
    this.JLtitulo.setForeground(Color.white);
    this.JLtitulo.setBounds(80, 5, 500, 30);
    this.JLtitulo.setFont(this.A1);

    this.JLcontactID = new JLabel("ContactID:     " + this.update.getCodigo());
    this.JLcontactID.setForeground(Color.white);
    this.JLcontactID.setBounds(50, 60, 500, 20);

    this.JLNombres = new JLabel("Nombres:      " + this.update.getNombres() + " " + this.update.getApellidos());
    this.JLNombres.setForeground(Color.white);
    this.JLNombres.setBounds(50, 80, 600, 20);

    this.JLicon = new JLabel(new ImageIcon(getClass().getResource(ResourceImages+"upd.png")));
    this.JLicon.setBounds(20, 130, 150, 150);

    this.JLcampo = new JLabel("- Que campo desea actualizar:");
    this.JLcampo.setForeground(Color.white);
    this.JLcampo.setBounds(200, 130, 500, 20);

    String[] campos = { "Seleccione un campo", "Apodo", "Celular", "Fijo", "Email", "Direccion", "Ciudad" };
    this.JCcampos = new JComboBox(campos);
    this.JCcampos.setBounds(220, 160, 200, 25);
    this.JCcampos.addActionListener(this);

    this.JLdato = new JLabel("- Ingrese el nuevo dato:");
    this.JLdato.setForeground(Color.white);
    this.JLdato.setBounds(200, 195, 200, 20);

    this.JTdato = new JTextField();
    this.JTdato.setBounds(220, 225, 200, 25);

    this.JBguardar = new JButton("Grabar");
    this.JBguardar.setBounds(370, 280, 120, 30);
    this.JBguardar.setIcon(new ImageIcon(getClass().getResource(ResourceImages+"Floppy.png")));
    this.JBguardar.addActionListener(this);

    this.JBcancelar = new JButton("Cancelar");
    this.JBcancelar.setIcon(new ImageIcon(getClass().getResource(ResourceImages+"Stop.png")));
    this.JBcancelar.setBounds(240, 280, 120, 30);
    this.JBcancelar.addActionListener(this);

    this.JPadmin.add(this.JLtitulo);
    this.JPadmin.add(this.JLNombres);
    this.JPadmin.add(this.JLcontactID);
    this.JPadmin.add(this.JLcampo);
    this.JPadmin.add(this.JCcampos);
    this.JPadmin.add(this.JLdato);
    this.JPadmin.add(this.JTdato);
    this.JPadmin.add(this.JLicon);
    this.JPadmin.add(this.JBguardar);
    this.JPadmin.add(this.JBcancelar);
    getContentPane().add(this.JPadmin);
  }

    @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == this.JBcancelar) {
      int response = JOptionPane.showConfirmDialog(this.rootPane, "se cancelará la actualización.", "NiconPersonal v 0.3.5 APR Venus", 1);
      if (response == 0) {
        setVisible(false);
      }
    }
    if (e.getSource() == this.JBguardar) {
      String campo = (String)this.JCcampos.getSelectedItem();
      String dato = this.JTdato.getText();
      if ((dato.equals("")) || (campo.equals("Seleccione un campo"))) {
        JOptionPane.showMessageDialog(this.rootPane, "Los datos ingresados so incorrectos, campo o dato", "NiconPersonal v 0.1", 0);
        this.JTdato.setText("");
      } else {
        ApiContact.updateContact(this.update.getCodigo(), this.update.getNombres(), campo, dato);
        setVisible(false);
        GuiContacts.ReloadDataContactList();
      }
    }
  }
}