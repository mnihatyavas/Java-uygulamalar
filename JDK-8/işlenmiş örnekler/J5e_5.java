// J5e_5.java: FieldValidator (MetinsatýrýGeçerlileyicisi) örneði.

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
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...

    public static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Matin Satýrý Geçerlileyicisi");
        JComponent içerik = içerikKomponentleriYarat();
        çerçeve.add (içerik);
        çerçeve.pack();
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    private static JComponent içerikKomponentleriYarat() {
        Dimension etiketEbatý = new Dimension (80, 20);
        Box kutu = Box.createVerticalBox(); // Yeni Ýçerik panosu komponenti kutusu...

        // Tüm metin satýrlarý için tek bir tabakaUI kuralým (UI:[Graphics]UnitInterface: [Grafik]BirimArayüzü)...
        LayerUI<JFormattedTextField> tabakaUI = new GeçerlilemeTabakasýUI();

        // Ýlk (tam/ondalýk) sayýsal veri giriþi...
        JLabel etiket = new JLabel ("Sayý gir:");
        etiket.setHorizontalAlignment (SwingConstants.RIGHT);
        etiket.setPreferredSize (etiketEbatý);

        NumberFormat sayýsalBiçim = NumberFormat.getInstance();
        JFormattedTextField biçimliMetinSatýrý = new JFormattedTextField (sayýsalBiçim);
        biçimliMetinSatýrý.setColumns (16);
        biçimliMetinSatýrý.setFocusLostBehavior (JFormattedTextField.PERSIST);
        biçimliMetinSatýrý.setValue (99.99);

        JPanel panel = new JPanel();
        panel.add (etiket);
        panel.add (new JLayer<JFormattedTextField> (biçimliMetinSatýrý, tabakaUI));
        panel.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        kutu.add (Box.createGlue()); // Boþluk...
        kutu.add (panel);

        // Ýkinci tarihsel veri giriþi...
        etiket = new JLabel ("Tarih gir:");
        etiket.setHorizontalAlignment (SwingConstants.RIGHT);
        etiket.setPreferredSize (etiketEbatý);

        DateFormat tarihselBiçim = DateFormat.getDateInstance();
        biçimliMetinSatýrý = new JFormattedTextField (tarihselBiçim);
        biçimliMetinSatýrý.setColumns (16);
        biçimliMetinSatýrý.setFocusLostBehavior (JFormattedTextField.PERSIST);
        biçimliMetinSatýrý.setValue (new Date());

        panel = new JPanel();
        panel.add (etiket);
        panel.add (new JLayer<JFormattedTextField> (biçimliMetinSatýrý, tabakaUI));
        panel.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        kutu.add (Box.createGlue());
        kutu.add (panel);

        // Üçüncü zamansal veri giriþi...
        etiket = new JLabel ("Zaman gir:");
        etiket.setHorizontalAlignment (SwingConstants.RIGHT);
        etiket.setPreferredSize (etiketEbatý);

        DateFormat zamansalBiçim = DateFormat.getTimeInstance();
        biçimliMetinSatýrý = new JFormattedTextField (zamansalBiçim);
        biçimliMetinSatýrý.setColumns (16);
        biçimliMetinSatýrý.setFocusLostBehavior (JFormattedTextField.PERSIST);
        biçimliMetinSatýrý.setValue (new Date());

        panel = new JPanel();
        panel.add (etiket);
        panel.add (new JLayer<JFormattedTextField> (biçimliMetinSatýrý, tabakaUI));
        panel.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        kutu.add (Box.createGlue());
        kutu.add (panel);

        return kutu;
    } // içerikKomponentleriYarat() metodu sonu...
} // J5e_5 sýnýfý sonu...

class GeçerlilemeTabakasýUI extends LayerUI<JFormattedTextField> {
    @Override
    public void paint (Graphics g, JComponent komponent) {
        super.paint (g, komponent);

        JLayer tabaka = (JLayer)komponent;
        JFormattedTextField biçimliMetinSatýrý = (JFormattedTextField)tabaka.getView();
        if (!biçimliMetinSatýrý.isEditValid()) {// Sayýsal veri giriþleri geçerliyse...
            Graphics2D g2 = (Graphics2D)g.create();
            // Hatalý veri giriþinde belirecek olan kýrmýzý zeminli beyaz X sembolünü boyayalým...
            g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int en = komponent.getWidth();
            int boy = komponent.getHeight();
            int ebat = 8;
            int boþluk = 4;
            int x = en - boþluk - ebat;
            int y = (boy - ebat) / 2;
            g2.setPaint (Color.RED);
            g2.fillRect (x, y, ebat + 1, ebat + 1);
            g2.setPaint (Color.WHITE);
            g2.drawLine (x, y, x + ebat, y + ebat);
            g2.drawLine (x, y + ebat, x + ebat, y);

            g2.dispose(); // Hazýrlanan g2 grafik sembolünü fýrlatalým/kuralým...
        } // if kararý sonu...
  } // paint(..) hazýr override metodu...
} // GeçerlilemeTabakasýUI sýnýfý sonu...