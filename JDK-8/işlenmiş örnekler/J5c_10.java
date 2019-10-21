// J5c_10.java: CustomComboBoxDemo (GelenekselAçýlýrKutuGösterimi) örneði.

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.ListCellRenderer;

/*
 * J5c_10/CustomComboBoxDemo.java þu resim dosyalarýný kullanmakta:
 *   resim/Kuþ.gif
 *   resim/Kedi.gif
 *   resim/Köpek.gif
 *   resim/Tavþan.gif
 *   resim/Domuz.gif
 */
public class J5c_10 extends JPanel {
    ImageIcon[] resimler;
    String[] evcilStr = {"Kuþ", "Kedi", "Köpek", "Tavþan", "Domuz"};

    public J5c_10() {// Kurucu...
        super (new BorderLayout());

        resimler = new ImageIcon[evcilStr.length];
        Integer[] dizinÝnt = new Integer[evcilStr.length];
        for (int i = 0; i < evcilStr.length; i++) {
            dizinÝnt[i] = new Integer(i);
            resimler[i] = resimÝkonuYarat ("resim/" + evcilStr[i] + ".gif");
            if (resimler[i] != null) resimler[i].setDescription (evcilStr[i]);
        } // for döngüsü sonu...

        JComboBox evcilListesi = new JComboBox (dizinÝnt);
        AçýlýrKutuSunucusu sunucu= new AçýlýrKutuSunucusu();
        sunucu.setPreferredSize (new Dimension (200, 130));
        evcilListesi.setRenderer (sunucu);
        evcilListesi.setMaximumRowCount (3);

        add (evcilListesi, BorderLayout.PAGE_START);
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5c_10() kurucusu sonu...

    protected static ImageIcon resimÝkonuYarat (String yol) {
        java.net.URL resimYureli = J5c_10.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Geleneksel AçýlýrKutu Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JComponent yeniÝçerikPanosu = new J5c_10(); // Kurucu çalýþtýrýlýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);

        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main(String[] args) {
        // Bu uygulamanýn yaratVeGösterGUI'sini olay-raporlayan bir sicimden çaðýralým...
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...

    class AçýlýrKutuSunucusu extends JLabel implements ListCellRenderer {
        private Font kemKümFonu;

        public AçýlýrKutuSunucusu() {
            setOpaque (true);
            setHorizontalAlignment (CENTER);
            setVerticalAlignment (CENTER);
        } // AçýlýrKutuSunucusu() kurucusu sonu...

        // Bu metod seçili resim ve açýklamasýný etiketle döndürür...
        public Component getListCellRendererComponent (
                JList liste,
                Object deðer,
                int endeks,
                boolean seçildiMi,
                boolean hücreOdaklýMý) {
            // endeks parametresi doðru olmayabileceðinden seçilen deðerin endeksini kullanalým...
            int seçileninEndeksi = ((Integer)deðer).intValue();

            if (seçildiMi) {setBackground (liste.getSelectionBackground());
                setForeground (liste.getSelectionForeground());
            }else {setBackground (liste.getBackground());
                setForeground(liste.getForeground());
            } // if-else yapýsý sonu...

            // Ýkon ve resmini etikete kuralým, namevcutsa kemKüm'le söyleyelim...
            ImageIcon ikon = resimler[seçileninEndeksi];
            String evcil = evcilStr[seçileninEndeksi];
            setIcon (ikon);
            if (ikon != null) {setText (evcil);
                setFont (liste.getFont());
            }else kemKümMetniKoy ("[" + evcil + "] adlý resim namevcut.", liste.getFont());

            return this;
        } // getListCellRendererComponent(..) metodu sonu...

        //Set the font and text when no image was found.
        protected void kemKümMetniKoy (String yok, Font normalFon) {
            if (kemKümFonu == null) kemKümFonu = normalFon.deriveFont (Font.ITALIC);
            setFont (kemKümFonu);
            setText (yok);
        } // kemKümMetniKoy(..) metodu sonu...
    } // AçýlýrKutuSunucusu sýnýfý sonu...
} // J5c_10 sýnýfý sonu...