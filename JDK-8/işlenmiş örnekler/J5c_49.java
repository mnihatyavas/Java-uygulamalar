// J5c_49.java: SliderDemo (S�rg�G�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

// Gereken resim dosyalar�: "resim/k�pekler/T0..13.gif", toplam 14 resim...
public class J5c_49 extends JPanel
        implements ActionListener, WindowListener, ChangeListener {
    // Animasyon paametrelerini kural�m...
    static final int BRS_ASGAR� = 0; // BRS: BirsaniyedekiResimSay�s�
    static final int BRS_AZAM� = 30;
    static final int BRS_�LK = 15;
    int resimNo = 0;
    int RES�M_SAYISI = 14;
    ImageIcon[] resimler = new ImageIcon[RES�M_SAYISI];
    int bekletme;
    Timer zamanlay�c�;
    boolean donsunMu = false;
    JLabel resimEtiketi; // Bu etiket k�pek resimlerini g�steren ImageIcon'u kullan�r...

    public J5c_49() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));

        bekletme = 1000 / BRS_�LK;

        // S�rg� �st yaz�s� etiketi...
        JLabel s�rg�MetniEtiketi = new JLabel ("Saniyedeki Resim Say�s�", JLabel.CENTER);
        s�rg�MetniEtiketi.setAlignmentX (Component.CENTER_ALIGNMENT);
        s�rg�MetniEtiketi.setForeground (Color.WHITE);

        // Yatay s�rg�y� yaratal�m...
        JSlider s�rg� = new JSlider (JSlider.HORIZONTAL, BRS_ASGAR�, BRS_AZAM�, BRS_�LK);
        s�rg�.addChangeListener (this); // S�rg� de�i�imini dinleyiciye duyarlayal�m...

        // S�rg�l� cetveldeki b�y�k ve k���k (dikey) kertikleri koyal�m...
        s�rg�.setMajorTickSpacing (10);
        s�rg�.setMinorTickSpacing (1);
        s�rg�.setPaintTicks (true);
        s�rg�.setPaintLabels (true);
        s�rg�.setBorder (BorderFactory.createEmptyBorder (0,0,10,0)); // Sadece alta tampon...
        Font yaz�Fonu = new Font ("Serif", Font.ITALIC, 15);
        s�rg�.setFont (yaz�Fonu); // S�rg� rakamlar� i�in...
        s�rg�.setBackground (new Color ( (int)(Math.random()*200+56), (int)(Math.random()*200+56), (int)(Math.random()*200+56) )); // �ok a��k renkler...
        s�rg�.setForeground (Color.WHITE);

        // Animasyonu g�sterecek olan etiketi yarat�p kural�m...
        resimEtiketi = new JLabel();
        resimEtiketi.setHorizontalAlignment (JLabel.CENTER);
        resimEtiketi.setAlignmentX (Component.CENTER_ALIGNMENT);
        resimEtiketi.setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder (10,10,10,10))); // Etiketle resim aras� i� tampon...

        resmiG�ncelle (0); // 0 endeksli ilk resmi (T0) g�ster...

        // S�rg� metni, s�rg� ve resim etiketini penceremize ekleyelim...
        add (s�rg�MetniEtiketi);
        add (s�rg�);
        add (resimEtiketi);
        // Pencere etraf�na 10px'er bo�luk b�rakal�m...
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Aksiyon dinleyicisine duyarl� olan bir Timer zamanlay�c� kural�m...
        zamanlay�c� = new Timer (bekletme, this);
        zamanlay�c�.setInitialDelay (bekletme * 7); // Bir turda 2 kez (ba�ta ve sonda) duraksar...
        //zamanlay�c�.setCoalesce (true); // Burda koalisyon/birle�me gerekli mi?..
    } // J5c_49() kurucusu sonu...

    // Pencere olaylar�n� (pencere g�rev �ubu�una ufalt�ld� veya a��ld�) dinleyelim...
    void addWindowListener (Window pencere) {pencere.addWindowListener (this);}
    public void windowIconified (WindowEvent olay) {animasyonuDurdur();}
    public void windowDeiconified (WindowEvent olay) {animasyonuBa�lat();}
    public void windowOpened (WindowEvent ald�rma) {}
    public void windowClosing (WindowEvent ald�rma) {}
    public void windowClosed (WindowEvent ald�rma) {}
    public void windowActivated (WindowEvent ald�rma) {}
    public void windowDeactivated (WindowEvent ald�rma) {}

    // S�rg� de�i�imini dinler...
    public void stateChanged (ChangeEvent olay) {
        JSlider de�i�imKayna�� = (JSlider)olay.getSource();
        if (!de�i�imKayna��.getValueIsAdjusting()) {
            int brs = (int)de�i�imKayna��.getValue();
            if (brs == 0) {
                if (!donsunMu) animasyonuDurdur(); // S�rg�y� en sola �ektiysen...
            }else {
                bekletme = 1000 / brs;
                zamanlay�c�.setDelay (bekletme);
                zamanlay�c�.setInitialDelay (bekletme * 10);
                if (donsunMu) animasyonuBa�lat();
            } // else karar� sonu...
        } // d��-if karar� sonu...
    } // stateChanged(..) haz�r metodu sonu...

    public void animasyonuBa�lat() {
        // Canland�rmay�/hareketlendirmeyi ba�lat  (veya yeniden ba�lat)...
        zamanlay�c�.start();
        donsunMu = false;
    } // animasyonuBa�lat() metodu sonu...

    public void animasyonuDurdur() {
        // Canland�rma sicimini durdurur...
        zamanlay�c�.stop();
        donsunMu = true;
    } // animasyonuDurdur() metodu sonu...

    // Zamanlay�c� tetiklenince �a�r�l�r (Timer zamanlay�c�.start() ile)...
    public void actionPerformed (ActionEvent olay) {
        // S�radaki bir sonraki, veya sonuncudaysa ilk resmi al�r...
        if (resimNo == (RES�M_SAYISI - 1)) resimNo = 0; // Son resimse ilkini al...
        else resimNo++; // S�radaki bir sonraki resmi al...

        resmiG�ncelle (resimNo); // Akt�el al�nan resmi g�ster...
        
        if ( resimNo==(RES�M_SAYISI - 1) || resimNo==(RES�M_SAYISI/2 - 1) ) zamanlay�c�.restart();
    } // actionPerformed(..) haz�r metodu sonu...

    // Akt�el resmi g�stermek i�in resim etiketini g�nceller...
    protected void resmiG�ncelle (int �er�eveNum) {
        // E�er �nceden al�nmad�ysa, ilgili resmi �imdi alal�m...
        if (resimler[resimNo] == null) resimler[resimNo] = resim�konunuYarat (
                "resim/k�pekler/T" + resimNo + ".gif");
        // Ald���m�z resmi etikete kural�m...
        if (resimler[resimNo] != null) resimEtiketi.setIcon (resimler[resimNo]);
        else resimEtiketi.setText ("Resim: T" + resimNo + " bulunamad�!");
    } // resmiG�ncelle(..) metodu sonu...

    protected static ImageIcon resim�konunuYarat (String yol) {
        java.net.URL resimYureli = J5c_49.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("S�rg� G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_49 animat�r = new J5c_49(); // Kurucuyu �a��r...
        �er�eve.add (animat�r, BorderLayout.CENTER);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
        animat�r.animasyonuBa�lat(); 
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yaz� fonu kullan�m�n� kap�yoruz...
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_49 s�n�f� sonu...