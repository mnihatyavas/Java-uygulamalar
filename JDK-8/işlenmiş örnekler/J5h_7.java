// J5h_7.java: TextCutPaste (MetniKesYap��t�r) �rne�i.

import java.awt.Color; 
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.DefaultEditorKit;

// Gereken dosya: J5h_7x.java = TextTransferHandler.java
public class J5h_7 extends JPanel {
    public J5h_7() {// Kurucu...
        super (new BorderLayout());

        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));

        J5h_7x aktarmaY�netimi = new J5h_7x(); // Aktarma y�netimini �a��r�r...

        // 3 adet metin i�arikli metin sat�r� yaratal�m...
        JPanel panel = new JPanel (new GridLayout (3, 1)); // Tek kolonda alt-alta 3 sat�r...
        JTextField metinSat�r� = new JTextField ("Kes, kopyala ve yap��t�r...", 30);
        metinSat�r�.setTransferHandler (aktarmaY�netimi);
        metinSat�r�.setDragEnabled (true); // S�r�kle m�mk�n...
        panel.add (metinSat�r�);
        metinSat�r�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)0.95));
        metinSat�r� = new JTextField ("veya s�r�kle ve b�rak...", 30);
        metinSat�r�.setTransferHandler (aktarmaY�netimi);
        metinSat�r�.setDragEnabled (true);
        panel.add (metinSat�r�);
        metinSat�r�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)0.95));
        metinSat�r� = new JTextField ("i�lemlerinde bu 3 metin sat�r�n� da kullanabilirsiniz.", 30);
        metinSat�r�.setTransferHandler (aktarmaY�netimi);
        metinSat�r�.setDragEnabled (true);
        panel.add (metinSat�r�);
        metinSat�r�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)0.95));
        add (panel, BorderLayout.CENTER);
    } // J5h_7() kurucusu sonu...

    public JMenuBar men��ubu�unuYarat() {
        JMenuBar men��ubu�u = new JMenuBar();
        men��ubu�u.setBackground (Color.BLACK);
        JMenu anaMen� = new JMenu ("D�zenle");
        anaMen�.setForeground (Color.WHITE);
        anaMen�.setMnemonic (KeyEvent.VK_D); // Alt-D

        JMenuItem men�Birimi = null;
        men�Birimi = new JMenuItem (new DefaultEditorKit.CutAction());
        men�Birimi.setText ("Kes");
        men�Birimi.setMnemonic (KeyEvent.VK_K); // Alt-K
        // Varsay�l� olarak Ctrl-C, Ctrl-X ve Ctrl-P k�sakesek tu�lar� da �al��maktad�r...
        //men�Birimi.setAccelerator (javax.swing.KeyStroke.getKeyStroke (java.awt.KeyEvent.VK_X,  ActionEvent.CTRL_MASK));
        anaMen�.add (men�Birimi);

        men�Birimi = new JMenuItem (new DefaultEditorKit.CopyAction());
        men�Birimi.setText ("Kopyala");
        men�Birimi.setMnemonic (KeyEvent.VK_O); // Alt-O
        anaMen�.add (men�Birimi);

        men�Birimi = new JMenuItem (new DefaultEditorKit.PasteAction());
        men�Birimi.setText ("Yap��t�r");
        men�Birimi.setMnemonic (KeyEvent.VK_Y); // Alt-Y
        anaMen�.add (men�Birimi);

        men��ubu�u.add (anaMen�);
        return men��ubu�u;
    } // men��ubu�unuYarat() metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Metni Kes Yap��t�r");
        �er�eve.setDefaultCloseOperation (3); // 3= JFrame.EXIT_ON_CLOSE
        J5h_7 g�steri = new J5h_7(); // Kurucuyu �a��r�r...
        �er�eve.setJMenuBar (g�steri.men��ubu�unuYarat());
        g�steri.setOpaque (true);
        �er�eve.setContentPane (g�steri);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
            } // run() sicim haz�r metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_7 s�n�f� sonu...