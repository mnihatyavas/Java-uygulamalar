// J5f_9.java: FlowLayoutDemo (AkýþSerilimiGösterisi) örneði.

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
    JRadioButton saðdanSolaDüðmesi;
    JRadioButton soldanSaðaDüðmesi;
    FlowLayout akýþSerilimi = new FlowLayout();
    final String saðdanSola = "Saðdan sola";
    final String soldanSaða = "Soldan saða";
    JButton uygulaDüðmesi = new JButton ("Komponent dizilimine uygula");

    public J5f_9 (String ad) {super (ad);} // Kurucu...

    public void komponentleriKabaEkle (final Container kab) {
        final JPanel akýþSerilimPaneli = new JPanel();
        akýþSerilimPaneli.setLayout (akýþSerilimi);
        akýþSerilimi.setAlignment (FlowLayout.TRAILING);
        JPanel kontrolPaneli = new JPanel();
        kontrolPaneli.setLayout (new FlowLayout());

        soldanSaðaDüðmesi = new JRadioButton (soldanSaða);
        soldanSaðaDüðmesi.setActionCommand (soldanSaða);
        soldanSaðaDüðmesi.setSelected (true);
        saðdanSolaDüðmesi = new JRadioButton (saðdanSola);
        saðdanSolaDüðmesi.setActionCommand (saðdanSola);
        
        // Üst düðmeleri akýþ serilimi paneline (ardýþýk) ekleyelim...
        akýþSerilimPaneli.add (new JButton ("Düðme 1"));
        akýþSerilimPaneli.add (new JButton ("Düðme 2"));
        akýþSerilimPaneli.add (new JButton ("Düðme 3"));
        akýþSerilimPaneli.add (new JButton ("Uzun-Ýsimli Düðme 4"));
        akýþSerilimPaneli.add (new JButton ("5"));
        // Varsayýlý soldan saða seçili yapalým...
        akýþSerilimPaneli.setComponentOrientation (ComponentOrientation.LEFT_TO_RIGHT);        

        // Alt komponentleri kontrol paneline ekleyelim...
        final ButtonGroup grup = new ButtonGroup();
        grup.add (soldanSaðaDüðmesi);
        grup.add (saðdanSolaDüðmesi);
        kontrolPaneli.add (soldanSaðaDüðmesi);
        kontrolPaneli.add (saðdanSolaDüðmesi);
        kontrolPaneli.add (uygulaDüðmesi);

        // Uygula düðmesini üst butonlar dizilimi için dinleyiciye duyarlayalým...
        uygulaDüðmesi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                String komut = grup.getSelection().getActionCommand();
                if (komut.equals ("Soldan saða")) akýþSerilimPaneli.setComponentOrientation (ComponentOrientation.LEFT_TO_RIGHT);
                else akýþSerilimPaneli.setComponentOrientation (ComponentOrientation.RIGHT_TO_LEFT);

                akýþSerilimPaneli.validate();
                akýþSerilimPaneli.repaint();
            } // actionPerformed(..) hazýr metodu sonu...
        }); // uyg.. ifadesi sonu...

        akýþSerilimPaneli.setBackground (Color.CYAN);
        kontrolPaneli.setBackground (Color.BLUE);
        kab.add (akýþSerilimPaneli, BorderLayout.CENTER);
        kab.add (kontrolPaneli, BorderLayout.SOUTH); ;
    } // komponentleriKabaEkle(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        J5f_9 çerçeve = new J5f_9 ("Akýþ Serilimi Gösterisi"); // Kurucuyu çaðýrýr...
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.komponentleriKabaEkle (çerçeve.getContentPane());
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...
    
    public static void main(String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch bloðu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater(new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_9 sýnýfý sonu...