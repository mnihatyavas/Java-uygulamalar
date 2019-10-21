// J5c_50.java: SliderDemo2 (S�rg�G�sterisi2) �rne�i.

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.Timer;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.util.Hashtable;

// Gerekli resim dosyalar�: resim/k�pekler/T0..T13; toplam 14 resim...
public class J5c_50 extends JPanel
        implements ActionListener, WindowListener, ChangeListener {
    static final int BRS_ENAZ = 0; // BRS: BirsaniyedekiResimSay�s�...
    static final int BRS_EN�OK = 30;
    static final int BRS_�LK = 15;    //initial �er�eves per second
    int resimNo = 0;
    int RES�M_SAYISI = 14;
    ImageIcon[] resimler = new ImageIcon[RES�M_SAYISI];
    int geciktirme;
    Timer zamanlay�c�;
    boolean dursunMu = false;
    JLabel resimEtiketi;

    public J5c_50() {// Kurucu...
        super (new BorderLayout());
        geciktirme = 1000 / BRS_�LK;

        // S�rg� bu �rnekte dikey konumland�r�lacak...
        JSlider s�rg� = new JSlider (JSlider.VERTICAL, BRS_ENAZ, BRS_EN�OK, BRS_�LK);
        s�rg�.addChangeListener (this); // Dinleyiciyi s�rg� de�i�imine uyarlayal�m...
        s�rg�.setMajorTickSpacing (10);
        s�rg�.setPaintTicks (true);
        
        // S�rg�ye yaz�lacak h�z ayar� rakam ve metinleri i�in liste yapal�m...
        Hashtable<Integer, JLabel> liste = new Hashtable<Integer, JLabel>();
        liste.put (new Integer (0), new JLabel ("Dur"));
        liste.put (new Integer (BRS_EN�OK / 6 ), new JLabel ("Yava�"));
        liste.put (new Integer (BRS_EN�OK / 2), new JLabel ("Normal"));
        liste.put (new Integer (BRS_EN�OK), new JLabel ("H�zl�"));
        s�rg�.setLabelTable (liste);

        s�rg�.setPaintLabels (true);
        s�rg�.setBorder (BorderFactory.createEmptyBorder (0,0,0,10));
        s�rg�.setBackground (new Color ( (int)(Math.random()*200+56), (int)(Math.random()*200+56), (int)(Math.random()*200+56) )); // �ok a��k renkler...
        s�rg�.setForeground (Color.WHITE);

        // Animasyon resimlerinin i�ine konulaca�� etiketi yarat�p kural�m...
        resimEtiketi = new JLabel();
        resimEtiketi.setHorizontalAlignment (JLabel.CENTER);
        resimEtiketi.setAlignmentX (Component.CENTER_ALIGNMENT);
        resimEtiketi.setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder (10,10,10,10)));
        resmiG�ncelle (0); // �lk 0 no'lu resmi koyarak ba�layal�m...

        // Komponentleri pencereye ekleyelim...
        add (s�rg�, BorderLayout.LINE_START); // Pencere soluna...
        add (resimEtiketi, BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Aksiyon dinleyicisine duyarl� zamanlay�c�y� kural�m...
        zamanlay�c� = new Timer (geciktirme, this);
        zamanlay�c�.setInitialDelay (geciktirme * 7); // Her turun ba� ve sonunda HRS'yle orant�l� duraksar...
        //zamanlay�c�.setCoalesce (true); // Koalisyon gerekli mi?..
    } // J5c_50() kurucusu sonu...

    // Pencereyi olaylara (ikonla�t�rma/pencereleme) duyarlayal�m...
    void addWindowListener (Window pencere) {pencere.addWindowListener (this);}
    public void windowIconified (WindowEvent olay) {canland�rmay�Durdur();}
    public void windowDeiconified (WindowEvent olay) {canland�rmay�Ba�lat();}
    public void windowOpened (WindowEvent ald�rma) {}
    public void windowClosing (WindowEvent ald�rma) {}
    public void windowClosed (WindowEvent ald�rma) {}
    public void windowActivated (WindowEvent ald�rma) {}
    public void windowDeactivated (WindowEvent ald�rma) {}

    // S�rg� de�i�imine duyarl�d�r...
    public void stateChanged (ChangeEvent olay) {
        JSlider s�rg�Konumu = (JSlider)olay.getSource();
        if (!s�rg�Konumu.getValueIsAdjusting()) {
            int brs = (int)s�rg�Konumu.getValue(); // brs: Birsaniyedeki Resim Say�s�...
            if (brs == 0) {if (!dursunMu) canland�rmay�Durdur();
            }else {geciktirme = 1000 / brs;
                zamanlay�c�.setDelay (geciktirme);
                zamanlay�c�.setInitialDelay (geciktirme * 10);
                if (dursunMu) canland�rmay�Ba�lat();
            } // else karar� sonu...
        } // d��-if karar� sonu...
    } // stateChanged(..) haz�r metodu sonu...

    public void canland�rmay�Ba�lat() {zamanlay�c�.start(); dursunMu = false;}
    public void canland�rmay�Durdur() {zamanlay�c�.stop(); dursunMu = true;}

    // Timer zamanlay�c�.start() ile tetiklenir...
    public void actionPerformed (ActionEvent olay) {
        if (resimNo == (RES�M_SAYISI - 1)) resimNo = 0;
        else resimNo++;

        resmiG�ncelle (resimNo);

        if (resimNo==(RES�M_SAYISI - 1) || resimNo==(RES�M_SAYISI/2 - 1) ) zamanlay�c�.restart();
    } // actionPerformed(..) haz�r metodu sonu...

    protected void resmiG�ncelle (int resimNo) {
        if (resimler[resimNo] == null) resimler[resimNo] = resim�konuYarat ("resim/k�pekler/T" + resimNo + ".gif");

        if (resimler[resimNo] != null) resimEtiketi.setIcon (resimler[resimNo]);
        else resimEtiketi.setText ("Resim: T" + resimNo + " bulunamad�!");
    } // resmiG�ncelle(..) metodu sonu...

    protected static ImageIcon resim�konuYarat (String yol) {
        java.net.URL resimYureli = J5c_50.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("S�rg� G�sterisi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_50 canland�r�c� = new J5c_50(); // Kurucuyu �a��r...
        �er�eve.add (canland�r�c�, BorderLayout.CENTER);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
        canland�r�c�.canland�rmay�Ba�lat(); 
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main(String[] args) {
        javax.swing.UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yazmak yok...
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_50 s�n�f� sonu...