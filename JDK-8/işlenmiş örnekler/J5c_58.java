// J5c_58.java: TabbedPaneDemo (Fi�liPanoG�sterisi) �rne�i.

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

/* Gerekli resim dosyas�:
 *   resim/Orta.gif.
 */
public class J5c_58 extends JPanel {
    public J5c_58() {// Kurucu...
        super (new GridLayout (1, 1));
        
        JTabbedPane fi�liPano = new JTabbedPane();
        ImageIcon ikon = resim�konuYarat ("resim/Orta.gif");
        
        JComponent panel1 = fi�liPaneliYap ("Panel No:1");
        panel1.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        fi�liPano.addTab ("Fi� 1", ikon, panel1, "Bir i�de kullan�lmayacak");
        fi�liPano.setMnemonicAt (0, KeyEvent.VK_1); // Alt_1...
        
        JComponent panel2 = fi�liPaneliYap ("Panel No:2");
        fi�liPano.addTab ("Fi� 2", ikon, panel2, "Bir i�de iki misli de kullan�lmayacak");
        fi�liPano.setMnemonicAt (1, KeyEvent.VK_2);
        
        JComponent panel3 = fi�liPaneliYap ("Panel No:3");
        fi�liPano.addTab ("Fi� 3", ikon, panel3, "Hala hi�bir i�de kullan�lmayacak");
        fi�liPano.setMnemonicAt (2, KeyEvent.VK_3);
        
        JComponent panel4 = fi�liPaneliYap ("Panel No:4 (410 x 50'lik tercihi ebata sahiptir.)");
        panel4.setPreferredSize (new Dimension (410, 50));
        fi�liPano.addTab ("Fi� 4", ikon, panel4, "Katiyetle hi�bir i�de kullan�lmayacak");
        fi�liPano.setMnemonicAt (3, KeyEvent.VK_4);
        
        // Fi�li panoyu bu (super) �st panel'e ekleyelim...
        add (fi�liPano);
        
        // Kayd�rmal� fi� serimine izin verelim...
        fi�liPano.setTabLayoutPolicy (JTabbedPane.SCROLL_TAB_LAYOUT);
    } // J5c_58() kurucusu sonu...
    
    protected JComponent fi�liPaneliYap (String metin) {
        JPanel panel = new JPanel (false);
        JLabel metinliEtiket = new JLabel (metin);
        metinliEtiket.setForeground (Color.WHITE);
        panel.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        metinliEtiket.setHorizontalAlignment (JLabel.CENTER);
        panel.setLayout (new GridLayout (1, 1));
        panel.add (metinliEtiket);

        return panel;
    } // fi�liPaneliYap(..) metodu sonu...
    
    protected static ImageIcon resim�konuYarat (String yol) {
        java.net.URL resimYureli = J5c_58.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konuYarat(..) metodu sonu...
    
    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Fi�li Pano G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_58(), BorderLayout.CENTER);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...
    
    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {// Koyu metal yaz� fonu kullan�m� kapat�l�yor...
                javax.swing.UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
        }}); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_58 s�n�f� sonu...