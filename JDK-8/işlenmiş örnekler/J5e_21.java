// J5e_21.java: TrackFocusDemo (OdaklanmayýÝzlemeGösterisi) örneði.

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
    static String gülin = "Laine";
    static String kasým = "Cosmo";
    static String adil = "Adele";
    static String ali = "Alexi";
    String[] yorumlar = { "Hoops! Bu da nedir?",
            "Bunun adý: Meral",
            "Bunun adý: Ayla",
            "Bunun adý: Gülin",
            "Bunun adý: Kasým",
            "Bunun adý: Adil",
            "Bunun adý: Ali" };

    public J5e_21() {// Kurucu...
        super (new BorderLayout());

        JPanel resimPaneli = new JPanel (new GridLayout (2,3));
        resimPaneli.setBackground (Color.CYAN);
        // J5e_21x.java=Picture.java kurucusunu çaðýrýr...
        resim1 = new J5e_21x (resimÝkonuYarat ("resim/" + meral + ".jpg", meral).getImage());
        resim1.setName ("1");
        resimPaneli.add (resim1);
        resim2 = new J5e_21x (resimÝkonuYarat ("resim/" + ayla + ".jpg", ayla).getImage());
        resim2.setName ("2");
        resimPaneli.add (resim2);
        resim3 = new J5e_21x (resimÝkonuYarat ("resim/" + gülin + ".jpg", gülin).getImage());
        resim3.setName ("3");
        resimPaneli.add (resim3);
        resim4 = new J5e_21x (resimÝkonuYarat ("resim/" + kasým + ".jpg", kasým).getImage());
        resim4.setName ("4");
        resimPaneli.add (resim4);
        resim5 = new J5e_21x (resimÝkonuYarat ("resim/" + adil + ".jpg", adil).getImage());
        resim5.setName ("5");
        resimPaneli.add (resim5);
        resim6 = new J5e_21x (resimÝkonuYarat ("resim/" + ali + ".jpg", ali).getImage());
        resim6.setName ("6");
        resimPaneli.add (resim6);

        isimEtiketi = new JLabel ("Henüz seçilmiþ resim yok");
        isimEtiketi.setForeground (Color.MAGENTA);
        isimEtiketi.setText (yorumlar[0]); // setFocusable(true) olmasaydý ilk yorum okunacaktý...

        setPreferredSize (new Dimension (420, 310));
        add (resimPaneli, BorderLayout.CENTER);
        add (isimEtiketi, BorderLayout.PAGE_END);
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));

        KeyboardFocusManager odaklanmaYöneticisi =
                KeyboardFocusManager.getCurrentKeyboardFocusManager();
        odaklanmaYöneticisi.addPropertyChangeListener (
            new PropertyChangeListener() {
                public void propertyChange (PropertyChangeEvent olay) {
                    String özellik = olay.getPropertyName();
                    if (("focusOwner".equals (özellik)) &&
                          ((olay.getNewValue()) instanceof J5e_21x)) {

                        Component komponent = (Component)olay.getNewValue();
                        String ad = komponent.getName();
                        Integer sayý = new Integer (ad);
                        int endeks = sayý.intValue();
                        if (endeks < 0 || endeks > yorumlar.length) endeks = 0;
                        isimEtiketi.setText (yorumlar[endeks]);
                    } // if kararý sonu...
                } // propertyChange(..) hazýr metodu sonu...
            } // new.. ifadesi sonu...
        ); // oda.. ifadesi sonu...
    } // J5e_21() kurucusu sonu...

    protected static ImageIcon resimÝkonuYarat (String yol, String izah) {
        URL resimYureli = J5e_21.class.getResource (yol);
        if (resimYureli == null) {
            System.err.println ("[" + yol + "] isimli resim dosyasý bulunamadý!");
            return null;
        }else return new ImageIcon (resimYureli, izah);
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Odaklanmayý Ýzleme Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5e_21(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch bloðu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_21 sýnýfý sonu...