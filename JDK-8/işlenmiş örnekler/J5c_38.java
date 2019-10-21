// J5c_38.java: PasswordDemo (ÞifreGösterisi) örneði.

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.util.Arrays;

/* JOptionPane mesaj kutusu tercihleri:
 * -1=JOptionPane.PLAIN_MESSAGE
 * 0=JOptionPane.ERROR_MESSAGE
 * 1=JOptionPane.INFORMATION_MESSAGE
 * 2=JOptionPane.WARNING_MESSAGE
 * 3=JOptionPane.QUESTION_MESSAGE
 */

public class J5c_38 extends JPanel implements ActionListener {
    private static String TAMAM = "tamam";
    private static String YARDIM = "yardým";

    private JFrame kontrolÇerçevesi; // Diyaloglarda gerekecek...
    private JPasswordField þifreSatýrý;

    public J5c_38(JFrame pen) {// Kurucu...
        // Varsayýlý FlowLayout yerleþimi kullanýlacak...
        kontrolÇerçevesi = pen;

        // Þifreyi, etiketini ve butonlarý yaratýp kuralým...
        þifreSatýrý = new JPasswordField (10);
        þifreSatýrý.setActionCommand (TAMAM);
        þifreSatýrý.addActionListener (this);
        þifreSatýrý.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        JLabel etiket = new JLabel ("Lütfen þifreyi girin: ");
        etiket.setLabelFor (þifreSatýrý);

        JComponent düðmelerKomponenti = düðmelerPaneliniYarat();

        // Hepsini serimleyelim...
        JPanel metinPaneli = new JPanel (new FlowLayout (FlowLayout.TRAILING));
        metinPaneli.add (etiket);
        metinPaneli.add (þifreSatýrý);
        metinPaneli.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        add (metinPaneli);
        add (düðmelerKomponenti);
        setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
    } // J5c_38(..) kurucusu sonu...

    protected JComponent düðmelerPaneliniYarat() {
        JPanel panel = new JPanel (new GridLayout (0,1)); // Düðmeler tek sütunda üst-üste olacak...

        JButton tamamDüðmesi = new JButton ("TAMAM");
        JButton yardýmDüðmesi = new JButton ("Yardým");

        tamamDüðmesi.setActionCommand (TAMAM);
        yardýmDüðmesi.setActionCommand (YARDIM);

        tamamDüðmesi.addActionListener (this);
        yardýmDüðmesi.addActionListener (this);

        panel.add (tamamDüðmesi);
        panel.add (yardýmDüðmesi);

        return panel;
    } // düðmelerPaneliniYarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (TAMAM.equals (komut)) {// Girilen þifreyi iþlemden geçirelim...
            char[] girilenÞifre = þifreSatýrý.getPassword();
            if (þifreDoðruMu (girilenÞifre)) JOptionPane.showMessageDialog (kontrolÇerçevesi, "BRAVO! Doðru þifreyi girdiniz.", "Aferin Mesajý", -1); // -1=JOptionPane.PLAIN_MESSAGE
            else JOptionPane.showMessageDialog (kontrolÇerçevesi, "Geçersiz þifre! Tekrar deneyin.",  "Hata Mesajý", 0); // 0=JOptionPane.ERROR_MESSAGE

            // Güvenlik için girilen þifreyi sýfýrlayalým...
            Arrays.fill (girilenÞifre, '0');

            þifreSatýrý.selectAll();
            odaklandýr();
        }else {// kullanýcý yardým isterse...
            JOptionPane.showMessageDialog (kontrolÇerçevesi,
                "Bu örnek programýn kaynak kodlarýndaki\n" +
                "\"doðruÞifre\" karakter dizisini araþtýrýn.\n" +
                "Veya The JavaTutorial (Java Öðretimi)'ýn\n" +
                "How to Use Password Fields (Þifre Alanlarý\n" +
                "Nasýl Kullanýlýr) kýsmýný okuyun.", "Yardým Mesajý", 1); //1=JOptionPane.INFORMATION_MESSAGE
        } // if-else kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    private static boolean þifreDoðruMu (char[] girilenÞifre) {
        boolean doðruMu = true;
        char[] doðruÞifre = {'M', 'e', 'r', 'h', 'a', 'b', 'a', ' ', 'J', 'a','v','a'};

        if (girilenÞifre.length != doðruÞifre.length) doðruMu = false;
        else doðruMu = Arrays.equals (girilenÞifre, doðruÞifre);

        Arrays.fill (doðruÞifre, '0'); // Sýfýrlayalým...

        return doðruMu;
    } // þifreDoðruMu(..) metodu sonu...

    protected void odaklandýr() {þifreSatýrý.requestFocusInWindow();}

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Þifre Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        final J5c_38 yeniÝçerikPanosu = new J5c_38 (çerçeve);
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);

        // Pencere ilk göründüðünde imleç odaklanmasý þifre giriþ komponentinde olmalýdýr...
        çerçeve.addWindowListener (new WindowAdapter() {
            public void windowActivated (WindowEvent olay) {yeniÝçerikPanosu.odaklandýr();}
        }); // çer.. ifadesi sonu...

        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_38 sýnýfý sonu...