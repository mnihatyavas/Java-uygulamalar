/* J4a_8.java: GetApplets (AlApletleri) �rne�i.
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
    private JTextArea metinAlan� = null;

    public void init() {
        try {
            javax.swing.SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {yaratGUI();}
            }); // jav.. ifadesi sonu...
        }catch (Exception ist) {System.err.println ("yaratGUI tamamlanamad�!");
        } // try-catch blo�u sonu...
    } // init() metodu sonu...

    private void yaratGUI() {
        JPanel i�erikPanosu = new JPanel (new BorderLayout());
        i�erikPanosu.setBorder (BorderFactory.createCompoundBorder (
            BorderFactory.createLineBorder (Color.BLACK),
            BorderFactory.createEmptyBorder(10,10,10,10)));
        setContentPane (i�erikPanosu);

        JButton buton = new JButton ("getAppletContext().getApplets() i�in TIKLA");
        buton.addActionListener (this);
        add (buton, BorderLayout.PAGE_START);
        buton.setBackground (Color.MAGENTA);

        metinAlan� = new JTextArea (5, 40);
        metinAlan�.setBackground (Color.GREEN);
        metinAlan�.setEditable (true);
        JScrollPane kayd�rmaPanosu = new JScrollPane (metinAlan�);
        add (kayd�rmaPanosu, BorderLayout.CENTER);
    } // yaratGUI() metodu sonu...

    public void actionPerformed (ActionEvent olay) {apletleriYaz();}
    public String getAppletInfo() {return "J4a_8=GetApplets";}

    public void apletleriYaz() {
        // Enumeration taray�c�daki t�m apletleri i�ermektedir...
        Enumeration liste = getAppletContext().getApplets();

        metinAlan�.append ("[getAppletContext().getApplets()] t�klanmas� sonucu:\n");

        while (liste.hasMoreElements()) {
            Applet aplet = (Applet)liste.nextElement();
            String bilgi = ((Applet)aplet).getAppletInfo();
            if (bilgi != null) metinAlan�.append ("- " + bilgi + "\n");
            else metinAlan�.append ("- " + aplet.getClass().getName() + "\n");
        } // while d�ng�s� sonu...

        metinAlan�.append ("________________________\n\n");
    } // apletleriYaz() metodu sonu...
} // J4a_8 s�n�f� sonu...