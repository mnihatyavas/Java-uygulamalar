// J5i_7.java: KeyEventDemo (TuþOlayýGösterisi) örneði.

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class J5i_7 extends JFrame implements KeyListener,  ActionListener {
    JTextArea kayýtAlaný;
    JTextField tuþlamaSatýrý;
    static final String yeniSatýr = System.getProperty ("line.separator");

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace(); // java.lang.*
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch.. bloðu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        J5i_7 çerçeve = new J5i_7 ("Tuþ Olayý Gösterisi"); // Kurucuyu çaðýrýr...
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.parçalarýPanoyaEkle();
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public J5i_7(String ad) {super (ad);} // Kurucu...

    private void parçalarýPanoyaEkle() {
        JButton temizleDüðmesi = new JButton ("Kayýtlarý Temizle");
        temizleDüðmesi.addActionListener (this); // Dinleyiciye duyarlý...

        tuþlamaSatýrý = new JTextField (20);
        tuþlamaSatýrý.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f) );
        tuþlamaSatýrý.addKeyListener (this); // Tuþ dinleyicisine duyarlý...

        // (Shift/)Tab ile odaklanma geçiþini kapatýp, sadece tuþlama satýrýnda kalalým...
        tuþlamaSatýrý.setFocusTraversalKeysEnabled (false);

        kayýtAlaný = new JTextArea();
        kayýtAlaný.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f) );
        kayýtAlaný.setForeground (Color.WHITE);
        kayýtAlaný.setEditable (false); // Metin alaný müdahalesiz...
        JScrollPane kaydýraç = new JScrollPane (kayýtAlaný);
        kaydýraç.setPreferredSize (new Dimension (375, 125));

        getContentPane().add (tuþlamaSatýrý, BorderLayout.PAGE_START);
        getContentPane().add (kaydýraç, BorderLayout.CENTER);
        getContentPane().add (temizleDüðmesi, BorderLayout.PAGE_END);
    } // parçalarýPanoyaEkle() metodu sonu...

    // Temizle düðmesine týklamayý yönetelim...
    public void actionPerformed (ActionEvent olay) {
        kayýtAlaný.setText ("");
        tuþlamaSatýrý.setText ("");
        tuþlamaSatýrý.requestFocusInWindow();
    } // actionPerformed(..) hazýr metodu sonu...

    // Tuþa basýldý, yazýldý ve býrakýldý olaylarýný yönetelim...
    public void keyPressed (KeyEvent olay) {tuþMesajý(olay, "TUÞ BASILDI:");}
    public void keyTyped (KeyEvent olay) {tuþMesajý (olay, "TUÞ YAZILDI:");}    
    public void keyReleased (KeyEvent olay) {tuþMesajý (olay, "TUÞ BIRAKILDI:");}

    // Shift ve sonrasý basýlacak tuþlarý da göstersin...
    private void tuþMesajý (KeyEvent olay, String tuþDurumu) {
        int olayKimliði = olay.getID();
        String tuþDizgesi;
        if (olayKimliði == KeyEvent.KEY_TYPED) {
            char krk = olay.getKeyChar();
            tuþDizgesi = "tuþ karakteri = '" + krk + "'";
        }else {int tuþKodu = olay.getKeyCode();
            tuþDizgesi = "tuþ kodu = " + tuþKodu + " (" + KeyEvent.getKeyText (tuþKodu) + ")";
        } // else kararý sonu...

        int bileþikTuþKodu = olay.getModifiersEx();
        String bileþikTuþDizgesi = "bileþik tuþ kodu = " + bileþikTuþKodu;
        String geçiciDizge = KeyEvent.getModifiersExText (bileþikTuþKodu);
        if (geçiciDizge.length() > 0) bileþikTuþDizgesi += " (" + geçiciDizge + ")";
        else bileþikTuþDizgesi += " (bileþik tuþa basýlmadý)";

        String hareketDizgesi = "aksiyon tuþu mu? ";
        if (olay.isActionKey()) hareketDizgesi += "EVET";
        else hareketDizgesi += "HAYIR";

        String konumDizgesi = "tuþ konumu: ";
        int konum = olay.getKeyLocation();
        if (konum == KeyEvent.KEY_LOCATION_STANDARD) konumDizgesi += "standart";
        else if (konum == KeyEvent.KEY_LOCATION_LEFT) konumDizgesi += "sol";
        else if (konum == KeyEvent.KEY_LOCATION_RIGHT) konumDizgesi += "sað";
        else if (konum == KeyEvent.KEY_LOCATION_NUMPAD) konumDizgesi += "sayýlar";
        else // (konum == KeyEvent.KEY_LOCATION_UNKNOWN)
            konumDizgesi += "bilinmiyor";

        kayýtAlaný.append (tuþDurumu + yeniSatýr
                + "    " + tuþDizgesi + yeniSatýr
                + "    " + bileþikTuþDizgesi + yeniSatýr
                + "    " + hareketDizgesi + yeniSatýr
                + "    " + konumDizgesi + yeniSatýr);
        kayýtAlaný.setCaretPosition (kayýtAlaný.getDocument().getLength());
    } // tuþMesajý(..) metodu sonu...
} // J5i_7 sýnýfý sonu...