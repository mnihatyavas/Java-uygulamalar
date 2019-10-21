/* J4a_8.java: GetApplets (AlApletleri) örneði.
 *
 * <applet code="J4a_8.class" width="300" height="300"></applet>
 */

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.applet.Applet;

import java.awt.Color;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Enumeration;

public class J4a_8 extends JApplet implements ActionListener {
    private JTextArea metinAlaný = null;

    public void init() {
        try {
            javax.swing.SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {yaratGUI();}
            }); // jav.. ifadesi sonu...
        }catch (Exception ist) {System.err.println ("yaratGUI tamamlanamadý!");
        } // try-catch bloðu sonu...
    } // init() metodu sonu...

    private void yaratGUI() {
        JPanel içerikPanosu = new JPanel (new BorderLayout());
        içerikPanosu.setBorder (BorderFactory.createCompoundBorder (
            BorderFactory.createLineBorder (Color.BLACK),
            BorderFactory.createEmptyBorder(10,10,10,10)));
        setContentPane (içerikPanosu);

        JButton buton = new JButton ("getAppletContext().getApplets() için TIKLA");
        buton.addActionListener (this);
        add (buton, BorderLayout.PAGE_START);
        buton.setBackground (Color.MAGENTA);

        metinAlaný = new JTextArea (5, 40);
        metinAlaný.setBackground (Color.GREEN);
        metinAlaný.setEditable (true);
        JScrollPane kaydýrmaPanosu = new JScrollPane (metinAlaný);
        add (kaydýrmaPanosu, BorderLayout.CENTER);
    } // yaratGUI() metodu sonu...

    public void actionPerformed (ActionEvent olay) {apletleriYaz();}
    public String getAppletInfo() {return "J4a_8=GetApplets";}

    public void apletleriYaz() {
        // Enumeration tarayýcýdaki tüm apletleri içermektedir...
        Enumeration liste = getAppletContext().getApplets();

        metinAlaný.append ("[getAppletContext().getApplets()] týklanmasý sonucu:\n");

        while (liste.hasMoreElements()) {
            Applet aplet = (Applet)liste.nextElement();
            String bilgi = ((Applet)aplet).getAppletInfo();
            if (bilgi != null) metinAlaný.append ("- " + bilgi + "\n");
            else metinAlaný.append ("- " + aplet.getClass().getName() + "\n");
        } // while döngüsü sonu...

        metinAlaný.append ("________________________\n\n");
    } // apletleriYaz() metodu sonu...
} // J4a_8 sýnýfý sonu...