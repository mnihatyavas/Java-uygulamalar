// J5f_5.java: BoxLayoutDemo2 (KutuSerilimiGösterisi2) örneði.

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

// Gereken dosya: J5f_5x.java=BLDComponent.java (KSG:KutuSerilimiGösterisi/BLD:BoxLayoutDemo)...
public class J5f_5 implements ItemListener {
    protected static int KOMPONENT_SAYISI = 4;
    protected static float[] xHizalamalar = {
            Component.LEFT_ALIGNMENT,
            Component.CENTER_ALIGNMENT,
            Component.RIGHT_ALIGNMENT,
            Component.RIGHT_ALIGNMENT};
    protected static float[] renkler = {0.0f, 0.35f, 0.67f, 0.87f}; // Kýrmýzýdan mora...
    protected static boolean ebatSýnýrlýMI = true;
    protected static boolean tesadüfiEbatGösterilsinMi = true;
    protected static J5f_5x[] ksgKomponentleri = new J5f_5x[KOMPONENT_SAYISI];

    public void komponentleriKabaEkle (Container kab) {
        JPanel panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.PAGE_AXIS)); // Kutularý sayfa eksenine altalta ekleyecek...

        // Dikdörtgen kutularý yaratalým...
        int asgariKutuEbatý = 15;
        for (int i = 0; i < KOMPONENT_SAYISI; i++) {
            if (tesadüfiEbatGösterilsinMi) asgariKutuEbatý = (int)(30.0 * Math.random()) + 30; // [30->60)...
            else asgariKutuEbatý += 10;

            ksgKomponentleri[i] = new J5f_5x (
                    xHizalamalar[i],
                    renkler[i],
                    asgariKutuEbatý,
                    ebatSýnýrlýMI,
                    tesadüfiEbatGösterilsinMi,
                    String.valueOf (i));
            panel.add (ksgKomponentleri[i]);
        } // for döngüsü sonu...

        // Alt talimatý ve çentikli onayý kuralým...
        JLabel etiket = new JLabel ("X hizalanmasýný deðiþtirmek için bir dikdörtgeni týkla");
        JCheckBox çentikKutusu = new JCheckBox ("Azami dikdörtgen ebatýný sýnýrla");
        çentikKutusu.setSelected (ebatSýnýrlýMI);
        çentikKutusu.addItemListener (this); // Dinleyiciye duyarayalým...

        panel.setBorder (BorderFactory.createLineBorder (Color.RED));

        Box kutu = Box.createVerticalBox(); // Komponentler dikey altalta eklenecek...
        kutu.add (etiket);
        kutu.add (çentikKutusu);

        kab.add (panel, BorderLayout.CENTER);
        kab.add (kutu, BorderLayout.PAGE_END);
    } // komponentleriKabaEkle(..) metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        if (olay.getStateChange() == ItemEvent.SELECTED) ebatSýnýrlýMI = true;
        else ebatSýnýrlýMI = false;
        deðiþtirKSGKomponentlerini();
    } // itemStateChanged(..) hazýr metodu sonu...

    static public void deðiþtirKSGKomponentlerini() {
        for (int i = 0; i < KOMPONENT_SAYISI; i++) ksgKomponentleri[i].setSizeRestriction (ebatSýnýrlýMI);
        ksgKomponentleri[0].revalidate();
    } // deðiþtirKSGKomponentlerini() metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kutu Serilimi Gösterisi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5f_5 yeniÝçerikPanosu = new J5f_5(); // Varsayýlý kurucuyu çaðýrýr...
        yeniÝçerikPanosu.komponentleriKabaEkle (çerçeve.getContentPane());
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_5 sýnýfý sonu...

/* Çýktý:
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