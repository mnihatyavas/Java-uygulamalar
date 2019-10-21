// J5f_5.java: BoxLayoutDemo2 (KutuSerilimiG�sterisi2) �rne�i.

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_5x.java=BLDComponent.java (KSG:KutuSerilimiG�sterisi/BLD:BoxLayoutDemo)...
public class J5f_5 implements ItemListener {
    protected static int KOMPONENT_SAYISI = 4;
    protected static float[] xHizalamalar = {
            Component.LEFT_ALIGNMENT,
            Component.CENTER_ALIGNMENT,
            Component.RIGHT_ALIGNMENT,
            Component.RIGHT_ALIGNMENT};
    protected static float[] renkler = {0.0f, 0.35f, 0.67f, 0.87f}; // K�rm�z�dan mora...
    protected static boolean ebatS�n�rl�MI = true;
    protected static boolean tesad�fiEbatG�sterilsinMi = true;
    protected static J5f_5x[] ksgKomponentleri = new J5f_5x[KOMPONENT_SAYISI];

    public void komponentleriKabaEkle (Container kab) {
        JPanel panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.PAGE_AXIS)); // Kutular� sayfa eksenine altalta ekleyecek...

        // Dikd�rtgen kutular� yaratal�m...
        int asgariKutuEbat� = 15;
        for (int i = 0; i < KOMPONENT_SAYISI; i++) {
            if (tesad�fiEbatG�sterilsinMi) asgariKutuEbat� = (int)(30.0 * Math.random()) + 30; // [30->60)...
            else asgariKutuEbat� += 10;

            ksgKomponentleri[i] = new J5f_5x (
                    xHizalamalar[i],
                    renkler[i],
                    asgariKutuEbat�,
                    ebatS�n�rl�MI,
                    tesad�fiEbatG�sterilsinMi,
                    String.valueOf (i));
            panel.add (ksgKomponentleri[i]);
        } // for d�ng�s� sonu...

        // Alt talimat� ve �entikli onay� kural�m...
        JLabel etiket = new JLabel ("X hizalanmas�n� de�i�tirmek i�in bir dikd�rtgeni t�kla");
        JCheckBox �entikKutusu = new JCheckBox ("Azami dikd�rtgen ebat�n� s�n�rla");
        �entikKutusu.setSelected (ebatS�n�rl�MI);
        �entikKutusu.addItemListener (this); // Dinleyiciye duyarayal�m...

        panel.setBorder (BorderFactory.createLineBorder (Color.RED));

        Box kutu = Box.createVerticalBox(); // Komponentler dikey altalta eklenecek...
        kutu.add (etiket);
        kutu.add (�entikKutusu);

        kab.add (panel, BorderLayout.CENTER);
        kab.add (kutu, BorderLayout.PAGE_END);
    } // komponentleriKabaEkle(..) metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        if (olay.getStateChange() == ItemEvent.SELECTED) ebatS�n�rl�MI = true;
        else ebatS�n�rl�MI = false;
        de�i�tirKSGKomponentlerini();
    } // itemStateChanged(..) haz�r metodu sonu...

    static public void de�i�tirKSGKomponentlerini() {
        for (int i = 0; i < KOMPONENT_SAYISI; i++) ksgKomponentleri[i].setSizeRestriction (ebatS�n�rl�MI);
        ksgKomponentleri[0].revalidate();
    } // de�i�tirKSGKomponentlerini() metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kutu Serilimi G�sterisi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5f_5 yeni��erikPanosu = new J5f_5(); // Varsay�l� kurucuyu �a��r�r...
        yeni��erikPanosu.komponentleriKabaEkle (�er�eve.getContentPane());
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_5 s�n�f� sonu...

/* ��kt�:
**  >java J5f_5  **
KSGKomponenti 3: Ebat 74x37; Tercihi ebat 74x37
KSGKomponenti 2: Ebat 68x34; Tercihi ebat 68x34
KSGKomponenti 1: Ebat 72x36; Tercihi ebat 72x36
KSGKomponenti 0: Ebat 108x54; Tercihi ebat 108x54
KSGKomponenti 3: Ebat 74x37; Tercihi ebat 74x37
KSGKomponenti 2: Ebat 68x34; Tercihi ebat 68x34
KSGKomponenti 1: Ebat 72x36; Tercihi ebat 72x36
KSGKomponenti 0: Ebat 108x54; Tercihi ebat 108x54
*/