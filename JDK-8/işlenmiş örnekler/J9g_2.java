// J9g_2.java: Composite (Bile�im) �rne�i.

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import java.awt.image.BufferedImage;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class J9g_2 extends JApplet implements ItemListener {
    Bile�imPanelim bile�imPaneli;
    JLabel netliklerEtiketi, kurallarEtiketi;
    JComboBox netliklerKombo, kurallarKombo;
    String akt�elAlfaNetli�i = "1.0";
    int akt�elKural = 0;

    public static void main (String s[]) {
        JFrame �er�eve = new JFrame ("Bile�im");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JApplet aplet = new J9g_2(); // Varsay�l� kuruculu s�n�f nesnesini yarat�r...
        �er�eve.getContentPane().add ("Center", aplet);
        aplet.init(); // init() haz�r aplet metodunu �a��r�r...
        �er�eve.pack();
        �er�eve.setLocation (500, 100);
        �er�eve.setSize (new Dimension (300, 300));
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {
        GridBagLayout serilim = new GridBagLayout();
        getContentPane().setLayout (serilim);

        GridBagConstraints s�n�rlay�c�lar1 = new GridBagConstraints();
        s�n�rlay�c�lar1.weightx = 1.0;
        s�n�rlay�c�lar1.fill = GridBagConstraints.BOTH;

        s�n�rlay�c�lar1.gridwidth = GridBagConstraints.RELATIVE;
        netliklerEtiketi = new JLabel();
        netliklerEtiketi.setText ("Alfa Netlikleri");
        Font yeniFon = getFont().deriveFont (1);
        netliklerEtiketi.setFont (yeniFon);
        netliklerEtiketi.setHorizontalAlignment (JLabel.CENTER);
        serilim.setConstraints (netliklerEtiketi, s�n�rlay�c�lar1);
        getContentPane().add (netliklerEtiketi);   

        s�n�rlay�c�lar1.gridwidth = GridBagConstraints.REMAINDER;
        kurallarEtiketi = new JLabel();
        kurallarEtiketi.setText ("Bile�im Kurallar�");
        yeniFon = getFont().deriveFont (1);
        kurallarEtiketi.setFont (yeniFon);
        kurallarEtiketi.setHorizontalAlignment (JLabel.CENTER);
        serilim.setConstraints (kurallarEtiketi, s�n�rlay�c�lar1);
        getContentPane().add (kurallarEtiketi);   

        GridBagConstraints s�n�rlay�c�lar2 = new GridBagConstraints();
        s�n�rlay�c�lar2.weightx = 1.0;
        s�n�rlay�c�lar2.fill = GridBagConstraints.BOTH;

        s�n�rlay�c�lar2.gridwidth = GridBagConstraints.RELATIVE;
        netliklerKombo = new JComboBox();
        serilim.setConstraints (netliklerKombo, s�n�rlay�c�lar2);
        netliklerKombo.addItem ("1.0"); // Tam net/opak...
        netliklerKombo.addItem ("0.75");
        netliklerKombo.addItem ("0.50");
        netliklerKombo.addItem ("0.25");
        netliklerKombo.addItem ("0.0"); // Tam �effaf/transparan...
        netliklerKombo.addItemListener (this); // Dinleyiciye duyarl�...
        getContentPane().add (netliklerKombo);

        s�n�rlay�c�lar2.gridwidth = GridBagConstraints.REMAINDER;
        kurallarKombo = new JComboBox();
        serilim.setConstraints (kurallarKombo, s�n�rlay�c�lar2);
        kurallarKombo.addItem ("SRC"); // Kaynak elips'li...
        kurallarKombo.addItem ("CLEAR"); // Elips silinir...
        kurallarKombo.addItem ("SRC_IN"); // �effaf elips �stte ve hedef/kutu s�n�rlar� i�inde...
        kurallarKombo.addItem ("SRC_OUT"); // Elips altta, kesi�im �effaf ve hedef/kutu s�n�rlar� d���nda...
        kurallarKombo.addItem ("SRC_OVER"); // Elips �stte ve hedef/kutu s�n�rlar� d���nda...
        kurallarKombo.addItem ("DST_IN"); // Hedef/kutu �stte ve alttaki elips �effaf/yok...
        kurallarKombo.addItem ("DST_OUT"); // Hedef/kutu �stte ve �effaf elips s�n�rlar d���nda...
        kurallarKombo.addItem ("DST_OVER"); // Hedef/kutu �stte ve elips s�n�rlar d���nda...
        kurallarKombo.addItemListener (this); // Dinleyiciye duyarl�...
        getContentPane().add (kurallarKombo);

        GridBagConstraints s�n�rlay�c�lar3 = new GridBagConstraints(); 
        s�n�rlay�c�lar3.weightx = 1.0;
        s�n�rlay�c�lar3.weighty = 1.0;
        s�n�rlay�c�lar3.fill = GridBagConstraints.BOTH;
        s�n�rlay�c�lar3.gridwidth = GridBagConstraints.REMAINDER;
        bile�imPaneli = new Bile�imPanelim();
        serilim.setConstraints (bile�imPaneli, s�n�rlay�c�lar3);
        getContentPane().add (bile�imPaneli); 

        validate();
    } // init() haz�r metodu sonu...

    public void itemStateChanged (ItemEvent olay){
        if (olay.getStateChange() != ItemEvent.SELECTED) return;

        Object tercih = olay.getSource();
        if (tercih == netliklerKombo) akt�elAlfaNetli�i = (String)netliklerKombo.getSelectedItem();
        else akt�elKural = kurallarKombo.getSelectedIndex();
        bile�imPaneli.de�i�ikli�i��le (akt�elAlfaNetli�i, akt�elKural);
    } // itemStateChanged(..) haz�r metodu sonu...
} // J9g_2 s�n�f� sonu...

class Bile�imPanelim extends JPanel {
    AlphaComposite netlikBile�imi = AlphaComposite.getInstance (AlphaComposite.SRC);  
    float akt�elAlfaNetli�i = 1.0f;

    public Bile�imPanelim(){} // Varsay�l� s�n�f kurucusu...

    public void de�i�ikli�i��le (String a, int akt�elKural) {
        akt�elAlfaNetli�i = Float.valueOf (a).floatValue();
        netlikBile�imi = AlphaComposite.getInstance (kural�Al (akt�elKural), akt�elAlfaNetli�i);
        repaint(); // paintComponent(..) haz�r metodunu g�ncelle...
    } // de�i�ikli�i��le(..) metodu sonu...

    public int kural�Al (int akt�elKural) {
        int netlikKural� = 0;
        switch (akt�elKural) {
        case 0: netlikKural� = AlphaComposite.SRC; break;
        case 1: netlikKural� = AlphaComposite.CLEAR; break;
        case 2: netlikKural� = AlphaComposite.SRC_IN; break;
        case 3: netlikKural� = AlphaComposite.SRC_OUT; break;
        case 4: netlikKural� = AlphaComposite.SRC_OVER; break;
        case 5: netlikKural� = AlphaComposite.DST_IN; break;
        case 6: netlikKural� = AlphaComposite.DST_OUT; break;
        case 7: netlikKural� = AlphaComposite.DST_OVER; break;
        } // switch-case blo�u sonu...
        return netlikKural�;
    } // kural�Al(..) metodu sonu...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D)g;

        Dimension ebat = getSize();
        int en = ebat.width;
        int boy = ebat.height; 

        // �nceki �izim temizlenir...
        g2.setColor (Color.CYAN);
        g2.fillRect (0, 0, en, boy); // Camg�be�i tuval zemini...

        // Alfa netlikli RGB renkli tampon resmini yarat�r...
        BufferedImage tamponResmi = new BufferedImage (en, boy, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gtr = tamponResmi.createGraphics();

        int xKutu = en / 4;
        int yKutu = boy / 4;

        // Tampon resme kutu ve elips �izilir...
        gtr.setColor (new Color (0.0f, 0.0f, 1.0f, 1.0f)); // Mavi ve net...
        gtr.fill (new Rectangle2D.Double (xKutu, yKutu, 150, 100));
        gtr.setColor (new Color (1.0f, 0.0f, 0.0f, 1.0f)); // K�rm�z� ve net...
        gtr.setComposite (netlikBile�imi); // �effafl�k ve bile�im kurallar� resmedilecek...
        gtr.fill (new Ellipse2D.Double (xKutu+xKutu/2,yKutu+yKutu/2, 150,100));
        g2.drawImage (tamponResmi, null, 0, 0);
    } // paintComponent(..) haz�r metodu sonu...
} // Bile�imPanelim s�n�f� sonu...