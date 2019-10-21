// J9g_8.java: Transform (Dönüþüm) örneði.

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.TexturePaint;

import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;

import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import java.awt.image.BufferedImage;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;

public class J9g_8 extends JApplet implements ItemListener, ActionListener {
    JLabel þekilEtiketi, çizgiEtiketi, boyaEtiketi, dönüþümEtiketi, kenarlýkEtiketi;
    DönüþümPaneli gösteriPaneli;
    static JComboBox þekil, çizgi, boya, dönüþüm, kenarlýk;
    JButton güncelle;

    public static void main (String[] argüman) {
        JFrame çerçeve = new JFrame ("Dönüþüm");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JApplet aplet = new J9g_8(); // Varsayýlý kuruculu sýnýf nesnesi yaratýlýr...
        çerçeve.getContentPane().add (BorderLayout.CENTER, aplet);
        aplet.init(); // init() hazýr aplet metodu çaðrýlýr...
        çerçeve.setSize (550, 400);
        çerçeve.setVisible (true);
   } // main(..) metodu sonu...

    public void init() {
        GridBagLayout ýzgaraSerilim = new GridBagLayout();
        getContentPane().setLayout (ýzgaraSerilim);
        GridBagConstraints sýnýrlayýcýlar1 = new GridBagConstraints();

        sýnýrlayýcýlar1.weightx = 1.0;
        sýnýrlayýcýlar1.fill = GridBagConstraints.BOTH;

        þekilEtiketi = new JLabel();
        þekilEtiketi.setText ("Þekiller");
        Font yazýFonu = getFont().deriveFont (1); // 1=Bold
        þekilEtiketi.setFont (yazýFonu);
        þekilEtiketi.setHorizontalAlignment (JLabel.CENTER);
        ýzgaraSerilim.setConstraints (þekilEtiketi, sýnýrlayýcýlar1);
        getContentPane().add (þekilEtiketi);

        çizgiEtiketi = new JLabel();
        çizgiEtiketi.setText ("Çizgiler");
        çizgiEtiketi.setFont (yazýFonu);
        çizgiEtiketi.setHorizontalAlignment (JLabel.CENTER);
        ýzgaraSerilim.setConstraints (çizgiEtiketi, sýnýrlayýcýlar1);
        getContentPane().add (çizgiEtiketi);

        boyaEtiketi = new JLabel();
        boyaEtiketi.setText ("Boyama");
        boyaEtiketi.setFont (yazýFonu);
        boyaEtiketi.setHorizontalAlignment (JLabel.CENTER);
        ýzgaraSerilim.setConstraints (boyaEtiketi, sýnýrlayýcýlar1);
        getContentPane().add (boyaEtiketi);

        sýnýrlayýcýlar1.gridwidth = GridBagConstraints.RELATIVE;
        dönüþümEtiketi = new JLabel();  
        dönüþümEtiketi.setText ("Dönüþüm");
        dönüþümEtiketi.setFont (yazýFonu);
        dönüþümEtiketi.setHorizontalAlignment (JLabel.CENTER);
        ýzgaraSerilim.setConstraints (dönüþümEtiketi, sýnýrlayýcýlar1);
        getContentPane().add (dönüþümEtiketi);

        sýnýrlayýcýlar1.gridwidth = GridBagConstraints.REMAINDER;
        kenarlýkEtiketi = new JLabel();
        kenarlýkEtiketi.setText ("Takdim");
        kenarlýkEtiketi.setFont (yazýFonu);
        kenarlýkEtiketi.setHorizontalAlignment (JLabel.CENTER); 
        ýzgaraSerilim.setConstraints (kenarlýkEtiketi, sýnýrlayýcýlar1);
        getContentPane().add (kenarlýkEtiketi);

        GridBagConstraints sýnýrlayýcýlar2 = new GridBagConstraints();
        sýnýrlayýcýlar2.weightx = 1.0;
        sýnýrlayýcýlar2.fill = GridBagConstraints.BOTH;
        þekil = new JComboBox (new Object[] {
                "kare",
                "elips",
                "metin"});
        þekil.addItemListener (this); // Dinleyiciye duyarlý...
        yazýFonu = yazýFonu.deriveFont (0, 14.0f); // 0=Plain, px=14 (varsayýlý ebat)...
        þekil.setFont (yazýFonu);
        ýzgaraSerilim.setConstraints (þekil, sýnýrlayýcýlar2);
        getContentPane().add (þekil);

        çizgi = new JComboBox (new Object[] {
                "ince",
                "kalýn",
                "tireli"});
        çizgi.addItemListener (this); // Dinleyiciye duyarlý...
        çizgi.setFont (yazýFonu);
        ýzgaraSerilim.setConstraints (çizgi, sýnýrlayýcýlar2);
        getContentPane().add (çizgi);

        boya = new JComboBox (new Object[] {
                "koyu",
                "kademeli",
                "benekli"});
        boya.addItemListener (this); // Dinleyiciye duyarlý...
        boya.setFont (yazýFonu);
        ýzgaraSerilim.setConstraints (boya, sýnýrlayýcýlar2);
        getContentPane().add (boya);

        sýnýrlayýcýlar2.gridwidth = GridBagConstraints.RELATIVE;
        dönüþüm = new JComboBox (new Object[] {
                "orijinal",
                "dönder",
                "küçült",
                "yatýkla"});
        dönüþüm.addItemListener (this); // Dinleyiciye duyarlý...
        dönüþüm.setFont (yazýFonu);
        ýzgaraSerilim.setConstraints (dönüþüm, sýnýrlayýcýlar2);
        getContentPane().add (dönüþüm);

        sýnýrlayýcýlar2.gridwidth = GridBagConstraints.REMAINDER;
        kenarlýk = new JComboBox (new Object[] {
                "kenarlýk",
                "dolgu",
                "kenarlýk ve dolgu"}); 
        kenarlýk.addItemListener (this); // Dinleyiciye duyarlý...
        kenarlýk.setFont (yazýFonu);
        ýzgaraSerilim.setConstraints (kenarlýk, sýnýrlayýcýlar2);
        getContentPane().add (kenarlýk);

        GridBagConstraints sýnýrlayýcýlar3 = new GridBagConstraints();
        sýnýrlayýcýlar3.gridwidth = GridBagConstraints.REMAINDER;
        güncelle = new JButton ("Güncelle");
        güncelle.addActionListener (this); // Dinleyiciye duyarlý...
        güncelle.setFont (yazýFonu);
        ýzgaraSerilim.setConstraints (güncelle, sýnýrlayýcýlar3);
        getContentPane().add (güncelle);

        GridBagConstraints sýnýrlayýcýlar4 = new GridBagConstraints();
        sýnýrlayýcýlar4.fill = GridBagConstraints.BOTH;
        sýnýrlayýcýlar4.weightx = 1.0;
        sýnýrlayýcýlar4.weighty = 1.0;
        sýnýrlayýcýlar4.gridwidth = GridBagConstraints.REMAINDER;
        gösteriPaneli = new DönüþümPaneli();
        ýzgaraSerilim.setConstraints (gösteriPaneli, sýnýrlayýcýlar4);
        getContentPane().add (gösteriPaneli);
        //validate();
    } // init() hazýr aplet metodu sonu...

