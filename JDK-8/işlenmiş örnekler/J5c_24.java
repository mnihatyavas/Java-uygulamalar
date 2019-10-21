// J5c_24.java: IconDemoApp (ÝkonGösterimiUygulamasý) örneði.

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
    private JLabel fotoðrafEtiketi = new JLabel();
    private JToolBar butonÇubuðu = new JToolBar();
    private String resimDizini = "resim/";

    private J5c_24x boþÝkonÇerçevesi = new J5c_24x();

    // Resimlerin tooltip/alet-ipucu kýsa izahlý altyazýlarý listesi...
    private String[] resimAltyazýlarý = { "Orijinal SUNW Logosu", "Saat Kulesi",
        "Saat Kulesi, uzaktan", "Misafirhane Konaðý", "Sun Konferans Salonu"};

    // Yüklenecek resimlerin listesi...
    private String[] resimDosyasýAdlarý = { "sunw01.jpg", "sun02.jpg",
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
        setTitle ("Ýkon Gösterimi: Bir Resim Seçin");

        // Ýkonik küçük resimlerin büyük ebatlý olarak gösterileceði etiket...
        fotoðrafEtiketi.setVerticalTextPosition (JLabel.BOTTOM);
        fotoðrafEtiketi.setHorizontalTextPosition (JLabel.CENTER);
        fotoðrafEtiketi.setHorizontalAlignment (JLabel.CENTER);
        fotoðrafEtiketi.setBorder (BorderFactory.createEmptyBorder (5, 5, 5, 5));

        // Buton çubuðuna eklenen 2 zamk komponenti, daha sonra bu ikisinin
        // arasýna eklenecek parmak-ucu resimleri ortalayacaktýr...
        butonÇubuðu.add (Box.createGlue());
        butonÇubuðu.add (Box.createGlue());
        butonÇubuðu.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );

        add (butonÇubuðu, BorderLayout.SOUTH);
        add (fotoðrafEtiketi, BorderLayout.CENTER);

        setSize (400, 300); // Pencere ebatý...

        // Bu göreceli konumlama pencereyi ekranda ortalayarak açacaktýr...
        setLocationRelativeTo (null);

        // Resimleri yükleyecek olan SwingWorker'ý bir altyapý siciminde baþlatalým...
        resimleriYükle.execute();
    } // J5c_24() kurucusu sonu...
    
    // Ýlk anda büyük-ebat resim çerçevesi boþ olacaðýndan, ilk parametre Void'dir...
    private SwingWorker<Void, ParmakucuHareketi> resimleriYükle = new SwingWorker<Void, ParmakucuHareketi>() {

        // Resimlerin aletçubuðunda küçük, seçilenin de üstte büyük-ebat görüntülenmesini saðlar...
        @Override
        protected Void doInBackground() throws Exception {
            for (int i = 0; i < resimAltyazýlarý.length; i++) {
                ImageIcon ikon;
                ikon = resimÝkonunuYarat (resimDizini + resimDosyasýAdlarý[i], resimAltyazýlarý[i]);

                ParmakucuHareketi parmakucuHareketi;
                if (ikon != null) {
                    ImageIcon parmakucuÝkonu = new ImageIcon (parmakucuResmiAl (ikon.getImage(), 32, 32));
                    parmakucuHareketi = new ParmakucuHareketi (ikon, parmakucuÝkonu, resimAltyazýlarý[i]);
                }else {// Resim dosyasý bulunamadýysa parmakucu ikonu boþ görüntülensin...
                    parmakucuHareketi = new ParmakucuHareketi (boþÝkonÇerçevesi, boþÝkonÇerçevesi, resimAltyazýlarý[i]);
                } // if-else kararý sonu...
                publish (parmakucuHareketi); // process(..) hazýr metodunu çaðýrýr...
            } // for döngüsü sonu...

            // dönüþ tipi Void olduðundan null döndürülür...
            return null;
        } // doInBackground() hazýr metodu sonu...

        // publish(..) hazýr metodu process(..) hazýr metodunu çaðýrýr...
        @Override
        protected void process (List<ParmakucuHareketi> hareketler) {
            for (ParmakucuHareketi parmakucuHareketi : hareketler) {
                JButton parmakucuButonu = new JButton (parmakucuHareketi);
                // Yeni ikonu alet-çubuðunda son zamk'ýn önüne koyalým...
                butonÇubuðu.add (parmakucuButonu, butonÇubuðu.getComponentCount() - 1);
            } // for döngüsü sonu...
        } // process(..) hazýr metodu sonu...
    }; // private SwingWorker.. ifadesi sonu...
    
    protected ImageIcon resimÝkonunuYarat (String yol, String izah) {
        java.net.URL resimYureli = getClass().getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli, izah);
        else {System.err.println ("[" + yol + "] resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...
    
    // Parametrik resmi belirtilen en-boy'a ebatlandýrýr ve döndürür...
    private Image parmakucuResmiAl (Image gelenResim, int en, int boy) {
        BufferedImage ebatlananResim = new BufferedImage (en, boy, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = ebatlananResim.createGraphics();
        g2.setRenderingHint (RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage (gelenResim, 0, 0, en, boy, null);
        g2.dispose();
        return ebatlananResim;
    } // parmakucuResmiAl(..) metodu sonu...
    
    // Bu sýnýf kurucusuna aktarýlan resmi görüntüler...
    private class ParmakucuHareketi extends AbstractAction {
        private Icon resmiGöster;
        
        // Parametreler: büyük-resim, küçük-resim, ve ikon-izahý...
        public ParmakucuHareketi (Icon büyükResim, Icon parmakucuResim, String izah) {// Kurucu...
            resmiGöster = büyükResim;

            // Kýsa izah, ikon butonunun tooltip ipucu olur...
            putValue (SHORT_DESCRIPTION, izah);

            // The LARGE_ICON_KEY, týklandýðýnda actionPerformed(..) ile büyüðünü görüntüler...
            putValue (LARGE_ICON_KEY, parmakucuResim);
        } // ParmakucuHareketi(..) kurucusu sonu...

        // Büyük ebat resmi görüntüler...
        public void actionPerformed (ActionEvent olay) {
            fotoðrafEtiketi.setIcon (resmiGöster);
            setTitle ("Ýkon Gösterimi: " + getValue (SHORT_DESCRIPTION).toString());
        } // actionPerformed(..) hazýr metodu sonu...
    } // ParmakucuHareketi sýnýfý sonu...
} // J5c_24 sýnýfý sonu...