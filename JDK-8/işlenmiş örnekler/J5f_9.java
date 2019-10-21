// J5f_9.java: FlowLayoutDemo (Ak��SerilimiG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

public class J5f_9 extends JFrame{
    JRadioButton sa�danSolaD��mesi;
    JRadioButton soldanSa�aD��mesi;
    FlowLayout ak��Serilimi = new FlowLayout();
    final String sa�danSola = "Sa�dan sola";
    final String soldanSa�a = "Soldan sa�a";
    JButton uygulaD��mesi = new JButton ("Komponent dizilimine uygula");

    public J5f_9 (String ad) {super (ad);} // Kurucu...

    public void komponentleriKabaEkle (final Container kab) {
        final JPanel ak��SerilimPaneli = new JPanel();
        ak��SerilimPaneli.setLayout (ak��Serilimi);
        ak��Serilimi.setAlignment (FlowLayout.TRAILING);
        JPanel kontrolPaneli = new JPanel();
        kontrolPaneli.setLayout (new FlowLayout());

        soldanSa�aD��mesi = new JRadioButton (soldanSa�a);
        soldanSa�aD��mesi.setActionCommand (soldanSa�a);
        soldanSa�aD��mesi.setSelected (true);
        sa�danSolaD��mesi = new JRadioButton (sa�danSola);
        sa�danSolaD��mesi.setActionCommand (sa�danSola);
        
        // �st d��meleri ak�� serilimi paneline (ard���k) ekleyelim...
        ak��SerilimPaneli.add (new JButton ("D��me 1"));
        ak��SerilimPaneli.add (new JButton ("D��me 2"));
        ak��SerilimPaneli.add (new JButton ("D��me 3"));
        ak��SerilimPaneli.add (new JButton ("Uzun-�simli D��me 4"));
        ak��SerilimPaneli.add (new JButton ("5"));
        // Varsay�l� soldan sa�a se�ili yapal�m...
        ak��SerilimPaneli.setComponentOrientation (ComponentOrientation.LEFT_TO_RIGHT);        

        // Alt komponentleri kontrol paneline ekleyelim...
        final ButtonGroup grup = new ButtonGroup();
        grup.add (soldanSa�aD��mesi);
        grup.add (sa�danSolaD��mesi);
        kontrolPaneli.add (soldanSa�aD��mesi);
        kontrolPaneli.add (sa�danSolaD��mesi);
        kontrolPaneli.add (uygulaD��mesi);

        // Uygula d��mesini �st butonlar dizilimi i�in dinleyiciye duyarlayal�m...
        uygulaD��mesi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                String komut = grup.getSelection().getActionCommand();
                if (komut.equals ("Soldan sa�a")) ak��SerilimPaneli.setComponentOrientation (ComponentOrientation.LEFT_TO_RIGHT);
                else ak��SerilimPaneli.setComponentOrientation (ComponentOrientation.RIGHT_TO_LEFT);

                ak��SerilimPaneli.validate();
                ak��SerilimPaneli.repaint();
            } // actionPerformed(..) haz�r metodu sonu...
        }); // uyg.. ifadesi sonu...

        ak��SerilimPaneli.setBackground (Color.CYAN);
        kontrolPaneli.setBackground (Color.BLUE);
        kab.add (ak��SerilimPaneli, BorderLayout.CENTER);
        kab.add (kontrolPaneli, BorderLayout.SOUTH); ;
    } // komponentleriKabaEkle(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        J5f_9 �er�eve = new J5f_9 ("Ak�� Serilimi G�sterisi"); // Kurucuyu �a��r�r...
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.komponentleriKabaEkle (�er�eve.getContentPane());
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...
    
    public static void main(String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch blo�u sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater(new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_9 s�n�f� sonu...