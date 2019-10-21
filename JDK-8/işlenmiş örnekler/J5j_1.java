// J5j_1.java: CoordinatesDemo (KordinatlarGösterisi) örneði.

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

 // Kýrmýzý çerçeveli, 20x20 ýzgaralý komponent üzerinde fare kareketlerine ve 7x7 týklamasýna duyarlý...
public class J5j_1 {
    private JLabel mesajEtiketi;
    private Point týklamaNoktasý, imleçNoktasý;

    private void komponentleriEkle (Container duyarlýKab) {
        duyarlýKab.setLayout (new BoxLayout (duyarlýKab, BoxLayout.PAGE_AXIS));

        KordinatAlaný kordinatAlaný = new KordinatAlaný (this); // Sýnýf kurucusunu çaðýrýr...
        duyarlýKab.add (kordinatAlaný);

        mesajEtiketi = new JLabel();
        imleçMesajýnýSil();
        duyarlýKab.add (mesajEtiketi);

        // Parçalar sol kenara hizalý olacak...
        kordinatAlaný.setAlignmentX (Component.LEFT_ALIGNMENT);
        mesajEtiketi.setAlignmentX (Component.LEFT_ALIGNMENT);
    } // komponentleriEkle(..) metodu sonu...

    public static class KordinatAlaný extends JComponent implements MouseInputListener {
        Point nokta = null;
        J5j_1 kordinatcý;
        Dimension tercihiBoyut = new Dimension (400, 75);
        Color ýzgaraRengi;

        public KordinatAlaný (J5j_1 kordinatcý) {// Kurucu...
            this.kordinatcý = kordinatcý;
            setBorder (BorderFactory.createMatteBorder (
                    1,5,5,1, // üst-sað-alt-sol çerçeve kalýnlýklarý...
                    Color.RED)); // Çerçeve rengi kýrmýzý
            addMouseListener (this); // Fare (sol-sað) týklamasýna duyarlý...
            addMouseMotionListener (this); // Fare hareketine (sürüklemeye deðil) duyarlý...
            setBackground (Color.CYAN);
            setOpaque (true);
        } // KordinatAlaný(..) kurucusu sonu...

        // Kalan metodlar extends ve implements sýnýflarýnýn hazýr metodlarýdýr...
        public Dimension getPreferredSize() {return tercihiBoyut;}

        protected void paintComponent (Graphics g) {
            if (isOpaque()) {
                g.setColor (getBackground());
                g.fillRect (0, 0, getWidth(), getHeight());
            } // if kararý sonu...

            // 20x20 ebatlý ýzgaralarý çizer...
            g.setColor (Color.GRAY);
            ýzgarayýÇiz (g, 20);

            // Sol/sað fare týklama nokatýna 7x7'lik siyah (varsayýlý) kare çizer...
            if (nokta != null) {
                g.setColor (getForeground());
                g.fillRect (nokta.x - 3, nokta.y - 3, 7, 7);
            } // if kararý sonu...
        } // paintComponent(..) hazýr metodu sonu...

        private void ýzgarayýÇiz (Graphics g, int ýzgaraEbatý) {
            Insets kenarlýk = getInsets(); // Kenarlarýn (1,5,5,1) aralýðýný düþecek...
            int ilkX = kenarlýk.left;
            int ilkY = kenarlýk.top;
            int sonX = getWidth() - kenarlýk.right;
            int sonY = getHeight() - kenarlýk.bottom;

            // Önce dikey ýzgaralar çizilecek...
            int x = ilkX;
            while (x < sonX) {
                g.drawLine (x, ilkY, x, sonY);
                x += ýzgaraEbatý; // 20'þer artýrýr...
            } // while döngüsü sonu...

            // Sonra da yatay ýzgara çizgileri çizilecek...
            int y = ilkY;
            while (y < sonY) {
                g.drawLine (ilkX, y, sonX, y);
                y += ýzgaraEbatý; // 20'þer artýrýr...
            } // while döngüsü sonu...
        } // ýzgarayýÇiz(..) kullanýcý metodu sonu...

        public void mouseClicked (MouseEvent olay) { 
            int x = olay.getX();
            int y = olay.getY();
            if (nokta == null) nokta = new Point (x, y); // Altta önceki nokta yoksa...
            else {// Gerekir miydi?..
                nokta.x = x;
                nokta.y = y;
            } // else kararý sonu...
            kordinatcý.týklamaNoktasýnýGüncelle (nokta);
            repaint();
        } // mouseClicked(..) hazýr metodu sonu...

        public void mouseMoved (MouseEvent olay) {kordinatcý.imleçKonumunuGüncelle (olay.getX(), olay.getY());}
        public void mouseExited (MouseEvent olay) {kordinatcý.imleçMesajýnýSil();}

        // Aþaðýdaki hazýr fare olaylarýna aldýrma...
        public void mouseReleased (MouseEvent olay){}
        public void mouseEntered (MouseEvent olay){}
        public void mousePressed (MouseEvent olay){}
        public void mouseDragged (MouseEvent olay){}
    } // KordinatAlaný sýnýfý sonu...

    public void týklamaNoktasýnýGüncelle (Point nokta) {
        týklamaNoktasý = nokta;
        etiketiGüncelle();
    } // týklamaNoktasýnýGüncelle(..) metodu sonu...

    protected void etiketiGüncelle() {
        String etiketMetni = ""; // imleçNoktasý=null'sa bu geçerli...
        if ((týklamaNoktasý == null) && (imleçNoktasý == null))
            etiketMetni = "Ýmleçi çerçeveli alan içinde (sürüklemeden) gezdirin veya (sol/sað) týklayýn.";
        else {
            if (týklamaNoktasý != null) etiketMetni += "Son týklanma konumu: (" + týklamaNoktasý.x + "," + týklamaNoktasý.y + "). ";
            if (imleçNoktasý != null)
                etiketMetni += "Aktüel imleç konumu: (" + imleçNoktasý.x + "," + imleçNoktasý.y + "). ";
        } // else kararý sonu...
        mesajEtiketi.setText (etiketMetni);
    } // etiketiGüncelle() metodu sonu...

    public void imleçKonumunuGüncelle (int x, int y) {
        if (x < 0 || y < 0) {
            imleçNoktasý = null;
            etiketiGüncelle();
            return;
        } // if kararý sonu...
        if (imleçNoktasý == null) imleçNoktasý = new Point();
        imleçNoktasý.x = x;
        imleçNoktasý.y = y;
        etiketiGüncelle();
    } // imleçKonumunuGüncelle(..) metodu sonu...

    public void imleçMesajýnýSil() {
        imleçNoktasý = null;
        etiketiGüncelle();        
    } // imleçMesajýnýSil() metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kordinatlar Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5j_1 kordinatcý = new J5j_1(); // Namevcut varsayýlý kurucuyu çaðýrýr...
        kordinatcý.komponentleriEkle (çerçeve.getContentPane());
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5j_1 sýnýfý sonu...