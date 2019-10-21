// J5c_38.java: PasswordDemo (�ifreG�sterisi) �rne�i.

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
    private static String YARDIM = "yard�m";

    private JFrame kontrol�er�evesi; // Diyaloglarda gerekecek...
    private JPasswordField �ifreSat�r�;

    public J5c_38(JFrame pen) {// Kurucu...
        // Varsay�l� FlowLayout yerle�imi kullan�lacak...
        kontrol�er�evesi = pen;

        // �ifreyi, etiketini ve butonlar� yarat�p kural�m...
        �ifreSat�r� = new JPasswordField (10);
        �ifreSat�r�.setActionCommand (TAMAM);
        �ifreSat�r�.addActionListener (this);
        �ifreSat�r�.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        JLabel etiket = new JLabel ("L�tfen �ifreyi girin: ");
        etiket.setLabelFor (�ifreSat�r�);

        JComponent d��melerKomponenti = d��melerPaneliniYarat();

        // Hepsini serimleyelim...
        JPanel metinPaneli = new JPanel (new FlowLayout (FlowLayout.TRAILING));
        metinPaneli.add (etiket);
        metinPaneli.add (�ifreSat�r�);
        metinPaneli.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        add (metinPaneli);
        add (d��melerKomponenti);
        setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
    } // J5c_38(..) kurucusu sonu...

    protected JComponent d��melerPaneliniYarat() {
        JPanel panel = new JPanel (new GridLayout (0,1)); // D��meler tek s�tunda �st-�ste olacak...

        JButton tamamD��mesi = new JButton ("TAMAM");
        JButton yard�mD��mesi = new JButton ("Yard�m");

        tamamD��mesi.setActionCommand (TAMAM);
        yard�mD��mesi.setActionCommand (YARDIM);

        tamamD��mesi.addActionListener (this);
        yard�mD��mesi.addActionListener (this);

        panel.add (tamamD��mesi);
        panel.add (yard�mD��mesi);

        return panel;
    } // d��melerPaneliniYarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (TAMAM.equals (komut)) {// Girilen �ifreyi i�lemden ge�irelim...
            char[] girilen�ifre = �ifreSat�r�.getPassword();
            if (�ifreDo�ruMu (girilen�ifre)) JOptionPane.showMessageDialog (kontrol�er�evesi, "BRAVO! Do�ru �ifreyi girdiniz.", "Aferin Mesaj�", -1); // -1=JOptionPane.PLAIN_MESSAGE
            else JOptionPane.showMessageDialog (kontrol�er�evesi, "Ge�ersiz �ifre! Tekrar deneyin.",  "Hata Mesaj�", 0); // 0=JOptionPane.ERROR_MESSAGE

            // G�venlik i�in girilen �ifreyi s�f�rlayal�m...
            Arrays.fill (girilen�ifre, '0');

            �ifreSat�r�.selectAll();
            odakland�r();
        }else {// kullan�c� yard�m isterse...
            JOptionPane.showMessageDialog (kontrol�er�evesi,
                "Bu �rnek program�n kaynak kodlar�ndaki\n" +
                "\"do�ru�ifre\" karakter dizisini ara�t�r�n.\n" +
                "Veya The JavaTutorial (Java ��retimi)'�n\n" +
                "How to Use Password Fields (�ifre Alanlar�\n" +
                "Nas�l Kullan�l�r) k�sm�n� okuyun.", "Yard�m Mesaj�", 1); //1=JOptionPane.INFORMATION_MESSAGE
        } // if-else karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    private static boolean �ifreDo�ruMu (char[] girilen�ifre) {
        boolean do�ruMu = true;
        char[] do�ru�ifre = {'M', 'e', 'r', 'h', 'a', 'b', 'a', ' ', 'J', 'a','v','a'};

        if (girilen�ifre.length != do�ru�ifre.length) do�ruMu = false;
        else do�ruMu = Arrays.equals (girilen�ifre, do�ru�ifre);

        Arrays.fill (do�ru�ifre, '0'); // S�f�rlayal�m...

        return do�ruMu;
    } // �ifreDo�ruMu(..) metodu sonu...

    protected void odakland�r() {�ifreSat�r�.requestFocusInWindow();}

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("�ifre G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        final J5c_38 yeni��erikPanosu = new J5c_38 (�er�eve);
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);

        // Pencere ilk g�r�nd���nde imle� odaklanmas� �ifre giri� komponentinde olmal�d�r...
        �er�eve.addWindowListener (new WindowAdapter() {
            public void windowActivated (WindowEvent olay) {yeni��erikPanosu.odakland�r();}
        }); // �er.. ifadesi sonu...

        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_38 s�n�f� sonu...