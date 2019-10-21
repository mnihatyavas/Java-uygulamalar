// J5c_29.java: RootLayeredPaneDemo (KökTabakalýPanoGösterisi) örneði.

import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

// Gereken resim dosyasý: resim/dukeWaveRed.gif
public class J5c_29 extends JPanel implements ActionListener, MouseMotionListener {
    private int[] tabakalar = {-30000, -29999, -1, 0, 30001, 100000};
    // Ýlki 0+'da siyah tabakanýn altýna kayýyor; -30000'de dük altta yok; -30001'de sarý tabaka da kayboluyor...
    private String[] tabakaDizgesi = { "Sarý (-30000)", "Siyah (-29999)", "Mavi (-1)", "Eflatun (0)", "Camgöbeði (30001)", "Yeþil (100000)" };
    private Color[] tabakaRenkleri = { Color.yellow, Color.black, Color.blue, Color.magenta, Color.cyan, Color.green };

    private JLayeredPane tabakalýPano;
    private JLabel dükEtiketi;
    private JCheckBox çentikKutusu;
    private JComboBox açýlýrKutu;

    // Aksiyon komutlarý...
    private static String ÜSTÜNDEN_KOMUTU = "üstünden";
    private static String TABAKA_KOMUTU = "tabaka";

    // Dük'ün (64x64) sol ayak parmaðýný imleç okuna ayarlama...
    private static final int XKONUM = 40;
    private static final int YKONUM = 57;

    // Dük etiketi ilk açýlýþta hangi tabaka üstünde olacak...
    private static final int AÇILIÞ_TABAKASI_ENDEKSÝ = 1;

    public J5c_29 (JLayeredPane tabakalýPano) {// Kurucu...
        super (new GridLayout (1,1));

        // Dük ikonunu yaratýp mevcutsa resmi yoksa "null" yükleyelim...
        final ImageIcon ikon = resimÝkonunuYarat ("resim/dukeWaveRed.gif");

        // Tabakalý panoyu kuralým ve fare hareketine duyarlayalým...
        this.tabakalýPano = tabakalýPano;
        tabakalýPano.addMouseMotionListener (this);

        // Tabakalý panoya ilk eklenecek renkli etiketin konumunu belirleyelim...
        Point ilkKonum = new Point (10,70);

        // Tabakalý panodaki bir sonraki renkli etiketin saða-aþaðýya kayýþ pikseli...
        int saðAþaðýya = 25;

        // Tabakalý panoya, dizide tanýmlý renkli etiketleri ekleyelim...
        for (int i = 0; i < tabakaDizgesi.length; i++) {
            JLabel renkliEtiket = renkliEtiketiYarat (tabakaDizgesi[i], tabakaRenkleri[i], ilkKonum);
            tabakalýPano.add (renkliEtiket, new Integer (tabakalar[i]));
            ilkKonum.x += saðAþaðýya;
            ilkKonum.y += saðAþaðýya;
        } // for döngüsü sonu...

        // Mevcutsa resimli deðilse kýrmýzý kareli dük etiketini yaratýp tabakalý panoya ekleyelim...
        dükEtiketi = new JLabel (ikon);
        if (ikon != null) dükEtiketi.setBounds (15, 225, ikon.getIconWidth(), ikon.getIconHeight());
        else {System.err.println ("Dük ikonu bulunamadý; yerine (50x50 ebatlý) kýrmýzý bir kare tanýmlanýp kullanýlacak!");
            dükEtiketi.setBounds (15, 225, 50, 50);
            dükEtiketi.setOpaque (true);
            dükEtiketi.setBackground (Color.RED);
        } // else kararý sonu...

        tabakalýPano.add (dükEtiketi, new Integer (tabakalar[AÇILIÞ_TABAKASI_ENDEKSÝ]), 0);

        // Açýlýr ve çentikli kutulu kontrol panelini de yaratýp (bu tabakalý panoya) ekleyelim...
        add (kontrolPaneliniYarat());
    } // J5c_29(..) kurucusu sonu...

    protected static ImageIcon resimÝkonunuYarat (String yol) {
        java.net.URL resimYureli = J5c_29.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...

    // Renk etiketlerini yaratalým...
    private JLabel renkliEtiketiYarat (String etiketAdý, Color etiketRengi, Point ilkKonum) {
        JLabel renkliEtiket = new JLabel (etiketAdý);
        renkliEtiket.setVerticalAlignment (JLabel.TOP);
        renkliEtiket.setHorizontalAlignment (JLabel.CENTER);
        renkliEtiket.setOpaque (true);
        renkliEtiket.setBackground (etiketRengi);
        renkliEtiket.setForeground (Color.WHITE);
        renkliEtiket.setBorder (BorderFactory.createLineBorder (Color.red));
        renkliEtiket.setBounds (ilkKonum.x, ilkKonum.y, 140, 140);
        return renkliEtiket;
    } // renkliEtiketiYarat(..) metodu sonu...

    // Çentikli ve açýlýr kutuyu aksiyon dinleyicisine duyarlayalým...
    private JPanel kontrolPaneliniYarat() {
        çentikKutusu = new JCheckBox ("Tabakanýn üstü");
        çentikKutusu.setSelected (true);
        çentikKutusu.setActionCommand (ÜSTÜNDEN_KOMUTU);
        çentikKutusu.addActionListener (this);

        açýlýrKutu = new JComboBox (tabakaDizgesi);
        açýlýrKutu.setSelectedIndex (AÇILIÞ_TABAKASI_ENDEKSÝ);
        açýlýrKutu.setActionCommand (TABAKA_KOMUTU);
        açýlýrKutu.addActionListener (this);

        JPanel kontrolPaneli = new JPanel();
        kontrolPaneli.add (açýlýrKutu);
        kontrolPaneli.add (çentikKutusu);
        kontrolPaneli.setBorder (BorderFactory.createTitledBorder ("Dük tabakasýný ve üst/alt'ý seçin"));
        return kontrolPaneli;
    } // kontrolPaneliniYarat() metodu sonu...

    // Dük pencere içinde fare okunu izlesin...
    public void mouseMoved (MouseEvent olay) {dükEtiketi.setLocation (olay.getX()-XKONUM, olay.getY()-YKONUM);}
    public void mouseDragged (MouseEvent aldýrma) {}

    // Dük hareketlerini tabakayý ve üst/alt konumlarýný seçerek yönetelim...
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (ÜSTÜNDEN_KOMUTU.equals (komut)) {
            if (çentikKutusu.isSelected()) tabakalýPano.moveToFront (dükEtiketi);
            else tabakalýPano.moveToBack (dükEtiketi);
        }else if (TABAKA_KOMUTU.equals (komut)) {
            int konum = çentikKutusu.isSelected() ? 0 : -1;
            tabakalýPano.setLayer (dükEtiketi, tabakalar[açýlýrKutu.getSelectedIndex()], konum);
        } // else-if kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kök Tabakalý Pano Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_29 yeniÝçerikPanosu = new J5c_29 (çerçeve.getLayeredPane());
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setSize (new Dimension (300, 400));
        çerçeve.setLocation (500,100);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_29 sýnýfý sonu...