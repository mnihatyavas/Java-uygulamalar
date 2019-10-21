// J5c_45.java: ScrollDemo2 (KaydýraçGösterisi2) örneði.

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import javax.swing.event.MouseInputAdapter;

import java.util.Vector;

public class J5c_45 extends JPanel implements MouseListener {
    private Dimension grafikAlaný;
    private Vector<Rectangle> daireler; // Çizilecek grafik konumlarý elemenlarý...
    private JPanel çizimPanosu;

    private final Color renkler[] = {Color.red, Color.blue, Color.green, Color.orange,Color.cyan, Color.magenta, Color.darkGray, Color.yellow, Color.gray, Color.white, Color.black, Color.pink};
    private final int renkSayýsý = renkler.length;

    public J5c_45() {// Kurucu...
        super (new BorderLayout());

        grafikAlaný = new Dimension (0,0);
        daireler = new Vector<Rectangle>();

        // Pencere üstüne açýklamalarý koyalým...
        JLabel solTýklama = new JLabel ("Yeni bir daire için SOL TIKLAYIN.");
        JLabel saðTýklama = new JLabel ("Çizim alaný temizliði için SAÐ TIKLAYIN.");
        JPanel izahPaneli = new JPanel (new GridLayout (0,1));
        izahPaneli.setFocusable (true);
        izahPaneli.add (solTýklama);
        izahPaneli.add (saðTýklama);
        izahPaneli.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) ));

        // Grafik alanýný kuralým...
        çizimPanosu = new ÇizimPanosu();
        // çizimPanosu.setBackground (Color.white);
        çizimPanosu.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) ));
        çizimPanosu.addMouseListener (this); // Fare sol/sað týklamasýna duyarlayalým...

        // Grafik alanýný kaydýrma panosuyla sarmalayalým...
        JScrollPane kaydýrýcý = new JScrollPane (çizimPanosu);
        kaydýrýcý.setPreferredSize (new Dimension (300,300));

        // 2 komponenti de penceremize serimleyelim...
        add (izahPaneli, BorderLayout.PAGE_START);
        add (kaydýrýcý, BorderLayout.CENTER);
    } // J5c_45() kurucusu sonu...

    // Vector daireler'de konumlarý listelenen noktalara daire(ler) çizer...
    public class ÇizimPanosu extends JPanel {
        protected void paintComponent (Graphics g) {
            super.paintComponent (g);

            Rectangle dikdörtgen;
            for (int i = 0; i < daireler.size(); i++) {// Vector eleman sayýsý kadar...
                dikdörtgen = daireler.elementAt (i);
                g.setColor (renkler[(i % renkSayýsý)]); // Renk serisi bitince, baþtan tekrarlanýr...
                g.fillOval (dikdörtgen.x, dikdörtgen.y, dikdörtgen.width, dikdörtgen.height);
            } // for döngüdü sonu...
        } // paintComponent(..) hazýr metodu sonu...
    } // ÇizimPanosu sýnýfý sonu...

    public void mouseReleased (MouseEvent olay) {
        final int E = 50; // En...
        final int B = 50; // Boy...
        boolean deðiþtiMi = false;

        if (javax.swing.SwingUtilities.isRightMouseButton (olay)) {
            // Çizilen tüm grafik nesneleri temizlenecek...
            daireler.removeAllElements();
            grafikAlaný.width = 0;
            grafikAlaný.height = 0;
            deðiþtiMi = true;
        }else {// Sol týklanan konuma bir daire çizilecek...
            int x = olay.getX() - E/2;
            int y = olay.getY() - B/2;
            if (x < 0) x = 0;
            if (y < 0) y = 0;
            Rectangle dikdörtgen = new Rectangle (x, y, E, B);
            daireler.addElement (dikdörtgen);
            çizimPanosu.scrollRectToVisible (dikdörtgen);

            int geniþlik = (x + E + 2); // En taþmasýnda, taþma kadar kaydýr...
            if (geniþlik > grafikAlaný.width) {grafikAlaný.width = geniþlik; deðiþtiMi=true;}

            int yükseklik = (y + B + 2); // Boy taþmasýnda, taþma kadar kaydýr...
            if (yükseklik > grafikAlaný.height) {grafikAlaný.height = yükseklik; deðiþtiMi=true;}
        } // else kararý sonu...

        if (deðiþtiMi) {
            // Temizlenmiþse kaydýrmasýz, taþmýþsa kaydýrmalý güncelle...
            çizimPanosu.setPreferredSize(grafikAlaný);
            çizimPanosu.revalidate();
        } // if kararý sonu...
        çizimPanosu.repaint(); //ÇizimPanosu'nun paintComponent(..)'ini çaðýrýr...
    } // mouseReleased(..) hazýr metodu sonu...

    public void mouseClicked (MouseEvent olay){}
    public void mouseEntered (MouseEvent olay){}
    public void mouseExited (MouseEvent olay){}
    public void mousePressed (MouseEvent olay){}

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kaydýrma Gösterisi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_45();
        yeniÝçerikPanosu. setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_45 sýnýfý sonu...