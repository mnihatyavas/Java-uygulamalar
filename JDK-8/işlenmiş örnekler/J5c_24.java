// J5c_24.java: IconDemoApp (�konG�sterimiUygulamas�) �rne�i.

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.RenderingHints;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.image.BufferedImage;

import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/* Gerekli dosyalar:
 * resim/sunw01.jpg <br>
 * resim/sunw02.jpg <br>
 * resim/sunw03.jpg <br>
 * resim/sunw04.jpg <br>
 * resim/sunw05.jpg <br>
 *
 * J5c_24x.java=MissingIcon.java
 */
public class J5c_24 extends JFrame {
    private JLabel foto�rafEtiketi = new JLabel();
    private JToolBar buton�ubu�u = new JToolBar();
    private String resimDizini = "resim/";

    private J5c_24x bo��kon�er�evesi = new J5c_24x();

    // Resimlerin tooltip/alet-ipucu k�sa izahl� altyaz�lar� listesi...
    private String[] resimAltyaz�lar� = { "Orijinal SUNW Logosu", "Saat Kulesi",
        "Saat Kulesi, uzaktan", "Misafirhane Kona��", "Sun Konferans Salonu"};

    // Y�klenecek resimlerin listesi...
    private String[] resimDosyas�Adlar� = { "sunw01.jpg", "sun02.jpg",
        "sunw03.jpg", "sunw04.jpg", "sunw05.jpg"};

    public static void main (String args[]) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                J5c_24 uygulama = new J5c_24();
                uygulama.setVisible (true);
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...

    public J5c_24() {// Kurucu...
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        setTitle ("�kon G�sterimi: Bir Resim Se�in");

        // �konik k���k resimlerin b�y�k ebatl� olarak g�sterilece�i etiket...
        foto�rafEtiketi.setVerticalTextPosition (JLabel.BOTTOM);
        foto�rafEtiketi.setHorizontalTextPosition (JLabel.CENTER);
        foto�rafEtiketi.setHorizontalAlignment (JLabel.CENTER);
        foto�rafEtiketi.setBorder (BorderFactory.createEmptyBorder (5, 5, 5, 5));

        // Buton �ubu�una eklenen 2 zamk komponenti, daha sonra bu ikisinin
        // aras�na eklenecek parmak-ucu resimleri ortalayacakt�r...
        buton�ubu�u.add (Box.createGlue());
        buton�ubu�u.add (Box.createGlue());
        buton�ubu�u.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );

        add (buton�ubu�u, BorderLayout.SOUTH);
        add (foto�rafEtiketi, BorderLayout.CENTER);

        setSize (400, 300); // Pencere ebat�...

        // Bu g�receli konumlama pencereyi ekranda ortalayarak a�acakt�r...
        setLocationRelativeTo (null);

        // Resimleri y�kleyecek olan SwingWorker'� bir altyap� siciminde ba�latal�m...
        resimleriY�kle.execute();
    } // J5c_24() kurucusu sonu...
    
    // �lk anda b�y�k-ebat resim �er�evesi bo� olaca��ndan, ilk parametre Void'dir...
    private SwingWorker<Void, ParmakucuHareketi> resimleriY�kle = new SwingWorker<Void, ParmakucuHareketi>() {

        // Resimlerin alet�ubu�unda k���k, se�ilenin de �stte b�y�k-ebat g�r�nt�lenmesini sa�lar...
        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 0; i < resimAltyaz�lar�.length; i++) {
                ImageIcon ikon;
                ikon = resim�konunuYarat (resimDizini + resimDosyas�Adlar�[i], resimAltyaz�lar�[i]);

                ParmakucuHareketi parmakucuHareketi;
                if (ikon != null) {
                    ImageIcon parmakucu�konu = new ImageIcon (parmakucuResmiAl (ikon.getImage(), 32, 32));
                    parmakucuHareketi = new ParmakucuHareketi (ikon, parmakucu�konu, resimAltyaz�lar�[i]);
                }else {// Resim dosyas� bulunamad�ysa parmakucu ikonu bo� g�r�nt�lensin...
                    parmakucuHareketi = new ParmakucuHareketi (bo��kon�er�evesi, bo��kon�er�evesi, resimAltyaz�lar�[i]);
                } // if-else karar� sonu...
                publish (parmakucuHareketi); // process(..) haz�r metodunu �a��r�r...
            } // for d�ng�s� sonu...

            // d�n�� tipi Void oldu�undan null d�nd�r�l�r...
            return null;
        } // doInBackground() haz�r metodu sonu...

        // publish(..) haz�r metodu process(..) haz�r metodunu �a��r�r...
        @Override
        protected void process (List<ParmakucuHareketi> hareketler) {
            for (ParmakucuHareketi parmakucuHareketi : hareketler) {
                JButton parmakucuButonu = new JButton (parmakucuHareketi);
                // Yeni ikonu alet-�ubu�unda son zamk'�n �n�ne koyal�m...
                buton�ubu�u.add (parmakucuButonu, buton�ubu�u.getComponentCount() - 1);
            } // for d�ng�s� sonu...
        } // process(..) haz�r metodu sonu...
    }; // private SwingWorker.. ifadesi sonu...
    
    protected ImageIcon resim�konunuYarat (String yol, String izah) {
        java.net.URL resimYureli = getClass().getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli, izah);
        else {System.err.println ("[" + yol + "] resim dosyas� bulunamad�!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...
    
    // Parametrik resmi belirtilen en-boy'a ebatland�r�r ve d�nd�r�r...
    private Image parmakucuResmiAl (Image gelenResim, int en, int boy) {
        BufferedImage ebatlananResim = new BufferedImage (en, boy, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = ebatlananResim.createGraphics();
        g2.setRenderingHint (RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage (gelenResim, 0, 0, en, boy, null);
        g2.dispose();
        return ebatlananResim;
    } // parmakucuResmiAl(..) metodu sonu...
    
    // Bu s�n�f kurucusuna aktar�lan resmi g�r�nt�ler...
    private class ParmakucuHareketi extends AbstractAction {
        private Icon resmiG�ster;
        
        // Parametreler: b�y�k-resim, k���k-resim, ve ikon-izah�...
        public ParmakucuHareketi (Icon b�y�kResim, Icon parmakucuResim, String izah) {// Kurucu...
            resmiG�ster = b�y�kResim;

            // K�sa izah, ikon butonunun tooltip ipucu olur...
            putValue (SHORT_DESCRIPTION, izah);

            // The LARGE_ICON_KEY, t�kland���nda actionPerformed(..) ile b�y���n� g�r�nt�ler...
            putValue (LARGE_ICON_KEY, parmakucuResim);
        } // ParmakucuHareketi(..) kurucusu sonu...

        // B�y�k ebat resmi g�r�nt�ler...
        public void actionPerformed (ActionEvent olay) {
            foto�rafEtiketi.setIcon (resmiG�ster);
            setTitle ("�kon G�sterimi: " + getValue (SHORT_DESCRIPTION).toString());
        } // actionPerformed(..) haz�r metodu sonu...
    } // ParmakucuHareketi s�n�f� sonu...
} // J5c_24 s�n�f� sonu...