// J5h_7.java: TextCutPaste (MetniKesYapýþtýr) örneði.

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

        J5h_7x aktarmaYönetimi = new J5h_7x(); // Aktarma yönetimini çaðýrýr...

        // 3 adet metin içarikli metin satýrý yaratalým...
        JPanel panel = new JPanel (new GridLayout (3, 1)); // Tek kolonda alt-alta 3 satýr...
        JTextField metinSatýrý = new JTextField ("Kes, kopyala ve yapýþtýr...", 30);
        metinSatýrý.setTransferHandler (aktarmaYönetimi);
        metinSatýrý.setDragEnabled (true); // Sürükle mümkün...
        panel.add (metinSatýrý);
        metinSatýrý.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)0.95));
        metinSatýrý = new JTextField ("veya sürükle ve býrak...", 30);
        metinSatýrý.setTransferHandler (aktarmaYönetimi);
        metinSatýrý.setDragEnabled (true);
        panel.add (metinSatýrý);
        metinSatýrý.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)0.95));
        metinSatýrý = new JTextField ("iþlemlerinde bu 3 metin satýrýný da kullanabilirsiniz.", 30);
        metinSatýrý.setTransferHandler (aktarmaYönetimi);
        metinSatýrý.setDragEnabled (true);
        panel.add (metinSatýrý);
        metinSatýrý.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)0.95));
        add (panel, BorderLayout.CENTER);
    } // J5h_7() kurucusu sonu...

    public JMenuBar menüÇubuðunuYarat() {
        JMenuBar menüÇubuðu = new JMenuBar();
        menüÇubuðu.setBackground (Color.BLACK);
        JMenu anaMenü = new JMenu ("Düzenle");
        anaMenü.setForeground (Color.WHITE);
        anaMenü.setMnemonic (KeyEvent.VK_D); // Alt-D

        JMenuItem menüBirimi = null;
        menüBirimi = new JMenuItem (new DefaultEditorKit.CutAction());
        menüBirimi.setText ("Kes");
        menüBirimi.setMnemonic (KeyEvent.VK_K); // Alt-K
        // Varsayýlý olarak Ctrl-C, Ctrl-X ve Ctrl-P kýsakesek tuþlarý da çalýþmaktadýr...
        //menüBirimi.setAccelerator (javax.swing.KeyStroke.getKeyStroke (java.awt.KeyEvent.VK_X,  ActionEvent.CTRL_MASK));
        anaMenü.add (menüBirimi);

        menüBirimi = new JMenuItem (new DefaultEditorKit.CopyAction());
        menüBirimi.setText ("Kopyala");
        menüBirimi.setMnemonic (KeyEvent.VK_O); // Alt-O
        anaMenü.add (menüBirimi);

        menüBirimi = new JMenuItem (new DefaultEditorKit.PasteAction());
        menüBirimi.setText ("Yapýþtýr");
        menüBirimi.setMnemonic (KeyEvent.VK_Y); // Alt-Y
        anaMenü.add (menüBirimi);

        menüÇubuðu.add (anaMenü);
        return menüÇubuðu;
    } // menüÇubuðunuYarat() metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Metni Kes Yapýþtýr");
        çerçeve.setDefaultCloseOperation (3); // 3= JFrame.EXIT_ON_CLOSE
        J5h_7 gösteri = new J5h_7(); // Kurucuyu çaðýrýr...
        çerçeve.setJMenuBar (gösteri.menüÇubuðunuYarat());
        gösteri.setOpaque (true);
        çerçeve.setContentPane (gösteri);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
            } // run() sicim hazýr metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_7 sýnýfý sonu...