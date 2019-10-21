/* J4a_11.java: ShowDocument (G�sterD�k�man�) �rne�i.
 *
 * <applet code="J4a_11.class" width="200" height="30"></applet>
 * �al��t�r: appletviewer J4a_11.java
 */

import java.applet.AppletContext;

import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import java.net.URL;
import java.net.MalformedURLException;

/* Verili/girili d�k�man� g�sterme problemi var ("MalformedURLException").
 * URL girileni okusa da "apletZemini.showDocument (url)" g�stermiyor...
*/
public class J4a_11 extends JApplet implements ActionListener {
    URLPencerem yurelPenceresi;

    public void init() {
        try {SwingUtilities.invokeAndWait (new Runnable() {public void run() {yaratGUI();}});
        }catch (Exception ist) {System.err.println ("Hata: GUI yarat�lamad�!");}
    } // init() haz�r aplet metodu sonu...

    private void yaratGUI() {
        JButton d��me = new JButton ("URL Penceresini Getir");
        d��me.addActionListener (this); // Dinleyiciye duyarl�...
        add (d��me);

        JFrame.setDefaultLookAndFeelDecorated (true);
        yurelPenceresi = new URLPencerem (getAppletContext()); // S�n�f kurucusunu �a��r�r...
        yurelPenceresi.pack();
    } // yaratGUI() metodu sonu...

    public void destroy() {// URLPencerem kapat�l�rsa...
        try {SwingUtilities.invokeAndWait (new Runnable() {public void run() {yoketGUI();}});
        }catch (Exception ist) {}
    } // destroy() haz�r metodu sonu...

    private void yoketGUI() {yurelPenceresi.setVisible (false); yurelPenceresi = null;}
    public void actionPerformed (ActionEvent olay) {yurelPenceresi.setVisible (true);}
} // J4a_11 s�n�f� sonu...

class URLPencerem extends JFrame implements ActionListener {
    JTextField metinSat�r�;
    JComboBox g�r�nt�Tercihi;
    AppletContext apletZemini;

    public URLPencerem (AppletContext apletZemini) {// Kurucu...
        super ("D�k�man� G�ster");
        this.apletZemini = apletZemini;

        JPanel i�erikPaneli = new JPanel(new GridBagLayout());
        setContentPane(i�erikPaneli);
        i�erikPaneli.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        GridBagConstraints s�n�rlay�c�lar = new GridBagConstraints();
        s�n�rlay�c�lar.fill = GridBagConstraints.HORIZONTAL;

        JLabel etiket1 = new JLabel ("G�sterilecek d�k�man�n URL adresini gir:", JLabel.TRAILING);
        add (etiket1, s�n�rlay�c�lar);

        metinSat�r� = new JTextField ("J4a_11.java", 50);
        etiket1.setLabelFor (metinSat�r�);
        metinSat�r�.addActionListener (this); // Giri�ler dinleyiciye duyarl�...
        s�n�rlay�c�lar.gridwidth = GridBagConstraints.REMAINDER;
        s�n�rlay�c�lar.weightx = 1.0;
        add (metinSat�r�, s�n�rlay�c�lar);

        JLabel etiket2 = new JLabel ("�st�nde g�sterilecek Window/Pencere:", JLabel.TRAILING);
        s�n�rlay�c�lar.gridwidth = 1;
        s�n�rlay�c�lar.weightx = 0.0;
        add (etiket2, s�n�rlay�c�lar);

        String[] a��l�rKutuSat�rlar� = {
            "(taray�c� tercihi)", // belirtilmezse, varsay�l� aplet zemini kullan�l�r...
            "Ki�isel Pencerem", // Olu�turulacak "Ki�isel Pencerem" adl� pencere...
            "_empty", // bo�: isimsiz, yeni bir taray�c� sayfas�...
            "_self", // kendi: i�inde bulunulan pencere...
            "_parent", // ebeveyn: bir �st, gelinen pencere...
            "_top" // tepe: bu apleti i�eren temel �er�eve...
        }; // a��l�rKutuSat�rlar� dizisi sonu...

        g�r�nt�Tercihi = new JComboBox (a��l�rKutuSat�rlar�);
        etiket2.setLabelFor (g�r�nt�Tercihi);
        s�n�rlay�c�lar.fill = GridBagConstraints.NONE;
        s�n�rlay�c�lar.gridwidth = GridBagConstraints.REMAINDER;
        s�n�rlay�c�lar.insets = new Insets (5,0,0,0);
        s�n�rlay�c�lar.anchor = GridBagConstraints.LINE_START;
        add (g�r�nt�Tercihi, s�n�rlay�c�lar);

        JButton d��me = new JButton ("D�k�man� G�ster");
        d��me.addActionListener (this); // Dinleyiciye duyarl�...
        s�n�rlay�c�lar.weighty = 1.0;
        s�n�rlay�c�lar.ipadx = 10;
        s�n�rlay�c�lar.ipady = 10;
        s�n�rlay�c�lar.insets = new Insets (10,0,0,0);
        s�n�rlay�c�lar.anchor = GridBagConstraints.PAGE_END;
        add (d��me, s�n�rlay�c�lar);
    } // URLPencerem(..) kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        String yurelDizgesi = metinSat�r�.getText();
        URL yurel = null;
        try {yurel = J4a_11.class.getResource (yurelDizgesi); // veya "new URL (yurelDizgesi);"
        }catch (Exception ist) {// MalformedURLException: Her�eye ar�za g�steriyor!..
            System.err.println ("Bozuk URL: " + yurelDizgesi);}

        if (yurel != null) {
            System.out.println (yurel);
            if (g�r�nt�Tercihi.getSelectedIndex() == 0) apletZemini.showDocument (yurel);
            else apletZemini.showDocument (yurel, (String)g�r�nt�Tercihi.getSelectedItem());
        } // d��-if karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...
} // URLPencerem s�n�f� sonu...