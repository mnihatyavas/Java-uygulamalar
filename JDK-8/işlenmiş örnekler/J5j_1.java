// J5j_1.java: CoordinatesDemo (KordinatlarG�sterisi) �rne�i.

import java.awt.Container;
import java.awt.Component;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.event.MouseInputListener;

 // K�rm�z� �er�eveli, 20x20 �zgaral� komponent �zerinde fare kareketlerine ve 7x7 t�klamas�na duyarl�...
public class J5j_1 {
    private JLabel mesajEtiketi;
    private Point t�klamaNoktas�, imle�Noktas�;

    private void komponentleriEkle (Container duyarl�Kab) {
        duyarl�Kab.setLayout (new BoxLayout (duyarl�Kab, BoxLayout.PAGE_AXIS));

        KordinatAlan� kordinatAlan� = new KordinatAlan� (this); // S�n�f kurucusunu �a��r�r...
        duyarl�Kab.add (kordinatAlan�);

        mesajEtiketi = new JLabel();
        imle�Mesaj�n�Sil();
        duyarl�Kab.add (mesajEtiketi);

        // Par�alar sol kenara hizal� olacak...
        kordinatAlan�.setAlignmentX (Component.LEFT_ALIGNMENT);
        mesajEtiketi.setAlignmentX (Component.LEFT_ALIGNMENT);
    } // komponentleriEkle(..) metodu sonu...

    public static class KordinatAlan� extends JComponent implements MouseInputListener {
        Point nokta = null;
        J5j_1 kordinatc�;
        Dimension tercihiBoyut = new Dimension (400, 75);
        Color �zgaraRengi;

        public KordinatAlan� (J5j_1 kordinatc�) {// Kurucu...
            this.kordinatc� = kordinatc�;
            setBorder (BorderFactory.createMatteBorder (
                    1,5,5,1, // �st-sa�-alt-sol �er�eve kal�nl�klar�...
                    Color.RED)); // �er�eve rengi k�rm�z�
            addMouseListener (this); // Fare (sol-sa�) t�klamas�na duyarl�...
            addMouseMotionListener (this); // Fare hareketine (s�r�klemeye de�il) duyarl�...
            setBackground (Color.CYAN);
            setOpaque (true);
        } // KordinatAlan�(..) kurucusu sonu...

        // Kalan metodlar extends ve implements s�n�flar�n�n haz�r metodlar�d�r...
        public Dimension getPreferredSize() {return tercihiBoyut;}

        protected void paintComponent (Graphics g) {
            if (isOpaque()) {
                g.setColor (getBackground());
                g.fillRect (0, 0, getWidth(), getHeight());
            } // if karar� sonu...

            // 20x20 ebatl� �zgaralar� �izer...
            g.setColor (Color.GRAY);
            �zgaray��iz (g, 20);

            // Sol/sa� fare t�klama nokat�na 7x7'lik siyah (varsay�l�) kare �izer...
            if (nokta != null) {
                g.setColor (getForeground());
                g.fillRect (nokta.x - 3, nokta.y - 3, 7, 7);
            } // if karar� sonu...
        } // paintComponent(..) haz�r metodu sonu...

        private void �zgaray��iz (Graphics g, int �zgaraEbat�) {
            Insets kenarl�k = getInsets(); // Kenarlar�n (1,5,5,1) aral���n� d��ecek...
            int ilkX = kenarl�k.left;
            int ilkY = kenarl�k.top;
            int sonX = getWidth() - kenarl�k.right;
            int sonY = getHeight() - kenarl�k.bottom;

            // �nce dikey �zgaralar �izilecek...
            int x = ilkX;
            while (x < sonX) {
                g.drawLine (x, ilkY, x, sonY);
                x += �zgaraEbat�; // 20'�er art�r�r...
            } // while d�ng�s� sonu...

            // Sonra da yatay �zgara �izgileri �izilecek...
            int y = ilkY;
            while (y < sonY) {
                g.drawLine (ilkX, y, sonX, y);
                y += �zgaraEbat�; // 20'�er art�r�r...
            } // while d�ng�s� sonu...
        } // �zgaray��iz(..) kullan�c� metodu sonu...

        public void mouseClicked (MouseEvent olay) { 
            int x = olay.getX();
            int y = olay.getY();
            if (nokta == null) nokta = new Point (x, y); // Altta �nceki nokta yoksa...
            else {// Gerekir miydi?..
                nokta.x = x;
                nokta.y = y;
            } // else karar� sonu...
            kordinatc�.t�klamaNoktas�n�G�ncelle (nokta);
            repaint();
        } // mouseClicked(..) haz�r metodu sonu...

        public void mouseMoved (MouseEvent olay) {kordinatc�.imle�KonumunuG�ncelle (olay.getX(), olay.getY());}
        public void mouseExited (MouseEvent olay) {kordinatc�.imle�Mesaj�n�Sil();}

        // A�a��daki haz�r fare olaylar�na ald�rma...
        public void mouseReleased (MouseEvent olay){}
        public void mouseEntered (MouseEvent olay){}
        public void mousePressed (MouseEvent olay){}
        public void mouseDragged (MouseEvent olay){}
    } // KordinatAlan� s�n�f� sonu...

    public void t�klamaNoktas�n�G�ncelle (Point nokta) {
        t�klamaNoktas� = nokta;
        etiketiG�ncelle();
    } // t�klamaNoktas�n�G�ncelle(..) metodu sonu...

    protected void etiketiG�ncelle() {
        String etiketMetni = ""; // imle�Noktas�=null'sa bu ge�erli...
        if ((t�klamaNoktas� == null) && (imle�Noktas� == null))
            etiketMetni = "�mle�i �er�eveli alan i�inde (s�r�klemeden) gezdirin veya (sol/sa�) t�klay�n.";
        else {
            if (t�klamaNoktas� != null) etiketMetni += "Son t�klanma konumu: (" + t�klamaNoktas�.x + "," + t�klamaNoktas�.y + "). ";
            if (imle�Noktas� != null)
                etiketMetni += "Akt�el imle� konumu: (" + imle�Noktas�.x + "," + imle�Noktas�.y + "). ";
        } // else karar� sonu...
        mesajEtiketi.setText (etiketMetni);
    } // etiketiG�ncelle() metodu sonu...

    public void imle�KonumunuG�ncelle (int x, int y) {
        if (x < 0 || y < 0) {
            imle�Noktas� = null;
            etiketiG�ncelle();
            return;
        } // if karar� sonu...
        if (imle�Noktas� == null) imle�Noktas� = new Point();
        imle�Noktas�.x = x;
        imle�Noktas�.y = y;
        etiketiG�ncelle();
    } // imle�KonumunuG�ncelle(..) metodu sonu...

    public void imle�Mesaj�n�Sil() {
        imle�Noktas� = null;
        etiketiG�ncelle();        
    } // imle�Mesaj�n�Sil() metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kordinatlar G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5j_1 kordinatc� = new J5j_1(); // Namevcut varsay�l� kurucuyu �a��r�r...
        kordinatc�.komponentleriEkle (�er�eve.getContentPane());
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5j_1 s�n�f� sonu...