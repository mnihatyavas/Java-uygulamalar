// J5j_3.java: SelectionDemo (SeçmeGösterisi) örneði.

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

import java.net.URL;

// Gerekli resim dosyasý: resim/yýldýzlýUzay.gif
public class J5j_3 {
    JLabel etiket;
    static String resimDosyasý = "resim/yýldýzlýUzay.gif";

    private void komponentleriEkle (Container içerikKabý, ImageIcon resimÝkonu) {
        içerikKabý.setLayout  (new BoxLayout (içerikKabý, BoxLayout.PAGE_AXIS));
        SeçmeliUzayAlaný uzayAlaný = new SeçmeliUzayAlaný (resimÝkonu, this); // Sýnýf kurucusunu çaðýrýr...
        içerikKabý.add (uzayAlaný);
        etiket = new JLabel ("Uzay boþluðunda sürükleyip bir alan seçin.");
        etiket.setLabelFor (uzayAlaný);
        içerikKabý.add (etiket);
        // Parçalarý sola (ancak boyutu resim ikonu boyutuna sabit) hizalayalým...
        uzayAlaný.setAlignmentX (Component.LEFT_ALIGNMENT);
        etiket.setAlignmentX (Component.LEFT_ALIGNMENT);
    } // komponentleriEkle(..) metodu sonu...

    private class SeçmeliUzayAlaný extends JLabel {
        Rectangle aktüelDikdörtgen = null;
        Rectangle çizilecekDikdörtgen = null;
        Rectangle öncekiÇizilenDikdörtgen = new Rectangle();
        J5j_3 sýnýfNesnesi;
    
        public SeçmeliUzayAlaný (ImageIcon resimÝkonu, J5j_3 sýnýfNesnesi) {// Kurucu...
            super (resimÝkonu);
            this.sýnýfNesnesi = sýnýfNesnesi;
            setOpaque (true);
            setMinimumSize (new Dimension (10,10)); // Uzayý pek büyükce kirletmeyelim...
            FareDinleyicim dinleyicim = new FareDinleyicim();
            addMouseListener (dinleyicim); // Fare sürüklemesine duyarlý...
            addMouseMotionListener (dinleyicim);
        } // SeçmeliUzayAlaný sýnýfý kurucusu sonu...

        private class FareDinleyicim extends MouseInputAdapter {
            public void mousePressed (MouseEvent olay) {
                int x = olay.getX();
                int y = olay.getY();
                aktüelDikdörtgen = new Rectangle (x, y, 0, 0);
                çizilebilirDikdörtgeniGüncelle (getWidth(), getHeight());
                repaint();
            } // mousePressed(..) hazýr metodu sonu...

            public void mouseDragged (MouseEvent olay) {boyutuGüncelle (olay);}
            public void mouseReleased (MouseEvent olay) {boyutuGüncelle (olay);}

            void boyutuGüncelle (MouseEvent olay) {
                int x = olay.getX();
                int y = olay.getY();
                aktüelDikdörtgen.setSize (x - aktüelDikdörtgen.x, y - aktüelDikdörtgen.y);
                çizilebilirDikdörtgeniGüncelle (getWidth(), getHeight());
                Rectangle toplamBoyanan = çizilecekDikdörtgen.union (öncekiÇizilenDikdörtgen);
                repaint (toplamBoyanan.x, toplamBoyanan.y, toplamBoyanan.width, toplamBoyanan.height);
            } // boyutuGüncelle(..) metodu sonu...
        } // FareDinleyicim sýnýfý sonu...

        private void çizilebilirDikdörtgeniGüncelle (int parçanýnGeniþliði, int parçanýnYüksekliði) {
            int x = aktüelDikdörtgen.x;
            int y = aktüelDikdörtgen.y;
            int en = aktüelDikdörtgen.width;
            int boy = aktüelDikdörtgen.height;

            if (en < 0) {
                en = 0 - en;
                x = x - en + 1; 
                if (x < 0) {
                    en += x; 
                    x = 0;
                } // iç-if kararý sonu...
            } // dýþ if kararý sonu...
            if (boy < 0) {
                boy = 0 - boy;
                y = y - boy + 1; 
                if (y < 0) {
                    boy += y; 
                    y = 0;
                } // iç-if kararý sonu...
            } // dýþ-if kararý sonu...

            // Seçili dikdörtgen boyutu uzay alanýný taþamaz...
            if ((x + en) > parçanýnGeniþliði) en = parçanýnGeniþliði - x;
            if ((y + boy) > parçanýnYüksekliði) boy = parçanýnYüksekliði - y;
            if (çizilecekDikdörtgen != null) {öncekiÇizilenDikdörtgen.setBounds (
                    çizilecekDikdörtgen.x, çizilecekDikdörtgen.y, 
                    çizilecekDikdörtgen.width, çizilecekDikdörtgen.height);
                çizilecekDikdörtgen.setBounds (x, y, en, boy);
            } else çizilecekDikdörtgen = new Rectangle (x, y, en, boy);
        } // çizilebilirDikdörtgeniGüncelle(..) metodu sonu...

        protected void paintComponent (Graphics g) {
            super.paintComponent (g);
            if (aktüelDikdörtgen != null) {
                g.setXORMode (Color.white);
                g.drawRect (çizilecekDikdörtgen.x, çizilecekDikdörtgen.y, çizilecekDikdörtgen.width - 1, çizilecekDikdörtgen.height - 1);
                sýnýfNesnesi.etiketiGüncelle (çizilecekDikdörtgen);
            } // if kararý sonu...
        } // paintComponent(..) hazýr metodu sonu...
    } // SeçmeliUzayAlaný sýnýfý sonu...

    public void etiketiGüncelle (Rectangle dikdörtgen) {
        int en = dikdörtgen.width;
        int boy = dikdörtgen.height;

        if (en == 0) en = 1;
        if (boy == 0) boy = 1;

        etiket.setText ("Dikdörtgenin ilk ve son konumlarý: ("
                + dikdörtgen.x + "," + dikdörtgen.y + ") ve ("
                + (dikdörtgen.x + en - 1) + "," + (dikdörtgen.y + boy - 1) + ").");
    } // etiketiGüncelle(..) metodu sonu...

    protected static ImageIcon resimÝkonunuYarat (String yol) {
        URL resimYureli = J5j_3.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Seçme Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5j_3 sýnýfNesnesi = new J5j_3(); // Namevcut varsayýlý kurucuyu çaðýrýr...
        sýnýfNesnesi.komponentleriEkle (çerçeve.getContentPane(), resimÝkonunuYarat (resimDosyasý));
        çerçeve.setLocation (200,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main(String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI(); }});
    } // main(..) metodu sonu...
} // J5j_3 sýnýfý sonu...