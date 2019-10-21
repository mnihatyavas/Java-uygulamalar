// J9e_6.java: SaveImage (SaklaResmi) �rne�i.

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Graphics;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.geom.AffineTransform;

import java.awt.image.BufferedImage;
import java.awt.image.Kernel;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.LookupOp;
import java.awt.image.ByteLookupTable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import java.util.TreeSet;

// Gereken resim dosyas�: ev�n�.jpg
public class J9e_6 extends Component implements ActionListener {
    private BufferedImage tamponluResim, filitreliTResim;
    int tercihEndeksi;
    int akt�elResimTercihi;
    int en, boy;
    String g�r�nt�Se�enekleri[] = {
        "Orijinal Resim", 
        "Hatlar : Hafif Bulan�k",
        "Hatlar : Keskin", 
        "Mavimtrak Negatifi",
    };
    public static final float[] KESK�N3x3 = {// Kernel resim filitresini keskin hatla�t�r�r...
        0.f, -1.f,  0.f,
       -1.f,  5.f, -1.f,
        0.f, -1.f,  0.f
    };
    public static final float[] BULANIK3x3 = {// Kernel filitresini bulan�kla�t�r�r...
        0.1f, 0.1f, 0.1f,
        0.1f, 0.2f, 0.1f,
        0.1f, 0.1f, 0.1f
    };

    public J9e_6() {// Kurucu...
        try {tamponluResim = ImageIO.read (new File ("resim/ev�n�.jpg"));
            en = tamponluResim.getWidth (null);
            boy = tamponluResim.getHeight ();
            if (tamponluResim.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage tamponluResim2 = new BufferedImage (en, boy, BufferedImage.TYPE_INT_RGB);
                Graphics tResimG = tamponluResim2.getGraphics();
                tResimG.drawImage (tamponluResim, 0, 0, null);
                filitreliTResim = tamponluResim = tamponluResim2;
            } // if karar� sonu...
        }catch (IOException ist) {
            System.err.println ("Resim okunamad�!..");
            System.exit (1);
        } // try-catch blo�u sonu...
    } // J9e_6() kurucusu sonu...

    public void paint (Graphics g) {
        resmiFilitrele();
        g.drawImage (filitreliTResim, 0, 0, null);
    } // paint(..) haz�r metodu sonu...

    public void resmiFilitrele() {
        BufferedImageOp tercih = null;
        if (tercihEndeksi == akt�elResimTercihi) return;
        akt�elResimTercihi = tercihEndeksi;

        switch (tercihEndeksi) {
        case 0: filitreliTResim = tamponluResim; // Orijinal
            return; 
        case 1:  // Bulan�k
        case 2:  // Keskin
            float[] veri = (tercihEndeksi == 1) ? BULANIK3x3 : KESK�N3x3;
            tercih = new ConvolveOp (
                    new Kernel (3, 3, veri),
                    ConvolveOp.EDGE_NO_OP,
                    null);
            break;
        case 3 : // Negatif
            byte negatifVeri[] = new byte[256];
            for (int j=0; j<256; j++) negatifVeri[j] = (byte)(256-j); 
            ByteLookupTable baytAramaTablosu = new ByteLookupTable (0, negatifVeri); 
            tercih = new LookupOp (baytAramaTablosu, null);
            break;
        } // switch-case blo�u sonu...

        filitreliTResim = new BufferedImage (en, boy, BufferedImage.TYPE_INT_RGB);
        tercih.filter (tamponluResim, filitreliTResim);
    } // resmiFilitrele() metodu sonu...

    public Dimension getPreferredSize() {return new Dimension (en, boy);} // Haz�r metod...
    String[] a��klamay�Al() {return g�r�nt�Se�enekleri;}
    void tercihEndeksiniKur (int i) {tercihEndeksi = i;}

    public void actionPerformed (ActionEvent olay) {
        JComboBox a��l�rKutu = (JComboBox)olay.getSource();
        if (a��l�rKutu.getActionCommand().equals ("Filitreyi Kur")) {
            tercihEndeksiniKur (a��l�rKutu.getSelectedIndex());
            repaint(); // Se�ilen resim tercihiyle bile�eni tekrar kur...
        }else if (a��l�rKutu.getActionCommand().equals ("Bi�imle")) {
            String bi�imUzant�s� = (String)a��l�rKutu.getSelectedItem(); // Se�ilen bi�im uzant�s� al�n�r...
            File resimDosyas�n�Sakla = new File ("resim." + bi�imUzant�s�); // Uzant�y� isme ekle...
            JFileChooser dizinSe�ici = new JFileChooser();
            dizinSe�ici.setSelectedFile (resimDosyas�n�Sakla);
            int d�nen = dizinSe�ici.showSaveDialog (a��l�rKutu);
            if (d�nen == JFileChooser.APPROVE_OPTION) {
                resimDosyas�n�Sakla = dizinSe�ici.getSelectedFile();
                try {ImageIO.write (filitreliTResim, bi�imUzant�s�, resimDosyas�n�Sakla); // Dosya dizine saklan�r...
                }catch (IOException ist) {} // Ald�rma...
            } // if-i� karar� sonu...
        } // else karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    // Sistemdeki mevcut uzant�lar k���k harfli ve artan s�ral� al�n�r...
    public String[] mevcutUzant�lar�Al() {
        String[] resimUzant�lar� = ImageIO.getWriterFormatNames();
        TreeSet<String> a�a�K�meListesi = new TreeSet<String>();
        for (String uz : resimUzant�lar�) a�a�K�meListesi.add (uz.toLowerCase());
        return a�a�K�meListesi.toArray (new String[0]);
    } // mevcutUzant�lar�Al() metodu sonu...

    public static void main (String s[]) {
        JFrame �er�eve = new JFrame ("Resmi Sakla Uygulamas�");
        �er�eve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        J9e_6 resmiSakla = new J9e_6(); // Kurucuyu �a��r�r...
        �er�eve.add ("Center", resmiSakla);
        JComboBox resimTercihleri = new JComboBox (resmiSakla.a��klamay�Al());
        resimTercihleri.setActionCommand ("Filitreyi Kur");
        resimTercihleri.addActionListener (resmiSakla); // Komutla dinleyiciye duyarl�...
        JComboBox resimUzant�lar� = new JComboBox (resmiSakla.mevcutUzant�lar�Al());
        resimUzant�lar�.setActionCommand ("Bi�imle");
        resimUzant�lar�.addActionListener (resmiSakla); // Komutla dinleyiciye duyarl�...
        JPanel panel = new JPanel();
        panel.add (resimTercihleri);
        panel.add (new JLabel ("Se�ili Uzant�yla Sakla"));
        panel.add (resimUzant�lar�);
        �er�eve.add ("South", panel);
        �er�eve.pack();
        �er�eve.setLocation (500, 50);
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...
} // J9e_6 s�n�f� sonu...