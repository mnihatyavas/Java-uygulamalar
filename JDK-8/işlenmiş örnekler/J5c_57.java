// J5c_57.java: SplitPaneDividerDemo (B�lmePanosuB�l�c�s�G�sterimi) �rne�i.

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import java.beans.*;

/* Gereken java ve resim dosyalar�:
 *   J5c_57x.java=SizeDisplayer.java
 *   resim/Kedi.gif
 *   resim/K�pek.gif
 */
public class J5c_57 extends JPanel implements ActionListener {
    private JSplitPane b�lmePanosu;
    
    public J5c_57() {
        super (new BorderLayout());

        Font yaz�Fonu = new Font ("Serif", Font.BOLD, 30);

        ImageIcon ikon = resim�konuYarat ("resim/Kedi.gif");
        J5c_57x solB�l�m = new J5c_57x ("sol", ikon);
        solB�l�m.setMinimumSize (new Dimension (40,40));
        solB�l�m.setFont (yaz�Fonu);
        
        ikon = resim�konuYarat ("resim/K�pek.gif");
        J5c_57x sa�B�l�m = new J5c_57x ("sa�", ikon);
        sa�B�l�m.setMinimumSize (new Dimension (60,60));
        sa�B�l�m.setFont (yaz�Fonu);
        
        b�lmePanosu = new JSplitPane (
                JSplitPane.HORIZONTAL_SPLIT, // Yatay yanyana 2 b�l�m...
                solB�l�m, sa�B�l�m);
        b�lmePanosu.setResizeWeight (0.5); // Etkisini anlayamad�m...
        b�lmePanosu.setOneTouchExpandable (true); // D��meli b�lme �ubu�u...
        b�lmePanosu.setContinuousLayout (true);
        
        add (b�lmePanosu, BorderLayout.CENTER);
        add (butonPaneliniYarat(), BorderLayout.PAGE_END);
    } // J5c_57() kurucusu sonu...

    private JComponent butonPaneliniYarat() {
        JPanel panel = new JPanel();
        JButton buton = new JButton ("�lk Ayarlar");
        buton.addActionListener (this); // Dinleyiciye duyarl�...
        panel.add (buton);
        panel.setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // T�m renkler...
        return panel;
    } // butonPaneliniYarat() metodu sonu...
    
    // "�lk Ayarlar" butonu t�klanmas�na duyarl�...
    public void actionPerformed (ActionEvent olay) {b�lmePanosu.resetToPreferredSizes();}
    
    private static ImageIcon resim�konuYarat (String yol) {
        java.net.URL resimYureli = J5c_57.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("B�lme Panosu B�l�c�s� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_57 yeni��erikPanosu = new J5c_57(); // Kurucuyu �a��r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_57 s�n�f� sonu...