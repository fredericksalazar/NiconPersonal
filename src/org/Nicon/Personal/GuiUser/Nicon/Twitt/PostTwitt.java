/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.GuiUser.Nicon.Twitt;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import org.Nicon.Personal.LibCore.Sbin.GlobalConfigSystem;
import org.Nicon.Personal.LibCore.NiconTwitt.NiconTwitt;

/**
 *
 * @author frederick
 */
public final class PostTwitt extends JDialog {
    
    private JPanel PanelTwitt;
    private JTextArea JTStatus;
    private JLabel JLCountInput;
    private JLabel JLInfo;
    
    private JButton JBSend;
    private int countInput;
    private NiconTwitt twit;
    
    public PostTwitt(){
        this.setTitle(GlobalConfigSystem.getTitleAplication());
        this.setLocationByPlatform(false);
        this.setSize(320,200);
        this.setResizable(false);         
        this.setLocationRelativeTo(this);
        this.setModal(true);
        twit=new NiconTwitt();
        twit.getConfigurationAccount();
        countInput=0;
        initComponents();
    }
    
    public void initComponents(){
        
        PanelTwitt=new JPanel();
        PanelTwitt.setBackground(GlobalConfigSystem.getNiconPersonalColorInterface());
        PanelTwitt.setLayout(null);
        
        JLCountInput=new JLabel(String.valueOf(countInput));
        JLCountInput.setBounds(20, 120,100, 20);
        JLCountInput.setForeground(GlobalConfigSystem.getForegroundDashPanel());
        
        JTStatus=new JTextArea();
        JTStatus.setLineWrap(true);
        JTStatus.setBounds(10, 10,300,100);
        JTStatus.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
            public void keyTyped(KeyEvent ke) {
                JLCountInput.setText(String.valueOf(JTStatus.getText().length()));
                if(JTStatus.getText().length()==140){
                    ke.consume();
                }
            }
        });
        
        JLInfo=new JLabel();
        JLInfo.setForeground(GlobalConfigSystem.getForegroundDashPanel());
        JLInfo.setBounds(60, 120,120, 20);
        
        JBSend=new JButton("Twittear");
        JBSend.setToolTipText("Enviar el tweet ");
        JBSend.setIcon(new javax.swing.ImageIcon(getClass().getResource(GlobalConfigSystem.getIconsPath()+"NiconAcept.png")));
        JBSend.setBounds(190, 125, 120, 30);
        JBSend.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {                
                    if(!JTStatus.getText().equals("")){
                       showMesagge();
                       twit.updateStatus(JTStatus.getText());                       
                       dispose(); 
                    }                    
            }
        });
        
        this.getRootPane().setDefaultButton(JBSend);
        PanelTwitt.add(JTStatus);
        PanelTwitt.add(JLCountInput);
        PanelTwitt.add(JBSend);
        PanelTwitt.add(JLInfo);
        getContentPane().add(PanelTwitt);        
    }
    
    private void showMesagge(){
        JLInfo.setText("Enviando tu twett ...");
        PanelTwitt.repaint();
    }
}
