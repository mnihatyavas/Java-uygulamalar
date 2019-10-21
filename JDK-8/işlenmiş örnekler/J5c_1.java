// J5c_1.java: BorderDemo (S�n�rG�sterimi) �rne�i.

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

// Gereken resim dosyas�: wavy.gif
public class J5c_1 extends JPanel {
    public J5c_1() {// Kurucu...
        super (new GridLayout (1,0)); // Kolonsuz tek sat�r...

        // Bile�ik s�n�rlar ve ba�l�klar� i�in referans de�i�kenleri haz�rlayal�m...
        Border siyahHat, y�ksekKenar, d���kKenar,
               y�ksekTaban, d���kTaban, bo�;

        // Sa�-sol-alt kenarlar�nda 10 fazladan piksel bulunan pano kenar�...
        Border panoKenar� = BorderFactory.createEmptyBorder (0,10,10,10);

        siyahHat = BorderFactory.createLineBorder (Color.black);
        y�ksekKenar = BorderFactory.createEtchedBorder (EtchedBorder.RAISED);
        d���kKenar = BorderFactory.createEtchedBorder (EtchedBorder.LOWERED);
        y�ksekTaban = BorderFactory.createRaisedBevelBorder();
        d���kTaban = BorderFactory.createLoweredBevelBorder();
        bo� = BorderFactory.createEmptyBorder();

        // �lk pano: basit s�n�rlar...
        JPanel basitS�n�rlar = new JPanel();
        basitS�n�rlar.setBorder (panoKenar�);
        basitS�n�rlar.setLayout (new BoxLayout (basitS�n�rlar, BoxLayout.Y_AXIS));

        komponentleriEkle (siyahHat, "�izgili s�n�r", basitS�n�rlar);
        komponentleriEkle (y�ksekKenar, "y�ksek kenarl� s�n�r", basitS�n�rlar);
        komponentleriEkle (d���kKenar, "d���k kenarl� s�n�r", basitS�n�rlar);
        komponentleriEkle (y�ksekTaban, "y�ksek tabanl� s�n�r", basitS�n�rlar);
        komponentleriEkle (d���kTaban, "d���k tabanl� s�n�r", basitS�n�rlar);
        komponentleriEkle (bo�, "bo� s�n�r", basitS�n�rlar);

        // �kinci pano: paspasl�/dalgal� s�n�rlar...
        JPanel paspasl�S�n�rlar = new JPanel();
        paspasl�S�n�rlar.setBorder (panoKenar�);
        paspasl�S�n�rlar.setLayout (new BoxLayout (paspasl�S�n�rlar, BoxLayout.Y_AXIS));

        ImageIcon ikon = yaratImageIcon ("wavy.gif", "dalgal� s�n�r ikonu"); // 20x22 piksel...
        Border s�n�r = BorderFactory.createMatteBorder (-1, -1, -1, -1, ikon);

        if (ikon != null) komponentleriEkle (s�n�r, "dalgal� s�n�r (-1,-1,-1,-1, ikon)", paspasl�S�n�rlar);
        else komponentleriEkle (s�n�r, "dalgal� s�n�r (-1,-1,-1,-1,<null-ikon>)", paspasl�S�n�rlar);

        s�n�r = BorderFactory.createMatteBorder (1, 5, 1, 1, Color.red);
        komponentleriEkle (s�n�r, "dalgal� s�n�r (1,5,1,1,Color.red)", paspasl�S�n�rlar);

        s�n�r = BorderFactory.createMatteBorder (0, 20, 0, 0, ikon);
        if (ikon != null) komponentleriEkle (s�n�r, "dalgal� s�n�r (0,20,0,0,ikon)", paspasl�S�n�rlar);
        else komponentleriEkle (s�n�r, "dalgal� s�n�r (0,20,0,0,<null-ikon>)", paspasl�S�n�rlar);

        // ���nc� pano: ba�l�kl� s�n�rlar...
        JPanel ba�l�kl�S�n�rlar = new JPanel();
        ba�l�kl�S�n�rlar.setBorder (panoKenar�);
        ba�l�kl�S�n�rlar.setLayout (new BoxLayout (ba�l�kl�S�n�rlar, BoxLayout.Y_AXIS));
        TitledBorder ba�l�kl�;

        ba�l�kl� = BorderFactory.createTitledBorder ("Ba�l�k");
        komponentleriEkle (ba�l�kl�, "varsay�l� ba�l�kl� s�n�r (varsay�l� hizalama, varsay�l� konumlama)", ba�l�kl�S�n�rlar);

        ba�l�kl� = BorderFactory.createTitledBorder (siyahHat, "Ba�l�k");
        ba�l�kl�KomponentleriEkle (ba�l�kl�, "ba�l�kl� �izgi s�n�r (ortalanm��, varsay�l� konumlama)",
                TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, ba�l�kl�S�n�rlar);

        ba�l�kl� = BorderFactory.createTitledBorder (d���kKenar, "Ba�l�k");
        ba�l�kl�KomponentleriEkle (ba�l�kl�, "ba�l�kl� d���k kenarl� s�n�r (sa�a hizal�, varsay�l� konumlama)",
                TitledBorder.RIGHT, TitledBorder.DEFAULT_POSITION, ba�l�kl�S�n�rlar);

        ba�l�kl� = BorderFactory.createTitledBorder (d���kTaban, "Ba�l�k");
        ba�l�kl�KomponentleriEkle (ba�l�kl�, "ba�l�kl� d���k tabanl� s�n�r (varsay�l� hizalama, �st�n �zerinde)",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP, ba�l�kl�S�n�rlar);

        ba�l�kl� = BorderFactory.createTitledBorder (bo�, "Ba�l�k");
        ba�l�kl�KomponentleriEkle (ba�l�kl�, "ba�l�kl� bo� s�n�r (varsay�l� konumlama, altda)",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BOTTOM, ba�l�kl�S�n�rlar);

        // D�rd�nc� pano: bile�imli s�n�rlar...
        JPanel bili�imliS�n�rlar = new JPanel();
        bili�imliS�n�rlar.setBorder (panoKenar�);
        bili�imliS�n�rlar.setLayout (new BoxLayout (bili�imliS�n�rlar, BoxLayout.Y_AXIS));
        Border k�rm�z��izgi = BorderFactory.createLineBorder (Color.red);

        Border bile�im;
        bile�im = BorderFactory.createCompoundBorder (y�ksekTaban, d���kTaban);
        komponentleriEkle (bile�im, "bile�im s�n�r (�ift yabanl�)", bili�imliS�n�rlar);

        bile�im = BorderFactory.createCompoundBorder ( k�rm�z��izgi, bile�im);
        komponentleriEkle(bile�im, "bile�im s�n�r (k�rm�z� �er�eve ekli)", bili�imliS�n�rlar);

        ba�l�kl� = BorderFactory.createTitledBorder ( bile�im, "Ba�l�k", TitledBorder.CENTER, TitledBorder.BELOW_BOTTOM);
        komponentleriEkle (ba�l�kl�,  "ba�l�kl� bile�im s�n�r (ortalanm��, alt�n alt�nda)", bili�imliS�n�rlar);

        JTabbedPane yaftal�Pano = new JTabbedPane();
        yaftal�Pano.addTab ("Basit", null, basitS�n�rlar, null);
        yaftal�Pano.addTab("Dalgal�", null, paspasl�S�n�rlar, null);
        yaftal�Pano.addTab("Ba�l�kl�", null, ba�l�kl�S�n�rlar, null);
        yaftal�Pano.addTab("Birle�ik", null, bili�imliS�n�rlar, null);
        yaftal�Pano.setSelectedIndex (0);
        String ipucu = new String ("<html>Mavi Dalgal� �izgi s�n�r sanat ekibi:<br>Bill Pauley<br>Cris St.Aubyn<br>Ben Wronsky<br>Nathan Walrath<br>Tommy Adams, �zel dan��man<br>M.Nihat Yava�, l�zumsuz dan��man</html>");
        yaftal�Pano.setToolTipTextAt (1, ipucu);

        add (yaftal�Pano);
    } // J5c_1() kurucusu sonu...

