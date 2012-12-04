/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Nicon.Personal.LibCore.Sbin;

import org.Nicon.Personal.LibCore.Obj.NiContact;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.Nicon.Personal.Data.DataContact;

/**
 *
 * @author frederick
 */
public class ExportNiContacts {

    private static ArrayList ListContact;
    private static Iterator ListIterator;
    private static int counter;
    private String DirExp;
    private String LineRead;
    private String HeaderLine;
    private String[] vector;
    private NiContact contact;
    private FileWriter NiconWriter;
    private BufferedWriter NiconBuffer;
    private PrintWriter NiconPrinter;
    private File NiconFile;
    private char c;
    private int response;

    public ExportNiContacts() {
        HeaderLine = "\n\n"
                + "Aplicación:    " + GlobalConfigSystem.getNameAplication() + "\n"
                + "Versión:       " + GlobalConfigSystem.getCurrentVersion() + "\n"
                + "Nombre clave:         " + GlobalConfigSystem.getAlternativeName() + "\n"
                + "Fecha:         " + NiconSystemAdmin.getInstantTime() + "\n"
                + "\nRegistro de contactos almacenados en NiconPersonal\n";
        DirExp = "./exportaciones";
        ListContact = new ArrayList();
        ListContact = DataContact.CloneData();
        ListIterator = ListContact.iterator();
    }

    /*
     * Este metodo se encarga de tomar todos los objetos de tipo NiContacts y exportarlos a un archivo .txt en el
     * directorio actual de trabajo. hace uso de la interfaz ContactData y clona todos los objetos ahi almacenados
     * para posteriormente ser escritos en el archivo de texto.
     * 
     * Frederick Adolfo Salazar Sanchex
     * 13-oct-2012
     */
    public void exportNiContactsToTxt() {

        try {
            System.out.println("Iniciando exportacion NiContactsList A Archivo .txt....");
            verifyExistDir(DirExp);
            NiconFile = new File(DirExp, "NiContacts.txt");
            NiconWriter = new FileWriter(NiconFile);
            System.out.println("Escribiendo Archivo NiContacts.txt....");
            NiconWriter.write(HeaderLine + "\n");

            for (int i = 0; i < ListContact.size(); i++) {
                contact = (NiContact) ListContact.get(i);
                LineRead = contact.toStringExport();
                NiconWriter.write(LineRead + "\n\n");
            }
            NiconWriter.close();            
            System.out.println("Exportación terminada ....");
            JOptionPane.showMessageDialog(null, "El archivo ha sido creado exitosamente:\n"
                                              + "Nombre Archivo:         "+NiconFile.getName()+"\n"
                                              + "Carpeta de archivo:     "+NiconFile.getPath()+"\n"
                                              + "Tamaño de archivo:     "+NiconFile.length()/1024+"KB", GlobalConfigSystem.getTitleAplication(), JOptionPane.INFORMATION_MESSAGE);
            Desktop.getDesktop().open(NiconFile);
        } catch (Exception e) {
            System.out.println("Error en ExportNiContacts detail Error:\n" + e);
        }
    }

