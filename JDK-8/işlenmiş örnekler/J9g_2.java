// J9g_2.java: Composite (Bileþim) örneði.

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
    BileþimPanelim bileþimPaneli;
    JLabel netliklerEtiketi, kurallarEtiketi;
    JComboBox netliklerKombo, kurallarKombo;
    String aktüelAlfaNetliði = "1.0";
    int aktüelKural = 0;

    public static void main (String s[]) {
        JFrame çerçeve = new JFrame ("Bileþim");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JApplet aplet = new J9g_2(); // Varsayýlý kuruculu sýnýf nesnesini yaratýr...
        çerçeve.getContentPane().add ("Center", aplet);
        aplet.init(); // init() hazýr aplet metodunu çaðýrýr...
        çerçeve.pack();
        çerçeve.setLocation (500, 100);
        çerçeve.setSize (new Dimension (300, 300));
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {
        GridBagLayout serilim = new GridBagLayout();
        getContentPane().setLayout (serilim);

        GridBagConstraints sýnýrlayýcýlar1 = new GridBagConstraints();
        sýnýrlayýcýlar1.weightx = 1.0;
        sýnýrlayýcýlar1.fill = GridBagConstraints.BOTH;

        sýnýrlayýcýlar1.gridwidth = GridBagConstraints.RELATIVE;
        netliklerEtiketi = new JLabel();
        netliklerEtiketi.setText ("Alfa Netlikleri");
        Font yeniFon = getFont().deriveFont (1);
        netliklerEtiketi.setFont (yeniFon);
        netliklerEtiketi.setHorizontalAlignment (JLabel.CENTER);
        serilim.setConstraints (netliklerEtiketi, sýnýrlayýcýlar1);
        getContentPane().add (netliklerEtiketi);   

        sýnýrlayýcýlar1.gridwidth = GridBagConstraints.REMAINDER;
        kurallarEtiketi = new JLabel();
        kurallarEtiketi.setText ("Bileþim Kurallarý");
        yeniFon = getFont().deriveFont (1);
        kurallarEtiketi.setFont (yeniFon);
        kurallarEtiketi.setHorizontalAlignment (JLabel.CENTER);
        serilim.setConstraints (kurallarEtiketi, sýnýrlayýcýlar1);
        getContentPane().add (kurallarEtiketi);   

        GridBagConstraints sýnýrlayýcýlar2 = new GridBagConstraints();
        sýnýrlayýcýlar2.weightx = 1.0;
        sýnýrlayýcýlar2.fill = GridBagConstraints.BOTH;

        sýnýrlayýcýlar2.gridwidth = GridBagConstraints.RELATIVE;
        netliklerKombo = new JComboBox();
        serilim.setConstraints (netliklerKombo, sýnýrlayýcýlar2);
        netliklerKombo.addItem ("1.0"); // Tam net/opak...
        netliklerKombo.addItem ("0.75");
        netliklerKombo.addItem ("0.50");
        netliklerKombo.addItem ("0.25");
        netliklerKombo.addItem ("0.0"); // Tam þeffaf/transparan...
        netliklerKombo.addItemListener (this); // Dinleyiciye duyarlý...
        getContentPane().add (netliklerKombo);

        sýnýrlayýcýlar2.gridwidth = GridBagConstraints.REMAINDER;
        kurallarKombo = new JComboBox();
        serilim.setConstraints (kurallarKombo, sýnýrlayýcýlar2);
        kurallarKombo.addItem ("SRC"); // Kaynak elips'li...
        kurallarKombo.addItem ("CLEAR"); // Elips silinir...
        kurallarKombo.addItem ("SRC_IN"); // Þeffaf elips üstte ve hedef/kutu sýnýrlarý içinde...
        kurallarKombo.addItem ("SRC_OUT"); // Elips altta, kesiþim þeffaf ve hedef/kutu sýnýrlarý dýþýnda...
        kurallarKombo.addItem ("SRC_OVER"); // Elips üstte ve hedef/kutu sýnýrlarý dýþýnda...
        kurallarKombo.addItem ("DST_IN"); // Hedef/kutu üstte ve alttaki elips þeffaf/yok...
        kurallarKombo.addItem ("DST_OUT"); // Hedef/kutu üstte ve þeffaf elips sýnýrlar dýþýnda...
        kurallarKombo.addItem ("DST_OVER"); // Hedef/kutu üstte ve elips sýnýrlar dýþýnda...
        kurallarKombo.addItemListener (this); // Dinleyiciye duyarlý...
        getContentPane().add (kurallarKombo);

        GridBagConstraints sýnýrlayýcýlar3 = new GridBagConstraints(); 
        sýnýrlayýcýlar3.weightx = 1.0;
        sýnýrlayýcýlar3.weighty = 1.0;
        sýnýrlayýcýlar3.fill = GridBagConstraints.BOTH;
        sýnýrlayýcýlar3.gridwidth = GridBagConstraints.REMAINDER;
        bileþimPaneli = new BileþimPanelim();
        serilim.setConstraints (bileþimPaneli, sýnýrlayýcýlar3);
        getContentPane().add (bileþimPaneli); 

        validate();
    } // init() hazýr metodu sonu...

    public void itemStateChanged (ItemEvent olay){
        if (olay.getStateChange() != ItemEvent.SELECTED) return;

        Object tercih = olay.getSource();
        if (tercih == netliklerKombo) aktüelAlfaNetliði = (String)netliklerKombo.getSelectedItem();
        else aktüelKural = kurallarKombo.getSelectedIndex();
        bileþimPaneli.deðiþikliðiÝþle (aktüelAlfaNetliði, aktüelKural);
    } // itemStateChanged(..) hazýr metodu sonu...
} // J9g_2 sýnýfý sonu...

