// J5c_28b.java: LayeredPaneDemo2 (Tabakal�PanoG�sterimi2) �rne�i.

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/* Gereken resim dosyas�:
 * resim/dukeWaveRed.gif.
*/
public class J5c_28b extends JPanel implements ActionListener, MouseMotionListener {
    private String[] tabakaDizgeleri = { "0:Sar�", "1:Eflatun", "2:Camg�be�i",   "3:K�rm�z�", "4:Ye�il",  "5:Mavi", "6:Turuncu", "7:Gri", "8:Siyah" };
    private Color[] tabakaRenkleri = { Color.yellow, Color.magenta, Color.cyan, Color.red, Color.green, Color.blue, Color.orange, Color.gray, Color.black };

    private JLayeredPane tabakal�Pano;
    private JLabel d�kEtiketi;
    private JCheckBox �st�nden;
    private JComboBox tabakaListesi;

    // Aksiyon komutlar�...
    private static String �ST�NDEN_KOMUTU = "�st�nden";
    private static String TABAKA_KOMUTU = "tabaka";

    public J5c_28b() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));

        // D�k ikonunu yarat�p kural�m...
        final ImageIcon ikon = resim�konunuYarat ("resim/dukeWaveRed.gif");

        // Ba�l�kl� tabakal� panoyu yarat�p kural�m...
        tabakal�Pano = new JLayeredPane();
        tabakal�Pano.setPreferredSize (new Dimension (300, 310));
        tabakal�Pano.setBorder (BorderFactory.createTitledBorder ("D�k'� y�r�tmek i�in (t�klamadan) fareyi ta��y�n"));
        tabakal�Pano.addMouseMotionListener (this);

        // Tabakal� panoya listedeki renkli etiketleri ekleyelim...
        tabakal�Pano.setLayout (new GridLayout (2, 4));
        for (int i = 0; i < tabakaDizgeleri.length; i++) {
            JLabel etiket = renkliEtiketiYarat (tabakaDizgeleri[i], tabakaRenkleri[i]);
            tabakal�Pano.add (etiket, new Integer (i));
        } // for d�ng�s� sonu...

        // (�konlu veya siyah kare) d�k etiketini yarat�p tabakal� panoya ekleyelim...
        d�kEtiketi = new JLabel (ikon);
        if (ikon == null) {System.err.println ("D�k ikonu bulunamad���ndan, yerine siyah kare kullan�lacak.");
            d�kEtiketi.setOpaque (true);
            d�kEtiketi.setBackground (Color.BLACK);
        } // if karar� sonu...

        tabakal�Pano.add (d�kEtiketi, new Integer (2), 0); // 2:Camg�be�i se�ili, ve 0:�st�nden �entikli...

        // Tabakal� panoyu ve olu�turulacak kontrol panelini i�erik panosuna ekleyelim...
        add (Box.createRigidArea (new Dimension (0, 10))); // �st tampon...
        add (kontrolPaneliniYarat());
        add (Box.createRigidArea (new Dimension (0, 10))); // Orta tampon...
        add(tabakal�Pano);
    } // J5c_28b() kurucusu sonu...

    protected static ImageIcon resim�konunuYarat (String yol) {
        java.net.URL resimYureli = J5c_28b.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas� bulunamad�!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...

    // Tabaka isimli ve renkli etiketi yaratal�m...
    private JLabel renkliEtiketiYarat (String tabakaAd�, Color tabakaRengi) {
        JLabel etiket = new JLabel (tabakaAd�);
        etiket.setVerticalAlignment (JLabel.TOP); // tabakaAd� �st-ortal� olsun...
        etiket.setHorizontalAlignment (JLabel.CENTER);
        etiket.setOpaque (true);
        etiket.setBackground (tabakaRengi);
        etiket.setForeground (Color.WHITE);
        etiket.setBorder (BorderFactory.createLineBorder (Color.black));
        etiket.setPreferredSize (new Dimension (140, 140));

        return etiket;
    } // renkliEtiketiYarat(..) metodu sonu...

    // Ba�l�kl� kontrol paneli aksiyon dinleyicisine duyarl� a��l�r ve �entik kutusunu i�erecek...
    private JPanel kontrolPaneliniYarat() {
        �st�nden = new JCheckBox ("Tabakan�n �st�nden ge�sin");
        �st�nden.setSelected (true);
        �st�nden.setActionCommand (�ST�NDEN_KOMUTU);
        �st�nden.addActionListener (this);

        tabakaListesi = new JComboBox (tabakaDizgeleri);
        tabakaListesi.setSelectedIndex (2); // Camg�be�i (2) tabakas�...
        tabakaListesi.setActionCommand (TABAKA_KOMUTU);
        tabakaListesi.addActionListener (this);

        JPanel kontrolPaneli = new JPanel();
        kontrolPaneli.add (tabakaListesi);
        kontrolPaneli.add (�st�nden);

        kontrolPaneli.setBorder (BorderFactory.createTitledBorder ("D�k'�n tabakas�n� ve �st�nden/alt�ndan'� se�in"));
        return kontrolPaneli;
    } // kontrolPaneliniYarat() metodu sonu...

    // Tabakal� pano i�inde d�k ('�n en ve boy ortas�ndan) fare okunu izlesin...
    public void mouseMoved (MouseEvent olay) {d�kEtiketi.setLocation (olay.getX()-d�kEtiketi.getWidth()/2, olay.getY()-d�kEtiketi.getHeight()/2);}
    public void mouseDragged (MouseEvent olay) {} // S�r�klemeye duyars�z olsun...

    // �entik kutusu ve a��l�r kutuya duyarl�l��� y�netelim...
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();
        if (�ST�NDEN_KOMUTU.equals (komut)) {
            if (�st�nden.isSelected()) tabakal�Pano.moveToFront (d�kEtiketi);
            else tabakal�Pano.moveToBack (d�kEtiketi);
        }else
            if (TABAKA_KOMUTU.equals (komut)) {
                int konum = �st�nden.isSelected() ? 0 : 1; // 0:�st�nden, 1:alt�ndan...
                tabakal�Pano.setLayer (d�kEtiketi, tabakaListesi.getSelectedIndex(), konum);
            } // i�-if ve d��-else karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Tabakal� Pano G�sterimi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_28b();
        yeni��erikPanosu.setOpaque (true);
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
} // J5c_28b s�n�f� sonu...