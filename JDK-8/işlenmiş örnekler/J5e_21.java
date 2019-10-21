// J5e_21.java: TrackFocusDemo (Odaklanmay��zlemeG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.KeyboardFocusManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

import java.net.URL;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/* Gereken dosyalar:
 *     J5e_21x.java=Picture.java
 *     resim/Maya.jpg
 *     resim/Anya.jpg
 *     resim/Laine.jpg
 *     resim/Cosmo.jpg
 *     resim/Adele.jpg
 *     resim/Alexi.jpg
 */
public class J5e_21 extends JPanel {
    J5e_21x resim1, resim2, resim3, resim4, resim5, resim6;
    JLabel isimEtiketi;
    static String meral = "Maya";
    static String ayla = "Anya";
    static String g�lin = "Laine";
    static String kas�m = "Cosmo";
    static String adil = "Adele";
    static String ali = "Alexi";
    String[] yorumlar = { "Hoops! Bu da nedir?",
            "Bunun ad�: Meral",
            "Bunun ad�: Ayla",
            "Bunun ad�: G�lin",
            "Bunun ad�: Kas�m",
            "Bunun ad�: Adil",
            "Bunun ad�: Ali" };

    public J5e_21() {// Kurucu...
        super (new BorderLayout());

        JPanel resimPaneli = new JPanel (new GridLayout (2,3));
        resimPaneli.setBackground (Color.CYAN);
        // J5e_21x.java=Picture.java kurucusunu �a��r�r...
        resim1 = new J5e_21x (resim�konuYarat ("resim/" + meral + ".jpg", meral).getImage());
        resim1.setName ("1");
        resimPaneli.add (resim1);
        resim2 = new J5e_21x (resim�konuYarat ("resim/" + ayla + ".jpg", ayla).getImage());
        resim2.setName ("2");
        resimPaneli.add (resim2);
        resim3 = new J5e_21x (resim�konuYarat ("resim/" + g�lin + ".jpg", g�lin).getImage());
        resim3.setName ("3");
        resimPaneli.add (resim3);
        resim4 = new J5e_21x (resim�konuYarat ("resim/" + kas�m + ".jpg", kas�m).getImage());
        resim4.setName ("4");
        resimPaneli.add (resim4);
        resim5 = new J5e_21x (resim�konuYarat ("resim/" + adil + ".jpg", adil).getImage());
        resim5.setName ("5");
        resimPaneli.add (resim5);
        resim6 = new J5e_21x (resim�konuYarat ("resim/" + ali + ".jpg", ali).getImage());
        resim6.setName ("6");
        resimPaneli.add (resim6);

        isimEtiketi = new JLabel ("Hen�z se�ilmi� resim yok");
        isimEtiketi.setForeground (Color.MAGENTA);
        isimEtiketi.setText (yorumlar[0]); // setFocusable(true) olmasayd� ilk yorum okunacakt�...

        setPreferredSize (new Dimension (420, 310));
        add (resimPaneli, BorderLayout.CENTER);
        add (isimEtiketi, BorderLayout.PAGE_END);
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));

        KeyboardFocusManager odaklanmaY�neticisi =
                KeyboardFocusManager.getCurrentKeyboardFocusManager();
        odaklanmaY�neticisi.addPropertyChangeListener (
            new PropertyChangeListener() {
                public void propertyChange (PropertyChangeEvent olay) {
                    String �zellik = olay.getPropertyName();
                    if (("focusOwner".equals (�zellik)) &&
                          ((olay.getNewValue()) instanceof J5e_21x)) {

                        Component komponent = (Component)olay.getNewValue();
                        String ad = komponent.getName();
                        Integer say� = new Integer (ad);
                        int endeks = say�.intValue();
                        if (endeks < 0 || endeks > yorumlar.length) endeks = 0;
                        isimEtiketi.setText (yorumlar[endeks]);
                    } // if karar� sonu...
                } // propertyChange(..) haz�r metodu sonu...
            } // new.. ifadesi sonu...
        ); // oda.. ifadesi sonu...
    } // J5e_21() kurucusu sonu...

    protected static ImageIcon resim�konuYarat (String yol, String izah) {
        URL resimYureli = J5e_21.class.getResource (yol);
        if (resimYureli == null) {
            System.err.println ("[" + yol + "] isimli resim dosyas� bulunamad�!");
            return null;
        }else return new ImageIcon (resimYureli, izah);
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Odaklanmay� �zleme G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5e_21(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch blo�u sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_21 s�n�f� sonu...