    void ba�l�kl�KomponentleriEkle (TitledBorder s�n�r, String izah, int hizalama, int konum, Container i�erik) {
        s�n�r.setTitleJustification (hizalama);
        s�n�r.setTitlePosition (konum);
        komponentleriEkle (s�n�r, izah, i�erik);
    } // ba�l�kl�KomponentleriEkle(..) metodu sonu...

    void komponentleriEkle (Border s�n�r, String izah, Container i�erik) {
        JPanel bilie�imPanosu = new JPanel (new GridLayout (1, 1), false);
        JLabel etiket = new JLabel (izah, JLabel.CENTER);
        bilie�imPanosu.add (etiket);
        bilie�imPanosu.setBorder (s�n�r);

        i�erik.add (Box.createRigidArea (new Dimension (0, 10)));
        i�erik.add (bilie�imPanosu);
    } // komponentleriEkle(..) metodu sonu...

    // Yol ge�erliyse bir ImageIcon d�nd�r�r, de�ilse null d�ner...
    protected static ImageIcon yaratImageIcon (String yol, String izah) {
        java.net.URL resimURL = J5c_1.class.getResource (yol);
        if (resimURL != null) return new ImageIcon (resimURL, izah);
        else {System.err.println ("Resim dosyas�n�n [ " + yol + "] yolu bulunamad�!"); return null;}
    } // yaratImageIcon(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("S�n�r G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J5c_1 yeni��erikPanosu = new J5c_1(); // Kurucuyu i�let...
        yeni��erikPanosu.setOpaque (true); // ��erik panolar� opak/saydams�z olmal�d�r...
        �er�eve.setContentPane (yeni��erikPanosu);

        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_1 s�n�f� sonu...