class BileþimPanelim extends JPanel {
    AlphaComposite netlikBileþimi = AlphaComposite.getInstance (AlphaComposite.SRC);  
    float aktüelAlfaNetliði = 1.0f;

    public BileþimPanelim(){} // Varsayýlý sýnýf kurucusu...

    public void deðiþikliðiÝþle (String a, int aktüelKural) {
        aktüelAlfaNetliði = Float.valueOf (a).floatValue();
        netlikBileþimi = AlphaComposite.getInstance (kuralýAl (aktüelKural), aktüelAlfaNetliði);
        repaint(); // paintComponent(..) hazýr metodunu güncelle...
    } // deðiþikliðiÝþle(..) metodu sonu...

    public int kuralýAl (int aktüelKural) {
        int netlikKuralý = 0;
        switch (aktüelKural) {
        case 0: netlikKuralý = AlphaComposite.SRC; break;
        case 1: netlikKuralý = AlphaComposite.CLEAR; break;
        case 2: netlikKuralý = AlphaComposite.SRC_IN; break;
        case 3: netlikKuralý = AlphaComposite.SRC_OUT; break;
        case 4: netlikKuralý = AlphaComposite.SRC_OVER; break;
        case 5: netlikKuralý = AlphaComposite.DST_IN; break;
        case 6: netlikKuralý = AlphaComposite.DST_OUT; break;
        case 7: netlikKuralý = AlphaComposite.DST_OVER; break;
        } // switch-case bloðu sonu...
        return netlikKuralý;
    } // kuralýAl(..) metodu sonu...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D)g;

        Dimension ebat = getSize();
        int en = ebat.width;
        int boy = ebat.height; 

        // Önceki çizim temizlenir...
        g2.setColor (Color.CYAN);
        g2.fillRect (0, 0, en, boy); // Camgöbeði tuval zemini...

        // Alfa netlikli RGB renkli tampon resmini yaratýr...
        BufferedImage tamponResmi = new BufferedImage (en, boy, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gtr = tamponResmi.createGraphics();

        int xKutu = en / 4;
        int yKutu = boy / 4;

        // Tampon resme kutu ve elips çizilir...
        gtr.setColor (new Color (0.0f, 0.0f, 1.0f, 1.0f)); // Mavi ve net...
        gtr.fill (new Rectangle2D.Double (xKutu, yKutu, 150, 100));
        gtr.setColor (new Color (1.0f, 0.0f, 0.0f, 1.0f)); // Kýrmýzý ve net...
        gtr.setComposite (netlikBileþimi); // Þeffaflýk ve bileþim kurallarý resmedilecek...
        gtr.fill (new Ellipse2D.Double (xKutu+xKutu/2,yKutu+yKutu/2, 150,100));
        g2.drawImage (tamponResmi, null, 0, 0);
    } // paintComponent(..) hazýr metodu sonu...
} // BileþimPanelim sýnýfý sonu...