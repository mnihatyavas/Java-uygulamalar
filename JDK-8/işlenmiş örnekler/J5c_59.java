// J5c_59.java: TabComponentsDemo (Fi�KomponentleriG�sterisi) �rne�i.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/* Gereken java dosyas�:
 *   J5c_59x.java=ButtonTabComponent.java
 */
public class J5c_59 extends JFrame {    
    private final int fi�Say�s� = 7;
    private final JTabbedPane fi�liPano = new JTabbedPane();
    private JMenuItem kapatD��meliBirim;
    private JMenuItem yanyanaSerilimBirimi;
    
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE);
                new J5c_59 ("Fi� Komponentleri G�sterisi").g�sterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
    
    public J5c_59 (String ba�l�k) {
        super (ba�l�k); // extends JFrame'li s�n�f nesnesi �er�evesi...
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        men�y�Ba�lat();
        add (fi�liPano); // Fi�li panoyu �er�eveye ekle...
    } // J5c_59(..) kurucusu sonu...
    
    public void g�sterGUI() {
        fi�liPano.removeAll(); // Fi�li pano yeni ba�tan kuruluyor...
        for (int i = 0; i < fi�Say�s�; i++) {
            String ba�l�k = "Fi�: " + i;
            fi�liPano.add (ba�l�k, new JLabel (ba�l�k));
            fi�KomponentiniBa�lat (i);
        } // for d�ng�s� sonu...
        kapatD��meliBirim.setSelected (true);
        fi�liPano.setTabLayoutPolicy (JTabbedPane.WRAP_TAB_LAYOUT);
        yanyanaSerilimBirimi.setSelected (false);
        setSize (new Dimension (400, 200));
        setLocationRelativeTo (null); // Ekran� ortala...
        setVisible (true);
        //setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // T�m renkler...
    } // g�sterGUI() metodu sonu...
    
    
    private void fi�KomponentiniBa�lat (int i) {
        fi�liPano.setTabComponentAt (i, new J5c_59x (fi�liPano));
        fi�liPano.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
    } // fi�KomponentiniBa�lat(..) metodu sonu...

    // Men� �ubu�umuzu kural�m...
    private void men�y�Ba�lat() {
        JMenuBar men��ubu�u = new JMenuBar();

        // Se�enekler men�s�n� yarat�p kural�m...
        kapatD��meliBirim = new JCheckBoxMenuItem ("KapatD��meli Fi� Kullan", true);
        kapatD��meliBirim.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_K, InputEvent.ALT_MASK));
        kapatD��meliBirim.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                for (int i = 0; i < fi�liPano.getTabCount(); i++) {
                    if (kapatD��meliBirim.isSelected()) fi�KomponentiniBa�lat (i);
                    else fi�liPano.setTabComponentAt (i, null);
                } // for d�ng�s� sonu...
            } // actionPerformed(..) haz�r metodu sonu...
        }); // kap.. ifadesi sonu...

        yanyanaSerilimBirimi = new JCheckBoxMenuItem ("Yanyana Serilimi Kur");
        yanyanaSerilimBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_Y, InputEvent.ALT_MASK));
        yanyanaSerilimBirimi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (fi�liPano.getTabLayoutPolicy() == JTabbedPane.WRAP_TAB_LAYOUT)
                    fi�liPano.setTabLayoutPolicy (JTabbedPane.SCROLL_TAB_LAYOUT); // Yanyana serilim...
                else fi�liPano.setTabLayoutPolicy (JTabbedPane.WRAP_TAB_LAYOUT); // Altasar�ml� serilim...
            } // actionPerformed(..) haz�r metodu sonu...
        }); // yan.. ifadesi sonu...

        JMenuItem ilkayarlarBirimi = new JMenuItem ("Fi�li Pano �lkayarlar�");
        ilkayarlarBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_L, InputEvent.ALT_MASK));
        ilkayarlarBirimi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {g�sterGUI();}
        }); // ilk.. ifadesi sonu...
       
        JMenu se�eneklerMen�s� = new JMenu ("Se�enekler");
        se�eneklerMen�s�.add (kapatD��meliBirim);
        se�eneklerMen�s�.add (yanyanaSerilimBirimi);
        se�eneklerMen�s�.add (ilkayarlarBirimi);
        se�eneklerMen�s�.setForeground (Color.WHITE);

        men��ubu�u.add (se�eneklerMen�s�);
        men��ubu�u.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        setJMenuBar (men��ubu�u);
    } // men�y�Ba�lat() metodu sonu...
} // J5c_59 s�n�f� sonu...