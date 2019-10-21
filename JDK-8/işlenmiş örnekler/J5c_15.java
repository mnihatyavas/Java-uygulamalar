// J5c_15.java: FileChooserDemo2 (DosyaSeçiciGösterimi2) örneði.

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.io.File;

/* Gerekli dosyalar:
 *   ImageFileView.java/J5c_15x2.java
 *   ImageFilter.java/J5c_15x1.java
 *   ImagePreview.java/J5c_15x3.java
 *   Utils.java/J5c_15x4.java
 *   resim/jpgIcon.gif (ImageFileView.java kullanýyor.)
 *   resim/gifIcon.gif (ImageFileView.java kullanýyor.)
 *   resim/tiffIcon.gif (ImageFileView.java kullanýyor.)
 *   resim/pngIcon.png (ImageFileView.java kullanýyor.)
 */
public class J5c_15 extends JPanel implements ActionListener {
    static private String yeniSatýr = "\n";
    private JTextArea metinAlaný;
    private JFileChooser dosyaSeçici;

    public J5c_15() {// Kurucu...
        super (new BorderLayout());

        metinAlaný = new JTextArea (5,20);
        metinAlaný.setMargin (new Insets (5,5,5,5));
        metinAlaný.setEditable (false);
        metinAlaný.setBackground (Color.CYAN);
        metinAlaný.setForeground (new Color (0.7865f,0.0f,0.5f));
        JScrollPane maKaydýrmaPanosu = new JScrollPane (metinAlaný);

        JButton ekleDüðmesi = new JButton ("Ýliþtir...");
        ekleDüðmesi.addActionListener (this);

        add (ekleDüðmesi, BorderLayout.PAGE_START);
        add (maKaydýrmaPanosu, BorderLayout.CENTER);
    } // J5c_15() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        // Dosya seçiciyi kurup filitre, ikon ve öngörüntü dosyalarýný ekleyelim...
        if (dosyaSeçici == null) {
            dosyaSeçici = new JFileChooser();

            // Geleneksel bir (resim) dosya filitresini ekleyip varsayýlýyý etkisizleyelim...
            dosyaSeçici.addChoosableFileFilter (new J5c_15x1());
            dosyaSeçici.setAcceptAllFileFilterUsed (false);

            // Dosya tipleri (.jpg/.jpeg/.gif/.tif/.tiff/.png) ikonlarýný ekleyelim...
            dosyaSeçici.setFileView (new J5c_15x2());

            // Týklanan filitreli resim dosyasýnýnýn öngörüntü panosunu ekleyelim...
            dosyaSeçici.setAccessory (new J5c_15x3 (dosyaSeçici));
        } // if kararý sonu...

        // Dosya seçici diyaloðunu gösterelim...
        int dönenDeðer = dosyaSeçici.showDialog (J5c_15.this, "Ýliþtir");

        // Seçilen öngörüntülü resim dosyasýný ekle veya vazgeç...
        if (dönenDeðer == JFileChooser.APPROVE_OPTION) {
            File dosya = dosyaSeçici.getSelectedFile();
            metinAlaný.append ("[" + dosya.getName() + "] resim dosyasýný iliþtiriyor (dese de bekleme)." + yeniSatýr);
        }else metinAlaný.append ("Kullanýcý iliþtirme iþleminden vazgeçti." + yeniSatýr);

        metinAlaný.setCaretPosition (metinAlaný.getDocument().getLength());

        // Dosya seçicideki seçiliyi bir sonraki seçim için null'la (boþalt)...
        dosyaSeçici.setSelectedFile (null);
    } // actionPerformed(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame("Dosya Seçici Gösterimi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_15());
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_15 sýnýfý sonu...