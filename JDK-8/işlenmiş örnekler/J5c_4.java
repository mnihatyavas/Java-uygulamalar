// J5c_4.java: CheckBoxDemo (KontrolKutusuG�sterimi) �rne�i.

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

// Gereken 16 adet resim dosyalar�: portre-/----/�---/�--d/�g--/�g-d/�gs-/�gsd/�-s-/�-sd/---d/-g--/-g-d/-gs-/-gsd/--s-/--sd
public class J5c_4 extends JPanel implements ItemListener {
    JCheckBox �eneButonu;
    JCheckBox g�zl�kButonu;
    JCheckBox sa�Butonu;
    JCheckBox di�Butonu;

    /* toplam 16 adet resim, portre-XXXX, burada XXXX=�gsd, yani: �eneG�zl�ksa�Di�
     * XXXX'a denk gelen 4 KontrolKutusu �entiklendi�inde veya �entiksizlendi�inde
     * ilgili resim tespit edilip g�ncellenecek. B�ylece portre istedi�imiz gibi de�i�ebilecek.
     */
    StringBuffer tercihler;
    JLabel resimEtiketi;

    public J5c_4() {// Kurucu...
        super (new BorderLayout());

        // Kontrol kutular�n� yaratal�m...
        �eneButonu = new JCheckBox ("�ene");
        �eneButonu.setMnemonic (KeyEvent.VK_C);
        // KeyEvent i�letilseydi C tu�uyla da �ene tercihi de�i�ebilecekti...
        �eneButonu.setSelected (true);

        g�zl�kButonu = new JCheckBox ("G�zl�k");
        g�zl�kButonu.setMnemonic (KeyEvent.VK_G);
        g�zl�kButonu.setSelected (true);

        sa�Butonu = new JCheckBox ("Sa�");
        sa�Butonu.setMnemonic (KeyEvent.VK_S);
        sa�Butonu.setSelected (true);

        di�Butonu = new JCheckBox ("Di�");
        di�Butonu.setMnemonic (KeyEvent.VK_D);
        di�Butonu.setSelected (true);

        // Kontrol kutular�n� ItemListener'e kay�tlayal�m...
        �eneButonu.addItemListener (this);
        g�zl�kButonu.addItemListener (this);
        sa�Butonu.addItemListener (this);
        di�Butonu.addItemListener (this);

        // Portrenin ilk g�r�nt�s�ndeki 4 adet full ter�ihleri:
        tercihler = new StringBuffer ("�gsd");

        // Resim etiketini yarat�p resmi yerle�tirelim...
        resimEtiketi = new JLabel();
        resimEtiketi.setFont (resimEtiketi.getFont().deriveFont (Font.ITALIC));
        resmiG�ncelle();

        // Kontrol kutular�n� tek s�tunluk (Grid) yerle�imle bir panele ekleyelim...
        JPanel kontrolPaneli = new JPanel (new GridLayout (0, 1));
        kontrolPaneli.add (sa�Butonu);
        kontrolPaneli.add (g�zl�kButonu);
        kontrolPaneli.add (di�Butonu);
        kontrolPaneli.add (�eneButonu);

        add (kontrolPaneli, BorderLayout.LINE_START);
        add (resimEtiketi, BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (20,20, 20,20));
        setBackground (new Color (0x88cc11));
    } // J5c_4() kurucu sonu...

    // Kontrol kutular�n�n �entiklili�i/�entiksizli�ine duyarl�...
    public void itemStateChanged (ItemEvent olay) {
        int endeks = 0;
        char krk = '-';
        Object kaynak = olay.getItemSelectable();
        if (kaynak == �eneButonu) {endeks = 0; krk = '�';
        }else if (kaynak == g�zl�kButonu) {endeks = 1; krk = 'g';
        }else if (kaynak == sa�Butonu) {endeks = 2; krk = 's';
        }else if (kaynak == di�Butonu) {endeks = 3; krk = 'd';
        } // if-else karar� sonu...

        // Hangi butonun t�kland���n� bildi�imize g�re, �entiklenip �entiksizlendi�ini saptayal�m...
        if (olay.getStateChange() == ItemEvent.DESELECTED) krk = '-';

        // de�i�ikli�i farkl� resim ismi tercihler ekine uygulayal�m...
        tercihler.setCharAt (endeks, krk);
        resmiG�ncelle();
    } // itemStateChanged(..) metodu sonu...

    protected void resmiG�ncelle() {
        // Resme uyacak ikonu alal�m...
        ImageIcon ikon = resim�konunuYarat ("resim/portre-" + tercihler.toString() + ".gif");
        resimEtiketi.setIcon (ikon);
        resimEtiketi.setToolTipText (tercihler.toString()); // resmin ismi ipucu...
        if (ikon == null) resimEtiketi.setText ("Resim Yok");
        else resimEtiketi.setText (null);
    } // resmiG�ncelle() metodu sonu...

    protected static ImageIcon resim�konunuYarat (String adresliDosya) {
        java.net.URL resimYureli = J5c_4.class.getResource (adresliDosya);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + adresliDosya + "] dosyas� bulunamad�!"); return null;}
    } // resim�konunuYarat(..) metodunun sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("KontrolKutusu G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_4();
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav-- ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_4 s�n�f� sonu...