    public void itemStateChanged (ItemEvent olay){}

    public void actionPerformed (ActionEvent olay) {
        gösteriPaneli.dönüþümüKur (dönüþüm.getSelectedIndex());
        gösteriPaneli.þekliSun();
    } // actionPerformed(..) hazýr metodu sonu...
} // J9g_8 sýnýfý sonu...

class DönüþümPaneli extends JPanel {
    AffineTransform dönüþtürücü1 = new AffineTransform();
    int en, boy;
    Shape þekiller[] = new Shape[3];
    boolean ilkMi = true;

    public DönüþümPaneli() {// Kurucu...
        setBackground (Color.ORANGE);
        // Þekilleri kur...
        þekiller[0] = new Rectangle (0,0, 100,100);
        þekiller[1] = new Ellipse2D.Double (0,0, 100,100);
        TextLayout metinSerilim1 = new TextLayout ("Metin", new Font ("Helvetica", 1, 96), new FontRenderContext (null, false, false));
        // "Metin" ibaresi 1=koyu ve 96=piksel ebatýnda...
        AffineTransform metinDönüþtürücü = new AffineTransform();
        metinDönüþtürücü.translate (0, (float)metinSerilim1.getBounds().getHeight());
        þekiller[2] = metinSerilim1.getOutline (metinDönüþtürücü);
    } // DönüþümPaneli() kurucusu sonu...

