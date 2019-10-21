// J5c_14.java: FileChooserDemo (DosyaSe�iciG�sterimi) �rne�i.

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.io.File;

/* �u resim dosyalar� gerekiyor:
 * resim/A�.gif
 * resim/Sakla.gif
 */
public class J5c_14 extends JPanel implements ActionListener {
    static private final String yeniSat�r = "\n";
    JButton a�D��mesi, saklaD��mesi;
    JTextArea metinAlan�;
    JFileChooser dosyaSe�ici;

    public J5c_14() {// Kurucu...
        super (new BorderLayout());

        // Evvela metinAlan� yaratal�m, zira dinleyici a�/sakla sonu�lar�n� oraya beyanlayacak...
        metinAlan� = new JTextArea (5,20);
        metinAlan�.setMargin (new Insets (5,5,5,5));
        metinAlan�.setEditable (false); // Metin alan�na m�dahele etme...
        metinAlan�.setBackground (new Color (0.5f, 0.5f, 1.0f)); // HSB/HugeSaturationBrightness/RenkDoygunlukParlakl�k
        JScrollPane metinAlanl�Kayd�rmaPaneli = new JScrollPane (metinAlan�);

        // Varsay�l� kipi FILES_ONLY olan dosya se�icimizi yaratal�m...
        dosyaSe�ici = new JFileChooser();
        //dosyaSe�ici.setFileSelectionMode (JFileChooser.DIRECTORIES_ONLY);
        //dosyaSe�ici.setFileSelectionMode (JFileChooser.FILES_AND_DIRECTORIES);

        // �konlu A� d��mesi yaratal�m...
        a�D��mesi = new JButton ("Yeni bir Dosya A�", resim�konuYarat ("resim/A�.gif"));
        a�D��mesi.addActionListener (this);
        a�D��mesi.setBackground (new Color (0.9f, 0.5f, 0.9f));

        // �konlu Sakla d��mesi yaratal�m...
        saklaD��mesi = new JButton ("Mevcut Dosyay� Sakla", resim�konuYarat ("resim/Sakla.gif"));
        saklaD��mesi.addActionListener (this);
        saklaD��mesi.setBackground (new Color (0.7f, 0.5f, 0.9f));

        // Serim ama�l�, d��meleri ayr� bir panele koyal�m...
        JPanel d��mePaneli = new JPanel(); // Varsay�l� FlowLayout kullan�lmakta...
        d��mePaneli.add (a�D��mesi);
        d��mePaneli.add (saklaD��mesi);
        d��mePaneli.setBackground (new Color (0.0f, 0.0f, 0.0f));

        // D��me panelini ve kayd�rmal� metin alan�n� �er�evemize ekleyelim...
        add (d��mePaneli, BorderLayout.PAGE_START);
        add (metinAlanl�Kayd�rmaPaneli, BorderLayout.CENTER);
    } // J5c_14() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        // Dosya a� aksiyonunu y�netelim...
        if (olay.getSource() == a�D��mesi) {
            int d�n��De�eri = dosyaSe�ici.showOpenDialog (J5c_14.this);

            if (d�n��De�eri == JFileChooser.APPROVE_OPTION) {
                File dosya = dosyaSe�ici.getSelectedFile();
                // Buraya a��lan dosya i�eriklerini okuyup yans�tacak kodlamalar� koyabilirsiniz...
                metinAlan�.append ("[" + dosya.getName() + "] dosyas� a��l�yor (dese de inanma)!.." + yeniSat�r);
            }else metinAlan�.append ("A� komutu kullan�c� taraf�ndan iptal edildi." + yeniSat�r);
            metinAlan�.setCaretPosition (metinAlan�.getDocument().getLength());

        // Dosya sakla aksiyonunu y�netelim...
        }else if (olay.getSource() == saklaD��mesi) {
            int d�n��De�eri = dosyaSe�ici.showSaveDialog (J5c_14.this);
            if (d�n��De�eri == JFileChooser.APPROVE_OPTION) {
                File dosya = dosyaSe�ici.getSelectedFile();
                // Buraya i�erikleri ayn�/farkl� adl� dosyaya saklayacak kodlamalar� koyabilirsiniz...
                metinAlan�.append ("[" + dosya.getName() + "] dosyas� saklan�yor (dese de inanma)!.." + yeniSat�r);
            }else metinAlan�.append ("Sakla komutu kullan�c� taraf�ndan iptal edildi." + yeniSat�r);
            metinAlan�.setCaretPosition (metinAlan�.getDocument().getLength());
        } // d�� if-else karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    protected static ImageIcon resim�konuYarat (String yol) {
        java.net.URL resimYureli = J5c_14.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Dosya Se�ici G�sterim");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // extends JPanel'li i�eri�i penceremize ekleyelim...
        �er�eve.add (new J5c_14());

        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                // G�rHisset metal'�n bold yaz� fonunu kapatal�m...
                UIManager.put ("swing.boldMetal", Boolean.FALSE); 
                yaratVeG�sterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_14 s�n�f� sonu...