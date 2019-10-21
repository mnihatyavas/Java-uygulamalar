// J5c_1.java: BorderDemo (SýnýrGösterimi) örneði.

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel; 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;

// Gereken resim dosyasý: wavy.gif
public class J5c_1 extends JPanel {
    public J5c_1() {// Kurucu...
        super (new GridLayout (1,0)); // Kolonsuz tek satýr...

        // Bileþik sýnýrlar ve baþlýklarý için referans deðiþkenleri hazýrlayalým...
        Border siyahHat, yüksekKenar, düþükKenar,
               yüksekTaban, düþükTaban, boþ;

        // Sað-sol-alt kenarlarýnda 10 fazladan piksel bulunan pano kenarý...
        Border panoKenarý = BorderFactory.createEmptyBorder (0,10,10,10);

        siyahHat = BorderFactory.createLineBorder (Color.black);
        yüksekKenar = BorderFactory.createEtchedBorder (EtchedBorder.RAISED);
        düþükKenar = BorderFactory.createEtchedBorder (EtchedBorder.LOWERED);
        yüksekTaban = BorderFactory.createRaisedBevelBorder();
        düþükTaban = BorderFactory.createLoweredBevelBorder();
        boþ = BorderFactory.createEmptyBorder();

        // Ýlk pano: basit sýnýrlar...
        JPanel basitSýnýrlar = new JPanel();
        basitSýnýrlar.setBorder (panoKenarý);
        basitSýnýrlar.setLayout (new BoxLayout (basitSýnýrlar, BoxLayout.Y_AXIS));

        komponentleriEkle (siyahHat, "çizgili sýnýr", basitSýnýrlar);
        komponentleriEkle (yüksekKenar, "yüksek kenarlý sýnýr", basitSýnýrlar);
        komponentleriEkle (düþükKenar, "düþük kenarlý sýnýr", basitSýnýrlar);
        komponentleriEkle (yüksekTaban, "yüksek tabanlý sýnýr", basitSýnýrlar);
        komponentleriEkle (düþükTaban, "düþük tabanlý sýnýr", basitSýnýrlar);
        komponentleriEkle (boþ, "boþ sýnýr", basitSýnýrlar);

        // Ýkinci pano: paspaslý/dalgalý sýnýrlar...
        JPanel paspaslýSýnýrlar = new JPanel();
        paspaslýSýnýrlar.setBorder (panoKenarý);
        paspaslýSýnýrlar.setLayout (new BoxLayout (paspaslýSýnýrlar, BoxLayout.Y_AXIS));

        ImageIcon ikon = yaratImageIcon ("wavy.gif", "dalgalý sýnýr ikonu"); // 20x22 piksel...
        Border sýnýr = BorderFactory.createMatteBorder (-1, -1, -1, -1, ikon);

        if (ikon != null) komponentleriEkle (sýnýr, "dalgalý sýnýr (-1,-1,-1,-1, ikon)", paspaslýSýnýrlar);
        else komponentleriEkle (sýnýr, "dalgalý sýnýr (-1,-1,-1,-1,<null-ikon>)", paspaslýSýnýrlar);

        sýnýr = BorderFactory.createMatteBorder (1, 5, 1, 1, Color.red);
        komponentleriEkle (sýnýr, "dalgalý sýnýr (1,5,1,1,Color.red)", paspaslýSýnýrlar);

        sýnýr = BorderFactory.createMatteBorder (0, 20, 0, 0, ikon);
        if (ikon != null) komponentleriEkle (sýnýr, "dalgalý sýnýr (0,20,0,0,ikon)", paspaslýSýnýrlar);
        else komponentleriEkle (sýnýr, "dalgalý sýnýr (0,20,0,0,<null-ikon>)", paspaslýSýnýrlar);

        // Üçüncü pano: baþlýklý sýnýrlar...
        JPanel baþlýklýSýnýrlar = new JPanel();
        baþlýklýSýnýrlar.setBorder (panoKenarý);
        baþlýklýSýnýrlar.setLayout (new BoxLayout (baþlýklýSýnýrlar, BoxLayout.Y_AXIS));
        TitledBorder baþlýklý;

        baþlýklý = BorderFactory.createTitledBorder ("Baþlýk");
        komponentleriEkle (baþlýklý, "varsayýlý baþlýklý sýnýr (varsayýlý hizalama, varsayýlý konumlama)", baþlýklýSýnýrlar);

        baþlýklý = BorderFactory.createTitledBorder (siyahHat, "Baþlýk");
        baþlýklýKomponentleriEkle (baþlýklý, "baþlýklý çizgi sýnýr (ortalanmýþ, varsayýlý konumlama)",
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, baþlýklýSýnýrlar);

        baþlýklý = BorderFactory.createTitledBorder (düþükKenar, "Baþlýk");
        baþlýklýKomponentleriEkle (baþlýklý, "baþlýklý düþük kenarlý sýnýr (saða hizalý, varsayýlý konumlama)",
                TitledBorder.RIGHT, TitledBorder.DEFAULT_POSITION, baþlýklýSýnýrlar);

        baþlýklý = BorderFactory.createTitledBorder (düþükTaban, "Baþlýk");
        baþlýklýKomponentleriEkle (baþlýklý, "baþlýklý düþük tabanlý sýnýr (varsayýlý hizalama, üstün üzerinde)",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP, baþlýklýSýnýrlar);

        baþlýklý = BorderFactory.createTitledBorder (boþ, "Baþlýk");
        baþlýklýKomponentleriEkle (baþlýklý, "baþlýklý boþ sýnýr (varsayýlý konumlama, altda)",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BOTTOM, baþlýklýSýnýrlar);

        // Dördüncü pano: bileþimli sýnýrlar...
        JPanel biliþimliSýnýrlar = new JPanel();
        biliþimliSýnýrlar.setBorder (panoKenarý);
        biliþimliSýnýrlar.setLayout (new BoxLayout (biliþimliSýnýrlar, BoxLayout.Y_AXIS));
        Border kýrmýzýÇizgi = BorderFactory.createLineBorder (Color.red);

        Border bileþim;
        bileþim = BorderFactory.createCompoundBorder (yüksekTaban, düþükTaban);
        komponentleriEkle (bileþim, "bileþim sýnýr (çift yabanlý)", biliþimliSýnýrlar);

        bileþim = BorderFactory.createCompoundBorder ( kýrmýzýÇizgi, bileþim);
        komponentleriEkle(bileþim, "bileþim sýnýr (kýrmýzý çerçeve ekli)", biliþimliSýnýrlar);

        baþlýklý = BorderFactory.createTitledBorder ( bileþim, "Baþlýk", TitledBorder.CENTER, TitledBorder.BELOW_BOTTOM);
        komponentleriEkle (baþlýklý,  "baþlýklý bileþim sýnýr (ortalanmýþ, altýn altýnda)", biliþimliSýnýrlar);

        JTabbedPane yaftalýPano = new JTabbedPane();
        yaftalýPano.addTab ("Basit", null, basitSýnýrlar, null);
        yaftalýPano.addTab("Dalgalý", null, paspaslýSýnýrlar, null);
        yaftalýPano.addTab("Baþlýklý", null, baþlýklýSýnýrlar, null);
        yaftalýPano.addTab("Birleþik", null, biliþimliSýnýrlar, null);
        yaftalýPano.setSelectedIndex (0);
        String ipucu = new String ("<html>Mavi Dalgalý Çizgi sýnýr sanat ekibi:<br>Bill Pauley<br>Cris St.Aubyn<br>Ben Wronsky<br>Nathan Walrath<br>Tommy Adams, özel danýþman<br>M.Nihat Yavaþ, lüzumsuz danýþman</html>");
        yaftalýPano.setToolTipTextAt (1, ipucu);

        add (yaftalýPano);
    } // J5c_1() kurucusu sonu...

