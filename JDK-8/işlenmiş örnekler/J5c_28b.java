// J5c_28b.java: LayeredPaneDemo2 (TabakalýPanoGösterimi2) örneði.

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

/* Gereken resim dosyasý:
 * resim/dukeWaveRed.gif.
*/
public class J5c_28b extends JPanel implements ActionListener, MouseMotionListener {
    private String[] tabakaDizgeleri = { "0:Sarý", "1:Eflatun", "2:Camgöbeði",   "3:Kýrmýzý", "4:Yeþil",  "5:Mavi", "6:Turuncu", "7:Gri", "8:Siyah" };
    private Color[] tabakaRenkleri = { Color.yellow, Color.magenta, Color.cyan, Color.red, Color.green, Color.blue, Color.orange, Color.gray, Color.black };

    private JLayeredPane tabakalýPano;
    private JLabel dükEtiketi;
    private JCheckBox üstünden;
    private JComboBox tabakaListesi;

    // Aksiyon komutlarý...
    private static String ÜSTÜNDEN_KOMUTU = "üstünden";
    private static String TABAKA_KOMUTU = "tabaka";

    public J5c_28b() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));

        // Dük ikonunu yaratýp kuralým...
        final ImageIcon ikon = resimÝkonunuYarat ("resim/dukeWaveRed.gif");

        // Baþlýklý tabakalý panoyu yaratýp kuralým...
        tabakalýPano = new JLayeredPane();
        tabakalýPano.setPreferredSize (new Dimension (300, 310));
        tabakalýPano.setBorder (BorderFactory.createTitledBorder ("Dük'ü yürütmek için (týklamadan) fareyi taþýyýn"));
        tabakalýPano.addMouseMotionListener (this);

        // Tabakalý panoya listedeki renkli etiketleri ekleyelim...
        tabakalýPano.setLayout (new GridLayout (2, 4));
        for (int i = 0; i < tabakaDizgeleri.length; i++) {
            JLabel etiket = renkliEtiketiYarat (tabakaDizgeleri[i], tabakaRenkleri[i]);
            tabakalýPano.add (etiket, new Integer (i));
        } // for döngüsü sonu...

        // (Ýkonlu veya siyah kare) dük etiketini yaratýp tabakalý panoya ekleyelim...
        dükEtiketi = new JLabel (ikon);
        if (ikon == null) {System.err.println ("Dük ikonu bulunamadýðýndan, yerine siyah kare kullanýlacak.");
            dükEtiketi.setOpaque (true);
            dükEtiketi.setBackground (Color.BLACK);
        } // if kararý sonu...

        tabakalýPano.add (dükEtiketi, new Integer (2), 0); // 2:Camgöbeði seçili, ve 0:üstünden çentikli...

        // Tabakalý panoyu ve oluþturulacak kontrol panelini içerik panosuna ekleyelim...
        add (Box.createRigidArea (new Dimension (0, 10))); // Üst tampon...
        add (kontrolPaneliniYarat());
        add (Box.createRigidArea (new Dimension (0, 10))); // Orta tampon...
        add(tabakalýPano);
    } // J5c_28b() kurucusu sonu...

    protected static ImageIcon resimÝkonunuYarat (String yol) {
        java.net.URL resimYureli = J5c_28b.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...

    // Tabaka isimli ve renkli etiketi yaratalým...
    private JLabel renkliEtiketiYarat (String tabakaAdý, Color tabakaRengi) {
        JLabel etiket = new JLabel (tabakaAdý);
        etiket.setVerticalAlignment (JLabel.TOP); // tabakaAdý üst-ortalý olsun...
        etiket.setHorizontalAlignment (JLabel.CENTER);
        etiket.setOpaque (true);
        etiket.setBackground (tabakaRengi);
        etiket.setForeground (Color.WHITE);
        etiket.setBorder (BorderFactory.createLineBorder (Color.black));
        etiket.setPreferredSize (new Dimension (140, 140));

        return etiket;
    } // renkliEtiketiYarat(..) metodu sonu...

    // Baþlýklý kontrol paneli aksiyon dinleyicisine duyarlý açýlýr ve çentik kutusunu içerecek...
    private JPanel kontrolPaneliniYarat() {
        üstünden = new JCheckBox ("Tabakanýn üstünden geçsin");
        üstünden.setSelected (true);
        üstünden.setActionCommand (ÜSTÜNDEN_KOMUTU);
        üstünden.addActionListener (this);

        tabakaListesi = new JComboBox (tabakaDizgeleri);
        tabakaListesi.setSelectedIndex (2); // Camgöbeði (2) tabakasý...
        tabakaListesi.setActionCommand (TABAKA_KOMUTU);
        tabakaListesi.addActionListener (this);

        JPanel kontrolPaneli = new JPanel();
        kontrolPaneli.add (tabakaListesi);
        kontrolPaneli.add (üstünden);

        kontrolPaneli.setBorder (BorderFactory.createTitledBorder ("Dük'ün tabakasýný ve üstünden/altýndan'ý seçin"));
        return kontrolPaneli;
    } // kontrolPaneliniYarat() metodu sonu...

    // Tabakalý pano içinde dük ('ün en ve boy ortasýndan) fare okunu izlesin...
    public void mouseMoved (MouseEvent olay) {dükEtiketi.setLocation (olay.getX()-dükEtiketi.getWidth()/2, olay.getY()-dükEtiketi.getHeight()/2);}
    public void mouseDragged (MouseEvent olay) {} // Sürüklemeye duyarsýz olsun...

    // Çentik kutusu ve açýlýr kutuya duyarlýlýðý yönetelim...
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();
        if (ÜSTÜNDEN_KOMUTU.equals (komut)) {
            if (üstünden.isSelected()) tabakalýPano.moveToFront (dükEtiketi);
            else tabakalýPano.moveToBack (dükEtiketi);
        }else
            if (TABAKA_KOMUTU.equals (komut)) {
                int konum = üstünden.isSelected() ? 0 : 1; // 0:üstünden, 1:altýndan...
                tabakalýPano.setLayer (dükEtiketi, tabakaListesi.getSelectedIndex(), konum);
            } // iç-if ve dýþ-else kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Tabakalý Pano Gösterimi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_28b();
        yeniÝçerikPanosu.setOpaque (true);
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
} // J5c_28b sýnýfý sonu...