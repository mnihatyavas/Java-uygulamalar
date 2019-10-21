// J5c_82.java: TumbleItem (TaklaBirimi) aplet �rne�i.

/*  <applet code="J5c_82.class" width="150" height="100" alt="Takla Birimi">
        <param name="Resim" value="resim/takla">
        <param name="Kayma" value="0">
        <param name="AzamiGeni�lik" value="130">
    </applet>
 * �al��t�rmak i�in: >appletviewer J5c_82.java
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

// Gereken resim dosyalar�: resim/takla/T1..T17.gif
public class J5c_82 extends JApplet implements ActionListener {
    int resimEndeksi = -1; // �imdiki resim endeksi: resimler[resimEndeksi]
    String resimDizini; // Resimlerin y�klendi�i dizin: resim/takla
    Timer zamanlay�c�; // Resimlerin (ilk duraksama dahil) animasyon s�resi...
    int duraksama; // Yeniden canland�rmalar �ncesi duraksama s�resi...
    int kayma; // x<0 ise canland�rma her d�ng�de sa�dan sola x kadar; x>0 ise soldan sa�a x kadar kayar, tekrarlar; x=0 ise kaymaz...
    int akt�elKonum; // Akt�el resmin konumlanaca�� kayma'lar toplam�...
    int h�z; // (2 resim aras�) canland�rma h�z�...
    int resimSay�s�; // Oynat�lmak i�in y�klenecek toplam resim say�s�...
    int geni�lik; // Aplet'in i�erik pnosu geni�li�i...
    Canland�r�c� animat�r; // Aplet'in i�erik panosu...
    ImageIcon resimDizisi[]; // Resimler...
    int azamiGeni�lik; // En geni� resmin geni�li�i...
    JLabel durumEtiketi;

    // Aplet taray�c�ya y�klendi�inde ilk �al��an (main gibi) haz�r metoddur...
    public void init() {
        apletParametreleriniY�kle();

        // Aplet GUI'sini yaratacak metodu raporlamal� (try-catch y�netimli) sicimde (Thread veya Runnable) �al��t�r...
        try {SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {yaratGUI();}
            }); // Swi.. ifadesi sonu...
        }catch (Exception ist) {System.err.println ("HATA: yaratGUI() metodu ba�ar�yla tamamlanamad�!");}

        // Canland�rma olaylar�n� s�recek olan zamanlay�c�y� kural�m...
        zamanlay�c� = new Timer (h�z, this);
        zamanlay�c�.setInitialDelay (duraksama);
        zamanlay�c�.start(); // Tekrar init()'i ba�lat�r...

        // Resimleri arkaplanda y�klemeyi ba�lat�r...
        i�g�ren.execute();
    } // init() haz�r metodu sonu...

    protected void apletParametreleriniY�kle() {
        // Aplet parametreleri i�in �ncelikle (HTML) dosyas�na bak...
        String parametre = getParameter ("Resim");
        resimDizini = (parametre != null) ? parametre : "resim/takla";
        parametre = getParameter ("Duraksama");
        duraksama = (parametre != null) ? Integer.valueOf (parametre).intValue() : 3000; // 3sn.
        parametre = getParameter ("Kayma");
        kayma = (parametre != null) ? Integer.valueOf (parametre).intValue() : 0;
        parametre = getParameter ("H�z");
        h�z = (parametre != null) ? (1000 / Integer.valueOf (parametre).intValue()) : 100;
        parametre = getParameter ("ResimSay�s�");
        resimSay�s� = (parametre != null) ? Integer.valueOf (parametre).intValue() : 16;
        parametre = getParameter ("AzamiGeni�lik");
        azamiGeni�lik = (parametre != null) ? Integer.valueOf (parametre).intValue() : 0;
    } // apletParametreleriniY�kle() metodu sonu...

    private void yaratGUI() {
        // Kayma negatifse sa�a yana��k, pozitifse sola yana��k canlans�n...
        geni�lik = getSize().width;
        if (kayma < 0) akt�elKonum = geni�lik - azamiGeni�lik;

        // Canland�rmay� ger�ekle�tiren paintComponent/Icon �al��t�r�l�r...
        animat�r = new Canland�r�c�();
        animat�r.setOpaque (true);
        animat�r.setBackground (Color.blue);
        setContentPane (animat�r);

        durumEtiketi = new JLabel ("Resimler y�kleniyor...", JLabel.CENTER);
        animat�r.add (durumEtiketi, BorderLayout.CENTER);
    } // yaratGUI() metodu sonu...

    // Ger�ek canland�rmay� yapan grafik birimidir...
    public class Canland�r�c� extends JPanel {
        public Canland�r�c�() {super (new BorderLayout());} // Kurucu...

        protected void paintComponent (Graphics g) {
            super.paintComponent (g);

            if (i�g�ren.isDone() && (resimEndeksi > -1) && (resimEndeksi < resimSay�s�))
                if (resimDizisi != null && resimDizisi[resimEndeksi] != null)
                    resimDizisi[resimEndeksi].paintIcon (this, g, akt�elKonum, 0);
        } // paintComponent(..) haz�r metodu sonu...
    } // Canland�r�c� s�n�f� sonu...

    // Resimleri y�kleyen arkaplan g�revi...
    SwingWorker i�g�ren = new SwingWorker<ImageIcon[], Void>() {
        @Override
        public ImageIcon[] doInBackground() {
            final ImageIcon[] i�Resimler = new ImageIcon[resimSay�s�];
            for (int i = 0; i < resimSay�s�; i++) i�Resimler[i] = s�radakiResmiY�kle (i + 1);
            return i�Resimler;
        } // doInBackground() haz�r metodu sonu...

        @Override
        public void done() {
            // Animat�r panosunu temizleyelim...
            animat�r.removeAll();
            resimEndeksi = -1;
            try {resimDizisi = get();}
            catch (InterruptedException ald�rma) {}
            catch (ExecutionException ist) {
                String ni�in = null;
                Throwable sebep = ist.getCause();
                if (sebep != null) ni�in = sebep.getMessage();
                else ni�in = ist.getMessage();
                System.err.println ("Resim dosyas�n� y�kleme hatas�: " + ni�in);
            } // catch blo�u sonu...
        } // done() haz�r metodu sonu...
    }; // Swi.. ifadesi sonu...

    protected ImageIcon s�radakiResmiY�kle (int resimNo) {
        String resminYolu = resimDizini + "/T" + resimNo + ".gif";
        int AZAM�_RES�M_EBATI = 2400;
        int say� = 0;
        BufferedInputStream resmiOku = new BufferedInputStream (
                this.getClass().getResourceAsStream (resminYolu));
        if (resmiOku != null) {
            byte tampon[] = new byte[AZAM�_RES�M_EBATI];
            try {say� = resmiOku.read (tampon);
                resmiOku.close();
            }catch (IOException ist) {System.err.println ("[" + resminYolu + "] dosyas�ndan resmi okuyam�yorum!"); return null;}
            if (say� <= 0) {System.err.println ("[" + resminYolu + "] dosyas� bo�!"); return null;}
            return new ImageIcon (Toolkit.getDefaultToolkit().createImage (tampon));
        }else {System.err.println ("[" + resminYolu + "] dosyas�n� bulamad�m!");return null;}
    } // s�radakiResmiY�kle(..) metodu sonu...

    // Timer/zamanlay�c�'ya duyarl�d�r...
    public void actionPerformed (ActionEvent olay) {
        // Hala resim y�kleniysa animasyon ba�lamamal�d�r...
        if (!i�g�ren.isDone()) return;

        resimEndeksi++;
        if (resimEndeksi >= resimSay�s�) {
            resimEndeksi = 0;
            akt�elKonum += kayma;
            if (akt�elKonum < 0) akt�elKonum = geni�lik - azamiGeni�lik;
            else if ((akt�elKonum + azamiGeni�lik) > geni�lik) akt�elKonum = 0;
        } // if karar� sonu...
        animat�r.repaint(); // paintComponent(..) �al���r...

        if (resimEndeksi == resimSay�s� - 1) zamanlay�c�.restart();
    } // actionPerformed(..) haz�r metodu sonu...

    public void start() {if (i�g�ren.isDone() && (resimSay�s� > 1)) zamanlay�c�.restart();}
    public void stop() {zamanlay�c�.stop();}
} // J5c_82 s�n�f� sonu...