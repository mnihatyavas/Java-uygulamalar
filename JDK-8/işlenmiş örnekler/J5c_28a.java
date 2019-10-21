// J5c_28a.java: LayeredPaneDemo (Tabakal�PanoG�sterimi) �rne�i.

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
//import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/* Gereken resim dosyas�:
 * resim/dukeWaveRed.gif. 
 */
public class J5c_28a extends JPanel implements ActionListener, MouseMotionListener {
    private String[] tabakaAdlar� = { "Sar� (0)", "Eflatun (1)", "Camg�be�i (2)",   "K�rm�z� (3)", "Ye�il (4)" };
    private Color[] tabakaRenkleri = { Color.yellow, Color.magenta, Color.cyan,   Color.red, Color.green };

    private JLayeredPane tabakal�Pano;
    private JLabel d�kEtiketi;
    private JCheckBox �entikKutusu;
    private JComboBox a��l�rKutu;

    // Aksiyon komutlar�...
    private static String �ST�NDEN_KOMUTU = "�st�nden";
    private static String TABAKA_KOMUTU = "tabaka";

    // D�k'�n sol_TM ayak ba�parma��n� fare okucuna ba�lama ayarlar�...
    private static final int X_KONUMU = 40; // Resim ebat�: 64x64; sol ayakucu konumu: 40x57...
    private static final int Y_KONUMU = 57;

    public J5c_28a() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));

        // D�k ikonunu yarat�p y�kleyelim...
        final ImageIcon ikon = resim�konuYarat ("resim/dukeWaveRed.gif");

        // Tabakal� ba�l�kl� panoyu yarat�p kural�m...
        tabakal�Pano = new JLayeredPane();
        tabakal�Pano.setPreferredSize (new Dimension (300, 310));
        tabakal�Pano.setBorder (BorderFactory.createTitledBorder ("D�k'� y�r�tmek i�in fareyi (t�klamadan) kullan�n"));
        tabakal�Pano.addMouseMotionListener (this);

        // Eklenecek ilk tabakan�n (tabakal�Pano'daki) konumunu belirtelim...
        Point ilkKonum = new Point (10, 20);

        // Bir sonraki tabakan�n (x,y) sol-a�a�� kayd�r�lacak mesafesi...
        int kayd�r = 35;

        // Mutlak kayd�rmal� konumland�rmal� 5 renkli tabakay� serelim...
        for (int i = 0; i < tabakaAdlar�.length; i++) {
            JLabel tabakaEtiketi = renkliEtiketYarat (tabakaAdlar�[i],  tabakaRenkleri[i], ilkKonum);
            tabakal�Pano.add (tabakaEtiketi, new Integer (i));
            ilkKonum.x += kayd�r;
            ilkKonum.y += kayd�r;
        } // for d�ng�s� sonu...

        // D�k'� veya namevcutsa 30x30 ebatl� siyah kareyi yarat�p tabakal� etiketin alt�na koyal�m...
        d�kEtiketi = new JLabel (ikon);
        if (ikon != null) d�kEtiketi.setBounds (15, 225, ikon.getIconWidth(), ikon.getIconHeight());
        else {System.err.println ("D�k ikonu bulunamad�, yerine 30x30 ebatl� siyah kare kullan�lacak.");
            d�kEtiketi.setBounds (15, 225, 30, 30);
            d�kEtiketi.setOpaque (true);
            d�kEtiketi.setBackground (Color.BLACK);
        } // else karar� sonu...

        tabakal�Pano.add (d�kEtiketi, new Integer (2), 0); // �lk anda Camg�be�i (2) �zerinde olacak..

        // Kontrol panelini ve tabakal�Pano'yu bu Box/kutu serimli JPanel'e ekleyelim...
        add (Box.createRigidArea (new Dimension (0, 10)));
        add (kontrolPaneliYarat());
        add (Box.createRigidArea (new Dimension (0, 10)));
        add (tabakal�Pano);
    } // J5c_28a() kurucusu sonu...

    protected static ImageIcon resim�konuYarat (String yol) {
        java.net.URL resimYureli = J5c_28a.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!");return null;}
    } // resim�konuYarat(..) metodu sonu...

    // Tabaka etiketi yarat�p renkle ve konumla kural�m...
    private JLabel renkliEtiketYarat (String dizge, Color renk, Point ilkKonum) {
        JLabel tabakaEtiketi = new JLabel (dizge);
        tabakaEtiketi.setVerticalAlignment (JLabel.TOP);
        tabakaEtiketi.setHorizontalAlignment (JLabel.CENTER);
        tabakaEtiketi.setOpaque (true);
        tabakaEtiketi.setBackground (renk);
        tabakaEtiketi.setForeground (Color.black);
        tabakaEtiketi.setBorder (BorderFactory.createLineBorder (Color.black));
        tabakaEtiketi.setBounds (ilkKonum.x, ilkKonum.y, 140, 140);
        return tabakaEtiketi;
    } // renkliEtiketYarat(..) metodu sonu...

    // �entik ve a��l�r kutulu kontrol panelini ba�l�kl� yarat�p d�nd�relim...
    private JPanel kontrolPaneliYarat() {
        �entikKutusu = new JCheckBox ("Se�ili tabakan�n �st�nden");
        �entikKutusu.setSelected (true);
        �entikKutusu.setActionCommand (�ST�NDEN_KOMUTU);
        �entikKutusu.addActionListener (this);

        a��l�rKutu = new JComboBox (tabakaAdlar�);
        a��l�rKutu.setSelectedIndex (2); // Camg�be�i (2) tabakas�...
        a��l�rKutu.setActionCommand (TABAKA_KOMUTU);
        a��l�rKutu.addActionListener (this);

        JPanel kontrolPaneli = new JPanel();
        kontrolPaneli.add (a��l�rKutu);
        kontrolPaneli.add (�entikKutusu);
        kontrolPaneli.setBorder (BorderFactory.createTitledBorder ("D�k'�n ge�ece�i tabakay� ve �st/alt se�in"));

        return kontrolPaneli;
    } // kontrolPaneliYarat() metodu sonu...

    // D�k'�n tabakal�Pano i�inde fareyi takip etmesini sa�layal�m...
    public void mouseMoved (MouseEvent olay) {d�kEtiketi.setLocation (olay.getX()-X_KONUMU, olay.getY()-Y_KONUMU);}
    public void mouseDragged (MouseEvent olay) {} // T�klayarak s�r�klenmeye duyars�z b�rakal�m...

    // �st �entikliyse D�k se�ili tabakan�n �st�nden, �entiksizse alt�ndan ge�er...
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (�ST�NDEN_KOMUTU.equals (komut)) {
            if (�entikKutusu.isSelected()) tabakal�Pano.moveToFront (d�kEtiketi);
            else tabakal�Pano.moveToBack (d�kEtiketi);

        }else
            if (TABAKA_KOMUTU.equals (komut)) {
                int konum = �entikKutusu.isSelected() ? 0 : 1; // 0:�st�nden, 1:alt�ndan...
                tabakal�Pano.setLayer (d�kEtiketi, a��l�rKutu.getSelectedIndex(), konum);
            } // i�-if ve d��-else karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Tabakal� Pano G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_28a();
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        //�er�eve.setBounds (Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-250,0,0);
        //�er�eve.setLocationRelativeTo (null);
        //�er�eve.setBounds (550,100,0,0);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_28a s�n�f� sonu...