/* J4a_11.java: ShowDocument (GösterDökümaný) örneði.
 *
 * <applet code="J4a_11.class" width="200" height="30"></applet>
 * çalýþtýr: appletviewer J4a_11.java
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

/* Verili/girili dökümaný gösterme problemi var ("MalformedURLException").
 * URL girileni okusa da "apletZemini.showDocument (url)" göstermiyor...
*/
public class J4a_11 extends JApplet implements ActionListener {
    URLPencerem yurelPenceresi;

    public void init() {
        try {SwingUtilities.invokeAndWait (new Runnable() {public void run() {yaratGUI();}});
        }catch (Exception ist) {System.err.println ("Hata: GUI yaratýlamadý!");}
    } // init() hazýr aplet metodu sonu...

    private void yaratGUI() {
        JButton düðme = new JButton ("URL Penceresini Getir");
        düðme.addActionListener (this); // Dinleyiciye duyarlý...
        add (düðme);

        JFrame.setDefaultLookAndFeelDecorated (true);
        yurelPenceresi = new URLPencerem (getAppletContext()); // Sýnýf kurucusunu çaðýrýr...
        yurelPenceresi.pack();
    } // yaratGUI() metodu sonu...

    public void destroy() {// URLPencerem kapatýlýrsa...
        try {SwingUtilities.invokeAndWait (new Runnable() {public void run() {yoketGUI();}});
        }catch (Exception ist) {}
    } // destroy() hazýr metodu sonu...

    private void yoketGUI() {yurelPenceresi.setVisible (false); yurelPenceresi = null;}
    public void actionPerformed (ActionEvent olay) {yurelPenceresi.setVisible (true);}
} // J4a_11 sýnýfý sonu...

class URLPencerem extends JFrame implements ActionListener {
    JTextField metinSatýrý;
    JComboBox görüntüTercihi;
    AppletContext apletZemini;

    public URLPencerem (AppletContext apletZemini) {// Kurucu...
        super ("Dökümaný Göster");
        this.apletZemini = apletZemini;

        JPanel içerikPaneli = new JPanel(new GridBagLayout());
        setContentPane(içerikPaneli);
        içerikPaneli.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        GridBagConstraints sýnýrlayýcýlar = new GridBagConstraints();
        sýnýrlayýcýlar.fill = GridBagConstraints.HORIZONTAL;

        JLabel etiket1 = new JLabel ("Gösterilecek dökümanýn URL adresini gir:", JLabel.TRAILING);
        add (etiket1, sýnýrlayýcýlar);

        metinSatýrý = new JTextField ("J4a_11.java", 50);
        etiket1.setLabelFor (metinSatýrý);
        metinSatýrý.addActionListener (this); // Giriþler dinleyiciye duyarlý...
        sýnýrlayýcýlar.gridwidth = GridBagConstraints.REMAINDER;
        sýnýrlayýcýlar.weightx = 1.0;
        add (metinSatýrý, sýnýrlayýcýlar);

        JLabel etiket2 = new JLabel ("Üstünde gösterilecek Window/Pencere:", JLabel.TRAILING);
        sýnýrlayýcýlar.gridwidth = 1;
        sýnýrlayýcýlar.weightx = 0.0;
        add (etiket2, sýnýrlayýcýlar);

        String[] açýlýrKutuSatýrlarý = {
            "(tarayýcý tercihi)", // belirtilmezse, varsayýlý aplet zemini kullanýlýr...
            "Kiþisel Pencerem", // Oluþturulacak "Kiþisel Pencerem" adlý pencere...
            "_empty", // boþ: isimsiz, yeni bir tarayýcý sayfasý...
            "_self", // kendi: içinde bulunulan pencere...
            "_parent", // ebeveyn: bir üst, gelinen pencere...
            "_top" // tepe: bu apleti içeren temel çerçeve...
        }; // açýlýrKutuSatýrlarý dizisi sonu...

        görüntüTercihi = new JComboBox (açýlýrKutuSatýrlarý);
        etiket2.setLabelFor (görüntüTercihi);
        sýnýrlayýcýlar.fill = GridBagConstraints.NONE;
        sýnýrlayýcýlar.gridwidth = GridBagConstraints.REMAINDER;
        sýnýrlayýcýlar.insets = new Insets (5,0,0,0);
        sýnýrlayýcýlar.anchor = GridBagConstraints.LINE_START;
        add (görüntüTercihi, sýnýrlayýcýlar);

        JButton düðme = new JButton ("Dökümaný Göster");
        düðme.addActionListener (this); // Dinleyiciye duyarlý...
        sýnýrlayýcýlar.weighty = 1.0;
        sýnýrlayýcýlar.ipadx = 10;
        sýnýrlayýcýlar.ipady = 10;
        sýnýrlayýcýlar.insets = new Insets (10,0,0,0);
        sýnýrlayýcýlar.anchor = GridBagConstraints.PAGE_END;
        add (düðme, sýnýrlayýcýlar);
    } // URLPencerem(..) kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        String yurelDizgesi = metinSatýrý.getText();
        URL yurel = null;
        try {yurel = J4a_11.class.getResource (yurelDizgesi); // veya "new URL (yurelDizgesi);"
        }catch (Exception ist) {// MalformedURLException: Herþeye arýza gösteriyor!..
            System.err.println ("Bozuk URL: " + yurelDizgesi);}

        if (yurel != null) {
            System.out.println (yurel);
            if (görüntüTercihi.getSelectedIndex() == 0) apletZemini.showDocument (yurel);
            else apletZemini.showDocument (yurel, (String)görüntüTercihi.getSelectedItem());
        } // dýþ-if kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...
} // URLPencerem sýnýfý sonu...