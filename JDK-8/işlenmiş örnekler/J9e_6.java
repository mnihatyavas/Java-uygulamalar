// J9e_6.java: SaveImage (SaklaResmi) örneði.

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

// Gereken resim dosyasý: evönü.jpg
public class J9e_6 extends Component implements ActionListener {
    private BufferedImage tamponluResim, filitreliTResim;
    int tercihEndeksi;
    int aktüelResimTercihi;
    int en, boy;
    String görüntüSeçenekleri[] = {
        "Orijinal Resim", 
        "Hatlar : Hafif Bulanýk",
        "Hatlar : Keskin", 
        "Mavimtrak Negatifi",
    };
    public static final float[] KESKÝN3x3 = {// Kernel resim filitresini keskin hatlaþtýrýr...
        0.f, -1.f,  0.f,
       -1.f,  5.f, -1.f,
        0.f, -1.f,  0.f
    };
    public static final float[] BULANIK3x3 = {// Kernel filitresini bulanýklaþtýrýr...
        0.1f, 0.1f, 0.1f,
        0.1f, 0.2f, 0.1f,
        0.1f, 0.1f, 0.1f
    };

    public J9e_6() {// Kurucu...
        try {tamponluResim = ImageIO.read (new File ("resim/evönü.jpg"));
            en = tamponluResim.getWidth (null);
            boy = tamponluResim.getHeight ();
            if (tamponluResim.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage tamponluResim2 = new BufferedImage (en, boy, BufferedImage.TYPE_INT_RGB);
                Graphics tResimG = tamponluResim2.getGraphics();
                tResimG.drawImage (tamponluResim, 0, 0, null);
                filitreliTResim = tamponluResim = tamponluResim2;
            } // if kararý sonu...
        }catch (IOException ist) {
            System.err.println ("Resim okunamadý!..");
            System.exit (1);
        } // try-catch bloðu sonu...
    } // J9e_6() kurucusu sonu...

    public void paint (Graphics g) {
        resmiFilitrele();
        g.drawImage (filitreliTResim, 0, 0, null);
    } // paint(..) hazýr metodu sonu...

    public void resmiFilitrele() {
        BufferedImageOp tercih = null;
        if (tercihEndeksi == aktüelResimTercihi) return;
        aktüelResimTercihi = tercihEndeksi;

        switch (tercihEndeksi) {
        case 0: filitreliTResim = tamponluResim; // Orijinal
            return; 
        case 1:  // Bulanýk
        case 2:  // Keskin
            float[] veri = (tercihEndeksi == 1) ? BULANIK3x3 : KESKÝN3x3;
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
        } // switch-case bloðu sonu...

        filitreliTResim = new BufferedImage (en, boy, BufferedImage.TYPE_INT_RGB);
        tercih.filter (tamponluResim, filitreliTResim);
    } // resmiFilitrele() metodu sonu...

    public Dimension getPreferredSize() {return new Dimension (en, boy);} // Hazýr metod...
    String[] açýklamayýAl() {return görüntüSeçenekleri;}
    void tercihEndeksiniKur (int i) {tercihEndeksi = i;}

    public void actionPerformed (ActionEvent olay) {
        JComboBox açýlýrKutu = (JComboBox)olay.getSource();
        if (açýlýrKutu.getActionCommand().equals ("Filitreyi Kur")) {
            tercihEndeksiniKur (açýlýrKutu.getSelectedIndex());
            repaint(); // Seçilen resim tercihiyle bileþeni tekrar kur...
        }else if (açýlýrKutu.getActionCommand().equals ("Biçimle")) {
            String biçimUzantýsý = (String)açýlýrKutu.getSelectedItem(); // Seçilen biçim uzantýsý alýnýr...
            File resimDosyasýnýSakla = new File ("resim." + biçimUzantýsý); // Uzantýyý isme ekle...
            JFileChooser dizinSeçici = new JFileChooser();
            dizinSeçici.setSelectedFile (resimDosyasýnýSakla);
            int dönen = dizinSeçici.showSaveDialog (açýlýrKutu);
            if (dönen == JFileChooser.APPROVE_OPTION) {
                resimDosyasýnýSakla = dizinSeçici.getSelectedFile();
                try {ImageIO.write (filitreliTResim, biçimUzantýsý, resimDosyasýnýSakla); // Dosya dizine saklanýr...
                }catch (IOException ist) {} // Aldýrma...
            } // if-iç kararý sonu...
        } // else kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    // Sistemdeki mevcut uzantýlar küçük harfli ve artan sýralý alýnýr...
    public String[] mevcutUzantýlarýAl() {
        String[] resimUzantýlarý = ImageIO.getWriterFormatNames();
        TreeSet<String> aðaçKümeListesi = new TreeSet<String>();
        for (String uz : resimUzantýlarý) aðaçKümeListesi.add (uz.toLowerCase());
        return aðaçKümeListesi.toArray (new String[0]);
    } // mevcutUzantýlarýAl() metodu sonu...

    public static void main (String s[]) {
        JFrame çerçeve = new JFrame ("Resmi Sakla Uygulamasý");
        çerçeve.setDefaultCloseOperation (3); // 3=EXIT_ON_CLOSE
        J9e_6 resmiSakla = new J9e_6(); // Kurucuyu çaðýrýr...
        çerçeve.add ("Center", resmiSakla);
        JComboBox resimTercihleri = new JComboBox (resmiSakla.açýklamayýAl());
        resimTercihleri.setActionCommand ("Filitreyi Kur");
        resimTercihleri.addActionListener (resmiSakla); // Komutla dinleyiciye duyarlý...
        JComboBox resimUzantýlarý = new JComboBox (resmiSakla.mevcutUzantýlarýAl());
        resimUzantýlarý.setActionCommand ("Biçimle");
        resimUzantýlarý.addActionListener (resmiSakla); // Komutla dinleyiciye duyarlý...
        JPanel panel = new JPanel();
        panel.add (resimTercihleri);
        panel.add (new JLabel ("Seçili Uzantýyla Sakla"));
        panel.add (resimUzantýlarý);
        çerçeve.add ("South", panel);
        çerçeve.pack();
        çerçeve.setLocation (500, 50);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9e_6 sýnýfý sonu...