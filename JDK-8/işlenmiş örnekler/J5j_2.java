// J5j_2.java: IconDisplayer (�konG�stericisi) �rne�i.

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

// Gereken resim dosyas�: resim/uzaygemisi.gif
public class J5j_2 extends JComponent {
    private Icon ikon;
    private int tercihiResimSay�s� = 5;
    private int aral�k = 10; // Resimler aras� bo�luk...
    private Rectangle net�kon = new Rectangle();
    private Rectangle silik�kon = new Rectangle();

    public J5j_2 (Icon ikon, int tercihiResimSay�s�, int aral�k) {// Kurucu...
        this.ikon = ikon;
        if (tercihiResimSay�s� > 0) this.tercihiResimSay�s� = tercihiResimSay�s�;
        if (aral�k >= 0) this.aral�k = aral�k;
        if (this.aral�k > 0) setBorder (BorderFactory.createEmptyBorder (// 5 px bo�luk...
                this.aral�k, this.aral�k, this.aral�k, this.aral�k)); // Resmin etraf�ndaki bo�luk...
    } // 5j_2(..) kurucusu sonu...

    // A�a��dakiler JComponent haz�r metodlar�d�r...
    public Dimension getPreferredSize() {
        if (ikon != null) {Insets tamponlar = getInsets();
            return new Dimension (ikon.getIconWidth() * tercihiResimSay�s�
                    + aral�k * (tercihiResimSay�s� - 1) + tamponlar.left + tamponlar.right,
                    ikon.getIconHeight()  + tamponlar.top + tamponlar.bottom);
        } else return new Dimension (100, 100);
    } // getPreferredSize() haz�r metodu sonu...

    public Dimension getMinimumSize() {
        if (ikon != null) {Insets tamponlar = getInsets();
            return new Dimension (ikon.getIconWidth() + tamponlar.left + tamponlar.right,
                    ikon.getIconHeight()  + tamponlar.top + tamponlar.bottom);
        } else return new Dimension (0,0);
    } // getMinimumSize() haz�r metodu sonu...

    protected void paintComponent (Graphics g) {
        if (isOpaque()) {
            g.setColor (getBackground());
            g.fillRect (0, 0, getWidth(), getHeight());
        } // if karar� sonu...

        if (ikon != null) {
            // �konu 5 kez, ilki net, sonrakiler silik, �izelim...
            Insets tamponlar = getInsets();
            int ikonGeni�li�i = ikon.getIconWidth();
            int ikonX = getWidth() - tamponlar.right - ikonGeni�li�i;
            int ikonY = tamponlar.top;
            boolean silikMi = false;

            Graphics2D g2d = (Graphics2D)g.create();

            g.getClipBounds (silik�kon);
            while (ikonX >= tamponlar.left) {
                net�kon.setBounds (ikonX, ikonY,  ikonGeni�li�i,  ikon.getIconHeight());
                if (net�kon.intersects (silik�kon)) ikon.paintIcon (this, g2d, ikonX, ikonY);
                ikonX -= (ikonGeni�li�i + aral�k);
                if (!silikMi) { 
                    g2d.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER, 0.1f));
                    silikMi = true;
                } // if karar� sonu...
            } // while d�ng�s� sonu...

            g2d.dispose(); // Temizlik...
        } // d��-if karar� sonu...
    } // paintComponent(..) haz�r metodu sonu...

    protected static ImageIcon resimIkonuYarat (String yol) {
        URL resimYureli = J5j_2.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyas�n� bulamad�m."); return null;}
    } // resimIkonuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("�kon G�stericisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        ImageIcon uzayGemisi = resimIkonuYarat ("resim/uzaygemisi.gif");
        J5j_2 s�n�fNesnesi = new J5j_2 (uzayGemisi,0, 5); // Kurucuyu �a��r�r...
        �er�eve.getContentPane().add (s�n�fNesnesi, BorderLayout.CENTER);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});}
} // J5j_2 s�n�f� sonu...