    /*
     * este metodo hace la exportacion de todo el listado de contactos a un archivos CSV
     * hace uso de la Libreria JavaCSV no retorna ni recibe ninguna variable.
     * 
     * Version 0.1
     * creado 13-oct-2012
     */
    public void exportNiContactsListToCvs() {

        try {
            vector = new String[9];
            System.out.println("Iniciando eixportacion NiContactsList a CSV....");
            c = ',';
            verifyExistDir(DirExp);
            NiconFile = new File(DirExp, "NiContacts.csv");
            NiconWriter = new FileWriter(NiconFile);
//            CsvWriter CsvW = new CsvWriter(NiconWriter, c);

            for (int i = 0; i < ListContact.size(); i++) {
                contact = (NiContact) ListContact.get(i);
                vector[0] = String.valueOf(contact.getCodigo());
                vector[1] = contact.getNombres() + " " + contact.getApellidos();
                vector[2] = contact.getApodo();
                vector[3] = contact.getCiudad();
                vector[4] = contact.getDireccion();
                vector[5] = contact.getCelular();
                vector[6] = contact.getFijo();
                vector[7] = contact.getEmail();
                vector[8] = contact.getFecha_nac();
//                CsvW.writeRecord(vector);
            }
//            CsvW.close();
            NiconWriter.close();
            System.out.println("Exportación terminada ....");JOptionPane.showMessageDialog(null, "El archivo ha sido creado exitosamente:\n"
                                              + "Nombre Archivo:         "+NiconFile.getName()+"\n"
                                              + "Carpeta de archivo:     "+NiconFile.getPath()+"\n"
                                              + "Tamaño de archivo:     "+NiconFile.length()/1024+"KB", GlobalConfigSystem.getTitleAplication(), JOptionPane.INFORMATION_MESSAGE);
            Desktop.getDesktop().open(NiconFile);
        } catch (Exception e) {
            System.out.println("Error en ExportNiContacts detail Error:\n" + e.getClass().getName());
        }
    }

    /*
     * Este metodo se usa para exportar toda la informacion de un solo contacto y enviarla a un archivo
     * .txt, recibe como parametros un contacto a exportar, no retorna nada.
     */
    public void exportContactToTxt(NiContact Contact) {

        try {
            if (Contact != null) {
                System.out.println("Iniciando la exportacion de datos de contacto ...");
                contact = Contact;
                NiconFile = new File(DirExp,contact.getNombres() + " " + contact.getApellidos() + ".txt");

                if (NiconFile.exists()) {
                    response = JOptionPane.showConfirmDialog(null, "El archivo de contacto ya existe\n¿Desea reemplazarlo?", GlobalConfigSystem.getTitleAplication(), JOptionPane.WARNING_MESSAGE);
                    if (response == 0) {
                        System.out.println("Escribiendo Archivo " + contact.getNombres() + " " + contact.getApellidos() + ".txt ...");
                        NiconWriter = new FileWriter(NiconFile);
                        NiconWriter.write(HeaderLine + "\n\n");
                        NiconWriter.write("Información de contacto:\n" + contact.toStringExport());
                        NiconWriter.close();
                        System.out.println("Exportación de archivo terminada ....");
                        JOptionPane.showMessageDialog(null, "El archivo ha sido creado exitosamente.", GlobalConfigSystem.getTitleAplication(), JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    System.out.println("Escribiendo Archivo " + contact.getNombres() + " " + contact.getApellidos() + ".txt ...");
                    NiconWriter = new FileWriter(NiconFile);
                    NiconWriter.write(HeaderLine + "\n\n");
                    NiconWriter.write("Información de contacto:\n" + contact.toStringExport());
                    NiconWriter.close();
                    System.out.println("Exportación de archivo terminada ....");
                    JOptionPane.showMessageDialog(null, "El archivo ha sido creado exitosamente.", GlobalConfigSystem.getTitleAplication(), JOptionPane.INFORMATION_MESSAGE);

                }
            }
        } catch (Exception e) {
        }
    }

   

    /*
     * Este metodo se encarga de verificar si un directorio existe o no, en caso de ser negativo, lo crea
     * recibe como parametro el PATH del directorio a verificar.
     *
     * V 0.1
     * Fecha de creacion 17-Ocutbre de 2012
     */
    
    public void verifyExistDir(String Path) {
        System.out.println("Verificando la existencia del directorio"+Path+"...");
        boolean exist = false;
        NiconFile = new File(Path);
        exist = NiconFile.exists();
        if (exist == false) {
            System.out.println("El directorio "+Path+" no existe ....");
            System.out.println("Creando directorio: " + Path);
            NiconFile.mkdir();
            System.out.println("El direcotiro ha sido creado exitosamente ...");
        }
    }
}
