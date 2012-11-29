package org.Nicon.Personal.GuiUser.NiContacts;

import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.Nicon.Personal.LibCore.Obj.NiContact;
import org.Nicon.Personal.LibCore.Sbin.NicondateEvaluator;
import org.Nicon.Personal.LibCore.Sbin.WidgetUsefulInterface;

public class GuiViewContact extends JDialog
  implements ActionListener
{
  private AWTUtilities.Translucency support;
  private AWTUtilities utiliti;
  private Font A1;
  private Font A2;
  private JPanel JPView;
  private JButton JBsalir;
  private JLabel JLNombres;
  private NiContact contacto;
  private JLabel JLubicacion;
  private JLabel JLMovil;
  private JLabel JLFijo;
  private JLabel JLemail;
  private JLabel JLedad;
  private NicondateEvaluator DateEvaluator;
  private JLabel JLgrupo;
  private JLabel JLAlias;
  private String Mensaje;

  public GuiViewContact(NiContact contacto)
  {
    this.contacto = contacto;
    setSize(800, 740);
    setLocationRelativeTo(null);
    setModal(true);
    setUndecorated(true);
    InitComponent();
  }

  private void InitComponent() {
    try {
      boolean translucencySupported = AWTUtilities.isTranslucencySupported(AWTUtilities.Translucency.TRANSLUCENT);
      if (translucencySupported == true) {
        AWTUtilities.setWindowOpacity(this, 0.94F);
        AWTUtilities.setWindowOpaque(this, true);
      }
      this.DateEvaluator = new NicondateEvaluator();

      this.A1 = new Font("Droid Sans", 0, 43);
      this.A2 = new Font("Droid Sans", 0, 22);

      this.JPView = new JPanel();
      this.JPView.setLayout(null);
      this.JPView.setBackground(new Color(35, 35, 35));
      this.JPView.setSize(700, 500);

      this.JBsalir = new JButton("Salir");
      this.JBsalir.setBackground(Color.white);
      this.JBsalir.setBounds(650, 680, 120, 35);
      this.JBsalir.addActionListener(this);

      this.JLNombres = new JLabel(this.contacto.getNombres() + " " + this.contacto.getApellidos());
      this.JLNombres.setBounds(80, 100, 700, 50);
      this.JLNombres.setForeground(new Color(116, 175, 19));
      this.JLNombres.setFont(this.A1);

      this.JLAlias = new JLabel(this.contacto.getApodo());
      this.JLAlias.setForeground(new Color(116, 175, 19));
      this.JLAlias.setBounds(80, 155, 500, 30);
      this.JLAlias.setFont(new Font("Verdana", 0, 25));

      this.JLubicacion = new JLabel("Vive en :        " + this.contacto.getCiudad() + " / " + this.contacto.getDireccion());
      this.JLubicacion.setForeground(new Color(116, 175, 19));
      this.JLubicacion.setBounds(150, 250, 700, 25);
      this.JLubicacion.setFont(this.A2);

      this.JLMovil = new JLabel("Llamar a:        Celular  " + this.contacto.getCelular());
      this.JLMovil.setForeground(new Color(116, 175, 19));
      this.JLMovil.setFont(this.A2);
      this.JLMovil.setBounds(145, 310, 700, 25);

      this.JLFijo = new JLabel("Fijo      " + this.contacto.getFijo());
      this.JLFijo.setForeground(new Color(116, 175, 19));
      this.JLFijo.setFont(this.A2);
      this.JLFijo.setBounds(318, 345, 400, 25);

      this.JLemail = new JLabel("Mensajes a:        " + this.contacto.getEmail());
      this.JLemail.setForeground(new Color(116, 175, 19));
      this.JLemail.setFont(this.A2);
      this.JLemail.setBounds(115, 405, 1000, 25);

      this.JLgrupo = new JLabel("Pertenece a:       grupo de " + this.contacto.getGrupo());
      this.JLgrupo.setForeground(new Color(116, 175, 19));
      this.JLgrupo.setFont(this.A2);
      this.JLgrupo.setBounds(115, 470, 1000, 25);

      this.JLedad = new JLabel();
      this.JLedad.setForeground(new Color(116, 175, 19));
      this.JLedad.setFont(new Font("Verdana", 0, 15));
      this.JLedad.setBounds(50, 550, 1000, 25);

      this.JLedad.setText(this.DateEvaluator.CalculateBirthDay(this.contacto.getFecha_nac()));

      this.JPView.add(this.JBsalir);
      this.JPView.add(this.JLNombres);
      this.JPView.add(this.JLAlias);
      this.JPView.add(this.JLubicacion);
      this.JPView.add(this.JLMovil);
      this.JPView.add(this.JLFijo);
      this.JPView.add(this.JLemail);
      this.JPView.add(this.JLedad);
      this.JPView.add(this.JLgrupo);      
      getContentPane().add(this.JPView);
    }
    catch (Exception e) {
      System.out.println(e);
    }
  }

    @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == JBsalir)
      setVisible(false);
  }
}