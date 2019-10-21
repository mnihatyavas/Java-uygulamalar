// J5c_59.java: TabComponentsDemo (FiþKomponentleriGösterisi) örneði.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/* Gereken java dosyasý:
 *   J5c_59x.java=ButtonTabComponent.java
 */
public class J5c_59 extends JFrame {    
    private final int fiþSayýsý = 7;
    private final JTabbedPane fiþliPano = new JTabbedPane();
    private JMenuItem kapatDüðmeliBirim;
    private JMenuItem yanyanaSerilimBirimi;
    
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE);
                new J5c_59 ("Fiþ Komponentleri Gösterisi").gösterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
    
    public J5c_59 (String baþlýk) {
        super (baþlýk); // extends JFrame'li sýnýf nesnesi çerçevesi...
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        menüyüBaþlat();
        add (fiþliPano); // Fiþli panoyu çerçeveye ekle...
    } // J5c_59(..) kurucusu sonu...
    
    public void gösterGUI() {
        fiþliPano.removeAll(); // Fiþli pano yeni baþtan kuruluyor...
        for (int i = 0; i < fiþSayýsý; i++) {
            String baþlýk = "Fiþ: " + i;
            fiþliPano.add (baþlýk, new JLabel (baþlýk));
            fiþKomponentiniBaþlat (i);
        } // for döngüsü sonu...
        kapatDüðmeliBirim.setSelected (true);
        fiþliPano.setTabLayoutPolicy (JTabbedPane.WRAP_TAB_LAYOUT);
        yanyanaSerilimBirimi.setSelected (false);
        setSize (new Dimension (400, 200));
        setLocationRelativeTo (null); // Ekraný ortala...
        setVisible (true);
        //setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // Tüm renkler...
    } // gösterGUI() metodu sonu...
    
    
    private void fiþKomponentiniBaþlat (int i) {
        fiþliPano.setTabComponentAt (i, new J5c_59x (fiþliPano));
        fiþliPano.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
    } // fiþKomponentiniBaþlat(..) metodu sonu...

    // Menü çubuðumuzu kuralým...
    private void menüyüBaþlat() {
        JMenuBar menüÇubuðu = new JMenuBar();

        // Seçenekler menüsünü yaratýp kuralým...
        kapatDüðmeliBirim = new JCheckBoxMenuItem ("KapatDüðmeli Fiþ Kullan", true);
        kapatDüðmeliBirim.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_K, InputEvent.ALT_MASK));
        kapatDüðmeliBirim.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                for (int i = 0; i < fiþliPano.getTabCount(); i++) {
                    if (kapatDüðmeliBirim.isSelected()) fiþKomponentiniBaþlat (i);
                    else fiþliPano.setTabComponentAt (i, null);
                } // for döngüsü sonu...
            } // actionPerformed(..) hazýr metodu sonu...
        }); // kap.. ifadesi sonu...

        yanyanaSerilimBirimi = new JCheckBoxMenuItem ("Yanyana Serilimi Kur");
        yanyanaSerilimBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_Y, InputEvent.ALT_MASK));
        yanyanaSerilimBirimi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (fiþliPano.getTabLayoutPolicy() == JTabbedPane.WRAP_TAB_LAYOUT)
                    fiþliPano.setTabLayoutPolicy (JTabbedPane.SCROLL_TAB_LAYOUT); // Yanyana serilim...
                else fiþliPano.setTabLayoutPolicy (JTabbedPane.WRAP_TAB_LAYOUT); // Altasarýmlý serilim...
            } // actionPerformed(..) hazýr metodu sonu...
        }); // yan.. ifadesi sonu...

        JMenuItem ilkayarlarBirimi = new JMenuItem ("Fiþli Pano Ýlkayarlarý");
        ilkayarlarBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_L, InputEvent.ALT_MASK));
        ilkayarlarBirimi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {gösterGUI();}
        }); // ilk.. ifadesi sonu...
       
        JMenu seçeneklerMenüsü = new JMenu ("Seçenekler");
        seçeneklerMenüsü.add (kapatDüðmeliBirim);
        seçeneklerMenüsü.add (yanyanaSerilimBirimi);
        seçeneklerMenüsü.add (ilkayarlarBirimi);
        seçeneklerMenüsü.setForeground (Color.WHITE);

        menüÇubuðu.add (seçeneklerMenüsü);
        menüÇubuðu.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        setJMenuBar (menüÇubuðu);
    } // menüyüBaþlat() metodu sonu...
} // J5c_59 sýnýfý sonu...