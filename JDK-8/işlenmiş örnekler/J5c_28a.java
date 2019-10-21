// J5c_28a.java: LayeredPaneDemo (TabakalýPanoGösterimi) örneði.

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

/* Gereken resim dosyasý:
 * resim/dukeWaveRed.gif. 
 */
public class J5c_28a extends JPanel implements ActionListener, MouseMotionListener {
    private String[] tabakaAdlarý = { "Sarý (0)", "Eflatun (1)", "Camgöbeði (2)",   "Kýrmýzý (3)", "Yeþil (4)" };
    private Color[] tabakaRenkleri = { Color.yellow, Color.magenta, Color.cyan,   Color.red, Color.green };

    private JLayeredPane tabakalýPano;
    private JLabel dükEtiketi;
    private JCheckBox çentikKutusu;
    private JComboBox açýlýrKutu;

    // Aksiyon komutlarý...
    private static String ÜSTÜNDEN_KOMUTU = "üstünden";
    private static String TABAKA_KOMUTU = "tabaka";

    // Dük'ün sol_TM ayak baþparmaðýný fare okucuna baðlama ayarlarý...
    private static final int X_KONUMU = 40; // Resim ebatý: 64x64; sol ayakucu konumu: 40x57...
    private static final int Y_KONUMU = 57;

    public J5c_28a() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));

        // Dük ikonunu yaratýp yükleyelim...
        final ImageIcon ikon = resimÝkonuYarat ("resim/dukeWaveRed.gif");

        // Tabakalý baþlýklý panoyu yaratýp kuralým...
        tabakalýPano = new JLayeredPane();
        tabakalýPano.setPreferredSize (new Dimension (300, 310));
        tabakalýPano.setBorder (BorderFactory.createTitledBorder ("Dük'ü yürütmek için fareyi (týklamadan) kullanýn"));
        tabakalýPano.addMouseMotionListener (this);

        // Eklenecek ilk tabakanýn (tabakalýPano'daki) konumunu belirtelim...
        Point ilkKonum = new Point (10, 20);

        // Bir sonraki tabakanýn (x,y) sol-aþaðý kaydýrýlacak mesafesi...
        int kaydýr = 35;

        // Mutlak kaydýrmalý konumlandýrmalý 5 renkli tabakayý serelim...
        for (int i = 0; i < tabakaAdlarý.length; i++) {
            JLabel tabakaEtiketi = renkliEtiketYarat (tabakaAdlarý[i],  tabakaRenkleri[i], ilkKonum);
            tabakalýPano.add (tabakaEtiketi, new Integer (i));
            ilkKonum.x += kaydýr;
            ilkKonum.y += kaydýr;
        } // for döngüsü sonu...

        // Dük'ü veya namevcutsa 30x30 ebatlý siyah kareyi yaratýp tabakalý etiketin altýna koyalým...
        dükEtiketi = new JLabel (ikon);
        if (ikon != null) dükEtiketi.setBounds (15, 225, ikon.getIconWidth(), ikon.getIconHeight());
        else {System.err.println ("Dük ikonu bulunamadý, yerine 30x30 ebatlý siyah kare kullanýlacak.");
            dükEtiketi.setBounds (15, 225, 30, 30);
            dükEtiketi.setOpaque (true);
            dükEtiketi.setBackground (Color.BLACK);
        } // else kararý sonu...

        tabakalýPano.add (dükEtiketi, new Integer (2), 0); // Ýlk anda Camgöbeði (2) üzerinde olacak..

        // Kontrol panelini ve tabakalýPano'yu bu Box/kutu serimli JPanel'e ekleyelim...
        add (Box.createRigidArea (new Dimension (0, 10)));
        add (kontrolPaneliYarat());
        add (Box.createRigidArea (new Dimension (0, 10)));
        add (tabakalýPano);
    } // J5c_28a() kurucusu sonu...

    protected static ImageIcon resimÝkonuYarat (String yol) {
        java.net.URL resimYureli = J5c_28a.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!");return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    // Tabaka etiketi yaratýp renkle ve konumla kuralým...
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

    // Çentik ve açýlýr kutulu kontrol panelini baþlýklý yaratýp döndürelim...
    private JPanel kontrolPaneliYarat() {
        çentikKutusu = new JCheckBox ("Seçili tabakanýn üstünden");
        çentikKutusu.setSelected (true);
        çentikKutusu.setActionCommand (ÜSTÜNDEN_KOMUTU);
        çentikKutusu.addActionListener (this);

        açýlýrKutu = new JComboBox (tabakaAdlarý);
        açýlýrKutu.setSelectedIndex (2); // Camgöbeði (2) tabakasý...
        açýlýrKutu.setActionCommand (TABAKA_KOMUTU);
        açýlýrKutu.addActionListener (this);

        JPanel kontrolPaneli = new JPanel();
        kontrolPaneli.add (açýlýrKutu);
        kontrolPaneli.add (çentikKutusu);
        kontrolPaneli.setBorder (BorderFactory.createTitledBorder ("Dük'ün geçeceði tabakayý ve üst/alt seçin"));

        return kontrolPaneli;
    } // kontrolPaneliYarat() metodu sonu...

    // Dük'ün tabakalýPano içinde fareyi takip etmesini saðlayalým...
    public void mouseMoved (MouseEvent olay) {dükEtiketi.setLocation (olay.getX()-X_KONUMU, olay.getY()-Y_KONUMU);}
    public void mouseDragged (MouseEvent olay) {} // Týklayarak sürüklenmeye duyarsýz býrakalým...

    // Üst çentikliyse Dük seçili tabakanýn üstünden, çentiksizse altýndan geçer...
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (ÜSTÜNDEN_KOMUTU.equals (komut)) {
            if (çentikKutusu.isSelected()) tabakalýPano.moveToFront (dükEtiketi);
            else tabakalýPano.moveToBack (dükEtiketi);

        }else
            if (TABAKA_KOMUTU.equals (komut)) {
                int konum = çentikKutusu.isSelected() ? 0 : 1; // 0:üstünden, 1:altýndan...
                tabakalýPano.setLayer (dükEtiketi, açýlýrKutu.getSelectedIndex(), konum);
            } // iç-if ve dýþ-else kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Tabakalý Pano Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_28a();
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        //çerçeve.setBounds (Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-250,0,0);
        //çerçeve.setLocationRelativeTo (null);
        //çerçeve.setBounds (550,100,0,0);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_28a sýnýfý sonu...