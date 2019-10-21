// J9e_1.java: ImageDrawingApplet (ResimÇizmeApleti) örneði.

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JComboBox;

import java.io.File;

import java.net.URL;
import java.net.MalformedURLException;

/* Gereken alt-program: J9e_1x.java=ImageDrawingComponent
 * resim dosyasý: resim/evönü.jpg
 */
public class J9e_1 extends JApplet {
    static String resimDosyasýAdý = "resim/evönü.jpg";
    private URL resimYurelKaynaðý;

    public J9e_1 (URL resimYurelKaynaðý) {this.resimYurelKaynaðý = resimYurelKaynaðý;} // Argümanlý kurucu...

    public void kurUI() {
        final J9e_1x resimÇizme = new J9e_1x (resimYurelKaynaðý); // Alt-program kurucusunu çaðýrýr...
        add ("Center", resimÇizme);
        JComboBox açýlýrKutu = new JComboBox (resimÇizme.getDescriptions());
        açýlýrKutu.addActionListener (new ActionListener() {// Açýlýr kutu dinleyiciye duyarlý...
            public void actionPerformed (ActionEvent olay) {
                JComboBox jcb = (JComboBox)olay.getSource();
                resimÇizme.setOpIndex (jcb.getSelectedIndex());
                resimÇizme.repaint();
            }; // actionPerformed(..) hazýr metodu sonu...
        }); // açý..ifadesi sonu...
        add ("South", açýlýrKutu);
    } // kurUI() metodu sonu...

    public static void main (String s[]) {
        JFrame çerçeve = new JFrame ("Resim Çizme");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE //addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        URL resimYurelKaynaðý = null;
        try {resimYurelKaynaðý = ((new File (resimDosyasýAdý)).toURI()).toURL();
        }catch (MalformedURLException olay) {}
        J9e_1 resimÇizimi = new J9e_1 (resimYurelKaynaðý); // Argümanlý kurucuyu çaðýrýr...
        resimÇizimi.kurUI();
        çerçeve.add ("Center", resimÇizimi);
        çerçeve.setLocation (500, 50);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9e_1 sýnýfý sonu...