// J5c_57.java: SplitPaneDividerDemo (BölmePanosuBölücüsüGösterimi) örneði.

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

/* Gereken java ve resim dosyalarý:
 *   J5c_57x.java=SizeDisplayer.java
 *   resim/Kedi.gif
 *   resim/Köpek.gif
 */
public class J5c_57 extends JPanel implements ActionListener {
    private JSplitPane bölmePanosu;
    
    public J5c_57() {
        super (new BorderLayout());

        Font yazýFonu = new Font ("Serif", Font.BOLD, 30);

        ImageIcon ikon = resimÝkonuYarat ("resim/Kedi.gif");
        J5c_57x solBölüm = new J5c_57x ("sol", ikon);
        solBölüm.setMinimumSize (new Dimension (40,40));
        solBölüm.setFont (yazýFonu);
        
        ikon = resimÝkonuYarat ("resim/Köpek.gif");
        J5c_57x saðBölüm = new J5c_57x ("sað", ikon);
        saðBölüm.setMinimumSize (new Dimension (60,60));
        saðBölüm.setFont (yazýFonu);
        
        bölmePanosu = new JSplitPane (
                JSplitPane.HORIZONTAL_SPLIT, // Yatay yanyana 2 bölüm...
                solBölüm, saðBölüm);
        bölmePanosu.setResizeWeight (0.5); // Etkisini anlayamadým...
        bölmePanosu.setOneTouchExpandable (true); // Düðmeli bölme çubuðu...
        bölmePanosu.setContinuousLayout (true);
        
        add (bölmePanosu, BorderLayout.CENTER);
        add (butonPaneliniYarat(), BorderLayout.PAGE_END);
    } // J5c_57() kurucusu sonu...

    private JComponent butonPaneliniYarat() {
        JPanel panel = new JPanel();
        JButton buton = new JButton ("Ýlk Ayarlar");
        buton.addActionListener (this); // Dinleyiciye duyarlý...
        panel.add (buton);
        panel.setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // Tüm renkler...
        return panel;
    } // butonPaneliniYarat() metodu sonu...
    
    // "Ýlk Ayarlar" butonu týklanmasýna duyarlý...
    public void actionPerformed (ActionEvent olay) {bölmePanosu.resetToPreferredSizes();}
    
    private static ImageIcon resimÝkonuYarat (String yol) {
        java.net.URL resimYureli = J5c_57.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Bölme Panosu Bölücüsü Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_57 yeniÝçerikPanosu = new J5c_57(); // Kurucuyu çaðýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_57 sýnýfý sonu...