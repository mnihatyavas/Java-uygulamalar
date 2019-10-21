// J9e_1.java: ImageDrawingApplet (Resim�izmeApleti) �rne�i.

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JComboBox;

import java.io.File;

import java.net.URL;
import java.net.MalformedURLException;

/* Gereken alt-program: J9e_1x.java=ImageDrawingComponent
 * resim dosyas�: resim/ev�n�.jpg
 */
public class J9e_1 extends JApplet {
    static String resimDosyas�Ad� = "resim/ev�n�.jpg";
    private URL resimYurelKayna��;

    public J9e_1 (URL resimYurelKayna��) {this.resimYurelKayna�� = resimYurelKayna��;} // Arg�manl� kurucu...

    public void kurUI() {
        final J9e_1x resim�izme = new J9e_1x (resimYurelKayna��); // Alt-program kurucusunu �a��r�r...
        add ("Center", resim�izme);
        JComboBox a��l�rKutu = new JComboBox (resim�izme.getDescriptions());
        a��l�rKutu.addActionListener (new ActionListener() {// A��l�r kutu dinleyiciye duyarl�...
            public void actionPerformed (ActionEvent olay) {
                JComboBox jcb = (JComboBox)olay.getSource();
                resim�izme.setOpIndex (jcb.getSelectedIndex());
                resim�izme.repaint();
            }; // actionPerformed(..) haz�r metodu sonu...
        }); // a��..ifadesi sonu...
        add ("South", a��l�rKutu);
    } // kurUI() metodu sonu...

    public static void main (String s[]) {
        JFrame �er�eve = new JFrame ("Resim �izme");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE //addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        URL resimYurelKayna�� = null;
        try {resimYurelKayna�� = ((new File (resimDosyas�Ad�)).toURI()).toURL();
        }catch (MalformedURLException olay) {}
        J9e_1 resim�izimi = new J9e_1 (resimYurelKayna��); // Arg�manl� kurucuyu �a��r�r...
        resim�izimi.kurUI();
        �er�eve.add ("Center", resim�izimi);
        �er�eve.setLocation (500, 50);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9e_1 s�n�f� sonu...