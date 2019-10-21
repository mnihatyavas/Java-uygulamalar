// J5i_7.java: KeyEventDemo (Tu�Olay�G�sterisi) �rne�i.

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
    JTextArea kay�tAlan�;
    JTextField tu�lamaSat�r�;
    static final String yeniSat�r = System.getProperty ("line.separator");

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace(); // java.lang.*
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch.. blo�u sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        J5i_7 �er�eve = new J5i_7 ("Tu� Olay� G�sterisi"); // Kurucuyu �a��r�r...
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.par�alar�PanoyaEkle();
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public J5i_7(String ad) {super (ad);} // Kurucu...

    private void par�alar�PanoyaEkle() {
        JButton temizleD��mesi = new JButton ("Kay�tlar� Temizle");
        temizleD��mesi.addActionListener (this); // Dinleyiciye duyarl�...

        tu�lamaSat�r� = new JTextField (20);
        tu�lamaSat�r�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f) );
        tu�lamaSat�r�.addKeyListener (this); // Tu� dinleyicisine duyarl�...

        // (Shift/)Tab ile odaklanma ge�i�ini kapat�p, sadece tu�lama sat�r�nda kalal�m...
        tu�lamaSat�r�.setFocusTraversalKeysEnabled (false);

        kay�tAlan� = new JTextArea();
        kay�tAlan�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f) );
        kay�tAlan�.setForeground (Color.WHITE);
        kay�tAlan�.setEditable (false); // Metin alan� m�dahalesiz...
        JScrollPane kayd�ra� = new JScrollPane (kay�tAlan�);
        kayd�ra�.setPreferredSize (new Dimension (375, 125));

        getContentPane().add (tu�lamaSat�r�, BorderLayout.PAGE_START);
        getContentPane().add (kayd�ra�, BorderLayout.CENTER);
        getContentPane().add (temizleD��mesi, BorderLayout.PAGE_END);
    } // par�alar�PanoyaEkle() metodu sonu...

    // Temizle d��mesine t�klamay� y�netelim...
    public void actionPerformed (ActionEvent olay) {
        kay�tAlan�.setText ("");
        tu�lamaSat�r�.setText ("");
        tu�lamaSat�r�.requestFocusInWindow();
    } // actionPerformed(..) haz�r metodu sonu...

    // Tu�a bas�ld�, yaz�ld� ve b�rak�ld� olaylar�n� y�netelim...
    public void keyPressed (KeyEvent olay) {tu�Mesaj�(olay, "TU� BASILDI:");}
    public void keyTyped (KeyEvent olay) {tu�Mesaj� (olay, "TU� YAZILDI:");}    
    public void keyReleased (KeyEvent olay) {tu�Mesaj� (olay, "TU� BIRAKILDI:");}

    // Shift ve sonras� bas�lacak tu�lar� da g�stersin...
    private void tu�Mesaj� (KeyEvent olay, String tu�Durumu) {
        int olayKimli�i = olay.getID();
        String tu�Dizgesi;
        if (olayKimli�i == KeyEvent.KEY_TYPED) {
            char krk = olay.getKeyChar();
            tu�Dizgesi = "tu� karakteri = '" + krk + "'";
        }else {int tu�Kodu = olay.getKeyCode();
            tu�Dizgesi = "tu� kodu = " + tu�Kodu + " (" + KeyEvent.getKeyText (tu�Kodu) + ")";
        } // else karar� sonu...

        int bile�ikTu�Kodu = olay.getModifiersEx();
        String bile�ikTu�Dizgesi = "bile�ik tu� kodu = " + bile�ikTu�Kodu;
        String ge�iciDizge = KeyEvent.getModifiersExText (bile�ikTu�Kodu);
        if (ge�iciDizge.length() > 0) bile�ikTu�Dizgesi += " (" + ge�iciDizge + ")";
        else bile�ikTu�Dizgesi += " (bile�ik tu�a bas�lmad�)";

        String hareketDizgesi = "aksiyon tu�u mu? ";
        if (olay.isActionKey()) hareketDizgesi += "EVET";
        else hareketDizgesi += "HAYIR";

        String konumDizgesi = "tu� konumu: ";
        int konum = olay.getKeyLocation();
        if (konum == KeyEvent.KEY_LOCATION_STANDARD) konumDizgesi += "standart";
        else if (konum == KeyEvent.KEY_LOCATION_LEFT) konumDizgesi += "sol";
        else if (konum == KeyEvent.KEY_LOCATION_RIGHT) konumDizgesi += "sa�";
        else if (konum == KeyEvent.KEY_LOCATION_NUMPAD) konumDizgesi += "say�lar";
        else // (konum == KeyEvent.KEY_LOCATION_UNKNOWN)
            konumDizgesi += "bilinmiyor";

        kay�tAlan�.append (tu�Durumu + yeniSat�r
                + "    " + tu�Dizgesi + yeniSat�r
                + "    " + bile�ikTu�Dizgesi + yeniSat�r
                + "    " + hareketDizgesi + yeniSat�r
                + "    " + konumDizgesi + yeniSat�r);
        kay�tAlan�.setCaretPosition (kay�tAlan�.getDocument().getLength());
    } // tu�Mesaj�(..) metodu sonu...
} // J5i_7 s�n�f� sonu...