   // Dönüþtürücüyü kur...
    public void dönüþümüKur (int dönüþtürmeEndeksi) {
        switch (dönüþtürmeEndeksi) {
            case 0 : dönüþtürücü1.setToIdentity();
                dönüþtürücü1.translate(en/2, boy/2);
                break;
            case 1 : dönüþtürücü1.rotate (Math.toRadians (45)); break;
            case 2 : dönüþtürücü1.scale (0.5, 0.5); break;
            case 3 : dönüþtürücü1.shear (0.5, 0.0); break;
        } // switch-case bloðu sonu...
    } // dönüþümüKur(..) metodu sonu...

    public void þekliSun() {repaint();} // paintComponent(..) metodunu yineler...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D)g;
        Dimension ebat = getSize();
        en = ebat.width;
        boy = ebat.height;

        String talimat = "Açýlýr kutulardan bir þekil, çizgi, boya, dönüþüm ve takdim";
        TextLayout metinSerilim2 = new TextLayout (talimat, new Font ("Helvetica", 0, 10), g2.getFontRenderContext());
        // 0=Plain, 10=piksel yazý fonu ebatý...
        float eni = (float)metinSerilim2.getBounds().getWidth();
        metinSerilim2.draw (g2, en/2-eni/2, 15);

        talimat = "metodu seçtiksen sonra etkisini görmek için Güncelle düðmesini týklayýn.";
        metinSerilim2 = new TextLayout (talimat, new Font ("Helvetica", 0, 10), g2.getFontRenderContext());
        eni = (float)metinSerilim2.getBounds().getWidth();
        float boyu = (float)metinSerilim2.getBounds().getHeight();
        metinSerilim2.draw (g2, en/2-eni/2, boyu + 17);

        if (ilkMi) {dönüþtürücü1.setToIdentity();
            dönüþtürücü1.translate (en/2, boy/2);
            ilkMi = false;
        } // if kararý sonu...

        // Çizgileri kur...
        switch (J9g_8.çizgi.getSelectedIndex() ) {
            case 0 : g2.setStroke (new BasicStroke (3.0f)); break;
            case 1 : g2.setStroke (new BasicStroke (8.0f)); break;
            case 2 : float tire[] = {10.0f};
                g2.setStroke (new BasicStroke (3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, tire, 0.0f));
                break;
        } // switch-case bloðu sonu...

        // Boyayý kur...
        switch (J9g_8.boya.getSelectedIndex() ) {
            case 0 : g2.setPaint (Color.BLUE); break;
            case 1 : g2.setPaint (new GradientPaint (0,0, Color.lightGray, en-250, boy, Color.BLUE, false)); break;
            case 2 : BufferedImage tamRes = new BufferedImage (15,15, BufferedImage.TYPE_INT_RGB);
                Graphics2D tamRes2D = tamRes.createGraphics();
                tamRes2D.setColor (Color.BLUE);
                tamRes2D.fillRect (0,0, 15,15);
                tamRes2D.setColor (Color.lightGray);
                tamRes2D.translate ((15/2)-(5/2), (15/2)-(5/2));
                tamRes2D.fillOval (0,0, 7,7); 
                Rectangle kutu = new Rectangle (0,0, 25,25);
                g2.setPaint (new TexturePaint (tamRes, kutu));
                break;
        } // switch-case bloðu sonu...

        Shape þeklet = þekiller[J9g_8.þekil.getSelectedIndex()];
        Rectangle kutu = þeklet.getBounds();

        // Seçilen þekil tuvalin ortasýna konur...
        AffineTransform ortalayanDönüþtürücü = new AffineTransform();
        ortalayanDönüþtürücü.concatenate (dönüþtürücü1);
        ortalayanDönüþtürücü.translate (-(kutu.width/2), -(kutu.height/2));
        g2.transform (ortalayanDönüþtürücü);

        // Kenarlýðý kur...
        switch (J9g_8.kenarlýk.getSelectedIndex()) {
            case 0 : g2.draw (þeklet); break;
            case 1 : g2.fill (þeklet); break;
            case 2 : Graphics2D geçici2D = g2;
                g2.fill (þeklet);
                g2.setColor (Color.darkGray);
                g2.draw (þeklet);
                g2.setPaint (geçici2D.getPaint()); break;   
        } // switch-case bloðu sonu...
    } // paintComponent(..) hazýr metodu sonu...
} // DönüþümPaneli sýnýfý sonu...