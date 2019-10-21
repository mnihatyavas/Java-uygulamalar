// J5c_49.java: SliderDemo (SürgüGösterisi) örneði.

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

// Gereken resim dosyalarý: "resim/köpekler/T0..13.gif", toplam 14 resim...
public class J5c_49 extends JPanel
        implements ActionListener, WindowListener, ChangeListener {
    // Animasyon paametrelerini kuralým...
    static final int BRS_ASGARÝ = 0; // BRS: BirsaniyedekiResimSayýsý
    static final int BRS_AZAMÝ = 30;
    static final int BRS_ÝLK = 15;
    int resimNo = 0;
    int RESÝM_SAYISI = 14;
    ImageIcon[] resimler = new ImageIcon[RESÝM_SAYISI];
    int bekletme;
    Timer zamanlayýcý;
    boolean donsunMu = false;
    JLabel resimEtiketi; // Bu etiket köpek resimlerini gösteren ImageIcon'u kullanýr...

    public J5c_49() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));

        bekletme = 1000 / BRS_ÝLK;

        // Sürgü üst yazýsý etiketi...
        JLabel sürgüMetniEtiketi = new JLabel ("Saniyedeki Resim Sayýsý", JLabel.CENTER);
        sürgüMetniEtiketi.setAlignmentX (Component.CENTER_ALIGNMENT);
        sürgüMetniEtiketi.setForeground (Color.WHITE);

        // Yatay sürgüyü yaratalým...
        JSlider sürgü = new JSlider (JSlider.HORIZONTAL, BRS_ASGARÝ, BRS_AZAMÝ, BRS_ÝLK);
        sürgü.addChangeListener (this); // Sürgü deðiþimini dinleyiciye duyarlayalým...

        // Sürgülü cetveldeki büyük ve küçük (dikey) kertikleri koyalým...
        sürgü.setMajorTickSpacing (10);
        sürgü.setMinorTickSpacing (1);
        sürgü.setPaintTicks (true);
        sürgü.setPaintLabels (true);
        sürgü.setBorder (BorderFactory.createEmptyBorder (0,0,10,0)); // Sadece alta tampon...
        Font yazýFonu = new Font ("Serif", Font.ITALIC, 15);
        sürgü.setFont (yazýFonu); // Sürgü rakamlarý için...
        sürgü.setBackground (new Color ( (int)(Math.random()*200+56), (int)(Math.random()*200+56), (int)(Math.random()*200+56) )); // Çok açýk renkler...
        sürgü.setForeground (Color.WHITE);

        // Animasyonu gösterecek olan etiketi yaratýp kuralým...
        resimEtiketi = new JLabel();
        resimEtiketi.setHorizontalAlignment (JLabel.CENTER);
        resimEtiketi.setAlignmentX (Component.CENTER_ALIGNMENT);
        resimEtiketi.setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder (10,10,10,10))); // Etiketle resim arasý iç tampon...

        resmiGüncelle (0); // 0 endeksli ilk resmi (T0) göster...

        // Sürgü metni, sürgü ve resim etiketini penceremize ekleyelim...
        add (sürgüMetniEtiketi);
        add (sürgü);
        add (resimEtiketi);
        // Pencere etrafýna 10px'er boþluk býrakalým...
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // Aksiyon dinleyicisine duyarlý olan bir Timer zamanlayýcý kuralým...
        zamanlayýcý = new Timer (bekletme, this);
        zamanlayýcý.setInitialDelay (bekletme * 7); // Bir turda 2 kez (baþta ve sonda) duraksar...
        //zamanlayýcý.setCoalesce (true); // Burda koalisyon/birleþme gerekli mi?..
    } // J5c_49() kurucusu sonu...

    // Pencere olaylarýný (pencere görev çubuðuna ufaltýldý veya açýldý) dinleyelim...
    void addWindowListener (Window pencere) {pencere.addWindowListener (this);}
    public void windowIconified (WindowEvent olay) {animasyonuDurdur();}
    public void windowDeiconified (WindowEvent olay) {animasyonuBaþlat();}
    public void windowOpened (WindowEvent aldýrma) {}
    public void windowClosing (WindowEvent aldýrma) {}
    public void windowClosed (WindowEvent aldýrma) {}
    public void windowActivated (WindowEvent aldýrma) {}
    public void windowDeactivated (WindowEvent aldýrma) {}

    // Sürgü deðiþimini dinler...
    public void stateChanged (ChangeEvent olay) {
        JSlider deðiþimKaynaðý = (JSlider)olay.getSource();
        if (!deðiþimKaynaðý.getValueIsAdjusting()) {
            int brs = (int)deðiþimKaynaðý.getValue();
            if (brs == 0) {
                if (!donsunMu) animasyonuDurdur(); // Sürgüyü en sola çektiysen...
            }else {
                bekletme = 1000 / brs;
                zamanlayýcý.setDelay (bekletme);
                zamanlayýcý.setInitialDelay (bekletme * 10);
                if (donsunMu) animasyonuBaþlat();
            } // else kararý sonu...
        } // dýþ-if kararý sonu...
    } // stateChanged(..) hazýr metodu sonu...

    public void animasyonuBaþlat() {
        // Canlandýrmayý/hareketlendirmeyi baþlat  (veya yeniden baþlat)...
        zamanlayýcý.start();
        donsunMu = false;
    } // animasyonuBaþlat() metodu sonu...

    public void animasyonuDurdur() {
        // Canlandýrma sicimini durdurur...
        zamanlayýcý.stop();
        donsunMu = true;
    } // animasyonuDurdur() metodu sonu...

    // Zamanlayýcý tetiklenince çaðrýlýr (Timer zamanlayýcý.start() ile)...
    public void actionPerformed (ActionEvent olay) {
        // Sýradaki bir sonraki, veya sonuncudaysa ilk resmi alýr...
        if (resimNo == (RESÝM_SAYISI - 1)) resimNo = 0; // Son resimse ilkini al...
        else resimNo++; // Sýradaki bir sonraki resmi al...

        resmiGüncelle (resimNo); // Aktüel alýnan resmi göster...
        
        if ( resimNo==(RESÝM_SAYISI - 1) || resimNo==(RESÝM_SAYISI/2 - 1) ) zamanlayýcý.restart();
    } // actionPerformed(..) hazýr metodu sonu...

    // Aktüel resmi göstermek için resim etiketini günceller...
    protected void resmiGüncelle (int çerçeveNum) {
        // Eðer önceden alýnmadýysa, ilgili resmi þimdi alalým...
        if (resimler[resimNo] == null) resimler[resimNo] = resimÝkonunuYarat (
                "resim/köpekler/T" + resimNo + ".gif");
        // Aldýðýmýz resmi etikete kuralým...
        if (resimler[resimNo] != null) resimEtiketi.setIcon (resimler[resimNo]);
        else resimEtiketi.setText ("Resim: T" + resimNo + " bulunamadý!");
    } // resmiGüncelle(..) metodu sonu...

    protected static ImageIcon resimÝkonunuYarat (String yol) {
        java.net.URL resimYureli = J5c_49.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Sürgü Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_49 animatör = new J5c_49(); // Kurucuyu çaðýr...
        çerçeve.add (animatör, BorderLayout.CENTER);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
        animatör.animasyonuBaþlat(); 
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yazý fonu kullanýmýný kapýyoruz...
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_49 sýnýfý sonu...