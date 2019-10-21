// J5c_10.java: CustomComboBoxDemo (GelenekselA��l�rKutuG�sterimi) �rne�i.

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
 * J5c_10/CustomComboBoxDemo.java �u resim dosyalar�n� kullanmakta:
 *   resim/Ku�.gif
 *   resim/Kedi.gif
 *   resim/K�pek.gif
 *   resim/Tav�an.gif
 *   resim/Domuz.gif
 */
public class J5c_10 extends JPanel {
    ImageIcon[] resimler;
    String[] evcilStr = {"Ku�", "Kedi", "K�pek", "Tav�an", "Domuz"};

    public J5c_10() {// Kurucu...
        super (new BorderLayout());

        resimler = new ImageIcon[evcilStr.length];
        Integer[] dizin�nt = new Integer[evcilStr.length];
        for (int i = 0; i < evcilStr.length; i++) {
            dizin�nt[i] = new Integer(i);
            resimler[i] = resim�konuYarat ("resim/" + evcilStr[i] + ".gif");
            if (resimler[i] != null) resimler[i].setDescription (evcilStr[i]);
        } // for d�ng�s� sonu...

        JComboBox evcilListesi = new JComboBox (dizin�nt);
        A��l�rKutuSunucusu sunucu= new A��l�rKutuSunucusu();
        sunucu.setPreferredSize (new Dimension (200, 130));
        evcilListesi.setRenderer (sunucu);
        evcilListesi.setMaximumRowCount (3);

        add (evcilListesi, BorderLayout.PAGE_START);
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5c_10() kurucusu sonu...

    protected static ImageIcon resim�konuYarat (String yol) {
        java.net.URL resimYureli = J5c_10.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyas� bulunamad�!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Geleneksel A��l�rKutu G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JComponent yeni��erikPanosu = new J5c_10(); // Kurucu �al��t�r�l�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);

        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main(String[] args) {
        // Bu uygulaman�n yaratVeG�sterGUI'sini olay-raporlayan bir sicimden �a��ral�m...
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...

    class A��l�rKutuSunucusu extends JLabel implements ListCellRenderer {
        private Font kemK�mFonu;

        public A��l�rKutuSunucusu() {
            setOpaque (true);
            setHorizontalAlignment (CENTER);
            setVerticalAlignment (CENTER);
        } // A��l�rKutuSunucusu() kurucusu sonu...

        // Bu metod se�ili resim ve a��klamas�n� etiketle d�nd�r�r...
        public Component getListCellRendererComponent (
                JList liste,
                Object de�er,
                int endeks,
                boolean se�ildiMi,
                boolean h�creOdakl�M�) {
            // endeks parametresi do�ru olmayabilece�inden se�ilen de�erin endeksini kullanal�m...
            int se�ileninEndeksi = ((Integer)de�er).intValue();

            if (se�ildiMi) {setBackground (liste.getSelectionBackground());
                setForeground (liste.getSelectionForeground());
            }else {setBackground (liste.getBackground());
                setForeground(liste.getForeground());
            } // if-else yap�s� sonu...

            // �kon ve resmini etikete kural�m, namevcutsa kemK�m'le s�yleyelim...
            ImageIcon ikon = resimler[se�ileninEndeksi];
            String evcil = evcilStr[se�ileninEndeksi];
            setIcon (ikon);
            if (ikon != null) {setText (evcil);
                setFont (liste.getFont());
            }else kemK�mMetniKoy ("[" + evcil + "] adl� resim namevcut.", liste.getFont());

            return this;
        } // getListCellRendererComponent(..) metodu sonu...

        //Set the font and text when no image was found.
        protected void kemK�mMetniKoy (String yok, Font normalFon) {
            if (kemK�mFonu == null) kemK�mFonu = normalFon.deriveFont (Font.ITALIC);
            setFont (kemK�mFonu);
            setText (yok);
        } // kemK�mMetniKoy(..) metodu sonu...
    } // A��l�rKutuSunucusu s�n�f� sonu...
} // J5c_10 s�n�f� sonu...