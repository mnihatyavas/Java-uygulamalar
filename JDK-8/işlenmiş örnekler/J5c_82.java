// J5c_82.java: TumbleItem (TaklaBirimi) aplet örneði.

/*  <applet code="J5c_82.class" width="150" height="100" alt="Takla Birimi">
        <param name="Resim" value="resim/takla">
        <param name="Kayma" value="0">
        <param name="AzamiGeniþlik" value="130">
    </applet>
 * Çalýþtýrmak için: >appletviewer J5c_82.java
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import java.util.concurrent.ExecutionException;

import java.io.BufferedInputStream;
import java.io.IOException;

// Gereken resim dosyalarý: resim/takla/T1..T17.gif
public class J5c_82 extends JApplet implements ActionListener {
    int resimEndeksi = -1; // Þimdiki resim endeksi: resimler[resimEndeksi]
    String resimDizini; // Resimlerin yüklendiði dizin: resim/takla
    Timer zamanlayýcý; // Resimlerin (ilk duraksama dahil) animasyon süresi...
    int duraksama; // Yeniden canlandýrmalar öncesi duraksama süresi...
    int kayma; // x<0 ise canlandýrma her döngüde saðdan sola x kadar; x>0 ise soldan saða x kadar kayar, tekrarlar; x=0 ise kaymaz...
    int aktüelKonum; // Aktüel resmin konumlanacaðý kayma'lar toplamý...
    int hýz; // (2 resim arasý) canlandýrma hýzý...
    int resimSayýsý; // Oynatýlmak için yüklenecek toplam resim sayýsý...
    int geniþlik; // Aplet'in içerik pnosu geniþliði...
    Canlandýrýcý animatör; // Aplet'in içerik panosu...
    ImageIcon resimDizisi[]; // Resimler...
    int azamiGeniþlik; // En geniþ resmin geniþliði...
    JLabel durumEtiketi;

    // Aplet tarayýcýya yüklendiðinde ilk çalýþan (main gibi) hazýr metoddur...
    public void init() {
        apletParametreleriniYükle();

        // Aplet GUI'sini yaratacak metodu raporlamalý (try-catch yönetimli) sicimde (Thread veya Runnable) çalýþtýr...
        try {SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {yaratGUI();}
            }); // Swi.. ifadesi sonu...
        }catch (Exception ist) {System.err.println ("HATA: yaratGUI() metodu baþarýyla tamamlanamadý!");}

        // Canlandýrma olaylarýný sürecek olan zamanlayýcýyý kuralým...
        zamanlayýcý = new Timer (hýz, this);
        zamanlayýcý.setInitialDelay (duraksama);
        zamanlayýcý.start(); // Tekrar init()'i baþlatýr...

        // Resimleri arkaplanda yüklemeyi baþlatýr...
        iþgören.execute();
    } // init() hazýr metodu sonu...

    protected void apletParametreleriniYükle() {
        // Aplet parametreleri için öncelikle (HTML) dosyasýna bak...
        String parametre = getParameter ("Resim");
        resimDizini = (parametre != null) ? parametre : "resim/takla";
        parametre = getParameter ("Duraksama");
        duraksama = (parametre != null) ? Integer.valueOf (parametre).intValue() : 3000; // 3sn.
        parametre = getParameter ("Kayma");
        kayma = (parametre != null) ? Integer.valueOf (parametre).intValue() : 0;
        parametre = getParameter ("Hýz");
        hýz = (parametre != null) ? (1000 / Integer.valueOf (parametre).intValue()) : 100;
        parametre = getParameter ("ResimSayýsý");
        resimSayýsý = (parametre != null) ? Integer.valueOf (parametre).intValue() : 16;
        parametre = getParameter ("AzamiGeniþlik");
        azamiGeniþlik = (parametre != null) ? Integer.valueOf (parametre).intValue() : 0;
    } // apletParametreleriniYükle() metodu sonu...

    private void yaratGUI() {
        // Kayma negatifse saða yanaþýk, pozitifse sola yanaþýk canlansýn...
        geniþlik = getSize().width;
        if (kayma < 0) aktüelKonum = geniþlik - azamiGeniþlik;

        // Canlandýrmayý gerçekleþtiren paintComponent/Icon çalýþtýrýlýr...
        animatör = new Canlandýrýcý();
        animatör.setOpaque (true);
        animatör.setBackground (Color.blue);
        setContentPane (animatör);

        durumEtiketi = new JLabel ("Resimler yükleniyor...", JLabel.CENTER);
        animatör.add (durumEtiketi, BorderLayout.CENTER);
    } // yaratGUI() metodu sonu...

    // Gerçek canlandýrmayý yapan grafik birimidir...
    public class Canlandýrýcý extends JPanel {
        public Canlandýrýcý() {super (new BorderLayout());} // Kurucu...

        protected void paintComponent (Graphics g) {
            super.paintComponent (g);

            if (iþgören.isDone() && (resimEndeksi > -1) && (resimEndeksi < resimSayýsý))
                if (resimDizisi != null && resimDizisi[resimEndeksi] != null)
                    resimDizisi[resimEndeksi].paintIcon (this, g, aktüelKonum, 0);
        } // paintComponent(..) hazýr metodu sonu...
    } // Canlandýrýcý sýnýfý sonu...

    // Resimleri yükleyen arkaplan görevi...
    SwingWorker iþgören = new SwingWorker<ImageIcon[], Void>() {
        @Override
        public ImageIcon[] doInBackground() {
            final ImageIcon[] içResimler = new ImageIcon[resimSayýsý];
            for (int i = 0; i < resimSayýsý; i++) içResimler[i] = sýradakiResmiYükle (i + 1);
            return içResimler;
        } // doInBackground() hazýr metodu sonu...

        @Override
        public void done() {
            // Animatör panosunu temizleyelim...
            animatör.removeAll();
            resimEndeksi = -1;
            try {resimDizisi = get();}
            catch (InterruptedException aldýrma) {}
            catch (ExecutionException ist) {
                String niçin = null;
                Throwable sebep = ist.getCause();
                if (sebep != null) niçin = sebep.getMessage();
                else niçin = ist.getMessage();
                System.err.println ("Resim dosyasýný yükleme hatasý: " + niçin);
            } // catch bloðu sonu...
        } // done() hazýr metodu sonu...
    }; // Swi.. ifadesi sonu...

    protected ImageIcon sýradakiResmiYükle (int resimNo) {
        String resminYolu = resimDizini + "/T" + resimNo + ".gif";
        int AZAMÝ_RESÝM_EBATI = 2400;
        int sayý = 0;
        BufferedInputStream resmiOku = new BufferedInputStream (
                this.getClass().getResourceAsStream (resminYolu));
        if (resmiOku != null) {
            byte tampon[] = new byte[AZAMÝ_RESÝM_EBATI];
            try {sayý = resmiOku.read (tampon);
                resmiOku.close();
            }catch (IOException ist) {System.err.println ("[" + resminYolu + "] dosyasýndan resmi okuyamýyorum!"); return null;}
            if (sayý <= 0) {System.err.println ("[" + resminYolu + "] dosyasý boþ!"); return null;}
            return new ImageIcon (Toolkit.getDefaultToolkit().createImage (tampon));
        }else {System.err.println ("[" + resminYolu + "] dosyasýný bulamadým!");return null;}
    } // sýradakiResmiYükle(..) metodu sonu...

    // Timer/zamanlayýcý'ya duyarlýdýr...
    public void actionPerformed (ActionEvent olay) {
        // Hala resim yükleniysa animasyon baþlamamalýdýr...
        if (!iþgören.isDone()) return;

        resimEndeksi++;
        if (resimEndeksi >= resimSayýsý) {
            resimEndeksi = 0;
            aktüelKonum += kayma;
            if (aktüelKonum < 0) aktüelKonum = geniþlik - azamiGeniþlik;
            else if ((aktüelKonum + azamiGeniþlik) > geniþlik) aktüelKonum = 0;
        } // if kararý sonu...
        animatör.repaint(); // paintComponent(..) çalýþýr...

        if (resimEndeksi == resimSayýsý - 1) zamanlayýcý.restart();
    } // actionPerformed(..) hazýr metodu sonu...

    public void start() {if (iþgören.isDone() && (resimSayýsý > 1)) zamanlayýcý.restart();}
    public void stop() {zamanlayýcý.stop();}
} // J5c_82 sýnýfý sonu...