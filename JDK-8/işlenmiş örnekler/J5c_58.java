// J5c_58.java: TabbedPaneDemo (FiþliPanoGösterisi) örneði.

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;

/* Gerekli resim dosyasý:
 *   resim/Orta.gif.
 */
public class J5c_58 extends JPanel {
    public J5c_58() {// Kurucu...
        super (new GridLayout (1, 1));
        
        JTabbedPane fiþliPano = new JTabbedPane();
        ImageIcon ikon = resimÝkonuYarat ("resim/Orta.gif");
        
        JComponent panel1 = fiþliPaneliYap ("Panel No:1");
        panel1.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        fiþliPano.addTab ("Fiþ 1", ikon, panel1, "Bir iþde kullanýlmayacak");
        fiþliPano.setMnemonicAt (0, KeyEvent.VK_1); // Alt_1...
        
        JComponent panel2 = fiþliPaneliYap ("Panel No:2");
        fiþliPano.addTab ("Fiþ 2", ikon, panel2, "Bir iþde iki misli de kullanýlmayacak");
        fiþliPano.setMnemonicAt (1, KeyEvent.VK_2);
        
        JComponent panel3 = fiþliPaneliYap ("Panel No:3");
        fiþliPano.addTab ("Fiþ 3", ikon, panel3, "Hala hiçbir iþde kullanýlmayacak");
        fiþliPano.setMnemonicAt (2, KeyEvent.VK_3);
        
        JComponent panel4 = fiþliPaneliYap ("Panel No:4 (410 x 50'lik tercihi ebata sahiptir.)");
        panel4.setPreferredSize (new Dimension (410, 50));
        fiþliPano.addTab ("Fiþ 4", ikon, panel4, "Katiyetle hiçbir iþde kullanýlmayacak");
        fiþliPano.setMnemonicAt (3, KeyEvent.VK_4);
        
        // Fiþli panoyu bu (super) üst panel'e ekleyelim...
        add (fiþliPano);
        
        // Kaydýrmalý fiþ serimine izin verelim...
        fiþliPano.setTabLayoutPolicy (JTabbedPane.SCROLL_TAB_LAYOUT);
    } // J5c_58() kurucusu sonu...
    
    protected JComponent fiþliPaneliYap (String metin) {
        JPanel panel = new JPanel (false);
        JLabel metinliEtiket = new JLabel (metin);
        metinliEtiket.setForeground (Color.WHITE);
        panel.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        metinliEtiket.setHorizontalAlignment (JLabel.CENTER);
        panel.setLayout (new GridLayout (1, 1));
        panel.add (metinliEtiket);

        return panel;
    } // fiþliPaneliYap(..) metodu sonu...
    
    protected static ImageIcon resimÝkonuYarat (String yol) {
        java.net.URL resimYureli = J5c_58.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...
    
    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Fiþli Pano Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_58(), BorderLayout.CENTER);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...
    
    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {// Koyu metal yazý fonu kullanýmý kapatýlýyor...
                javax.swing.UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
        }}); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_58 sýnýfý sonu...