// J9g_8.java: Transform (D�n���m) �rne�i.

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
    JLabel �ekilEtiketi, �izgiEtiketi, boyaEtiketi, d�n���mEtiketi, kenarl�kEtiketi;
    D�n���mPaneli g�steriPaneli;
    static JComboBox �ekil, �izgi, boya, d�n���m, kenarl�k;
    JButton g�ncelle;

    public static void main (String[] arg�man) {
        JFrame �er�eve = new JFrame ("D�n���m");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        JApplet aplet = new J9g_8(); // Varsay�l� kuruculu s�n�f nesnesi yarat�l�r...
        �er�eve.getContentPane().add (BorderLayout.CENTER, aplet);
        aplet.init(); // init() haz�r aplet metodu �a�r�l�r...
        �er�eve.setSize (550, 400);
        �er�eve.setVisible (true);
   } // main(..) metodu sonu...

    public void init() {
        GridBagLayout �zgaraSerilim = new GridBagLayout();
        getContentPane().setLayout (�zgaraSerilim);
        GridBagConstraints s�n�rlay�c�lar1 = new GridBagConstraints();

        s�n�rlay�c�lar1.weightx = 1.0;
        s�n�rlay�c�lar1.fill = GridBagConstraints.BOTH;

        �ekilEtiketi = new JLabel();
        �ekilEtiketi.setText ("�ekiller");
        Font yaz�Fonu = getFont().deriveFont (1); // 1=Bold
        �ekilEtiketi.setFont (yaz�Fonu);
        �ekilEtiketi.setHorizontalAlignment (JLabel.CENTER);
        �zgaraSerilim.setConstraints (�ekilEtiketi, s�n�rlay�c�lar1);
        getContentPane().add (�ekilEtiketi);

        �izgiEtiketi = new JLabel();
        �izgiEtiketi.setText ("�izgiler");
        �izgiEtiketi.setFont (yaz�Fonu);
        �izgiEtiketi.setHorizontalAlignment (JLabel.CENTER);
        �zgaraSerilim.setConstraints (�izgiEtiketi, s�n�rlay�c�lar1);
        getContentPane().add (�izgiEtiketi);

        boyaEtiketi = new JLabel();
        boyaEtiketi.setText ("Boyama");
        boyaEtiketi.setFont (yaz�Fonu);
        boyaEtiketi.setHorizontalAlignment (JLabel.CENTER);
        �zgaraSerilim.setConstraints (boyaEtiketi, s�n�rlay�c�lar1);
        getContentPane().add (boyaEtiketi);

        s�n�rlay�c�lar1.gridwidth = GridBagConstraints.RELATIVE;
        d�n���mEtiketi = new JLabel();  
        d�n���mEtiketi.setText ("D�n���m");
        d�n���mEtiketi.setFont (yaz�Fonu);
        d�n���mEtiketi.setHorizontalAlignment (JLabel.CENTER);
        �zgaraSerilim.setConstraints (d�n���mEtiketi, s�n�rlay�c�lar1);
        getContentPane().add (d�n���mEtiketi);

        s�n�rlay�c�lar1.gridwidth = GridBagConstraints.REMAINDER;
        kenarl�kEtiketi = new JLabel();
        kenarl�kEtiketi.setText ("Takdim");
        kenarl�kEtiketi.setFont (yaz�Fonu);
        kenarl�kEtiketi.setHorizontalAlignment (JLabel.CENTER); 
        �zgaraSerilim.setConstraints (kenarl�kEtiketi, s�n�rlay�c�lar1);
        getContentPane().add (kenarl�kEtiketi);

        GridBagConstraints s�n�rlay�c�lar2 = new GridBagConstraints();
        s�n�rlay�c�lar2.weightx = 1.0;
        s�n�rlay�c�lar2.fill = GridBagConstraints.BOTH;
        �ekil = new JComboBox (new Object[] {
                "kare",
                "elips",
                "metin"});
        �ekil.addItemListener (this); // Dinleyiciye duyarl�...
        yaz�Fonu = yaz�Fonu.deriveFont (0, 14.0f); // 0=Plain, px=14 (varsay�l� ebat)...
        �ekil.setFont (yaz�Fonu);
        �zgaraSerilim.setConstraints (�ekil, s�n�rlay�c�lar2);
        getContentPane().add (�ekil);

        �izgi = new JComboBox (new Object[] {
                "ince",
                "kal�n",
                "tireli"});
        �izgi.addItemListener (this); // Dinleyiciye duyarl�...
        �izgi.setFont (yaz�Fonu);
        �zgaraSerilim.setConstraints (�izgi, s�n�rlay�c�lar2);
        getContentPane().add (�izgi);

        boya = new JComboBox (new Object[] {
                "koyu",
                "kademeli",
                "benekli"});
        boya.addItemListener (this); // Dinleyiciye duyarl�...
        boya.setFont (yaz�Fonu);
        �zgaraSerilim.setConstraints (boya, s�n�rlay�c�lar2);
        getContentPane().add (boya);

        s�n�rlay�c�lar2.gridwidth = GridBagConstraints.RELATIVE;
        d�n���m = new JComboBox (new Object[] {
                "orijinal",
                "d�nder",
                "k���lt",
                "yat�kla"});
        d�n���m.addItemListener (this); // Dinleyiciye duyarl�...
        d�n���m.setFont (yaz�Fonu);
        �zgaraSerilim.setConstraints (d�n���m, s�n�rlay�c�lar2);
        getContentPane().add (d�n���m);

        s�n�rlay�c�lar2.gridwidth = GridBagConstraints.REMAINDER;
        kenarl�k = new JComboBox (new Object[] {
                "kenarl�k",
                "dolgu",
                "kenarl�k ve dolgu"}); 
        kenarl�k.addItemListener (this); // Dinleyiciye duyarl�...
        kenarl�k.setFont (yaz�Fonu);
        �zgaraSerilim.setConstraints (kenarl�k, s�n�rlay�c�lar2);
        getContentPane().add (kenarl�k);

        GridBagConstraints s�n�rlay�c�lar3 = new GridBagConstraints();
        s�n�rlay�c�lar3.gridwidth = GridBagConstraints.REMAINDER;
        g�ncelle = new JButton ("G�ncelle");
        g�ncelle.addActionListener (this); // Dinleyiciye duyarl�...
        g�ncelle.setFont (yaz�Fonu);
        �zgaraSerilim.setConstraints (g�ncelle, s�n�rlay�c�lar3);
        getContentPane().add (g�ncelle);

        GridBagConstraints s�n�rlay�c�lar4 = new GridBagConstraints();
        s�n�rlay�c�lar4.fill = GridBagConstraints.BOTH;
        s�n�rlay�c�lar4.weightx = 1.0;
        s�n�rlay�c�lar4.weighty = 1.0;
        s�n�rlay�c�lar4.gridwidth = GridBagConstraints.REMAINDER;
        g�steriPaneli = new D�n���mPaneli();
        �zgaraSerilim.setConstraints (g�steriPaneli, s�n�rlay�c�lar4);
        getContentPane().add (g�steriPaneli);
        //validate();
    } // init() haz�r aplet metodu sonu...

    public void itemStateChanged (ItemEvent olay){}

    public void actionPerformed (ActionEvent olay) {
        g�steriPaneli.d�n���m�Kur (d�n���m.getSelectedIndex());
        g�steriPaneli.�ekliSun();
    } // actionPerformed(..) haz�r metodu sonu...
} // J9g_8 s�n�f� sonu...