    void baþlýklýKomponentleriEkle (TitledBorder sýnýr, String izah, int hizalama, int konum, Container içerik) {
        sýnýr.setTitleJustification (hizalama);
        sýnýr.setTitlePosition (konum);
        komponentleriEkle (sýnýr, izah, içerik);
    } // baþlýklýKomponentleriEkle(..) metodu sonu...

    void komponentleriEkle (Border sýnýr, String izah, Container içerik) {
        JPanel bilieþimPanosu = new JPanel (new GridLayout (1, 1), false);
        JLabel etiket = new JLabel (izah, JLabel.CENTER);
        bilieþimPanosu.add (etiket);
        bilieþimPanosu.setBorder (sýnýr);

        içerik.add (Box.createRigidArea (new Dimension (0, 10)));
        içerik.add (bilieþimPanosu);
    } // komponentleriEkle(..) metodu sonu...

    // Yol geçerliyse bir ImageIcon döndürür, deðilse null döner...
    protected static ImageIcon yaratImageIcon (String yol, String izah) {
        java.net.URL resimURL = J5c_1.class.getResource (yol);
        if (resimURL != null) return new ImageIcon (resimURL, izah);
        else {System.err.println ("Resim dosyasýnýn [ " + yol + "] yolu bulunamadý!"); return null;}
    } // yaratImageIcon(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Sýnýr Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J5c_1 yeniÝçerikPanosu = new J5c_1(); // Kurucuyu iþlet...
        yeniÝçerikPanosu.setOpaque (true); // Ýçerik panolarý opak/saydamsýz olmalýdýr...
        çerçeve.setContentPane (yeniÝçerikPanosu);

        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_1 sýnýfý sonu...