// J5c_50.java: SliderDemo2 (SürgüGösterisi2) örneði.

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

// Gerekli resim dosyalarý: resim/köpekler/T0..T13; toplam 14 resim...
public class J5c_50 extends JPanel
        implements ActionListener, WindowListener, ChangeListener {
    static final int BRS_ENAZ = 0; // BRS: BirsaniyedekiResimSayýsý...
    static final int BRS_ENÇOK = 30;
    static final int BRS_ÝLK = 15;    //initial çerçeves per second
    int resimNo = 0;
    int RESÝM_SAYISI = 14;
    ImageIcon[] resimler = new ImageIcon[RESÝM_SAYISI];
    int geciktirme;
    Timer zamanlayýcý;
    boolean dursunMu = false;
    JLabel resimEtiketi;

    public J5c_50() {// Kurucu...
        super (new BorderLayout());
        geciktirme = 1000 / BRS_ÝLK;

        // Sürgü bu örnekte dikey konumlandýrýlacak...
        JSlider sürgü = new JSlider (JSlider.VERTICAL, BRS_ENAZ, BRS_ENÇOK, BRS_ÝLK);
        sürgü.addChangeListener (this); // Dinleyiciyi sürgü deðiþimine uyarlayalým...
        sürgü.setMajorTickSpacing (10);
        sürgü.setPaintTicks (true);
        
        // Sürgüye yazýlacak hýz ayarý rakam ve metinleri için liste yapalým...
        Hashtable<Integer, JLabel> liste = new Hashtable<Integer, JLabel>();
        liste.put (new Integer (0), new JLabel ("Dur"));
        liste.put (new Integer (BRS_ENÇOK / 6 ), new JLabel ("Yavaþ"));
        liste.put (new Integer (BRS_ENÇOK / 2), new JLabel ("Normal"));
        liste.put (new Integer (BRS_ENÇOK), new JLabel ("Hýzlý"));
        sürgü.setLabelTable (liste);

        sürgü.setPaintLabels (true);
        sürgü.setBorder (BorderFactory.createEmptyBorder (0,0,0,10));
        sürgü.setBackground (new Color ( (int)(Math.random()*200+56), (int)(Math.random()*200+56), (int)(Math.random()*200+56) )); // Çok açýk renkler...
        sürgü.setForeground (Color.WHITE);

        // Animasyon resimlerinin içine konulacaðý etiketi yaratýp kuralým...
        resimEtiketi = new JLabel();
        resimEtiketi.setHorizontalAlignment (JLabel.CENTER);
        resimEtiketi.setAlignmentX (Component.CENTER_ALIGNMENT);
        resimEtiketi.setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder (10,10,10,10)));
        resmiGüncelle (0); // Ýlk 0 no'lu resmi koyarak baþlayalým...

        // Komponentleri pencereye ekleyelim...
        add (sürgü, BorderLayout.LINE_START); // Pencere soluna...
        add (resimEtiketi, BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Aksiyon dinleyicisine duyarlý zamanlayýcýyý kuralým...
        zamanlayýcý = new Timer (geciktirme, this);
        zamanlayýcý.setInitialDelay (geciktirme * 7); // Her turun baþ ve sonunda HRS'yle orantýlý duraksar...
        //zamanlayýcý.setCoalesce (true); // Koalisyon gerekli mi?..
    } // J5c_50() kurucusu sonu...

    // Pencereyi olaylara (ikonlaþtýrma/pencereleme) duyarlayalým...
    void addWindowListener (Window pencere) {pencere.addWindowListener (this);}
    public void windowIconified (WindowEvent olay) {canlandýrmayýDurdur();}
    public void windowDeiconified (WindowEvent olay) {canlandýrmayýBaþlat();}
    public void windowOpened (WindowEvent aldýrma) {}
    public void windowClosing (WindowEvent aldýrma) {}
    public void windowClosed (WindowEvent aldýrma) {}
    public void windowActivated (WindowEvent aldýrma) {}
    public void windowDeactivated (WindowEvent aldýrma) {}

    // Sürgü deðiþimine duyarlýdýr...
    public void stateChanged (ChangeEvent olay) {
        JSlider sürgüKonumu = (JSlider)olay.getSource();
        if (!sürgüKonumu.getValueIsAdjusting()) {
            int brs = (int)sürgüKonumu.getValue(); // brs: Birsaniyedeki Resim Sayýsý...
            if (brs == 0) {if (!dursunMu) canlandýrmayýDurdur();
            }else {geciktirme = 1000 / brs;
                zamanlayýcý.setDelay (geciktirme);
                zamanlayýcý.setInitialDelay (geciktirme * 10);
                if (dursunMu) canlandýrmayýBaþlat();
            } // else kararý sonu...
        } // dýþ-if kararý sonu...
    } // stateChanged(..) hazýr metodu sonu...

    public void canlandýrmayýBaþlat() {zamanlayýcý.start(); dursunMu = false;}
    public void canlandýrmayýDurdur() {zamanlayýcý.stop(); dursunMu = true;}

    // Timer zamanlayýcý.start() ile tetiklenir...
    public void actionPerformed (ActionEvent olay) {
        if (resimNo == (RESÝM_SAYISI - 1)) resimNo = 0;
        else resimNo++;

        resmiGüncelle (resimNo);

        if (resimNo==(RESÝM_SAYISI - 1) || resimNo==(RESÝM_SAYISI/2 - 1) ) zamanlayýcý.restart();
    } // actionPerformed(..) hazýr metodu sonu...

    protected void resmiGüncelle (int resimNo) {
        if (resimler[resimNo] == null) resimler[resimNo] = resimÝkonuYarat ("resim/köpekler/T" + resimNo + ".gif");

        if (resimler[resimNo] != null) resimEtiketi.setIcon (resimler[resimNo]);
        else resimEtiketi.setText ("Resim: T" + resimNo + " bulunamadý!");
    } // resmiGüncelle(..) metodu sonu...

    protected static ImageIcon resimÝkonuYarat (String yol) {
        java.net.URL resimYureli = J5c_50.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Sürgü Gösterisi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_50 canlandýrýcý = new J5c_50(); // Kurucuyu çaðýr...
        çerçeve.add (canlandýrýcý, BorderLayout.CENTER);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
        canlandýrýcý.canlandýrmayýBaþlat(); 
    } // yaratVeGösterGUI() metodu sonu...

    public static void main(String[] args) {
        javax.swing.UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yazmak yok...
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_50 sýnýfý sonu...