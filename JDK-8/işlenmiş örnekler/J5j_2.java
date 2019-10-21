// J5j_2.java: IconDisplayer (ÝkonGöstericisi) örneði.

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.BorderFactory;

import java.net.URL;

// Gereken resim dosyasý: resim/uzaygemisi.gif
public class J5j_2 extends JComponent {
    private Icon ikon;
    private int tercihiResimSayýsý = 5;
    private int aralýk = 10; // Resimler arasý boþluk...
    private Rectangle netÝkon = new Rectangle();
    private Rectangle silikÝkon = new Rectangle();

    public J5j_2 (Icon ikon, int tercihiResimSayýsý, int aralýk) {// Kurucu...
        this.ikon = ikon;
        if (tercihiResimSayýsý > 0) this.tercihiResimSayýsý = tercihiResimSayýsý;
        if (aralýk >= 0) this.aralýk = aralýk;
        if (this.aralýk > 0) setBorder (BorderFactory.createEmptyBorder (// 5 px boþluk...
                this.aralýk, this.aralýk, this.aralýk, this.aralýk)); // Resmin etrafýndaki boþluk...
    } // 5j_2(..) kurucusu sonu...

    // Aþaðýdakiler JComponent hazýr metodlarýdýr...
    public Dimension getPreferredSize() {
        if (ikon != null) {Insets tamponlar = getInsets();
            return new Dimension (ikon.getIconWidth() * tercihiResimSayýsý
                    + aralýk * (tercihiResimSayýsý - 1) + tamponlar.left + tamponlar.right,
                    ikon.getIconHeight()  + tamponlar.top + tamponlar.bottom);
        } else return new Dimension (100, 100);
    } // getPreferredSize() hazýr metodu sonu...

    public Dimension getMinimumSize() {
        if (ikon != null) {Insets tamponlar = getInsets();
            return new Dimension (ikon.getIconWidth() + tamponlar.left + tamponlar.right,
                    ikon.getIconHeight()  + tamponlar.top + tamponlar.bottom);
        } else return new Dimension (0,0);
    } // getMinimumSize() hazýr metodu sonu...

    protected void paintComponent (Graphics g) {
        if (isOpaque()) {
            g.setColor (getBackground());
            g.fillRect (0, 0, getWidth(), getHeight());
        } // if kararý sonu...

        if (ikon != null) {
            // Ýkonu 5 kez, ilki net, sonrakiler silik, çizelim...
            Insets tamponlar = getInsets();
            int ikonGeniþliði = ikon.getIconWidth();
            int ikonX = getWidth() - tamponlar.right - ikonGeniþliði;
            int ikonY = tamponlar.top;
            boolean silikMi = false;

            Graphics2D g2d = (Graphics2D)g.create();

            g.getClipBounds (silikÝkon);
            while (ikonX >= tamponlar.left) {
                netÝkon.setBounds (ikonX, ikonY,  ikonGeniþliði,  ikon.getIconHeight());
                if (netÝkon.intersects (silikÝkon)) ikon.paintIcon (this, g2d, ikonX, ikonY);
                ikonX -= (ikonGeniþliði + aralýk);
                if (!silikMi) { 
                    g2d.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER, 0.1f));
                    silikMi = true;
                } // if kararý sonu...
            } // while döngüsü sonu...

            g2d.dispose(); // Temizlik...
        } // dýþ-if kararý sonu...
    } // paintComponent(..) hazýr metodu sonu...

    protected static ImageIcon resimIkonuYarat (String yol) {
        URL resimYureli = J5j_2.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyasýný bulamadým."); return null;}
    } // resimIkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Ýkon Göstericisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        ImageIcon uzayGemisi = resimIkonuYarat ("resim/uzaygemisi.gif");
        J5j_2 sýnýfNesnesi = new J5j_2 (uzayGemisi,0, 5); // Kurucuyu çaðýrýr...
        çerçeve.getContentPane().add (sýnýfNesnesi, BorderLayout.CENTER);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});}
} // J5j_2 sýnýfý sonu...