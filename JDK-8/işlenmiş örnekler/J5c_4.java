// J5c_4.java: CheckBoxDemo (KontrolKutusuGösterimi) örneði.

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

// Gereken 16 adet resim dosyalarý: portre-/----/ç---/ç--d/çg--/çg-d/çgs-/çgsd/ç-s-/ç-sd/---d/-g--/-g-d/-gs-/-gsd/--s-/--sd
public class J5c_4 extends JPanel implements ItemListener {
    JCheckBox çeneButonu;
    JCheckBox gözlükButonu;
    JCheckBox saçButonu;
    JCheckBox diþButonu;

    /* toplam 16 adet resim, portre-XXXX, burada XXXX=çgsd, yani: ÇeneGözlüksaçDiþ
     * XXXX'a denk gelen 4 KontrolKutusu çentiklendiðinde veya çentiksizlendiðinde
     * ilgili resim tespit edilip güncellenecek. Böylece portre istediðimiz gibi deðiþebilecek.
     */
    StringBuffer tercihler;
    JLabel resimEtiketi;

    public J5c_4() {// Kurucu...
        super (new BorderLayout());

        // Kontrol kutularýný yaratalým...
        çeneButonu = new JCheckBox ("Çene");
        çeneButonu.setMnemonic (KeyEvent.VK_C);
        // KeyEvent iþletilseydi C tuþuyla da çene tercihi deðiþebilecekti...
        çeneButonu.setSelected (true);

        gözlükButonu = new JCheckBox ("Gözlük");
        gözlükButonu.setMnemonic (KeyEvent.VK_G);
        gözlükButonu.setSelected (true);

        saçButonu = new JCheckBox ("Saç");
        saçButonu.setMnemonic (KeyEvent.VK_S);
        saçButonu.setSelected (true);

        diþButonu = new JCheckBox ("Diþ");
        diþButonu.setMnemonic (KeyEvent.VK_D);
        diþButonu.setSelected (true);

        // Kontrol kutularýný ItemListener'e kayýtlayalým...
        çeneButonu.addItemListener (this);
        gözlükButonu.addItemListener (this);
        saçButonu.addItemListener (this);
        diþButonu.addItemListener (this);

        // Portrenin ilk görüntüsündeki 4 adet full terçihleri:
        tercihler = new StringBuffer ("çgsd");

        // Resim etiketini yaratýp resmi yerleþtirelim...
        resimEtiketi = new JLabel();
        resimEtiketi.setFont (resimEtiketi.getFont().deriveFont (Font.ITALIC));
        resmiGüncelle();

        // Kontrol kutularýný tek sütunluk (Grid) yerleþimle bir panele ekleyelim...
        JPanel kontrolPaneli = new JPanel (new GridLayout (0, 1));
        kontrolPaneli.add (saçButonu);
        kontrolPaneli.add (gözlükButonu);
        kontrolPaneli.add (diþButonu);
        kontrolPaneli.add (çeneButonu);

        add (kontrolPaneli, BorderLayout.LINE_START);
        add (resimEtiketi, BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (20,20, 20,20));
        setBackground (new Color (0x88cc11));
    } // J5c_4() kurucu sonu...

    // Kontrol kutularýnýn çentikliliði/çentiksizliðine duyarlý...
    public void itemStateChanged (ItemEvent olay) {
        int endeks = 0;
        char krk = '-';
        Object kaynak = olay.getItemSelectable();
        if (kaynak == çeneButonu) {endeks = 0; krk = 'ç';
        }else if (kaynak == gözlükButonu) {endeks = 1; krk = 'g';
        }else if (kaynak == saçButonu) {endeks = 2; krk = 's';
        }else if (kaynak == diþButonu) {endeks = 3; krk = 'd';
        } // if-else kararý sonu...

        // Hangi butonun týklandýðýný bildiðimize göre, çentiklenip çentiksizlendiðini saptayalým...
        if (olay.getStateChange() == ItemEvent.DESELECTED) krk = '-';

        // deðiþikliði farklý resim ismi tercihler ekine uygulayalým...
        tercihler.setCharAt (endeks, krk);
        resmiGüncelle();
    } // itemStateChanged(..) metodu sonu...

    protected void resmiGüncelle() {
        // Resme uyacak ikonu alalým...
        ImageIcon ikon = resimÝkonunuYarat ("resim/portre-" + tercihler.toString() + ".gif");
        resimEtiketi.setIcon (ikon);
        resimEtiketi.setToolTipText (tercihler.toString()); // resmin ismi ipucu...
        if (ikon == null) resimEtiketi.setText ("Resim Yok");
        else resimEtiketi.setText (null);
    } // resmiGüncelle() metodu sonu...

    protected static ImageIcon resimÝkonunuYarat (String adresliDosya) {
        java.net.URL resimYureli = J5c_4.class.getResource (adresliDosya);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + adresliDosya + "] dosyasý bulunamadý!"); return null;}
    } // resimÝkonunuYarat(..) metodunun sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("KontrolKutusu Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_4();
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav-- ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_4 sýnýfý sonu...