class D�n���mPaneli extends JPanel {
    AffineTransform d�n��t�r�c�1 = new AffineTransform();
    int en, boy;
    Shape �ekiller[] = new Shape[3];
    boolean ilkMi = true;

    public D�n���mPaneli() {// Kurucu...
        setBackground (Color.ORANGE);
        // �ekilleri kur...
        �ekiller[0] = new Rectangle (0,0, 100,100);
        �ekiller[1] = new Ellipse2D.Double (0,0, 100,100);
        TextLayout metinSerilim1 = new TextLayout ("Metin", new Font ("Helvetica", 1, 96), new FontRenderContext (null, false, false));
        // "Metin" ibaresi 1=koyu ve 96=piksel ebat�nda...
        AffineTransform metinD�n��t�r�c� = new AffineTransform();
        metinD�n��t�r�c�.translate (0, (float)metinSerilim1.getBounds().getHeight());
        �ekiller[2] = metinSerilim1.getOutline (metinD�n��t�r�c�);
    } // D�n���mPaneli() kurucusu sonu...

   // D�n��t�r�c�y� kur...
    public void d�n���m�Kur (int d�n��t�rmeEndeksi) {
        switch (d�n��t�rmeEndeksi) {
            case 0 : d�n��t�r�c�1.setToIdentity();
                d�n��t�r�c�1.translate(en/2, boy/2);
                break;
            case 1 : d�n��t�r�c�1.rotate (Math.toRadians (45)); break;
            case 2 : d�n��t�r�c�1.scale (0.5, 0.5); break;
            case 3 : d�n��t�r�c�1.shear (0.5, 0.0); break;
        } // switch-case blo�u sonu...
    } // d�n���m�Kur(..) metodu sonu...

