// J5c_29.java: RootLayeredPaneDemo (K�kTabakal�PanoG�sterisi) �rne�i.

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

// Gereken resim dosyas�: resim/dukeWaveRed.gif
public class J5c_29 extends JPanel implements ActionListener, MouseMotionListener {
    private int[] tabakalar = {-30000, -29999, -1, 0, 30001, 100000};
    // �lki 0+'da siyah tabakan�n alt�na kay�yor; -30000'de d�k altta yok; -30001'de sar� tabaka da kayboluyor...
    private String[] tabakaDizgesi = { "Sar� (-30000)", "Siyah (-29999)", "Mavi (-1)", "Eflatun (0)", "Camg�be�i (30001)", "Ye�il (100000)" };
    private Color[] tabakaRenkleri = { Color.yellow, Color.black, Color.blue, Color.magenta, Color.cyan, Color.green };

    private JLayeredPane tabakal�Pano;
    private JLabel d�kEtiketi;
    private JCheckBox �entikKutusu;
    private JComboBox a��l�rKutu;

    // Aksiyon komutlar�...
    private static String �ST�NDEN_KOMUTU = "�st�nden";
    private static String TABAKA_KOMUTU = "tabaka";

    // D�k'�n (64x64) sol ayak parma��n� imle� okuna ayarlama...
    private static final int XKONUM = 40;
    private static final int YKONUM = 57;

    // D�k etiketi ilk a��l��ta hangi tabaka �st�nde olacak...
    private static final int A�ILI�_TABAKASI_ENDEKS� = 1;

    public J5c_29 (JLayeredPane tabakal�Pano) {// Kurucu...
        super (new GridLayout (1,1));

        // D�k ikonunu yarat�p mevcutsa resmi yoksa "null" y�kleyelim...
        final ImageIcon ikon = resim�konunuYarat ("resim/dukeWaveRed.gif");

        // Tabakal� panoyu kural�m ve fare hareketine duyarlayal�m...
        this.tabakal�Pano = tabakal�Pano;
        tabakal�Pano.addMouseMotionListener (this);

        // Tabakal� panoya ilk eklenecek renkli etiketin konumunu belirleyelim...
        Point ilkKonum = new Point (10,70);

        // Tabakal� panodaki bir sonraki renkli etiketin sa�a-a�a��ya kay�� pikseli...
        int sa�A�a��ya = 25;

        // Tabakal� panoya, dizide tan�ml� renkli etiketleri ekleyelim...
        for (int i = 0; i < tabakaDizgesi.length; i++) {
            JLabel renkliEtiket = renkliEtiketiYarat (tabakaDizgesi[i], tabakaRenkleri[i], ilkKonum);
            tabakal�Pano.add (renkliEtiket, new Integer (tabakalar[i]));
            ilkKonum.x += sa�A�a��ya;
            ilkKonum.y += sa�A�a��ya;
        } // for d�ng�s� sonu...

        // Mevcutsa resimli de�ilse k�rm�z� kareli d�k etiketini yarat�p tabakal� panoya ekleyelim...
        d�kEtiketi = new JLabel (ikon);
        if (ikon != null) d�kEtiketi.setBounds (15, 225, ikon.getIconWidth(), ikon.getIconHeight());
        else {System.err.println ("D�k ikonu bulunamad�; yerine (50x50 ebatl�) k�rm�z� bir kare tan�mlan�p kullan�lacak!");
            d�kEtiketi.setBounds (15, 225, 50, 50);
            d�kEtiketi.setOpaque (true);
            d�kEtiketi.setBackground (Color.RED);
        } // else karar� sonu...

        tabakal�Pano.add (d�kEtiketi, new Integer (tabakalar[A�ILI�_TABAKASI_ENDEKS�]), 0);

        // A��l�r ve �entikli kutulu kontrol panelini de yarat�p (bu tabakal� panoya) ekleyelim...
        add (kontrolPaneliniYarat());
    } // J5c_29(..) kurucusu sonu...

    protected static ImageIcon resim�konunuYarat (String yol) {
        java.net.URL resimYureli = J5c_29.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...

    // Renk etiketlerini yaratal�m...
    private JLabel renkliEtiketiYarat (String etiketAd�, Color etiketRengi, Point ilkKonum) {
        JLabel renkliEtiket = new JLabel (etiketAd�);
        renkliEtiket.setVerticalAlignment (JLabel.TOP);
        renkliEtiket.setHorizontalAlignment (JLabel.CENTER);
        renkliEtiket.setOpaque (true);
        renkliEtiket.setBackground (etiketRengi);
        renkliEtiket.setForeground (Color.WHITE);
        renkliEtiket.setBorder (BorderFactory.createLineBorder (Color.red));
        renkliEtiket.setBounds (ilkKonum.x, ilkKonum.y, 140, 140);
        return renkliEtiket;
    } // renkliEtiketiYarat(..) metodu sonu...

    // �entikli ve a��l�r kutuyu aksiyon dinleyicisine duyarlayal�m...
    private JPanel kontrolPaneliniYarat() {
        �entikKutusu = new JCheckBox ("Tabakan�n �st�");
        �entikKutusu.setSelected (true);
        �entikKutusu.setActionCommand (�ST�NDEN_KOMUTU);
        �entikKutusu.addActionListener (this);

        a��l�rKutu = new JComboBox (tabakaDizgesi);
        a��l�rKutu.setSelectedIndex (A�ILI�_TABAKASI_ENDEKS�);
        a��l�rKutu.setActionCommand (TABAKA_KOMUTU);
        a��l�rKutu.addActionListener (this);

        JPanel kontrolPaneli = new JPanel();
        kontrolPaneli.add (a��l�rKutu);
        kontrolPaneli.add (�entikKutusu);
        kontrolPaneli.setBorder (BorderFactory.createTitledBorder ("D�k tabakas�n� ve �st/alt'� se�in"));
        return kontrolPaneli;
    } // kontrolPaneliniYarat() metodu sonu...

    // D�k pencere i�inde fare okunu izlesin...
    public void mouseMoved (MouseEvent olay) {d�kEtiketi.setLocation (olay.getX()-XKONUM, olay.getY()-YKONUM);}
    public void mouseDragged (MouseEvent ald�rma) {}

    // D�k hareketlerini tabakay� ve �st/alt konumlar�n� se�erek y�netelim...
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (�ST�NDEN_KOMUTU.equals (komut)) {
            if (�entikKutusu.isSelected()) tabakal�Pano.moveToFront (d�kEtiketi);
            else tabakal�Pano.moveToBack (d�kEtiketi);
        }else if (TABAKA_KOMUTU.equals (komut)) {
            int konum = �entikKutusu.isSelected() ? 0 : -1;
            tabakal�Pano.setLayer (d�kEtiketi, tabakalar[a��l�rKutu.getSelectedIndex()], konum);
        } // else-if karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("K�k Tabakal� Pano G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_29 yeni��erikPanosu = new J5c_29 (�er�eve.getLayeredPane());
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setSize (new Dimension (300, 400));
        �er�eve.setLocation (500,100);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_29 s�n�f� sonu...