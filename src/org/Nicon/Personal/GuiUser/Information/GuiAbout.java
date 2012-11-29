package org.Nicon.Personal.GuiUser.Information;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;

public class GuiAbout extends JFrame
{
  private JButton JBcerrar;
  private JLabel jLabel1;
  private JScrollPane jScrollPane1;
  private JTextArea jTextArea1;
  private String ResourceImages;
  
  public GuiAbout()
  {
    initComponents();
  }

  private void initComponents()
  {
    ResourceImages=GlobalConfigSystem.getIconsPath();
    
    this.jLabel1 = new JLabel();
    this.JBcerrar = new JButton();
    this.jScrollPane1 = new JScrollPane();
    this.jTextArea1 = new JTextArea();

    setDefaultCloseOperation(2);
    setAlwaysOnTop(true);
    setBounds(new Rectangle(0, 0, 500, 300));
    setLocationRelativeTo(null);
    setUndecorated(true);
    setName("AboutniconPersonal");
    setResizable(false);

    this.jLabel1.setIcon(new ImageIcon(getClass().getResource(ResourceImages+"AbouNicon.jpg")));

    this.JBcerrar.setFont(new Font("Verdana", 0, 12));
    this.JBcerrar.setText("Cerrar");
    this.JBcerrar.setToolTipText("salir de acerca");
    this.JBcerrar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent evt) {
        GuiAbout.this.JBcerrarActionPerformed(evt);
      }
    });
    this.jTextArea1.setBackground(new Color(254, 254, 254));
    this.jTextArea1.setColumns(20);
    this.jTextArea1.setEditable(false);
    this.jTextArea1.setFont(new Font("Verdana", 0, 12));
    this.jTextArea1.setLineWrap(true);
    this.jTextArea1.setRows(5);
    this.jTextArea1.setText("NiconPersonal, agenda movil de contactos, tenga segura la información de \nsus contactos personales, envie emails, recuerde sus actividades, además\npodrá llevarla en su llave USB sin problemas. \n\nNombre:             NiconPersonal\nVersion:             0.3.20\ncanal:                Alfa Public Release\nOS:                    Linux, Windows, Mac\nDesarrollador:   NiconSystem Inc.\n\nPara más información visite:  http://niconsystem.wordpress.com\nSoporte Tecnico:                    NiconSystem.Support@gmail.com\n\n               Todos los derechos reservados.  CopyRight (C)  2011\n           NiconPersonal y sus componentes son marcas  Registradas \n                                        de NiconSystem Inc.");
    this.jTextArea1.setToolTipText("Información de NiconPersonal");
    this.jTextArea1.setPreferredSize(new Dimension(475, 80));
    this.jScrollPane1.setViewportView(this.jTextArea1);

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(436, 32767).addComponent(this.JBcerrar).addContainerGap()).addComponent(this.jScrollPane1, GroupLayout.Alignment.TRAILING, -1, 500, 32767));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addGap(18, 18, 18).addComponent(this.jScrollPane1, -1, 295, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.JBcerrar).addContainerGap()));

    pack();
  }

  private void JBcerrarActionPerformed(ActionEvent evt) {
    setVisible(false);
  }

  public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
     @Override
      public void run() {
        new GuiAbout().setVisible(true);
      }
    });
  }
}