    public void �ekliSun() {repaint();} // paintComponent(..) metodunu yineler...

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        Graphics2D g2 = (Graphics2D)g;
        Dimension ebat = getSize();
        en = ebat.width;
        boy = ebat.height;

        String talimat = "A��l�r kutulardan bir �ekil, �izgi, boya, d�n���m ve takdim";
        TextLayout metinSerilim2 = new TextLayout (talimat, new Font ("Helvetica", 0, 10), g2.getFontRenderContext());
        // 0=Plain, 10=piksel yaz� fonu ebat�...
        float eni = (float)metinSerilim2.getBounds().getWidth();
        metinSerilim2.draw (g2, en/2-eni/2, 15);

        talimat = "metodu se�tiksen sonra etkisini g�rmek i�in G�ncelle d��mesini t�klay�n.";
        metinSerilim2 = new TextLayout (talimat, new Font ("Helvetica", 0, 10), g2.getFontRenderContext());
        eni = (float)metinSerilim2.getBounds().getWidth();
        float boyu = (float)metinSerilim2.getBounds().getHeight();
        metinSerilim2.draw (g2, en/2-eni/2, boyu + 17);

        if (ilkMi) {d�n��t�r�c�1.setToIdentity();
            d�n��t�r�c�1.translate (en/2, boy/2);
            ilkMi = false;
        } // if karar� sonu...

        // �izgileri kur...
        switch (J9g_8.�izgi.getSelectedIndex() ) {
            case 0 : g2.setStroke (new BasicStroke (3.0f)); break;
            case 1 : g2.setStroke (new BasicStroke (8.0f)); break;
            case 2 : float tire[] = {10.0f};
                g2.setStroke (new BasicStroke (3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, tire, 0.0f));
                break;
        } // switch-case blo�u sonu...

        // Boyay� kur...
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
        } // switch-case blo�u sonu...

        Shape �eklet = �ekiller[J9g_8.�ekil.getSelectedIndex()];
        Rectangle kutu = �eklet.getBounds();

        // Se�ilen �ekil tuvalin ortas�na konur...
        AffineTransform ortalayanD�n��t�r�c� = new AffineTransform();
        ortalayanD�n��t�r�c�.concatenate (d�n��t�r�c�1);
        ortalayanD�n��t�r�c�.translate (-(kutu.width/2), -(kutu.height/2));
        g2.transform (ortalayanD�n��t�r�c�);

        // Kenarl��� kur...
        switch (J9g_8.kenarl�k.getSelectedIndex()) {
            case 0 : g2.draw (�eklet); break;
            case 1 : g2.fill (�eklet); break;
            case 2 : Graphics2D ge�ici2D = g2;
                g2.fill (�eklet);
                g2.setColor (Color.darkGray);
                g2.draw (�eklet);
                g2.setPaint (ge�ici2D.getPaint()); break;   
        } // switch-case blo�u sonu...
    } // paintComponent(..) haz�r metodu sonu...
} // D�n���mPaneli s�n�f� sonu...