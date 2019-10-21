// J5e_5.java: FieldValidator (Metinsat�r�Ge�erlileyicisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

import java.text.NumberFormat;
import java.text.DateFormat;

import java.util.Date;

public class J5e_5 extends JPanel {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...

    public static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Matin Sat�r� Ge�erlileyicisi");
        JComponent i�erik = i�erikKomponentleriYarat();
        �er�eve.add (i�erik);
        �er�eve.pack();
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    private static JComponent i�erikKomponentleriYarat() {
        Dimension etiketEbat� = new Dimension (80, 20);
        Box kutu = Box.createVerticalBox(); // Yeni ��erik panosu komponenti kutusu...

        // T�m metin sat�rlar� i�in tek bir tabakaUI kural�m (UI:[Graphics]UnitInterface: [Grafik]BirimAray�z�)...
        LayerUI<JFormattedTextField> tabakaUI = new Ge�erlilemeTabakas�UI();

        // �lk (tam/ondal�k) say�sal veri giri�i...
        JLabel etiket = new JLabel ("Say� gir:");
        etiket.setHorizontalAlignment (SwingConstants.RIGHT);
        etiket.setPreferredSize (etiketEbat�);

        NumberFormat say�salBi�im = NumberFormat.getInstance();
        JFormattedTextField bi�imliMetinSat�r� = new JFormattedTextField (say�salBi�im);
        bi�imliMetinSat�r�.setColumns (16);
        bi�imliMetinSat�r�.setFocusLostBehavior (JFormattedTextField.PERSIST);
        bi�imliMetinSat�r�.setValue (99.99);

        JPanel panel = new JPanel();
        panel.add (etiket);
        panel.add (new JLayer<JFormattedTextField> (bi�imliMetinSat�r�, tabakaUI));
        panel.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        kutu.add (Box.createGlue()); // Bo�luk...
        kutu.add (panel);

        // �kinci tarihsel veri giri�i...
        etiket = new JLabel ("Tarih gir:");
        etiket.setHorizontalAlignment (SwingConstants.RIGHT);
        etiket.setPreferredSize (etiketEbat�);

        DateFormat tarihselBi�im = DateFormat.getDateInstance();
        bi�imliMetinSat�r� = new JFormattedTextField (tarihselBi�im);
        bi�imliMetinSat�r�.setColumns (16);
        bi�imliMetinSat�r�.setFocusLostBehavior (JFormattedTextField.PERSIST);
        bi�imliMetinSat�r�.setValue (new Date());

        panel = new JPanel();
        panel.add (etiket);
        panel.add (new JLayer<JFormattedTextField> (bi�imliMetinSat�r�, tabakaUI));
        panel.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        kutu.add (Box.createGlue());
        kutu.add (panel);

        // ���nc� zamansal veri giri�i...
        etiket = new JLabel ("Zaman gir:");
        etiket.setHorizontalAlignment (SwingConstants.RIGHT);
        etiket.setPreferredSize (etiketEbat�);

        DateFormat zamansalBi�im = DateFormat.getTimeInstance();
        bi�imliMetinSat�r� = new JFormattedTextField (zamansalBi�im);
        bi�imliMetinSat�r�.setColumns (16);
        bi�imliMetinSat�r�.setFocusLostBehavior (JFormattedTextField.PERSIST);
        bi�imliMetinSat�r�.setValue (new Date());

        panel = new JPanel();
        panel.add (etiket);
        panel.add (new JLayer<JFormattedTextField> (bi�imliMetinSat�r�, tabakaUI));
        panel.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        kutu.add (Box.createGlue());
        kutu.add (panel);

        return kutu;
    } // i�erikKomponentleriYarat() metodu sonu...
} // J5e_5 s�n�f� sonu...

class Ge�erlilemeTabakas�UI extends LayerUI<JFormattedTextField> {
    @Override
    public void paint (Graphics g, JComponent komponent) {
        super.paint (g, komponent);

        JLayer tabaka = (JLayer)komponent;
        JFormattedTextField bi�imliMetinSat�r� = (JFormattedTextField)tabaka.getView();
        if (!bi�imliMetinSat�r�.isEditValid()) {// Say�sal veri giri�leri ge�erliyse...
            Graphics2D g2 = (Graphics2D)g.create();
            // Hatal� veri giri�inde belirecek olan k�rm�z� zeminli beyaz X sembol�n� boyayal�m...
            g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int en = komponent.getWidth();
            int boy = komponent.getHeight();
            int ebat = 8;
            int bo�luk = 4;
            int x = en - bo�luk - ebat;
            int y = (boy - ebat) / 2;
            g2.setPaint (Color.RED);
            g2.fillRect (x, y, ebat + 1, ebat + 1);
            g2.setPaint (Color.WHITE);
            g2.drawLine (x, y, x + ebat, y + ebat);
            g2.drawLine (x, y + ebat, x + ebat, y);

            g2.dispose(); // Haz�rlanan g2 grafik sembol�n� f�rlatal�m/kural�m...
        } // if karar� sonu...
  } // paint(..) haz�r override metodu...
} // Ge�erlilemeTabakas�UI s�n�f� sonu...