// J5c_22.java: GlassPaneDemo (CamPanoG�sterimi) �rne�i.

import java.awt.Container;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.FlowLayout;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComponent;
import javax.swing.AbstractButton;
import javax.swing.SwingUtilities;

import javax.swing.event.MouseInputAdapter;

public class J5c_22 {
    static private CamPanom camPanom;

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Cam Pano G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Komponentleri yarat�p ekleyelim...
        JCheckBox �entikKutusu = new JCheckBox ("Cam pano \"�effaf\"");
        /* �entiklendi�inde men� �ubu�u ve i�erik panosunu �effaf camla kapar
         * ve onlar� g�r�nseler de etkisiz k�lar, t�kland���nda sadece k�rm�z� bir
         * nokta belirir, ta ki �entiksizlene dek...*/
        �entikKutusu.setSelected (false);
        
        // 2 butonu ve �entik kutusunu ekleyece�imiz i�erik panosunu kural�m...
        Container i�erikPanosu = �er�eve.getContentPane();
        i�erikPanosu.setLayout (new FlowLayout());
        i�erikPanosu.add (�entikKutusu);
        i�erikPanosu.add (new JButton("Buton-1"));
        i�erikPanosu.add (new JButton("Buton-2"));

        // ��erik panosu �st�ne men� �ubu�unu kural�m...
        JMenuBar men��ubu�u = new JMenuBar();
        JMenu men� = new JMenu ("Men�");
        men�.add (new JMenuItem ("A�"));
        men�.add (new JMenuItem ("Sakla"));
        men�.add (new JMenuItem ("��k"));
        men��ubu�u.add (men�);
        �er�eve.setJMenuBar (men��ubu�u);

        // Cam panoyu kural�m. Men� �ubu�unu ve i�erik panosunu �effaf kapatacakt�r.
        // Sadece �entik kutusu dinleyiciye duyarl� olacakt�r...
        camPanom = new CamPanom (�entikKutusu, men��ubu�u, �er�eve.getContentPane());
        �entikKutusu.addItemListener (camPanom);
        �er�eve.setGlassPane (camPanom);

        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_22 s�n�f� sonu...

// Cam panomuz mevcutken (�entikli) t�klay�nca k�rm�z� nokta �izebilmeliyiz...
class CamPanom extends JComponent implements ItemListener {
    Point nokta;

    public CamPanom (AbstractButton soyutButon, JMenuBar men��ubu�u, Container i�erikPanosu) {
        �KDinleyici dinleyici = new �KDinleyici (soyutButon, men��ubu�u, this, i�erikPanosu);
        addMouseListener (dinleyici);
        addMouseMotionListener (dinleyici);
    } // CamPanom(..) kurucusu sonu...

    // �entikli kutunun t�klanmas�na duyarl� k�lal�m...
    public void itemStateChanged (ItemEvent olay) {setVisible (olay.getStateChange() == ItemEvent.SELECTED);}
    public void setPoint (Point nokta) {this.nokta = nokta;}

    protected void paintComponent (Graphics g) {
        if (nokta != null) {
            g.setColor (Color.red);
            g.fillOval (nokta.x - 10, nokta.y - 10, 20, 20);
        } // if karar� sonu...
    } // paintComponent(..) haz�r metodu sonu...
} // CamPanom s�n�f� sonu...

// �K/�entik kutusunu ilgilendiren t�m fare olaylar�n� dinler ve raporlar...
class �KDinleyici extends MouseInputAdapter {
    Toolkit aletkutusu;
    Component �entikKutusu;
    JMenuBar men��ubu�u;
    CamPanom camPano;
    Container i�erikPanosu;

    public �KDinleyici (Component �entikKutusu, JMenuBar men��ubu�u, CamPanom camPano, Container i�erikPanosu) {
        aletkutusu = Toolkit.getDefaultToolkit();
        this.�entikKutusu = �entikKutusu;
        this.men��ubu�u = men��ubu�u;
        this.camPano = camPano;
        this.i�erikPanosu = i�erikPanosu;
    } // �KDinleyici(..) kurucusu sonu...

    public void mouseMoved (MouseEvent olay) {fareOlay�n�Raporla (olay, false);}
    public void mouseDragged (MouseEvent olay) {fareOlay�n�Raporla (olay, false);}
    public void mouseClicked (MouseEvent olay) {fareOlay�n�Raporla (olay, false);}
    public void mouseEntered (MouseEvent olay) {fareOlay�n�Raporla (olay, false);}
    public void mouseExited (MouseEvent olay) {fareOlay�n�Raporla (olay, false);}
    public void mousePressed (MouseEvent olay) {fareOlay�n�Raporla (olay, false);}
    public void mouseReleased (MouseEvent olay) {fareOlay�n�Raporla (olay, true);}

    private void fareOlay�n�Raporla (MouseEvent olay, boolean repaint) {
        Point camPanoNoktas� = olay.getPoint();
        Container i�erik = i�erikPanosu;
        Point i�erikNoktas� = SwingUtilities.convertPoint (camPano, camPanoNoktas�, i�erikPanosu);
        if (i�erikNoktas�.y < 0) { // ��erik panosunda de�ilsek...
            if ((i�erikNoktas�.y + men��ubu�u.getHeight()) >= 0) { 
                // Fare olay� men� �ubu�u �zerinde demektir...
                // �stedi�in y�netimi buraya kodla...
            }else { 
                // Fare olay� d��ardad�r demektir... Ona g�re y�net....
            }
        }else {
            // Fare i�erik panosunda demektir...
            // Kesin hangi komponent �zerinde oldu�unu saptayal�m...
            Component komponent = SwingUtilities.getDeepestComponentAt (i�erik, i�erikNoktas�.x, i�erikNoktas�.y);
            if ((komponent != null) && (komponent.equals (�entikKutusu))) {
                // Olaylar� �entik kutusu �zerine y�nlendir...
                Point komponentNoktas� = SwingUtilities.convertPoint (camPano, camPanoNoktas�, komponent);
                komponent.dispatchEvent (new MouseEvent (komponent, olay.getID(), olay.getWhen(), olay.getModifiers(), komponentNoktas�.x, komponentNoktas�.y, olay.getClickCount(), olay.isPopupTrigger()));
            } // i�-if karar� sonu...
        } // d�� else karar� sonu...

        // Gerekiyorsa cam panoyo g�ncelleyelim...
        if (repaint) {
            camPano.setPoint (camPanoNoktas�);
            camPano.repaint();
        } // if karar� sonu...
    } // fareOlay�n�Raporla(..) metodu sonu...
} // �KDinleyici s�n�f� sonu...