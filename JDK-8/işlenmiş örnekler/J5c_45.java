// J5c_45.java: ScrollDemo2 (Kayd�ra�G�sterisi2) �rne�i.

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
    private Dimension grafikAlan�;
    private Vector<Rectangle> daireler; // �izilecek grafik konumlar� elemenlar�...
    private JPanel �izimPanosu;

    private final Color renkler[] = {Color.red, Color.blue, Color.green, Color.orange,Color.cyan, Color.magenta, Color.darkGray, Color.yellow, Color.gray, Color.white, Color.black, Color.pink};
    private final int renkSay�s� = renkler.length;

    public J5c_45() {// Kurucu...
        super (new BorderLayout());

        grafikAlan� = new Dimension (0,0);
        daireler = new Vector<Rectangle>();

        // Pencere �st�ne a��klamalar� koyal�m...
        JLabel solT�klama = new JLabel ("Yeni bir daire i�in SOL TIKLAYIN.");
        JLabel sa�T�klama = new JLabel ("�izim alan� temizli�i i�in SA� TIKLAYIN.");
        JPanel izahPaneli = new JPanel (new GridLayout (0,1));
        izahPaneli.setFocusable (true);
        izahPaneli.add (solT�klama);
        izahPaneli.add (sa�T�klama);
        izahPaneli.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) ));

        // Grafik alan�n� kural�m...
        �izimPanosu = new �izimPanosu();
        // �izimPanosu.setBackground (Color.white);
        �izimPanosu.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) ));
        �izimPanosu.addMouseListener (this); // Fare sol/sa� t�klamas�na duyarlayal�m...

        // Grafik alan�n� kayd�rma panosuyla sarmalayal�m...
        JScrollPane kayd�r�c� = new JScrollPane (�izimPanosu);
        kayd�r�c�.setPreferredSize (new Dimension (300,300));

        // 2 komponenti de penceremize serimleyelim...
        add (izahPaneli, BorderLayout.PAGE_START);
        add (kayd�r�c�, BorderLayout.CENTER);
    } // J5c_45() kurucusu sonu...

    // Vector daireler'de konumlar� listelenen noktalara daire(ler) �izer...
    public class �izimPanosu extends JPanel {
        protected void paintComponent (Graphics g) {
            super.paintComponent (g);

            Rectangle dikd�rtgen;
            for (int i = 0; i < daireler.size(); i++) {// Vector eleman say�s� kadar...
                dikd�rtgen = daireler.elementAt (i);
                g.setColor (renkler[(i % renkSay�s�)]); // Renk serisi bitince, ba�tan tekrarlan�r...
                g.fillOval (dikd�rtgen.x, dikd�rtgen.y, dikd�rtgen.width, dikd�rtgen.height);
            } // for d�ng�d� sonu...
        } // paintComponent(..) haz�r metodu sonu...
    } // �izimPanosu s�n�f� sonu...

    public void mouseReleased (MouseEvent olay) {
        final int E = 50; // En...
        final int B = 50; // Boy...
        boolean de�i�tiMi = false;

        if (javax.swing.SwingUtilities.isRightMouseButton (olay)) {
            // �izilen t�m grafik nesneleri temizlenecek...
            daireler.removeAllElements();
            grafikAlan�.width = 0;
            grafikAlan�.height = 0;
            de�i�tiMi = true;
        }else {// Sol t�klanan konuma bir daire �izilecek...
            int x = olay.getX() - E/2;
            int y = olay.getY() - B/2;
            if (x < 0) x = 0;
            if (y < 0) y = 0;
            Rectangle dikd�rtgen = new Rectangle (x, y, E, B);
            daireler.addElement (dikd�rtgen);
            �izimPanosu.scrollRectToVisible (dikd�rtgen);

            int geni�lik = (x + E + 2); // En ta�mas�nda, ta�ma kadar kayd�r...
            if (geni�lik > grafikAlan�.width) {grafikAlan�.width = geni�lik; de�i�tiMi=true;}

            int y�kseklik = (y + B + 2); // Boy ta�mas�nda, ta�ma kadar kayd�r...
            if (y�kseklik > grafikAlan�.height) {grafikAlan�.height = y�kseklik; de�i�tiMi=true;}
        } // else karar� sonu...

        if (de�i�tiMi) {
            // Temizlenmi�se kayd�rmas�z, ta�m��sa kayd�rmal� g�ncelle...
            �izimPanosu.setPreferredSize(grafikAlan�);
            �izimPanosu.revalidate();
        } // if karar� sonu...
        �izimPanosu.repaint(); //�izimPanosu'nun paintComponent(..)'ini �a��r�r...
    } // mouseReleased(..) haz�r metodu sonu...

    public void mouseClicked (MouseEvent olay){}
    public void mouseEntered (MouseEvent olay){}
    public void mouseExited (MouseEvent olay){}
    public void mousePressed (MouseEvent olay){}

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kayd�rma G�sterisi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_45();
        yeni��erikPanosu. setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_45 s�n�f� sonu...